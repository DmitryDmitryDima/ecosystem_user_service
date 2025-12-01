package com.ecosystem.usersservice.controller;


import com.ecosystem.usersservice.dto.SecurityContext;
import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    основная информация о пользователе
    Доступна всем
     */
    @GetMapping("/{targetUUID}")
    public ResponseEntity<UserPropertiesDTO> showUser(@PathVariable("targetUUID") String targetUUID,
                                   @RequestHeader Map<String, String> headers){


        return ResponseEntity.ok(userService.getUser(UUID.fromString(targetUUID), SecurityContext.generateContext(headers)));




    }
}
