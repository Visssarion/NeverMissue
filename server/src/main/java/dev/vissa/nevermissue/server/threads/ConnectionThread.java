package dev.vissa.nevermissue.server.threads;

import dev.vissa.nevermissue.shared.connection.Connection;
import dev.vissa.nevermissue.shared.connection.SocketState;

public class ConnectionThread extends Thread implements SocketState {
	private Connection connection;

	public ConnectionThread(Connection connection) {
		super(new ConnectionRunnable(connection));
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
	
	
}
