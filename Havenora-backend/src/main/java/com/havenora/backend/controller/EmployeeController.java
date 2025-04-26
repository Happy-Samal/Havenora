package com.havenora.backend.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.havenora.backend.dto.EmployeeDto;
import com.havenora.backend.service.EmployeeServiceInterface;



@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/havenoraEmployee")
public class EmployeeController {
 
	@Autowired
	private EmployeeServiceInterface empServ;
	
	@PostMapping("/createEmployee")
	public ResponseEntity<EmployeeDto> insertEmployee(@RequestBody EmployeeDto dto) 
	{
		EmployeeDto newDto =empServ.createEmployee(dto);
		
		return new ResponseEntity<>(newDto,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<EmployeeDto> loginEmployee(@RequestBody EmployeeDto dto) 
	{
		EmployeeDto newDto =empServ.findEmployee(dto);

		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}

	
	@GetMapping("/displayAll")
	public ResponseEntity<List<EmployeeDto>> displayAllEmployee()
	{
		List<EmployeeDto> displayDto = empServ.displayAllEmployee();
		return new ResponseEntity<>(displayDto,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/displayEmployeeById/{id}")
	public ResponseEntity<EmployeeDto> displayAllEmployeeId(@PathVariable Long id)
	{
		EmployeeDto newDto =empServ.displayEmployeeById(id);
		return new ResponseEntity<>(newDto,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteEmployeeId/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable Long id) 
	{
		String msg = empServ.deleteEmployeeById(id);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	}
	@PutMapping("/updateEmployeeById/{id}")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto dto, @PathVariable Long id)
	{
		EmployeeDto updatedEmp = empServ.updateEmployee(dto, id);
		return new ResponseEntity<>(updatedEmp,HttpStatus.OK);
	}
}
