package com.trinary.collecto.services;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.trinary.collecto.entities.Console;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;

@ApplicationScoped
public interface ConsoleService {
	public List<Console> getConsoles();
	public Console getConsole(String id);
	public Console createConsole(Console console) throws CollectomundoBusinessException;
	public Console updateConsole(String id, Console console) throws CollectomundoBusinessException;
	public List<Console> getConsolesByCompany(String company);
}