package com.trinary.collecto.converters;

import java.net.URI;

import javax.ws.rs.core.UriInfo;

import com.trinary.collecto.entities.Game;
import com.trinary.collecto.resources.CompanyResource;
import com.trinary.collecto.resources.ConsoleResource;
import com.trinary.collecto.resources.GameResource;
import com.trinary.collecto.ro.GameRO;
import com.trinary.ro.v2.converter.ROConverter;

public class GameConverter extends ROConverter<GameRO, Game> {
	public GameConverter(UriInfo uriInfo) {
		super(uriInfo);
	}

	@Override
	protected Game _convertRO(GameRO ro) {
		Game entity = new Game();
		entity.setId(ro.getId());
		entity.setTitle(ro.getTitle());
		entity.setDescription(ro.getDescription());
		entity.setConsole(ro.getConsole());
		entity.setCompany(ro.getCompany());
		entity.setVariant(ro.getVariant());
		return entity;
	}

	@Override
	protected GameRO _convertEntity(Game entity) {
		GameRO ro = new GameRO();
		ro.setId(entity.getId());
		ro.setTitle(entity.getTitle());
		ro.setDescription(entity.getDescription());
		ro.setConsole(entity.getConsole());
		ro.setCompany(entity.getCompany());
		ro.setVariant(entity.getVariant());
		return ro;
	}

	@Override
	protected GameRO _addLinks(GameRO object) {
		if (object != null && object.getId() != null) {
			URI self = this.getUriInfo()
					.getBaseUriBuilder()
					.path(GameResource.class)
					.path(GameResource.class, "getGame")
					.build(object.getId());
			
			object.addLink("self", self.toString());
		}
		
		if (object != null && object.getConsole() != null) {
			URI console = this.getUriInfo()
					.getBaseUriBuilder()
					.path(ConsoleResource.class)
					.path(ConsoleResource.class, "getConsole")
					.build(object.getConsole());
			
			object.addLink("console", console.toString());
		}
		
		if (object != null && object.getCompany() != null) {
			URI company = this.getUriInfo()
					.getBaseUriBuilder()
					.path(CompanyResource.class)
					.path(CompanyResource.class, "getCompany")
					.build(object.getCompany());
			
			object.addLink("company", company.toString());
		}
		
		return object;
	}
}