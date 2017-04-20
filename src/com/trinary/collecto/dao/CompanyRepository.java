package com.trinary.collecto.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.trinary.collecto.entities.Company;

public interface CompanyRepository extends PagingAndSortingRepository<Company, String> {
	public Company findByAbbreviation(String abbreviation);
}