package com.havenora.backend.service;

import java.util.List;

import com.havenora.backend.dto.TowerDto;


public interface TowerServiceInterface {
	public TowerDto createTower(TowerDto dto);
	
	public List<TowerDto> displayAllTower();
	
	public List<TowerDto> displayByLocation(String location);
	
	public TowerDto displayTowerById(Long id);
	
	public String DeleteTowerById(Long id);

	public TowerDto UpdateTower(TowerDto d, Long id);
}
