package dev.vissa.nevermissue.server;

import java.io.IOException;
import java.net.ServerSocket;

import dev.vissa.nevermissue.server.database.Database;
import dev.vissa.nevermissue.server.threads.MainThread;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	ServerSocket serverSocket;
    	try {
			serverSocket = new ServerSocket(12888);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
    	Database.startup();
    	MainThread mainThread = new MainThread(serverSocket);
    	while(true) {
    		mainThread.update();
    	}
    }
}
