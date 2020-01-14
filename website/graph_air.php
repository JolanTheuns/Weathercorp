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
        <div class="panel-heading">Airpressure</div>
        <div class="panel-body">
		
				<canvas id="pres" ></canvas>
<script>
	var ctxL = document.getElementById("pres").getContext('2d');
	var myLineChart = new Chart(ctxL, {
	type: 'line',
	data: {
	labels: [
	<?php
		$sql = $conn->query("SELECT ROUND(AVG(pressure)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate))");

		foreach($sql as $obj){
		echo '"' . $obj['DATE(timedate)']  . '",';
		}
	?>
	],
	datasets: [{
	label: "Airpressure of the last 7 days.",
	data: [
	<?php
		foreach($sql as $obj){
		echo '"' . $obj['ROUND(AVG(pressure))']/100  . '",';
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
          return value + ' mbar';
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
		echo  "Latest airpressure mbar:<h1><b> " . $obj['pressure']/100  . " mBar  </b></h1>from: " . $obj['timedate'] ;
		}

		?>	
		</div>
      </div>
