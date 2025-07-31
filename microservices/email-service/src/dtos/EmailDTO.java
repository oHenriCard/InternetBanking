package com.finalproject.internet.banking.internetbanking.dtos;

public class EmailDTO {
    private String to;
    private String subject;
    private String body;

// GETTERS N SETTERS
    public String getTo() {
        return this.to;
    }
    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return this.subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return this.body;
    }
    public void setBody(String body) {
        this.body = body;
    }


}
