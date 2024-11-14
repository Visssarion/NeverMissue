package dev.vissa.nevermissue.shared.communication;

import org.junit.jupiter.api.Test;

public class MessageCommandTest {
	@Test
	public void test() {
		MessageCommand<TestClass> m1 = new MessageCommand<TestClass>("test", new TestClass("test"));
		System.out.println(m1.toString());
		MessageCommand<TestClass> m2 = MessageCommand.fromString(m1.toString(), TestClass.class);
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
