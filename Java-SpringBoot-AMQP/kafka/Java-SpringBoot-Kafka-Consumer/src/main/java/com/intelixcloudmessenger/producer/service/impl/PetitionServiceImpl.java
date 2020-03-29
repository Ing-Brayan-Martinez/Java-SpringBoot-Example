package com.intelixcloudmessenger.producer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelixcloudmessenger.producer.domain.Petition;
import com.intelixcloudmessenger.producer.domain.messenger.MailPetition;
import com.intelixcloudmessenger.producer.domain.messenger.SmsPetition;
import com.intelixcloudmessenger.producer.repository.PetitionRepository;
import com.intelixcloudmessenger.producer.service.PetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class PetitionServiceImpl implements PetitionService {

    private static final Logger log = LoggerFactory.getLogger(PetitionServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PetitionRepository petitionRepository;

    @Override
    public void saveMail(String petition) {

        try {
            final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            final ObjectMapper objectMapper = new ObjectMapper();
            final MailPetition value = objectMapper.readValue(petition, MailPetition.class);

            final Timestamp created = new Timestamp(formatter.parse(value.getCreated()).getTime());

            final Petition mail = new Petition();
            mail.setId(value.getPetitionID());
            mail.setIsActive("Y");
            mail.setCreated(created);
            mail.setIpAddress(value.getIpAddress());
            mail.setMacAddress(value.getMacAddress());
            mail.setStatus(value.getStatus());
            mail.setType(value.getType());
            mail.setCustomer(value.getCustomer());
            mail.setPassword(value.getPassword());
            mail.setPetition(petition);

            this.petitionRepository.save(mail);

            final HttpEntity<String> request = new HttpEntity<>(petition);
            this.restTemplate.postForObject("http://com-intelixcloudmesenger-mail-consumer/v1/mail", request, String.class);

            log.debug(petition);

        } catch (IOException e) {
            log.error("Error al procesar el correo", e);

        } catch (ParseException e) {
            log.error("Error al procesar el correo", e);

        }

    }

    @Override
    public void saveSMS(String petition) {

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final SmsPetition value = objectMapper.readValue(petition, SmsPetition.class);

            final Petition sms = new Petition();
            sms.setId(value.getPetitionID());
            sms.setIsActive("Y");
            sms.setCreated(value.getCreated());
            sms.setStatus(value.getStatus());
            sms.setType(value.getType());
            sms.setPetition(petition);

            this.petitionRepository.save(sms);

            final HttpEntity<String> request = new HttpEntity<>(petition);
            this.restTemplate.postForObject("http://com-intelixcloudmesenger-mail-consumer/v1/sms", request, String.class);

            log.debug(petition);

        } catch (IOException e) {
            log.error("Error al procesar el sms", e);

        }
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}


