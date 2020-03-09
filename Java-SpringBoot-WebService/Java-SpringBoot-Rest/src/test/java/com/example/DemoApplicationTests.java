package com.example;

import com.example.repository.impl.PersonRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PersonRepositoryImpl repository;

    @Test
    public void test() {

        try {
            System.out.println(dataSource.getConnection());
            System.out.println(repository);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
