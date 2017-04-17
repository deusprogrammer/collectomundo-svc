package com.trinary.collecto.resources;

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

import com.trinary.collecto.converters.ConsoleModelConverter;
import com.trinary.collecto.entities.ConsoleModel;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.ro.ConsoleModelRO;
import com.trinary.collecto.services.ConsoleModelService;

@Path("/console-models")
@Produces(MediaType.APPLICATION_JSON)
public class ConsoleModelResource {
	@Context
	UriInfo uriInfo;
	
	@Inject
	ConsoleModelService modelService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createConsoleModel(ConsoleModelRO consoleModelRo) throws CollectomundoBusinessException {
		ConsoleModelConverter converter = new ConsoleModelConverter(uriInfo);
		
		ConsoleModel model = converter.convertRO(consoleModelRo);
		modelService.createConsoleModel(model);
		
		return Response.ok(converter.convertEntity(model)).build();
	}
	
	@Path("/{id}")
	@GET
	public Response getConsoleModel(@PathParam("id") String id) {
		ConsoleModelConverter converter = new ConsoleModelConverter(uriInfo);
		
		ConsoleModel model = modelService.getConsoleModel(id);
		
		return Response.ok(converter.convertEntity(model)).build();
	}
	
	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateConsoleModel(@PathParam("id") String id, ConsoleModelRO consoleModelRo) throws CollectomundoBusinessException {
		ConsoleModelConverter converter = new ConsoleModelConverter(uriInfo);
		
		ConsoleModel model = converter.convertRO(consoleModelRo);
		model = modelService.updateConsoleModel(id, model);
		
		return Response.ok(converter.convertEntity(model)).build();
	}
}