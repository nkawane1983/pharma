<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<article class="content forms-page">
	<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="title-block">
						<h3 class="title">
							Search User <a
								href="${pageContext.request.contextPath}/user/newUser.do"
								class="btn btn-primary pull-right" data-toggle="tooltip"
								title="Add User">Add User</a>

						</h3>
					</div>
					<form:form class="form-inline" role="form"
						action="${pageContext.request.contextPath}/user/searchPg.do"
						method="GET">
						<div class="form-group">
							<label>Enter name</label> <input class="form-control"
								id="usrName" placeholder="Enter Name" type="text" name="usrName"
								autofocus="autofocus">
						</div>
						<div class="form-group">
							<label>Enter email</label> <input class="form-control"
								id="usrEmail" placeholder="Enter email" type="email"
								name="usrEmail">
						</div>
						<button type="reset" class="btn btn-success-1">Reset</button>
						<button type="submit" class="btn btn-primary" data-toggle="modal">Search</button>
					</form:form>
				</div>
			</div>
		</div>
	</section>

	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="usersList"
							requestURI="${pageContext.request.contextPath}/user/searchPg.do"
							pagesize="30" defaultsort="1">
							<display:column property="userId" title="User Id" sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrDisplayName" title="Display Name"
								sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrFirstName" title="First Name"
								sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrMiddleName" title="Middle Name"
								sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrLastName" title="Last Name"
								sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrEmail" title="Email" sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrMobile" title="Mobile Number"
								sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<display:column property="usrDesignation" title="Designation"
								sortable="true"
								href="${pageContext.request.contextPath}/user/editUser.do"
								paramId="usrId" paramProperty='userId' />
							<sec:authorize
								access="hasAnyRole('ROLE_Admin','ROLE_SuperAdmin')">
								<display:column title="" sortable="true"
									href="${pageContext.request.contextPath}/user/deactiveUser.do"
									paramId="userId" paramProperty='userId'>
									<button type="submit" class="btn btn-primary"
										style="padding: 0.09rem 0.8rem; margin-bottom: 0px">Delete</button>
								</display:column>
							</sec:authorize>
						</display:table>

					</div>
				</div>
			</div>
		</div>
	</section>
</article>

<script>
	$(function() {
		$("ul li").removeClass("active");
		$("#liAdmin").addClass("open");
		$("#liAdmin").addClass("active");
		$("#liAdmin ul").addClass("collapse in");
		$("#liUser").addClass("active");
	});
</script>