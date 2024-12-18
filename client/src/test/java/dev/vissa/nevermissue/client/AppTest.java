package dev.vissa.nevermissue.client;

import java.io.IOException;
import java.net.Socket;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import dev.vissa.nevermissue.shared.communication.Request;
import dev.vissa.nevermissue.shared.communication.Request.RequestType;
import dev.vissa.nevermissue.shared.connection.Connection;
import dev.vissa.nevermissue.shared.entities.UserLogin;

public class AppTest {
	@Disabled
	@Test
	public void ping() {
		try {
			Socket socket = new Socket("127.0.0.1",12888);
			Connection connection = new Connection(socket);
			Request<String> request = new Request<String>(RequestType.PING, "ping!");
			connection.send(request.toString());
			System.out.println(connection.recieve());
			connection.send(request.toString());
			System.out.println(connection.recieve());
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void login() {
		try {
			Socket socket = new Socket("127.0.0.1",12888);
			Connection connection = new Connection(socket);
			Request<UserLogin> request = new Request<UserLogin>(RequestType.AUTHORIZE, new UserLogin("testuser", "testpass"));
			connection.send(request.toString());
			System.out.println(connection.recieve());
			connection.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
