package com.ASSESSMENT.Meeting.domain.component;

import com.ASSESSMENT.Meeting.config.StructureToken.SessionDto;
import com.ASSESSMENT.Meeting.config.customExeption.InternalServerError;
import com.ASSESSMENT.Meeting.config.service.ValidationService;
import com.ASSESSMENT.Meeting.domain.repository.SessionRepository;
import com.ASSESSMENT.Meeting.persistence.crud.AttendesCrudRepository;
import com.ASSESSMENT.Meeting.persistence.crud.SessionCrudRepository;
import com.ASSESSMENT.Meeting.persistence.crud.UserCrudRepository;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class sessionComponent implements SessionRepository {
    private final ValidationService _sessionService = new ValidationService();
    @Autowired
    private SessionCrudRepository sessionCrudRepository;
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Autowired
    private AttendesCrudRepository attendesCrudRepository;

    @Override
    public List<SessionDto> getAll() {
        List<Session> sessions = sessionCrudRepository.findAllByAccessType("PUBLIC");
        List<SessionDto> sessionDtos = sessions.stream().map(session -> {
            SessionDto sessionDto = new SessionDto();
            sessionDto.setSessionId(session.getSessionId());
            sessionDto.setSessionDescription(session.getSessionDescription());
            sessionDto.setSessionName(session.getSessionName());
            sessionDto.setSessionDuration(session.getSessionDuration());
            return sessionDto;
        }).toList();
        return sessionDtos;


    }

    @Override
    public Session save(String token, Session session) {
        System.out.println(session);
        _sessionService.Validation(session.getSessionDescription(), "Description is required");
        _sessionService.Validation(session.getSessionName(), "Name is required");
        _sessionService.Validation(session.getSessionSchedule(), "sesssion Schedule is required");
        _sessionService.Validation(session.getSessionDuration(), "Sesion Duration is required");
        Claims tokenClaims = _sessionService.GetDataToken(token);
        Long Id = Long.parseLong(tokenClaims.get("id").toString());
        User user = userCrudRepository.findById(Id).get();
        _sessionService.Validation(user, "User not found");
        Session savedSession = sessionCrudRepository.save(session);
        _sessionService.Validation(savedSession, "Session not saved");
        Attendees attended = new Attendees();
        attended.setSessionId(savedSession);
        attended.setUserId(user);
        attended.setAccessType(Attendees.AccessType.OWNER);
        Attendees savedAttendee = attendesCrudRepository.save(attended);
        if (savedAttendee.getAttendeesId() == null) {
            sessionCrudRepository.delete(savedSession);
            throw new IllegalArgumentException("Session not saved");
        }
        return savedSession;
    }

    @Override
    public void delete(String token, Long id ,String confirm) {
        _sessionService.Validation(confirm, "You confirm delete no exist or is null");
        Claims tokenClaims = _sessionService.GetDataToken(token);
        Long IdUser = Long.parseLong(tokenClaims.get("id").toString());
        Attendees attendees = attendesCrudRepository.findBySessionIdAndUserId(id, IdUser);
        _sessionService.Validation(attendees, "The user not be registered in the session");
        if (attendees.getAccessType() != Attendees.AccessType.OWNER) {
            throw new IllegalArgumentException("The user not is owner of the session");
        }
        if (!confirm.equals("yes")) {
            throw new IllegalArgumentException("Not confirm delete session");
        }
        attendesCrudRepository.delete(attendees);
        sessionCrudRepository.deleteById(id);
    }

    @Override
    public Session update(String token, Long idSession ,Session session) {

            _sessionService.Validation(session, "the body not can be null");
            Claims tokenClaims = _sessionService.GetDataToken(token);
            Long IdUser = Long.parseLong(tokenClaims.get("id").toString());
             Attendees attendees = attendesCrudRepository.findBySessionIdAndUserId(idSession, IdUser);
            _sessionService.Validation(attendees, "The user not is owner of the session");
            if (attendees.getAccessType() != Attendees.AccessType.OWNER) {
                throw new IllegalArgumentException("The user not is owner of the session");
            }
            Session SessionOld = sessionCrudRepository.findById(idSession).get();
            _sessionService.validateAndApplyIfNotNull(SessionOld, e -> e.setSessionDescription(session.getSessionDescription()), session.getSessionDescription(), "Description Is null");
            _sessionService.validateAndApplyIfNotNull(SessionOld, e -> e.setSessionName(session.getSessionName()), session.getSessionName(), "Name Is null");
            _sessionService.validateAndApplyIfNotNull(SessionOld, e -> e.setSessionDuration(session.getSessionDuration()), session.getSessionDuration(), "Duration Is null");
            _sessionService.validateAndApplyIfNotNull(SessionOld, e -> e.setSessionSchedule(session.getSessionSchedule()), session.getSessionSchedule(), "Schedule Is null");


            return sessionCrudRepository.save(SessionOld);

    }

    @Override
    public User register(String token, Long id) {
        _sessionService.Validation(id, "Id is required");
        Claims tokenClaims = _sessionService.GetDataToken(token);
        Long IdUser = Long.parseLong(tokenClaims.get("id").toString());
        Attendees attendees = attendesCrudRepository.findBySessionIdAndUserId(id, IdUser);
        if (attendees != null) {
            throw new IllegalArgumentException("The user is already registered in the session");
        }
        Session session = sessionCrudRepository.findBySessionIdAndAccessType(id, "PUBLIC");
        _sessionService.Validation(session, "This Session not exist or is private");
        User user = userCrudRepository.findById(IdUser).get();
        _sessionService.Validation(user, "User not found");
        Attendees attended = new Attendees();
        attended.setSessionId(session);
        attended.setUserId(user);
        attended.setAccessType(Attendees.AccessType.ATTENDEE);
        Attendees savedAttendee = attendesCrudRepository.save(attended);
        if (savedAttendee.getAttendeesId() == null) {
            throw new IllegalArgumentException("Not registered");
        }
         return userCrudRepository.findById(IdUser).get();
    }

    public User registerPrivate(String token, Long id, String code, String email) {
        _sessionService.Validation(id, "Id is required");
        _sessionService.Validation(code, "Code is required");
        _sessionService.Validation(email, "Email is required");
        Claims tokenClaims = _sessionService.GetDataToken(token);
        Long IdUser = Long.parseLong(tokenClaims.get("id").toString());
        String emailToken = tokenClaims.get("email").toString();
        if (!emailToken.equals(email)) {
            throw new IllegalArgumentException("The email is incorrect");
        }
        Attendees attendees = attendesCrudRepository.findBySessionIdAndUserId(id, IdUser);
        if (attendees != null) {
            throw new IllegalArgumentException("The user is already registered in the session");
        }
        Session session = sessionCrudRepository.findBySessionIdAndAccessType(id, "PRIVATE");
        _sessionService.Validation(session, "This Session not exist or is public");
        _sessionService.Validation(session.getAccessCode().equals(code), "The code is incorrect");
        User user = userCrudRepository.findByEmail(email);
        _sessionService.Validation(user, "User not found");
        Attendees attended = new Attendees();
        attended.setSessionId(session);
        attended.setUserId(user);
        attended.setAccessType(Attendees.AccessType.ATTENDEE);
        Attendees savedAttendee = attendesCrudRepository.save(attended);
        if (savedAttendee.getAttendeesId() == null) {
            throw new IllegalArgumentException("Not registered");
        }
        return userCrudRepository.findById(IdUser).get();
    }
    @Override
    public User deleteRegister(String token, Long id) {
        _sessionService.Validation(id, "Id is required");
        Claims tokenClaims = _sessionService.GetDataToken(token);
        Long IdUser = Long.parseLong(tokenClaims.get("id").toString());
        Attendees attendees = attendesCrudRepository.findBySessionIdAndUserId(id, IdUser);
        _sessionService.Validation(attendees, "The user not be registered in the session");
        attendesCrudRepository.delete(attendees);
        return userCrudRepository.findById(IdUser).get();
    }

    @Override
    public Session getOne(Long id) {
        _sessionService.Validation(id, "Id is required");
        Session session = sessionCrudRepository.findBySessionIdAndAccessType(id, "PUBLIC");
        _sessionService.Validation(session, "This Session not exist or is private");
        return session;
    }
}
