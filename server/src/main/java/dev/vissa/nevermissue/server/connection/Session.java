package dev.vissa.nevermissue.server.connection;

import java.io.IOException;

import dev.vissa.nevermissue.shared.connection.Connection;
import dev.vissa.nevermissue.shared.connection.SocketState;
import dev.vissa.nevermissue.shared.entities.User;

public class Session implements SocketState{
	private Connection connection;
	private User user;
	
	public Session(Connection connection) {
		this.connection = connection;
	}

	@Override
	public boolean isConnected() {
		return connection.isConnected();
	}

	@Override
	public boolean isClosed() {
		return connection.isClosed();
	}

	@Override
	public boolean isBound() {
		return connection.isBound();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Connection getConnection() {
		return connection;
	}
	
	public void close() throws IOException {
		connection.close();
	}
	
}
