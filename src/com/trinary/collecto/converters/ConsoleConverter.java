package com.trinary.collecto.converters;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.trinary.collecto.entities.Console;
import com.trinary.collecto.resources.CompanyResource;
import com.trinary.collecto.resources.ConsoleResource;
import com.trinary.collecto.ro.ConsoleRO;
import com.trinary.ro.v2.converter.ROConverter;

public class ConsoleConverter extends ROConverter<ConsoleRO, Console> {

	public ConsoleConverter(UriInfo uriInfo) {
		super(uriInfo);
	}

	@Override
	protected Console _convertRO(ConsoleRO ro) {
		Console entity = new Console();
		entity.setName(ro.getName());
		entity.setAbbreviation(ro.getId());
		entity.setCompany(ro.getCompany());
		return entity;
	}

	@Override
	protected ConsoleRO _convertEntity(Console entity) {
		ConsoleRO ro = new ConsoleRO();
		ro.setId(entity.getAbbreviation());
		ro.setName(entity.getName());
		ro.setCompany(entity.getCompany());
		return ro;
	}

	@Override
	protected ConsoleRO _addLinks(ConsoleRO object) {
		URI self = this.getUriInfo()
				.getBaseUriBuilder()
				.path(ConsoleResource.class)
				.path(ConsoleResource.class, "getConsole")
				.build(object.getId());
		
		URI company = this.getUriInfo()
				.getBaseUriBuilder()
				.path(CompanyResource.class)
				.path(CompanyResource.class, "getCompany")
				.build(object.getCompany());
		
		URI games = this.getUriInfo()
				.getBaseUriBuilder()
				.path(ConsoleResource.class)
				.path(ConsoleResource.class, "getConsoleGames")
				.build(object.getId());
		
		object.addLink("self", self.toString());
		object.addLink("company", company.toString());
		object.addLink("games", games.toString());
		
		return object;
	}

}
