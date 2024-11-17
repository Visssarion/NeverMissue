package dev.vissa.nevermissue.shared.communication;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.vissa.nevermissue.shared.connection.Connection;

public class RequestReciever {
	private Connection connection;
	private Gson gson = new Gson();
	
	private List<RequestParser> parsers = new ArrayList<RequestParser>();
	
	public boolean recieve() throws IOException {
		String data = connection.recieve();
		if(data == null) {
			return false;
		}
		Request<?> dummyRequest = gson.fromJson(data, Request.class);
		for(RequestParser parser: parsers) {
			parser.parse(dummyRequest.getAction(), data, connection);
		}
		return true;
	}

	public RequestReciever(Connection connection) {
		this.connection = connection;
	}
	
	public void addParser(RequestParser parser) {
		parsers.add(parser);
	}
}
