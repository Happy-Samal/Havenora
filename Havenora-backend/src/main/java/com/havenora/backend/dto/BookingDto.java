package com.havenora.backend.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Data
public class BookingDto {
	private Long bookingId;
	private Long employeeId;
	private Long roomId;
	private String fromDate;
	private String toDate;
	private String status="Pending";
	private Long flatId;
	private Long towerId;
	private String location;
}

