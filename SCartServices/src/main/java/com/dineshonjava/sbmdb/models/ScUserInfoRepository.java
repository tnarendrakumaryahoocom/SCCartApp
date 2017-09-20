package com.dineshonjava.sbmdb.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ScUserInfoRepository extends MongoRepository<ScUserInfo, String> {
	

	  public ScUserInfo findByName(String name);
}
