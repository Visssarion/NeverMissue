package dev.vissa.nevermissue.server.threads;

import java.io.IOException;

import dev.vissa.nevermissue.server.parsers.AuthorizationRequestParser;
import dev.vissa.nevermissue.server.parsers.PingRequestParser;
import dev.vissa.nevermissue.server.parsers.RegisterRequestParser;
import dev.vissa.nevermissue.shared.communication.RequestReciever;
import dev.vissa.nevermissue.shared.connection.Connection;
import dev.vissa.nevermissue.shared.connection.SocketState;

public class ConnectionRunnable implements Runnable, SocketState {

	private Connection connection;
	
	@Override
	public void run() {
		RequestReciever requestReciever = new RequestReciever(connection);
		addParsers(requestReciever);
		boolean continuing = true;
		while(continuing) {
			try {
				continuing = requestReciever.recieve();
			} catch (IOException e) {
				e.printStackTrace();
				continuing = false;
			}
		}
		try {
			connection.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ConnectionRunnable(Connection connection) {
		this.connection = connection;
	}

	private static void addParsers(RequestReciever requestReciever) {
		requestReciever.addParser(new PingRequestParser());
		requestReciever.addParser(new AuthorizationRequestParser());
		requestReciever.addParser(new RegisterRequestParser());
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
