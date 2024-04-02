package com.ASSESSMENT.Meeting.web.controller;


import com.ASSESSMENT.Meeting.config.Error.ApiError;
import com.ASSESSMENT.Meeting.config.customExeption.DtoNotAutorizate;
import com.ASSESSMENT.Meeting.config.customExeption.SessionNotExist;
import com.ASSESSMENT.Meeting.config.customExeption.UserNotExist;
import com.ASSESSMENT.Meeting.domain.service.SessionService;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SessionController {
    private final SessionService sessionService;
    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }
    @PostMapping
    @RequestMapping("/session/createSession")
    public ResponseEntity<?> createSession(@RequestHeader(name = "Authorization", required = false) String token,  @RequestBody Session createSession) {
        try {
            Session savedSession = sessionService.save(token, createSession);
            return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
        } catch ( UserNotExist e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch ( IllegalArgumentException e){
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,errorMessage), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    @RequestMapping("/session/updateSession")
    public ResponseEntity<?> updateSession(@RequestHeader(name = "Authorization" , required = false) String token, @RequestBody Session updateSession) {
        try {
            Session updatedSession = sessionService.update(token, updateSession);
            return new ResponseEntity<>(updatedSession, HttpStatus.OK);
        } catch (SessionNotExist e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,errorMessage), HttpStatus.BAD_REQUEST);
        } catch (DtoNotAutorizate e) {
            String errorMessage = "Error: " + e.getMessage();
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,errorMessage), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
