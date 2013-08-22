window.onload = function() {
	 jQuery.ajax("http://axispbcusen.appspot.com/_api/stats/servno/all", {
		  dataType : "json",
		  type : "GET", //GET is the default but just wanted to show this option
		  error : function(jqXHR, textStatus, errorThrown) {
			  alert(textStatus);
		  },
		  success : function(data, textStatus, jqXHR) {
			  
			  $('#container').highcharts('StockChart', {
					
					title : {
						text : 'In-store Help Feature Analysis'
					},
					
					series : [{
						name : 'Number of Usage',
						data :data.data, 
		                marker : {
							enabled : true,
							radius : 3
						},
						shadow : true,
						tooltip : {
							valueDecimals : 2
						}
					}]
				});
		  }
	  });
}



