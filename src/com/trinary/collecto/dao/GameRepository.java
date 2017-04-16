package com.trinary.collecto.dao;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.trinary.collecto.entities.Game;

@N1qlPrimaryIndexed
public interface GameRepository extends PagingAndSortingRepository<Game, String> {
	public List<Game> findByConsole(String console);
}