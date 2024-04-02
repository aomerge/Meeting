package com.ASSESSMENT.Meeting.domain.service;

import com.ASSESSMENT.Meeting.config.StructureToken.UserToken;
import com.ASSESSMENT.Meeting.config.customExeption.UserNotExist;
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
        }catch (Exception e){
            throw  new UserNotExist(e.getMessage());
        }
    }
    public void delete(Long id) {
        userRepository.delete(id);
    }
    public User update(User user) {
        return userRepository.update(user);
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

}
