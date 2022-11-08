package com.dcu.wfd.test;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	public static void main(String[] args) {
//		url 집어넣으면 됨 
		Connection connection= Jsoup.connect("https://sports.daum.net/game/80060309/cast");
		// jsoup VO객체(document, element, elements)
		try {
			Document doc = connection.get();
//			Elements el = doc.select("#content > div > div.today_section.type_no_da > ul > li:nth-child(1) > a > div.text_area > strong");
//			System.out.println(el.size());
			// 1 
//			Element el = doc.selectFirst("#content > div > div.today_section.type_no_da > ul > li:nth-child(1) > a > div.text_area > strong");
//			System.out.println(el.text());
			
//			Elements els = doc.select("#content > div > div.today_section.type_no_da > ul > li");
//			System.out.println(els.size());
			
//			for(int i = 1; i <= els.size(); i++) {
//				Element elsItem = doc.selectFirst("#content > div > div.today_section.type_no_da > ul > li:nth-child(" + i + ") > a > div.text_area > strong");
//				System.out.println(elsItem.text());
//			}
			
			System.out.println(doc);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
