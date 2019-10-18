<?php
class DBController {
private $host = "localhost";
private $user = "id11161569_logger123";
private $password = "123456";
private $database = "id11161569_login";
private $conn;

function __construct() {
$this->conn = $this->connectDB();

}

function connectDB() {
$conn = mysqli_connect($this->host,$this->user,$this->password,$this->database);
return $conn;



}
function runQuery($query) {
$result = mysqli_query($this->conn,$query);
while($row=mysqli_fetch_assoc($result)) {
$resultset[] = $row;
}
if(!empty($resultset))
return $resultset;
}
}
?>