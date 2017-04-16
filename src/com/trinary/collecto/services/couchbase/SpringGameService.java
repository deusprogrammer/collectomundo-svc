package com.trinary.collecto.services.couchbase;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trinary.collecto.configs.CouchbaseConfig;
import com.trinary.collecto.dao.GameRepository;
import com.trinary.collecto.entities.Game;
import com.trinary.collecto.services.GameService;

@Alternative
@ApplicationScoped
public class SpringGameService implements GameService {
	private GameRepository gameDao;
	private ConfigurableApplicationContext ctx;
	
	@PostConstruct
	public void init() {
		ctx = new AnnotationConfigApplicationContext(CouchbaseConfig.class);
		gameDao = ctx.getBean(GameRepository.class);
	}
	
	@PreDestroy
	public void deinit() {
		ctx.close();
	}

	@Override
	public List<Game> getGames() {
		return (List<Game>)gameDao.findAll();
	}

	@Override
	public Game getGame(String id) {
		return gameDao.findOne(id);
	}

	@Override
	public Game createGame(Game game) {
		game.setId(UUID.randomUUID().toString());
		return gameDao.save(game);
	}

	@Override
	public Game updateGame(String id, Game game) {
		game.setId(id);
		return gameDao.save(game);
	}

	@Override
	public List<Game> getGamesByConsole(String console) {
		return gameDao.findByConsole(console);
	}
}