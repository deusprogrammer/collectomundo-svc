package com.trinary.collecto.services;

import java.util.List;

import com.trinary.collecto.entities.ConsoleModel;

public interface ConsoleModelService {
	public List<ConsoleModel> getConsoleModels(String console);
	public ConsoleModel getConsoleModel(String id);
	public ConsoleModel getConsoleModelByModelNumber(String modelNumber);
	public ConsoleModel createConsoleModel(ConsoleModel model);
}
