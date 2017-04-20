package com.trinary.collecto.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trinary.collecto.entities.Accessory;

public interface AccessoryRepository extends PagingAndSortingRepository<Accessory, String> {
	public Slice<Accessory> findByConsole(String console, Pageable pageable);
}