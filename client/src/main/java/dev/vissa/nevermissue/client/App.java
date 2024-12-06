package dev.vissa.nevermissue.client;

import java.io.IOException;
import java.net.Socket;

import dev.vissa.nevermissue.client.connection.RuntimeSession;

public class App {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 12888);
			RuntimeSession.createSession(socket);
			System.out.println(RuntimeSession.getSession());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JavaFXApp.main(args);
		
		
        
	}
}
