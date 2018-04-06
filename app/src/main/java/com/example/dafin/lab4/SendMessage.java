package com.example.dafin.lab4;
/**
 * Created by dafin on 05-Apr-18.
 */

import java.util.Map;

public class SendMessage extends Message {
    private Map time;

    public SendMessage(String message, String name, String profilePic, String type_message, Map time) {
        super(message, name, profilePic, type_message);
        this.time = time;
    }

    public SendMessage(Map time) {
        this.time = time;
    }
    public SendMessage(){

    }

    public Map getTime() {
        return time;
    }

    public void setTime(Map time) {
        this.time = time;
    }
}
