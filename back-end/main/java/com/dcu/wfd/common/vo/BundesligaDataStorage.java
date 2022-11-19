package com.dcu.wfd.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class BundesligaDataStorage {

      //  프리미어리그 오늘 경기 일정 크롤링데이터 담을 변수.
      private static  ArrayList<HashMap<String, String>> todayBundesligaMatchData = null;
      // 프리미어리그 오늘 경기 일정 크롤링한 시간 담을 변수.
      private static Date todayBundesligaMatchCrawlingTime = null;
      
      // 프리미어리그 전체 팀 순위 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> BundesligaTeamRankData = null;
      // 팀순위 크롤링한 시간 담을 변수.
      private static Date BundesligaTeamRankCrawlingTime = null;
      
      // 프리미어리그 선수 개인 순위 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> BundesligaPlayerRankData = null;
      // 개인순위 크롤링한 시간 담을 변수.
      private static Date BundesligaPlayerRankCrawlingTime = null;
      
      // 프리미어리그 각 팀 내 모든선수 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> BundesligaInnerPlayerRankData = null;
      // 팀 내 모든선수 크롤링한 시간 담을 변수.
      private static Date BundesligaInnerPlayerRankCrawlingTime = null;
      
      // 프리미어리그 경기 상세정보 크롤링 데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> BundesligaMatchDetailData = null;
      // 상세정보 크롤링한 시간 담을 변수.
      private static Date BundesligaMatchDetailCrawlingTime = null;
      
      // 프리미어리그 선수 개인 어시스트 순위 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> BundesligaPlayerAsistRankData = null;
      // 개인 어시스트 순위 크롤링한 시간 담을 변수.
      private static Date BundesligaPlayerAsistRankCrawlingTime = null;
   public static ArrayList<HashMap<String, String>> getTodayBundesligaMatchData() {
      return todayBundesligaMatchData;
   }
   public static void setTodayBundesligaMatchData(ArrayList<HashMap<String, String>> todayBundesligaMatchData) {
      BundesligaDataStorage.todayBundesligaMatchData = todayBundesligaMatchData;
   }
   public static Date getTodayBundesligaMatchCrawlingTime() {
      return todayBundesligaMatchCrawlingTime;
   }
   public static void setTodayBundesligaMatchCrawlingTime(Date todayBundesligaMatchCrawlingTime) {
      BundesligaDataStorage.todayBundesligaMatchCrawlingTime = todayBundesligaMatchCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getBundesligaTeamRankData() {
      return BundesligaTeamRankData;
   }
   public static void setBundesligaTeamRankData(ArrayList<HashMap<String, String>> bundesligaTeamRankData) {
      BundesligaTeamRankData = bundesligaTeamRankData;
   }
   public static Date getBundesligaTeamRankCrawlingTime() {
      return BundesligaTeamRankCrawlingTime;
   }
   public static void setBundesligaTeamRankCrawlingTime(Date bundesligaTeamRankCrawlingTime) {
      BundesligaTeamRankCrawlingTime = bundesligaTeamRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getBundesligaPlayerRankData() {
      return BundesligaPlayerRankData;
   }
   public static void setBundesligaPlayerRankData(ArrayList<HashMap<String, String>> bundesligaPlayerRankData) {
      BundesligaPlayerRankData = bundesligaPlayerRankData;
   }
   public static Date getBundesligaPlayerRankCrawlingTime() {
      return BundesligaPlayerRankCrawlingTime;
   }
   public static void setBundesligaPlayerRankCrawlingTime(Date bundesligaPlayerRankCrawlingTime) {
      BundesligaPlayerRankCrawlingTime = bundesligaPlayerRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getBundesligaInnerPlayerRankData() {
      return BundesligaInnerPlayerRankData;
   }
   public static void setBundesligaInnerPlayerRankData(ArrayList<HashMap<String, String>> bundesligaInnerPlayerRankData) {
      BundesligaInnerPlayerRankData = bundesligaInnerPlayerRankData;
   }
   public static Date getBundesligaInnerPlayerRankCrawlingTime() {
      return BundesligaInnerPlayerRankCrawlingTime;
   }
   public static void setBundesligaInnerPlayerRankCrawlingTime(Date bundesligaInnerPlayerRankCrawlingTime) {
      BundesligaInnerPlayerRankCrawlingTime = bundesligaInnerPlayerRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getBundesligaMatchDetailData() {
      return BundesligaMatchDetailData;
   }
   public static void setBundesligaMatchDetailData(ArrayList<HashMap<String, String>> bundesligaMatchDetailData) {
      BundesligaMatchDetailData = bundesligaMatchDetailData;
   }
   public static Date getBundesligaMatchDetailCrawlingTime() {
      return BundesligaMatchDetailCrawlingTime;
   }
   public static void setBundesligaMatchDetailCrawlingTime(Date bundesligaMatchDetailCrawlingTime) {
      BundesligaMatchDetailCrawlingTime = bundesligaMatchDetailCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getBundesligaPlayerAsistRankData() {
      return BundesligaPlayerAsistRankData;
   }
   public static void setBundesligaPlayerAsistRankData(ArrayList<HashMap<String, String>> bundesligaPlayerAsistRankData) {
      BundesligaPlayerAsistRankData = bundesligaPlayerAsistRankData;
   }
   public static Date getBundesligaPlayerAsistRankCrawlingTime() {
      return BundesligaPlayerAsistRankCrawlingTime;
   }
   public static void setBundesligaPlayerAsistRankCrawlingTime(Date bundesligaPlayerAsistRankCrawlingTime) {
      BundesligaPlayerAsistRankCrawlingTime = bundesligaPlayerAsistRankCrawlingTime;
   }
}