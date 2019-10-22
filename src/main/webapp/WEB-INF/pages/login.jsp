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
<%-- <link href="${images}/favicon.ico" rel="shortcut icon" type="image/x-icon" /> --%>


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
					<c:set var="displayDiv" value="none" />
					<c:if test="${not empty message}">
						<c:set var="displayDiv" value="block" />
					</c:if>
					<p class="text-xs-center">LOGIN TO CONTINUE</p>


					<c:url var="loginUrl" value="/login" />
					<form:form action="${loginUrl}" method="post" id="loginform"
						class="form-horizontal" role="form">
						<input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" />

						<c:if test="${param.error != null}">
							<div class="alert alert-error" id="displayError"
								style="color: red; font-family: sans-serif; font-size: medium; font-weight: bold;"
								align="center">
								Failed to login.<br>
							</div>
						</c:if>

						<c:if test="${not empty message}">
							<div class="alert alert-error" id="displayError"
								style="color: red; font-family: sans-serif; font-size: medium; font-weight: bold;"
								align="center">
								${message}<br>
							</div>
						</c:if>

						<div class="form-group">
							<label for="username">User name/Email id</label> <input type="text"
								autofocus="autofocus" class="form-control underlined"
								name="username" id="username" placeholder="Your user name or Your Email id"
								required>
						</div>
						<div class="form-group">
							<label for="password">Password</label> <input type="password"
								class="form-control underlined" name="password" id="password"
								placeholder="Your password" required>
						</div>
						<div class="form-group">
							<label for="remember"> <input id="remember"
								type="checkbox" style="margin: 0 0 0 4px" name="remember-me">
								<span>Remember me</span>
							</label>

							<c:url var="forgotUrl" value="/forgotPwdPg" />
							<a href="${forgotUrl}" class="forgot-btn pull-right">Forgot
								password?</a>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-block btn-primary">Login</button>
						</div>
						<!-- 	<div class="form-group">
							<p class="text-muted text-xs-center">
								Do not have an account? <a href="signup.html">Sign Up!</a>
							</p>
						</div> -->
					</form:form>
				</div>
			</div>
			<!-- 			<div class="text-xs-center"> -->
			<!-- 				<a href="index.html" class="btn btn-secondary rounded btn-sm"> <i -->
			<!-- 					class="fa fa-arrow-left"></i>  -->
			<!-- 				</a> -->
			<!-- 			</div> -->
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
	

<%-- 	<spring:url value="/resources/js/vendor.js" var="vendorJs" /> --%>
<%-- 	<script type="text/javascript" src="${vendorJs}"></script> --%>

<%-- 	<spring:url value="/resources/js/app.js" var="appJs" /> --%>
<%-- 	<script type="text/javascript" src="${appJs}"></script> --%>
<%-- 	<spring:url value="/resources/js/jquery.backstretch.min.js" --%>
<%-- 		var="jquerybackstretchJs" /> --%>
<%-- 	<script type="text/javascript" src="${jquerybackstretchJs}"></script> --%>

	<script type="text/javascript">
		/*$.backstretch("${images}/pharmaLogin11.jpg", {
			speed : 350
		});*/
	</script>
</body>
</html>