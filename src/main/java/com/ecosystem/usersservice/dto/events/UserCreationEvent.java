package com.ecosystem.usersservice.dto.events;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserCreationEvent {


    // часть контекста, дублируемого в user service

    @NotNull
    private UUID uuid;
    @NotBlank
    private String role;
    @NotBlank
    private String username;
}