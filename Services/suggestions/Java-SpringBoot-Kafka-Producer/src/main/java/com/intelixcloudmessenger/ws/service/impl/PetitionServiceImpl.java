package com.intelixcloudmessenger.ws.service.impl;

import com.intelixcloudmessenger.ws.kafka.PetitionProducerKafka;
import com.intelixcloudmessenger.ws.service.PetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetitionServiceImpl implements PetitionService {

    private static final Logger log = LoggerFactory.getLogger(PetitionServiceImpl.class);

    @Autowired
    private PetitionProducerKafka producer;
    private int count = 0;

    @Override
    public void saveMail(String messenger) {
        log.info(messenger);
        log.info(String.valueOf(count++));
        this.producer.sendMail(messenger);
    }

    @Override
    public void saveSms(String messenger) {
        log.info(messenger);
        log.info(String.valueOf(count++));
        this.producer.sendSMS(messenger);
    }

}


