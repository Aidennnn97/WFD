package com.dcu.wfd.test;

public class Test {
	public static void main(String[] args) {
		
		TestClass testClass = new TestClass();
		int result = testClass.sum(1, 1);
		System.out.println("result : " + result);
		
		int result2 = testClass.sum(2, 3);
		System.out.println("result2 : " + result2);
		
	}
}
