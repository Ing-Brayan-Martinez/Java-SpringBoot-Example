package com.example.jdbc.repository.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.jdbc.domain.Student;
import org.springframework.jdbc.core.RowMapper;


public class StudentMapper implements RowMapper<Student> {

    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {

        final Student student = new Student();

        student.setId(rs.getInt("id"));
        student.setName(rs.getString("name"));
        student.setAge(rs.getInt("age"));

        return student;
    }
}
