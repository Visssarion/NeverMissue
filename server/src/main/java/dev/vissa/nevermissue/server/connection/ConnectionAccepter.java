package dev.vissa.nevermissue.server.connection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import dev.vissa.nevermissue.shared.connection.Connection;

public class ConnectionAccepter implements ConnectionFactory{

	ServerSocket serverSocket;
	
	@Override
	public Connection accept() {
		Socket socket;
		Connection connection = null;
		try {
			socket = serverSocket.accept();
			connection = new Connection(socket);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return connection;
	}

	public ConnectionAccepter(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	
	
}
