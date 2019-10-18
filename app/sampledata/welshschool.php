<?php
include 'DBController.php';
$db_handle = new DBController();
$productResult = $db_handle->runQuery("select * from attendance");

if (isset($_POST["export"])) {
$filename = "Export_excel.xls";
header("Content-Type: application/vnd.ms-excel");
header("Content-Disposition: attachment; filename=\"$filename\"");
$isPrintHeader = false;
if (! empty($productResult)) {
foreach ($productResult as $row) {
if (! $isPrintHeader) {
echo implode("\t", array_keys($row)) . "\n";
$isPrintHeader = true;
}
echo implode("\t", array_values($row)) . "\n";
}
}
exit();
}
?>
<html>
<head>

<style>
* {
box-sizing: border-box;
}
#myInput {
background-image: url('w3schools.com/css/searchicon.png');
background-position: 10px 10px;
background-repeat: no-repeat;
width: 100%;
font-size: 16px;
padding: 12px 20px 12px 40px;
border: 1px solid #ddd;
margin-bottom: 12px;
}
body {
font-size: 0.95em;
font-family: arial;
color: #212121;
}

th {
background: #E6E6E6;
border-bottom: 1px solid #000000;
}

#table-container {
width: 850px;
margin: 50px auto;
}

table#tab {
border-collapse: collapse;
width: 100%;
}

table#tab th, table#tab td {
border: 1px solid #E0E0E0;
padding: 8px 15px;
text-align: left;
font-size: 0.95em;
}

.btn {
padding: 8px 4px 8px 1px;
}
#btnExport {
padding: 10px 40px;
background: #499a49;
border: #499249 1px solid;
color: #ffffff;
font-size: 0.9em;
cursor: pointer;
}
</style>
</head>
<body>
<img src="https://directory.edugorilla.com/wp-content/uploads/sites/6/2017/03/1375849_1087076704659034_5673026594666843_n.jpeg" width="100" height="100"/>
<center><font color="red"><font size="8"><b>Inet Infotech Pupil Attendance Portal</font></b></center>
<marquee direction="right"><b>Welcome to inet infotech official attendance marksheet.</b></marquee>
<input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for students.." title="Type Student Id here">
<div id="table-container">
<table id="tab">
<thead>
<tr>
<th width="35%">Student Id</th>
<th width="20%">Name</th>
<th width="25%">Arrival Time</th>

</tr>
</thead>
<tbody>

<?php
if (! empty($productResult)) {
foreach ($productResult as $key => $value) {
?>

<tr>
<td><?php echo $productResult[$key]["S_id"]; ?></td>
<td><?php echo $productResult[$key]["Name"]; ?></td>
<td><?php echo $productResult[$key]["ARRTIME"]; ?></td>


</tr>
<?php
}
}
?>
</tbody>
</table>

<div class="btn">
<form action="" method="post">
<button type="submit" id="btnExport"
name='export' value="Export to Excel"
class="btn btn-info">Export to Excel</button>
</form>
</div>
</div>


<script>
function myFunction() {
var input, filter, table, tr, td, i, txtValue;
input = document.getElementById("myInput");
filter = input.value.toUpperCase();
table = document.getElementById("tab");
tr = table.getElementsByTagName("tr");
for (i = 0; i < tr.length; i++) {
td = tr[i].getElementsByTagName("td")[0];
if (td) {
txtValue = td.textContent || td.innerText;
if (txtValue.toUpperCase().indexOf(filter) > -1) {
tr[i].style.display = "";
} else {
tr[i].style.display = "none";
}
}
}
}
</script>
</body>
</html>