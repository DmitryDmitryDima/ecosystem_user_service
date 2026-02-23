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

    public Optional<UserPropertiesDTO> getUser(SecurityContext securityContext, String targetUsername){

        if (securityContext.getTargetUUID()==null) {
            return Optional.empty();
        }



        Optional<UserProperties> propertiesCheck = userPropertiesRepository.findByUserUUID(securityContext.getTargetUUID());
        if (propertiesCheck.isEmpty()){
            if (securityContext.getUuid().equals(securityContext.getTargetUUID())){
                UserProperties newUserQuery = new UserProperties();
                newUserQuery.setUserUUID(securityContext.getUuid());
                newUserQuery.setAbout("hello from "+targetUsername+"!");
                newUserQuery = userPropertiesRepository.saveAndFlush(newUserQuery);
                UserPropertiesDTO dto = UserPropertiesDTO.builder()
                        .about(newUserQuery.getAbout())
                        .build();

                return Optional.of(dto);
            }
            else return Optional.empty();
        }

        UserProperties properties = propertiesCheck.get();



        return Optional.of(UserPropertiesDTO.builder()
                .about(properties.getAbout())
                .avatarLink(properties.getAvatarLink())
                .build());
    }









}
