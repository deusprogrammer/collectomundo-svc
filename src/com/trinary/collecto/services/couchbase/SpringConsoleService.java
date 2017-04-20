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
import com.trinary.collecto.dao.ConsoleRepository;
import com.trinary.collecto.entities.Console;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.services.ConsoleService;

@ApplicationScoped
@Alternative
public class SpringConsoleService implements ConsoleService {
	@Inject SpringValidationService validator;
	
	private ConfigurableApplicationContext ctx;
	private ConsoleRepository consoleDao;
	
	@PostConstruct
	public void init() {
		ctx = new AnnotationConfigApplicationContext(CouchbaseConfig.class);
		consoleDao = ctx.getBean(ConsoleRepository.class);
	}
	
	@PreDestroy
	public void deinit() {
		ctx.close();
	}

	@Override
	public List<Console> getConsoles(Integer page, Integer pageSize) {
		return (List<Console>) consoleDao.findAll();
	}

	@Override
	public Console getConsole(String abbrev) {
		return consoleDao.findByAbbreviation(abbrev);
	}

	@Override
	public Console createConsole(Console console) throws CollectomundoBusinessException {
		validator.validateCompany(console.getCompany());
		
		Console c = consoleDao.findByAbbreviation(console.getAbbreviation());
		
		if (c != null) {
			return c;
		}
		
		console.setId(UUID.randomUUID().toString());
		return consoleDao.save(console);
	}

	@Override
	public Console updateConsole(String abbrev, Console console) throws CollectomundoBusinessException {
		validator.validateCompany(console.getCompany());
		
		Console c = consoleDao.findByAbbreviation(abbrev);
		
		if (c == null) {
			return createConsole(console);
		}
		
		console.setId(c.getId());
		return consoleDao.save(console);
	}

	@Override
	public List<Console> getConsolesByCompany(String company, Integer page, Integer pageSize) {
		return consoleDao.findByCompany(company, new PageRequest(page - 1, pageSize)).getContent();
	}

}