package com.example.service.dto;

import lombok.Data;

@Data
public class EmailDTO {

    private String msg;
    private String recepient;
    private String subject;

    public EmailDTO(String msg, String recepient, String subject) {
        this.msg = msg;
        this.recepient = recepient;
        this.subject = subject;
    }

}
