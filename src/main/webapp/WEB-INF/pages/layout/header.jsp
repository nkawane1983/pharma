<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<header class="header">
	<div class="header-block header-block-collapse hidden-lg-up">
		<button class="collapse-btn" id="sidebar-collapse-btn">
			<i class="fa fa-bars"></i>
		</button>
	</div>
	<div class="header-block header-block-search hidden-sm-down">
		<sec:authorize
			access="hasAnyRole('ROLE_Supervisor','ROLE_Users','ROLE_Managers')">
			<c:set var="branchDetails" value="${sessionScope.branchDetails}" />
			<div>
				<h5>
					<c:out value="${branchDetails.getDescription()}" />
					&nbsp;&nbsp;(
					<c:out value="${branchDetails.getInternalBranchId()}" />
					)
				</h5>

				<h6>
					<c:out value="${branchDetails.getAddrline1()}" />
					&nbsp;
					<c:out value="${branchDetails.getAddrline2()}" />
					&nbsp;
					<c:out value="${branchDetails.getTown()}" />
					&nbsp;,
					<c:out value="${branchDetails.getPostcode()}" />
				</h6>
			</div>
		</sec:authorize>
	</div>
	<div class="header-block header-block-nav">
		<ul class="nav-profile">
			<li class="notifications new"><a href="" data-toggle="dropdown">
					<i class="fa fa-bell-o"></i> <sup><c:if
							test="${not empty sessionScope.notificationsLength }">
							<span class="counter"><c:out value="${sessionScope.notificationsLength}" /></span>
						</c:if> </sup>
			</a>

				<div class="dropdown-menu notifications-dropdown-menu">

					<ul class="notifications-container">
						<c:if test="${not empty notifications}">
							<c:forEach items="${notifications}" var="notification">
							<c:choose>
							<c:when test="${notification[10]==-1}">
								<li><a
										class="notification-item">
										<div class="body-col">

											<p>
												<span class="accent">${notification[1]}</span> New cashing:
												<span class="accent">Requset Send Till</span>.
											</p>
										</div>
								</a></li>
								</c:when>
								<c:otherwise>
								<li><a
									href="${pageContext.request.contextPath}/cashing/editCashingPg.do?id=${notification[0]}&msg=message"
									class="notification-item">
										<div class="body-col">

											<p>
												<span class="accent">${notification[1]}</span> New cashing:
												<span class="accent">Incomplete Till</span>.
											</p>
										</div>
								</a></li>
								</c:otherwise>
								</c:choose>
							</c:forEach>
						</c:if>
					</ul>
					<footer>
						<ul>
							<li><a
								href="${pageContext.request.contextPath}/notificationsPg.do">
									View All </a></li>
						</ul>
					</footer>
				</div></li>
			<li class="profile dropdown"><a class="nav-link dropdown-toggle"
				data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
				aria-expanded="false"> <span class="name">
						${userInfo.usrDisplayName} </span> <span style="color: #52BCD3;"><c:out
							value="${sessionScope.workingOn}" /></span></a>
				<div class="dropdown-menu profile-dropdown-menu"
					aria-labelledby="dropdownMenu1">
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/user/userProfile.do">
						<i class="fa fa-user icon"></i> Profile
					</a> <a class="dropdown-item"
						href="${pageContext.request.contextPath}/notificationsPg.do">
						<i class="fa fa-bell icon"></i> Notifications
					</a>
					<sec:authorize
						access="hasAnyRole('ROLE_Supervisor','ROLE_Users','ROLE_Managers')">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/improtFilePg.do"> <i
							class="fa fa-file-o icon"></i> Import File
						</a>
					</sec:authorize>
					<sec:authorize
						access="hasAnyRole('ROLE_Supervisor','ROLE_Users','ROLE_Managers')">
						<c:if test="${MsgextendOrNot=='false'}">
							<a class="dropdown-item" href="javascript:endMonthLink();"> <i
								class="fa fa-times-circle-o"></i> End Of Month
							</a>
						</c:if>
					</sec:authorize>

					<sec:authorize access="hasRole('ROLE_SuperAdmin')">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/settingPg.do"> <i
							class="fa fa-gear icon"></i> Settings
						</a>
					</sec:authorize>

					<div class="dropdown-divider"></div>
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/helpPg.do"> <i
						class="fa fa-question icon"></i> Help
					</a>

					<div class="dropdown-divider"></div>
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/logout.do"> <i
						class="fa fa-power-off icon"></i> Logout
					</a>
				</div></li>
		</ul>
	</div>

</header>

<c:if test="${not empty alertMsg}">
	<input type="hidden" id="alertMsg" value="${alertMsg}" />
</c:if>
<c:if test="${empty alertMsg}">
	<input type="hidden" id="alertMsg" value="0" />
</c:if>
<c:if test="${not empty alertMsgForMonthEnd}">
	<input type="hidden" id="alertMsgForMonthEnd"
		value="${alertMsgForMonthEnd}" />
</c:if>
<c:if test="${empty alertMsgForMonthEnd}">
	<input type="hidden" id="alertMsgForMonthEnd" value="0" />
</c:if>
<c:if test="${not empty MsgextendOrNot}">
	<input type="hidden" id="MsgextendOrNot"
		value='<c:out value="${MsgextendOrNot}"/>' />
</c:if>
<!-- ------------------------------------------------------- -->
<!-- ---------------Managerlogin - Model------------------------- -->
<!-- ------------------------------------------------------- -->
<input type="hidden" name="contextPath" class="form-control"
	id="contextPath" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="manager-modal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog" style="max-width: 465px; margin: 125px auto;">
		<div class="card loginm">

			<div class="modal-body " style="padding: 0px; height: 381px;">
				<div id="manager"></div>
			</div>
		</div>
	</div>
</div>
<style>
.loginm {
	border: 1px solid #999;
	border: inset 1px solid #333;
	-webkit-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	-moz-box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	box-shadow: 0px 0px 8px rgba(0, 0, 0, 0.3);
	border-radius: 10px;
	font-family: Tahoma, Geneva, sans-serif;
}
</style>
<script>
	$(document)
			.ready(
					function() {
						if ($("#alertMsg").val() != 0) {
							alert($("#alertMsg").val());
						}
						if ($("#alertMsgForMonthEnd").val() != 0) {
							if ($("#MsgextendOrNot").val() == 'true') {
								alert($("#alertMsgForMonthEnd").val());
								openManagerauth();
							} else {
								if (confirm($("#alertMsgForMonthEnd").val()) == true) {
									openManagerauth();
								} else {
									window.location = "${pageContext.request.contextPath}/extendDayPg.do"
								}
							}

						}
						
					});
	function openManagerauth() {

		$.ajax({
			url : "${pageContext.request.contextPath}/managersAuth.do",
			type : 'post',
			success : function(data) {

				$('#manager-modal').modal({
					backdrop : 'static',
					keyboard : false
				// to prevent closing with Esc button (if you want this too)
				})

				$('#manager').html(data);

			}
		});
	}
	function openManagerauthDo() {
		if ($("#username").val() == '' || $("#password").val() == '') {
			alert("Invalid Username and Password.");
			$("#username").val("");
			$("#password").val("");
		} else {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/managersLogin.do",
						type : 'post',
						data : {
							username : $("#username").val(),
							password : $("#password").val()
						},
						success : function(data) {
							if (data == "error") {
								alert("Invalid Username and Password.");
								$("#username").val("");
								$("#password").val("");
							} else {

								$('#manager-modal').modal('hide');
								if ($("#both").val() == "fault") {
									$('#note-modal').modal({
										backdrop : 'static',
										keyboard : false
									// to prevent closing with Esc button (if you want this too)
									});
									$("#managername").val($("#username").val());
								}
								if ($("#both").val() == "Discrepancy") {
									$('#Discrepancy-modal').modal({
										backdrop : 'static',
										keyboard : false
									// to prevent closing with Esc button (if you want this too)
									});
									$("#managername").val($("#username").val());
								}
								if ($("#both").val() == "Updatecashing") {
									$('#Updatecashing-modal').modal({
										backdrop : 'static',
										keyboard : false
									// to prevent closing with Esc button (if you want this too)
									});
									$("#managername").val($("#username").val());
								}
								if ($("#both").val() == "amendmentTill") {
									$('#SendReqToHo-modal').modal({
										backdrop : 'static',
										keyboard : false
									// to prevent closing with Esc button (if you want this too)
									});
									$("#managername").val($("#username").val());
								}
								if ($("#alertMsgForMonthEnd").val() != 0) {
									window.location.href = "${pageContext.request.contextPath}/monthClosurePg.do";
								}
							}

						}
					});
			return false;
		}
	}

	function endMonthLink() {
		$("#alertMsgForMonthEnd").val(
				"Are you sure End Of Month procedure has been run ?")

		if (confirm($("#alertMsgForMonthEnd").val()) == true) {
			openManagerauth();
		}

	}
	function amendmentTill() {
		$("#both").val("amendmentTill");
		$("#alertMsg").val(
				"Are you sure  send request to H.O. ?")

		if (confirm($("#alertMsg").val()) == true) {
			openManagerauth();
		}

	}
</script>