package com.trinary.collecto.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trinary.collecto.entities.Accessory;

public interface AccessoryRepository extends CrudRepository<Accessory, String> {
	public List<Accessory> findByConsole(String console);
}
