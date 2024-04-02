package com.ASSESSMENT.Meeting.domain.service;

import com.ASSESSMENT.Meeting.config.customExeption.SessionNotExist;
import com.ASSESSMENT.Meeting.config.customExeption.TokenNotAutorization;
import com.ASSESSMENT.Meeting.config.customExeption.UserNotExist;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.domain.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> getAll() {
        return sessionRepository.getAll();
    }
    public Session save(String token, Session session) {
        try {
           return sessionRepository.save(token,  session );
         } catch ( UserNotExist e){
          throw new UserNotExist(e.getMessage());
        } catch ( IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new TokenNotAutorization(e.getMessage());
        }
    }
    public void delete(String token, String confirm) {
        try {
            sessionRepository.delete(token, confirm);
        } catch (SessionNotExist e) {
            throw new SessionNotExist(e.getMessage());
        }catch (Exception e) {
            throw new TokenNotAutorization(e.getMessage());
        }
    }
    public Session update(String Token,  Session session) {
        try {
            return sessionRepository.update(Token , session);
        } catch (SessionNotExist e) {
            throw new SessionNotExist(e.getMessage());
        } catch (Exception e) {
            throw new TokenNotAutorization(e.getMessage());
        }
    }
}
