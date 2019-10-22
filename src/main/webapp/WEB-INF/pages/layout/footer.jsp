<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="version" value="${sessionScope.version}" />


<footer class="footer">
<div class="footer-block buttons">
<a class="pull-left" style="text-decoration: none; font-size: 13px;">Pharm@dmin version <c:out value="${version.getVersionNo()}" /></a>
</div>
	<div class="footer-block author"> 
		<ul>
			<li style="font-size: 13px;"> &copy;&nbsp;<c:out value="${version.getVersionYear()}" /> &nbsp;<a href="http://www.easyway3e.com" tabindex="-36" style="text-decoration: none;" target="_blank">Easyway 3E Solutions Pvt. Ltd.</a> All rights reserved.
			</li>
		</ul>
	</div>
</footer>