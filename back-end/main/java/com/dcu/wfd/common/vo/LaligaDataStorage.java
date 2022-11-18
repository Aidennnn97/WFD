package com.dcu.wfd.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class LaligaDataStorage {

   //  프리미어리그 오늘 경기 일정 크롤링데이터 담을 변수.
   private static  ArrayList<HashMap<String, String>> todayLaligaMatchData = null;
   // 프리미어리그 오늘 경기 일정 크롤링한 시간 담을 변수.
   private static Date todayLaligaMatchCrawlingTime = null;
   
   // 프리미어리그 전체 팀 순위 크롤링데이터 담을 변수.
   private static ArrayList<HashMap<String, String>> LaligaTeamRankData = null;
   // 팀순위 크롤링한 시간 담을 변수.
   private static Date LaligaTeamRankCrawlingTime = null;
   
   // 프리미어리그 선수 개인 순위 크롤링데이터 담을 변수.
   private static ArrayList<HashMap<String, String>> LaligaPlayerRankData = null;
   // 개인순위 크롤링한 시간 담을 변수.
   private static Date LaligaPlayerRankCrawlingTime = null;
   
   // 프리미어리그 각 팀 내 모든선수 크롤링데이터 담을 변수.
   private static ArrayList<HashMap<String, String>> LaligaInnerPlayerRankData = null;
   // 팀 내 모든선수 크롤링한 시간 담을 변수.
   private static Date LaligaInnerPlayerRankCrawlingTime = null;
   
   // 프리미어리그 경기 상세정보 크롤링 데이터 담을 변수.
   private static ArrayList<HashMap<String, String>> LaligaMatchDetailData = null;
   // 상세정보 크롤링한 시간 담을 변수.
   private static Date LaligaMatchDetailCrawlingTime = null;
   
   // 프리미어리그 선수 개인 어시스트 순위 크롤링데이터 담을 변수.
   private static ArrayList<HashMap<String, String>> LaligaPlayerAsistRankData = null;
   // 개인 어시스트 순위 크롤링한 시간 담을 변수.
   private static Date LaligaPlayerAsistRankCrawlingTime = null;
   public static ArrayList<HashMap<String, String>> getTodayLaligaMatchData() {
      return todayLaligaMatchData;
   }
   public static void setTodayLaligaMatchData(ArrayList<HashMap<String, String>> todayLaligaMatchData) {
      LaligaDataStorage.todayLaligaMatchData = todayLaligaMatchData;
   }
   public static Date getTodayLaligaMatchCrawlingTime() {
      return todayLaligaMatchCrawlingTime;
   }
   public static void setTodayLaligaMatchCrawlingTime(Date todayLaligaMatchCrawlingTime) {
      LaligaDataStorage.todayLaligaMatchCrawlingTime = todayLaligaMatchCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getLaligaTeamRankData() {
      return LaligaTeamRankData;
   }
   public static void setLaligaTeamRankData(ArrayList<HashMap<String, String>> laligaTeamRankData) {
      LaligaTeamRankData = laligaTeamRankData;
   }
   public static Date getLaligaTeamRankCrawlingTime() {
      return LaligaTeamRankCrawlingTime;
   }
   public static void setLaligaTeamRankCrawlingTime(Date laligaTeamRankCrawlingTime) {
      LaligaTeamRankCrawlingTime = laligaTeamRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getLaligaPlayerRankData() {
      return LaligaPlayerRankData;
   }
   public static void setLaligaPlayerRankData(ArrayList<HashMap<String, String>> laligaPlayerRankData) {
      LaligaPlayerRankData = laligaPlayerRankData;
   }
   public static Date getLaligaPlayerRankCrawlingTime() {
      return LaligaPlayerRankCrawlingTime;
   }
   public static void setLaligaPlayerRankCrawlingTime(Date laligaPlayerRankCrawlingTime) {
      LaligaPlayerRankCrawlingTime = laligaPlayerRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getLaligaInnerPlayerRankData() {
      return LaligaInnerPlayerRankData;
   }
   public static void setLaligaInnerPlayerRankData(ArrayList<HashMap<String, String>> laligaInnerPlayerRankData) {
      LaligaInnerPlayerRankData = laligaInnerPlayerRankData;
   }
   public static Date getLaligaInnerPlayerRankCrawlingTime() {
      return LaligaInnerPlayerRankCrawlingTime;
   }
   public static void setLaligaInnerPlayerRankCrawlingTime(Date laligaInnerPlayerRankCrawlingTime) {
      LaligaInnerPlayerRankCrawlingTime = laligaInnerPlayerRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getLaligaMatchDetailData() {
      return LaligaMatchDetailData;
   }
   public static void setLaligaMatchDetailData(ArrayList<HashMap<String, String>> laligaMatchDetailData) {
      LaligaMatchDetailData = laligaMatchDetailData;
   }
   public static Date getLaligaMatchDetailCrawlingTime() {
      return LaligaMatchDetailCrawlingTime;
   }
   public static void setLaligaMatchDetailCrawlingTime(Date laligaMatchDetailCrawlingTime) {
      LaligaMatchDetailCrawlingTime = laligaMatchDetailCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getLaligaPlayerAsistRankData() {
      return LaligaPlayerAsistRankData;
   }
   public static void setLaligaPlayerAsistRankData(ArrayList<HashMap<String, String>> laligaPlayerAsistRankData) {
      LaligaPlayerAsistRankData = laligaPlayerAsistRankData;
   }
   public static Date getLaligaPlayerAsistRankCrawlingTime() {
      return LaligaPlayerAsistRankCrawlingTime;
   }
   public static void setLaligaPlayerAsistRankCrawlingTime(Date laligaPlayerAsistRankCrawlingTime) {
      LaligaPlayerAsistRankCrawlingTime = laligaPlayerAsistRankCrawlingTime;
   }
}