package com.havenora.backend.mapper;

import com.havenora.backend.dto.EmployeeDto;
import com.havenora.backend.model.Employee;

public class EmployeeMapper {
	
	public static EmployeeDto mapModelToDto(Employee e) {
		return new EmployeeDto(e.getEmployeeId(), e.getName(), e.getEmail(),e.getPassword());

		
	}
	
	public static Employee mapDtoToModel(EmployeeDto d) {
		return new Employee(d.getEmployeeId(), d.getName(), d.getEmail(),d.getPassword());

		
	}
}
