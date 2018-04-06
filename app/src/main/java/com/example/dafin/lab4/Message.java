package com.example.dafin.lab4;

public class Message {
    private String message;
    private String name;
    private String profilePic;
    private String type_message;


    public Message(String message, String name, String profilePic, String type_message) {
        this.message = message;
        this.name = name;
        this.profilePic = profilePic;
        this.type_message = type_message;

    }

    public Message() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getType_message() {
        return type_message;
    }

    public void setType_message(String type_message) {
        this.type_message = type_message;
    }
}