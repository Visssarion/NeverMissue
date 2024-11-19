package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.connection.Session;

public interface RequestParser {
	public void parse(Request.RequestType action, String data, Session session);
}
