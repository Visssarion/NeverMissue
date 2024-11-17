package dev.vissa.nevermissue.server.threads;

import java.io.IOException;

import dev.vissa.nevermissue.server.parsers.PingRequestParser;
import dev.vissa.nevermissue.shared.communication.RequestReciever;
import dev.vissa.nevermissue.shared.connection.Connection;

public class ConnectionRunnable implements Runnable {

	private Connection connection;
	
	@Override
	public void run() {
		RequestReciever requestReciever = new RequestReciever(connection);
		requestReciever.addParser(new PingRequestParser());
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

	
	
}
