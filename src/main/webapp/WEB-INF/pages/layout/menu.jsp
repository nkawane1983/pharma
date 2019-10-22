<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<aside class="sidebar">
	<div class="sidebar-container">
		<div class="sidebar-header">
			<div class="brand">
				<div class="logo">
					<span class="l l1"></span> <span class="l l2"></span> <span
						class="l l3"></span> <span class="l l4"></span> <span class="l l5"></span>
				</div>
				Pharm@dmin
			</div>
		</div>
		<nav class="menu">
			<ul class="nav metismenu" id="sidebar-menu">
				<li class="active"><a
					href="${pageContext.request.contextPath}/dashboard"><i
						class="fa fa-tachometer"></i> Dashboard</a></li>

				<c:forEach items="${menuList}" var="menulist">
					<c:if test="${menulist.parentKey=='-'}">
						<li id="li${menulist.menuElementName}"><a
							href="${pageContext.request.contextPath}${menulist.menuElementURL}">
								${menulist.menuIcon}&nbsp;${menulist.displayMenuName} <c:if test="${empty menulist.menuElementURL}">
									<i class="fa arrow"></i>
								</c:if>
						</a> <c:forEach items="${menuList}" var="submenuList">
								<c:set var="menuid">${menulist.id}</c:set>
								<c:if test="${menuid==submenuList.parentKey}">

									<ul class="metismenu">
										<li id="li${submenuList.menuElementName}"><a
											href="${pageContext.request.contextPath}${submenuList.menuElementURL}">
												${submenuList.menuIcon}&nbsp;${submenuList.displayMenuName} </a></li>
									</ul>
								</c:if>
							</c:forEach></li>
					</c:if>
				</c:forEach>
			</ul>
		</nav>
	</div>

</aside>

