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
$(function(){
	$(".box .h_title").not(this).next("ul").hide("normal");
	$(".box .h_title").not(this).next("#home").show("normal");
	$(".box").children(".h_title").click( function() { $(this).next("ul").slideToggle(); });
});
</script>
<script type="text/javascript" language="javascript" src="js/ajax.js"></script>
<script type="text/javascript">
window.onload = function(){
	var name = "${userName}";
	if(name=="") {
		window.location = "http://"+window.location.hostname;
	}
initTab();
makeRequest();
}
</script>

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
          <p>Last login: <strong>08-23-2013 13:12</strong></p>
        </div>
      </div>
    </div>
    <div id="nav">
      <ul>
        <li class="upp"><a href="helpRequest.jsp">In-store Help</a> </li>
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
      <div class="half_w half_left scrollable">
        <div class="h_title">Help Request List</div>
        <div id="helping_request"> </div>
        <div class="scroll_panel" id="request_list_panel">
        </div>
      </div>
      <div class="half_w half_right scrollable">
        <div class="h_title">Products Details</div>
        <div class="scroll_panel">
          <div id="productDetails"> </div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="full_w scrollable">
        <div class="h_title">Other Information</div>
        <div id="tabco4">
          <div> 
          <div id="similar_products" class="scroll_panel"> </div>
          </div>
          <div>
            <h3>Most Useful Customer Reviews:</h3>
            <div class="comment_list scroll_panel">
            <ol id="comment_list" ></ol>
            </div>
          </div>
          <ul>
            <li><a href="javascript:vd()">Similar Products</a></li>
            <li><a href="javascript:vd()">Reviews</a></li>
          </ul>
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