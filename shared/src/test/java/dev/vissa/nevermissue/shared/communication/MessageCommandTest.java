package dev.vissa.nevermissue.shared.communication;

import java.lang.reflect.Type;

import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MessageCommandTest {
	@Test
	public void test() {
		MessageCommand<TestClass> m1 = new MessageCommand<TestClass>("test", new TestClass("test"));
		System.out.println(m1.toString());
		MessageCommand<TestClass> m2 = new MessageCommand<TestClass>();
		Type collectionType = new TypeToken<MessageCommand<TestClass>>(){}.getType();
		m2 = new Gson().fromJson(m1.toString(), collectionType);

		//m2.fromString(m1.toString());
		//MessageCommand<TestClass> m2 = MessageCommand.fromString(m1.toString());
		System.out.println(m2.toString());
		System.out.println(m2.getArgument().getClass());
		
	}
	
	public class TestClass{
		public String name;

		public TestClass(String name) {
			super();
			this.name = name;
		}
		
		
	}
}
