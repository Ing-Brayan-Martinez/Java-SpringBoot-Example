package com.example.service.impl;


import com.example.service.MensajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessagesServicesImpl implements MensajesService {

	@Autowired
    private MessageSource messageSource;

    public String get(String code, String ... params) {
    	return messageSource.getMessage(code, params, Locale.forLanguageTag("ES"));
    }
}
