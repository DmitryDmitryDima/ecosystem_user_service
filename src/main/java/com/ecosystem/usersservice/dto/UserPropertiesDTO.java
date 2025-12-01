package com.ecosystem.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPropertiesDTO {

    private String about;


    // system storage -> images (обезличено)
    private String avatarLink;


}
