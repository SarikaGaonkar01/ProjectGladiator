<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@page errorPage="ErrorPage.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description"
	content="Loanplus - Loan Company HTML Template, Credit Website Template.">
<meta name="keywords"
	content="Home Loan Template, Bootstrap Template, Loan Product, Personal Loan">
<link rel="icon" href="asset/img/favicon.png" type="image/gif">
<title>SVC Bank</title>
<!-- Bootstrap CSS -->
<link rel="stylesheet" type="text/css"
	href="asset/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="asset/css/fontawesome-all.min.css">
<link rel="stylesheet" type="text/css" href="asset/css/reset.css">
<link rel="stylesheet" type="text/css" href="asset/css/style.css">
<link rel="stylesheet" type="text/css" href="asset/css/responsive.css">

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
<style>
.button3 {
	border-radius: 8px;
	width: 140px;
	height: 48px;
}

.button4 {
	border-radius: 8px;
	width: 140px;
	height: 20px;
}

div.abso {
	position: relative;
	top: 40px;
	left: 500px;
}

.bg3 {
	/* The image used */
	background-image: url("asset/img/titlebk3.jpg");
	/* Full height */
	height: 100%;
	/* Center and scale the image nicely */
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
</head>
<body class="js">
	<div style="background-color: rgb(178, 206, 250)">
		<div class="container">
			<div class="row">
				<div class="col-md-4 text-left">
					<div class="sitelogo">
						<a href="index-2.jsp"><img src="asset/img/BankLogo1.png"
							alt=""></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="preloader"></div>
	<!-- start header area -->

	<div class="menu-area">
		<div class="container">
			<div class="row">
				<div class="col-md-11 col-lg-11">
					<div id="cssmenu">
						<ul>
							<li class="current-menu-item"><a href="index-2.jsp">Home</a></li>
							<li><a href="about.jsp">About us </a></li>
							<li><a href="contact.jsp">Feedback</a></li>
							<li><a href="howdoi.jsp">How Do I</a></li>
							<li><a href="impnotes.jsp">Importance</a></li>
							<li><a href="registration.jsp">Registration</a></li>
							<li><a href="login.jsp">Login</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="bg3">
		<div class="abso">
			<form action="transferAmount" method="post">
				<table>
					<tr>

						<td colspan="2" bgcolor="#80002a">
							<h1 style="color: #e0e0eb" align="center">Transfer Money</h1>
						</td>
					</tr>
					<tr>
						<td colspan="2"><br></td>
					</tr>


					<tr>
						<td align="right">Select Payee:&nbsp;</td>
						<td><div class="input-group">
								<span class="input-group-addon"><i class="fa fa-building"
									aria-hidden="true"></i></span> <select class="form-control"
									name="ga_receiver_accno">
									<c:forEach items="${list}" var="list">
										<option value="${list.ga_payee_accno}">${list.ga_payee_name}</option>
									</c:forEach>
								</select>
							</div></td>

					</tr>
					<tr>
						<td colspan="2"><br></td>
					</tr>
					<tr>
						<td align="right">Enter Amount to be Transfered:&nbsp;</td>
						<td><div class="input-group">
								<span class="input-group-addon"><i class="fa fa-building"
									aria-hidden="true"></i></span> <input type="text"
									name="ga_transfer_amount" class="form-control"
									placeholder="Enter Amount" required
									title="Amount is required" min="1" />
							</div></td>

					</tr>
					<tr>
						<td colspan="2"><br></td>
					</tr>
					<tr>
						<td align="right">Transfer Mode:&nbsp;</td>
						<td><div class="input-group">
								<span class="input-group-addon"><i class="fa fa-building"
									aria-hidden="true"></i></span> <select class="form-control"
									name="ga_transfer_mode">
									<option value="neft">Neft Transfer</option>
									<option value="rtgs">RTGS Transfer</option>
									<option value="imps">IMPS Transfer</option>
								</select>
							</div></td>

					</tr>
					<tr>
						<td colspan="2"><br></td>
					</tr>
					<tr>
						<td align="right">Enter Remarks:&nbsp;</td>
						<td><div class="input-group">
								<span class="input-group-addon"><i class="fa fa-building"
									aria-hidden="true"></i></span> <input type="text" name="ga_payee_remarks"
									class="form-control" placeholder="Enter Remarks"
									required title="Remarks is required" />
							</div></td>
					</tr>
					<tr>
						<td colspan="2"><br></td>
					</tr>
					<tr>
						<td><input class="button btn btn-default button3"
							type="submit" value="Submit" id="submit" /></td>
						<td align="center"><input
							class="button btn btn-default button3" type="reset" value="Reset" /></td>
					</tr>
					<tr>
						<td colspan="2"><br></td>
					</tr>
				</table>
			</form>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
	</div>
	<div class="copy-right-section">
		<div class="container">
			<div class="row">
				<div class="col-md-6 text-left">
					<div class="copyright-text">
						<p>
							<i class="fa fa-copyright"></i> 2018 SVC Bank. All Rights
							Reserved
						</p>
					</div>
				</div>
				<div class="col-md-6 text-right">
					<div class="footer-nave">
						<a href="#"><i class="fa fa-facebook-square"></i></a> <a href="#"><i
							class="fa fa-twitter-square"></i></a> <a href="#"><i
							class="fa fa-linkedin-square"></i></a> <a href="#"><i
							class="fa fa-pinterest-square"></i></a> <a href="#"><i
							class="fa fa-vimeo-square"></i></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of footer area -->
	<!-- Optional JavaScript -->
	<script src="asset/js/jquery-3.3.1.min.js"></script>
	<script
		src="../../../../cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.min.js"></script>
	<script src="asset/js/popper.min.js"></script>
	<script src="asset/js/bootstrap.min.js"></script>
	<script src="asset/js/jquery.nice-select.js"></script>
	<script src="asset/js/menumaker.js"></script>
	<script src="asset/js/owl.carousel.min.js"></script>
	<script src="asset/js/slider.js"></script>
	<script src="asset/js/active.js"></script>
</body>
</html>