package com.jade.service;

import com.jade.model.MongodbConnectionRequest;

public interface ApiService {

	public boolean getConnectionFromMongo(MongodbConnectionRequest request);
}
