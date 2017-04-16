package com.trinary.collecto.configs;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;
import org.springframework.data.couchbase.repository.support.IndexManager;

@Configuration
@EnableCouchbaseRepositories("com.trinary.collecto.dao")
public class CouchbaseConfig extends AbstractCouchbaseConfiguration {

    @Override
    protected List<String> getBootstrapHosts() {
        return Collections.singletonList("10.0.0.170");
    }

    @Override
    protected String getBucketName() {
        return "trinventory";
    }

    @Override
    protected String getBucketPassword() {
        return "test";
    }

	@Override
	public IndexManager indexManager() {
		// TODO Auto-generated method stub
		return new IndexManager(true, false, false);
	}
}