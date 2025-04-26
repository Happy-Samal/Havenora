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
public class Room
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long roomId;
	private Long flatId;
	private int roomNo;
	private int capacity;
	private int currentOccupancy;
	private String status;
	
	
}
