package dev.vissa.nevermissue.server.threads;

import dev.vissa.nevermissue.shared.connection.Connection;

public class ConnectionRunnable implements Runnable {

	private Connection connection;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public ConnectionRunnable(Connection connection) {
		this.connection = connection;
	}

	
	
}
