package com.dcu.wfd.common.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcu.wfd.common.vo.SerieaDataStorage;
import com.dcu.wfd.util.HttpUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class SerieaController {

   @RequestMapping("/seriea")
      public String serieaJsp(Model model) {
         
         return "/seriea";
      }


      //   오늘 경기 일정 크롤링 데이터 
      @RequestMapping("/craw/serieaPlan.ajax")
      @ResponseBody
      public ArrayList<HashMap<String, String>> serieaTodayMatch()throws Exception {

         // DataStorage VO의 변수에 담긴 프리미어리그 경기일정 크롤링 데이터 리턴.
         
         return SerieaDataStorage.getTodaySerieaMatchData();

      }



      // 경기박스 클릭시 해당경기 상세 정보  크롤링 데이터 
      @RequestMapping("/craw/clickSerieaMatchData.ajax")
      @ResponseBody
      public ArrayList<HashMap<String, String>> serieaMatchDetail(String gameId)throws Exception {
         // 경기 상세 정보 크롤링 모듈.
            
            ArrayList<HashMap<String, String>> GameDetailList = new ArrayList<>();

            String url = "https://sports.daum.net/prx/hermes/api/game/get.json?gameId="+gameId+"&detail=liveData";

            try {
               HttpUtil httpUtil = new HttpUtil();
               String responseBody = httpUtil.httpRequest(url, null);

               HashMap<String, String> Detail1 = new HashMap<>();

               ObjectMapper mapper = new ObjectMapper();         //json 파싱기
               HashMap<String, Object> jsonData =  mapper.readValue(responseBody, new TypeReference<HashMap<String, Object> >() {});
               HashMap<String, Object> homeList = (HashMap<String, Object>) jsonData.get("home");
               HashMap<String, Object> homeTeamList= (HashMap<String, Object>) homeList.get("team");
               HashMap<String, Object> homeFormationList = (HashMap<String, Object>) homeList.get("formation");
               ArrayList<HashMap<String, Object>> homeTeamPlayer = (ArrayList<HashMap<String, Object>>) jsonData.get("homePerson");

               String homeTeamName = (String)homeTeamList.get("name");
               String homeTeamImg = (String) homeTeamList.get("imageUrl");
               String homeTeamResult = (String) homeList.get("result");
               Detail1.put("homeTeamName", homeTeamName);
               Detail1.put("homeTeamImg", homeTeamImg);
               Detail1.put("homeTeamResult", homeTeamResult);

               if(homeFormationList != null) {

                  String homeTeamFormation = (String) homeFormationList.get("name");
                  Detail1.put("homeTeamFormation", homeTeamFormation);

               } else {

                  Detail1.put("homeTeamFormation", null);
               }

               for(int i=0; i < homeTeamPlayer.size(); i++) {
                  HashMap<String, String> Detail2 = new HashMap<>();

                  String homePlayerName = (String) homeTeamPlayer.get(i).get("name");
                  String homePlayerImg = (String) homeTeamPlayer.get(i).get("imageUrl");
                  String homePlayerFormation = String.valueOf(homeTeamPlayer.get(i).get("formationPlace"));
                  String homePlayerChanged = String.valueOf(homeTeamPlayer.get(i).get("isChanged"));
                  String homePlayerStarted = String.valueOf(homeTeamPlayer.get(i).get("isStarted"));
                  String homePlayerBackNumber = String.valueOf(homeTeamPlayer.get(i).get("backNumber"));

                  Detail2.put("homePlayerName", homePlayerName);
                  Detail2.put("homePlayerImg", homePlayerImg);
                  Detail2.put("homePlayerFormation", homePlayerFormation);
                  Detail2.put("homePlayerChanged", homePlayerChanged);
                  Detail2.put("homePlayerStarted", homePlayerStarted);
                  Detail2.put("homePlayerBackNumber", homePlayerBackNumber);
                  GameDetailList.add(Detail2);

               }

               HashMap<String, Object> awayList = (HashMap<String, Object>) jsonData.get("away");
               HashMap<String, Object> awayTeamList = (HashMap<String, Object>) awayList.get("team");
               HashMap<String, Object> awayFormationList = (HashMap<String, Object>) awayList.get("formation");
               ArrayList<HashMap<String, Object>> awayTeamPlayer = (ArrayList<HashMap<String, Object>>) jsonData.get("awayPerson");


               String awayTeamName = (String) awayTeamList.get("name");
               String awayTeamImg = (String) awayTeamList.get("imageUrl");
               String awayTeamResult = (String) awayList.get("result");

               Detail1.put("awayTeamName", awayTeamName);
               Detail1.put("awayTeamImg", awayTeamImg);
               Detail1.put("awayTeamResult", awayTeamResult);

               if(awayFormationList != null) {
                  String awayTeamFormation = (String) awayFormationList.get("name");
                  Detail1.put("awayTeamFormation", awayTeamFormation);
               } else {
                  Detail1.put("awayTeamFormation", null);

               }
               for(int i=0; i < awayTeamPlayer.size(); i++) {
                  HashMap<String, String> Detail3 = new HashMap<>();

                  String awayPlayerName = (String) awayTeamPlayer.get(i).get("name");
                  String awayPlayerImg = (String) awayTeamPlayer.get(i).get("imageUrl");
                  String awayPlayerFormation = String.valueOf(awayTeamPlayer.get(i).get("formationPlace"));
                  String awayPlayerChanged = String.valueOf(awayTeamPlayer.get(i).get("isChanged"));
                  String awayPlayerStarted = String.valueOf(awayTeamPlayer.get(i).get("isStarted"));
                  String awayPlayerBackNumber = String.valueOf(awayTeamPlayer.get(i).get("backNumber"));

                  Detail3.put("awayPlayerName", awayPlayerName);
                  Detail3.put("awayPlayerImg", awayPlayerImg);
                  Detail3.put("awayPlayerFormation", awayPlayerFormation);
                  Detail3.put("awayPlayerChanged", awayPlayerChanged);
                  Detail3.put("awayPlayerStarted", awayPlayerStarted);
                  Detail3.put("awayPlayerBackNumber", awayPlayerBackNumber);
                  GameDetailList.add(Detail3);

               }
               
               
               ArrayList<HashMap<String, Object>> playerGoal = (ArrayList<HashMap<String, Object>>) jsonData.get("goal");
               
               if(playerGoal != null) {
                  
                  for(int i=0; i < playerGoal.size(); i++) {
                     
                     HashMap<String, String> Detail4 = new HashMap<>();
                     
                     String homeAway = (String) playerGoal.get(i).get("homeAway");
                     String halfTime = (String) playerGoal.get(i).get("periodType");
                     String timeMin = String.valueOf(playerGoal.get(i).get("timeMin"));
                     HashMap<String, Object> whoGoalList = (HashMap<String, Object>) playerGoal.get(i).get("person");
                     String whoGoal = (String) whoGoalList.get("name");
                     
                     Detail4.put("homeAway", homeAway);
                     Detail4.put("halfTime", halfTime);
                     Detail4.put("timeMin", timeMin);
                     Detail4.put("whoGoal", whoGoal);
                     GameDetailList.add(Detail4);
                     
                  }
               }
               
               
               HashMap<String, Object> homeStatList = (HashMap<String, Object>) jsonData.get("homeTeamStat");
               String HomePossession = String.valueOf(homeStatList.get("possession"));      //점유율
               String HomeShooting = String.valueOf(homeStatList.get("sht"));      //점유율
               String HomeSog = String.valueOf(homeStatList.get("sog"));      //유효슈팅
               String HomeCk = String.valueOf(homeStatList.get("ck"));      //코너킥
               String HomeFo = String.valueOf(homeStatList.get("fo"));      //파울
               String HomeOff = String.valueOf(homeStatList.get("off"));      //오프사이드
               String HomeYel = String.valueOf(homeStatList.get("yel"));      //경고
               String HomeRed = String.valueOf(homeStatList.get("red"));      //퇴장


               Detail1.put("HomePossession", HomePossession);
               Detail1.put("HomeShooting", HomeShooting);
               Detail1.put("HomeSog", HomeSog);
               Detail1.put("HomeCk", HomeCk);
               Detail1.put("HomeFo", HomeFo);
               Detail1.put("HomeOff", HomeOff);
               Detail1.put("HomeYel", HomeYel);
               Detail1.put("HomeRed", HomeRed);

               HashMap<String, Object> awayStatList = (HashMap<String, Object>) jsonData.get("awayTeamStat");
               String AwayPossession = String.valueOf(awayStatList.get("possession"));      //점유율
               String AwayShooting = String.valueOf(awayStatList.get("sht"));      //점유율
               String AwaySog = String.valueOf(awayStatList.get("sog"));      //유효슈팅
               String AwayCk = String.valueOf(awayStatList.get("ck"));      //코너킥
               String AwayFo = String.valueOf(awayStatList.get("fo"));      //파울
               String AwayOff = String.valueOf(awayStatList.get("off"));      //오프사이드
               String AwayYel = String.valueOf(awayStatList.get("yel"));      //경고
               String AwayRed = String.valueOf(awayStatList.get("red"));      //퇴장

               Detail1.put("AwayPossession", AwayPossession);
               Detail1.put("AwayShooting", AwayShooting);
               Detail1.put("AwaySog", AwaySog);
               Detail1.put("AwayCk", AwayCk);
               Detail1.put("AwayFo", AwayFo);
               Detail1.put("AwayOff", AwayOff);
               Detail1.put("AwayYel", AwayYel);
               Detail1.put("AwayRed", AwayRed);
               GameDetailList.add(Detail1);
               

            } catch (Exception e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            return GameDetailList;
         }


      // 전체 팀 순위 크롤링 데이터 
      @RequestMapping("/craw/serieaTeamRank.ajax")
      @ResponseBody
      public ArrayList<HashMap<String, String>> serieaTeamRank()throws Exception {

         return SerieaDataStorage.getSerieaTeamRankData();

      }


      // 전체 선수 순위 크롤링데이터 
      @RequestMapping("/craw/serieaPlayerRank.ajax")
      @ResponseBody
      public ArrayList<HashMap<String, String>> serieaPlayerRank()throws Exception {

         return SerieaDataStorage.getSerieaPlayerRankData();

      }
      
      
      
      // 전체 선수 어시스트 순위 크롤링데이터 
      @RequestMapping("/craw/serieaPlayerAsistRank.ajax")
      @ResponseBody
      public ArrayList<HashMap<String, String>> serieaPlayerAsistRank()throws Exception {
         
         return SerieaDataStorage.getSerieaPlayerAsistRankData();
         
      }
      

      //팀 내 모든선수 크롤링데이터 
      @RequestMapping("/craw/innerSerieaPlayerRank.ajax")
      @ResponseBody
      public ArrayList<HashMap<String, String>> innerSerieaPlayerRank(String teamId)throws Exception{
         // 모든 팀 내의 선수단 정보를 받아올 그릇 (리그 내 전체 선수 데이터)
               ArrayList<HashMap<String, String>> allPlayerList = new ArrayList<>();
               
               HttpUtil httpUtil = new HttpUtil();

               try {
                  ObjectMapper mapper = new ObjectMapper();

                  // 팀 상세페이지의 데이터 크롤링 주소 
                  String url = "https://sports.daum.net/prx/hermes/api/person/list.json?leagueCode=seriea&seasonKey=20222023&teamId="+ teamId +"&detail=true&pageSize=100";
                  String repBody = httpUtil.httpRequest(url, null);
                  HashMap<String, Object> data = mapper.readValue(repBody, new TypeReference<HashMap<String, Object>>() {});


                  // 팀 상세페이지의 선수단 정보를 담을 그릇 
                  ArrayList<HashMap<String, Object>> teamPlayerList = (ArrayList<HashMap<String, Object>>)data.get("list");


                  for(int i=0; i < teamPlayerList.size(); i++) {

                     // 선수정보: 팀아이디, 팀이름, 선수이름, 선수 골, 선수 경기수, , 선수 슈팅, 선수 어시스트 담을 그릇 
                     HashMap<String, String> playerInfo = new HashMap<>();

                     // 선수정보에 팀아이디 삽입 
                     //               playerInfo.put("playerTeamId", playerTeamId);

                     String playerName = (String) teamPlayerList.get(i).get("nameMain");

                     playerInfo.put("playerName", playerName);

                     HashMap<String, Object> playerTeam = (HashMap<String, Object>) teamPlayerList.get(i).get("team");

                     String teamName = (String) playerTeam.get("name");
                     playerInfo.put("teamName", teamName);

                     HashMap<String, Object> playerStat = (HashMap<String, Object>) teamPlayerList.get(i).get("stat");

                     String playerImg = (String)teamPlayerList.get(i).get("imageUrl");
                     playerInfo.put("playerImg" , playerImg);

                     if(playerStat != null) {
                        String playerGf = String.valueOf(playerStat.get("gf"));
                        String playerMp = String.valueOf(playerStat.get("mp"));
                        String playerSht = String.valueOf(playerStat.get("sht"));
                        String playerAst = String.valueOf(playerStat.get("ast"));

                        playerInfo.put("playerGf", playerGf);
                        playerInfo.put("playerMp", playerMp);
                        playerInfo.put("playerSht", playerSht);
                        playerInfo.put("playerAst", playerAst);
                     } else {
                        playerInfo.put("playerGf", "0");
                        playerInfo.put("playerMp", "0");
                        playerInfo.put("playerSht", "0");
                        playerInfo.put("playerAst", "0");
                     }
                     allPlayerList.add(playerInfo);
                  }

                  //         }



               } catch (Exception e) {
                  // TODO: handle exception
                  e.printStackTrace();
               }
               
//               DataStorage.setEplInnerPlayerRankData(allPlayerList);
               
               return allPlayerList;
      }
}