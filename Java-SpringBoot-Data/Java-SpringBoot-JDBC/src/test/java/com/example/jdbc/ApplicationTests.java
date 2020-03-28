package com.example.jdbc;


import com.example.jdbc.domain.Student;
import com.example.jdbc.repository.impl.StudentRepositoryImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;




@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private StudentRepositoryImpl repository;

	@Test
	public void createUser() {

		System.out.println("------Records Creation--------" );
		repository.create("Zara", 11);
		repository.create("Nuha", 2);
		repository.create("Ayan", 15);

	}

	@Test
	public void findAllUsers() {

		System.out.println("------Listing Multiple Records--------" );
		final List<Student> students = repository.listStudents();

		for (Student record : students) {
			System.err.print("ID : " + record.getId() );
			System.err.print(", Name : " + record.getName() );
			System.err.println(", Age : " + record.getAge());
		}

		assertNotNull(students);
		assertTrue(students.size() > 0);
	}

	@Test
	public void updateUser() {
		System.out.println("----Updating Record with ID = 2 -----" );
		repository.update(2, 200);
	}

	@Test
	public void findUserById() {

		System.out.println("----Listing Record with ID = 2 -----" );
		final Student student = repository.getStudent(2);
		System.err.print("ID : " + student.getId() );
		System.err.print(", Name : " + student.getName() );
		System.err.println(", Age : " + student.getAge());

		assertNotNull(student);
	}


}
