package com.havenora.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.havenora.backend.model.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room,Long>{
	List<Room> findByFlatId(Long flatId);
}
