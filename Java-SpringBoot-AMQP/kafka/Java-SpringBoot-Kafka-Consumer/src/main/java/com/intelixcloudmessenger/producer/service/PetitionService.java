package com.intelixcloudmessenger.producer.service;

public interface PetitionService {

    void saveMail(String petition);

    void saveSMS(String petition);
}
