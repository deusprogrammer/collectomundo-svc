package com.trinary.collecto.services.couchbase;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.trinary.collecto.configs.CouchbaseConfig;
import com.trinary.collecto.dao.CompanyRepository;
import com.trinary.collecto.dao.ConsoleRepository;
import com.trinary.collecto.entities.Company;
import com.trinary.collecto.entities.Console;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;

@ApplicationScoped
public class SpringValidationService {
	private ConfigurableApplicationContext ctx;
	private CompanyRepository companyDao;
	private ConsoleRepository consoleDao;

	@PostConstruct
	public void init() {
		ctx = new AnnotationConfigApplicationContext(CouchbaseConfig.class);
		companyDao = ctx.getBean(CompanyRepository.class);
		consoleDao = ctx.getBean(ConsoleRepository.class);
	}
	
	public void validateCompany(String company) throws CollectomundoBusinessException {
		Company c = companyDao.findByName(company);
		
		if (c == null) {
			throw new CollectomundoBusinessException("Company " + company + " does not exist.");
		}
	}

	public void validateConsole(String console) throws CollectomundoBusinessException {
		Console c = consoleDao.findByAbbreviation(console);
		
		if (c == null) {
			throw new CollectomundoBusinessException("Console " + console + " does not exist.");
		}
	}

}
