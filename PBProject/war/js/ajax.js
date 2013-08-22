var host = window.location.hostname;
//alert(host);

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
	setTimeout('makeRequest()',6000);
	var xmlHttp_one = getXMLHttpRequest();
	xmlHttp_one.onreadystatechange=function(){
		if(xmlHttp_one.readyState==4){
			document.getElementById("request_list_panel").innerHTML = xmlHttp_one.responseText;
		}
	}
	xmlHttp_one.open("GET","helpRequestAction.do",true);
	xmlHttp_one.send(null);
}

function refresh() {
//	setTimeout('refresh()',10000);
	var xmlHttp_refresh = getXMLHttpRequest();
	xmlHttp_refresh.onreadystatechange=function(){
		if(xmlHttp_refresh.readyState==4){
			document.getElementById("request_list_panel").innerHTML += xmlHttp_refresh.responseText;
		}
	}
	xmlHttp_refresh.open("GET","requestRefreshAJAX.do",true);
	xmlHttp_refresh.send(null);
}

function getHelping() {
	var xmlHttp_helping = getXMLHttpRequest();
	xmlHttp_helping.onreadystatechange=function(){
		if(xmlHttp_helping.readyState==4){
			document.getElementById("helping_request").innerHTML = xmlHttp_helping.responseText;
		}
	}
	xmlHttp_helping.open("GET","getHelpingAJAX.do",true);
	xmlHttp_helping.send(null);
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



	jQuery.ajax("http://"+host+"/_api/info/"+id, {
		dataType : "json",
		type : "GET", //GET is the default but just wanted to show this option
		error : function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
		},
		success : function(data, textStatus, jqXHR) {
			var selectString = "";

			data.Reviews.forEach(function(val, index, array) {
				selectString += "<li>"
					+ "<div class=\"comment\">"
					+ "<div class=\"comment_left\">"
					+ "<img style=\"margin-left:15px\" height=\"50\" width=\"50\" src=\"img/user.png\" />"
					+ "<div class=\"comment-meta\">"
					+ "<p class=\"comment-author\"><span>"+val.CustomerName+"</span></p>"
					+ "<p class=\"comment-date\">"+val.Date+"</p> "
					+ "</div>"
					+ "</div>"
					+ "<div class=\"comment_right\">"
					+ "<p><b>"+val.Title+"</b></p>"
					+ "<p>"+val.Content+"</p>"
					+ "<p><img src=\"img/"+val.Rating.charAt(0)+"_Star.gif\" width=\"92\" height=\"20\" /> </p>"
					+ "</div>"
					+ "</div>"
					+ "</li>";
			});

			document.getElementById("comment_list").innerHTML=selectString;
		}
	});








}
var status;

function goHelp(childnode, requestID) {
	var child=childnode;
	var parentdiv=child.parentNode.parentNode;
	var requestPanel=parentdiv.parentNode;
	
	checkStatus(requestID);
//alert('status '+status);
	
	if(status == "Serving") {

		alert('Someone else is helping with this request.');
		parentdiv.parentNode.removeChild(parentdiv);

	} else if(status == "Done") {

		alert('This request is already processed.');
		parentdiv.parentNode.removeChild(parentdiv);

	} else {

		var xmlHttp_help = getXMLHttpRequest();
		xmlHttp_help.onreadystatechange=function(){
			if(xmlHttp_help.readyState==4){
				parentdiv.parentNode.removeChild(parentdiv);
				requestPanel.style.height="64%";
				document.getElementById("helping_request").innerHTML=xmlHttp_help.responseText;
			}
		}
		xmlHttp_help.open("GET","goHelpAJAX.do?id="+requestID,true);
		xmlHttp_help.send(null);

	}
}

function checkStatus(requestID) {

	jQuery.ajax(
			"http://"+host+"/_api/help/status/"+requestID,
			{
				dataType: "json",
				async: false, 
				type: "GET", //GET is the default but just wanted to show this option
				error: function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				success: function(data, textStatus, jqXHR) {
					/*
					 * jQuery will automatically parse the returned JSON so 
					 * data should be our deserialized object.
					 */
					status = data.requestStatus;


					//$("div").html(htmlString);

//					alert('status inside '+status);
//					return status;
				}
			}
	);
}


function complete(requestID) {

	jQuery.ajax(
			"http://"+host+"/_api/help/done/"+requestID,
			{
				dataType: "json",
				type: "GET", //GET is the default but just wanted to show this option
				error: function(jqXHR, textStatus, errorThrown) {
					alert(errorThrown);
				},
				success: function(data, textStatus, jqXHR) {

					document.getElementById('helping_request').innerHTML = "";
					document.getElementById('request_list_panel').style.height="88%";

//					alert('OK');
//					return status;
				}
			}
	);
}


function loadFeedback() {
	jQuery.ajax("http://"+host+"/_api/stats/feedback/employee", {
		dataType : "json",
		type : "GET", //GET is the default but just wanted to show this option
		error : function(jqXHR, textStatus, errorThrown) {
			alert(errorThrown);
		},
		success : function(data, textStatus, jqXHR) {
			var selectString = "";

			data.data.forEach(function(val, index, array) {
				selectString += "<li>"
					+ "<div class=\"comment\">"
					+ "<div class=\"comment_left\">"
					+ "<img style=\"margin-left:15px\" height=\"50\" width=\"50\" src=\"img/user.png\" />"
					+ "<div class=\"comment-meta\">"
					+ "<p class=\"comment-author\"><span>Anonymous</span></p>"
					+ "</div>"
					+ "</div>"
					+ "<div class=\"comment_right\">"
					+ "<p>"+val.feedback+"</p>"
					+ "<p><img src=\"img/"+val.emp_rating+"_Star.gif\" width=\"92\" height=\"20\" /> </p>"
					+ "</div>"
					+ "</div>"
					+ "</li>";
			});

			document.getElementById("feedback_list").innerHTML=selectString;
		}
	});
}

function getCoupon(childnode, barcode) {
	var child=childnode;
	var parentdiv=child.parentNode.parentNode;

	if(barcode=="027242842755") {
		parentdiv.innerHTML = "<div class=\"coupon_pic\"> <img width=\"55\" src=\"img/coupon.png\" /></div>"
			+"<div class=\"coupon_text_yes\"><b>Instant Offer: $179.99</b></div>";
	} else {
		parentdiv.innerHTML = "<div class=\"coupon_text_no\"><p>No instant offer for this product.</p></div>";
	}
	
	
}

