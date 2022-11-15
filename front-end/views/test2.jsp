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

<style>
#piechart {
   width: 100%;
   height: 500px;
}

#chartdiv2 {
   width: 100%;
   height: 100%;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>


</head>
<body>
   <div id="piechart"></div>


   <script type="text/javascript">
   $(document).ready(function(){
      
       
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

      
});

/* 팀 순위 및 개인 순위 파이차트 끝 */
</script>



</body>
</html>























