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
import com.trinary.collecto.dao.AccessoryRepository;
import com.trinary.collecto.entities.Accessory;
import com.trinary.collecto.exceptions.CollectomundoBusinessException;
import com.trinary.collecto.services.AccessoryService;

@ApplicationScoped
@Alternative
public class SpringAccessoryService implements AccessoryService {
	@Inject SpringValidationService validator;
	
	private ConfigurableApplicationContext ctx;
	private AccessoryRepository accessoryDao;
	
	@PostConstruct
	public void init() {
		ctx = new AnnotationConfigApplicationContext(CouchbaseConfig.class);
		accessoryDao = ctx.getBean(AccessoryRepository.class);
	}
	
	@PreDestroy
	public void deinit() {
		ctx.close();
	}

	@Override
	public List<Accessory> getAccessories(String console, Integer page, Integer pageSize) {
		return accessoryDao.findByConsole(console, new PageRequest(page - 1, pageSize)).getContent();
	}

	@Override
	public Accessory getAccessory(String id) {
		return accessoryDao.findOne(id);
	}

	@Override
	public Accessory createAccessory(Accessory accessory) throws CollectomundoBusinessException {
		//validator.validateCompany(accessory.getCompany());
		//validator.validateConsole(accessory.getConsole());
		
		String id = UUID.randomUUID().toString();
		accessory.setId(id);
		
		accessoryDao.save(accessory);
		
		return accessory;
	}

	@Override
	public Accessory updateAccessory(String id, Accessory accessory) throws CollectomundoBusinessException {
		//validator.validateCompany(accessory.getCompany());
		//validator.validateConsole(accessory.getConsole());
		
		Accessory a = accessoryDao.findOne(id);
		
		if (a == null) {
			return createAccessory(accessory);
		}
		
		accessory.setId(id);
		return accessoryDao.save(accessory);
	}

}
