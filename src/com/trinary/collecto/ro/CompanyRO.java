package com.trinary.collecto.ro;

import com.trinary.ro.v2.RepresentationObject;

public class CompanyRO extends RepresentationObject {
	private String id;
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
