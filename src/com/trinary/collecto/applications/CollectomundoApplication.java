package com.trinary.collecto.applications;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.trinary.collecto.exceptions.mappers.CollectomundoBusinessExceptionMapper;
import com.trinary.collecto.resources.AccessoryResource;
import com.trinary.collecto.resources.CompanyResource;
import com.trinary.collecto.resources.ConsoleModelResource;
import com.trinary.collecto.resources.ConsoleResource;
import com.trinary.collecto.resources.GameResource;

@ApplicationPath("v1")
public class CollectomundoApplication extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<>();
		
		// Resources
		classes.add(AccessoryResource.class);
		classes.add(CompanyResource.class);
		classes.add(ConsoleResource.class);
		classes.add(ConsoleModelResource.class);
		classes.add(GameResource.class);
		
		// Exception mappers
		classes.add(CollectomundoBusinessExceptionMapper.class);
		
		return classes;
	}
	
}