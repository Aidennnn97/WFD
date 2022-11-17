<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.header{
	height: 80px;
    width: 97%;
    margin: 0 auto;
    border-bottom: 5px solid #eeeeee;
}
.col-width{
	float: none;
    clear: both;
    width: 90%;
    margin: 0 auto;
    overflow: hidden;
    height: 60px;
}
.headerLeft{
	height: 60px;
    width: 100%;
    float: left;
    overflow: hidden;
}

.logo {
    float: left;
    display: inline-block;
    margin-right: 100px;
}
.main-menu {
    height: 60px;
    position: relative;
    top: 15px;
    margin-left:70%;
}
ul, ol, li {
	display:block;
    list-style-type: none;
    margin: 0;
    padding: 0;
}

.main-menu li {
    display: inline-block;
    float: left;
    padding-right: 30px;
    color: #fff;
    position: relative;
    top: 16px;
    font-size: 16px;
}

div{
	margin:0;
	padding:0;
	border:0;
}

a {
    margin: 0;
    padding: 0;
    text-decoration: none;
    color: black;
}

</style>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.theme.min.css">
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/owl-carousel/1.3.3/owl.carousel.min.js"></script>
	
	<!-- Resources -->
<script src="https://cdn.amcharts.com/lib/5/index.js"></script>
<script src="https://cdn.amcharts.com/lib/5/percent.js"></script>
<script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
<script src="https://cdn.amcharts.com/lib/5/wc.js"></script>
<script src="https://cdn.amcharts.com/lib/5/hierarchy.js"></script>
<script src="https://cdn.amcharts.com/lib/5/map.js"></script>
<script src="https://cdn.amcharts.com/lib/5/geodata/worldLow.js"></script>
	
</head>
<body>
<div class="header">
		<div class="col-width">

			<div class="headerLeft">

				<div class="logo"><h1><a href="main">WorldFootballData</a></h1></div>
				<div class="main-menu">

					<ul>	
						<li><a href="epl">Epl</a></li>
						<li><a href="laliga">Laliga</a></li>
						<li><a href="bundesliga">Bundesliga</a></li>
						<li><a href="seriea">Serie A</a></li>
					</ul>

				</div>
				
			</div>
			
		</div>
	</div>
</body>
</html>