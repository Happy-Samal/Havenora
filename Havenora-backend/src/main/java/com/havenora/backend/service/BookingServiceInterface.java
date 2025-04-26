package com.havenora.backend.service;

import java.util.List;

import com.havenora.backend.dto.BookingDto;


public interface BookingServiceInterface {
public BookingDto createBooking(BookingDto dto);
	

	public List<BookingDto> displayAllBooking();
	
	public BookingDto displayBookingById(Long id);
	
	public String DeleteBookingById(Long id);

	public BookingDto UpdateBooking(String status, Long id);
	
	public List<BookingDto> displayBookingsByEmployeeId(Long employeeId);
}
