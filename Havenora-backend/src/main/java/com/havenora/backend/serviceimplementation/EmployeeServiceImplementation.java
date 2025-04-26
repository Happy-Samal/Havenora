package com.havenora.backend.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.havenora.backend.controller.BookingController;
import com.havenora.backend.dto.EmployeeDto;
import com.havenora.backend.mapper.EmployeeMapper;
import com.havenora.backend.model.Employee;
import com.havenora.backend.repository.EmployeeRepository;
import com.havenora.backend.service.EmployeeServiceInterface;


@Service
public class EmployeeServiceImplementation implements EmployeeServiceInterface{

    private final BookingController bookingController;
	
	@Autowired
	private EmployeeRepository empRepo;

    EmployeeServiceImplementation(BookingController bookingController) {
        this.bookingController = bookingController;
    }
	
	 public EmployeeDto createEmployee(EmployeeDto dto)
	 	{
		
		 Employee e = EmployeeMapper.mapDtoToModel(dto);
			return EmployeeMapper.mapModelToDto(empRepo.save(e));
	    }
	
	 @Override
		public EmployeeDto findEmployee(EmployeeDto dto) {
			// TODO Auto-generated method stub
		 	Employee e = EmployeeMapper.mapDtoToModel(dto);
		 	String email = e.getEmail();
		 	String password = e.getPassword();
		 	System.out.println(email);
		 	System.out.println(password);
		 	Employee emp = empRepo.findByEmailAndPassword(email, password);
		 	
		 	System.out.println(emp);
			return EmployeeMapper.mapModelToDto(emp);
		}
	 
		public List<EmployeeDto> displayAllEmployee()
		{
			List<Employee> emp = empRepo.findAll();
			List<EmployeeDto> emptoList = new ArrayList<EmployeeDto>();
			for(Employee e: emp)
			{
				emptoList.add(EmployeeMapper.mapModelToDto(e));
			}
			return emptoList;
			
		}
		
		public EmployeeDto displayEmployeeById(Long id)
		{
			if(empRepo.existsById(id)) 
			{
				Optional<Employee> emp = empRepo.findById(id);
				return EmployeeMapper.mapModelToDto(emp.get());
			}
			else
			{		
			return null;
			}
		}
		public String deleteEmployeeById(Long id) 
		{
			if(empRepo.existsById(id)) 
			{
				empRepo.deleteById(id);
				return "Deleted Employee Record Successfully";
			}else
			{
						return "No such record exist";
			}
		}
			
		

		public EmployeeDto updateEmployee(EmployeeDto d, Long id)
		{
			if(empRepo.existsById(id)) 
			{
				Optional<Employee> emp = empRepo.findById(id);
				Employee oldEmp = emp.get();
				oldEmp.setName(d.getName());
				oldEmp.setEmail(d.getEmail());
				oldEmp.setPassword(d.getPassword());

				return EmployeeMapper.mapModelToDto(empRepo.save(oldEmp));
				}
			return null;
		}


		
			
		

	
}

