package com.pyae.domain;

public record TownshipDto(
		int id, 
		String name,
		int districtId,
		String districtName,
		int divisionId,
		String divisionName) {

}
