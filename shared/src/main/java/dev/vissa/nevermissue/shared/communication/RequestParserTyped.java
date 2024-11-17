package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.communication.Request.RequestType;

public abstract class RequestParserTyped implements RequestParser {
	@Override
	public void parse(RequestType action, String data) {
		if(isCorrectAction(action)) {
			run(data);
		}
	}

	abstract protected void run(String data);
	abstract protected boolean isCorrectAction(RequestType action);
}
