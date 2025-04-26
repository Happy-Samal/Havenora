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

import com.havenora.backend.dto.EmployeeDto;
import com.havenora.backend.mapper.EmployeeMapper;
import com.havenora.backend.model.Employee;
import com.havenora.backend.repository.EmployeeRepository;
import com.havenora.backend.serviceimplementation.EmployeeServiceImplementation;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository empRepo;

    @InjectMocks
    private EmployeeServiceImplementation employeeService;

    private Employee employee;
    private EmployeeDto employeeDto;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmployeeId(1L);;
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setPassword("password123");

        employeeDto = new EmployeeDto();
        employeeDto.setName("John Doe");
        employeeDto.setEmail("john@example.com");
        employeeDto.setPassword("password123");
    }

    //SUCCESS: Create Employee 
    @Test
    void testCreateEmployee_Success() {
        when(empRepo.save(any(Employee.class))).thenReturn(employee);

        try (var mockedStatic = mockStatic(EmployeeMapper.class)) {
            mockedStatic.when(() -> EmployeeMapper.mapDtoToModel(any(EmployeeDto.class))).thenReturn(employee);
            mockedStatic.when(() -> EmployeeMapper.mapModelToDto(any(Employee.class))).thenReturn(employeeDto);

            EmployeeDto result = employeeService.createEmployee(employeeDto);

            assertNotNull(result);
            assertEquals("John Doe", result.getName());
            verify(empRepo, times(1)).save(any(Employee.class));
        }
    }

    // SUCCESS: Find Employee 
    @Test
    void testFindEmployee_Success() {
        when(empRepo.findByEmailAndPassword(employee.getEmail(), employee.getPassword())).thenReturn(employee);

        try (var mockedStatic = mockStatic(EmployeeMapper.class)) {
            mockedStatic.when(() -> EmployeeMapper.mapDtoToModel(any(EmployeeDto.class))).thenReturn(employee);
            mockedStatic.when(() -> EmployeeMapper.mapModelToDto(any(Employee.class))).thenReturn(employeeDto);

            EmployeeDto result = employeeService.findEmployee(employeeDto);

            assertNotNull(result);
            assertEquals("John Doe", result.getName());
            verify(empRepo, times(1)).findByEmailAndPassword(anyString(), anyString());
        }
    }

    // FAILURE: Find Employee - Not Found 
    @Test
    void testFindEmployee_NotFound() {
        when(empRepo.findByEmailAndPassword(anyString(), anyString())).thenReturn(null);

        try (var mockedStatic = mockStatic(EmployeeMapper.class)) {
            mockedStatic.when(() -> EmployeeMapper.mapDtoToModel(any(EmployeeDto.class))).thenReturn(employee);
            
            EmployeeDto result = employeeService.findEmployee(employeeDto);

            assertNull(result);
            verify(empRepo, times(1)).findByEmailAndPassword(anyString(), anyString());
        }
    }

    // SUCCESS: Display All Employees 
    @Test
    void testDisplayAllEmployees_WithData() {
        List<Employee> employees = new ArrayList<>();
        employees.add(employee);

        when(empRepo.findAll()).thenReturn(employees);

        try (var mockedStatic = mockStatic(EmployeeMapper.class)) {
            mockedStatic.when(() -> EmployeeMapper.mapModelToDto(any(Employee.class))).thenReturn(employeeDto);

            List<EmployeeDto> result = employeeService.displayAllEmployee();

            assertFalse(result.isEmpty());
            assertEquals(1, result.size());
            verify(empRepo, times(1)).findAll();
        }
    }

    // FAILURE: Display All Employees - No Data 
    @Test
    void testDisplayAllEmployees_NoData() {
        when(empRepo.findAll()).thenReturn(new ArrayList<>());

        List<EmployeeDto> result = employeeService.displayAllEmployee();

        assertTrue(result.isEmpty());
        verify(empRepo, times(1)).findAll();
    }

    // SUCCESS: Display Employee by ID 
    @Test
    void testDisplayEmployeeById_Found() {
        when(empRepo.existsById(1L)).thenReturn(true);
        when(empRepo.findById(1L)).thenReturn(Optional.of(employee));

        try (var mockedStatic = mockStatic(EmployeeMapper.class)) {
            mockedStatic.when(() -> EmployeeMapper.mapModelToDto(any(Employee.class))).thenReturn(employeeDto);

            EmployeeDto result = employeeService.displayEmployeeById(1L);

            assertNotNull(result);
            assertEquals("John Doe", result.getName());
            verify(empRepo, times(1)).findById(1L);
        }
    }

    //FAILURE: Display Employee by ID - Not Found 
    @Test
    void testDisplayEmployeeById_NotFound() {
        when(empRepo.existsById(1L)).thenReturn(false);

        EmployeeDto result = employeeService.displayEmployeeById(1L);

        assertNull(result);
        verify(empRepo, never()).findById(anyLong());
    }

    //SUCCESS: Delete Employee by ID 
    @Test
    void testDeleteEmployeeById_Found() {
        when(empRepo.existsById(1L)).thenReturn(true);
        doNothing().when(empRepo).deleteById(1L);

        String result = employeeService.deleteEmployeeById(1L);

        assertEquals("Deleted Employee Record Successfully", result);
        verify(empRepo, times(1)).deleteById(1L);
    }

    // FAILURE: Delete Employee by ID - Not Found 
    @Test
    void testDeleteEmployeeById_NotFound() {
        when(empRepo.existsById(1L)).thenReturn(false);

        String result = employeeService.deleteEmployeeById(1L);

        assertEquals("No such record exist", result);
        verify(empRepo, never()).deleteById(anyLong());
    }

    // SUCCESS: Update Employee 
    @Test
    void testUpdateEmployee_Found() {
        when(empRepo.existsById(1L)).thenReturn(true);
        when(empRepo.findById(1L)).thenReturn(Optional.of(employee));
        when(empRepo.save(any(Employee.class))).thenReturn(employee);

        try (var mockedStatic = mockStatic(EmployeeMapper.class)) {
            mockedStatic.when(() -> EmployeeMapper.mapModelToDto(any(Employee.class))).thenReturn(employeeDto);

            EmployeeDto result = employeeService.updateEmployee(employeeDto, 1L);

            assertNotNull(result);
            assertEquals("John Doe", result.getName());
            verify(empRepo, times(1)).save(any(Employee.class));
        }
    }

    //Update Employee - Not Found
    @Test
    void testUpdateEmployee_NotFound() {
        when(empRepo.existsById(1L)).thenReturn(false);

        EmployeeDto result = employeeService.updateEmployee(employeeDto, 1L);

        assertNull(result);
        verify(empRepo, never()).save(any(Employee.class));
    }
}

