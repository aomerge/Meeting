package com.ASSESSMENT.Meeting.domain.component;

import com.ASSESSMENT.Meeting.config.StructureToken.UserToken;
import com.ASSESSMENT.Meeting.config.customExeption.TokenNotAutorization;
import com.ASSESSMENT.Meeting.config.customExeption.UserNotExist;
import com.ASSESSMENT.Meeting.config.service.UserService;
import com.ASSESSMENT.Meeting.persistence.crud.UserCrudRepository;
import com.ASSESSMENT.Meeting.domain.repository.UserRepository;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import io.jsonwebtoken.Claims;
import org.antlr.v4.runtime.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserComponent implements UserRepository {
    private final UserService _userService = new UserService();
    /**/
    @Autowired
    private UserCrudRepository userCrudRepository;
    @Override
    public String getTest() {
        return "Test";
    }
    @Override
    public List<User> getAll() {
        return (List<User>) userCrudRepository.findAll();
    }
    @Override
    public User save (User user) throws UserNotExist {
        UserService userService = new UserService();
        userService.ValidMethod(user.getEmail(), "Email is null");
        userService.ValidMethod(user.getPassword(), "Password is null");
        userService.ValidMethod(user.getUserName(), "User Name is null");
        userService.ContainsSpecialCharacter(user.getUserName());
        userService.ContainsSpecialCharacter(user.getPassword());
        userService.ContainsSpecialCharacter(user.getEmail());
        userService.ContainsSpecialCharacter(user.getSubName());
        String hashedPassword = userService.EncodePassword(user.getPassword());
        user.setPassword(hashedPassword);
        User userDto = userCrudRepository.findByEmail(user.getEmail());
        System.out.println(userDto);
        userService.EmailExist(userDto);

        return userCrudRepository.save(user);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userCrudRepository.findById(id);
    }
    @Override
    public UserToken login(User user) {
        try{
            UserService userService = new UserService();
            userService.ValidMethod(user.getEmail(), "Email is null");
            userService.ValidMethod(user.getPassword(), "Password is null");
            User userDto = userCrudRepository.findByEmail(user.getEmail());
            userService.EmailNotExist(userDto);
            userService.CheckPassword(user.getPassword(), userDto.getPassword());
            String Token = userService.GenerateToken(userDto);
            UserToken userToken = new UserToken();
            userToken.setToken(Token);
            userToken.setEmail(userDto.getEmail());
            userToken.setUsername(userDto.getUserName());
            userToken.setId(userDto.getUserId().toString());

            return userToken;
        }catch (Exception e){
            throw new UserNotExist(e.getMessage());
        }
    }
    @Override
    public User tokenAuth(String token) {
        UserService userService = new UserService();
        Claims claims = userService.GetDataToken(token);
        User userDto = userCrudRepository.findByEmailAndUserId(claims.get("email").toString(), Long.parseLong(claims.get("id").toString()));
        userService.EmailNotExist(userDto);
        return userDto;
    }
    @Override
    public void delete( String token, String confirm) {
            _userService.ValidMethod(token, "Token is null");
            _userService.ValidMethod(confirm, "Confirm is null");
            Claims claims = _userService.GetDataToken(token);
            if (confirm == null || !confirm.equals(claims.get("username"))) {
                throw new UserNotExist("User not confirm delete");
            }
            userCrudRepository.deleteById(Long.parseLong(claims.get("id").toString()));
    }

    @Override
    public User update(String Token, User user) {
        _userService.ValidMethod(Token, "Token is null");
        _userService.ValidMethodUser(user, "The body is Required");
        Claims claims = _userService.GetDataToken(Token);
        User userDto = userCrudRepository.findByEmailAndUserId(claims.get("email").toString(), Long.parseLong(claims.get("id").toString()));
        _userService.ValidMethodUser(userDto, "User not found");
        userDto.setUserName(user.getUserName());
        userDto.setSubName(user.getSubName());
        User Email =  userCrudRepository.findByEmail(user.getEmail());
        _userService.EmailExist(Email);
        userDto.setEmail(user.getEmail());

        return userCrudRepository.save(userDto);
    }

}
