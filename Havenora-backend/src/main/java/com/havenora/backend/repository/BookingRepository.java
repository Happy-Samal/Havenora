package com.havenora.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.havenora.backend.model.Booking;

@Repository
public interface BookingRepository  extends JpaRepository<Booking, Long>{
	List<Booking> findByEmployeeId(Long employeeId);

}
