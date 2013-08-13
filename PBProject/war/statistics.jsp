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
$(function(){
	$(".box .h_title").not(this).next("ul").hide("normal");
	$(".box .h_title").not(this).next("#home").show("normal");
	$(".box").children(".h_title").click( function() { $(this).next("ul").slideToggle(); });
});
</script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
    <script type="text/javascript">
      google.load("visualization", "1", {packages:["corechart"]});
      google.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Sales', 'Expenses'],
          ['2004',  0,      400],
          ['2005',  0,      460],
          ['2006',  0,       1120],
          ['2007',  0,      540]
        ]);

        var options = {
          title: 'Company Performance',
          hAxis: {title: 'Year', titleTextStyle: {color: 'red'}},
		  animation: {duration:1000, easing:'out'}
        };

        var chart = new google.visualization.ColumnChart(document.getElementById('chart_div'));
        chart.draw(data, options);
		
		
		
		setTimeout(function() {

		  data.setValue(0,1,300);
		   data.setValue(1,1,300);
		    data.setValue(2,1,300);
			 data.setValue(3,1,300);
			  chart.draw(data, options);
	  },1000);
		
      }
	  


    </script>


</head>
<body>
<div class="wrap">
	<div id="header">
		<div id="top">
			<div class="left">
				<p>Welcome, <strong>Paweł B.</strong> [ <a href="">logout</a> ]</p>
			</div>
			<div class="right">
				<div class="align-right">
					<p>Last login: <strong>08-01-2013 13:12</strong></p>
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
				<div class="h_title">Help Feature Analysis</div>
					
					<div id="chart_div"></div>
					
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