package com.ecosystem.usersservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserProperties {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;


    @Column(unique = true, columnDefinition = "uuid")
    private UUID userUUID;







    @Column
    private String about;


    // todo тут будет ссылка на аватар, загруженный в облако.
    // у пользователя будет свое пространство в облаке, куда он сможет загружать свои файлы для проектов и rag системы
    // при этом дисковое пространство под проекты будет изолировано от диска, то есть по сути это будет два разных микросервиса с дисковым пространством
    @Column
    private String avatarLink;


    // todo реализация друзей - стратегии рассмотреть позже

}
