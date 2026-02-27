package com.ecosystem.usersservice.controller;


import com.ecosystem.usersservice.dto.SecurityContext;
import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/")
public class UserController {

    @Autowired
    private UserService userService;

    /*
    основная информация о пользователе
    Доступна всем, если допускают настройки приватности
     */
    @GetMapping("/getUserByUsername")
    public ResponseEntity<UserPropertiesDTO> getUser(
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
    /*
    возвращаем список профилей на основе батч запроса
     */

    @GetMapping("/getUsersByUUID")
    public ResponseEntity<List<UserPropertiesDTO>> getUsersByUUIDs(@RequestHeader Map<String, String> headers,
                                                                   @RequestParam("uuids") String uuids){
        SecurityContext securityContext = SecurityContext.generateContext(headers);

        List<UUID> parsedList = Arrays.stream(uuids.split(",")).map(UUID::fromString).toList();


        return ResponseEntity.ok(userService.getUsers(securityContext, parsedList));
    }
}
