<!DOCTYPE html>
<html lang="en">
<head>
<title>Result for ${lastname}</title>
 <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container"> 
  <table class="table table-hover" align="right">
    <thead>
      <tr>
       <th>Customer ID</th>
				<th>Full Name</th>
				<th>Title</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Street Address</th>
				<th>State</th>
				<th>City</th>
				<th>Zipcode</th>
				<th>Email</th>
				<th>Position</th>
				<th>Company</th>
      </tr>
    </thead>
    <tbody>
      ${fullList}
    </tbody>
  </table>
</div>

	
<br><br>
<a href="Search.html"><b>Back</b></a>
</body>
</html>
