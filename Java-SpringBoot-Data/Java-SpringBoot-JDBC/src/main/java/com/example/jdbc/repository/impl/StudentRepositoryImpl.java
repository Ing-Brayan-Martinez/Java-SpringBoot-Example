package com.example.jdbc.repository.impl;

import java.util.List;

import com.example.jdbc.domain.Student;
import com.example.jdbc.repository.mapper.StudentMapper;
import com.example.jdbc.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepositoryImpl implements StudentRepository {


    @Autowired
    private JdbcTemplate jdbcTemplateObject;


    @Override
    public void create(String name, Integer age) {
        final String SQL = "insert into Student (name, age) values (?, ?)";
        this.jdbcTemplateObject.update(SQL, name, age);
    }

    @Override
    public Student getStudent(Integer id) {
        final String SQL = "select * from Student where id = ?";
        return jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new StudentMapper());
    }

    @Override
    public List<Student> listStudents() {
        final String SQL = "select * from Student";
        return jdbcTemplateObject.query(SQL, new StudentMapper());
    }

    @Override
    public void delete(Integer id) {
        final String SQL = "delete from Student where id = ?";
        jdbcTemplateObject.update(SQL, id);
    }

    @Override
    public void update(Integer id, Integer age) {
        final String SQL = "update Student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);
    }
}
