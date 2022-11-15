<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>WorldFootballData</title>
<link rel="stylesheet" href="/resources/css/reset.css" type="text/css">
<link rel="stylesheet" href="/resources/css/league.css?ver=1.27"
	type="text/css">
</head>
<body>
	<!--  페이지 Nav바 시작  -->
	<%@ include file="header.jsp"%>
	<!--  페이지 Nav바 끝  -->

	<div class="container">
		<!-- container start -->

		<!-- 상단 리그 경기 일정 캐러셀 시작  -->
		<div class="row" style="padding: 0 50px;">
			<div class="col-md-6">
				<div id="testimonial-slider" class="owl-carousel"></div>
			</div>
		</div>
		<!-- 상단 리그 경기 일정 캐러셀 끝  -->

		<!-- 축구경기장 시작  -->
		<div class="match_info_box">
			<div class="match_info_top">
				<div class="match_info_top_home">
					<div class="match_info_home_detail"></div>
				</div>
				<div class="match_info_top_away">
					<div class="match_info_away_detail"></div>
				</div>
			</div>
			<div class="match_info_detail">

				<div class="match_home"></div>

				<!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->

				<div class="match_away"></div>

			</div>

			<div class="match_detail_chart">
				<div id="chartdiv"></div>
			</div>

		</div>
		<!-- 축구경기장 끝  -->

		<!-- Rank box Start -->
		<div class="rank_info_box">
			<!-- Team Rank Start -->
			<div class="team_rank_box">
				<table class="team_rank_table">
					<thead>
						<tr>
							<th>순위</th>
							<th>팀</th>
							<th>경기수</th>
							<th>승점</th>
							<th>승</th>
							<th>무</th>
							<th>패</th>
						</tr>
					</thead>
				</table>
				<div class="team_data_box">
					<div class="data_bar_graph">
					</div>
					<div class="data_bar_graph">
					</div>
				</div>
			</div>
			<!-- Team Rank Finish -->

			<!-- Personal Rank Start -->

			<div class="personal_rank_box">
				<table>
					<thead>
						<tr>
							<th>순위</th>
							<th>선수</th>
							<th>득점</th>
							<th>도움</th>
							<th>슈팅</th>
						</tr>
					</thead>
					<tbody class="personal_rank_table">
					</tbody>
				</table>
				<div class="personal_data_box">
					<div class="data_circle_graph">
						<div id="chartdiv2"></div>
					</div>
					<div class="data_circle_graph"></div>
				</div>
			</div>

			<!-- Personal Rank Finish -->
		</div>
		<!-- Rank box Finish -->

		<!-- data visibility box Start -->
		<div class="data_visibility_box">
			<div class="data_bubble_chart">Bubble Chart</div>
			<div class="data_word_cloud">
				<div id="chartdiv3"></div>
			</div>
		</div>
		<!-- data visibility box Finish -->


	</div>
	<!-- container Finish -->

	<script type="text/javascript">
	
	          $(document).ready(function(){
	        	  
	        	  
	        	  var allTeamPlayer = `${allTeamPlayer}`;
	        	  
	        	  
	        	  
	        	  
		          /* 팀 순위테이블 시작  */
		             $.ajax({
		                url: "/craw/eplTeamRank.ajax",
		                dataType : "json",
		                type : "post",
		                async: false,
		                success:function(data){
		                   teamRankData = data;
		                   for(var i=0; i < data.length; i++){
		                         $(".team_rank_table").append(
		                            "<tbody>"+
		                               "<tr class='line'>"+
		                                  "<td>"+data[i].teamRank+"</td>"+
		                                  "<td class='teamClick' id='"+data[i].teamId+"'><img class='team_img' src="+data[i].teamImg+" style='width:25px;margin-right:10px;'>"+data[i].teamName +"</td>"+
		                                  "<td>"+data[i].teamGame+"</td>"+
		                                  "<td>"+data[i].teamPts+"</td>"+
		                                  "<td>"+data[i].teamWin+"</td>"+
		                                  "<td>"+data[i].teamDraw+"</td>"+
		                                  "<td>"+data[i].teamLoss+"</td>"+
		                                  "<td class='teamClick' id='team_id_check' style='display:none;'>"+data[i].teamId+"</td>"+
		                               "</tr>"+
		                            "</tbody>"
		                      ); 
		                   }
		                   
		                   /* 팀 선택시 해당 팀의 선수 정보 가져오기 시작  */
		                   $(".line").click(function(){     
		                       
		                       // 현재 클릭된 Row(<tr>)
		                       var tr = $(this);
		                       
		                       var td = tr.children();
		                       // tr.text()는 클릭된 Row 즉 tr에 있는 모든 값을 가져온다.
		                        var teamId = td.eq(7).text();
		                       
		                     $.ajax({
		                        url:"/craw/innerPlayerRank.ajax",
		                        dataType: "json",
		                        type: "post",
		                        data:{
		                           teamId : teamId, //url 주소로 팀 아이디 값을 넘겨줌 
		                        },
		                        success:function(data4){
		                        	// 정렬할 기준설정 
		                        	var sortItem = "playerGf";
		                        	// 정럴 
		                        	data4.sort(function(a,b){
		                        		return b[sortItem] - a[sortItem];
		                        	});
		                        	
		                        	// 테이블 비워주고 
		                          $(".personal_rank_table").empty();
		                          
		                        	// 추가 
		                              for(var i=0; i < 19; i++){
		                                  $(".personal_rank_table").append(
		                                           "<tr class='line'>"+
		                                              "<td>"+(i+1)+"</td>"+
		                                              "<td><img class='player_img' src="+data4[i].playerImg+" style='width:25px;height:25px;border-radius:50%;margin-right:10px;'>"+data4[i].playerName+"</td>"+
		                                              "<td>"+data4[i].playerGf+"</td>"+
		                                              "<td>"+data4[i].playerAst+"</td>"+
		                                              "<td>"+data4[i].playerSht+"</td>"+
		                                           "</tr>"
		                                  );
		                               }
		                        }
		                        
		                        
		                        
		                        
		                        
		                        
		                     }); // ajax finish
		                     
		                     }); /* 팀 선택시 해당 팀의 선수 정보 가져오기 끝  */
		                   
		                }
		             });/*  팀순위 테이블 끝  */
		        
		        
		        
		        
		        
		        
	        	  
		             /* 전체선수 테이블 20명까지  */  
		             $.ajax({
		                url: "/craw/eplPlayerRank.ajax",
		                dataType : "json",
		                type : "post",
		                async: false,
		                success:function(data){
		                   for(var i=0; i < 20; i++){
		                      $(".personal_rank_table").append(
		                               "<tr>"+
		                                  "<td>"+data[i].personRank+"</td>"+
		                                  "<td><img class='player_img' src="+data[i].playerImg+" style='width:25px;height:25px;border-radius:50%;margin-right:10px;'>"+data[i].name+"</td>"+
		                                  "<td>"+data[i].personGf+"</td>"+
		                                  "<td>"+data[i].personAst+"</td>"+
		                                  "<td>"+data[i].personSht+"</td>"+
		                               "</tr>"
		                      );
		                   }
		                   
		                   
		                         
		                   /* 득점순위 원 그래프 시작. */
		               /*        var root = am5.Root.new("chartdiv2");

		                      root.setThemes([
		                        am5themes_Animated.new(root)
		                      ]);

		                      var chart = root.container.children.push(
		                        am5percent.PieChart.new(root, {
		                        })
		                      );

		                      var series = chart.series.push(
		                        am5percent.PieSeries.new(root, {
		                          name: "Series",
		                          valueField: "value",
		                          categoryField: "category",
		                          alignLabels: false,
                                  tooltip: am5.Tooltip.new(root, {
	                                    labelText: "{value}골"
	                               })
		                        })
		                      );
		                   
		                      series.labels.template.setAll({
		                    	  fontSize: 10,
		                    	  text: "{category}",
		                    	  textType: "radial",
		                    	  centerX: am5.percent(110)
		                    	})

		                         var chartData = [
		                        	 {
		 		                        category: data[data.length-20].name,
		 		                        value: data[data.length-20].personGf
		 		                      },
		 		                      {
		 		                        category: data[data.length-19].name,
		 		                        value: data[data.length-19].personGf
		 		                      },
		 		                      {
		 		                        category: data[data.length-18].name,
		 		                        value: data[data.length-18].personGf
		 		                      },
		 		                      {
		 		                        category: data[data.length-17].name,
		 		                        value: data[data.length-17].personGf
		 		                      },
		 		                      {
		 		                        category: data[data.length-16].name,
		 		                        value: data[data.length-16].personGf
		 		                      },
		 		                      {
		 		                        category: data[data.length-15].name,
		 		                        value: data[data.length-15].personGf
		 		                      },
		 		                      {
		 		                        category: data[data.length-14].name,
		 		                        value: data[data.length-14].personGf
		 		                      }
		                         ];
		                      
		                      //console.log(chartData);
		                      
		                      series.data.setAll(chartData);
		                      
		                      series.ticks.template.set("visible", false);
		                      
		                      series.appear(1000, 100); */
		                      /* 득점순위 원 그래프 종료. */
		                      
		                      
		                      
		                      /* 득점순위 워드클라우드 시작. */
		                      // Create root
								var wordRoot = am5.Root.new("chartdiv3");
		                      
		                      wordRoot.setThemes([
		                    	  am5themes_Animated.new(wordRoot)
		                    	]);
		                      
		                      var wordContainer = wordRoot.container.children.push(am5.Container.new(wordRoot, {
		                    	  width: am5.percent(100),
		                    	  height: am5.percent(100),
		                    	  layout: wordRoot.verticalLayout
		                    	}));
		                      
		                      var wordTitle = wordContainer.children.push(am5.Label.new(wordRoot, {
		                    	  text: "EPL 득점 순위",
		                    	  fontSize: 50,
		                    	  x: am5.percent(50),
		                    	  centerX: am5.percent(50)
		                    	}));
								
								// Create series
								var wordSeries = wordContainer.children.push(am5wc.WordCloud.new(wordRoot, {
									categoryField: "category",
									valueField: "value",
								    maxFontSize: am5.percent(30),
								    randomness: 0,
								    calculateAggregates: true
								}));
								

								//console.log(data);
								
									
		                         var wordData = [];
		                         
		                         
		                         for(var i = 0; i < data.length; i++){
		                        	 var JsonPlayer = new Object();
		                        	 JsonPlayer.category = data[i].name;
		                        	 JsonPlayer.value = parseInt(data[i].personGf);
		                        	 wordData.push(JsonPlayer);
		                         }
		                         
		                         console.log(wordData)
		                         
		                         
	                         	wordSeries.set("heatRules", [{
		                        	  target: wordSeries.labels.template,
		                        	  dataField: "value",
		                        	  min: am5.color(0xB9E0FF),
		                        	  max: am5.color(0x6C4AB6),
		                        	  key: "fill"
		                        	}]);
		                         
		                         wordSeries.labels.template.setAll({
		                        	  fontFamily: "Courier New",
		                        	  cursorOverStyle: "pointer",
		                         })
		                         
		                         /* wordSeries.labels.template.events.on("click", function(ev) {
									  const category = ev.target.dataItem.get("category");
									  window.open("https://stackoverflow.com/questions/tagged/" + encodeURIComponent(category));
									}); */
									
		                         wordSeries.labels.template.set("tooltipText", "{category}: [bold]{value}[/]골");
		                         
									wordSeries.data.setAll(wordData);
									

		                      /* 득점순위 워드클라우드 종료. */
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                      
		                       
		                }
		             }); /* 전체선수 테이블 끝  */
	        	 
         
         
         
	        	 
	        	 /* 오늘 경기 일정 시작 */
         $.ajax({
             url: "/craw/eplPlan.ajax",
             dataType : "json",
             type : "post",
             success:function(data3){
                if(data3.length){
                	var cnt = 0;
                for(var i=0; i < data3.length; i++){
                	if(data3[i].leagueName == "프리미어리그"){
                		cnt++;
                   $("#testimonial-slider").append(
                         "<div class='testimonial-item equal-height style-6' ' style='height:170px;'>"+
                              "<div class='match_schedule' id='"+data3[i].gameId+"'>"+
                                  "<div class='match_day'>"+data3[i].startDate.replace(/(\d{4})(\d{2})(\d{2})/g, '$1-$2-$3')+"</div>"+
                                  "<div class='match_vs'>"+
                                     "<div class='team_emblem'>"+
                                        "<img class='team_logo' src="+data3[i].homeTeamImageUrl+">"+
                                     	"<div class='team_name' style='font-size:15px;'>"+data3[i].homeTN+"</div>"+
                                     "</div>"+
                                     "<div class='match_score'>"+
                                        "<span class='score'>"+data3[i].homeResult+"</span>"+ "<span class='score_vs'>"+":"+"</span>"+ 
                                        "<span class='score'>"+data3[i].awayResult+"</span>"+
                                     "</div>"+
                                     "<div class='team_emblem'>"+
                                        "<img class='team_logo' src="+data3[i].awayTeamImageUrl+">"+
                                        "<div class='team_name'style='font-size:15px;'>"+data3[i].awayTN+"</div>"+
                                     "</div>"+
                                  "</div>"+
                                  "<div class='match_time'>"+data3[i].startTime.replace(/\B(?=(\d{2})+(?!\d))/g, ':')+"</div>"+
                                  "</div>"+
                                  "</div>"

                   );
                   
                	}
                	
                	
                } /* for finish */
                
                if(cnt ==0){
                    $("#testimonial-slider").append(
                            "<div class='testimonial-item equal-height style-6' style='height:170px;'>"+
                                 "<div style='margin-top:70px;'>"+
                                    	"오늘은 경기가 없습니다. *^_^*"+
                                     "</div>"+
                            "</div>"

                      );

                }
                
                } else{
                    $("#testimonial-slider").append(
                            "<div class='testimonial-item equal-height style-6' style='height:170px;'>"+
                                 "<div style='margin-top:70px;'>"+
                                    	"오늘은 경기가 없습니다."+
                                     "</div>"+
                                     "</div>"

                      );

                }
                
                    $("#testimonial-slider").owlCarousel({
                       items : 4,
                   	   itemsDesktop : [ 1000, 4 ],
                       pagination : false,
                       navigation : true,
                       navigationText : [ "<",">" ],
                       autoPlay : false 
                    });
                    
                    /* 오늘 경기일정 박스 클릭시 경기 Id 값 가져오기 시작   */
                   $(".match_schedule").click(function(){
                   var gameId = $(this).attr("id")
                   $.ajax({
                      url:"/craw/clickMatchData.ajax",
                      dataType: "json",
                      type: "post",
                      data:{
                         gameId : gameId,
                      },
                      success:function(data5){
                    	  
                    	  console.log(data5);
                    	  
                    	  $(".match_home").empty();
                    	  $(".match_away").empty();
                    	  $(".match_info_home_detail").empty();
                    	  $(".match_info_away_detail").empty();
                    	  $(".match_detail_chart").empty();
                    	  $(".match_detail_chart").append("<div id='chartdiv'></div>");
                    	  
                    	  const matchData = data5[data5.length-1];
                    	  var data = data5[data5.length-1].homeTeamFormation;
                          var data2 = data5[data5.length-1].awayTeamFormation;
                          
                          if(data.length == 3) {
                             var homeTeamFormation = data.replace(/(\d{1})(\d{1})(\d{1})/g, '$1-$2-$3')
                          } else if (data.length == 4) {
                             var homeTeamFormation = data.replace(/(\d{1})(\d{1})(\d{1})(\d{1})/g, '$1-$2-$3-$4')
                          } else if (data.length == 5) {
                             var homeTeamFormation = data.replace(/(\d{1})(\d{1})(\d{1})(\d{1})(\d{1})/g, '$1-$2-$3-$4-$5')
                          }
                          
                          if(data2.length == 3) {
                             var awayTeamFormation = data2.replace(/(\d{1})(\d{1})(\d{1})/g, '$1-$2-$3')
                          } else if (data2.length == 4) {
                             var awayTeamFormation = data2.replace(/(\d{1})(\d{1})(\d{1})(\d{1})/g, '$1-$2-$3-$4')
                          } else if (data2.length == 5) {
                             var awayTeamFormation = data2.replace(/(\d{1})(\d{1})(\d{1})(\d{1})(\d{1})/g, '$1-$2-$3-$4-$5')
                          }
                          
                          
                          const homeArray = [];
                          const awayArray = [];
                          
                          for(var i=0; i<data5.length; i++){
                        	  
                             if(data5[i].homePlayerFormation !=null && data5[i].homePlayerFormation != "null"){
                                var JsonHome = new Object();
                                JsonHome.homePlayerName = data5[i].homePlayerName;
                                JsonHome.homePlayerFormation = data5[i].homePlayerFormation;
                                JsonHome.homePlayerChanged = data5[i].homePlayerChanged;
                                JsonHome.homePlayerImg = data5[i].homePlayerImg;
                                JsonHome.homePlayerStarted = data5[i].homePlayerStarted;
                                JsonHome.homePlayerBackNumber = data5[i].homePlayerBackNumber;
                                homeArray.push(JsonHome);
                             }
                             
                             if(data5[i].awayPlayerFormation != null && data5[i].awayPlayerFormation != "null"){
                            	 var JsonAway = new Object();
                                 JsonAway.awayPlayerName = data5[i].awayPlayerName;
                                 JsonAway.awayPlayerFormation = data5[i].awayPlayerFormation;
                                 JsonAway.awayPlayerChanged = data5[i].awayPlayerChanged;
                                 JsonAway.awayPlayerImg = data5[i].awayPlayerImg;
                                 JsonAway.awayPlayerStarted = data5[i].awayPlayerStarted;
                                 JsonAway.awayPlayerBackNumber = data5[i].awayPlayerBackNumber;
                                 awayArray.push(JsonAway);
                             }
                             
                         }
                          
                          var sortHomeItem = "homePlayerFormation";
                          
                          homeArray.sort(function(a,b) {
                          return a[sortHomeItem]- b[sortHomeItem];
                       });  
                          
                          const homeGoalArray = [];
                          const awayGoalArray = [];
                          
                          for(var i = 0; i < data5.length; i++){
                        	  if(data5[i].homeAway != null && data5[i].homeAway != "null"){
                        		  if(data5[i].homeAway == "HOME"){
                        			  var JsonHomeGoal = new Object();
                            		  JsonHomeGoal.homeAway = data5[i].homeAway;
                            		  if(data5[i].halfTime == "FIRST_HALF"){
                            			  JsonHomeGoal.halfTime = "(전)";
                        			  } else{
                        				  JsonHomeGoal.halfTime = "(후)";
                        			  }
                            		  JsonHomeGoal.timeMin = data5[i].timeMin;
                            		  JsonHomeGoal.whoGoal = data5[i].whoGoal;
                            		  homeGoalArray.push(JsonHomeGoal);
                        		  }
                        		  if(data5[i].homeAway == "AWAY"){
                        			  var JsonAwayGoal = new Object();
                        			  JsonAwayGoal.homeAway = data5[i].homeAway;
                        			  if(data5[i].halfTime == "FIRST_HALF"){
	                        			  JsonAwayGoal.halfTime = "(전)";
                        			  } else{
                        				  JsonAwayGoal.halfTime = "(후)";
                        			  }
                        			  JsonAwayGoal.whoGoal = data5[i].whoGoal;
                        			  JsonAwayGoal.timeMin = data5[i].timeMin;
                        			  awayGoalArray.push(JsonAwayGoal);
                        		  }
                        	  }
                        	  
                          }
                          
                          //console.log(homeGoalArray);
                          //console.log(awayGoalArray);
                          
                          
                           var sortAwayItem = "awayPlayerFormation";
                          
                           awayArray.sort(function(a,b) {
                           return a[sortAwayItem]- b[sortAwayItem];
                        });  
                          
                        
                        	 
                        	$(".match_info_home_detail").append(
                        		"<div class='match_home_info_img'><img class='match_team_emblem' src='"+matchData.homeTeamImg+"'/></div>"+
                        		"<div class='match_home_info_formation'>"+homeTeamFormation+"</div>"+
                        		"<div class='match_home_info_name'>"+matchData.homeTeamName+"</div>"+
                        		"<div class='match_away_info_goal tooltip'>"+
                        		"<span>"+matchData.homeTeamResult+"</span>"+
                        		"<div class='home_tooltip_content'></div>"+
                        		"</div>"
                        	);
                        	
                        	
                        	for(var i = 0; i < homeGoalArray.length; i++){
                        		
	                        	$(".home_tooltip_content").append(
	                        		"<div>"+homeGoalArray[i].timeMin + "' " + homeGoalArray[i].whoGoal + " " + homeGoalArray[i].halfTime +"</div>"		
	                        	);
	                        	
                        	} 
                        	
                        	
                        	
    						$(".match_info_away_detail").append(
                            		"<div class='match_away_info_goal tooltip'>"+
                            		"<span>"+matchData.awayTeamResult+"</span>"+
                            		"<div class='away_tooltip_content'></div>"+
                            		"</div>"+
                            		"<div class='match_away_info_name'>"+matchData.awayTeamName+"</div>"+
                            		"<div class='match_away_info_formation'>"+awayTeamFormation+"</div>"+
                            		"<div class='match_away_info_img'><img class='match_team_emblem' src='"+matchData.awayTeamImg+"'/></div>"
                            	);
    						
							for(var i = 0; i < awayGoalArray.length; i++){
                        		
	                        	$(".away_tooltip_content").append(
	                        		"<div>"+awayGoalArray[i].timeMin + "' " + awayGoalArray[i].whoGoal + " " + awayGoalArray[i].halfTime +"</div>"		
	                        	);
	                        	
                        	} 
    						
                        	
                        	if(matchData.homeTeamFormation == "3421"){
                        	 $(".match_home").append(
                        			 <%@ include file="formation/formation3421.jsp"%>
                        	 );
                        	} else if(matchData.homeTeamFormation == "343"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation343.jsp"%>
                        		);
                        	} else if(matchData.homeTeamFormation == "3511"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation3511.jsp"%>
                        		);
                        	} else if(matchData.homeTeamFormation == "352"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation352.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "41212"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation41212.jsp"%>
                        		);
                        	} else if(matchData.homeTeamFormation == "4132"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation4132.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "4141"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation4141.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "4231"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation4231.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "4321"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation4321.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "433"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation433.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "442"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation442.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "451"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation451.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "532"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation532.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "541"){
                        		$(".match_home").append(
                        		<%@ include file="formation/formation541.jsp"%>
                        		);
                        	}else if(matchData.homeTeamFormation == "3142"){
                        		$(".match_home").append(
                                		<%@ include file="formation/formation3142.jsp"%>
                                		);
                                	}else if(matchData.homeTeamFormation == "3412"){
                                		$(".match_home").append(
                                        		<%@ include file="formation/formation3412.jsp"%>
                                        		);
                                        	}
                        	
                        	if(matchData.awayTeamFormation == "3421"){
                        		$(".match_away").append(
                           			 <%@ include file="formation/reverse3421.jsp"%>
                           	 );
                        	} else if(matchData.awayTeamFormation == "343"){
                        		$(".match_away").append(
                              			 <%@ include file="formation/reverse343.jsp"%>
                              	 );
                        	}else if(matchData.awayTeamFormation == "3511"){
                        		$(".match_away").append(
                             			 <%@ include file="formation/reverse3511.jsp"%>
                             	 );
                       	}else if(matchData.awayTeamFormation == "352"){
                    		$(".match_away").append(
                         			 <%@ include file="formation/reverse352.jsp"%>
                         	 );
			                   	}else if(matchData.awayTeamFormation == "41212"){
			                		$(".match_away").append(
			                     			 <%@ include file="formation/reverse41212.jsp"%>
			                     	 );
			               	}else if(matchData.awayTeamFormation == "4132"){
			            		$(".match_away").append(
			                 			 <%@ include file="formation/reverse4132.jsp"%>
			                 	 );
			           	}else if(matchData.awayTeamFormation == "4141"){
			        		$(".match_away").append(
			             			 <%@ include file="formation/reverse4141.jsp"%>
			             	 );
			       	}else if(matchData.awayTeamFormation == "4231"){
			    		$(".match_away").append(
			         			 <%@ include file="formation/reverse4231.jsp"%>
			         	 );
				   	}else if(matchData.awayTeamFormation == "4321"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse4321.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "433"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse433.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "442"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse442.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "451"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse451.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "532"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse532.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "541"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse541.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "3142"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse3142.jsp"%>
				     	 );
					}else if(matchData.awayTeamFormation == "3412"){
						$(".match_away").append(
				     			 <%@ include file="formation/reverse3412.jsp"%>
				     	 );
					}
                        	
                        	
                            //매치 디테일 데이터 통계 시작
                            
                            // Create root element
                            // https://www.amcharts.com/docs/v5/getting-started/#Root_element
                            
                            var root = am5.Root.new("chartdiv");
                            
                            // Set themes
                            // https://www.amcharts.com/docs/v5/concepts/themes/
                            root.setThemes([
                              am5themes_Animated.new(root)
                            ]);
                            
                            // Create chart
                            // https://www.amcharts.com/docs/v5/charts/xy-chart/
                            var chart = root.container.children.push(
                              am5xy.XYChart.new(root, {
                                /* panX: false,
                                panY: false,
                                wheelX: "panX",
                                wheelY: "zoomX", */
                                layout: root.verticalLayout,
                                arrangeTooltips: true
                              })
                            );
                            
                            // Use only absolute numbers
                            chart.getNumberFormatter().set("numberFormat", "#.#s");
                            
                            // Add legend
                            // https://www.amcharts.com/docs/v5/charts/xy-chart/legend-xy-series/
                            var legend = chart.children.push(
                              am5.Legend.new(root, {
                                centerX: am5.p50,
                                x: am5.p50
                              })
                            );
                            
                            // Data
                            var data =   [
                               {
                                   age: "점유율",
                                   home: +(data5[data5.length-1].HomePossession / 10),
                                   away: -(data5[data5.length-1].AwayPossession / 10)
                                 },
                                 {
                                     age: "슈팅",
                                     home: +data5[data5.length-1].HomeShooting,
                                     away: -data5[data5.length-1].AwayShooting
                                   },
                                   {
                                       age: "유효슈팅",
                                       home: +data5[data5.length-1].HomeSog,
                                       away: -data5[data5.length-1].AwaySog
                                     },
                               {
                                   age: "코너킥",
                                   home: +data5[data5.length-1].HomeCk,
                                   away: -data5[data5.length-1].AwayCk
                                 },
                               {
                                   age: "파울",
                                   home: +data5[data5.length-1].HomeFo,
                                   away: -data5[data5.length-1].AwayFo
                                 },
                                 {
                                     age: "경고",
                                     home: +data5[data5.length-1].HomeYel,
                                     away: -data5[data5.length-1].AwayYel
                                   },
                               {
                                   age: "퇴장",
                                   home: +data5[data5.length-1].HomeRed,
                                   away: -data5[data5.length-1].AwayRed
                                 }
                               
                               
                               
                            ];
                            
                            //console.log("data : ",data5[data5.length-1]);
                            
                            
                            var yAxis = chart.yAxes.push(
                              am5xy.CategoryAxis.new(root, {
                                categoryField: "age",
                                renderer: am5xy.AxisRendererY.new(root, {
                                  inversed: true,
                                  cellStartLocation: 0.1,
                                  cellEndLocation: 0.9
                                })
                              })
                            );
                            
                            yAxis.data.setAll(data);
                            
                            var xAxis = chart.xAxes.push(
                              am5xy.ValueAxis.new(root, {
                                renderer: am5xy.AxisRendererX.new(root, {})
                              })
                            );
                            
                            function createSeries(field, labelCenterX, pointerOrientation, rangeValue) {
                              var series = chart.series.push(
                                am5xy.ColumnSeries.new(root, {
                                  xAxis: xAxis,
                                  yAxis: yAxis,
                                  valueXField: field,
                                  categoryYField: "age",
                                  sequencedInterpolation: true,
                                  clustered: false,
                                  tooltip: am5.Tooltip.new(root, {
                                    pointerOrientation: "down",
                                    labelText: "{categoryY}: {valueX}"
                                  })
                                })
                              );
                            
                              series.columns.template.setAll({
                                height: am5.p100
                              });
                            
                              series.bullets.push(function() {
                                return am5.Bullet.new(root, {
                                  locationX: 1,
                                  locationY: 0.5,
                                  sprite: am5.Label.new(root, {
                                    centerY: am5.p50,
                                    text: "{valueX}",
                                    populateText: true,
                                    centerX: labelCenterX
                                  })
                                });
                              });
                            
                              series.data.setAll(data);
                              series.appear();
                            
                              var rangeDataItem = xAxis.makeDataItem({
                                value: rangeValue
                              });
                              xAxis.createAxisRange(rangeDataItem);
                              rangeDataItem.get("grid").setAll({
                                strokeOpacity: 1,
                                stroke: series.get("stroke")
                              });
                            
                              var label = rangeDataItem.get("label");
                              label.setAll({
                                text: field.toUpperCase(),
                                fontSize: "1.1em",
                                fill: series.get("stroke"),
                                paddingTop: 10,
                                isMeasured: false,
                                centerX: labelCenterX
                              });
                              label.adapters.add("dy", function() {
                                return -chart.plotContainer.height();
                              });
                            
                              return series;
                            }
                            
                            createSeries("home", am5.p100, "right", -3);
                            createSeries("away", 0, "left", 4);
                            
                            var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {
                              behavior: "zoomY"
                            }));
                            cursor.lineY.set("forceHidden", true);
                            cursor.lineX.set("forceHidden", true);
                            
                            chart.appear(1000, 100);
							
                               //매치 디테일 데이터 통계 끝

                        	
                        	
                        			 
                      } // success funciton finish.
                   })
                });
                    
                
             }
         
          })  /* 오늘 경기 일정 끝 */
          
          
	        	 
      });
	          
	          
	          
	</script>
</body>
</html>
























