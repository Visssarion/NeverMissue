package dev.vissa.nevermissue.shared.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Connection implements SocketState{
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	

	public Connection(Socket socket) throws IOException {
		this.socket = socket;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
		out = new PrintWriter(socket.getOutputStream(), true, StandardCharsets.UTF_8);
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

	public void send(String str) {
		out.println(str);
	}
	
	public String recieve() throws IOException {
		return in.readLine();
	}
	
	public void close() throws IOException {
		out.close();
		in.close();
		socket.close();
	}
}
