package com.havenora.backend.mapper;

import com.havenora.backend.dto.RoomDto;
import com.havenora.backend.model.Room;

public class RoomMapper {
	public static RoomDto mapModelToDto(Room r) {
		return new RoomDto(r.getRoomId(),r.getFlatId(),r.getRoomNo(),r.getCapacity(),r.getCurrentOccupancy(),r.getStatus());

	}
	
	public static Room mapDtoToModel(RoomDto d) {
		return new Room(d.getRoomId(),d.getFlatId(),d.getRoomNo(),d.getCapacity(),d.getCurrentOccupancy(),d.getStatus());

	}

}
