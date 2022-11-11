package com.dcu.wfd.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DataStorage {
	
	private static ArrayList<HashMap<String, String>> naverSportsNewsData = null;
	
	private static Date naverSportsNewsCrawlingTime = null;
	
	private static ArrayList<HashMap<String, String>> todayMatchData = null;
	
	private static Date todayMatchCrawlingTime = null;

	
	public static ArrayList<HashMap<String, String>> getNaverSportsNewsData() {
		return naverSportsNewsData;
	}

	public static void setNaverSportsNewsData(ArrayList<HashMap<String, String>> naverSportsNewsData) {
		DataStorage.naverSportsNewsData = naverSportsNewsData;
	}

	public static Date getNaverSportsNewsCrawlingTime() {
		return naverSportsNewsCrawlingTime;
	}

	public static void setNaverSportsNewsCrawlingTime(Date naverSportsNewsCrawlingTime) {
		DataStorage.naverSportsNewsCrawlingTime = naverSportsNewsCrawlingTime;
	}

	public static ArrayList<HashMap<String, String>> getTodayMatchData() {
		return todayMatchData;
	}

	public static void setTodayMatchData(ArrayList<HashMap<String, String>> todayMatchData) {
		DataStorage.todayMatchData = todayMatchData;
	}

	public static Date getTodayMatchCrawlingTime() {
		return todayMatchCrawlingTime;
	}

	public static void setTodayMatchCrawlingTime(Date todayMatchCrawlingTime) {
		DataStorage.todayMatchCrawlingTime = todayMatchCrawlingTime;
	}
	
	

	
	
}
