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

import com.trinary.collecto.converters.CompanyConverter;
import com.trinary.collecto.converters.ConsoleConverter;
import com.trinary.collecto.entities.Company;
import com.trinary.collecto.entities.Console;
import com.trinary.collecto.ro.CompanyRO;
import com.trinary.collecto.services.CompanyService;
import com.trinary.collecto.services.ConsoleService;

@Path("/companies")
@Produces(MediaType.APPLICATION_JSON)
public class CompanyResource {
	@Context
	UriInfo uriInfo;
	
	@Inject
	CompanyService companyService;
	
	@Inject
	ConsoleService consoleService;
	
	@GET
	public Response getCompanies() {
		CompanyConverter converter = new CompanyConverter(uriInfo);
		
		List<Company> companies = companyService.getCompanies();
		
		return Response.ok(converter.convertEntityList(companies)).build();
	}
	
	@POST
	public Response createCompany(CompanyRO companyRo) {
		CompanyConverter converter = new CompanyConverter(uriInfo);
		Company company = converter.convertRO(companyRo);
		
		company = companyService.createCompany(company);
		
		return Response.ok(converter.convertEntity(company)).build();
	}
	
	@Path("/{id}")
	@GET
	public Response getCompany(@PathParam("id") String id) {
		CompanyConverter converter = new CompanyConverter(uriInfo);
		
		Company company = companyService.getCompany(id);
		
		return Response.ok(converter.convertEntity(company)).build();
	}
	
	@Path("/{id}/consoles")
	@GET
	public Response getCompanyConsoles(@PathParam("id") String id) {
		ConsoleConverter converter = new ConsoleConverter(uriInfo);
		
		List<Console> consoles = consoleService.getConsolesByCompany(id);
		
		return Response.ok(converter.convertEntityList(consoles)).build();
	}
	
	@Path("/{id}")
	@PUT
	public Response updateCompany(@PathParam("id") String id, CompanyRO companyRo) {
		CompanyConverter converter = new CompanyConverter(uriInfo);
		Company company = converter.convertRO(companyRo);
		
		company = companyService.updateCompany(id, company);
		
		return Response.ok(converter.convertEntity(company)).build();
	}
}
