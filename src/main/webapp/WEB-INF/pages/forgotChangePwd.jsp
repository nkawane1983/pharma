
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">
<head>

<title><spring:message code="form.login.title" /></title>

<meta charset="utf-8" />
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<spring:url value="/resources/css/vendor.min.css" var="vendorCss" />
<link rel="stylesheet" href="${vendorCss}">

<spring:url value="/resources/css/app-blue.min.css" var="appCss" />
<link rel="stylesheet" href="${appCss}">

<spring:url value="/resources/images" var="images" />
<link href="${images}/favicon.ico" rel="icon" type="image/x-icon" />
<link href="${images}/favicon.ico" rel="shortcut icon"
	type="image/x-icon" />
<style type="text/css">
.auth {
	background-color: transparent;
}

.card {
	border: 1px solid #999;
	border: inset 1px solid #333;
	-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	border-radius: 10px;
	font-family: Tahoma, Geneva, sans-serif;
}
</style>
<!--[if lt IE 9]> <script src="js/ie/respond.min.js" cache="false"></script> <script src="js/ie/html5.js" cache="false"></script> <script src="js/ie/fix.js" cache="false"></script> <![endif]-->
</head>

<body>
	<div class="auth">
		<div class="auth-container">
			<div class="card">
				<header class="auth-header">
					<h1 class="auth-title">
						<div class="logo">
							<span class="l l1"></span> <span class="l l2"></span> <span
								class="l l3"></span> <span class="l l4"></span> <span
								class="l l5"></span>
						</div>
						Pharm@dmin
					</h1>

				</header>
				<div class="auth-content">


					<div style="display: none" id="alerErrortDivId"
						class="alert alert-danger col-sm-12"></div>

					<label id="pwdMsg" class="alert-danger"></label> <br />
					<c:url var="forgotUrl" value="/changePassword" />

					<form:form action="${forgotUrl}" method="post"
						class="form-horizontal" role="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />
						<input type="hidden" name="usrId" value="${usrId}">
						<div class="form-group">
							<label for="username">New Password</label> <input type="password"
								autofocus="autofocus" class="form-control underlined"
								name="newpwd" id="newpwd" placeholder="Enter New Password"
								required>
						</div>
						<div class="form-group">
							<label for="password">Confirm Password</label> <input
								type="password" class="form-control underlined" name="confpwd"
								id="confpwd" placeholder="Enter Confirm Password"
								onchange="checkPwd(this)" onchange="blankTextBox('pwdMsg')"
								required>
						</div>

						<div class="form-group">
							<input type="submit" class="btn btn-block btn-primary"
								value="Change Password" />
						</div>
						<div class="form-group clearfix">
							<a class="pull-right" href="login.do">Sign IN!</a>
						</div>

						<hr>

					</form:form>


				</div>
	
			</div>
		</div>
	</div>
	<!-- Reference block for JS -->
	<div class="ref" id="ref">
		<div class="color-primary"></div>
		<div class="chart">
			<div class="color-primary"></div>
			<div class="color-secondary"></div>
		</div>
	</div>
	

	<script type="text/javascript">
	
		function checkPwd(id) {
			var opwd = document.getElementById("newpwd").value;
			if (id.value != opwd) {
				document.getElementById("pwdMsg").innerHTML = "Your new password and retype password do not match.";
				$('input[type="submit"]').prop('disabled', true);
			} else {
				document.getElementById("pwdMsg").innerHTML = "";
				$('input[type="submit"]').prop('disabled', false);
			}
		}

		function blankTextBox(id) {
			document.getElementById(id).innerHTML = "";
		}
	</script>
</body>
</html>