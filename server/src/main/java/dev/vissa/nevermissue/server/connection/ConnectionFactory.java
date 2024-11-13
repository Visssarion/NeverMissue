package dev.vissa.nevermissue.server.connection;

import dev.vissa.nevermissue.shared.connection.Connection;

public interface ConnectionFactory {
	public Connection accept();
}
