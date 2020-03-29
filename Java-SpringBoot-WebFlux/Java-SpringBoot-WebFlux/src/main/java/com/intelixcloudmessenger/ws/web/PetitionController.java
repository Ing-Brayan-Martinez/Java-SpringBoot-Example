package com.intelixcloudmessenger.ws.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intelixcloudmessenger.ws.domain.MailPetition;
import com.intelixcloudmessenger.ws.service.PetitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@EnableAutoConfiguration
@CrossOrigin
@RequestMapping("/v1")
public class PetitionController {

    private static final Logger log = LoggerFactory.getLogger(PetitionController.class);
    private static String UPLOADED_FOLDER = "/var";

    @Autowired
    private PetitionService service;

    @PostMapping("/mail")
    public Mono<Void> mailAction(@RequestParam("json") String json) {

        try {
            final ObjectMapper objectMapper = new ObjectMapper();
            final MailPetition mail = objectMapper.readValue(json, MailPetition.class);

            mail.setIsAttached("N");
            mail.setAttached("");
            mail.setFileName("");

            final String result = objectMapper.writeValueAsString(mail);

            this.service.saveMail(result);

            log.info(result);
            log.info("listo......");

        } catch (IOException e) {
            log.error("Error al procesar el json.", e);
        }

        return Mono.empty();
    }


    @PostMapping("/mail/attached")
    public Mono<Void> mailAction(@RequestParam("json") String json, @RequestParam("file") MultipartFile file) {

        try {
            if (!Files.exists(Paths.get(UPLOADED_FOLDER))) {
                Files.createDirectory(Paths.get(UPLOADED_FOLDER));
            }

            if (file.isEmpty()) {
                log.info("El archivo estaba vacio.");
                return Mono.empty();
            }

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + "/" + file.getOriginalFilename());
            Files.write(path, bytes);


            final ObjectMapper objectMapper = new ObjectMapper();
            final MailPetition mail = objectMapper.readValue(json, MailPetition.class);

            mail.setIsAttached("Y");
            mail.setAttached(UPLOADED_FOLDER + "/" + file.getOriginalFilename());
            mail.setFileName(file.getOriginalFilename());

            final String result = objectMapper.writeValueAsString(mail);

            this.service.saveMail(result);

            log.info(result);
            log.info("listo......");

        } catch (IOException e) {
            log.error("Error al procesar el json.", e);

        }

        return Mono.empty();
    }

    @PostMapping("/sms")
    public Mono<Void> smsAction(@RequestBody String petition) {
        service.saveSms(petition);
        return Mono.empty();
    }

}
