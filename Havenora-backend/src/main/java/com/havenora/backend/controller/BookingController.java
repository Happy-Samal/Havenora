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

import com.havenora.backend.dto.BookingDto;
import com.havenora.backend.service.BookingServiceInterface;

@CrossOrigin(origins = {"http://localhost:4200/"}) 
@RestController
@RequestMapping("/havenoraBooking")
public class BookingController {
	@Autowired
	private BookingServiceInterface bookingSer;
	
	@PostMapping("/createBooking")
	public ResponseEntity<BookingDto> insertbooking(@RequestBody BookingDto dto) {
		BookingDto newDto = bookingSer.createBooking(dto);
		return new ResponseEntity<>(newDto, HttpStatus.CREATED);
	}
	
	@GetMapping("/displayAllBooking")
	public ResponseEntity<List<BookingDto>> displayAll()
	{
		List<BookingDto> displayDto = bookingSer.displayAllBooking();
		return new ResponseEntity<>(displayDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/displayBookingById/{id}")
	public ResponseEntity<BookingDto> displayById(@PathVariable  Long id)
	{
		BookingDto newDto =bookingSer.displayBookingById(id);
		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletebookingId/{id}")
	public ResponseEntity<String> delete(@PathVariable  Long id) 
	{
		String msg = bookingSer.DeleteBookingById(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	@PutMapping("/updatebookingById/{id}")
	public ResponseEntity<BookingDto> update(@RequestBody String status, @PathVariable  Long id)
	{
		BookingDto updatedBooking = bookingSer.UpdateBooking(status, id);
		return new ResponseEntity<>(updatedBooking,HttpStatus.OK);
	}
	
	@GetMapping("/displayBookingsByEmployeeId/{employeeId}")
	public ResponseEntity<List<BookingDto>> displayByEmployeeId(@PathVariable Long employeeId)
	{
		List<BookingDto> displayDto = bookingSer.displayBookingsByEmployeeId(employeeId);
		return new ResponseEntity<>(displayDto,HttpStatus.ACCEPTED);
	}
}
