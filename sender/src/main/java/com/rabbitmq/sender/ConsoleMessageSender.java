package com.rabbitmq.sender;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.UUID;

@Component
public class ConsoleMessageSender implements CommandLineRunner {

    private final RabbitMQMessageService rabbitMQMessageService;

    public ConsoleMessageSender(RabbitMQMessageService rabbitMQMessageService) {
        this.rabbitMQMessageService = rabbitMQMessageService;
    }

    @Override
    public void run(String... args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.print("(sender) Enter your message (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if ("exit".equalsIgnoreCase(message)) {
                    break;
                }

                ResponseDTO responseDTO = this.rabbitMQMessageService.sendAndReceive(
                        "receiverExchange",
                        "receiverRoutingKey",
                        new MsgDTO().setId(UUID.randomUUID().toString()).setMessage(message),
                        ResponseDTO.class
                );

                System.out.printf("(sender) Response: %s%s", responseDTO.getMessage(), System.lineSeparator());
            }
        }
    }
}