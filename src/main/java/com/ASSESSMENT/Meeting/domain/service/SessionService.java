package com.ASSESSMENT.Meeting.domain.service;

import com.ASSESSMENT.Meeting.config.StructureToken.SessionDto;
import com.ASSESSMENT.Meeting.config.customExeption.*;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.domain.repository.SessionRepository;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public List<SessionDto> getAll() {
        return sessionRepository.getAll();
    }
    public Session save(String token, Session session) {
        try {
           return sessionRepository.save(token,  session );
         } catch ( BadRequestDataException e){
          throw new BadRequestDataException(e.getMessage());
        } catch ( TokenNotAutorization e) {
            throw new TokenNotAutorization(e.getMessage());
        } catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public void delete(String token, Long id,String confirm) {
        try {
            sessionRepository.delete(token, id, confirm);
        } catch (SessionNotExist e) {
            throw new SessionNotExist(e.getMessage());
        }catch (Exception e) {
            throw new TokenNotAutorization(e.getMessage());
        }
    }
    public Session update(String Token, Long id , Session session) {
        try {
            return sessionRepository.update(Token, id , session);
        } catch (BadRequestDataException e) {
            throw new BadRequestDataException(e.getMessage());
        } catch ( TokenNotAutorization e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public User register(String token, Long id) {
        try {
            return sessionRepository.register(token, id);
        } catch (BadRequestDataException e) {
            throw new BadRequestDataException(e.getMessage());
        } catch ( TokenNotAutorization | IllegalArgumentException e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public User registerPrivate(String token, Long id, String code, String email) {
        try {
            return sessionRepository.registerPrivate(token, id, code, email);
        } catch (BadRequestDataException e) {
            throw new BadRequestDataException(e.getMessage());
        } catch ( TokenNotAutorization | IllegalArgumentException e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public User deletedRegister(String token, Long id) {
        try {
            return sessionRepository.deleteRegister(token, id);
        } catch (BadRequestDataException e) {
            throw new BadRequestDataException(e.getMessage());
        } catch ( TokenNotAutorization | IllegalArgumentException e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public Session getOne(Long id) {
        try {
            return sessionRepository.getOne(id);
        } catch (BadRequestDataException e) {
            throw new BadRequestDataException(e.getMessage());
        } catch ( TokenNotAutorization | IllegalArgumentException e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }

}
