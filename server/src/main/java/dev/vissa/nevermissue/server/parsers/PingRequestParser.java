package dev.vissa.nevermissue.server.parsers;

import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.connection.Connection;
import dev.vissa.nevermissue.shared.communication.RequestParserTyped;

public class PingRequestParser extends RequestParserTyped {

	@Override
	protected void run(String data, Connection connection) {
		Request<String> request = Request.fromString(data, String.class);
		Request<String> repeatRequest = new Request<String>(RequestType.RESULT, request.getArgument());
		connection.send(repeatRequest.toString());
	}

	@Override
	protected boolean isCorrectAction(RequestType action) {
		return action == RequestType.PING;
	}

}
