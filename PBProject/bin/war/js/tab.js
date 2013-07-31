window.onload = function(){
init()
}
var containercolor="#ffffff";

function init() {

var border="#ffffff 1px solid";
var conbkcolor="#ffffff";
var tabbk="#cfcfcf";

t4 = new Bindtabs("tabco4",0,"mouseover","t",border,tabbk,conbkcolor,"400px");
t4.c();
}

function vd() {
}

function tabEvent(evt) {
    evt = (evt) ? evt : ((window.event) ? window.event : "");
    if (evt) {
    var elem = getTargetElement(evt);
    var actContainer = (elem.tagName=='A') ? elem.parentNode.parentNode.parentNode.id : elem.parentNode.parentNode.id; 
    var tp = gettab(actContainer).position;
    var hvelem = (elem.tagName=='A') ? elem.parentNode : elem;
    var lielem =  (elem.tagName=='A') ? elem.parentNode.parentNode.getElementsByTagName('li') : elem.parentNode.getElementsByTagName('li');
   
    if ((evt.type == "mouseover") || (evt.type == "click")) {
       actIndex = getIndex(lielem,hvelem);
       if (actIndex==undefined) return;
   
         showContent(actContainer,actIndex);
       } 
   	}
}


function getIndex(arry,elem) {
	for (var i=0; i < arry.length; i++) {
		if (arry[i] == elem) {
		return i;
		}
	}
}
 
function showContent(container,act) {
	var contDiv = new Array;
	var j =0;
	var tab = gettab(container);
	var tp = tab.position;
	var vborder = tab.border;
	var vboxwidth = tab.width;
	var vtabcolor =tab.tabcolor;
	var contDiv0 = $$($(container),('div'));
	
	for(var i=0; i<contDiv0.length; i++){
		if (contDiv0[i].parentNode.id==container) {
			contDiv[j] = contDiv0[i];
			j = j+1;
		}
    }    

	contDiv[act].className = "content";	    
		
	var vbkcolor = getbkColor(contDiv[act]);
	var tagul = getUL(container,tp);
	var tagula = $$(tagul,('a'));
	var tagulli = $$(tagul,('li'));

	for(var i=0; i<tagula.length; i++){
	    tagula[i].className="ula";
	}

	for(var i=0; i<tagulli.length; i++){	
		tagulli[i].className="ullifloat";
	}
	tagul.className= "ulnofloat";
	
	$(container).style.borderRight = (ie6()==true) ?  containercolor +" 0px solid" : containercolor +" 2px solid";
	
	contDiv[act].style.display = "block";
	
	for(var i=0; i<contDiv.length; i++){	
		if (i != act) {
			contDiv[i].style.display = "none";
		}
	}
	var actitem = tagulli[act];
	var actitema = $$(actitem,('a'))[0];
	actitema.style.top ="-1px";
	actitem.style.borderBottom = vborder;	    	
	actitema.style.backgroundColor =vbkcolor;	
    var marginRight_li = getmarginRight(actitem);	
	var marginBottom_li = getmarginBottom(actitem);	
	for(var i=0; i<tagulli.length; i++){
		
	    if (marginRight_li=="0px") {	
		    tagulli[i].style.borderLeft = (i==0) ? vborder : "#fff 0px";					
	    }	
    	
	    if (i != act) {
    	
            var a = $$(tagulli[i],'a')[0];
            	
            a.style.top ="0px";	
            a.style.left ="0px";

            a.style.backgroundColor =vtabcolor;
            tagulli[i].style.borderBottom = vborder;
    		        
	    }
	}
}
  
function bindEvent(container, e) {

var tp = gettab(container).position;
var ele = $$(getUL(container,tp),'li');

   for(var i=0; i<ele.length; i++){ 
		if (e=="click") {
		   ele[i].onmouseover ="";
		   ele[i].onclick = tabEvent;
		}
		if (e=="mouseover") { 
		   ele[i].onmouseover = tabEvent;
		} 	    
   }
}
    

function Bindtabs(id,activetab1,eventType,position,border,tabcolor,bkcolor,width) {	
    this.id = id;
	this.activetab1 = activetab1;
	this.eventType = eventType;
	this.position = position;
	this.border =border;
	this.tabcolor=tabcolor;
	this.bkcolor=bkcolor;
	this.width = width;

	this.c = function () {
	
showContent(this.id,this.activetab1);

	bindEvent(this.id,this.eventType);

	}
}

function getUL(id,ttp) {

if ((ttp=="t") || (ttp=="l") ||(ttp=="p")) {
	var ul = $$($(id),('ul'))[0];
}
else
{
	var n = $$($(id),('ul')).length;
	var ul = $$($(id),('ul'))[n-1];

}
return ul;
}

function gettab(id) {
return (id=="tabco4") ? t4 : t1;
}
 
function ie6() {
var appVer = navigator.appVersion.toLowerCase();
var iePos = appVer.indexOf('msie');
if (iePos !=-1) {
is_minor = parseFloat(appVer.substring(iePos+5,appVer.indexOf(';',iePos)))
is_major = parseInt(is_minor);
}

var is_ie = ((iePos!=-1));
var is_ie6 = (is_ie && is_major == 6);
return is_ie6;
}

function  getmarginBottom(obj) {
return (obj.currentStyle) ?  obj.currentStyle.marginBottom : getComputedStyle(obj,'').getPropertyValue('margin-bottom');
}

function getmarginRight(obj) {
return (obj.currentStyle) ?  obj.currentStyle.marginRight : getComputedStyle(obj,'').getPropertyValue('margin-right');
}

function getbkColor(obj) {
return (obj.currentStyle) ?  obj.currentStyle.backgroundColor : getComputedStyle(obj,'').getPropertyValue('background-color');
}

function getTargetElement(evt) {
    return (evt.target) ? ((evt.target.nodeType == 3) ? evt.target.parentNode : evt.target) : evt.srcElement;
}

function $(id){ return(document.getElementById(id)); }
function $$(e,tag) {return e.getElementsByTagName(tag);}

   