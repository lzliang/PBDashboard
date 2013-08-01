var queryString = '';
      var dataUrl = '';

      function onLoadCallback() {
        if (dataUrl.length > 0) {
          var query = new google.visualization.Query(dataUrl);
          query.setQuery(queryString);
          query.send(handleQueryResponse);
        } else {
          var dataTable = new google.visualization.DataTable();
          dataTable.addRows(10);

          dataTable.addColumn('number');
          dataTable.setValue(0, 0, 2.1379885147325695);
          dataTable.setValue(1, 0, 2.6575927812082227);
          dataTable.setValue(2, 0, 3.497399208310526);
          dataTable.setValue(3, 0, 4.284093046822818);
          dataTable.setValue(4, 0, 4.2821303139207885);
          dataTable.setValue(5, 0, 4.702677052555373);
          dataTable.setValue(6, 0, 4.15733832056867);
          dataTable.setValue(7, 0, 3.768619989074068);
          dataTable.setValue(8, 0, 3.551052220762358);
          dataTable.setValue(9, 0, 2.5697740667965263);

          draw(dataTable);
        }
      }

      function draw(dataTable) {
        var vis = new google.visualization.ImageChart(document.getElementById('chart'));
        var options = {
          chxl: '1:|Voroinca|Tom|Sam|Mary|Kevin|Jame|Jack|Carol|Andy|Alice',
          chxp: '',
          chxr: '0,0,5|1,0,0',
          chxs: '',
          chxtc: '',
          chxt: 'x,y',
          chbh: 'a,12',
          chs: '520x400',
          cht: 'bhs',
          chco: '000000',
          chds: '0,5',
          chd: 't:2.138,2.658,3.497,4.284,4.282,4.703,4.157,3.769,3.551,2.57',
          chdl: 'Average Score',
          chm: 'N,0D0C0B,0,-1,11',
          chtt: 'Staff Performance Evaluation',
          chts: '676767,14'
        };
        vis.draw(dataTable, options);
      }

      function handleQueryResponse(response) {
        if (response.isError()) {
          alert('Error in query: ' + response.getMessage() + ' ' + response.getDetailedMessage());
          return;
        }
        draw(response.getDataTable());
      }

      google.load("visualization", "1", {packages:["imagechart"]});
      google.setOnLoadCallback(onLoadCallback);