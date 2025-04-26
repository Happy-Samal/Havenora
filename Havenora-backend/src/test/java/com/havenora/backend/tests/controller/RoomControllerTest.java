package com.havenora.backend.tests.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.havenora.backend.controller.RoomController;
import com.havenora.backend.dto.RoomDto;
import com.havenora.backend.service.RoomServiceInterface;

@WebMvcTest(RoomController.class)
public class RoomControllerTest {

    private MockMvc mockMvc;

    @MockBean
    private RoomServiceInterface roomService;

    @InjectMocks
    private RoomController roomController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(roomController).build();
    }

    @Test
    void testDisplayAll() throws Exception {
        List<RoomDto> roomList = Arrays.asList(
            new RoomDto(1L, 101L, 1, 2, 1, "Available"),
            new RoomDto(2L, 102L, 2, 3, 2, "Occupied")
        );

        when(roomService.displayAllRoom()).thenReturn(roomList);

        mockMvc.perform(get("/iStayRoom/displayAll")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$[0].roomId").value(1L))
                .andExpect(jsonPath("$[0].flatId").value(101L))
                .andExpect(jsonPath("$[0].status").value("Available"));
    }

    @Test
    void testDisplayRoomById() throws Exception {
        RoomDto room = new RoomDto(1L, 101L, 1, 2, 1, "Available");

        when(roomService.displayRoomById(1L)).thenReturn(room);

        mockMvc.perform(get("/iStayRoom/displayRoomById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roomId").value(1L))
                .andExpect(jsonPath("$.status").value("Available"));
    }

    @Test
    void testDisplayFlatById() throws Exception {
        List<RoomDto> roomList = Arrays.asList(
            new RoomDto(1L, 101L, 1, 2, 1, "Available"),
            new RoomDto(2L, 101L, 2, 3, 2, "Occupied")
        );

        when(roomService.displayflatById(101L)).thenReturn(roomList);

        mockMvc.perform(get("/iStayRoom/displayFlatById/101")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].flatId").value(101L))
                .andExpect(jsonPath("$[1].flatId").value(101L));
    }
}
