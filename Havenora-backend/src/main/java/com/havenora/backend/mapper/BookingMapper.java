package com.havenora.backend.mapper;

import com.havenora.backend.dto.BookingDto;
import com.havenora.backend.model.Booking;

public class BookingMapper {
	public static BookingDto mapModelToDto(Booking b) {
		return new BookingDto(b.getBookingId(), b.getEmployeeId(), b.getRoomId(), b.getFromDate(), b.getToDate(), b.getStatus(), b.getFlatId(), b.getTowerId(),b.getLocation());
	}
	
	public static Booking mapDtoToModel(BookingDto b) {
		return new Booking(b.getBookingId(), b.getEmployeeId(), b.getRoomId(), b.getFromDate(), b.getToDate(), b.getStatus(), b.getFlatId(), b.getTowerId(),b.getLocation());
	}
}
