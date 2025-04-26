package com.havenora.backend.tests.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.havenora.backend.controller.FlatController;
import com.havenora.backend.dto.FlatDto;
import com.havenora.backend.service.FlatServiceInterface;

@WebMvcTest(FlatController.class)
@ExtendWith(MockitoExtension.class)
public class FlatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FlatServiceInterface flatSer;

    @Autowired
    private ObjectMapper objectMapper;

    // ✅ Test for POST /createFlat
    @Test
    public void testCreateFlat_Success() throws Exception {
        FlatDto flat = new FlatDto(1L, 201, "Male", 1L);
        when(flatSer.createFlat(flat)).thenReturn(flat);

        mockMvc.perform(post("/iStayFlat/createFlat")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(flat)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.flatId").value(1))
                .andExpect(jsonPath("$.flatNo").value(201))
                .andExpect(jsonPath("$.type").value("Male"))
                .andExpect(jsonPath("$.towerId").value(1));
    }

    // ✅ Test for GET /findByTower/{towerId}
    @Test
    public void testFindByTower_Success() throws Exception {
        FlatDto flat1 = new FlatDto(1L, 201, "Male", 1L);
        FlatDto flat2 = new FlatDto(2L, 202, "Female", 1L);
        List<FlatDto> flats = Arrays.asList(flat1, flat2);

        when(flatSer.findByTower(1L)).thenReturn(flats);

        mockMvc.perform(get("/iStayFlat/findByTower/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].flatId").value(1))
                .andExpect(jsonPath("$[0].flatNo").value(201))
                .andExpect(jsonPath("$[0].type").value("Male"))
                .andExpect(jsonPath("$[0].towerId").value(1))
                .andExpect(jsonPath("$[1].flatId").value(2))
                .andExpect(jsonPath("$[1].flatNo").value(202))
                .andExpect(jsonPath("$[1].type").value("Female"))
                .andExpect(jsonPath("$[1].towerId").value(1));
    }

    // ✅ Test for GET /displayFlatById/{id}
    @Test
    public void testDisplayFlatById_Success() throws Exception {
        FlatDto flat = new FlatDto(1L, 201, "Male", 1L);
        when(flatSer.displayById(1L)).thenReturn(flat);

        mockMvc.perform(get("/iStayFlat/displayFlatById/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.flatId").value(1))
                .andExpect(jsonPath("$.flatNo").value(201))
                .andExpect(jsonPath("$.type").value("Male"))
                .andExpect(jsonPath("$.towerId").value(1));
    }
}
