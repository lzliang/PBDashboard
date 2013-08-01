$(function () {
    var chart;
    $(document).ready(function() {
    Highcharts.setOptions({
        colors: ['#32353A']
    });
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                type: 'column',
                margin: [ 50, 30, 80, 60]
            },
            title: {
                text: 'Usage this month'
            },
            xAxis: {
                categories: [
					'07-14-2013',
                    '07-15-2013',
                    '07-16-2013',
                    '07-17-2013',
                    '07-18-2013',
                    '07-19-2013',
                    '07-20-2013',
                    '07-21-2013',
                    '07-22-2013',
                    '07-23-2013',
                    '07-24-2013',
                    '07-25-2013',
                    '07-26-2013',
                    '07-27-2013',
                    '07-28-2013',
                    '07-29-2013',
                    '07-30-2013',
                    '07-31-2013',
                    '08-01-2013',
                ],
                labels: {
                    rotation: -45,
                    align: 'right',
                    style: {
                        fontSize: '9px',
                        fontFamily: 'Tahoma, Verdana, sans-serif'
                    }
                }
            },
            yAxis: {
                min: 0,
                title: {
                    text: 'Amount of Usage'
                }
            },
            legend: {
                enabled: false
            },
            tooltip: {
                formatter: function() {
                    return '<b>'+ this.x +'</b><br/>'+
                        'Visits: '+ Highcharts.numberFormat(this.y, 0);
                }
            },
                series: [{
                name: 'Visits',
                data: [113, 102, 162, 221, 101, 120, 231, 411, 141,
                    112, 146, 137, 102, 165, 142, 114, 331, 112],
                dataLabels: {
                    enabled: false,
                    rotation: 0,
                    color: '#F07E01',
                    align: 'right',
                    x: -3,
                    y: 20,
                    formatter: function() {
                        return this.y;
                    },
                    style: {
                        fontSize: '11px',
                        fontFamily: 'Verdana, sans-serif'
                    }
                }, 
                pointWidth: 20
            }]
        });
    });
    
});
