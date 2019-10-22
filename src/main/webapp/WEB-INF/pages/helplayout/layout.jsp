<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>

<meta name="description" content="" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="-1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Script-Type" content="text/javascript" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">



<spring:url value="/resources/css/datepicker.min.css" var="datepickerCss" />
<link rel="stylesheet" href="${datepickerCss}">

<spring:url value="/resources/css/vendor.css" var="vendorCss" />
<link rel="stylesheet" href="${vendorCss}">

<spring:url value="/resources/css/app-blue.css" var="appCss" />
<link rel="stylesheet" href="${appCss}">



<!-- Font Awesome -->
<spring:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css" var="fontAwesomeCss" />
<link rel="stylesheet" href="${fontAwesomeCss}">

<spring:url value="/resources/js/vendor.js" var="vendorJs" />
<script type="text/javascript" src="${vendorJs}"></script>

<spring:url value="/resources/js/app.js" var="appJs" />
<script type="text/javascript" src="${appJs}"></script>



<spring:url value="/resources/css/displaytag.css" var="displaytagCss" />
<link href="${displaytagCss}" rel="stylesheet" />

<spring:url value="/resources/js/jsUtility.js" var="jsUtility" />
	<script src="${jsUtility}"></script>

<spring:url value="/resources/js/angular.min.js" var="angularJs" />
<script src="${angularJs}"></script>

<spring:url value="/resources/js/bootstrap-datepicker.min.js" var="bootstrapdatepickerJs" />
<script src="${bootstrapdatepickerJs}"></script>


<!--[if lt IE 9]> <script src="js/ie/respond.min.js" cache="false"></script> <script src="js/ie/html5.js" cache="false"></script> <script src="js/ie/fix.js" cache="false"></script> <![endif]-->
<SCRIPT type="text/javascript">
   /* window.history.forward();
    function noBack() { window.history.forward(); }*/
</SCRIPT>

<style>
.error {
	color: #ff0000;
	font-weight: bold;
}
</style>

</head>


<!-- <body class="hold-transition skin-blue sidebar-mini" onload="noBack();" -->
<!-- 	onpageshow="if (event.persisted) noBack();" onunload=""> -->

<body >
	<div class="main-wrapper">
		<div class="app header-fixed sidebar-fixed" id="app">
		
			<tiles:insertAttribute name="header" />

			<tiles:insertAttribute name="menu" />
		
			<div class="sidebar-overlay" id="sidebar-overlay"></div>
			
			<tiles:insertAttribute name="body" />
			
			
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
	
	
	<div class="ref" id="ref">
		<div class="color-primary"></div>
		<div class="chart">
			<div class="color-primary"></div>
			<div class="color-secondary"></div>
		</div>
	</div>

	


</body>
</html>