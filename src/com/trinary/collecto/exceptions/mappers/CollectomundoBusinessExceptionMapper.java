package com.trinary.collecto.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.ro.CollectomundoErrorRO;

@Provider
public class CollectomundoBusinessExceptionMapper implements ExceptionMapper<CollectomundoBusinessException> {	
	@Override
	public Response toResponse(CollectomundoBusinessException e) {
		CollectomundoErrorRO error = new CollectomundoErrorRO();
		
		error.setCode("BUSINESS");
		error.setMessage(e.getMessage());
		
		return Response.status(400).entity(error).build();
	}

}
