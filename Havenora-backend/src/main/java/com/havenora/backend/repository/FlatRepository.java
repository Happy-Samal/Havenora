package com.havenora.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.havenora.backend.model.Flat;
import com.havenora.backend.model.Tower;

@Repository
public interface FlatRepository extends JpaRepository<Flat , Long> {
	List<Flat> findByTowerId(Long towerId);
}
