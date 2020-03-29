
package com.intelixcloudmessenger.producer.web;

import com.intelixcloudmessenger.producer.kafka.PetitionProducerKafka;
import com.intelixcloudmessenger.producer.service.PetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
@CrossOrigin
public class HomeController {

    @GetMapping("/")
    public String hola() {
        return "<h1>Este es el microservicio productor, echo con Spring Framework.<h1>";
    }

    @Autowired
    private PetitionProducerKafka kafka;

    @GetMapping("/kafka")
    public String test() {
        kafka.send("Hola desde apache kafka");
        return "<h1>Probando Kafka <h1>";
    }

    @Autowired
    private PetitionService service;

    @GetMapping("/test")
    public String mailAction() {
        String petition = "{\n" +
        "  \"petitionID\" : \"53f96195-d2e3-444c-a824-45d08f22715a\",\n" +
        "  \"created\" : 1561135740664,\n" +
        "  \"ipAddress\" : \"10.1.209.96\",\n" +
        "  \"macAddress\" : \"F8-0F-41-44-E5-5A\",\n" +
        "  \"status\" : \"CREATE\",\n" +
        "  \"customer\" : \"PT\",\n" +
        "  \"password\" : \"123456\",\n" +
        "  \"type\" : \"Mail\",\n" +
        "  \"messenger\" : {\n" +
        "    \"to\" : [ \"bmartinez@intelix.biz\", \"dcortez@intelix.biz\", \"mameliach@intelix.biz\" ],\n" +
        "    \"subject\" : \"Prueba\",\n" +
        "    \"content\" : \"Este es un contenido de prueba\"\n" +
        "  }\n" +
        "}";
        service.saveMail(petition);

        return "<h1>Probando Ribbon <h1>";
    }


}
