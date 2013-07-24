<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<link href="css/mctabs.css" rel="stylesheet" type="text/css" />
<script src= "//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
<script src="js/jquery-tabs.js" type="text/javascript"></script>
<title>Pitney Bowes Dashboard</title>
</head>

<body>
<div class="centered">
  <ul id="tabs1" class="mctabs">
    <li><a href="#view1">Help Request</a></li>
    <li><a href="#view2">Deal Center</a></li>
    <li><a href="#view2">Settings</a></li>
  </ul>
  <div class="panel-container">
    <div class="box1">
      <div class="title"> Customers Needing Assitance </div>
      <div class="content" onclick="changeText()" > Sample data </div>
      <div class="content"> Sample data </div>
      <div class="content"> Sample data </div>
    </div>
    <div class="box2">
      <div class="title"> Additional Product Information </div>
     <div class="contentfull">
      <div class = "productimg">
      	<img height="90" width="100" src="img/product1.png"  />
      </div>
      </div>
      </div>
    <div class="box1">
      <div class="title"> Similiar Products </div>
      content 3 </div>
    <div class="box2">
      <div class="title"> Competitor Prices </div>
      <div class="content"> <p id="amazonPrice"> Amazon </p></div>
      <div class="content"> BH Photo </div>
      <div class="content"> Target </div>
    </div>
  </div>
</div>

<div>Getting Started with AJAX using JAVA: Hello World!</div>
  <div id="hello"><button type="button" onclick="makeRequest()">Say Hello!</button></div>
</body>

<script type="text/javascript" language="javascript" src="js/ajax.js"></script>
<script src="js/homepage-js.js" type="text/javascript"></script>

</html>