<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="pl" xml:lang="pl">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="author" content="Paweł 'kilab' Balicki - kilab.pl" />
<title>Statistics</title>
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



</head>
<body>
<div class="wrap">

	<jsp:include page="header.jsp" />
	
	<div id="content">
	  <div id="main">
      
      <jsp:include page="fast_nav.jsp" />
      
	    <div class="clear"></div>
      
      
		  <div class="full_w">
				<div class="h_title">Help Feature Analysis</div>
					
					<script src="js/highcharts_init.js"></script>
					<script src="http://code.highcharts.com/stock/highstock.js"></script>
<script src="http://code.highcharts.com/stock/modules/exporting.js"></script>


<div id="container" style="height: 400px; min-width: 500px"></div>
					
			</div>
		  <div class="clear"></div>
	  </div>
		<div id="json" class="clear"></div>
	</div>

<jsp:include page="footer.jsp" />	

</div>

</body>
</html>