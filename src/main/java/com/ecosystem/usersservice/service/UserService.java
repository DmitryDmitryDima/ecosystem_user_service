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



    // todo все случаи, касающиеся безопасности и прав, будут генерировать специальное исклюение

    public Optional<UserPropertiesDTO> getUser(SecurityContext securityContext){

        if (securityContext.getTargetUUID()==null) {
            return Optional.empty();
        }



        Optional<UserProperties> propertiesCheck = userPropertiesRepository.findByUserUUID(securityContext.getTargetUUID());
        if (propertiesCheck.isEmpty()) return Optional.empty();

        UserProperties properties = propertiesCheck.get();
        System.out.println(properties);


        return Optional.of(UserPropertiesDTO.builder()
                .about(properties.getAbout())
                .avatarLink(properties.getAvatarLink())
                .build());
    }









}
