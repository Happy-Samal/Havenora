package com.havenora.backend.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havenora.backend.dto.FlatDto;
import com.havenora.backend.dto.TowerDto;
import com.havenora.backend.mapper.FlatMapper;
import com.havenora.backend.mapper.RoomMapper;
import com.havenora.backend.mapper.TowerMapper;
import com.havenora.backend.model.Flat;
import com.havenora.backend.model.Room;
import com.havenora.backend.model.Tower;
import com.havenora.backend.repository.FlatRepository;
import com.havenora.backend.service.FlatServiceInterface;


@Service
public class FlatServiceImplementation implements FlatServiceInterface {
	
	@Autowired
	private  FlatRepository flatRepo;
	
	@Override
	public FlatDto createFlat(FlatDto dto) {
		// TODO Auto-generated method stub
		Flat f = FlatMapper.mapDtoToModel(dto);
		Flat flat = flatRepo.save(f);
		return FlatMapper.mapModelToDto(flat);
	}

	@Override
	public List<FlatDto> findByTower(Long towerId) {
		// TODO Auto-generated method stub
		List<Flat> flats = flatRepo.findByTowerId(towerId);
		List<FlatDto> flatDto = new ArrayList<FlatDto>();
		for(Flat f: flats)
		{
			flatDto.add(FlatMapper.mapModelToDto(f));
		}
		return flatDto;
	}

	

	@Override
	public FlatDto displayById(Long id) {
		// TODO Auto-generated method stub
		if(flatRepo.existsById(id)) 
		{
			Optional<Flat> emp = flatRepo.findById(id);
			return FlatMapper.mapModelToDto(emp.get());
		}
		return null;
	}



}
