package com.havenora.backend.service;


import java.util.List;

import com.havenora.backend.dto.EmployeeDto;


public interface EmployeeServiceInterface {
		
	public EmployeeDto createEmployee(EmployeeDto dto);
	
	public EmployeeDto findEmployee(EmployeeDto dto);
	
	public List<EmployeeDto> displayAllEmployee();
	
	public EmployeeDto displayEmployeeById(Long id);
	
	public String deleteEmployeeById(Long id);

	public EmployeeDto updateEmployee(EmployeeDto d, Long id);
	
	
}
