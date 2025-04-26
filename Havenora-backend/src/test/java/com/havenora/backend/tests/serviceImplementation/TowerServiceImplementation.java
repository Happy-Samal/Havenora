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

import com.havenora.backend.dto.TowerDto;
import com.havenora.backend.mapper.TowerMapper;
import com.havenora.backend.model.Tower;
import com.havenora.backend.repository.TowerRepository;
import com.havenora.backend.serviceimplementation.TowerServiceImplementation;

@ExtendWith(MockitoExtension.class)
class TowerServiceTests {

    @Mock
    private TowerRepository towerRepo;

    @InjectMocks
    private TowerServiceImplementation towerService;

    private Tower tower;
    private TowerDto towerDto;

    @BeforeEach
    void setUp() {
        tower = new Tower();
        tower.setTowerId(1L);
        tower.setName("Tower A");
        tower.setLocation("City Center");

        towerDto = new TowerDto();
        towerDto.setName("Tower A");
        towerDto.setLocation("City Center");
    }

    //SUCCESS: Create Tower 
    @Test
    void testCreateTower_Success() {
        when(towerRepo.save(any(Tower.class))).thenReturn(tower);

        try (var mockedStatic = mockStatic(TowerMapper.class)) {
            mockedStatic.when(() -> TowerMapper.mapDtoToModel(any(TowerDto.class))).thenReturn(tower);
            mockedStatic.when(() -> TowerMapper.mapModelToDto(any(Tower.class))).thenReturn(towerDto);

            TowerDto result = towerService.createTower(towerDto);

            assertNotNull(result);
            assertEquals("Tower A", result.getName());
            verify(towerRepo, times(1)).save(any(Tower.class));
        }
    }

    //SUCCESS: Display All Towers 
    @Test
    void testDisplayAllTower_WithData() {
        List<Tower> towers = new ArrayList<>();
        towers.add(tower);
        when(towerRepo.findAll()).thenReturn(towers);

        try (var mockedStatic = mockStatic(TowerMapper.class)) {
            mockedStatic.when(() -> TowerMapper.mapModelToDto(any(Tower.class))).thenReturn(towerDto);

            List<TowerDto> result = towerService.displayAllTower();

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(towerRepo, times(1)).findAll();
        }
    }

    //FAILURE: Display All Towers - No Data 
    @Test
    void testDisplayAllTower_NoData() {
        when(towerRepo.findAll()).thenReturn(new ArrayList<>());

        List<TowerDto> result = towerService.displayAllTower();

        assertTrue(result.isEmpty());
        verify(towerRepo, times(1)).findAll();
    }

    //SUCCESS: Display Tower by ID 
    @Test
    void testDisplayTowerById_Found() {
        when(towerRepo.existsById(1L)).thenReturn(true);
        when(towerRepo.findById(1L)).thenReturn(Optional.of(tower));

        try (var mockedStatic = mockStatic(TowerMapper.class)) {
            mockedStatic.when(() -> TowerMapper.mapModelToDto(any(Tower.class))).thenReturn(towerDto);

            TowerDto result = towerService.displayTowerById(1L);

            assertNotNull(result);
            assertEquals("Tower A", result.getName());
            verify(towerRepo, times(1)).findById(1L);
        }
    }

    //FAILURE: Display Tower by ID - Not Found 
    @Test
    void testDisplayTowerById_NotFound() {
        when(towerRepo.existsById(1L)).thenReturn(false);

        TowerDto result = towerService.displayTowerById(1L);

        assertNull(result);
        verify(towerRepo, never()).findById(anyLong());
    }

    // SUCCESS: Delete Tower by ID 
    @Test
    void testDeleteTowerById_Success() {
        when(towerRepo.existsById(1L)).thenReturn(true);

        String result = towerService.DeleteTowerById(1L);

        assertEquals("Deleted Tower Record Successfully", result);
        verify(towerRepo, times(1)).deleteById(1L);
    }

    // FAILURE: Delete Tower by ID - Not Found 
    @Test
    void testDeleteTowerById_NotFound() {
        when(towerRepo.existsById(1L)).thenReturn(false);

        String result = towerService.DeleteTowerById(1L);

        assertEquals("No such record exist", result);
        verify(towerRepo, never()).deleteById(anyLong());
    }

    // SUCCESS: Update Tower by ID 
    @Test
    void testUpdateTower_Success() {
        when(towerRepo.existsById(1L)).thenReturn(true);
        when(towerRepo.findById(1L)).thenReturn(Optional.of(tower));
        when(towerRepo.save(any(Tower.class))).thenReturn(tower);

        try (var mockedStatic = mockStatic(TowerMapper.class)) {
            mockedStatic.when(() -> TowerMapper.mapModelToDto(any(Tower.class))).thenReturn(towerDto);

            TowerDto result = towerService.UpdateTower(towerDto, 1L);

            assertNotNull(result);
            assertEquals("Tower A", result.getName());
            verify(towerRepo, times(1)).save(any(Tower.class));
        }
    }

    // FAILURE: Update Tower by ID - Not Found 
    @Test
    void testUpdateTower_NotFound() {
        when(towerRepo.existsById(1L)).thenReturn(false);

        TowerDto result = towerService.UpdateTower(towerDto, 1L);

        assertNull(result);
        verify(towerRepo, never()).save(any(Tower.class));
    }
}

