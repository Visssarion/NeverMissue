package dev.vissa.nevermissue.shared.communication;

import org.junit.jupiter.api.Test;

public class StaticInheritanceTest {

	@Test
	public void test() {
		new Child1().test();
		new Child2().test();
		
	}
	
	class Parent{
		static int lol = 0;
		void test() {lol++; System.out.println(lol);};
	}
	
	class Child1 extends Parent{
		static int lol = 0;
		
	}
	
	class Child2 extends Parent{
		static int lol = 0;
	}
	
	
}
