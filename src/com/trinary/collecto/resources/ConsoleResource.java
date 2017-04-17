package com.trinary.collecto.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
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

import com.trinary.collecto.converters.AccessoryConverter;
import com.trinary.collecto.converters.ConsoleConverter;
import com.trinary.collecto.converters.ConsoleModelConverter;
import com.trinary.collecto.converters.GameConverter;
import com.trinary.collecto.entities.Accessory;
import com.trinary.collecto.entities.Console;
import com.trinary.collecto.entities.ConsoleModel;
import com.trinary.collecto.entities.Game;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.ro.ConsoleRO;
import com.trinary.collecto.services.AccessoryService;
import com.trinary.collecto.services.ConsoleModelService;
import com.trinary.collecto.services.ConsoleService;
import com.trinary.collecto.services.GameService;


@Path("/consoles")
@Produces(MediaType.APPLICATION_JSON)
public class ConsoleResource {
	@Context UriInfo uriInfo;
	@Inject ConsoleService consoleService;
	@Inject GameService gameService;
	@Inject AccessoryService accessoryService;
	@Inject ConsoleModelService modelService;
	
	@GET
	public Response getConsoles() {
		ConsoleConverter converter = new ConsoleConverter(uriInfo);
		
		List<Console> consoles = consoleService.getConsoles();
		
		return Response.ok(converter.convertEntityList(consoles)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConsole(ConsoleRO consoleRo) throws CollectomundoBusinessException {
		ConsoleConverter converter = new ConsoleConverter(uriInfo);
		Console console = converter.convertRO(consoleRo);
		
		console = consoleService.createConsole(console);
		
		return Response.ok(converter.convertEntity(console)).build();
	}
	
	@Path("/{id}")
	@GET
	public Response getConsole(@PathParam("id") String id) {
		ConsoleConverter converter = new ConsoleConverter(uriInfo);
		
		Console console = consoleService.getConsole(id);
		
		return Response.ok(converter.convertEntity(console)).build();
	}
	
	@Path("/{id}/games")
	@GET
	public Response getConsoleGames(@PathParam("id") String id) {
		GameConverter converter = new GameConverter(uriInfo);
		
		List<Game> games = gameService.getGamesByConsole(id);
		
		return Response.ok(converter.convertEntityList(games)).build();
	}
	
	@Path("/{id}/models")
	@GET
	public Response getConsoleModels(@PathParam("id") String id) {
		ConsoleModelConverter converter = new ConsoleModelConverter(uriInfo);
		
		List<ConsoleModel> models = modelService.getConsoleModels(id);
		
		return Response.ok(converter.convertEntityList(models)).build();
	}
	
	@Path("/{id}/accessories")
	@GET
	public Response getConsoleAccessories(@PathParam("id") String id) {
		AccessoryConverter converter = new AccessoryConverter(uriInfo);
		
		List<Accessory> accessories = accessoryService.getAccessories(id);
		
		return Response.ok(converter.convertEntityList(accessories)).build();
	}
	
	@Path("/{id}")
	@PUT
	public Response updateConsole(@PathParam("id") String id, ConsoleRO consoleRo) throws CollectomundoBusinessException {
		ConsoleConverter converter = new ConsoleConverter(uriInfo);
		Console console = converter.convertRO(consoleRo);
		
		console = consoleService.updateConsole(id, console);
		
		return Response.ok(converter.convertEntity(console)).build();
	}
}