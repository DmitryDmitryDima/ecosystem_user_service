package com.ecosystem.usersservice.repository;

import com.ecosystem.usersservice.model.UserProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPropertiesRepository extends JpaRepository<UserProperties, Long> {


    Optional<UserProperties> findByUserUUID(UUID uuid);




}
