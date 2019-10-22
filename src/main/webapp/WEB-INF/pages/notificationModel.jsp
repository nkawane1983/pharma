<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<c:if test="${not empty notifications }">
		<input type="hidden" id="notification" value="${fn:length(notifications)}">
	</c:if>
	<section>
		<!-- ------------------------------------------------------- -->
		<!-- ---------------Notification - Model------------------------- -->
		<!-- ------------------------------------------------------- -->
		<div class="modal fade" id="notification-modal" tabindex="-1"
			role="dialog" aria-labelledby="basicModal" aria-hidden="true">

			<div class="modal-dialog"
				style="max-width: 465px; margin: 222px auto;">
				<div class="modal-content">
					<div class="modal-header" style="padding: 10px;">
						<a type="button" class="close" data-dismiss="modal"
							aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
						</a>
						<h6 class="modal-title" id="myModalLabel"
							style="font-family: Tahoma, Geneva, sans-serif">
							<strong>Notification</strong>
						</h6>
					</div>
					<div class="modal-body" style="padding: 0px; height: 90px;">
						<div class="col-md-12" style="padding-top: 10px;">
							<div class="form-group">
							<sec:authorize
			access="hasAnyRole('ROLE_Supervisor','ROLE_Users','ROLE_Managers')">
								<p>
									<strong>Please Check ${fn:length(notifications)}
										Incomplete Till</strong>
								</p>
								</sec:authorize>
								<sec:authorize
							access="hasAnyRole('ROLE_SuperAdmin','ROLE_Admin')">
								<p>
									<strong>Please Check ${fn:length(notifications)}
										Amendment Till Request </strong>
								</p>
								</sec:authorize>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group pull-right">
								<a type="button" class="btn btn-primary"
									href="${pageContext.request.contextPath}/notificationsPg.do"
									tabindex="2">OK</a> <a type="button" class="btn btn-primary"
									data-dismiss="modal" aria-hidden="true" tabindex="3">Close</a>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</section>