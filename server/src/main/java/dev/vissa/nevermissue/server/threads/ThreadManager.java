package dev.vissa.nevermissue.server.threads;

import java.util.ArrayList;
import java.util.List;

public class ThreadManager {
	private List<ConnectionThread> threads = new ArrayList<ConnectionThread>();
	private List<ConnectionThread> threadsQueuedForDeletion = new ArrayList<ConnectionThread>();

	
	public void add(ConnectionThread thread) {
		threads.add(thread);
	}
	
	public void check() {
		for(ConnectionThread thread: threads) {
			if(thread.isClosed()) {
				thread.interrupt();
				threadsQueuedForDeletion.add(thread);
			}
		}
		for(ConnectionThread thread: threadsQueuedForDeletion) {
			threads.remove(thread);
			System.out.println("Thread disconnected");
		}
		threadsQueuedForDeletion.clear();
	}
	
}
