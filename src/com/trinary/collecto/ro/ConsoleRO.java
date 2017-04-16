package com.trinary.collecto.ro;

import com.trinary.ro.v2.RepresentationObject;

public class ConsoleRO extends RepresentationObject {
	private String id;
	private String name;
	private String company;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
}
