package com.havenora.backend.tests.serviceImplementation;


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

import com.havenora.backend.dto.RoomDto;
import com.havenora.backend.mapper.RoomMapper;
import com.havenora.backend.model.Room;
import com.havenora.backend.repository.RoomRepository;
import com.havenora.backend.serviceimplementation.RoomServiceImplementation;

@ExtendWith(MockitoExtension.class)
class RoomServiceTest {

    @Mock
    private RoomRepository roomRepo;

    @InjectMocks
    private RoomServiceImplementation roomService;

    private Room room;
    private RoomDto roomDto;

    @BeforeEach
    void setUp() {
        room = new Room();
        room.setRoomId(1L);;
        
        roomDto = new RoomDto();
    }

    //SUCCESS: Display All Rooms 
    @Test
    void testDisplayAllRoom_Success() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomRepo.findAll()).thenReturn(rooms);

        try (var mockedStatic = mockStatic(RoomMapper.class)) {
            mockedStatic.when(() -> RoomMapper.mapModelToDto(any(Room.class))).thenReturn(roomDto);
            
            List<RoomDto> result = roomService.displayAllRoom();
            
            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(roomRepo, times(1)).findAll();
        }
    }

    //FAILURE: Display All Rooms - No Data 
    @Test
    void testDisplayAllRoom_NoData() {
        when(roomRepo.findAll()).thenReturn(new ArrayList<>());
        
        List<RoomDto> result = roomService.displayAllRoom();
        
        assertTrue(result.isEmpty());
        verify(roomRepo, times(1)).findAll();
    }

    // SUCCESS: Display Room by ID 
    @Test
    void testDisplayRoomById_Found() {
        when(roomRepo.existsById(1L)).thenReturn(true);
        when(roomRepo.findById(1L)).thenReturn(Optional.of(room));

        try (var mockedStatic = mockStatic(RoomMapper.class)) {
            mockedStatic.when(() -> RoomMapper.mapModelToDto(any(Room.class))).thenReturn(roomDto);
            
            RoomDto result = roomService.displayRoomById(1L);
            
            assertNotNull(result);
            verify(roomRepo, times(1)).findById(1L);
        }
    }

    // FAILURE: Display Room by ID - Not Found 
    @Test
    void testDisplayRoomById_NotFound() {
        when(roomRepo.existsById(1L)).thenReturn(false);
        
        RoomDto result = roomService.displayRoomById(1L);
        
        assertNull(result);
        verify(roomRepo, never()).findById(anyLong());
    }

    //SUCCESS: Display Rooms by Flat ID 
    @Test
    void testDisplayFlatById_WithData() {
        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        when(roomRepo.findByFlatId(1L)).thenReturn(rooms);

        try (var mockedStatic = mockStatic(RoomMapper.class)) {
            mockedStatic.when(() -> RoomMapper.mapModelToDto(any(Room.class))).thenReturn(roomDto);
            
            List<RoomDto> result = roomService.displayflatById(1L);
            
            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(roomRepo, times(1)).findByFlatId(1L);
        }
    }

    //FAILURE: Display Rooms by Flat ID - No Data 
    @Test
    void testDisplayFlatById_NoData() {
        when(roomRepo.findByFlatId(anyLong())).thenReturn(new ArrayList<>());
        
        List<RoomDto> result = roomService.displayflatById(1L);
        
        assertTrue(result.isEmpty());
        verify(roomRepo, times(1)).findByFlatId(1L);
    }
}

