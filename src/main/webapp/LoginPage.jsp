<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="css/dashb1.css">
<link rel="stylesheet" href="formati.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="assets/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="assets/css/main.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<%@page errorPage="ErrorPage.jsp" %>
<title>Insert title here</title>
</head>
<body>
	<div class="bg6">
		<h2 align="center" style="color: #f7f5f3">Welcome to</h2>
		<h1 align="center" style="color: #f6f6f6">SPJ Co-operative Bank</h1>
	</div>
	<div class="bg5">
		<div class="absolut">
			<form action="login" method="post" class="w3-container">
				<table>
					<tr>
						<td colspan="2">
							<div class="w3-container w3-blue">
								<h2>Login Form</h2>
							</div>
						</td>
					</tr>
					<tr>
						<td>Enter User Name:</td>
						<td><input class="w3-input" type="text" name="uname"></td>
					</tr>
					<tr>
						<td>Enter Password:</td>
						<td><input class="w3-input" type="password" name="pass"></td>
					</tr>

					<tr>
						<td><input class="button button3" type="submit"
							value="Submit"></td>
						<td><input class="button button3" type="reset" value="Reset"></td>
					</tr>
					
				</table>
			</form>
			<form action=forgotsomething method="post"  class="w3-container"><input type="submit" value="forgot_Something?"></form>
		</div>
	</div>


</body>
</html>