package com.trinary.collecto.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trinary.collecto.entities.ConsoleModel;

public interface ConsoleModelRepository extends CrudRepository<ConsoleModel, String> {
	public List<ConsoleModel> findByConsole(String console);
	public ConsoleModel findByModelNumber(String modelNumber);
}
