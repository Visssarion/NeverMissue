package dev.vissa.nevermissue.shared.communication;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessageCommand<E> {
	private String action;
	private E argument;
	
	public MessageCommand(String action, E argument) {
		this.action = action;
		this.argument = argument;
	}
	
	public MessageCommand() {
	}

	public String getAction() {
		return action;
	}

	public E getArgument() {
		return argument;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}
	
	public void fromString(String s) {
		MessageCommand<E> temp;
		Type collectionType = new TypeToken<MessageCommand<E>>(){}.getType();
		temp = new Gson().fromJson(s, collectionType);
		this.action = temp.action;
		this.argument = temp.argument;
	}
	
//	public static <E> MessageCommand<E> fromString(String s) {
//		return new Gson().fromJson(s, MessageCommand.class);
//	}
//	
//	public static <E> MessageCommand<E> fromReader(Reader r) {
//		return new Gson().fromJson(r, MessageCommand.class);
//	}
	
	
	
}
