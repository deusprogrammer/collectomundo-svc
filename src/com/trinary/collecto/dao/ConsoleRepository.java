package com.trinary.collecto.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trinary.collecto.entities.Console;

public interface ConsoleRepository extends PagingAndSortingRepository<Console, String> {
	public Console findByAbbreviation(String abbreviation);
	public Slice<Console> findByCompany(String company, Pageable pageable);
}