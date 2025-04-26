package com.havenora.backend.tests.serviceImplementation;


//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.dao.DataAccessException;
//
//import com.istay.backend.dto.BookingDto;
//import com.istay.backend.mapper.BookingMapper;
//import com.istay.backend.model.Booking;
//import com.istay.backend.repository.BookingRepository;
//import com.istay.backend.serviceimplementation.BookingServiceImplementation;
//import org.mockito.MockedStatic;
//import org.mockito.Mockito;
//
//
//class BookingServiceTest {
//
//    @InjectMocks
//    private BookingServiceImplementation bookingService;
//
//    @Mock
//    private BookingRepository bookingRepo;
//    
//    @Mock
//    private BookingMapper bookingMapper;
//
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    
//    @Test
//    void testCreateBooking_Success() {
//        Booking booking = new Booking();
//        BookingDto bookingDto = new BookingDto();
//
//        try (MockedStatic<BookingMapper> mockedStatic = Mockito.mockStatic(BookingMapper.class)) {
//            when(bookingRepo.save(any(Booking.class))).thenReturn(booking);
//            mockedStatic.when(() -> BookingMapper.mapModelToDto(booking)).thenReturn(bookingDto); // Static mocking
//
//            BookingDto result = bookingService.createBooking(bookingDto);
//
//            assertNotNull(result);
//            verify(bookingRepo, times(1)).save(any(Booking.class));
//        }
//    }
//
//
//
//    
//    @Test
//    void testCreateBooking_Failure() {
//        BookingDto bookingDto = new BookingDto();
//
//        when(bookingRepo.save(any(Booking.class))).thenThrow(new DataAccessException("Database Error") {});
//
//        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.createBooking(bookingDto));
//
//        assertEquals("Database Error", exception.getMessage());
//        verify(bookingRepo, times(1)).save(any(Booking.class));
//    }
//
//   
//    @Test
//    void testDisplayAllBookingWithData() {
//        List<Booking> bookings = Arrays.asList(new Booking(), new Booking());
//        when(bookingRepo.findAll()).thenReturn(bookings);
//
//        List<BookingDto> result = bookingService.displayAllBooking();
//
//        assertFalse(result.isEmpty());
//        verify(bookingRepo, times(1)).findAll();
//    }
//
//   
//    @Test
//    void testDisplayAllBooking_Exception() {
//        when(bookingRepo.findAll()).thenThrow(new RuntimeException("Database Failure"));
//
//        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.displayAllBooking());
//
//        assertEquals("Database Failure", exception.getMessage());
//        verify(bookingRepo, times(1)).findAll();
//    }
//
//    
//    @Test
//    void testDisplayBookingByIdExists() {
//        Long id = 1L;
//        Booking booking = new Booking();
//        when(bookingRepo.existsById(id)).thenReturn(true);
//        when(bookingRepo.findById(id)).thenReturn(Optional.of(booking));
//
//        BookingDto result = bookingService.displayBookingById(id);
//
//        assertNotNull(result);
//        verify(bookingRepo, times(1)).findById(id);
//    }
//
//
//    @Test
//    void testDisplayBookingById_Exception() {
//        Long id = 1L;
//        when(bookingRepo.findById(id)).thenThrow(new RuntimeException("DB Error"));
//
//        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.displayBookingById(id));
//
//        assertEquals("DB Error", exception.getMessage());
//        verify(bookingRepo, times(1)).findById(id);
//    }
//
//   
//    @Test
//    void testUpdateBookingExists() {
//        Long id = 1L;
//        Booking booking = new Booking();
//        when(bookingRepo.existsById(id)).thenReturn(true);
//        when(bookingRepo.findById(id)).thenReturn(Optional.of(booking));
//        when(bookingRepo.save(any(Booking.class))).thenReturn(booking);
//
//        BookingDto result = bookingService.UpdateBooking("Confirmed", id);
//
//        assertNotNull(result);
//        verify(bookingRepo, times(1)).save(any(Booking.class));
//    }
//
//    
//    @Test
//    void testUpdateBooking_Exception() {
//        Long id = 1L;
//        when(bookingRepo.existsById(id)).thenReturn(true);
//        when(bookingRepo.findById(id)).thenThrow(new RuntimeException("DB Update Error"));
//
//        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.UpdateBooking("Confirmed", id));
//
//        assertEquals("DB Update Error", exception.getMessage());
//        verify(bookingRepo, times(1)).findById(id);
//    }
//
//    
//    @Test
//    void testDeleteBookingByIdExists() {
//        Long id = 1L;
//        when(bookingRepo.existsById(id)).thenReturn(true);
//
//        String result = bookingService.DeleteBookingById(id);
//
//        assertEquals("Deleted booking Record Successfully", result);
//        verify(bookingRepo, times(1)).deleteById(id);
//    }
//
//    
//    @Test
//    void testDeleteBookingById_Exception() {
//        Long id = 1L;
//        when(bookingRepo.existsById(id)).thenReturn(true);
//        doThrow(new RuntimeException("DB Delete Error")).when(bookingRepo).deleteById(id);
//
//        Exception exception = assertThrows(RuntimeException.class, () -> bookingService.DeleteBookingById(id));
//
//        assertEquals("DB Delete Error", exception.getMessage());
//        verify(bookingRepo, times(1)).deleteById(id);
//    }
//}

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.havenora.backend.dto.BookingDto;
import com.havenora.backend.mapper.BookingMapper;
import com.havenora.backend.model.Booking;
import com.havenora.backend.repository.BookingRepository;
import com.havenora.backend.serviceimplementation.BookingServiceImplementation;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepo;

    @InjectMocks
    private BookingServiceImplementation bookingService;

    private Booking booking;
    private BookingDto bookingDto;

    @BeforeEach
    void setUp() {
        booking = new Booking();
        booking.setBookingId(1L);
        booking.setStatus("Confirmed");

        bookingDto = new BookingDto();
        bookingDto.setStatus("Confirmed");
    }

    // SUCCESS: Create Booking 
    @Test
    void testCreateBooking_Success() {
        when(bookingRepo.save(any(Booking.class))).thenReturn(booking);

        try (var mockedStatic = mockStatic(BookingMapper.class)) {
            mockedStatic.when(() -> BookingMapper.mapDtoToModel(any(BookingDto.class))).thenReturn(booking);
            mockedStatic.when(() -> BookingMapper.mapModelToDto(any(Booking.class))).thenReturn(bookingDto);

            BookingDto result = bookingService.createBooking(bookingDto);

            assertNotNull(result);
            assertEquals("Confirmed", result.getStatus());
            verify(bookingRepo, times(1)).save(any(Booking.class));
        }
    }

    //SUCCESS: Display All Bookings 
    @Test
    void testDisplayAllBooking_WithData() {
        List<Booking> bookings = new ArrayList<>();
        bookings.add(booking);
        when(bookingRepo.findAll()).thenReturn(bookings);

        try (var mockedStatic = mockStatic(BookingMapper.class)) {
            mockedStatic.when(() -> BookingMapper.mapModelToDto(any(Booking.class))).thenReturn(bookingDto);

            List<BookingDto> result = bookingService.displayAllBooking();

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(bookingRepo, times(1)).findAll();
        }
    }

    // FAILURE: Display All Bookings - No Data 
    @Test
    void testDisplayAllBooking_NoData() {
        when(bookingRepo.findAll()).thenReturn(new ArrayList<>());

        List<BookingDto> result = bookingService.displayAllBooking();

        assertTrue(result.isEmpty());
        verify(bookingRepo, times(1)).findAll();
    }

    //SUCCESS: Display Booking by ID 
    @Test
    void testDisplayBookingById_Found() {
        when(bookingRepo.existsById(1L)).thenReturn(true);
        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));

        try (var mockedStatic = mockStatic(BookingMapper.class)) {
            mockedStatic.when(() -> BookingMapper.mapModelToDto(any(Booking.class))).thenReturn(bookingDto);

            BookingDto result = bookingService.displayBookingById(1L);

            assertNotNull(result);
            assertEquals("Confirmed", result.getStatus());
            verify(bookingRepo, times(1)).findById(1L);
        }
    }

    //FAILURE: Display Booking by ID - Not Found 
    @Test
    void testDisplayBookingById_NotFound() {
        when(bookingRepo.existsById(1L)).thenReturn(false);

        BookingDto result = bookingService.displayBookingById(1L);

        assertNull(result);
        verify(bookingRepo, never()).findById(anyLong());
    }

    //SUCCESS: Delete Booking by ID 
    @Test
    void testDeleteBookingById_Success() {
        when(bookingRepo.existsById(1L)).thenReturn(true);

        String result = bookingService.DeleteBookingById(1L);

        assertEquals("Deleted booking Record Successfully", result);
        verify(bookingRepo, times(1)).deleteById(1L);
    }

    // FAILURE: Delete Booking by ID - Not Found
    @Test
    void testDeleteBookingById_NotFound() {
        when(bookingRepo.existsById(1L)).thenReturn(false);

        String result = bookingService.DeleteBookingById(1L);

        assertEquals("No such record exist", result);
        verify(bookingRepo, never()).deleteById(anyLong());
    }

    // SUCCESS: Update Booking 
    @Test
    void testUpdateBooking_Success() {
        when(bookingRepo.existsById(1L)).thenReturn(true);
        when(bookingRepo.findById(1L)).thenReturn(Optional.of(booking));
        when(bookingRepo.save(any(Booking.class))).thenReturn(booking);

        try (var mockedStatic = mockStatic(BookingMapper.class)) {
            mockedStatic.when(() -> BookingMapper.mapModelToDto(any(Booking.class))).thenReturn(bookingDto);

            BookingDto result = bookingService.UpdateBooking("Cancelled", 1L);

            assertNotNull(result);
            assertEquals("Confirmed", result.getStatus());
            verify(bookingRepo, times(1)).save(any(Booking.class));
        }
    }

    //FAILURE: Update Booking - Not Found 
    @Test
    void testUpdateBooking_NotFound() {
        when(bookingRepo.existsById(1L)).thenReturn(false);

        BookingDto result = bookingService.UpdateBooking("Cancelled", 1L);

        assertNull(result);
        verify(bookingRepo, never()).save(any(Booking.class));
    }
}


