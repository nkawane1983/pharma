<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%-- <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<spring:url value="/resources/images" var="images" />

<header class="header">
	<div class="header-block header-block-collapse hidden-lg-up">
		<button class="collapse-btn" id="sidebar-collapse-btn">
			<i class="fa fa-bars"></i>
		</button>
	</div>
	<div class="header-block header-block-search hidden-sm-down">
		<form role="search">
			<div class="input-container">
				<i class="fa fa-search"></i> <input type="search"
					placeholder="Search">
				<div class="underline"></div>
			</div>
		</form>
	</div>
	<div class="header-block header-block-buttons">
		<span style="color: #33aec9;"> <c:if
				test="${not empty successMessage}">
				${successMessage}
			</c:if>
		</span> <span style="color: red;"> <c:if
				test="${not empty errorMessage}">
				${errorMessage}
			</c:if>
		</span>

		<!-- 		<a -->
		<!-- 			href="https://github.com/modularcode/modular-admin-html/stargazers" -->
		<!-- 			class="btn btn-sm header-btn"> <i class="fa fa-star"></i> <span>Star -->
		<!-- 				Us</span> -->
		<!-- 		</a> <a -->
		<!-- 			href="https://github.com/modularcode/modular-admin-html/releases/download/v1.0.1/modular-admin-html-1.0.1.zip" -->
		<!-- 			class="btn btn-sm header-btn"> <i class="fa fa-cloud-download"></i> -->
		<!-- 			<span>Download .zip</span> -->
		<!-- 		</a> -->
	</div>
	
	<div class="header-block header-block-nav">
		<ul class="nav-profile">
			<li class="notifications new"><a href="" data-toggle="dropdown">
					<i class="fa fa-bell-o"></i> <sup> <span class="counter">8</span>
				</sup>
			</a>
				<div class="dropdown-menu notifications-dropdown-menu">
					<ul class="notifications-container">
						<li><a href="" class="notification-item">
								<div class="body-col">
									<p>
										<span class="accent">Zack Alien</span> pushed new commit: <span
											class="accent">Fix page load performance issue</span>.
									</p>
								</div>
						</a></li>
						<li><a href="" class="notification-item">
								<div class="body-col">
									<p>
										<span class="accent">Amaya Hatsumi</span> started new task: <span
											class="accent">Dashboard UI design.</span>.
									</p>
								</div>
						</a></li>
						<li><a href="" class="notification-item">
								<div class="body-col">
									<p>
										<span class="accent">Andy Nouman</span> deployed new version
										of <span class="accent">NodeJS REST Api V3</span>
									</p>
								</div>
						</a></li>
					</ul>
					<footer>
						<ul>
							<li><a href=""> View All </a></li>
						</ul>
					</footer>
				</div></li>
			<li class="profile dropdown"><a class="nav-link dropdown-toggle"
				data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
				aria-expanded="false"> <span class="name">
						${userInfo.usrDisplayName} </span> <span style="color: #52BCD3;"><%=session.getAttribute("workingOn")%></span></a>
				<div class="dropdown-menu profile-dropdown-menu"
					aria-labelledby="dropdownMenu1">
					<a class="dropdown-item"
						href="${pageContext.request.contextPath}/user/userProfile.do">
						<i class="fa fa-user icon"></i> Profile
					</a> <a class="dropdown-item" href="#"> <i class="fa fa-bell icon"></i>
						Notifications
					</a>
				
					<!--<sec:authorize access="hasRole('ROLE_SuperAdmin')">
						<a class="dropdown-item"
							href="${pageContext.request.contextPath}/settingPg.do"> <i
							class="fa fa-gear icon"></i> Settings
						</a>
					</sec:authorize>-->
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
	<input type="text" id="url" value="${url}" />
</c:if>
<c:if test="${empty alertMsg}">
	<input type="hidden" id="alertMsg" value="0" />
</c:if>
<c:if test="${not empty alertMsg1}">
	<input type="hidden" id="alertMsg1" value="${alertMsg1}" />
</c:if>
<c:if test="${empty alertMsg1}">
	<input type="hidden" id="alertMsg1" value="0" />
</c:if>
<!-- ------------------------------------------------------- -->
<!-- ---------------Manager login - Model------------------------- -->
<!-- ------------------------------------------------------- -->
<input type="hidden" name="contextPath" class="form-control"
	id="contextPath" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="manager-modal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog" style="max-width: 465px; margin: 125px auto;">
		<div class="modal-content">

			<div class="modal-body" style="padding: 0px; height: 381px;">
				<div id="manager"></div>
			</div>
		</div>
	</div>
</div>
<script>
	$(document)
			.ready(
					function() {
						if ($("#alertMsg").val() != 0) {
							alert($("#alertMsg").val());
							window.location.href = "${pageContext.request.contextPath}/"
									+ $('#url').val();
						}
						if ($("#alertMsg1").val() != 0) {
							alert($("#alertMsg1").val());
							openManagerauth();

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
			$("#username").val(" ");
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
								$("#username").val(" ");
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
								if ($("#alertMsg1").val() != 0) {
									window.location.href = "${pageContext.request.contextPath}/monthClosurePg.do";
								}
							}

						}
					});
		}
	}
</script>