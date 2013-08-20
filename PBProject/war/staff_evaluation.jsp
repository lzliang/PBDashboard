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
<script language="javascript" src="http://www.google.com/jsapi"></script>
<script language="javascript" src="js/bar-chart.js"></script>

</head>
<body>
<div class="wrap">
	<div id="header">
		<div id="top">
			<div class="left">
				<p>Welcome, <strong>${userName}</strong> [ <a href="">logout</a> ]</p>
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
      
      <div id="fast_nav" class="fast_nav">
      	<ul class="fast">
					<li><a class="pgr" href="statistics.jsp">Help Feature Analysis</a></li>
                    <li><a class="windows" href="staff_evaluation.jsp">Staff Evaluation</a></li>
					<li><a class="safari" href="customer_analysis.jsp">Customer Analysis</a></li>
					<li><a class="chart" href="#">Other Graphics</a></li>
				</ul>
      
      </div>
	    <div class="clear"></div>
      
      
		  <div class="full_w">
				<div class="h_title">Staff Evaluation</div>
				
                <div style="text-align:center">	
                <script src="js/barchart_init.js"></script>
                    <script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>

<div id="container" style="width: 610px; min-height: 400px; margin: 0 auto"></div>
                    
                    </div>
                    
                    
			</div>
		  
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