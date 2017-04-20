package com.trinary.collecto.converters;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.trinary.collecto.entities.Company;
import com.trinary.collecto.resources.CompanyResource;
import com.trinary.collecto.ro.CompanyRO;
import com.trinary.ro.v2.converter.ROConverter;

public class CompanyConverter extends ROConverter<CompanyRO, Company> {

	public CompanyConverter(UriInfo uriInfo) {
		super(uriInfo);
	}

	@Override
	protected Company _convertRO(CompanyRO ro) {
		Company entity = new Company();
		entity.setAbbreviation(ro.getId());
		entity.setName(ro.getName());
		return entity;
	}

	@Override
	protected CompanyRO _convertEntity(Company entity) {
		CompanyRO ro = new CompanyRO();
		ro.setId(entity.getAbbreviation());
		ro.setName(entity.getName());
		return ro;
	}

	@Override
	protected CompanyRO _addLinks(CompanyRO object) {
		if (object == null || object.getId() == null) {
			return null;
		}
		
		URI self = this.getUriInfo()
				.getBaseUriBuilder()
				.path(CompanyResource.class)
				.path(CompanyResource.class, "getCompany")
				.build(object.getId());
		
		URI consoles = this.getUriInfo()
				.getBaseUriBuilder()
				.path(CompanyResource.class)
				.path(CompanyResource.class, "getCompanyConsoles")
				.build(object.getId());
		
		object.addLink("self", self.toString());
		object.addLink("consoles", consoles.toString());
		
		return object;
	}

}
