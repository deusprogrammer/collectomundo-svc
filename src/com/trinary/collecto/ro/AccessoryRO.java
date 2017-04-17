package com.trinary.collecto.ro;

import com.trinary.ro.v2.RepresentationObject;

public class AccessoryRO extends RepresentationObject {
	private String id;
	private String name;
	private String company;
	private String console;
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
	public String getConsole() {
		return console;
	}
	public void setConsole(String console) {
		this.console = console;
	}
}
