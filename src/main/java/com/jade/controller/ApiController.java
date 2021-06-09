package com.jade.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jade.model.MongodbConnectionRequest;
import com.jade.model.Response;
import com.jade.service.ApiService;

/**
 * @author saklen.mulla
 *
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/mongodb")
public class ApiController {
	
	Logger logger=LoggerFactory.getLogger(ApiController.class);
	
	@Autowired
	private ApiService apiService;

	@PostMapping("/getConnection")
	public Response getConnection(@RequestBody MongodbConnectionRequest request) {
		if(apiService.getConnectionFromMongo(request)) {
			logger.info("Mongodb Connected Successfully");
			return new Response(HttpStatus.OK.value(), "OK", "MongoDb Connection : Success");
		}
		
		logger.error("Mongodb Connection Failed. Check credentials");
		return new Response(HttpStatus.BAD_REQUEST.value(), "Something went wrong.Please check Credentials", "MongoDb Connection : Failed");
	}
}
