package com.havenora.backend.serviceimplementation;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havenora.backend.dto.BookingDto;
import com.havenora.backend.mapper.BookingMapper;
import com.havenora.backend.model.Booking;
import com.havenora.backend.repository.BookingRepository;
import com.havenora.backend.service.BookingServiceInterface;

@Service
public class BookingServiceImplementation implements BookingServiceInterface {
	@Autowired
	private BookingRepository bookingRepo;
	
	
	@Override
	public BookingDto createBooking(BookingDto dto) {
		// TODO Auto-generated method stub
		Booking b = BookingMapper.mapDtoToModel(dto);
		Booking savedBooking = bookingRepo.save(b);
		return BookingMapper.mapModelToDto(savedBooking);
	}

	@Override
	public List<BookingDto> displayAllBooking() {
		// TODO Auto-generated method stub
		List<Booking> booking = bookingRepo.findAll();
		List<BookingDto> bookingList = new ArrayList<BookingDto>();
		for(Booking b: booking)
		{
			bookingList.add(BookingMapper.mapModelToDto(b));
		}
		return bookingList;
	}

	@Override
	public BookingDto displayBookingById(Long id) {
		// TODO Auto-generated method stub
		if(bookingRepo.existsById(id)) 
		{
			Optional<Booking> booking = bookingRepo.findById(id);
			return BookingMapper.mapModelToDto(booking.get());
		}
		else
		{		
		return null;
		}
	}

	@Override
	public String DeleteBookingById(Long id) {
		// TODO Auto-generated method stub
		if(bookingRepo.existsById(id)) 
		{
			bookingRepo.deleteById(id);
			return "Deleted booking Record Successfully";
		}else
		{
			return "No such record exist";
		}
	}

	@Override
	public BookingDto UpdateBooking(String status, Long id) {
		// TODO Auto-generated method stub
		if(bookingRepo.existsById(id)) 
		{
			Optional<Booking> booking = bookingRepo.findById(id);
			Booking oldbooking = booking.get();
			oldbooking.setStatus(status);

			return BookingMapper.mapModelToDto(bookingRepo.save(oldbooking));
			}
		return null;
	}

	@Override
	public List<BookingDto> displayBookingsByEmployeeId(Long employeeId) {
		// TODO Auto-generated method stub
		List<Booking> booking = bookingRepo.findByEmployeeId(employeeId);
		List<BookingDto> bookingList = new ArrayList<BookingDto>();
		for(Booking b: booking)
		{
			bookingList.add(BookingMapper.mapModelToDto(b));
		}
		return bookingList;
	}

}

