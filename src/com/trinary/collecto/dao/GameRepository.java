package com.trinary.collecto.dao;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trinary.collecto.entities.Game;

@N1qlPrimaryIndexed
public interface GameRepository extends PagingAndSortingRepository<Game, String> {
	public Slice<Game> findByConsole(String console, Pageable pageable);
}