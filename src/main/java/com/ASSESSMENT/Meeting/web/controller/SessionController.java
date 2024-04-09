package com.ASSESSMENT.Meeting.web.controller;


import com.ASSESSMENT.Meeting.config.Error.ApiError;
import com.ASSESSMENT.Meeting.config.customExeption.*;
import com.ASSESSMENT.Meeting.domain.service.SessionService;
import com.ASSESSMENT.Meeting.persistence.entity.Attendees;
import com.ASSESSMENT.Meeting.persistence.entity.Session;
import com.ASSESSMENT.Meeting.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE })
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
        } catch ( BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch ( TokenNotAutorization e){
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(path = "/session/updateSession/{id}",consumes = "application/json")
    public ResponseEntity<?> updateSession(@RequestHeader(name = "Authorization" , required = false) String token, @PathVariable Long id, @RequestBody(required = false) Session updateSession){
        try {
            Session updatedSession = sessionService.update(token, id ,updateSession);
            return new ResponseEntity<>(updatedSession, HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (TokenNotAutorization e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(path = "/session/deleteSession/{id}")
    public ResponseEntity<?> deleteSession(@RequestHeader(name = "Authorization" , required = false) String token, @PathVariable Long id, @Param("confirm") String confirm){
        try {
            sessionService.delete(token, id , confirm);
            return new ResponseEntity<>( new ApiError(HttpStatus.OK, "User Deleted"), HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (TokenNotAutorization e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    @RequestMapping("/session/{id}/register")
    public ResponseEntity<?> registerSession(@RequestHeader(name = "Authorization" , required = false) String token, @PathVariable Long id){
        try {
            User session =  sessionService.register(token, id);
            return new ResponseEntity<>( session, HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (TokenNotAutorization e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    @RequestMapping("/session/{id}/register/private")
    public ResponseEntity<?> registerSessionPrivate(@RequestHeader(name = "Authorization" , required = false) String token, @PathVariable Long id, @Param("key") String code, @Param("Email") String email){
        try {
            User session =  sessionService.registerPrivate(token, id, code, email);
            return new ResponseEntity<>( session, HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (TokenNotAutorization e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping(path = "/session/{id}/register/delete")
    public ResponseEntity<?> deleteRegisterSession(@RequestHeader(name = "Authorization" , required = false) String token, @PathVariable Long id){
        try {
            sessionService.deletedRegister(token, id);
            return new ResponseEntity<>( new ApiError(HttpStatus.OK, "Register Deleted of the session"), HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.BAD_REQUEST,e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (TokenNotAutorization e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.UNAUTHORIZED,e.getMessage()), HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/session")
    public ResponseEntity<?> getAllSession(){
        try {
            return new ResponseEntity<>( sessionService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(path = "/session/{id}")
    public ResponseEntity<?> getSession(@PathVariable Long id){
        try {
            return new ResponseEntity<>( sessionService.getOne(id), HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.NOT_FOUND,e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /* @GetMapping(Path = "/session/User/{id}")
    public ResponseEntity<?> getUserSession(@RequestHeader(name = "Authorization" , required = false)){
        try {
            return new ResponseEntity<>( sessionService.getUserSession(id), HttpStatus.OK);
        } catch (BadRequestDataException e) {
            return new ResponseEntity<>( new ApiError(HttpStatus.NOT_FOUND,e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(new ApiError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }*/
}
