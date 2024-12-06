package dev.vissa.nevermissue.client.connection;

import java.io.IOException;
import java.net.Socket;

import dev.vissa.nevermissue.shared.connection.Connection;
import dev.vissa.nevermissue.shared.connection.Session;

public class RuntimeSession {
	
	private static Session session;

	public static Session getSession() {
		return session;
	}
	
	public static void createSession(Socket socket) {
		try {
			if (session != null) {
				session.close();
			}
			session = new Session(new Connection(socket));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeSession() {
		try {
			if (session != null) {
				session.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
