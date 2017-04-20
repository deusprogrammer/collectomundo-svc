package com.trinary.collecto.services.couchbase;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;

import com.trinary.collecto.configs.CouchbaseConfig;
import com.trinary.collecto.dao.ConsoleModelRepository;
import com.trinary.collecto.entities.ConsoleModel;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.services.ConsoleModelService;

@ApplicationScoped
@Alternative
public class SpringConsoleModelService implements ConsoleModelService {
	@Inject SpringValidationService validator;
	
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
	public List<ConsoleModel> getConsoleModels(String console, Integer page, Integer pageSize) {
		return modelDao.findByConsole(console, new PageRequest(page - 1, pageSize)).getContent();
	}

	@Override
	public ConsoleModel getConsoleModel(String id) {
		return modelDao.findOne(id);
	}

	@Override
	public ConsoleModel createConsoleModel(ConsoleModel model) throws CollectomundoBusinessException {
		//validator.validateCompany(model.getCompany());
		//validator.validateConsole(model.getConsole());
		
		String id = UUID.randomUUID().toString();
		model.setId(id);
		
		modelDao.save(model);
		
		return model;
	}

	@Override
	public ConsoleModel getConsoleModelByModelNumber(String modelNumber) {
		return modelDao.findByModelNumber(modelNumber);
	}

	@Override
	public ConsoleModel updateConsoleModel(String id, ConsoleModel model) throws CollectomundoBusinessException {
		//validator.validateCompany(model.getCompany());
		//validator.validateConsole(model.getConsole());
		
		ConsoleModel cm = modelDao.findOne(id);
		
		if (cm == null) {
			return createConsoleModel(model);
		}
		
		model.setId(id);
		return modelDao.save(model);
	}

}
