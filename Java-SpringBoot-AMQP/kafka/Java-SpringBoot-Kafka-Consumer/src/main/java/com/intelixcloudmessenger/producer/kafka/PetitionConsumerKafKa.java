package com.intelixcloudmessenger.producer.kafka;

import com.intelixcloudmessenger.producer.service.PetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PetitionConsumerKafKa {

    private static final Logger log = LoggerFactory.getLogger(PetitionConsumerKafKa.class);

    @Autowired
    private PetitionService service;

    @KafkaListener(topics = "mensageria")
    public void mail(String massenger) {
        log.info(massenger);
        service.saveMail(massenger);
    }

}
