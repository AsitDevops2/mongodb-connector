package com.jade.service.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.jade.model.MongodbConnectionRequest;
import com.jade.service.ApiService;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Service
public class ApiServiceImpl implements ApiService{
	
	Logger logger=LoggerFactory.getLogger(ApiServiceImpl.class);
	
	@Override
	public boolean getConnectionFromMongo(MongodbConnectionRequest request) {
		boolean status=true;
		ArrayList<String> dbList=new ArrayList<String>();
		
		logger.info("User {} request's Mongodb connection for {} database",request.getUserName(),request.getDb());

		MongoCredential mongoCredential=MongoCredential.createCredential(request.getUserName(),
																		 request.getDb(),
																		 request.getPassword().toCharArray());
		
		MongoClient mongoClient=null;
		try {
			MongoClientSettings settings=MongoClientSettings.builder()
					.credential(mongoCredential)
					.applyToClusterSettings(builder -> 
						builder.hosts(Arrays.asList(new ServerAddress(request.getServerAddress(),request.getPort()))))
					.build();
					
			mongoClient = MongoClients.create(settings);
			mongoClient.listDatabaseNames().forEach((db)->dbList.add(db));
			
			logger.info("{} databases found",dbList.size());
			
		} catch (MongoException e) {
			logger.error(e.getMessage());
			status= false;
		}
		
		return status;
	}

}
