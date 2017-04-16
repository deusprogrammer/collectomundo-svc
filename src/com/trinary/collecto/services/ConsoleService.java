package com.trinary.collecto.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.trinary.collecto.entities.Console;

@ApplicationScoped
public interface ConsoleService {
	public List<Console> getConsoles();
	public Console getConsole(String id);
	public Console createConsole(Console console);
	public Console updateConsole(String id, Console console);
	public List<Console> getConsolesByCompany(String company);
}