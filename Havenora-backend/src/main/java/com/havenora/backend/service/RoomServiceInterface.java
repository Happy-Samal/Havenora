package com.havenora.backend.service;

import java.util.List;

import com.havenora.backend.dto.RoomDto;



public interface RoomServiceInterface {
	
	public List<RoomDto> displayAllRoom();
	public RoomDto displayRoomById(Long id);
	public List<RoomDto> displayflatById(Long flatId);
	

}
