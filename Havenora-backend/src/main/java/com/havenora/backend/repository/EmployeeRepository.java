package com.havenora.backend.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.havenora.backend.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	Employee findByEmailAndPassword(String email , String password);

}