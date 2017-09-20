package com.dineshonjava.sbmdb.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ScProductRepository extends MongoRepository<Product, String> {
	

	  public Product findByName(String name);
}
