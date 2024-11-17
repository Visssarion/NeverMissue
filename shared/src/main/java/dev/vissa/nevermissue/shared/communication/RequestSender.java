package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.connection.Connection;

public class RequestSender {
	private Connection connection;
	
	public void send(Request<?> request) {
		connection.send(request.toString());
	}

	public RequestSender(Connection connection) {
		this.connection = connection;
	}
	
	
}
