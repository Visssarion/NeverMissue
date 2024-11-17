package dev.vissa.nevermissue.shared.communication;

import dev.vissa.nevermissue.shared.connection.Connection;

public interface RequestParser {
	public void parse(Request.RequestType action, String data, Connection connection);
}
