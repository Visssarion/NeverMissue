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
	
	public static <E> MessageCommand<E> fromString(String s, Class<?> clazz) {
		MessageCommand<E> temp;
		Type collectionType = TypeToken.getParameterized(MessageCommand.class, clazz).getType();
		temp = new Gson().fromJson(s, collectionType);
		return temp;
	}
	
	public static <E> MessageCommand<E> fromReader(Reader r, Class<?> clazz) {
		MessageCommand<E> temp;
		Type collectionType = TypeToken.getParameterized(MessageCommand.class, clazz).getType();
		temp = new Gson().fromJson(r, collectionType);
		return temp;
	}
	
	
	
}
