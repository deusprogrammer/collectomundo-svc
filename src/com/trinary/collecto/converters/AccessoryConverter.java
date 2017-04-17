package com.trinary.collecto.converters;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.trinary.collecto.entities.Accessory;
import com.trinary.collecto.resources.AccessoryResource;
import com.trinary.collecto.resources.CompanyResource;
import com.trinary.collecto.resources.ConsoleResource;
import com.trinary.collecto.ro.AccessoryRO;
import com.trinary.ro.v2.converter.ROConverter;

// TODO Implement AccessoryConverter
public class AccessoryConverter extends ROConverter<AccessoryRO, Accessory> {

	public AccessoryConverter(UriInfo uriInfo) {
		super(uriInfo);
	}

	@Override
	protected Accessory _convertRO(AccessoryRO ro) {
		Accessory entity = new Accessory();
		entity.setId(ro.getId());
		entity.setName(ro.getName());
		entity.setCompany(ro.getCompany());
		entity.setConsole(ro.getConsole());
		return entity;
	}

	@Override
	protected AccessoryRO _convertEntity(Accessory entity) {
		AccessoryRO ro = new AccessoryRO();
		ro.setId(entity.getId());
		ro.setName(entity.getName());
		ro.setCompany(entity.getCompany());
		ro.setConsole(entity.getConsole());
		return ro;
	}

	@Override
	protected AccessoryRO _addLinks(AccessoryRO object) {
		URI self = this.getUriInfo()
				.getBaseUriBuilder()
				.path(AccessoryResource.class)
				.path(AccessoryResource.class, "getAccessory")
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
