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
<link rel="stylesheet" href="/resources/css/league.css?ver=1.14"
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
					<div class="match_home_info">
						<div class="match_home_info_img">Home team Emblem</div>
						<div class="match_home_info_formation">Home team Formation</div>
						<div class="match_home_info_name">Home team Name</div>
						<div class="match_home_info_goal">Home team Goal</div>
					</div>
				</div>
				<div class="match_info_top_away">
					<div class="match_away_info">
						<div class="match_away_info_goal">Away team Goal</div>
						<div class="match_away_info_name">Away team Name</div>
						<div class="match_away_info_formation">Away team Formation</div>
						<div class="match_away_info_img">Away team team Emblem</div>
					</div>
				</div>
			</div>
			<div class="match_info_detail">
				

				<%@ include file="formation/formation4321.jsp"%>

				<!-- ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ -->

				<%@ include file="formation/reverse4321.jsp"%>

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
		                        var Tid = td.eq(7).text();
		                       
		                     $.ajax({
		                        url:"/craw/innerPlayerRank.ajax",
		                        dataType: "json",
		                        type: "post",
		                        data:{
		                           Tid : Tid, //url 주소로 팀 아이디 값을 넘겨줌 
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
                	if(data3[i].leagueName == "라리가"){
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
                                 "<div class='match_schedule'>"+
                                    	"오늘은 경기가 없습니다. *^_^*"+
                                     "</div>"+
                            "</div>"

                      );

                }
                
                } else{
                    $("#testimonial-slider").append(
                            "<div class='testimonial-item equal-height style-6' style='height:170px;'>"+
                                 "<div class='match_schedule'>"+
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
                         console.log(gameId)
                      }
                   })
                });
                    
                
             }
         
          })  /* 오늘 경기 일정 끝 */
          
          
	        	 
      });
	          
	          
	          
	</script>
</body>
</html>
























