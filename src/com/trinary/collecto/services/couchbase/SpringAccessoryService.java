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
import com.trinary.collecto.dao.AccessoryRepository;
import com.trinary.collecto.entities.Accessory;
import com.trinary.collecto.services.AccessoryService;

@ApplicationScoped
@Alternative
public class SpringAccessoryService implements AccessoryService {
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
	public List<Accessory> getAccessories(String console) {
		return accessoryDao.findByConsole(console);
	}

	@Override
	public Accessory getAccessory(String id) {
		return accessoryDao.findOne(id);
	}

	@Override
	public Accessory createAccessory(Accessory accessory) {
		String id = UUID.randomUUID().toString();
		accessory.setId(id);
		
		accessoryDao.save(accessory);
		
		return accessory;
	}

}
