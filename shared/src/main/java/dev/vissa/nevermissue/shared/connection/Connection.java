package dev.vissa.nevermissue.shared.connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection {
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		ois = new ObjectInputStream(socket.getInputStream());
		oos = new ObjectOutputStream(socket.getOutputStream());
	}


	public Socket getSocket() {
		return socket;
	}


	public ObjectInputStream getObjectInputStream() {
		return ois;
	}


	public ObjectOutputStream getObjectOutputStream() {
		return oos;
	}
	
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	public boolean isClosed() {
		return socket.isClosed();
	}
	
	public boolean isBound() {
		return socket.isBound();
	}
	
	
	
	
}
