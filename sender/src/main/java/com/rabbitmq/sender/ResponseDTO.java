package com.rabbitmq.sender;

public class ResponseDTO {
    private String id;
    private String message;

    public String getId() {
        return id;
    }

    public ResponseDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
