package com.example.home_doctor.Chat_Feature;

public class Send_Message_To_Firebase {
    String message;

    public Send_Message_To_Firebase(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
