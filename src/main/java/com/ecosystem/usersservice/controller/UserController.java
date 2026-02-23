package com.ecosystem.usersservice.controller;


import com.ecosystem.usersservice.dto.SecurityContext;
import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    основная информация о пользователе
    Доступна всем, если допускают настройки приватности
     */
    @GetMapping
    public ResponseEntity<UserPropertiesDTO> showUser(
                                   @RequestHeader Map<String, String> headers, @RequestParam("targetUsername") String targetUsername){

        System.out.println("user properties extraction");

        SecurityContext securityContext = SecurityContext.generateContext(headers);
        UUID target = securityContext.getTargetUUID();
        if (target == null){
            return ResponseEntity.notFound().build();
        }

        Optional<UserPropertiesDTO> userPresenceCheck = userService.getUser(securityContext, targetUsername);

        return userPresenceCheck.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());







    }
}
