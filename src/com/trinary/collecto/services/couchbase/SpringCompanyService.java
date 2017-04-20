package com.trinary.collecto.services.couchbase;

import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.PageRequest;

import com.trinary.collecto.configs.CouchbaseConfig;
import com.trinary.collecto.dao.CompanyRepository;
import com.trinary.collecto.entities.Company;
import com.trinary.collecto.services.CompanyService;

@ApplicationScoped
@Alternative
public class SpringCompanyService implements CompanyService {
	private ConfigurableApplicationContext ctx;
	private CompanyRepository companyDao;
	
	@PostConstruct
	public void init() {
		ctx = new AnnotationConfigApplicationContext(CouchbaseConfig.class);
		companyDao = ctx.getBean(CompanyRepository.class);
	}
	
	@PreDestroy
	public void deinit() {
		ctx.close();
	}

	@Override
	public List<Company> getCompanies(Integer page, Integer pageSize) {
		return companyDao.findAll(new PageRequest(page - 1, pageSize)).getContent();
	}

	@Override
	public Company getCompany(String name) {
		return companyDao.findByAbbreviation(name);
	}

	@Override
	public Company createCompany(Company company) {
		Company c = companyDao.findByAbbreviation(company.getName());
		
		if (c != null) {
			return company;
		}
		
		company.setId(UUID.randomUUID().toString());
		return companyDao.save(company);
	}

	@Override
	public Company updateCompany(String name, Company company) {
		Company c = companyDao.findByAbbreviation(name);
		
		if (c == null) {
			createCompany(company);
		}
		
		company.setId(c.getId());
		return companyDao.save(company);
	}
}