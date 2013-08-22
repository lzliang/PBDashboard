$(function() {
	

		// Create the chart
		$('#container').highcharts('StockChart', {
		

			title : {
				text : 'Customer Conversion Rate'
			},

            
            
           yAxis: {
               min: 0, 
               max: 100,
                labels: {
	    		    formatter: function() {
	    			    return this.value +'%'; 
	    		    }
	    	    }
           },
            
            tooltip: {
                pointFormat: '<b>{point.y:.1f}%</b>',
            },

			series : [{
				name : 'Conversion Rate',
				data : [  
            [1376481600000,45.72],  
            [1376524800000,41.42],  
            [1376568000000,55.93],  
            [1376611200000,59.87],  
            [1376654400000,58.40],  
            [1376740800000,52.59],  
            [1376784000000,57.96],  
            [1376827200000,65.93],  
            [1376870400000,60.87],  
            [1376913600000,58.40],  
            [1376956800000,62.59],  
            [1377000000000,60.96],  
            [1377043200000,57.55],  
            [1377086400000,67.24],  
            [1377129600000,65.46],
            [1377172800000,64.16]      
        ],
				type : 'area',
				threshold : null,
				tooltip : {
					valueDecimals : 2
				},
				fillColor : {
					linearGradient : {
						x1: 0, 
						y1: 0, 
						x2: 0, 
						y2: 1
					},
					stops : [[0, Highcharts.getOptions().colors[0]], [1, 'rgba(0,0,0,0)']]
				}
			}]
		});
});
