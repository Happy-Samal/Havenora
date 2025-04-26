package com.havenora.backend.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havenora.backend.dto.RoomDto;
import com.havenora.backend.mapper.RoomMapper;
import com.havenora.backend.model.Room;
import com.havenora.backend.repository.RoomRepository;
import com.havenora.backend.service.RoomServiceInterface;



@Service
public class RoomServiceImplementation implements RoomServiceInterface {
	@Autowired
	private RoomRepository roomRepo;
	
public List<RoomDto> displayAllRoom()
	{
	List<Room> room = roomRepo.findAll();
	List<RoomDto> emptoList = new ArrayList<RoomDto>();
	for(Room r: room)
	{
		emptoList.add(RoomMapper.mapModelToDto(r));
	}
	return emptoList;
	}
	
	public RoomDto displayRoomById(Long id) 
	{
		if(roomRepo.existsById(id)) 
		{
			Optional<Room> emp = roomRepo.findById(id);
			return RoomMapper.mapModelToDto(emp.get());
		}
		return null;
		
	}

	@Override
	public List<RoomDto> displayflatById(Long flatId) {
		
			List<Room> emp = roomRepo.findByFlatId(flatId);
			List<RoomDto> dto = new ArrayList<>();
			for(Room el:emp){
				dto.add(RoomMapper.mapModelToDto(el));
			}
			
			return dto;
	}

	
}

