package com.havenora.backend.mapper;

import com.havenora.backend.dto.FlatDto;
import com.havenora.backend.model.Flat;

public class FlatMapper {
	
	public static FlatDto mapModelToDto(Flat f) {
		return new FlatDto(f.getFlatId(), f.getFlatNo(), f.getType(),f.getTowerId());
	}
	
	public static Flat mapDtoToModel(FlatDto dto) {
		return new Flat(dto.getFlatId(), dto.getFlatNo(), dto.getType(), dto.getTowerId());
	}


}
