package com.rabbitmq.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQMessageService {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQMessageService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends a message to the specified exchange with optional routing key, priority, and delay.
     *
     * @param exchange   The name of the exchange.
     * @param routingKey The routing key (optional for fanout exchanges).
     * @param message    The message to be sent.
     * @param priority   The priority of the message (optional).
     * @param delay      The delay in milliseconds before the message is sent (optional).
     */
    private <T> void sendMessage(String exchange, String routingKey, T message, Integer priority, Integer delay) {
        rabbitTemplate.convertAndSend(exchange, routingKey != null ? routingKey : "", message, m -> {
            if (priority != null) {
                m.getMessageProperties().setPriority(priority);
            }

            if (delay != null) {
                m.getMessageProperties().setDelay(delay);
            }

            return m;
        });
    }

    /**
     * Sends a message to the specified exchange using a routing key.
     *
     * @param exchange   The name of the exchange.
     * @param routingKey The routing key for the message.
     * @param message    The message to be sent.
     */
    public <T> void sendMessage(String exchange, String routingKey, T message) {
        this.sendMessage(exchange, routingKey, message, null, null);
    }

    /**
     * Sends a message to a fanout exchange.
     *
     * @param exchange The name of the fanout exchange.
     * @param message  The message to be sent.
     */
    public <T> void sendMessage(String exchange, T message) {
        this.sendMessage(exchange, null, message, null, null);
    }

    /**
     * Sends a delayed message to the specified exchange using a routing key.
     *
     * @param exchange   The name of the exchange.
     * @param routingKey The routing key for the message.
     * @param message    The message to be sent.
     * @param delay      The delay in milliseconds before the message is sent.
     */
    public <T> void sendDelayedMessage(String exchange, String routingKey, T message, int delay) {
        this.sendMessage(exchange, routingKey, message, null, delay);
    }

    /**
     * Sends a delayed message to a fanout exchange.
     *
     * @param exchange The name of the fanout exchange.
     * @param message  The message to be sent.
     * @param delay    The delay in milliseconds before the message is sent.
     */
    public <T> void sendDelayedMessage(String exchange, T message, int delay) {
        this.sendMessage(exchange, null, message, null, delay);
    }

    /**
     * Sends a message with priority to the specified exchange using a routing key.
     *
     * @param exchange   The name of the exchange.
     * @param routingKey The routing key for the message.
     * @param message    The message to be sent.
     * @param priority   The priority of the message.
     */
    public <T> void sendPriorityMessage(String exchange, String routingKey, T message, int priority) {
        this.sendMessage(exchange, routingKey, message, priority, null);
    }

    /**
     * Sends a priority message to a fanout exchange.
     *
     * @param exchange The name of the fanout exchange.
     * @param message  The message to be sent.
     * @param priority The priority of the message.
     */
    public <T> void sendPriorityMessage(String exchange, T message, int priority) {
        this.sendMessage(exchange, null, message, priority, null);
    }

    /**
     * Sends a message and waits for a reply.
     *
     * @param exchange     The name of the exchange.
     * @param routingKey   The routing key for the message.
     * @param message      The message to be sent.
     * @param responseType The type of the expected response.
     * @return The response of the type specified by responseType.
     */
    public <T, R> R sendAndReceive(String exchange, String routingKey, T message, Class<R> responseType) {
        return this.rabbitTemplate
                .convertSendAndReceiveAsType(exchange, routingKey, message, ParameterizedTypeReference.forType(responseType));
    }
}
