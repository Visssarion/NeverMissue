package dev.vissa.nevermissue.shared.communication;

import org.junit.jupiter.api.Test;

public class MessageCommandTest {
	@Test
	public void test() {
		Request<TestClass> m1 = new Request<TestClass>(Request.RequestType.PING, new TestClass("test"));
		System.out.println(m1.toString());
		Request<TestClass> m2 = Request.fromString(m1.toString(), TestClass.class);
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
