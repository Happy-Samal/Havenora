package com.havenora.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.havenora.backend.dto.FlatDto;
import com.havenora.backend.service.FlatServiceInterface;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/havenoraFlat")
public class FlatController {

	@Autowired
	private FlatServiceInterface flatSer;
	
	@PostMapping("/createFlat")
	public ResponseEntity<FlatDto> addFlat(@RequestBody FlatDto f){
		FlatDto dto = flatSer.createFlat(f);
		return new ResponseEntity<>(dto,HttpStatus.CREATED);
	}
	
	@GetMapping("/findByTower/{towerId}")
	public ResponseEntity<List<FlatDto>> displayByTower(@PathVariable Long towerId){
		List<FlatDto> dtos = flatSer.findByTower(towerId);
		return new ResponseEntity<>(dtos,HttpStatus.OK);
	}
	

	
	@GetMapping("/displayFlatById/{id}")
	public ResponseEntity<FlatDto> displayFlatById(@PathVariable Long id){
		FlatDto dto = flatSer.displayById(id);
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	
}
