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

import com.trinary.collecto.converters.AccessoryConverter;
import com.trinary.collecto.entities.Accessory;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.ro.AccessoryRO;
import com.trinary.collecto.services.AccessoryService;

@Path("/accessories")
@Produces(MediaType.APPLICATION_JSON)
public class AccessoryResource {
	@Context
	UriInfo uriInfo;
	
	@Inject
	AccessoryService accessoryService;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createAccessory(AccessoryRO accessoryRo) throws CollectomundoBusinessException {
		AccessoryConverter converter = new AccessoryConverter(uriInfo);
		
		Accessory accessory = converter.convertRO(accessoryRo);
		accessoryService.createAccessory(accessory);
		
		return Response.ok(converter.convertEntity(accessory)).build();
	}
	
	@Path("/{id}")
	@GET
	public Response getAccessory(@PathParam("id") String id) {
		AccessoryConverter converter = new AccessoryConverter(uriInfo);
		
		Accessory accessory = accessoryService.getAccessory(id);
		
		return Response.ok(converter.convertEntity(accessory)).build();
	}
	
	@Path("/{id}")
	@PUT
	public Response updateAccessory(@PathParam("id") String id, AccessoryRO accessoryRo) throws CollectomundoBusinessException {
		AccessoryConverter converter = new AccessoryConverter(uriInfo);
		
		Accessory accessory = converter.convertRO(accessoryRo);
		accessory = accessoryService.updateAccessory(id, accessory);
		
		return Response.ok(converter.convertEntity(accessory)).build();
	}
}