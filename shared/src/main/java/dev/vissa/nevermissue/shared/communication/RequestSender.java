package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.connection.Connection;

/**Class that was made but wasn't used. Use connection.send(request.toString()) instead
*
*
*/
@Deprecated(forRemoval = true) 
public class RequestSender {
	private Connection connection;
	
	public void send(Request<?> request) {
		connection.send(request.toString());
	}

	public RequestSender(Connection connection) {
		this.connection = connection;
	}
	
	
}
