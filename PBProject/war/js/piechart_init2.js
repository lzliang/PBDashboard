$(function () {
    var chart;
    
    $(document).ready(function () {
    	
    	// Build the chart
        $('#container').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false
            },
            title: {
                text: 'Product Distribution'
            },
            tooltip: {
        	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                type: 'pie',
                name: 'Share',
                data: [
                    {
                        name: 'Computers',
                        y: 25,
                        sliced: true,
                        selected: true,
                        dataLabels: {
                    enabled: true,
                    color: '#000000',
                    connectorColor: '#000000',
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
                }
                    },
                    ['Car/GPS',       12.8],
                    ['Cameras', 16.8],
                    ['Appliances',    8.5],
                    ['Printers',     6.2],
                    ['Mobile Phones',   22.0],
                    ['Home Phone',     0.7],
                    ['MP3 Players',   8.0]
                ]
            }]
        });
    });
    
});