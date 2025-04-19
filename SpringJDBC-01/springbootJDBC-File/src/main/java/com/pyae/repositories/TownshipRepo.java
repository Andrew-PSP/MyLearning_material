package com.pyae.repositories;

import java.util.List;
import java.util.Optional;

import com.pyae.domain.TownshipDto;

public interface TownshipRepo {

	List<TownshipDto> search(Integer divisionId,Integer districtId,String name);
	
	Optional<TownshipDto> findById(int id);
}
