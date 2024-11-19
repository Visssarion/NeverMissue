package dev.vissa.nevermissue.server.parsers;

import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.connection.Session;
import dev.vissa.nevermissue.shared.communication.RequestParserTyped;
import dev.vissa.nevermissue.shared.communication.Response;
import dev.vissa.nevermissue.shared.communication.Response.RespondResult;

public class PingRequestParser extends RequestParserTyped {

	@Override
	protected void run(String data, Session session) {
		Request<String> request = Request.fromString(data, String.class);
		Response<String> repeatRequest = new Response<String>(RespondResult.OK, request.getArgument());
		session.getConnection().send(repeatRequest.toString());
	}

	@Override
	protected boolean isCorrectAction(RequestType action) {
		return action == RequestType.PING;
	}

}
