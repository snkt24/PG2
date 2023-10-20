<!doctype html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<h1>Pressure Gauge Data</h1>
		<a href="/getdatafromserialport">Get Data</a>
		<table class="table">
			<thead>
				<tr>
					<th scope="col">InstrumentId</th>
					<th scope="col">Value</th>
					<th scope="col">Time</th>
				</tr>
			</thead>
			<tbody>
				<#list dataList as data>
				<tr>
					<th scope="row">${data.instrumentID}</th>
					<td>${data.value}</td>
					<td>${data.time}</td>
				</tr>
				</#list>
			</tbody>
		</table>
	</div>
</body>
</html>