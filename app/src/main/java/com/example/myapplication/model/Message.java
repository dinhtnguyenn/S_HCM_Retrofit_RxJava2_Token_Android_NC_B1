package com.example.myapplication.model;

public class Message {
    private int status;
    private String notification;

    public Message(int status, String notification) {
        this.status = status;
        this.notification = notification;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
