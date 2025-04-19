package com.pyae.repositories;

import java.util.List;
import java.util.Optional;

import com.pyae.domain.DivisionDto;

public interface DivisionRepo {
	
	List<DivisionDto> search(String name);
	
	Optional<DivisionDto> findById(int id);
}
