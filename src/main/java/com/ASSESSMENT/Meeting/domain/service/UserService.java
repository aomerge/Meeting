package com.ASSESSMENT.Meeting.domain.service;

import com.ASSESSMENT.Meeting.config.StructureToken.UserToken;
import com.ASSESSMENT.Meeting.config.customExeption.*;
import com.ASSESSMENT.Meeting.domain.repository.UserRepository;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.getAll();
    }
    public User save(User user) {
        try {
           return userRepository.save( user );
        }catch (BadRequestDataException e){
            throw  new BadRequestDataException(e.getMessage());
        }catch (TokenNotAutorization e){
            throw  new TokenNotAutorization(e.getMessage());
        }catch (Exception e){
            throw new InternalServerError(e.getMessage());
        }
    }
    public void delete(String token, String confirm) {
        try {
            userRepository.delete(token, confirm);
        } catch (UserNotExist e) {
            throw new UserNotExist(e.getMessage());
        }catch ( TokenNotAutorization e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public User update(String Token,  User user) {
        try {
            return userRepository.update(Token , user);
        } catch (UserNotExist e) {
            throw new UserNotExist(e.getMessage());
        }catch (TokenNotAutorization e){
            throw new TokenNotAutorization(e.getMessage());
        }catch (Exception e) {
            throw new InternalServerError(e.getMessage());
        }
    }
    public String getTest() {
        return userRepository.getTest();
    }
    public UserToken login(User user) {
        try {
            return userRepository.login(user);
        } catch (Exception e) {
            throw new UserNotExist(e.getMessage());
        }
    }
    public User tokenAuth(String token) {
        try {
            return userRepository.tokenAuth(token);
        }catch ( UserNotExist e){
            throw new UserNotExist(e.getMessage());
        } catch (Exception e) {
            throw new TokenNotAutorization(e.getMessage());
        }
    }

}
