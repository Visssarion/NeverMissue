package dev.vissa.nevermissue.shared.communication;

import java.io.Reader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import dev.vissa.nevermissue.shared.gson.BidirectionalExclusionStrategy;

public class Request<E> {
	public static final Gson gson = new GsonBuilder().setExclusionStrategies(new BidirectionalExclusionStrategy()).create();
	
	private RequestType action;
	private E argument;
	
	public Request(RequestType action, E argument) {
		this.action = action;
		this.argument = argument;
	}
	
	public Request() {
	}

	public RequestType getAction() {
		return action;
	}

	public String getActionString() {
		return action.toString();
	}
	
	public E getArgument() {
		return argument;
	}

	@Override
	public String toString() {
		return gson.toJson(this);
	}
	
	public static <E> Request<E> fromString(String s, Class<E> clazz) {
		Request<E> temp;
		Type collectionType = TypeToken.getParameterized(Request.class, clazz).getType();
		temp = new Gson().fromJson(s, collectionType);
		return temp;
	}
	
	public static <E> Request<E> fromReader(Reader r, Class<E> clazz) {
		Request<E> temp;
		Type collectionType = TypeToken.getParameterized(Request.class, clazz).getType();
		temp = new Gson().fromJson(r, collectionType);
		return temp;
	}
	
	public enum RequestType{
		PING,
		AUTHORIZE,
		REGISTER
	}
	
}
