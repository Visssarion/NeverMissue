package dev.vissa.nevermissue.client;

import java.io.IOException;
import java.net.Socket;

import org.junit.jupiter.api.Test;

public class AppTest {
	@Test
	public void test() {
		try {
			Socket socket = new Socket("127.0.0.1",12888);
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
