package dev.vissa.nevermissue.shared.connection;

public interface SocketState {
	public boolean isConnected();
	public boolean isClosed();
	public boolean isBound();
}
