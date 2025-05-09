package com.pyae.em;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pyae.em.entity.Address;
import com.pyae.em.entity.Student;
import com.pyae.em.repo.StudentRepo;

@SpringBootTest
public class demo {

	@Autowired
	private StudentRepo repo;
	
	@Test
	void demoTest() {
		var student = new Student();
		var address = new Address();
		student.setName("pyae");
		student.setEmail("p@gmail.com");
		student.setPhone("0911112222");
		
		address.setBuilding("No.64");
		address.setStreet("Yadanar Myaing");
		address.setQuarter("1");
		address.setTownship("Kamayut");
		
		student.setAddress(address);
		
		repo.create(student);
		
		student.setName("kyaw");
		
		repo.modify(student);
	}
}
