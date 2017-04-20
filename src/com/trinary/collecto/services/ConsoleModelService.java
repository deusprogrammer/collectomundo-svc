package com.trinary.collecto.services;

import java.util.List;

import com.trinary.collecto.entities.ConsoleModel;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;

public interface ConsoleModelService {
	public List<ConsoleModel> getConsoleModels(String console, Integer page, Integer pageSize);
	public ConsoleModel getConsoleModel(String id);
	public ConsoleModel getConsoleModelByModelNumber(String modelNumber);
	public ConsoleModel createConsoleModel(ConsoleModel model) throws CollectomundoBusinessException;
	public ConsoleModel updateConsoleModel(String id, ConsoleModel model) throws CollectomundoBusinessException;
}
