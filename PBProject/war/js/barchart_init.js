$(function () {
        $('#container').highcharts({
            chart: {
                type: 'bar'
            },
            title: {
                text: 'Staff Evaluation'
            },
            xAxis: {
                categories: ['Alice', 'Conan', 'Dan', 'employee', 'Frank', 'Mandy', 'Oriana', 'Peter', 'Ron', 'Selina', 'Sara'],
                title: {
                    text: null
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: null
                },
                labels: {
                    overflow: 'justify'
                }
            },
            tooltip: {
                valueSuffix: ' points'
            },
            plotOptions: {
                bar: {
                    dataLabels: {
                        enabled: true
                    }
                }
            },
            
            credits: {
                enabled: false
            },
            series: [{
                name: 'Average Score',
                data: [4.07, 3.10, 3.55,
                       {
                           y:4.00,                 
                           events: {
                        	   click: function() {
                        		   window.location = "http://"+window.location.hostname+"/employee_details.jsp";
                        	   }
                           }                
                       },2.03, 4.2, 4.56, 3.47, 4.08, 2.6, 4.1]
            }]
        });
    });
    
