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
<link rel="stylesheet" href="/resources/css/league.css?ver=1.20"
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
				<div id="testimonial-slider" class="owl-carousel" >

				</div>
			</div>
		</div>
		<!-- 상단 리그 경기 일정 캐러셀 끝  -->

		<!-- 축구경기장 시작  -->
		<div class="match_info_box">
			<div class="match_info_top">
				<div class="match_info_top_home">
					<div class="match_info_home_detail">
					</div>
				</div>
				<div class="match_info_top_away">
					<div class="match_info_away_detail">
					</div>
				</div>
			</div>
			<div class="match_info_detail">
				
				<div class="match_home"></div>
				
				<!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->
				
				<div class="match_away"></div>

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
					<div class="data_bar_graph"></div>
					<div class="data_bar_graph"></div>
					<div class="data_bar_graph"></div>
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
					<div class="data_circle_graph"></div>
					<div class="data_circle_graph"></div>
				</div>
			</div>

			<!-- Personal Rank Finish -->
		</div>
		<!-- Rank box Finish -->

		<!-- data visibility box Start -->
		<div class="data_visibility_box">
			<div class="data_bubble_chart">Bubble Chart</div>
			<div class="data_word_cloud">Word Cloud</div>
		</div>
		<!-- data visibility box Finish -->


	</div>
	<!-- container Finish -->


	<script type="text/javascript">
	
	          $(document).ready(function(){
	        	  
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
               for(var i=0; i < data.length; i++){
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
                    	  
                    	  
                    	  $(".match_home").empty();
                    	  $(".match_away").empty();
                    	  $(".match_info_home_detail").empty();
                    	  $(".match_info_away_detail").empty();
                    	  
                    	  
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
                         }
                          
                          var sortHomeItem = "homePlayerFormation";
                          
                          homeArray.sort(function(a,b) {
                          return a[sortHomeItem]- b[sortHomeItem];
                       });  
                          
                          console.log(homeArray);
                          
                          /////////////////////////////////////////////////away
                          
                          const awayArray = [];
                          
                          for(var i=0; i<data5.length; i++){
                              if(data5[i].awayPlayerFormation !=null && data5[i].awayPlayerFormation != "null"){
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
                           
                           var sortAwayItem = "awayPlayerFormation";
                          
                           awayArray.sort(function(a,b) {
                           return a[sortAwayItem]- b[sortAwayItem];
                        });  
                          
                        console.log(awayArray);
                        	 
                        	$(".match_info_home_detail").append(
                        		"<div class='match_home_info_img'><img class='match_team_emblem' src='"+matchData.homeTeamImg+"'/></div>"+
                        		"<div class='match_home_info_formation'>"+homeTeamFormation+"</div>"+
                        		"<div class='match_home_info_name'>"+matchData.homeTeamName+"</div>"+
                        		"<div class='match_home_info_goal'>"+matchData.homeTeamResult+"</div>"
                        	);
                        	
    						$(".match_info_away_detail").append(
                            		"<div class='match_away_info_goal'>"+matchData.awayTeamResult+"</div>"+
                            		"<div class='match_away_info_name'>"+matchData.awayTeamName+"</div>"+
                            		"<div class='match_away_info_formation'>"+awayTeamFormation+"</div>"+
                            		"<div class='match_away_info_img'><img class='match_team_emblem' src='"+matchData.awayTeamImg+"'/></div>"
                            	);
                        	
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
                        			 
                      }
                   })
                });
                    
                
             }
         
          })  /* 오늘 경기 일정 끝 */
          
          
	        	 
      });
	          
	          
	          
	</script>
</body>
</html>
























