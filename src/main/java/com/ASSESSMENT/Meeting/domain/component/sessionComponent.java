package com.ASSESSMENT.Meeting.domain.component;

import com.ASSESSMENT.Meeting.config.service.SessionService;
import com.ASSESSMENT.Meeting.config.service.UserService;
import com.ASSESSMENT.Meeting.domain.repository.SessionRepository;
import com.ASSESSMENT.Meeting.persistence.crud.SessionCrudRepository;
import com.ASSESSMENT.Meeting.persistence.crud.UserCrudRepository;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class sessionComponent implements SessionRepository {
    private  final UserService _userService = new UserService();
    private final SessionService _sessionService = new SessionService();
    @Autowired
    private SessionCrudRepository sessionCrudRepository;
    @Autowired
    private UserCrudRepository userCrudRepository;

    @Override
    public List<Session> getAll() {
        return (List<Session>) sessionCrudRepository.findAll();
    }

    @Override
    public Session save(String token, Session session) {
        _userService.ValidMethod(session.getSessionDescription(), "Description is required");
        _userService.ValidMethod(session.getSessionName(), "Name is required");
        _sessionService.ValidationDate(session.getSessionSchedule(), "sesssion Schedule is required");
        _sessionService.ValidationFloat(session.getSessionDuration(), "Sesion Duration is required");
        Claims tokenClaims = _userService.GetDataToken(token);
        Long Id = Long.parseLong(tokenClaims.get("id").toString());
        User user = userCrudRepository.findById(Id).get();
        _userService.EmailNotExist(user);

        return sessionCrudRepository.save(session);
    }

    @Override
    public void delete(String token, String confirm) {

    }

    @Override
    public Session update(String Token, Session session) {
        return null;
    }
}
