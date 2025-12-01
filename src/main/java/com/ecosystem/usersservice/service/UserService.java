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



    // todo добавляем проверку безопасности - к примеру. профиль может быть закрыт

    public UserPropertiesDTO getUser(UUID target, SecurityContext securityContext){

        Optional<UserProperties> propertiesCheck = userPropertiesRepository.findByUserUUID(target);
        if (propertiesCheck.isEmpty()) throw new IllegalStateException("user not found");

        UserProperties properties = propertiesCheck.get();


        return UserPropertiesDTO.builder()
                .about(properties.getAbout())
                .avatarLink(properties.getAvatarLink())
                .build();
    }









}
