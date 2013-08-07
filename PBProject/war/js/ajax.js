/*
 * creates a new XMLHttpRequest object which is the backbone of AJAX,
 * or returns false if the browser doesn't support it
 */
function getXMLHttpRequest() {
	try{
		xmlHttp=new XMLHttpRequest(); // Firefox, Opera 8.0+, Safari
		return xmlHttp;
		}
		catch (e){
		try{
		xmlHttp=new ActiveXObject("Msxml2.XMLHTTP"); // Internet Explorer
		return xmlHttp;
		}
		catch (e){
		try{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		return xmlHttp;
		}
		catch (e){
		alert("Your browser does not support AJAX.");
		return false;
		}
		}
		}
		}

/*
 * AJAX call starts with this function
 */
function makeRequest() {
//	setTimeout('makeRequest()',2000);
  var xmlHttp_one = getXMLHttpRequest();
  xmlHttp_one.onreadystatechange=function(){
	  if(xmlHttp_one.readyState==4){
	  document.getElementById("request_list_panel").innerHTML += xmlHttp_one.responseText;
	  }
	  }
	  xmlHttp_one.open("GET","helpRequestAction.do",true);
	  xmlHttp_one.send(null);
}


function update(id) {
//	setTimeout('makeRequest()',2000);
  var xmlHttp_two = getXMLHttpRequest();
  xmlHttp_two.onreadystatechange=function(){
	  if(xmlHttp_two.readyState==4){
	  document.getElementById("similar_products").innerHTML=xmlHttp_two.responseText;
	  }
	  }
	  xmlHttp_two.open("GET","similarProductAJAX.do?id="+id,true);
	  xmlHttp_two.send(null);
	  
	  
	  var xmlHttp_details = getXMLHttpRequest();
	  xmlHttp_details.onreadystatechange=function(){
		  if(xmlHttp_details.readyState==4){
		  document.getElementById("productDetails").innerHTML=xmlHttp_details.responseText;
		  }
		  }
	  xmlHttp_details.open("GET","productDetailsAJAX.do?id="+id,true);
	  xmlHttp_details.send(null);
}

 



	