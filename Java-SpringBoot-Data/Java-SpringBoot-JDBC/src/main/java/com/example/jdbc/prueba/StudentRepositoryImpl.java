package com.example.jdbc.prueba;

import java.util.List;

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

        System.out.println("Created Record Name = " + name + " Age = " + age);

        return;
    }

    @Override
    public Student getStudent(Integer id) {

        final String SQL = "select * from Student where id = ?";
        final Student student = jdbcTemplateObject.queryForObject(SQL,
                new Object[]{id},
                new StudentMapper());

        return student;
    }

    @Override
    public List<Student> listStudents() {

        final String SQL = "select * from Student";
        final List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());

        return students;
    }

    @Override
    public void delete(Integer id) {

        final String SQL = "delete from Student where id = ?";
        jdbcTemplateObject.update(SQL, id);

        System.out.println("Deleted Record with ID = " + id );

        return;
    }

    @Override
    public void update(Integer id, Integer age) {

        final String SQL = "update Student set age = ? where id = ?";
        jdbcTemplateObject.update(SQL, age, id);

        System.out.println("Updated Record with ID = " + id );

        return;
    }
}
