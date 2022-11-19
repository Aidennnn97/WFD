package com.dcu.wfd.common.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class SerieaDataStorage {
//  프리미어리그 오늘 경기 일정 크롤링데이터 담을 변수.
      private static  ArrayList<HashMap<String, String>> todaySerieaMatchData = null;
      // 프리미어리그 오늘 경기 일정 크롤링한 시간 담을 변수.
      private static Date todaySerieaMatchCrawlingTime = null;
      
      // 프리미어리그 전체 팀 순위 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> SerieaTeamRankData = null;
      // 팀순위 크롤링한 시간 담을 변수.
      private static Date SerieaTeamRankCrawlingTime = null;
      
      // 프리미어리그 선수 개인 순위 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> SerieaPlayerRankData = null;
      // 개인순위 크롤링한 시간 담을 변수.
      private static Date SerieaPlayerRankCrawlingTime = null;
      
      // 프리미어리그 각 팀 내 모든선수 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> SerieaInnerPlayerRankData = null;
      // 팀 내 모든선수 크롤링한 시간 담을 변수.
      private static Date SerieaInnerPlayerRankCrawlingTime = null;
      
      // 프리미어리그 경기 상세정보 크롤링 데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> SerieaMatchDetailData = null;
      // 상세정보 크롤링한 시간 담을 변수.
      private static Date SerieaMatchDetailCrawlingTime = null;
      
      // 프리미어리그 선수 개인 어시스트 순위 크롤링데이터 담을 변수.
      private static ArrayList<HashMap<String, String>> SerieaPlayerAsistRankData = null;
      // 개인 어시스트 순위 크롤링한 시간 담을 변수.
      private static Date SerieaPlayerAsistRankCrawlingTime = null;
   public static ArrayList<HashMap<String, String>> getTodaySerieaMatchData() {
      return todaySerieaMatchData;
   }
   public static void setTodaySerieaMatchData(ArrayList<HashMap<String, String>> todaySerieaMatchData) {
      SerieaDataStorage.todaySerieaMatchData = todaySerieaMatchData;
   }
   public static Date getTodaySerieaMatchCrawlingTime() {
      return todaySerieaMatchCrawlingTime;
   }
   public static void setTodaySerieaMatchCrawlingTime(Date todaySerieaMatchCrawlingTime) {
      SerieaDataStorage.todaySerieaMatchCrawlingTime = todaySerieaMatchCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getSerieaTeamRankData() {
      return SerieaTeamRankData;
   }
   public static void setSerieaTeamRankData(ArrayList<HashMap<String, String>> serieaTeamRankData) {
      SerieaTeamRankData = serieaTeamRankData;
   }
   public static Date getSerieaTeamRankCrawlingTime() {
      return SerieaTeamRankCrawlingTime;
   }
   public static void setSerieaTeamRankCrawlingTime(Date serieaTeamRankCrawlingTime) {
      SerieaTeamRankCrawlingTime = serieaTeamRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getSerieaPlayerRankData() {
      return SerieaPlayerRankData;
   }
   public static void setSerieaPlayerRankData(ArrayList<HashMap<String, String>> serieaPlayerRankData) {
      SerieaPlayerRankData = serieaPlayerRankData;
   }
   public static Date getSerieaPlayerRankCrawlingTime() {
      return SerieaPlayerRankCrawlingTime;
   }
   public static void setSerieaPlayerRankCrawlingTime(Date serieaPlayerRankCrawlingTime) {
      SerieaPlayerRankCrawlingTime = serieaPlayerRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getSerieaInnerPlayerRankData() {
      return SerieaInnerPlayerRankData;
   }
   public static void setSerieaInnerPlayerRankData(ArrayList<HashMap<String, String>> serieaInnerPlayerRankData) {
      SerieaInnerPlayerRankData = serieaInnerPlayerRankData;
   }
   public static Date getSerieaInnerPlayerRankCrawlingTime() {
      return SerieaInnerPlayerRankCrawlingTime;
   }
   public static void setSerieaInnerPlayerRankCrawlingTime(Date serieaInnerPlayerRankCrawlingTime) {
      SerieaInnerPlayerRankCrawlingTime = serieaInnerPlayerRankCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getSerieaMatchDetailData() {
      return SerieaMatchDetailData;
   }
   public static void setSerieaMatchDetailData(ArrayList<HashMap<String, String>> serieaMatchDetailData) {
      SerieaMatchDetailData = serieaMatchDetailData;
   }
   public static Date getSerieaMatchDetailCrawlingTime() {
      return SerieaMatchDetailCrawlingTime;
   }
   public static void setSerieaMatchDetailCrawlingTime(Date serieaMatchDetailCrawlingTime) {
      SerieaMatchDetailCrawlingTime = serieaMatchDetailCrawlingTime;
   }
   public static ArrayList<HashMap<String, String>> getSerieaPlayerAsistRankData() {
      return SerieaPlayerAsistRankData;
   }
   public static void setSerieaPlayerAsistRankData(ArrayList<HashMap<String, String>> serieaPlayerAsistRankData) {
      SerieaPlayerAsistRankData = serieaPlayerAsistRankData;
   }
   public static Date getSerieaPlayerAsistRankCrawlingTime() {
      return SerieaPlayerAsistRankCrawlingTime;
   }
   public static void setSerieaPlayerAsistRankCrawlingTime(Date serieaPlayerAsistRankCrawlingTime) {
      SerieaPlayerAsistRankCrawlingTime = serieaPlayerAsistRankCrawlingTime;
   }
}