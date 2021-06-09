package com.jade.model;

public class MongodbConnectionRequest {

	private String serverAddress;
	private int port=27017;
	private String userName;
	private String password;
	private String db;
	
	public String getServerAddress() {
		return serverAddress;
	}
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	
}
