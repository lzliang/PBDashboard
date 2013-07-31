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
</head>
<body>
<div class="wrap">
	<div id="header">
		<div id="top">
			<div class="left">
				<p>Welcome, <strong>Pawe B.</strong> [ <a href="">logout</a> ]</p>
			</div>
			<div class="right">
				<div class="align-right">
					<p>Last login: <strong>23-04-2012 23:12</strong></p>
				</div>
			</div>
		</div>
		<div id="nav">
			<ul>
				<li class="upp"><a href="#">In-store Help</a>
					<ul>
						<li>&#8250; <a href="">Visit site</a></li>
						<li>&#8250; <a href="">Reports</a></li>
						<li>&#8250; <a href="">Add new page</a></li>
						<li>&#8250; <a href="">Site config</a></li>
					</ul>
				</li>
				<li class="upp"><a href="#">Statistics</a>
					<ul>
						<li>&#8250; <a href="">Show all pages</a></li>
						<li>&#8250; <a href="">Add new page</a></li>
						<li>&#8250; <a href="">Add new gallery</a></li>
						<li>&#8250; <a href="">Categories</a></li>
					</ul>
				</li>
				<li class="upp"><a href="#">Settings</a>
					<ul>
						<li>&#8250; <a href="">Site configuration</a></li>
						<li>&#8250; <a href="">Contact Form</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	
	<div id="content">
		
	  <div id="main">
			<div class="half_w half_left">
				<div class="h_title">Request List</div>
                
                <div class="scroll_panel" id="scroll_panel">
					<div class="card_style" onclick="changeText()"> 
                    
      					<div id="request_pic" class="request_pic">
       						 <img height="50" width="39" src="img/coke.jpg">        
       					 </div>
      					<div id="request_text" class="request_text">
       						 <p id="request_text_name">Name: Joe Doe</p>
       						 <p id="request_text_prodname">Product Name: Coca Cola Classic</p>
       						<p id="request_text_location">Location: 01-E45</p>
                         </div>
         				 <div id="request_button" class="request_button">
       						 <button>Help</button>
       					 </div>
           			<div class="clear"></div>
                     </div>
                     
                     <div class="card_style" onclick="changeText()">               
      					<div id="request_pic" class="request_pic">
       						 <img height="50" width="39" src="img/coke.jpg">        
       					 </div>
      					<div id="request_text" class="request_text">
       						 <p id="request_text_name">Name: Joe Doe</p>
       						 <p id="request_text_prodname">Product Name: Coca Cola Classic</p>
       						<p id="request_text_location">Location: 01-E45</p>
                         </div>
         				 <div id="request_button" class="request_button">
       						 <button>Help</button>
       					 </div>
           			<div class="clear"></div>
                     </div>
                    
					
                    </div>
			</div>
			<div class="half_w half_right">
				<div class="h_title">Product Detail Information</div>
				<div id="tabco4">
    
    <div>
    <div  style="padding-top:20px"><div id="request_pic" class="request_pic">
       						 <img height="50" width="39" src="img/coke.jpg">        
       					 </div>
      					<div id="request_text" class="request_text">
       						 <p id="request_text_name">Name: Joe Doe</p>
       						 <p id="request_text_prodname">Product Name: Coca Cola Classic</p>
       						<p id="request_text_location">Location: 01-E45</p>
                         </div>
                         </div>
                         <div class="clear"></div>
                         <div style="margin:15px 15px;">Product Dimensions: 18 x 12 x 5 inches ; 21.7 pounds
Shipping Weight: 21.7 pounds (View shipping rates and policies)
Shipping: This item can only be shipped to the 48 contiguous states. We regret it cannot be shipped to APO/FPO, Hawaii, Alaska, or Puerto Rico.
Shipping Advisory: This item must be shipped separately from other items in your order. Additional shipping charges will not apply.</div>
                         </div>
    <div>product review</div>
    
    <ul>
        
        <li><a href="javascript:vd()">Details</a></li>
        <li><a href="javascript:vd()">Reviews</a></li>
          
    </ul>
    
</div>
			</div>
			
			<div class="clear"></div>
            
            
            <div class="half_w half_left">
				<div class="h_title">Similar Products</div>
				
                <div class="scroll_panel">
                  <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/coke.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Pesi Cola</p>	 
                               <p id="similar_text_price">$9.99</p>	 
                           </div>
                   </div>
                 
                 
                 </div>
					
			</div>
			<div class="half_w half_right">
				<div class="h_title">Price Compare</div>
               	 	<div id="price_compare" class="price_compare_panel">
               		  <div id="price_compare_item" class="clear">
                         <div id="price_compare_pic" class="price_compare_pic left">
                               <img height="65" width="147" src="img/amazon.png">        
                           </div>
                          <div id="price_compare_text" class="price_compare_text left" >
                               <p id="price_compare_amazon">$8.99</p>	  
                           </div>
               		  </div>
                     
                      <div id="price_compare_item" class="clear">
                         <div id="price_compare_pic" class="price_compare_pic left">
                               <img height="45" width="125" src="img/ebay.png">        
                           </div>
                          <div id="price_compare_text" class="price_compare_text left" >
                               <p id="price_compare_amazon">$8.99</p>	  
                           </div>
               		  </div>
                    </div>
                
				
			</div>
			
			<div class="clear"></div>
            
	  </div>
		<div class="clear"></div>
	</div>

	<div id="footer">
		<div class="left">
			<p>Design: <a href="http://kilab.pl">Pawe Balicki</a> | Admin Panel: <a href="">http://axispbcusen.appspot.com/</a></p>
		</div>
		<div class="right">
			<p><a href="">Example link 1</a> | <a href="">Example link 2</a></p>
		</div>
	</div>
</div>



<!-- test code for AJAX, need to be deleted -->
<div class="clear"></div>

<div>Getting Started with AJAX using JAVA: Hello World!</div>
  <div><button type="button" onclick="makeRequest()">Say Hello!</button></div>
  <div id="ReloadThis">Default text</div>
</body>

<!--  
<script type="text/javascript" language="javascript" src="js/ajax.js"></script>
-->

</html>