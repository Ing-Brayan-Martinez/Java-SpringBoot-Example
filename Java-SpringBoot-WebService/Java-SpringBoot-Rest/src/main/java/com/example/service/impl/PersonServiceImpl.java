package com.example.service.impl;

import com.example.convert.PersonConvert;
import com.example.domain.Person;
import com.example.repository.PersonRepository;
import com.example.service.EmailService;
import com.example.service.PersonService;
import com.example.service.UserAuditableService;
import com.example.service.dto.PersonDTO;
import com.example.service.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonConvert personConvert;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserAuditableService userAuditableService;

    @Override
    public Optional<PersonDTO> save(PersonDTO dto) {
        //emailService.sendMessage(new EmailDTO("hola", "brayanmartinez827@gmail.com", "Este es un correo de prueba"));

        return Optional.ofNullable(this.personRepository.insert(this.personConvert.fromDTO(dto)))
            .map(entity -> this.personConvert.toDTO(entity));
    }

    @Override
    public Optional<PersonDTO> update(PersonDTO dto) {
        final Optional<Person> user = this.personRepository.findById(dto.getParsonId());
        return user
            .map(entity -> this.personRepository.update(this.personConvert.fromDTO(entity, dto)))
            .map(entity -> this.personConvert.toDTO(entity));
    }

    @Override
    public Optional<PersonDTO> delete(Long id) {
        final Optional<Person> optional = this.personRepository.findById(id);

        final UserDTO audit = this.userAuditableService.getCurrentUser()
            .orElseThrow(() -> new UsernameNotFoundException("No existe usuario solicitado"));

        return optional.map(entity -> {
                entity.setIsActive(!entity.getIsActive());
                entity.setUpdated(new Timestamp(System.currentTimeMillis()));
                entity.setUpdatedBy(audit.getUserId());
                return entity;
            })
            .map(entity -> this.personRepository.update(entity))
            .map(result -> this.personConvert.toDTO(result));
    }

    @Override
    public Optional<PersonDTO> findById(Long id) {
        return this.personRepository.findById(id)
            .map(entity -> this.personConvert.toDTO(entity));
    }

    @Override
    public List<PersonDTO> findAll() {
        return this.personRepository.findAll().stream()
            .map(entity -> this.personConvert.toDTO(entity))
            .collect(Collectors.toList());
    }


}
