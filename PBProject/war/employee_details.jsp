<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pl" xml:lang="pl">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="author" content="Paweł 'kilab' Balicki - kilab.pl" />
<title>SimpleAdmin</title>
<link rel="stylesheet" type="text/css" href="css/style.css" media="screen" />
<link rel="stylesheet" type="text/css" href="css/navi.css" media="screen" />
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="js/tab.js"></script>
<script type="text/javascript">
window.onload = function(){
	var name = "${userName}";
	if(name=="") {
		window.location = "http://"+window.location.hostname;
	}
}
$(function(){
	$(".box .h_title").not(this).next("ul").hide("normal");
	$(".box .h_title").not(this).next("#home").show("normal");
	$(".box").children(".h_title").click( function() { $(this).next("ul").slideToggle(); });
});
</script>
</head>
<body>
<div class="wrap">
	<div id="header">
		<div id="top">
			<div class="left">
				<p>Welcome, <strong>${userName}.</strong> [ <a href="">logout</a> ]</p>
			</div>
			<div class="right">
				<div class="align-right">
					<p>Last login: <strong>${loginTime}</strong></p>
				</div>
			</div>
		</div>
		<div id="nav">
			<ul>
				<li class="upp"><a href="helpRequest.jsp">In-store Help</a>					
				</li>
				<li class="upp"><a href="statistics.jsp">Statistics</a>
					<ul>
						<li>&#8250; <a href="statistics.jsp">Help Feature Analysis</a></li>
						<li>&#8250; <a href="staff_evaluation.jsp">Staff Evaluation</a></li>
						<li>&#8250; <a href="customer_analysis.jsp">Customer Analysis</a></li>
						<li>&#8250; <a href="">Other Graphics</a></li>
					</ul>
				</li>
				<li class="upp"><a href="#">Settings</a>
					<ul>
						<li>&#8250; <a href="">Site Configuration</a></li>
						<li>&#8250; <a href="">Contact Form</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	
	<div id="content">
		
	  <div id="main">
			<div class="half_w half_left">
				<div class="h_title">Average Rating</div>
                <div style="margin:50px 50px">
            <img src="img/rating.png" width="314" height="137" /> 
            </div>
            </div>
            
            <div class="half_w half_right">
				<div class="h_title">Statistics</div>
				
                <div class="stats">
					<div class="today">
						<h3>Today</h3>
						<p class="count">21</p>
						<p class="type">Helps</p>
						<p class="count">4.32</p>
						<p class="type">Rating</p>

					</div>
					<div class="week">
						<h3>Last week</h3>
						<p class="count">131</p>
						<p class="type">Helps</p>
						<p class="count">3.91</p>
						<p class="type">Rating</p>

					</div>
				</div>
                <div style="margin-left:40px">
                <img src="//chart.googleapis.com/chart?chxl=0:|Sun|Mon|Tue|Wed|Thu|Fri|Sat&chxr=1,0,5&chxt=x,y&chs=440x220&cht=lxy&chco=000000&chds=0,100,0,5&chd=t:-1|3.48,1.794,2.172,2.615,2.78,3.25,4.32&chdlp=b&chg=-1,-1,0,4&chls=3&chma=5,5,5,25&chm=N,282323,0,-1,10" width="360" height="160" alt="" />
                </div>
					
		</div>
           
			<div class="clear"></div>
            
	  </div>
		<div class="clear"></div>
	</div>

	<div id="footer">
		<div class="left">
			<p>Admin Panel: <a href="">http://axispbcusen.appspot.com/</a></p>
		</div>
		<div class="right">
			<p><a href="">Example link 1</a> | <a href="">Example link 2</a></p>
		</div>
	</div>
</div>

</body>

</html>