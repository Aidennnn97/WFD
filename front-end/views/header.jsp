<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
.header{
	height: 70px;
    width: 100%;
    margin: 0 auto;
    border-bottom: 1px solid black;
}
.col-width{
	float: none;
    clear: both;
    width: 1140px;
    margin: 0 auto;
    padding: 0 30px 0 30px;
    overflow: hidden;
    height: 60px;
}
.headerLeft{
	height: 60px;
    width: 800px;
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
    float: left;
    position: relative;
    top: 15px;
    margin-left:100px;
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
</head>
<body>
<div class="header">
		<div class="col-width">

			<div class="headerLeft">

				<div class="logo"><h1><a href="#">WorldFootballData</a></h1></div>
				<div class="main-menu">

					<ul>	
						<li><a href="#">Epl</a></li>
						<li><a href="#">Laliga</a></li>
						<li><a href="#">Bundesliga</a></li>
						<li><a href="#">Serie A</a></li>
					</ul>

				</div>
				
			</div>
			
		</div>
	</div>
</body>
</html>