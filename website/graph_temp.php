<?php
	include_once('dbconnect.php');
?>
      <div class="panel panel-primary">
        <div class="panel-heading">Temperature</div>
			<div class="panel-body">
		
				<canvas id="temp" ></canvas>
<script>
	var ctxL = document.getElementById("temp").getContext('2d');
	var myLineChart = new Chart(ctxL, {
	type: 'line',
	data: {
	labels: [
	<?php
		$sql = $conn->query("SELECT ROUND(AVG(temperature)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate))");

		foreach($sql as $obj){
		echo '"' . $obj['DATE(timedate)']  . '",';
		}
	?>
	],
	datasets: [{
	label: "Temperature of the last 7 days.",
	data: [
	<?php
		foreach($sql as $obj){
		echo '"' . $obj['ROUND(AVG(temperature))']  . '",';
		}
	?>
	],
	backgroundColor: [
	'rgba(105, 0, 132, .2)',
	],
	borderColor: [
	'rgba(200, 99, 132, .7)',
	],
	borderWidth: 2
	}
	]
	},
	  options: {
  	responsive: true,
	animation: { duration: 0 },
    scales: {
      yAxes: [{
      
      ticks: {
        // Include a dollar sign in the ticks
        callback: function(value, index, values) {
          return value + '\u{2103}';
        }
                }
      
      }
    ]
    }
  }
});
</script>
		
		
		</div>
        <div class="panel-footer">	<?php
		$sql = $conn->query("SELECT * FROM weather ORDER BY id DESC LIMIT 1");
		foreach($sql as $obj){
		echo  "Latest temperature &ordmC:<h1><b> " . $obj['temperature'] . "&ordmC </b></h1>from: " . $obj['timedate'] ;
		}?>	</div>
      </div>

    