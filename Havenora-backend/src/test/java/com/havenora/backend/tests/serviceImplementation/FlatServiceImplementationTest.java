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

import com.havenora.backend.dto.FlatDto;
import com.havenora.backend.mapper.FlatMapper;
import com.havenora.backend.model.Flat;
import com.havenora.backend.repository.FlatRepository;
import com.havenora.backend.serviceimplementation.FlatServiceImplementation;

@ExtendWith(MockitoExtension.class)
class FlatServiceTest {

    @Mock
    private FlatRepository flatRepo;

    @InjectMocks
    private FlatServiceImplementation flatService;

    private Flat flat;
    private FlatDto flatDto;

    @BeforeEach
    void setUp() {
        flat = new Flat();
        flat.setFlatId(1L);
        flat.setFlatNo(101);
        flat.setType("2BHK");
        
        flatDto = new FlatDto();
        flatDto.setFlatNo(101);
        flatDto.setType("2BHK");
    }

    //SUCCESS: Create Flat 
    @Test
    void testCreateFlat_Success() {
        when(flatRepo.save(any(Flat.class))).thenReturn(flat);

        try (var mockedStatic = mockStatic(FlatMapper.class)) {
            mockedStatic.when(() -> FlatMapper.mapDtoToModel(any(FlatDto.class))).thenReturn(flat);
            mockedStatic.when(() -> FlatMapper.mapModelToDto(any(Flat.class))).thenReturn(flatDto);

            FlatDto result = flatService.createFlat(flatDto);

            assertNotNull(result);
            assertEquals(101, result.getFlatNo());
            assertEquals("2BHK", result.getType());
            verify(flatRepo, times(1)).save(any(Flat.class));
        }
    }

    //SUCCESS: Find Flats by Tower ID 
    @Test
    void testFindByTower_WithData() {
        List<Flat> flats = new ArrayList<>();
        flats.add(flat);
        when(flatRepo.findByTowerId(1L)).thenReturn(flats);

        try (var mockedStatic = mockStatic(FlatMapper.class)) {
            mockedStatic.when(() -> FlatMapper.mapModelToDto(any(Flat.class))).thenReturn(flatDto);

            List<FlatDto> result = flatService.findByTower(1L);

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(flatRepo, times(1)).findByTowerId(1L);
        }
    }

    //FAILURE: Find Flats by Tower ID - No Data 
    @Test
    void testFindByTower_NoData() {
        when(flatRepo.findByTowerId(anyLong())).thenReturn(new ArrayList<>());

        List<FlatDto> result = flatService.findByTower(1L);

        assertTrue(result.isEmpty());
        verify(flatRepo, times(1)).findByTowerId(1L);
    }

    //SUCCESS: Display Flat by ID 
    @Test
    void testDisplayById_Found() {
        when(flatRepo.existsById(1L)).thenReturn(true);
        when(flatRepo.findById(1L)).thenReturn(Optional.of(flat));

        try (var mockedStatic = mockStatic(FlatMapper.class)) {
            mockedStatic.when(() -> FlatMapper.mapModelToDto(any(Flat.class))).thenReturn(flatDto);

            FlatDto result = flatService.displayById(1L);

            assertNotNull(result);
            assertEquals(101, result.getFlatNo());
            assertEquals("2BHK", result.getType());
            verify(flatRepo, times(1)).findById(1L);
        }
    }

    //FAILURE: Display Flat by ID - Not Found 
    @Test
    void testDisplayById_NotFound() {
        when(flatRepo.existsById(1L)).thenReturn(false);

        FlatDto result = flatService.displayById(1L);

        assertNull(result);
        verify(flatRepo, never()).findById(anyLong());
    }
}

