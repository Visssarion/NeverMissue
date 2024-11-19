package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.connection.Session;

public abstract class RequestParserTyped implements RequestParser {
	@Override
	public void parse(RequestType action, String data, Session session) {
		if(isCorrectAction(action)) {
			run(data, session);
		}
	}

	abstract protected void run(String data, Session session);
	abstract protected boolean isCorrectAction(RequestType action);
}
