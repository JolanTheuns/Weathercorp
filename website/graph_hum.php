<!--

	Website GUI.
	Project Software Engineering 2019 - 2020.
	
	Heiden, Jeroen (J) van der 463333@student.saxion.nl
	Dang, Quyen (MQ) 463122@student.saxion.nl
	Klosinski, Antoine (AJT) 468506@student.saxion.nl
	Theuns, Jolan (J) 467794@student.saxion.nl
	Nguyen, Tran Khanh Linh (TKL) 461345@student.saxion.nl
	
-->
<?php
	include_once('dbconnect.php');
?>
      <div class="panel panel-success">
        <div class="panel-heading">Humidity</div>
        <div class="panel-body">
		
				<canvas id="hum" ></canvas>
<script>
	var ctxL = document.getElementById("hum").getContext('2d');
	var myLineChart = new Chart(ctxL, {
	type: 'line',
	data: {
	labels: [
	<?php
		$sql = $conn->query("SELECT ROUND(AVG(humidity)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate))");

		foreach($sql as $obj){
		echo '"' . $obj['DATE(timedate)']  . '",';
		}
	?>
	],
	datasets: [{
	label: "Humidity of the last 7 days.",
	data: [
	<?php
		foreach($sql as $obj){
		echo '"' . $obj['ROUND(AVG(humidity))']  . '",';
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
		    fixedStepSize: 1,
        // Include a dollar sign in the ticks
        callback: function(value, index, values) {
          return value + '%';
        }
                }
      
      }
    ]
    }
  }
});
</script>
		
		</div>
        <div class="panel-footer">
		<?php
		$sql = $conn->query("SELECT * FROM weather ORDER BY id DESC LIMIT 1");
		foreach($sql as $obj){
		echo  "Latest Humidity %:<h1><b> " . $obj['humidity']  . "%  </b></h1>from: " . $obj['timedate'] ;
		}

		?>	
		</div>
      </div>
