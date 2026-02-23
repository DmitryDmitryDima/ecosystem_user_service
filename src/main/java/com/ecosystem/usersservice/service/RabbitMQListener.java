package com.ecosystem.usersservice.service;

import com.ecosystem.usersservice.dto.UserPropertiesDTO;
import com.ecosystem.usersservice.dto.events.UserCreationEvent;
import com.ecosystem.usersservice.model.UserProperties;
import com.ecosystem.usersservice.repository.UserPropertiesRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component

public class RabbitMQListener {


    @Autowired
    private UserPropertiesRepository userPropertiesRepository;





    /*
    отлавливаем ивенты от auth сервера

    важно, что ивенты имели разную структуру сущностей - то есть не было двусмысленности при выборе handler'а
     */


    @RabbitListener(queues = {"${users.main-events.queue.name}"})
    public void receiveUserCreationEvent(@Payload String payload, @Header("event_type") String eventType) throws Exception {

        // todo данная логика больше не актуальна - пользователь создается лениво, при первом запросе после регистрации
        /*
        ObjectMapper objectMapper = new ObjectMapper();
        if (eventType.equals("user_creation")){
            UserCreationEvent userCreationEvent = objectMapper.readValue(payload, UserCreationEvent.class);

            System.out.println(userCreationEvent);
            processUserCreation(userCreationEvent);

        }

         */











    }

    private void processUserCreation(UserCreationEvent event){
        UserProperties newUserQuery = new UserProperties();
        newUserQuery.setUserUUID(event.getUuid());
        newUserQuery.setAbout("hello !");


        userPropertiesRepository.save(newUserQuery);
    }
}
