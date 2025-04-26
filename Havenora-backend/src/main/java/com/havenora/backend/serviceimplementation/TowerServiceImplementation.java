package com.havenora.backend.serviceimplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.havenora.backend.dto.TowerDto;
import com.havenora.backend.mapper.TowerMapper;
import com.havenora.backend.model.Tower;
import com.havenora.backend.repository.TowerRepository;
import com.havenora.backend.service.TowerServiceInterface;

@Service
public class TowerServiceImplementation implements TowerServiceInterface{
	@Autowired
	private TowerRepository towerRepo;
	@Override
	public TowerDto createTower(TowerDto dto) {
		// TODO Auto-generated method stub
		Tower g = TowerMapper.mapDtoToModel(dto);
		return TowerMapper.mapModelToDto(towerRepo.save(g));
	}

	@Override
	public List<TowerDto> displayAllTower() {
		// TODO Auto-generated method stub
		List<Tower> emp = towerRepo.findAll();
		List<TowerDto> emptoList = new ArrayList<TowerDto>();
		for(Tower e: emp)
		{
			emptoList.add(TowerMapper.mapModelToDto(e));
		}
		return emptoList;
	}

	@Override
	public TowerDto displayTowerById(Long id) {
		// TODO Auto-generated method stub
		if(towerRepo.existsById(id)) 
		{
			Optional<Tower> guestHouse = towerRepo.findById(id);
			return TowerMapper.mapModelToDto(guestHouse.get());
		}
		else
		{		
		return null;
		}
	}

	@Override
	public String DeleteTowerById(Long id) {
		// TODO Auto-generated method stub
		if(towerRepo.existsById(id)) 
		{
			towerRepo.deleteById(id);
			return "Deleted Tower Record Successfully";
		}else
		{
			return "No such record exist";
		}
	}

	@Override
	public TowerDto UpdateTower(TowerDto t, Long id) {
		// TODO Auto-generated method stub
		if(towerRepo.existsById(id)) 
		{
			Optional<Tower> guestHouse = towerRepo.findById(id);
			Tower oldTower = guestHouse.get();
			oldTower.setLocation(t.getLocation());
			oldTower.setName(t.getName());

			return TowerMapper.mapModelToDto(towerRepo.save(oldTower));
			}
		return null;
	}

	@Override
	public List<TowerDto> displayByLocation(String location) {
		// TODO Auto-generated method stub
		List<Tower> towers = towerRepo.findByLocation(location);
		List<TowerDto> towerDto = new ArrayList<TowerDto>();
		for(Tower t: towers)
		{
			towerDto.add(TowerMapper.mapModelToDto(t));
		}
		return towerDto;
	}
}
