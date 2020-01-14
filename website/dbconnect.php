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
//Settings for the database
//weathercorpwc
$servername = "jolantheuns.com";
$username = "u504871954_weathercorp";
$password = "weathercorp";
$database = "u504871954_weather";

//$servername = "localhost";
//$username = "root";
//$password = "usbw";
//$database = "weather";

//Make the connection to the database
$conn = new mysqli($servername, $username, $password, $database);

if (mysqli_connect_error()) {
    die('Connection error: (' . mysqli_connect_errno() . ') '
            . mysqli_connect_error());
}
//$conn->close();
?>