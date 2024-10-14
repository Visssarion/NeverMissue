package dev.vissa.nevermissue.server;

import java.sql.Connection;
import java.sql.Statement;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
	private String IP;
	private Integer port;
	
	
	
	public Database(String IP, Integer port) {
		this.IP = IP;
		this.port = port;
	}



	public void connect() {
		
	}
}
