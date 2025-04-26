package com.havenora.backend.tests.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
//import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.havenora.backend.controller.BookingController;
import com.havenora.backend.dto.BookingDto;
import com.havenora.backend.service.BookingServiceInterface;

class BookingControllerTest {
    @Mock
    private BookingServiceInterface bookingService;
    
    @InjectMocks
    private BookingController bookingController;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testInsertBooking() {
        BookingDto dto = new BookingDto();
        when(bookingService.createBooking(dto)).thenReturn(dto);
        
        ResponseEntity<BookingDto> response = bookingController.insertbooking(dto);
        
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
    
    @Test
    void testDisplayAllBookings() {
        List<BookingDto> bookings = Arrays.asList(new BookingDto(), new BookingDto());
        when(bookingService.displayAllBooking()).thenReturn(bookings);
        
        ResponseEntity<List<BookingDto>> response = bookingController.displayAll();
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(bookings, response.getBody());
    }
    
    @Test
    void testDisplayBookingById_Found() {
        BookingDto dto = new BookingDto();
        when(bookingService.displayBookingById(1L)).thenReturn(dto);
        
        ResponseEntity<BookingDto> response = bookingController.displayById(1L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
    
    @Test
    void testDisplayBookingById_NotFound() {
        when(bookingService.displayBookingById(2L)).thenReturn(null);
        
        ResponseEntity<BookingDto> response = bookingController.displayById(2L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    void testDeleteBookingById_Found() {
        when(bookingService.DeleteBookingById(1L)).thenReturn("Deleted booking Record Successfully");
        
        ResponseEntity<String> response = bookingController.delete(1L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted booking Record Successfully", response.getBody());
    }
    
    @Test
    void testDeleteBookingById_NotFound() {
        when(bookingService.DeleteBookingById(2L)).thenReturn("No such record exist");
        
        ResponseEntity<String> response = bookingController.delete(2L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("No such record exist", response.getBody());
    }
    
    @Test
    void testUpdateBooking_Found() {
        BookingDto dto = new BookingDto();
        when(bookingService.UpdateBooking("Confirmed", 1L)).thenReturn(dto);
        
        ResponseEntity<BookingDto> response = bookingController.update("Confirmed", 1L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
    
    @Test
    void testUpdateBooking_NotFound() {
        when(bookingService.UpdateBooking("Confirmed", 2L)).thenReturn(null);
        
        ResponseEntity<BookingDto> response = bookingController.update("Confirmed", 2L);
        
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }
    
    @Test
    void testDisplayBookingsByEmployeeId_Found() {
        List<BookingDto> bookings = Arrays.asList(new BookingDto(), new BookingDto());
        when(bookingService.displayBookingsByEmployeeId(100L)).thenReturn(bookings);
        
        ResponseEntity<List<BookingDto>> response = bookingController.displayByEmployeeId(100L);
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(bookings, response.getBody());
    }
    
    @Test
    void testDisplayBookingsByEmployeeId_NotFound() {
        when(bookingService.displayBookingsByEmployeeId(200L)).thenReturn(Arrays.asList());
        
        ResponseEntity<List<BookingDto>> response = bookingController.displayByEmployeeId(200L);
        
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }
}

