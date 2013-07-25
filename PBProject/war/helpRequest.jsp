<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="css/main.css">
<link href="css/mctabs.css" rel="stylesheet" type="text/css" />
 <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

<link rel="stylesheet" href="/resources/demos/style.css" />
<script src="js/jquery-ui.js"></script>
<script src="js/jquery-1.8.2.js"></script>
  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
   <script>
  $(function() {
    $( "#tabs" ).tabs();
  });
  </script>
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
      <div class="requestpanel" id="requestpanel">
      <div class="request_1">
      	<div id="pic">
        <img height="100" width="77" src="img/coke.jpg"/>        
        </div>
      	<div id="request_1_text">
        <p id="request_1_text_name">Name: Joe Doe</p>
        <p id="request_1_text_prodname">Product Name: Coca Cola Classic</p>
        <p id="request_1_text_location">Location: 01-E45</p>
          <div id="request_1_button_helping">
        <button >I'm helping</button>
        </div>
          <div id="request_1_button_completed">
        <button>Completed</button>
        
        </div>
        </div>
      
      
      </div>
      <div class="request_1">
      	<div id="pic">
        <img height="100" width="77" src="img/coke.jpg"/>        
        </div>
      	<div id="request_1_text">
        <p id="request_1_text_name">Name: Joe Doe</p>
        <p id="request_1_text_prodname">Product Name: Coca Cola Classic</p>
        <p id="request_1_text_location">Location: 01-E45</p>
        <div id="request_1_button_helping">
        <button >I'm helping</button>
        </div>
          <div id="request_1_button_completed">
        <button>Completed</button>
        
        </div>
        </div>
        
      
      </div>
      
  
      </div>
    </div>
    <div class="box2">
      <div class="title"> Additional Product Information </div>
     <div class="detail">
      
        <div class="overviewBox">
        <div id="detail_pic">
        <img height="130" width="100" src="img/coke.jpg"/>        
        </div>
         <div class="detail_prodname">Coca Cola Classic</div>
         <div class="detail_rating">
         <h5>Rating: 80%</h5>
         </div>
         <div class="productPrice">
         <h5>Price:</h5><br/>
         <p id="pricePart"><span class="priceLarge">$15.</span><span class="priceSmall">99</span></p>
		 </div>
     
         <div id="tabs">
  <ul>
    <li><a href="#tabs-1">Product Information</a></li>
    <li><a href="#tabs-2">Review</a></li>

  </ul>
  <div id="tabs-1" class="detail_info">
    <p>coca cola</p>
  </div>
  <div id="tabs-2" class="detail_review">
    <p>Taste Good</p>
  </div>
  
</div>
         
         
         
         </div>
      
      </div>
      </div>
   
      
      
    <div class="box1">
      <div class="title"> Similiar Products </div>
      <div class="similarpanel">
      <div class="similar_1">
      	<div id="pic">
        <img height="100" width="77" src="img/pepsi.jpg"/>        
        </div>
      	<div id="similar_1_text">
       
        <p id="similar_1_text_prodname">Product Name: Pepsi Cola</p>
        <p id="similar_1_text_location">Location: 01-E46</p>
        </div>
     </div>
     </div>
     </div>
    <div class="box2">
      <div class="title"> Competitor Prices </div>
      <div class="competitorpanel">
      <div class="competitor_1">
      	<div id="pic">
        <img height="100" width="77" src="img/amazon.jpg"/>        
        </div>
      	<div id="competitor_1_text">
       
        <p id="competitor_1_text_prodname">Product Name: Pepsi Cola</p>
        <p id="competitor_1_text_price">Price: $16.04</p>
        </div>
     </div>
     </div>
    </div>
  </div>
</div>

<div>Getting Started with AJAX using JAVA: Hello World!</div>
  <div><button type="button" onclick="makeRequest()">Say Hello!</button></div>
  <div id="ReloadThis">Default text</div>
</body>

<script type="text/javascript" language="javascript" src="js/ajax.js"></script>
<script src="js/homepage-js.js" type="text/javascript"></script>

</html>