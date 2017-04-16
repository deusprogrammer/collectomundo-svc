package com.trinary.collecto.dao;

import org.springframework.data.repository.CrudRepository;

import com.trinary.collecto.entities.Company;

public interface CompanyRepository extends CrudRepository<Company, String> {
	public Company findByName(String name);
}
