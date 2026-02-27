package com.ecosystem.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPropertiesDTO {

    private String about;
    private UUID uuid;


    // system storage -> images (обезличено)
    private String avatarLink;


}
