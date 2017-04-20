package com.trinary.collecto.dao;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trinary.collecto.entities.ConsoleModel;

public interface ConsoleModelRepository extends PagingAndSortingRepository<ConsoleModel, String> {
	public Slice<ConsoleModel> findByConsole(String console, Pageable pageable);
	public ConsoleModel findByModelNumber(String modelNumber);
}