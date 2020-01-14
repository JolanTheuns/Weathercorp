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
        
        <div class="panel-heading">Overview 1 hour period</div>
		
		<br>
		 <div style="text-align:center;">
			<button class="btn btn-secondary" onClick="choose('graph_all.php')">Last week</button>
			<button class="btn btn-secondary" onClick="choose('graph_ld.php')">Last day</button>
			<button class="btn btn-secondary" onClick="choose('graph_lh.php')">Last hour</button>
		</div>
        
		<div class="panel-body">
						<canvas id="lum" ></canvas>
<script>
	var ctxL = document.getElementById("lum").getContext('2d');
	
	var myLineChart = new Chart(ctxL, {
	type: 'line',
	data: {
	labels: [
	<?php
		//Get the dates back from the last 7 days.
		$sql = $conn->query("SELECT light, TIME(timedate) FROM weather WHERE timedate >= DATE_SUB(NOW(), INTERVAL 1 HOUR);");

		foreach($sql as $obj){
		echo '"' . $obj['TIME(timedate)']  . '",';
		}
	?>
	],
	datasets: [{
	label: "Luminosity Lux",
	  yAxisID: 'lux',
	data: [
	<?php
		$sql = $conn->query("SELECT light, TIME(timedate) FROM weather WHERE timedate >= DATE_SUB(NOW(), INTERVAL 1 HOUR);");
		
		foreach($sql as $obj){
		echo '"' . $obj['light']  . '",';
		}
	?>
	],
	backgroundColor: ['rgba(105, 0, 132, .2)',],
	borderColor: ['rgba(200, 99, 132, .7)',],
	borderWidth: 2
	},
	{
	label: "Temperature ºC",
	 yAxisID: 'temp',
	data: [
	<?php
		$sql = $conn->query("SELECT temperature, TIME(timedate) FROM weather WHERE timedate >= DATE_SUB(NOW(), INTERVAL 1 HOUR);");
		
		foreach($sql as $obj){
		echo '"' . $obj['temperature']  . '",';
		}
	?>
	],
	backgroundColor: ['rgba(0, 137, 132, .2)',],
	borderColor: ['rgba(0, 10, 130, .7)',],
	borderWidth: 2
	},
	{
	label: "Humidity %",
	yAxisID: 'hum',
	data: [
	<?php
		$sql = $conn->query("SELECT humidity, TIME(timedate) FROM weather WHERE timedate >= DATE_SUB(NOW(), INTERVAL 1 HOUR);");
		
		foreach($sql as $obj){
		echo '"' . $obj['humidity']  . '",';
		}
	?>
	],
	backgroundColor: ['rgba(120,255,120, .2)',],
	borderColor: ['rgba(0, 10, 130, .7)',],
	borderWidth: 2
	},
	{
	label: "Air Pressure mbar",
	 yAxisID: 'air',
	data: [
	<?php
		$sql = $conn->query("SELECT pressure, TIME(timedate) FROM weather WHERE timedate >= DATE_SUB(NOW(), INTERVAL 1 HOUR);");
		
		foreach($sql as $obj){
		echo '"' . $obj['pressure']/100  . '",';
		}
	?>
	],
	backgroundColor: ['rgba(0,0,255, .2)',],
	borderColor: ['rgba(0, 10, 130, .7)',],
	borderWidth: 2
	}
	
	]
	},
	type: 'line',
	options: {
		
		elements: {
                    point:{
                        radius: 0
                    }
                },
		
  	responsive: true,
	animation: { duration: 0 },
    scales: {
      yAxes: [{
      
      ticks: {
		    fixedStepSize: 10,
                    // Include a dollar sign in the ticks
                    callback: function(value, index, values) {
                        return value + ' lux';
                    }
                },
        id: 'lux',
        type: 'linear',
        position: 'left',
      }, {
       ticks: {
		   fixedStepSize: 10,
                    // Include a dollar sign in the ticks
                    callback: function(value, index, values) {
                        return value + '\u{2103}';
                    }
                },
        id: 'temp',
        type: 'linear',
        position: 'left',
     
      },
      {
      ticks: {
		  fixedStepSize: 10,
                    // Include a dollar sign in the ticks
                    callback: function(value, index, values) {
                        return value + '%';
                    }
                },
        id: 'hum',
        type: 'linear',
        position: 'right',
     
      },
      {
      ticks: {
		  fixedStepSize: 50,
                    // Include a dollar sign in the ticks
                    callback: function(value, index, values) {
                        return value + ' mbar';
                    }
                },
        id: 'air',
        type: 'linear',
        position: 'right',
        
     
      }]
    }
  }
});



</script>
		</div>

		
		
        <div class="panel-footer">
		<?php
			$sql = $conn->query("SELECT * FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 1 HOUR) GROUP BY ROUND(DATE(timedate));");
			foreach($sql as $obj){
			echo  "Luminosity of the last hour lux:<h1><b> " . $obj['light']  . " lux  </b></h1>";
			
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
		<div class="panel-footer">
		<?php
			$sql = $conn->query("SELECT * FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 1 HOUR) GROUP BY ROUND(DATE(timedate));");
			foreach($sql as $obj){
			echo  "Temperature of the last hour &ordmC:<h1><b> " . $obj['temperature'] . "&ordmC </b></h1>from: " . $obj['timedate'] ;
			}
		?>
		</div>
		<div class="panel-footer">
		<?php
			$sql = $conn->query("SELECT * FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 1 HOUR) GROUP BY ROUND(DATE(timedate));");
			foreach($sql as $obj){
			echo  "Airpressure of the last hour mbar:<h1><b> " . $obj['pressure']/100  . " mBar  </b></h1>from: " . $obj['timedate'] ;
			}
		?>	
		</div>
		<div class="panel-footer">
			<?php
			$sql = $conn->query("SELECT * FROM weather WHERE DATE(timedate) >= (DATE(NOW())- INTERVAL 1 HOUR) GROUP BY ROUND(DATE(timedate));");
			foreach($sql as $obj){
			echo  "Humidity of the last hour %:<h1><b> " . $obj['humidity'] . "%</b></h1> from: " . $obj['timedate'] ;
			}?>	
		</div>

	  </div>
