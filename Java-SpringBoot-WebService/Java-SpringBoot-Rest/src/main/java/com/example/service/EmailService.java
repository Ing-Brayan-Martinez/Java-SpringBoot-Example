package com.example.service;

import com.example.service.dto.EmailDTO;

public interface EmailService {
    void sendMessage(EmailDTO emailDTO);
}
