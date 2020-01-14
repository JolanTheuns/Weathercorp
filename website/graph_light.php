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
      <div class="panel panel-primary">
        <div class="panel-heading">Luminosity</div>
        <div class="panel-body">
						<canvas id="lum" ></canvas>
<script>
	var ctxL = document.getElementById("lum").getContext('2d');
	var myLineChart = new Chart(ctxL, {
	type: 'line',
	data: {
	labels: [
	<?php
		$sql = $conn->query("SELECT ROUND(AVG(light)), DATE(timedate) FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 7 DAY) GROUP BY ROUND(DATE(timedate))");

		foreach($sql as $obj){
		echo '"' . $obj['DATE(timedate)']  . '",';
		}
	?>
	],
	datasets: [{
	label: "Luminosity of the last 7 days.",
	data: [
	<?php
		foreach($sql as $obj){
		echo '"' . $obj['ROUND(AVG(light))']  . '",';
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
        fixedStepSize: 1,
      ticks: {
        // Include a dollar sign in the ticks
        callback: function(value, index, values) {
          return value + ' lux';
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
				echo  "Latest luminosity lux:<h1><b> " . $obj['light']  . " lux  </b></h1>";
				
				if($obj['light'] <= 0){
					echo "<b>Total darkness, the lights may be turned off.";
				}
				elseif($obj['light'] < 20){
					echo "<b>Little too dark. ";
				}
				elseif($obj['light'] < 50){
					echo "<b>There is some light.";
				}
				elseif($obj['light'] < 32000){
					echo "<b>The sensor may be placed in direct sunlight.";
				}
				
				echo " </b>from: " . $obj['timedate'] ;
				
			}
		?>
		</div>
      </div>