package com.rabbitmq.sender;

public class MsgDTO {
    private String id;
    private String message;

    public String getId() {
        return id;
    }

    public MsgDTO setId(String id) {
        this.id = id;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public MsgDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}

