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

#chartdiv {
   width: 100%;
   height: 500px;
}

</style>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/hierarchy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>


</head>
<body>
   <!-- Chart code -->
   <div id="chartdiv"></div>
<!-- Chart code -->
<script>
am5.ready(function() {

// Define data for each year

// Create root element
// https://www.amcharts.com/docs/v5/getting-started/#Root_element
var root = am5.Root.new("chartdiv");


// Set themes
// https://www.amcharts.com/docs/v5/concepts/themes/
root.setThemes([
  am5themes_Animated.new(root)
]);


// Create chart
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/
var chart = root.container.children.push(am5percent.PieChart.new(root, {
  innerRadius: 100,
  layout: root.verticalLayout
}));


// Create series
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Series
var series = chart.series.push(am5percent.PieSeries.new(root, {
  valueField: "size",
  categoryField: "sector"
}));

series.ticks.template.set("visible", false);


// Set data
// https://www.amcharts.com/docs/v5/charts/percent-charts/pie-chart/#Setting_data
series.data.setAll([
  { sector: "Agriculture", size: 6.6 },
  { sector: "Mining and Quarrying", size: 0.6 },
  { sector: "Manufacturing", size: 23.2 },
  { sector: "Electricity and Water", size: 2.2 },
  { sector: "Construction", size: 4.5 },
  { sector: "Trade (Wholesale, Retail, Motor)", size: 14.6 },
  { sector: "Transport and Communication", size: 9.3 },
  { sector: "Finance, real estate and business services", size: 22.5 }
]);


// Play initial series animation
// https://www.amcharts.com/docs/v5/concepts/animations/#Animation_of_series
series.appear(1000, 100);


// Add label
var label = root.tooltipContainer.children.push(am5.Label.new(root, {
  x: am5.p50,
  y: am5.p50,
  centerX: am5.p50,
  centerY: am5.p50,
  fill: am5.color(0x000000),
  fontSize: 50
}));

series.labels.template.setAll({
     text: "",
   });


// Animate chart data
var currentYear = "점유율";
function getCurrentData() {
  var data = chartData[currentYear];
  return data;
}

label.set("text", currentYear);







}); // end am5.ready()
</script>


<!-- HTML -->




</body>
</html>