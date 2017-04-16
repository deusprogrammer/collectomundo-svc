package com.trinary.collecto.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.trinary.collecto.converters.GameConverter;
import com.trinary.collecto.entities.Game;
import com.trinary.collecto.ro.GameRO;
import com.trinary.collecto.services.GameService;

@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class GameResource {
	@Context
	private UriInfo uriInfo;
	
	@Inject
	private GameService gameService;
	
	@POST
	public Response createGame(GameRO gameRo) {
		GameConverter converter = new GameConverter(uriInfo);
		
		Game game = converter.convertRO(gameRo);
		game = gameService.createGame(game);
		
		return Response.ok(converter.convertEntity(game)).build();
	}
	
	@GET
	public Response getGames() {
		List<Game> games = gameService.getGames();
		
		GameConverter converter = new GameConverter(uriInfo);
		
		return Response.ok(converter.convertEntityList(games)).build();
	}
	
	@Path("/{id}")
	@GET
	public Response getGame(@PathParam("id") String id) {
		Game game = gameService.getGame(id);
		
		GameConverter converter = new GameConverter(uriInfo);
		
		return Response.ok(converter.convertEntity(game)).build();
	}
	
	@Path("/{id}")
	@PUT
	public Response updateGame(@PathParam("id") String id, GameRO gameRo) {
		GameConverter converter = new GameConverter(uriInfo);
		
		Game game = converter.convertRO(gameRo);
		game = gameService.updateGame(id, game);
		
		return Response.ok(converter.convertEntity(game)).build();
	}
}