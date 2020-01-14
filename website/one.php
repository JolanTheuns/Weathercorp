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
<!DOCTYPE html>
<html lang="en">
	<head>
		<title>WeatherCorp</title>
		<meta charset="utf-8">
		<link rel="icon" href="favicon.ico">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js"></script>
		<script src="js/main.js"></script> 
		<style>
			/* Remove the navbar's default rounded borders and increase the bottom margin */ 
			.navbar {
			  margin-bottom: 50px;
			  border-radius: 0;
			}
			
			/* Remove the jumbotron's default bottom margin */ 
			 .jumbotron {
			  margin-bottom: 0;
			}
		   
			/* Add a gray background color and some padding to the footer */
			footer {
			  background-color: #f2f2f2;
			  padding: 25px;
			}
		</style>
	</head>
<body onload="DateNow()">
<div class="jumbotron">
	<div class="container text-center">
		<h2>WeatherCorp</h2>   
		<img src="img/wc.png" width="10%">	
	</div>
</div>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand" href="#">WeatherCorp</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="index.php">Home</a></li>
		<li class="active"><a href="one.php">One</a></li>
		<li><a href="about.php">About</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container" id="graph_all"></div>

<script>
	//This is used to choose if you want to get the values from last hour etc.
	var page;
	function choose(choice){
		page = choice;
		
		$("#graph_all").load(page);
	}

	function test(click){
		alert("You chossed " + page);
	}

	//If the page is loaded first time it shows the most current values.
	if (page == null){
		page = "graph_ld.php";
	}

	//This is for the real live updates.
	$(document).ready(function(){    
		loadstation();
	});

	function loadstation(){
		$("#graph_all").load(page);
		setTimeout(loadstation, 60000);
	}
	
	
	
</script>

<br>
<br>

<footer class="container-fluid text-center">
 <div id='DateNow'></div><br>
	<p>WeatherCorp 2019</p>
</footer>

</body>
</html>


