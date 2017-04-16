package com.trinary.collecto.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.trinary.collecto.entities.Console;

public interface ConsoleRepository extends CrudRepository<Console, String> {
	public Console findByAbbreviation(String abbreviation);
	public List<Console> findByCompany(String company);
}