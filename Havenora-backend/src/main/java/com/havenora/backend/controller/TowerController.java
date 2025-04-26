package com.havenora.backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.havenora.backend.dto.TowerDto;
import com.havenora.backend.service.TowerServiceInterface;

@CrossOrigin(origins = {"http://localhost:4200"}) 
@RestController
@RequestMapping("/havenoraTower")
public class TowerController {
	@Autowired
	private TowerServiceInterface towerSer;
	
	@PostMapping("/createTower")
	public ResponseEntity<TowerDto> insertTower(@RequestBody TowerDto dto) {
		
		TowerDto newDto = towerSer.createTower(dto);
		return new ResponseEntity<>(newDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/displayAllTower")
	public ResponseEntity<List<TowerDto>> displayAllTower()
	{
		List<TowerDto> displayDto = towerSer.displayAllTower();
		return new ResponseEntity<>(displayDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/displayTowerById/{id}")
	public ResponseEntity<TowerDto> displayTowerById(@PathVariable Long id)
	{
		TowerDto newDto =towerSer.displayTowerById(id);
		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTowerById/{id}")
	public ResponseEntity<String> deleteTower(@PathVariable Long id) 
	{
		String msg = towerSer.DeleteTowerById(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	@PutMapping("/updateTowerById/{id}")
	public ResponseEntity<TowerDto> updateTower(@RequestBody TowerDto dto, @PathVariable Long id)
	{
		TowerDto updatedTower = towerSer.UpdateTower(dto, id);
		return new ResponseEntity<>(updatedTower,HttpStatus.OK);
	}
	
	@GetMapping("/displayTowerByLocation/{location}")
	public ResponseEntity<List<TowerDto>> displayTowerByLocation(@PathVariable String location)
	{
		List<TowerDto> newDto =towerSer.displayByLocation(location);
		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}
	
}
