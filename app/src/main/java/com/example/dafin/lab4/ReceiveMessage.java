package com.example.dafin.lab4;

public class ReceiveMessage extends Message{
    private Long time;

    public ReceiveMessage(){

    }

    public ReceiveMessage(Long time) {
        this.time = time;
    }

    public ReceiveMessage(String message, String name, String profilePic, String type_message, Long time) {
        super(message, name, profilePic, type_message);
        this.time = time;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
