package com.pyae.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pyae.repositories.DistrictRepo;

@SpringBootTest
@ActiveProfiles("client")
public class DistrictTest {
	
	@Autowired
	DistrictRepo repo;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/findbyId_found.txt", delimiter = '\t')
	void findbyId_found(int id, String name, int division_id, String division_name, long townships) {
		var result = repo.findById(id);
		assertTrue(result.isPresent());
		
		result.ifPresent(dto -> {
			assertEquals(id, dto.id());
			assertEquals(name, dto.name());
			assertEquals(division_id, dto.divisionId());
			assertEquals(division_name, dto.divisionName());
			assertEquals(townships, dto.townships());
		});
		
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			",,85",
			"13,,14",
			",ma,7",
			"13,m,4",
			"13,me,0"
	})
	void search(Integer id, String name, long size) {
		var result = repo.search(id, name);
		assertEquals(size,result.size());
	}
}
