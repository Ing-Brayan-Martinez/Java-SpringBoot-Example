package com.example.service.impl;

import com.example.convert.PersonConverted;
import com.example.domain.Person;
import com.example.repository.impl.PersonRepositoryImpl;
import com.example.service.EmailService;
import com.example.service.PersonService;
import com.example.service.dto.PersonDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepositoryImpl repository;

    @Autowired
    private PersonConverted converted;

    @Autowired
    private EmailService manager;

    @Override
    public Optional<PersonDTO> save(PersonDTO dto) {
        final Person person = this.converted.fromDto(null);
        this.repository.insert(person);

        //manager.sendMessage(new EmailDTO("hola", "brayanmartinez827@gmail.com", "Este es un correo de prueba"));
        return Optional.empty();
    }

    @Override
    public Optional<PersonDTO> update(PersonDTO dto) {
        Person person = this.converted.fromDto(null);
        this.repository.update(person);
        return Optional.empty();
    }

    @Override
    public Optional<PersonDTO> delete(Long id) {
        this.repository.delete(id);
        return Optional.empty();
    }

    @Override
    public Optional<PersonDTO> findById(Long id) {
        Optional<Person> person = this.repository.findById(id);
        return Optional.empty();
    }

    @Override
    public List<PersonDTO> findAll() {
        List<Person> list = this.repository.findAll();
        return null;
    }
}
