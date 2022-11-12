package com.dcu.wfd.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class DataStorage {
	// 크롤링한 데이터 담을 변수 선언.
	
	// 네이버 최신뉴스 크롤링데이터 담을 변수.
	private static ArrayList<HashMap<String, String>> naverSportsNewsLatestData = null;
	// 네이버 최신뉴스 크롤링한 시간 담을 변수.
	private static Date naverSportsNewsLatestCrawlingTime = null;
	
	// 오늘의 해외축구 전체 경기 일정 크롤링데이터 담을 변수.
	private static ArrayList<HashMap<String, String>> todayMatchScheduleData = null;
	// 오늘 경기 일정 크롤링한 시간 담을 변수.
	private static Date todayMatchScheduleCrawlingTime = null;
	
	//  프리미어리그 오늘 경기 일정 크롤링데이터 담을 변수.
	private static  ArrayList<HashMap<String, String>> todayEplMatchData = null;
	// 프리미어리그 오늘 경기 일정 크롤링한 시간 담을 변수.
	private static Date todayEplMatchCrawlingTime = null;
	
	// 프리미어리그 전체 팀 순위 크롤링데이터 담을 변수.
	private static ArrayList<HashMap<String, String>> eplTeamRankData = null;
	// 팀순위 크롤링한 시간 담을 변수.
	private static Date eplTeamRankCrawlingTime = null;
	
	// 프리미어리그 선수 개인 순위 크롤링데이터 담을 변수.
	private static ArrayList<HashMap<String, String>> eplPlayerRankData = null;
	// 개인순위 크롤링한 시간 담을 변수.
	private static Date eplPlayerRankCrawlingTime = null;
	
	// 프리미어리그 각 팀 내 모든선수 크롤링데이터 담을 변수.
	private static ArrayList<HashMap<String, String>> eplInnerPlayerRankData = null;
	// 팀 내 모든선수 크롤링한 시간 담을 변수.
	private static Date eplInnerPlayerRankCrawlingTime = null;
	
	// 프리미어리그 경기 상세정보 크롤링 데이터 담을 변수.
	private static ArrayList<HashMap<String, String>> eplMatchDetailData = null;
	// 상세정보 크롤링한 시간 담을 변수.
	private static Date eplMatchDetailCrawlingTime = null;
	
	public static ArrayList<HashMap<String, String>> getNaverSportsNewsLatestData() {
		return naverSportsNewsLatestData;
	}
	public static void setNaverSportsNewsLatestData(ArrayList<HashMap<String, String>> naverSportsNewsLatestData) {
		DataStorage.naverSportsNewsLatestData = naverSportsNewsLatestData;
	}
	public static Date getNaverSportsNewsLatestCrawlingTime() {
		return naverSportsNewsLatestCrawlingTime;
	}
	public static void setNaverSportsNewsLatestCrawlingTime(Date naverSportsNewsLatestCrawlingTime) {
		DataStorage.naverSportsNewsLatestCrawlingTime = naverSportsNewsLatestCrawlingTime;
	}
	public static ArrayList<HashMap<String, String>> getTodayMatchScheduleData() {
		return todayMatchScheduleData;
	}
	public static void setTodayMatchScheduleData(ArrayList<HashMap<String, String>> todayMatchScheduleData) {
		DataStorage.todayMatchScheduleData = todayMatchScheduleData;
	}
	public static Date getTodayMatchScheduleCrawlingTime() {
		return todayMatchScheduleCrawlingTime;
	}
	public static void setTodayMatchScheduleCrawlingTime(Date todayMatchScheduleCrawlingTime) {
		DataStorage.todayMatchScheduleCrawlingTime = todayMatchScheduleCrawlingTime;
	}
	public static ArrayList<HashMap<String, String>> getTodayEplMatchData() {
		return todayEplMatchData;
	}
	public static void setTodayEplMatchData(ArrayList<HashMap<String, String>> todayEplMatchData) {
		DataStorage.todayEplMatchData = todayEplMatchData;
	}
	public static Date getTodayEplMatchCrawlingTime() {
		return todayEplMatchCrawlingTime;
	}
	public static void setTodayEplMatchCrawlingTime(Date todayEplMatchCrawlingTime) {
		DataStorage.todayEplMatchCrawlingTime = todayEplMatchCrawlingTime;
	}
	public static ArrayList<HashMap<String, String>> getEplTeamRankData() {
		return eplTeamRankData;
	}
	public static void setEplTeamRankData(ArrayList<HashMap<String, String>> eplTeamRankData) {
		DataStorage.eplTeamRankData = eplTeamRankData;
	}
	public static Date getEplTeamRankCrawlingTime() {
		return eplTeamRankCrawlingTime;
	}
	public static void setEplTeamRankCrawlingTime(Date eplTeamRankCrawlingTime) {
		DataStorage.eplTeamRankCrawlingTime = eplTeamRankCrawlingTime;
	}
	public static ArrayList<HashMap<String, String>> getEplPlayerRankData() {
		return eplPlayerRankData;
	}
	public static void setEplPlayerRankData(ArrayList<HashMap<String, String>> eplPlayerRankData) {
		DataStorage.eplPlayerRankData = eplPlayerRankData;
	}
	public static Date getEplPlayerRankCrawlingTime() {
		return eplPlayerRankCrawlingTime;
	}
	public static void setEplPlayerRankCrawlingTime(Date eplPlayerRankCrawlingTime) {
		DataStorage.eplPlayerRankCrawlingTime = eplPlayerRankCrawlingTime;
	}
	public static ArrayList<HashMap<String, String>> getEplInnerPlayerRankData() {
		return eplInnerPlayerRankData;
	}
	public static void setEplInnerPlayerRankData(ArrayList<HashMap<String, String>> eplInnerPlayerRankData) {
		DataStorage.eplInnerPlayerRankData = eplInnerPlayerRankData;
	}
	public static Date getEplInnerPlayerRankCrawlingTime() {
		return eplInnerPlayerRankCrawlingTime;
	}
	public static void setEplInnerPlayerRankCrawlingTime(Date eplInnerPlayerRankCrawlingTime) {
		DataStorage.eplInnerPlayerRankCrawlingTime = eplInnerPlayerRankCrawlingTime;
	}
	public static ArrayList<HashMap<String, String>> getEplMatchDetailData() {
		return eplMatchDetailData;
	}
	public static void setEplMatchDetailData(ArrayList<HashMap<String, String>> eplMatchDetailData) {
		DataStorage.eplMatchDetailData = eplMatchDetailData;
	}
	public static Date getEplMatchDetailCrawlingTime() {
		return eplMatchDetailCrawlingTime;
	}
	public static void setEplMatchDetailCrawlingTime(Date eplMatchDetailCrawlingTime) {
		DataStorage.eplMatchDetailCrawlingTime = eplMatchDetailCrawlingTime;
	}
	
	
	
}
