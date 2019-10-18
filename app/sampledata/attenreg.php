<?php
$Name=$_POST['name'];
$S_id=$_POST['s_id'];
$servername = "localhost";
$username = "id11161569_logger123";
$password = "123456";
date_default_timezone_set('Asia/Calcutta'); // CDT


$current_date = date("M,d,Y h:i:s A");
$con =  new mysqli($servername,$username,$password,"id11161569_login") or die('Unable to Connect');


$sql = "INSERT INTO attendance (Name,S_id,ARRTIME) VALUES ('$Name','$S_id','$current_date')" or die('Unable to Connect');

if(mysqli_query($con,$sql))
{
echo "Successfully Uploaded";
}


else{
echo "Error";
}

$con->close();
?>