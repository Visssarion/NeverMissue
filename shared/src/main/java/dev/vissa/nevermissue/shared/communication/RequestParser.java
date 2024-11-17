package dev.vissa.nevermissue.shared.communication;

public interface RequestParser {
	public void parse(Request.RequestType action, String data);
}
