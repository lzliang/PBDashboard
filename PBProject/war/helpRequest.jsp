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
<script type="text/javascript">
function change(i) {
	if (i==1) {
	document.getElementById('similar1').style.display = "none";	
	document.getElementById('info1').style.display = "none";
	document.getElementById('similar2').style.display = "block";	
	document.getElementById('info2').style.display = "block";	
	} else if(i==2){
		document.getElementById('similar2').style.display = "none";	
	document.getElementById('info2').style.display = "none";
	document.getElementById('similar1').style.display = "block";	
	document.getElementById('info1').style.display = "block";
	} else if(i==3) {
		
		document.getElementById('btn2').style.display = "block";	
	document.getElementById('btn1').style.display = "none";
	}
	
}

function btnchange(childnode) {
	var child=childnode;
    var parentdiv=child.parentNode.parentNode;
	var requestPanel=parentdiv.parentNode;
	
	 var myElement = document.createElement("div");//输入想要创建的类型  
            myElement.className="card_style";    
            myElement.id="id1";  
			myElement.innerHTML = parentdiv.innerHTML;
	requestPanel.parentNode.insertBefore(myElement, requestPanel);
	<!-- can just reload the request list here-->
	parentdiv.parentNode.removeChild(parentdiv);
	requestPanel.style.height="62%";
	document.getElementById('btn2').style.display = "block";	
	document.getElementById('btn1').style.display = "none";
	
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
          <div class="card_style" onclick="update(813810010424)">
            <div id="request_pic" class="request_pic"> <img height="55" width="42" src="img/coke.jpg"> </div>
            <div id="request_text" class="request_text">
              <p id="request_text_name">Name: Joe Doe</p>
              <p id="request_text_prodname">Product Name: Coca Cola Classic</p>
              <p id="request_text_location">Location: 01-E45</p>
            </div>
            <div id="request_button" class="request_button">
              <button id="btn1" style="display: block;" onclick="btnchange(this)">Go Help</button>
              <button id="btn2" style="background: #80C65A; display: none;">Helping...</button>
            </div>
            <div class="clear"></div>
          </div>
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

<!--  
-->
<script type="text/javascript" language="javascript" src="js/ajax.js"></script>
<script type="text/javascript">
window.onload = function(){
initTab();
setTimeout('makeRequest()',0);
}
</script>
</html>