package com.ecosystem.usersservice.service;

import com.ecosystem.usersservice.dto.SecurityContext;
import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.model.UserProperties;
import com.ecosystem.usersservice.repository.UserPropertiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

/*
данный сервис работает с фронтенд запросами, внутренние ивенты не взаимодейтвуются с ним
 */
@Service
public class UserService {

    @Autowired
    private UserPropertiesRepository userPropertiesRepository;



    // todo пока что варианты для зарегистрированных пользователей
    public UserPropertiesDTO getUserByUsername(String username, SecurityContext securityContext){
        System.out.println(securityContext+" for"+username);

        return new UserPropertiesDTO();
    }









}
