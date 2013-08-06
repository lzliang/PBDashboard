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
			<div class="half_w half_left scrollable">
				<div class="h_title">Help Request List</div>
                
                <div id="helping_request">
                 
                </div>
                <div class="scroll_panel" id="request_list_panel">
					<div class="card_style" onclick="update()"> 
                    
      					<div id="request_pic" class="request_pic">
       						 <img height="55" width="42" src="img/coke.jpg">        
       					 </div>
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
                     
                     <div class="card_style" onclick="change(1)">               
      					<div id="request_pic" class="request_pic">
       						 <img height="55" width="42" src="img/iphone.jpg">        
       					 </div>
      					<div id="request_text" class="request_text">
       						 <p id="request_text_name">Name: Amy</p>
       						 <p id="request_text_prodname">Product Name: Apple iPhone 5 16GB</p>
       						<p id="request_text_location">Location: 03-C15</p>
                         </div>
         				 <div id="request_button" class="request_button">
       						 <button>Go Help</button>
       					 </div>
           			<div class="clear"></div>
                     </div>
                    
					
                    </div>
			</div>
            
            <div class="half_w half_right scrollable">
				<div class="h_title">Similar Products</div>
				
                <div class="scroll_panel">
                <div id="similar1" style="display:block">
                  <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/pesi.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Pesi Cola</p>	 
                               <p id="similar_text_price">$1.99</p>	 
                           </div>
                   </div>
                   <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/pesi2.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Pepsi Cola - 36/12 oz. cans</p>	 
                               <p id="similar_text_price">$14.99</p>	 
                           </div>
                   </div>
                   <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/7up.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">7-UP Soda Soft Drink</p>	 
                               <p id="similar_text_price">$1.99</p>	 
                           </div>
                   </div>
                   <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/fanta.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Coca Cola Fanta Strawberry</p>	 
                               <p id="similar_text_price">$1.99</p>	 
                           </div>
                   </div>
                   <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/coke2.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Coca Cola Coke Zero</p>	 
                               <p id="similar_text_price">$1.79</p>	 
                           </div>
                   </div>
                   <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/coke3.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Coca-Cola Vanilla Coke, 12-12 fl. oz Cans</p>	 
                               <p id="similar_text_price">$8.50</p>	 
                           </div>
                   </div>
                   
                   
                   
                   
                 </div>
                 
                 <div id="similar2" style="display:none">
                  <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/iphone2.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Apple iPhone 4 16GB (Black) </p>	 
                               <p id="similar_text_price">$285.99</p>	 
                           </div>
                   </div>
                   <div id="similar_item" class="similar_item">
                      <div id="similar_pic">
                               <img height="100" width="77" src="img/galaxys4.jpg">        
                           </div>
                          <div id="similar_text" >
                               <p id="similar_text_name">Samsung Galaxy S IV/S4 GT-I9500</p>	 
                               <p id="similar_text_price">$630.00</p>	 
                           </div>
                   </div>
                   
                  
                   
                   
                   
                   
                 </div>
                 
                 </div>
					
			</div>
           
			
			<div class="clear"></div>
            
            <div class="full_w">
				<div class="h_title">Product Detail Information</div>
				<div id="tabco4">
    <div>
    <div id="info1" style="display:block">
    <div  style="padding-top:20px"><div id="request_pic" class="request_pic">
       						 <img height="85" width="65" src="img/coke.jpg">        
       					 </div>
      					<div id="request_text" class="request_text">
       						 <p id="request_text_prodname">Product Name: Coca Cola Classic</p>
                             <p id="request_text_name">Price: $1.99</p>
       						<p id="request_text_location">Location: 01-E45</p>
                         </div>
                         </div>
                         <div class="clear"></div>
                         <div style="margin:15px 15px;"><h4>Price Compare:</h4> 
                         	<div style="margin-right:40px; float:left;">Amazon: $1.45</div>      
                         	<div style="margin-right:40px; float:left;">Target: $1.49</div>   
                        	<div style="margin-right:40px; float:left;">Walmart: $1.45</div>
                         
                         </div>
                         <div style="margin:35px 15px; clear:left;"><h4>Product Description:</h4> 18 x 12 x 5 inches ; 21.7 pounds
Shipping Weight: 21.7 pounds (View shipping rates and policies)
Shipping: This item can only be shipped to the 48 contiguous states. We regret it cannot be shipped to APO/FPO, Hawaii, Alaska, or Puerto Rico.
Shipping Advisory: This item must be shipped separately from other items in your order. Additional shipping charges will not apply.</div>
                         </div>
                  
                  <div id="info2" style="display:none">
    <div  style="padding-top:20px"><div id="request_pic" class="request_pic">
       						 <img height="85" width="65" src="img/iphone.jpg">        
       					 </div>
      					<div id="request_text" class="request_text">
       						 <p id="request_text_prodname">Product Name: Apple iPhone 5 16GB (White)</p>
                             <p id="request_text_name">Price: $710.99</p>
       						<p id="request_text_location">Location: 03-C15</p>
                         </div>
                         </div>
                         <div class="clear"></div>
                         <div style="margin:15px 15px;"><h4>Price Compare:</h4> 
                         	<div style="margin-right:40px; float:left;">Amazon: $708.45</div>      
                         	<div style="margin-right:40px; float:left;">BestBuy: $708.45</div>   
                        	
                         </div>
                         <div style="margin:35px 15px; clear:left;"><h4>Product Description:</h4> This iPhone 5 16GB White comes in the original box from Apple with all original accessories in the box. This iPhone 5 16GB comes Factory Unlocked for any GSM and will work with any GSM SIM card in the world.</div>
                         </div>
                  
                  
                         
                         </div>
    <div>
    <h3>Most Useful Customer Reviews:</h3>
    <div style="margin:15px 0px">
    <p >Name: James</p>
    <p> These make me feel like a giant., May 28, 2013</p>
    <p> ------------------------</p>
    </div>
    <div style="margin:15px 0px">
    <p >Name: Chris</p>
    <p> Classic!!., February 25, 2013</p>
    <p> ------------------------</p>
    </div>
    <div style="margin:15px 0px">
    <p>Name: Amber</p>
    <p> Delivery is so fast!!., February 08, 2013</p>

    </div>
    
    
    </div>
    
    
    
    <ul>
        
        <li><a href="javascript:vd()">Details</a></li>
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




<!-- test code for AJAX, need to be deleted 
  -->
<div class="clear"></div>

<div>Getting Started with AJAX using JAVA: Hello World!</div>
  <div><button type="button" onclick="makeRequest()">Say Hello!</button></div>
  <div id="ReloadThis">Defualt Text</div>
  <div>${testString}</div>
  
  
</body>

<!--  
-->
<script type="text/javascript" language="javascript" src="js/ajax.js"></script>
<script type="text/javascript">
window.onload = function(){
initTab();
setTimeout('makeRequest()',2000);
}
</script>
</html>