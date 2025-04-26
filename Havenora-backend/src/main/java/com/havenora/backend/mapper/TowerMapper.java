package com.havenora.backend.mapper;
import com.havenora.backend.dto.TowerDto;
import com.havenora.backend.model.Tower;

public class TowerMapper {
	public static TowerDto mapModelToDto(Tower g) {
		return new TowerDto(g.getTowerId(), g.getLocation(), g.getName());
	}
	
	public static Tower mapDtoToModel(TowerDto g) {
		return new Tower(g.getTowerId(), g.getLocation(), g.getName());
	}

}