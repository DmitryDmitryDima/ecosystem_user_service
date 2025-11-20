package com.ecosystem.usersservice.controller;


import com.ecosystem.usersservice.dto.SecurityContext;
import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/profile")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    основная информация о пользователе
    Доступна всем
     */
    @GetMapping("/{username}")
    public ResponseEntity<UserPropertiesDTO> showUser(@PathVariable("username") String observedUsername,
                                   @RequestHeader Map<String, String> headers){


        return ResponseEntity.ok(userService.getUserByUsername(observedUsername, SecurityContext.generateContext(headers)));




    }
}
