<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
#ft {
	padding-top: 15px;
}

.form-control {
	line-height: 0.0;
	padding: 0.225rem;
}

input {
	text-align: right;
	padding: 0.3rem;
}

.subtitle-block {
	padding-bottom: 5px;
	margin-bottom: 5px;
	border-bottom: 1px solid #e0e5ea
}

.btn {
	margin-bottom: 5px;
	line-height: 1;
}

.table th, .table td {
	padding: 0.25rem
}

#pharmtbody tr td {
	margin-bottom: 10px;
}
</style>
<article class="content dashboard-page" id="top">

	<c:forEach items="${userguides}" var="userguides"> ${userguides[2]}
			  </c:forEach>



</article>
