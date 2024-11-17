package dev.vissa.nevermissue.shared.communication;


import java.io.IOException;
import java.util.List;

import com.google.gson.Gson;

import dev.vissa.nevermissue.shared.connection.Connection;

public class RequestReciever {
	private Connection connection;
	private Gson gson;
	
	private List<RequestParser> parsers;
	
	public void recieve() throws IOException {
		String data = connection.recieve();
		Request<?> dummyRequest = gson.fromJson(data, Request.class);
		for(RequestParser parser: parsers) {
			parser.parse(dummyRequest.getAction(), data);
		}
	}
}
