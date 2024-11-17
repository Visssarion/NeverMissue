package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.connection.Connection;

public abstract class RequestParserTyped implements RequestParser {
	@Override
	public void parse(RequestType action, String data, Connection connection) {
		if(isCorrectAction(action)) {
			run(data, connection);
		}
	}

	abstract protected void run(String data, Connection connection);
	abstract protected boolean isCorrectAction(RequestType action);
}
