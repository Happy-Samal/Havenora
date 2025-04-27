package com.havenora.backend.service;

import java.util.List;

import com.havenora.backend.dto.FlatDto;

public interface FlatServiceInterface {
	
	public FlatDto createFlat(FlatDto dto);
	
	public List<FlatDto> findByTower(Long towerId);
	
	public FlatDto displayById(Long id);
	

	
}
