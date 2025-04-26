package com.havenora.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.havenora.backend.dto.RoomDto;
import com.havenora.backend.service.RoomServiceInterface;


@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/havenoraRoom")
public class RoomController 
	{
	
	@Autowired
	private RoomServiceInterface roomServ;
	
	@GetMapping("/displayAll")
	public ResponseEntity<List<RoomDto>> displayAll()
	{
		List<RoomDto> displayDto = roomServ.displayAllRoom();
		return new ResponseEntity<>(displayDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/displayRoomById/{id}")
	public ResponseEntity<RoomDto> displayById(@PathVariable Long id)
	{
		RoomDto newDto =roomServ.displayRoomById(id);
		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}
	@GetMapping("/displayFlatById/{id}")
	public ResponseEntity<List<RoomDto>> displayId(@PathVariable("id") Long flatId)
	{
		List<RoomDto> newDto =roomServ.displayflatById(flatId);
		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}
	
	
	}
