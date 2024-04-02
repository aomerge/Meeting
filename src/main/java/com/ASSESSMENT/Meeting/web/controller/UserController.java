package com.ASSESSMENT.Meeting.web.controller;

import com.ASSESSMENT.Meeting.config.Error.ApiError;
import com.ASSESSMENT.Meeting.config.StructureToken.UserToken;
import com.ASSESSMENT.Meeting.config.customExeption.DtoNotAutorizate;
import com.ASSESSMENT.Meeting.config.customExeption.EmailExist;
import com.ASSESSMENT.Meeting.config.customExeption.TokenNotAutorization;
import com.ASSESSMENT.Meeting.config.customExeption.UserNotExist;
import com.ASSESSMENT.Meeting.domain.service.UserService;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping
    @RequestMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User createUser) {
        try {
            User savedUser = userService.save(createUser);
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        } catch (UserNotExist e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch ( DtoNotAutorizate e){
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginUser) {
        try {
            UserToken savedUser = userService.login(loginUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (UserNotExist e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch ( DtoNotAutorizate e){
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/testAuth")
    public ResponseEntity<?> testAuth(@RequestHeader("Authorization") String token) {
        try {
            User userToken = userService.tokenAuth(token);
            return new ResponseEntity<>(userToken, HttpStatus.OK);
        } catch ( TokenNotAutorization e){
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,errorMessage), HttpStatus.UNAUTHORIZED);
        }catch (UserNotExist e){
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        }catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/user/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader(name = "Authorization", required = false, defaultValue = "") String token, @RequestParam("confirm") String Confirm) {
        try {
            userService.delete(token, Confirm);
            return new ResponseEntity<>(new ApiError(HttpStatus.OK, "User Delete Susses"),HttpStatus.OK);
        }catch (TokenNotAutorization e){
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,errorMessage), HttpStatus.UNAUTHORIZED);
        }catch (UserNotExist e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@RequestHeader(name = "Authorization", required = false, defaultValue = "") String Token,  @RequestBody(required = false) User updateUser) {
        try {
            User savedUser = userService.update(Token, updateUser);
            return new ResponseEntity<>(savedUser, HttpStatus.OK);
        } catch (UserNotExist e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
