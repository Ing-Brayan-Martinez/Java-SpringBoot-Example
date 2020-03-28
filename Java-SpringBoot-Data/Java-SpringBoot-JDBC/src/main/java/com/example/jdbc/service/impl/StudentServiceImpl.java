package com.example.jdbc.service.impl;

import com.example.jdbc.service.StudentService;
import com.example.jdbc.service.dto.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public Optional<StudentDTO> save(StudentDTO dto) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentDTO> update(StudentDTO dto) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentDTO> delete(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentDTO> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<StudentDTO> findAll() {
        return null;
    }
}
