package com.ASSESSMENT.Meeting.web.controller;

import com.ASSESSMENT.Meeting.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorld {
    private final UserService userService;
    @Autowired
    public HelloWorld(UserService userService) {
        this.userService = userService;
    }
    @RequestMapping("/world")
    @GetMapping
    public String helloWorld() {
        return userService.getTest();
    }
}
