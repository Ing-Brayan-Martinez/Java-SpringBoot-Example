package com.example.infraestructure.mail;

public class Correo {

    private String msg;
    private String recepient;
    private String subject;

    public Correo(String msg, String recepient, String subject) {
        this.msg = msg;
        this.recepient = recepient;
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRecepient() {
        return recepient;
    }

    public void setRecepient(String recepient) {
        this.recepient = recepient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
