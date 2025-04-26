package com.havenora.backend.tests.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.havenora.backend.controller.EmployeeController;
import com.havenora.backend.dto.EmployeeDto;
import com.havenora.backend.service.EmployeeServiceInterface;

public class EmployeeControllerTest {

    @Mock
    private EmployeeServiceInterface empServ;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInsertEmployee_Success() {
        EmployeeDto dto = new EmployeeDto();
        when(empServ.createEmployee(dto)).thenReturn(dto);
        ResponseEntity<EmployeeDto> response = employeeController.insertEmployee(dto);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testLoginEmployee_Success() {
        EmployeeDto dto = new EmployeeDto();
        when(empServ.findEmployee(dto)).thenReturn(dto);
        ResponseEntity<EmployeeDto> response = employeeController.loginEmployee(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDisplayAllEmployee_Success() {
        List<EmployeeDto> employees = Arrays.asList(new EmployeeDto(), new EmployeeDto());
        when(empServ.displayAllEmployee()).thenReturn(employees);
        ResponseEntity<List<EmployeeDto>> response = employeeController.displayAllEmployee();
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(employees, response.getBody());
    }

    @Test
    void testDisplayEmployeeById_Found() {
        EmployeeDto dto = new EmployeeDto();
        when(empServ.displayEmployeeById(1L)).thenReturn(dto);
        ResponseEntity<EmployeeDto> response = employeeController.displayAllEmployeeId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }

    @Test
    void testDisplayEmployeeById_NotFound() {
        when(empServ.displayEmployeeById(1L)).thenReturn(null);
        ResponseEntity<EmployeeDto> response = employeeController.displayAllEmployeeId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testDeleteEmployee_Success() {
        when(empServ.deleteEmployeeById(1L)).thenReturn("Deleted Successfully");
        ResponseEntity<String> response = employeeController.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Deleted Successfully", response.getBody());
    }

    @Test
    void testDeleteEmployee_NotFound() {
        when(empServ.deleteEmployeeById(1L)).thenReturn("No such record exist");
        ResponseEntity<String> response = employeeController.deleteEmployee(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("No such record exist", response.getBody());
    }

    @Test
    void testUpdateEmployee_Success() {
        EmployeeDto dto = new EmployeeDto();
        when(empServ.updateEmployee(dto, 1L)).thenReturn(dto);
        ResponseEntity<EmployeeDto> response = employeeController.updateEmployee(dto, 1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dto, response.getBody());
    }
}

