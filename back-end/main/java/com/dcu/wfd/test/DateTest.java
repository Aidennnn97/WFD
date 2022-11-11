package com.dcu.wfd.test;

import java.util.Date;

public class DateTest {
	public static void main (String args[]) throws InterruptedException {
		Date date = new Date();
		Thread.sleep(5000);
		Date date2 = new Date();
		long sec = (date.getTime() - date2.getTime()) / 1000; // 초
		
		System.out.print(sec);
	}
}
