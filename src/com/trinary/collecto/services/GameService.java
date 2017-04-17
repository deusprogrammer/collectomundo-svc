package com.trinary.collecto.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import com.trinary.collecto.entities.Game;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;

@ApplicationScoped
public interface GameService {
	public List<Game> getGames();
	public Game createGame(Game game) throws CollectomundoBusinessException;
	public Game updateGame(String id, Game game) throws CollectomundoBusinessException;
	public Game getGame(String id);
	public List<Game> getGamesByConsole(String console);
}