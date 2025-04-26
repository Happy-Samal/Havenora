package com.havenora.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@Entity
public class Employee {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private Long employeeId;
	    private String name;
	    private String email;
	    private String password;
	

}
