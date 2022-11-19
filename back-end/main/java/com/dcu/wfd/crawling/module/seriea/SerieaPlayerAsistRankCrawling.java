package com.dcu.wfd.crawling.module.seriea;

import java.util.ArrayList;
import java.util.HashMap;

import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerieaPlayerAsistRankCrawling {
   public ArrayList<HashMap<String, String>> serieaPlayerAsistRank()throws Exception {
      ArrayList<HashMap<String, String>> trCrawlingDataList2 = new ArrayList<>();
      HttpUtil httpUtil = new HttpUtil();
      String url = "https://sports.daum.net/prx/hermes/api/person/rank.json?leagueCode=seriea&seasonKey=20222023&page=1&pageSize=100&sort=ast";

      try {
         String repBody = httpUtil.httpRequest(url, null);

         ObjectMapper mapper = new ObjectMapper();
         HashMap<String, Object> jsonTrData = mapper.readValue(repBody, new TypeReference<HashMap<String, Object>>() {});
         ArrayList<HashMap<String, Object>> trList = (ArrayList<HashMap<String, Object>>) jsonTrData.get("list");

         for(int i = 0; i <20; i++) {
            String name = (String) trList.get(i).get("name");
            String playerImg = (String) trList.get(i).get("imageUrl");

            HashMap<String, Object> PersonRankInfo = (HashMap<String, Object>) trList.get(i).get("stat");
            String personRank = String.valueOf(PersonRankInfo.get("rank"));
            String personGf = String.valueOf(PersonRankInfo.get("gf"));            //득점
            String personAst = String.valueOf(PersonRankInfo.get("ast"));         //어시스트
            String personSht = String.valueOf(PersonRankInfo.get("sht"));         //슈팅

            HashMap<String, String> PlayerRankData = new HashMap<>();
            PlayerRankData.put("personRank", personRank);
            PlayerRankData.put("name", name);
            PlayerRankData.put("playerImg", playerImg);
            PlayerRankData.put("personGf", personGf);
            PlayerRankData.put("personAst", personAst);
            PlayerRankData.put("personSht", personSht);
            trCrawlingDataList2.add(PlayerRankData);

         }


      } catch (Exception e) {
         // TODO: handle exception
         e.printStackTrace();
      }
      
      return trCrawlingDataList2;
   }
}