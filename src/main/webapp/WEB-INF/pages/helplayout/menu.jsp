<%@page import="org.apache.jasper.tagplugins.jstl.core.Out"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>

<style>
.app .content {
	padding: 81px 10px 47px 117px;
}

#menu {
	background-color: #3a4651;
	border-top: 2px solid #52BCD3 !important;
	height: 490px;
	overflow: auto;
	border-bottom: 2px solid #52BCD3 !important;
}

.nav-tabs {
	border-bottom: none;
}

#accordion {
	
}

.sidebar {
	width: 340px;
}

.header, .footer {
	left: 340px;
}

.list-group-item {
	padding: 0.2rem 1.25rem;
	border: 0px;
	background-color: #3a4651;
}

.card-block {
	padding: 9px;
}
</style>
<aside class="sidebar">
	<div class="sidebar-container">
		<div class="sidebar-header">
			<div class="brand">
				<div class="logo">
					<span class="l l1"></span> <span class="l l2"></span> <span
						class="l l3"></span> <span class="l l4"></span> <span class="l l5"></span>
				</div>
				Pharma @dmin
			</div>
		</div>
		<ul class="nav nav-tabs" role="tablist">
			<li class="active nav-item" id="liaccordion"><a
				data-toggle="tab" href="#accordion"
				style="border-top-left-radius: 12px; border-top-right-radius: 12px;"><i
					class="fa fa-list"></i>Menu</a></li>
			<li class="nav-item" style="margin-left: 10px;" id="liSearch"><a
				data-toggle="tab" href="#Search"
				style="border-top-left-radius: 12px; border-top-right-radius: 12px;"><i
					class="fa fa-search"></i>Search</a></li>
		</ul>
		<nav class="menu" id="menu">

			<div class="tab-content">
				<c:forEach items="${userguides}" var="userguides"> ${userguides[7]}
			  </c:forEach>

				<div id="Search" class="tab-pane fade">
					<input type="text">
				</div>
			</div>

		</nav>
	</div>
</aside>
<script>
	$(document).ready(function() {

		$('li a').click(function() {

			$('ul li').removeClass("active");
			$(this).parent().get(1).addClass("Active");
		})
	});
	function onchangeclass(str) {
		var icon = $(str).find("#icn");
		var ficn = $(str).find("#ficn");
		var temp = str.href.split("#")[1];

		if (icon.hasClass("fa fa-plus-square-o")) {
			icon.addClass("fa-minus-square-o").removeClass("fa-plus-square-o");
		} else {
			icon.addClass("fa-plus-square-o").removeClass("fa-minus-square-o");
		}
		if (ficn.hasClass("fa fa-folder-o")) {
			ficn.addClass("fa-folder-open-o").removeClass("fa-folder-o");
		} else {
			ficn.addClass("fa-folder-o").removeClass("fa-folder-open-o");
		}
		$('.section').attr("hidden", "true");
		$('#' + temp + '1').removeAttr("hidden");

	}
	function appendValue() {

	}
</script>