package com.yks.cmt.config;

import com.yks.cmt.entity.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Sender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(Message message){
        rabbitTemplate.setExchange("exchange");
        rabbitTemplate.setRoutingKey("routingKey");
        rabbitTemplate.convertAndSend(message);
        log.info("Send msg = {}", message);
    }

    public void sendAppointment(Message message){
        rabbitTemplate.setExchange("exchange2");
        rabbitTemplate.setRoutingKey("routingKey2");
        rabbitTemplate.convertAndSend(message);
        log.info("Send msg = {}", message);
    }
}
