package dev.vissa.nevermissue.server.threads;

import java.net.ServerSocket;

import dev.vissa.nevermissue.server.connection.ConnectionAccepter;
import dev.vissa.nevermissue.shared.connection.Connection;

public class MainThread {
	private ThreadManager threadManager = new ThreadManager();
	private ConnectionAccepter connectionAccepter;
	
	
	public void update() {
		threadManager.check();
		Connection connection = connectionAccepter.accept();
		ConnectionThread connectionThread = new ConnectionThread(connection);
		threadManager.add(connectionThread);
		connectionThread.start();
	}


	public MainThread(ServerSocket serverSocket) {
		this.connectionAccepter = new ConnectionAccepter(serverSocket);
	}
	
	
}
