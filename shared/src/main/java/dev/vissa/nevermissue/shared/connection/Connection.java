package dev.vissa.nevermissue.shared.connection;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection implements SocketState{
	private Socket socket;
	private InputStreamReader isr;
	private OutputStreamWriter osw;
	

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		isr = new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8);
		osw = new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8);
	}


	public Socket getSocket() {
		return socket;
	}
	
	@Override
	public boolean isConnected() {
		return socket.isConnected();
	}
	
	@Override
	public boolean isClosed() {
		return socket.isClosed();
	}
	
	@Override
	public boolean isBound() {
		return socket.isBound();
	}


	public InputStreamReader getIsr() {
		return isr;
	}


	public OutputStreamWriter getOsw() {
		return osw;
	}
	
}
