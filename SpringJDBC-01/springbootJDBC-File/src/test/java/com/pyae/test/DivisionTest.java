package com.pyae.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pyae.repositories.DivisionRepo;

@SpringBootTest
@ActiveProfiles("name")
public class DivisionTest {

	@Autowired
	private DivisionRepo divisionRepo;
	
	@ParameterizedTest
	@CsvSource({
		"M,3",
		"K,3",
		"Yangon Region,1",
		"Yangon Regions,0"
	})
	void search(String name, int size) {
		var result = divisionRepo.search(name);
		assertEquals(size, result.size());
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"1,Ayeyarwady Region,8",
		"2,Bago Region,6",
		"3,Chin State,4",
		"4,Kachin State,4",
		"5,Kayah State,4",
		"6,Kayin State,4",
		"7,Magway Region,5",
		"8,Mandalay Region,7",
		"9,Mon State,2",
		"10,Naypyidaw Union Territory,2",
		"11,Rakhine State,5",
		"12,Sagaing Region,13",
		"13,Shan State,14",
		"14,Tanintharyi Region,3",
		"15,Yangon Region,4"
	})
	void findById(int id,String name,int districts) {
		var result = divisionRepo.findById(id);
		
		assertTrue(result.isPresent());
		
		var division = result.get();
		assertEquals(name, division.name());
		assertEquals(districts, division.districts());
	}
}
