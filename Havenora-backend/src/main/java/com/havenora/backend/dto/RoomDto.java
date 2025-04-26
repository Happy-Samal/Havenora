package com.havenora.backend.dto;


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
public class RoomDto
{
	
	private Long roomId;
	private Long flatId;
	private int roomNo;
	private int capacity;
	private int currentOccupancy;
	private String status;
	
	
}
