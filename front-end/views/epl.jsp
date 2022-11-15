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
<link rel="stylesheet" href="/resources/css/league.css?ver=1.29"
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
			
					<!-- 원 파이 그래프. -->
					<div id="piechart"></div>
				
					<!-- 데이터 막대그래프. -->
					<div id="barchartdiv"></div>
			</div>
			<!-- Team Rank Finish -->
		</div>
		<!-- Rank box Finish -->

		<!-- data visibility box Start -->
		<div class="data_visibility_box">
			<div class="data_bubble_chart">
			</div>
			<div class="data_word_cloud">
				<div id="chartdiv3"></div>
			</div>
		</div>
		<!-- data visibility box Finish -->


	</div>
	<!-- container Finish -->

	<script type="text/javascript">
	
	          $(document).ready(function(){
	        	  
	        	  
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
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	        	  
	               /* 팀 순위 및 개인 순위 파이차트 시작  */
	               //eplTeamRank.ajax로 데이터 받음
	               $.ajax({
	                  url: "/craw/eplTeamRank.ajax",
	                  dataType : "json",
	                  type : "post",
	                  async: false,
	                  success:function(data){
	             // id가 chartdiv인 곳에 루트로 생성
	                  var root = am5.Root.new("piechart");

	                  root.setThemes([am5themes_Animated.new(root)]);
	             
	                  var container = root.container.children.push(
	                    am5.Container.new(root, {
	                      width: am5.p100,
	                      height: am5.p100,
	                      layout: root.horizontalLayout
	                    })
	                  );

	                  // Create Main Chart (툴팁으로 승점 보여줌)
	                  var chart = container.children.push(
	                    am5percent.PieChart.new(root, {
	                      tooltip: am5.Tooltip.new(root, {
	                         labelText: "승점 : {value}점"
	                      })
	                    })
	                  );

	                  // Create series 
	                  var series = chart.series.push(
	                    am5percent.PieSeries.new(root, {
	                      valueField: "value",
	                      categoryField: "category",
	                      alignLabels: false,
	                    })
	                  );
	                //메인 차트 ui
	                  series.labels.template.setAll({
	                   fontSize: 13,
	                    textType: "radial",
	                    text: "{category}",
	                    centerX: am5.percent(110),
	                    radius: 4
	                  });
	                  series.ticks.template.set("visible", false);
	                  series.slices.template.set("toggleKey", "none");


	                  
	                
	                  // Create sub chart (몇 골 넣었는지 보여줌)
	                  var subChart = container.children.push(
	                    am5percent.PieChart.new(root, {
	                      radius: am5.percent(50),
	                      tooltip: am5.Tooltip.new(root, {
	                         labelText: "{value}골"
	                      })
	                    })
	                  );

	                  // Create sub series
	                  //서브 차트 ui
	                  var subSeries = subChart.series.push(
	                    am5percent.PieSeries.new(root, {
	                      valueField: "value",
	                      categoryField: "category",
	                      teamid: "teamId"
	                    })
	                  );
	                  subSeries.labels.template.setAll({
	                     fontSize: 15,
	                      textType: "radial",
	                      text: "{category}",
	                      radius: 4
	                    });
	             //서브 차트의 서브 시리즈 5개 0으로 정의 (있어야 실행 가능하고, 5명의 선수만 불러 올것이라서 5개만 넣음)
	                  subSeries.data.setAll([
	                    { category: "A", value: 0 },
	                    { category: "B", value: 0 },
	                    { category: "C", value: 0 },
	                    { category: "D", value: 0 },
	                    { category: "E", value: 0 }
	                  ]);
	                  subSeries.slices.template.set("toggleKey", "none");

	                  var selectedSlice;
	             //updateLines는 메인 차트와 서브 차트를 이어주는 선 연결
	                  series.on("startAngle", function() {
	                    updateLines();
	                  });

	                  container.events.on("boundschanged", function() {
	                    root.events.on("frameended", function(){
	                      updateLines();
	                     })
	                  })

	                  //updateLines()는 메인 차트와 서브 차트를 이어주는 선 연결
	                  function updateLines() {
	                    if (selectedSlice) {
	                      var startAngle = selectedSlice.get("startAngle");
	                      var arc = selectedSlice.get("arc");
	                      var radius = selectedSlice.get("radius");

	                      var x00 = radius * am5.math.cos(startAngle);
	                      var y00 = radius * am5.math.sin(startAngle);

	                      var x10 = radius * am5.math.cos(startAngle + arc);
	                      var y10 = radius * am5.math.sin(startAngle + arc);

	                      var subRadius = subSeries.slices.getIndex(0).get("radius");
	                      var x01 = 0;
	                      var y01 = -subRadius;

	                      var x11 = 0;
	                      var y11 = subRadius;

	                      var point00 = series.toGlobal({ x: x00, y: y00 });
	                      var point10 = series.toGlobal({ x: x10, y: y10 });

	                      var point01 = subSeries.toGlobal({ x: x01, y: y01 });
	                      var point11 = subSeries.toGlobal({ x: x11, y: y11 });

	                      line0.set("points", [point00, point01]);
	                      line1.set("points", [point10, point11]);
	                    }
	                  }

	                  // lines
	                  var line0 = container.children.push(
	                    am5.Line.new(root, {
	                      position: "absolute",
	                      stroke: root.interfaceColors.get("text"),
	                      strokeDasharray: [2, 2]
	                    })
	                  );
	                  var line1 = container.children.push(
	                    am5.Line.new(root, {
	                      position: "absolute",
	                      stroke: root.interfaceColors.get("text"),
	                      strokeDasharray: [2, 2]
	                    })
	                  );
	               // add events
	               
	               
	                  // 메인 차트를 클릭했을 때 발생하는 이벤트
	                  series.slices.template.events.on("click", function(e) {
	                      selectSlice(e.target);
	                    });

	                  // 메인 차트의 이름, 값들을 선언, 팀 아이디는 서브 차트에서 쓰이므로 넣음
	                  series.data.setAll([
	                    {
	                      category: data[data.length-20].teamName,
	                      value: data[data.length-20].teamPts,
	                      teamid: data[data.length-20].teamId
	                    },
	                    {
	                       category: data[data.length-19].teamName,
	                        value: data[data.length-19].teamPts,
	                        teamid: data[data.length-19].teamId
	                    },
	                    {
	                       category: data[data.length-18].teamName,
	                        value: data[data.length-18].teamPts,
	                        teamid: data[data.length-18].teamId
	                    },
	                    {
	                       category: data[data.length-17].teamName,
	                        value: data[data.length-17].teamPts,
	                        teamid: data[data.length-17].teamId
	                    },
	                    {
	                       category: data[data.length-16].teamName,
	                        value: data[data.length-16].teamPts,
	                        teamid: data[data.length-16].teamId
	                    },
	                    {
	                       category: data[data.length-15].teamName,
	                        value: data[data.length-15].teamPts,
	                        teamid: data[data.length-15].teamId
	                    },
	                    {
	                       category: data[data.length-14].teamName,
	                        value: data[data.length-14].teamPts,
	                        teamid: data[data.length-14].teamId
	                    },
	                    {
	                       category: data[data.length-13].teamName,
	                        value: data[data.length-13].teamPts,
	                        teamid: data[data.length-13].teamId
	                    },
	                    {
	                       category: data[data.length-12].teamName,
	                        value: data[data.length-12].teamPts,
	                        teamid: data[data.length-12].teamId
	                    },
	                    {
	                       category: data[data.length-11].teamName,
	                        value: data[data.length-11].teamPts,
	                        teamid: data[data.length-11].teamId
	                    },
	                    {
	                       category: data[data.length-10].teamName,
	                        value: data[data.length-10].teamPts,
	                        teamid: data[data.length-10].teamId
	                    },
	                    {
	                       category: data[data.length-9].teamName,
	                        value: data[data.length-9].teamPts,
	                        teamid: data[data.length-9].teamId
	                    },
	                    {
	                       category: data[data.length-8].teamName,
	                        value: data[data.length-8].teamPts,
	                        teamid: data[data.length-8].teamId
	                    },
	                    {
	                       category: data[data.length-7].teamName,
	                        value: data[data.length-7].teamPts,
	                        teamid: data[data.length-7].teamId
	                    },
	                    {
	                       category: data[data.length-6].teamName,
	                        value: data[data.length-6].teamPts,
	                        teamid: data[data.length-6].teamId
	                    },
	                    {
	                       category: data[data.length-5].teamName,
	                        value: data[data.length-5].teamPts,
	                        teamid: data[data.length-5].teamId
	                    },
	                    {
	                       category: data[data.length-4].teamName,
	                        value: data[data.length-4].teamPts,
	                        teamid: data[data.length-4].teamId
	                    },
	                    {
	                       category: data[data.length-3].teamName,
	                        value: data[data.length-3].teamPts,
	                        teamid: data[data.length-3].teamId
	                    },
	                    {
	                       category: data[data.length-2].teamName,
	                        value: data[data.length-2].teamPts,
	                        teamid: data[data.length-2].teamId
	                    },
	                    {
	                       category: data[data.length-1].teamName,
	                        value: data[data.length-1].teamPts,
	                        teamid: data[data.length-1].teamId
	                    },
	                  ]);
	               
	               
	               
	             //메인차트에서 클릭했을 때 발생하는 함수
	                  function selectSlice(slice) {
	                    selectedSlice = slice;
	                    //selectSlice 함수를 실행하면 slice > dataItem > dataContext로 들어가면 기존에 선언한 팀이름, 승점, 팀id 확인가능
	                    var dataItem = slice.dataItem;
	                    var dataContext = dataItem.dataContext;
	             //dataContext에서 팀id를 가지고 innerPlayerRank.ajax에서 받아온 데이터와 비교
	                    if (dataContext) {
	                       var tId = slice._dataItem.dataContext.teamid;
	                        innerPlayerRank(tId, function (data4) {
	                           if (data4 == null || data4.length == 0) {
	                            alert('선수 순위 데이터를 불러오지 못했습니다.');
	                            return;
	                           }
	                           //subData에 비교한 값을 받아서 push
	                         let subData = [];
	                         for (let i = 0; i < 5; i++) {
	                            if (!data4[i]) {
	                               break;
	                            } else {
	                               subData.push({
	                                   category: data4[i]['playerName'],
	                                   value: Number(data4[i]['playerGf'])
	                                })
	                            }
	                         }
	                          // 팀의 선수가 5명 보다 작으면 숨기는 기능
	                           var i = 0;
	                         subSeries.data.each(function(dataObject) {
	                           try {
	                            if(subData[i]){
	                                   subSeries.data.setIndex(i, subData[i]);
	                                   if(!subSeries.dataItems[i].get("visible")){
	                                       subSeries.dataItems[i].show();
	                                   }
	                               }
	                               else{
	                                   subSeries.dataItems[i].hide();
	                               }
	                           } catch (e) {
	                           }
	                           
	                           
	                           i++;
	                         });
	                           
	                        });
	                       
	                     
	                    }
	                
	                    var middleAngle = slice.get("startAngle") + slice.get("arc") / 2;
	                    var firstAngle = series.dataItems[0].get("slice").get("startAngle");

	                    series.animate({
	                      key: "startAngle",
	                      to: firstAngle - middleAngle,
	                      duration: 1000,
	                      easing: am5.ease.out(am5.ease.cubic)
	                    });
	                    series.animate({
	                      key: "endAngle",
	                      to: firstAngle - middleAngle + 360,
	                      duration: 1000,
	                      easing: am5.ease.out(am5.ease.cubic)
	                    });
	                  }

	                  container.appear(1000, 10);

	                  series.events.on("datavalidated", function() {
	                    selectSlice(series.slices.getIndex(0));
	                  });
	                  
	                  
	                  
	                  } // success function finish.
	               });
		             
		             
		             
		             
		             
		             //callback함수로 innerPlayerRank라는 함수를 만들어 위에서 사용하고있다.
		             function innerPlayerRank (tId, callback) {
		                $.ajax({
		                     url:"/craw/innerPlayerRank.ajax",
		                     dataType: "json",
		                     type: "post",
		                     data:{
		                        teamId : tId, 
		                     },
		                     success:function(data4){
		                        var sortItem = "playerGf";
		                        data4.sort(function(a,b){
		                           return b[sortItem] - a[sortItem];   
		                        });
		                        
		                        callback(data4);

		                     
		                     }
		                })
		             }
		             
		             $.ajax({
		                  url: "/craw/eplTeamRank.ajax",
		                  dataType : "json",
		                  type : "post",
		                  async: false,
		                  success:function(data){
		                      /* 막대그래프 시작. */
			                   // Create root element
						// https://www.amcharts.com/docs/v5/getting-started/#Root_element
						var root = am5.Root.new("barchartdiv");
						
						// Set themes
						// https://www.amcharts.com/docs/v5/concepts/themes/
						root.setThemes([
						  am5themes_Animated.new(root)
						]);
						
			            
						var barData = [
							{
							  name: data[0].teamName,
							  steps: parseInt(data[0].teamPts),
							  pictureSettings: {
							    src: data[0].teamImg
							  },
							  columnSettings: {fill: am5.color(0x8D9EFF)}
							},{
							  name: data[1].teamName,
							  steps: parseInt(data[1].teamPts),
							  pictureSettings: {
							    src: data[1].teamImg
							  },
							  columnSettings: {fill: am5.color(0x8D9EFF)}
							},{
							  name: data[2].teamName,
							  steps: parseInt(data[2].teamPts),
							  pictureSettings: {
							    src: data[2].teamImg
							  },
							  columnSettings: {fill: am5.color(0x8D9EFF)}
							},{
							  name: data[3].teamName,
							  steps: parseInt(data[3].teamPts),
							  pictureSettings: {
							    src: data[3].teamImg
							  },
							  columnSettings: {fill: am5.color(0x8D9EFF)}
							},{
							  name: data[4].teamName,
							  steps: parseInt(data[4].teamPts),
							  pictureSettings: {
							    src: data[4].teamImg
							  },
							  columnSettings: {fill: am5.color(0x82CD47)}
							},{
							  name: data[5].teamName,
							  steps: parseInt(data[5].teamPts),
							  pictureSettings: {
							    src: data[5].teamImg
							  },
							  columnSettings: {fill: am5.color(0x82CD47)}
							},{
							  name: data[17].teamName,
							  steps: parseInt(data[17].teamPts),
							  pictureSettings: {
							    src: data[17].teamImg
							  },
							  columnSettings: {fill: am5.color(0x6B728E)}
							},{
							  name: data[18].teamName,
							  steps: parseInt(data[18].teamPts),
							  pictureSettings: {
							    src: data[18].teamImg
							  },
							  columnSettings: {fill: am5.color(0x6B728E)}
							},{
							  name: data[19].teamName,
							  steps: parseInt(data[19].teamPts),
							  pictureSettings: {
							    src: data[19].teamImg
							  },
							  columnSettings: {fill: am5.color(0x6B728E)}
							}
						];
						console.log(barData);
						
						// Create chart
						// https://www.amcharts.com/docs/v5/charts/xy-chart/
						var chart = root.container.children.push(
						  am5xy.XYChart.new(root, {
						    panX: false,
						    panY: false,
						    wheelX: "none",
						    wheelY: "none",
						    paddingBottom: 50,
						    paddingTop: 40
						  })
						);
						
						// Create axes
						// https://www.amcharts.com/docs/v5/charts/xy-chart/axes/
						
						var xRenderer = am5xy.AxisRendererX.new(root, {});
						xRenderer.grid.template.set("visible", false);
						
						var xAxis = chart.xAxes.push(
						  am5xy.CategoryAxis.new(root, {
						    paddingTop:40,
						    categoryField: "name",
						    renderer: xRenderer
						  })
						);
						
						
						var yRenderer = am5xy.AxisRendererY.new(root, {});
						yRenderer.grid.template.set("strokeDasharray", [3]);
						
						var yAxis = chart.yAxes.push(
						  am5xy.ValueAxis.new(root, {
						    min: 0,
						    renderer: yRenderer
						  })
						);
						
						// Add series
						// https://www.amcharts.com/docs/v5/charts/xy-chart/series/
						var series = chart.series.push(
						  am5xy.ColumnSeries.new(root, {
						    name: "Income",
						    xAxis: xAxis,
						    yAxis: yAxis,
						    valueYField: "steps",
						    categoryXField: "name",
						    sequencedInterpolation: true,
						    calculateAggregates: true,
						    maskBullets: false,
						    tooltip: am5.Tooltip.new(root, {
						      dy: -30,
						      pointerOrientation: "vertical",
						      labelText: "{valueY}"
						    })
						  })
						);
						
						series.columns.template.setAll({
						  strokeOpacity: 0,
						  cornerRadiusBR: 10,
						  cornerRadiusTR: 10,
						  cornerRadiusBL: 10,
						  cornerRadiusTL: 10,
						  maxWidth: 50,
						  fillOpacity: 0.8,
						  templateField: "columnSettings"
						});
						
						var currentlyHovered;
						
						series.columns.template.events.on("pointerover", function (e) {
						  handleHover(e.target.dataItem);
						});
						
						series.columns.template.events.on("pointerout", function (e) {
						  handleOut();
						});
						
						function handleHover(dataItem) {
						  if (dataItem && currentlyHovered != dataItem) {
						    handleOut();
						    currentlyHovered = dataItem;
						    var bullet = dataItem.bullets[0];
						    bullet.animate({
						      key: "locationY",
						      to: 1,
						      duration: 600,
						      easing: am5.ease.out(am5.ease.cubic)
						    });
						  }
						}
						
						function handleOut() {
						  if (currentlyHovered) {
						    var bullet = currentlyHovered.bullets[0];
						    bullet.animate({
						      key: "locationY",
						      to: 0,
						      duration: 600,
						      easing: am5.ease.out(am5.ease.cubic)
						    });
						  }
						}
						
						var circleTemplate = am5.Template.new({});
						
						series.bullets.push(function (root, series, dataItem) {
						  var bulletContainer = am5.Container.new(root, {});
						  var circle = bulletContainer.children.push(
						    am5.Circle.new(
						      root,
						      {
						        radius: 34
						      },
						      circleTemplate
						    )
						  );
						
						  var maskCircle = bulletContainer.children.push(
						    am5.Circle.new(root, { radius: 27 })
						  );
						
						  // only containers can be masked, so we add image to another container
						  var imageContainer = bulletContainer.children.push(
						    am5.Container.new(root, {
						      mask: maskCircle
						    })
						  );
						
						  var image = imageContainer.children.push(
						    am5.Picture.new(root, {
						      templateField: "pictureSettings",
						      centerX: am5.p50,
						      centerY: am5.p50,
						      width: 60,
						      height: 60
						    })
						  );
						
						  return am5.Bullet.new(root, {
						    locationY: 0,
						    sprite: bulletContainer
						  });
						});
						
						// heatrule
						/* series.set("heatRules", [
						  {
						    dataField: "valueY",
						    min: am5.color(0xe5dc36),
						    max: am5.color(0x5faa46),
						    target: series.columns.template,
						    key: "fill"
						  },
						  {
						    dataField: "valueY",
						    min: am5.color(0xe5dc36),
						    max: am5.color(0x5faa46),
						    target: circleTemplate,
						    key: "fill"
						  }
						]);  */
						
						series.data.setAll(barData);
						xAxis.data.setAll(barData);
						
						var cursor = chart.set("cursor", am5xy.XYCursor.new(root, {}));
						cursor.lineX.set("visible", false);
						cursor.lineY.set("visible", false);
						
						cursor.events.on("cursormoved", function () {
						  var dataItem = series.get("tooltip").dataItem;
						  if (dataItem) {
						    handleHover(dataItem);
						  } else {
						    handleOut();
						  }
						});
						
						// Make stuff animate on load
						// https://www.amcharts.com/docs/v5/concepts/animations/
						series.appear();
						chart.appear(1000, 100);
			                   /* 막대그래프 종료.. */
		                  }
		             });
		        
		        
		        
		        
		        
		        
	        	  
		             /* 전체선수 테이블 20명까지  */  
		             $.ajax({
		                url: "/craw/eplPlayerRank.ajax",
		                dataType : "json",
		                type : "post",
		                async: false,
		                success:function(data){
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
		                         
		                         //console.log(wordData)
		                         
		                         
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
         
         
          
          
	        	 
      }); // 도큐먼트 레디 펑션 끝 .
	          
	          
	          
	</script>
</body>
</html>
























