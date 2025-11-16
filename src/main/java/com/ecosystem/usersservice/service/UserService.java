package com.ecosystem.usersservice.service;

import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.model.UserProperties;
import com.ecosystem.usersservice.repository.UserPropertiesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private UserPropertiesRepository userPropertiesRepository;

    // uuid того, кто запрашивает, берется из headers
    public UserPropertiesDTO getUserPropertiesByUUID(UUID uuid){
        Optional<UserProperties> user = userPropertiesRepository.findByUserUUID(uuid);
        return null;
    }




}
