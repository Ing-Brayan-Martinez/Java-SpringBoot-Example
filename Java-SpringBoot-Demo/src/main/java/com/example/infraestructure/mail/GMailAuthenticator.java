package com.example.infraestructure.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GMailAuthenticator extends Authenticator{

    private String email, password;

    public GMailAuthenticator(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(email, password);
    }

}