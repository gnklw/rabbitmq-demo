package com.rabbitmq.receiver;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQMessageListener {

    @RabbitListener(queuesToDeclare = @Queue("${rabbitmq.receive.queues.messages}"))
    public ResponseDTO receiveMessage(MsgDTO message) {
        System.out.printf("(receiver) Received message: %s%s", message.getMessage(), System.lineSeparator());

        return new ResponseDTO()
                .setId(UUID.randomUUID().toString())
                .setMessage("Message is received!");
    }
}