package com.pyae.repositories;

import java.util.List;
import java.util.Optional;

import com.pyae.domain.DistrictDto;

public interface DistrictRepo {

	List<DistrictDto> search(Integer divisionId,String name);
	
	Optional<DistrictDto> findById(int id);
}
