package com.pyae.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pyae.repositories.TownshipRepo;

@SpringBootTest
@ActiveProfiles("client")
public class TownshipTest {
	
	@Autowired
	private TownshipRepo repo;
	
	@ParameterizedTest
	@CsvFileSource(resources = "/TownshipFindbyId_found.txt", delimiterString ="\t")
	void findByIdTest(int id, String townshipName, int districtId, String districtName, int divisionId, String divisionName) {
		var result = repo.findById(id);
		assertTrue(result.isPresent());
		
		result.ifPresent(dto ->{
			assertEquals(id, dto.id());
			assertEquals(townshipName, dto.name());
			assertEquals(districtId, dto.districtId());
			assertEquals(districtName, dto.districtName());
			assertEquals(divisionId, dto.divisionId());
			assertEquals(divisionName, dto.divisionName());
		});
	}
	
	@ParameterizedTest
	@ValueSource( ints = {0,324})
	void findById_Not_FoundTest(int id) {
		var result = repo.findById(id);
		assertTrue(result.isEmpty());
		
	}
	
	@ParameterizedTest
	@CsvSource(value = {
			",,,323",
			"15,,,45",
			",15,,3",
			"15,85,,13",
			"15,85,k,3",
			"15,85,ks,0",
	})
	void search(Integer divisionId, Integer districtId, String name, int size) {
		var result = repo.search(divisionId, districtId, name);
		assertEquals(size, result.size());
	}
}
