package com.trinary.collecto.services.couchbase;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trinary.collecto.configs.CouchbaseConfig;
import com.trinary.collecto.dao.ConsoleModelRepository;
import com.trinary.collecto.entities.ConsoleModel;
import com.trinary.collecto.services.ConsoleModelService;

@ApplicationScoped
@Alternative
public class SpringConsoleModelService implements ConsoleModelService {
	private ConfigurableApplicationContext ctx;
	private ConsoleModelRepository modelDao;
	
	@PostConstruct
	public void init() {
		ctx = new AnnotationConfigApplicationContext(CouchbaseConfig.class);
		modelDao = ctx.getBean(ConsoleModelRepository.class);
	}
	
	@PreDestroy
	public void deinit() {
		ctx.close();
	}
	
	@Override
	public List<ConsoleModel> getConsoleModels(String console) {
		return modelDao.findByConsole(console);
	}

	@Override
	public ConsoleModel getConsoleModel(String id) {
		return modelDao.findOne(id);
	}

	@Override
	public ConsoleModel createConsoleModel(ConsoleModel model) {
		String id = UUID.randomUUID().toString();
		model.setId(id);
		
		modelDao.save(model);
		
		return model;
	}

	@Override
	public ConsoleModel getConsoleModelByModelNumber(String modelNumber) {
		return modelDao.findByModelNumber(modelNumber);
	}

}
