package com.trinary.collecto.converters;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.trinary.collecto.entities.ConsoleModel;
import com.trinary.collecto.resources.CompanyResource;
import com.trinary.collecto.resources.ConsoleModelResource;
import com.trinary.collecto.resources.ConsoleResource;
import com.trinary.collecto.ro.ConsoleModelRO;
import com.trinary.ro.v2.converter.ROConverter;

// TODO Implement ConsoleModelConverter
public class ConsoleModelConverter extends ROConverter<ConsoleModelRO, ConsoleModel> {

	public ConsoleModelConverter(UriInfo uriInfo) {
		super(uriInfo);
	}

	@Override
	protected ConsoleModel _convertRO(ConsoleModelRO ro) {
		ConsoleModel entity = new ConsoleModel();
		entity.setId(ro.getId());
		entity.setName(ro.getName());
		entity.setModelNumber(ro.getModelNumber());
		entity.setCompany(ro.getCompany());
		entity.setConsole(ro.getConsole());
		return entity;
	}

	@Override
	protected ConsoleModelRO _convertEntity(ConsoleModel entity) {
		ConsoleModelRO ro = new ConsoleModelRO();
		ro.setId(entity.getId());
		ro.setName(entity.getName());
		ro.setModelNumber(entity.getModelNumber());
		ro.setCompany(entity.getCompany());
		ro.setConsole(entity.getConsole());
		return ro;
	}

	@Override
	protected ConsoleModelRO _addLinks(ConsoleModelRO object) {
		URI self = this.getUriInfo()
				.getBaseUriBuilder()
				.path(ConsoleModelResource.class)
				.path(ConsoleModelResource.class, "getConsoleModel")
				.build(object.getId());
		
		URI console = this.getUriInfo()
				.getBaseUriBuilder()
				.path(ConsoleResource.class)
				.path(ConsoleResource.class, "getConsole")
				.build(object.getConsole());
		
		URI company = this.getUriInfo()
				.getBaseUriBuilder()
				.path(CompanyResource.class)
				.path(CompanyResource.class, "getCompany")
				.build(object.getCompany());
		
		object.addLink("self", self.toString());
		object.addLink("console", console.toString());
		object.addLink("company", company.toString());
		
		return object;
	}

}
