# RabbitMQ Console Application README

## Overview

This RabbitMQ Console Application demonstrates a basic message sending and receiving system using two microservices. The application is built in Java and utilizes the RabbitMQ messaging system for asynchronous communication. 

### Key Components:

1. **RabbitMQMessageListener**: A component that listens for incoming messages on a specified queue and processes them.
2. **RabbitMQMessageService**: A service responsible for sending messages to different types of exchanges, with additional features like setting priorities and delays.

## Features

1. **Message Reception and Response**: 
    - The `RabbitMQMessageListener` component receives messages and prints them to the console.
    - Upon receiving a message, it generates a unique ID and responds with a confirmation message.

2. **Sending Messages**:
    - The application allows the user to input messages via the console.
    - These messages are then sent to the `RabbitMQMessageService` for processing.

3. **Advanced Messaging Options**:
    - Messages can be sent with specific priorities and delays.
    - Supports different types of exchanges, including direct and fanout exchanges.
    - Capable of sending delayed and priority messages.

4. **Request-Reply Pattern**:
    - Implements a request-reply pattern where a message is sent and a response is awaited.

## Usage

1. **Starting the Application**:
    - Run the application. It initiates a console-based interface.
    - The user is prompted to enter messages.

2. **Sending Messages**:
    - Type your message in the console.
    - For exiting the application, type `exit`.

3. **Receiving Responses**:
    - After sending a message, the application displays a response received from the `RabbitMQMessageListener`.

## Configuration

- Ensure RabbitMQ is properly set up and configured.
- The queue names and exchange details should be specified in the application properties.

## Dependencies

- Java 17
- RabbitMQ Server
- Spring Boot 3.2.* (Spring AMQP, Spring Web)

## Notes

- This is a demonstration project. In a production environment, consider adding error handling, logging, and security measures.
- The application's effectiveness depends on the proper configuration of RabbitMQ and network settings.

---

For further details, please refer to the source code and accompanying comments.
