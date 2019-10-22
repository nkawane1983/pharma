<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<article class="content forms-page">
	<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<c:choose>
						<c:when
							test="${not empty appUser && not empty appUser.getUsrId() && not empty mode}">
							<c:set var="userId" value="${appUser.getUsrId()}"></c:set>
						</c:when>
					</c:choose>

					<form:form modelAttribute="appUser" method="POST"
						action="${pageContext.request.contextPath}/user/manageUser.do">

						<div class="subtitle-block">
							<h3 class="subtitle">Login Information</h3>
						</div>

						<input type="hidden" name="msg" value="${msg}" id="msg">

						<div class="col-md-12">
							<div class="row form-group">
								<c:choose>
									<c:when
										test="${not empty appUser && not empty appUser.getUsrId()
													&& not empty mode}">
										<div class="col-xs-3">
											<form:label path="usrId">User Id</form:label>
											<label class="text-danger">*</label>
											<form:input path="usrId" class="form-control"
												placeholder="User Id" disabled="true" required="required"
												maxlength="30" />
											<form:hidden path="usrId" />
											<form:errors path="usrId" cssClass="error" />
										</div>
										<div class="col-xs-3">
											<form:label path="usrName">User Name</form:label>
											<label class="text-danger">*</label>
											<form:input path="usrName" class="form-control"
												placeholder="User Name" disabled="true" required="required"
												maxlength="30" />
											<form:hidden path="usrName" />
											<form:errors path="usrName" cssClass="error" />
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-xs-3">
											<form:label path="usrId">User Id</form:label>
											<label class="text-danger">*</label>
											<form:input path="usrId" class="form-control"
												placeholder="User Id" required="required" maxlength="30" />
											<form:errors path="usrId" cssClass="error" />

										</div>
										<div class="col-xs-3">
											<form:label path="usrName">User Name</form:label>
											<label class="text-danger">*</label>
											<form:input path="usrName" class="form-control"
												placeholder="User Name" required="required" maxlength="30" />
											<form:errors path="usrName" cssClass="error" />
										</div>
										<div class="col-xs-3">
											<form:label path="usrPasswd">Password</form:label>
											<label class="text-danger">*</label>
											<form:password path="usrPasswd" class="form-control"
												maxlength="255" placeholder="Password" id="usrPwd"
												required="required" />
											<form:errors path="usrPasswd" cssClass="error" />
										</div>

										<div class="col-xs-3">
											<label>Re-type Password</label> <input type="password"
												name="reTypePwd" id="reTypePwd" class="form-control"
												onchange="checkPwd(this)" required="required"> <label
												id="pwdMsg" class="error">${pwdMsg}</label>
										</div>
									</c:otherwise>
								</c:choose>

							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-xs-4">
									<form:label path="usrType">User Type</form:label>

									<form:select path="usrType" class="form-control" id="usrType"
										onchange="getUsertype(this)" required="required">
										<form:option value="0" label="--- Select ---" />
										<c:forEach items="${userTypeList}" var="userTypeList">
											<sec:authorize access="hasRole('ROLE_Admin')">
												<c:choose>
													<c:when test="${userTypeList.name=='SuperAdmin'}">
													</c:when>

													<c:otherwise>
														<form:option value="${userTypeList.id}"
															label="${userTypeList.name}" />
													</c:otherwise>
												</c:choose>
											</sec:authorize>
											<sec:authorize access="hasRole('ROLE_Managers')">
												<c:choose>
													<c:when
														test="${userTypeList.name=='SuperAdmin' or userTypeList.name=='Admin'}">
													</c:when>

													<c:otherwise>
														<form:option value="${userTypeList.id}"
															label="${userTypeList.name}" />
													</c:otherwise>
												</c:choose>
											</sec:authorize>
											<sec:authorize access="hasRole('ROLE_Users')">
												<c:choose>
													<c:when
														test="${userTypeList.name=='SuperAdmin' or userTypeList.name=='Admin' or userTypeList.name=='Managers'}">
													</c:when>

													<c:otherwise>
														<form:option value="${userTypeList.id}"
															label="${userTypeList.name}" />
													</c:otherwise>
												</c:choose>
											</sec:authorize>
											<sec:authorize access="hasRole('ROLE_SuperAdmin')">
												<form:option value="${userTypeList.id}"
													label="${userTypeList.name}" />
											</sec:authorize>

										</c:forEach>
									</form:select>


									<form:errors path="usrType" cssClass="error" />
								</div>
								<div class="col-xs-4">
									<form:label path="gid">Group</form:label>

<%-- 									<form:select path="gid" class="form-control" --%>
<%-- 										onchange="getBranches(this)" multiple="true"> --%>
<%-- 										<sec:authorize --%>
<%-- 											access="hasAnyRole('ROLE_Managers','ROLE_SuperAdmin','ROLE_Admin')"> --%>
<%-- 											<form:option value='0' label="----Select Group----" /> --%>
<%-- 										</sec:authorize> --%>
<%-- 																			<form:options items="${groupDetails}" itemLabel="groupName" --%>
<%-- 																					itemValue="groupId" /> --%>
<%-- 										<c:forEach items="${groupDetails}" var="groupList"> --%>
<%-- 											<c:forEach items="${groupdetails}" var="groupList1"> --%>
<%-- 												<c:set var="isselect" /> --%>
<%-- 												<c:if --%>
<%-- 													test="${groupList1.getGroupId() == groupList.getGroupId()}"> --%>
<%-- 													<c:set var="isselect" value="selected" /> --%>
<%-- 												</c:if> --%>
<%-- 											</c:forEach> --%>
<%-- 											<form:option value='${groupList.getGroupId()}' --%>
<%-- 												label="${groupList.getGroupName()} " selected='${isselect}' /> --%>

<%-- 										</c:forEach> --%>
<%-- 									</form:select> --%>
									
									
									<c:choose>
										<c:when test="${ not empty groupdetails}">
											<select multiple="multiple" name="gid" id="gid"
												class="form-control">
												<c:forEach var="groupDetail" items="${groupDetails}">
													<c:set value="fal" var="flag"></c:set>
													<c:forEach var="groupDetailVal"
														items="${groupdetails}">
														<c:choose>
															<c:when
																test="${groupDetailVal.getGroupId() == groupDetail.getGroupId()}">
																<c:set value="tr" var="flag"></c:set>
																<option value="${groupDetail.getGroupId()}" selected="selected">${groupDetail.getGroupName()}</option>
															</c:when>
														</c:choose>
													</c:forEach>
													<c:choose>
														<c:when test="${flag == 'fal'}">
															<option value="${groupDetail.getGroupId()}" >${groupDetail.getGroupName()}</option>
														</c:when>
													</c:choose>
												</c:forEach>
											</select>
										</c:when>
										<c:otherwise>
											<select  name="gid" id="gid"
												class="form-control">
												<c:forEach var="groupDetail" items="${groupDetails}">
												<option value="${groupDetail.getGroupId()}" >${groupDetail.getGroupName()}</option>
												</c:forEach>
											</select>
										</c:otherwise>
									</c:choose>

								</div>
								<div class="col-xs-4">

									<form:label path="bid">Branch</form:label>

									<form:select path="bid" class="form-control" id="branchlist_AJ">
										<form:option value='0' label="----Select Branch----" />
										<form:options items="${branchlist}" itemLabel="branchName"
											itemValue="brnachid" />
									</form:select>

								</div>

							</div>
						</div>

						<div class="subtitle-block">
							<h3 class="subtitle">Personal Information</h3>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-xs-4">
									<form:label path="usrFirstName">First Name</form:label>
									<label class="text-danger">*</label>
									<form:input path="usrFirstName" class="form-control"
										placeholder="First Name" maxlength="50" required="required" />
									<form:errors path="usrFirstName" class="error"></form:errors>
								</div>
								<div class="col-xs-4">
									<form:label path="usrMiddleName">Middle Name</form:label>
									<form:input path="usrMiddleName" class="form-control"
										placeholder="MiddleName" maxlength="50" />
								</div>
								<div class="col-xs-4">
									<form:label path="usrLastName">Last Name</form:label>
									<label class="text-danger">*</label>
									<form:input path="usrLastName" class="form-control"
										placeholder="Last Name" required="required" maxlength="50" />
									<form:errors path="usrLastName" cssClass="error" />
								</div>
							</div>
							<div class="row form-group">
								<div class="col-xs-2">
									<form:label path="usrTelephone">Telephone</form:label>

									<form:input path="usrTelephone" class="form-control"
										placeholder="0000 0000000" data-required="true"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$" id="usrTelephone"
										onkeyup="GetPhoneFormat('usrTelephone')" maxlength="12" />
									<form:errors path="usrTelephone" cssClass="error" />
								</div>
								<div class="col-xs-2">
									<form:label path="usrMobile">Mobile</form:label>

									<form:input path="usrMobile" class="form-control"
										placeholder="0000 0000000" data-required="true"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$" id="usrMobile"
										onkeyup="GetPhoneFormat('usrMobile')" maxlength="12" />
									<form:errors path="usrMobile" cssClass="error" />
								</div>
								<div class="col-xs-2">
									<form:label path="usrFax">Fax</form:label>

									<form:input path="usrFax" class="form-control"
										placeholder="0000 0000000" data-required="true"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$" id="usrFax"
										onkeyup="GetPhoneFormat('usrFax')" maxlength="12" />
									<form:errors path="usrFax" cssClass="error" />
								</div>
								<div class="col-xs-2">
									<form:label path="usrEmail">Email</form:label>
									<label class="text-danger">*</label>
									<form:input type="email" path="usrEmail" class="form-control"
										placeholder="Email" required="required" />
									<form:errors path="usrEmail" cssClass="error" />
								</div>
								<div class="col-xs-4">
									<form:label path="usrDesignation">Designation</form:label>

									<form:select path="usrDesignation" class="form-control">
										<form:option value="0" label="--- Select ---" />
										<form:options items="${jobtitle}" itemLabel="categoryName"
											itemValue="id" />
									</form:select>
								</div>
							</div>

							<div class="row form-group">
								<div class="col-xs-3">
									<label>Expiry Date</label> <input class="form-control"
										data-date-format="dd/MM/yyyy" datepicker
										placeholder="Expiration Date" data-required="true" type="text"
										name="usrExpiryDt"
										value="<fmt:formatDate value='${appUser.usrExpiryDt}' pattern='dd/MM/yyyy' />">
								</div>
								<div class="col-xs-3">
									<label>Last Login</label> <input class="form-control"
										data-date-format="dd/MM/yyyy" datepicker
										placeholder="Last Login" data-required="true" type="text"
										name="usrLastLogin"
										value="<fmt:formatDate value='${appUser.usrLastLogin}' pattern='dd/MM/yyyy' />">
								</div>
								<div class="col-xs-3">
									<label>Password History</label>
									<form:input path="usrPasswdHistory" class="form-control"
										placeholder="Passwd History" maxlength="2000" />
								</div>
								<div class="col-xs-3">
									<label>Password Exp. Date</label> <input class="form-control"
										data-date-format="dd/MM/yyyy"
										placeholder="Password Expiration Date" data-required="true"
										type="text" name="usrPasswdExpirationDt"
										value="<fmt:formatDate value='${appUser.usrPasswdExpirationDt}' pattern='dd/MM/yyyy' />">
								</div>
							</div>

							<div class="row form-group">
								<!-- 								<div class="col-xs-3"> -->
								<!-- 									<label>Employee </label> -->
								<!-- 									<p class="text-muted"> -->
								<%-- 										<form:checkbox path="usrEmployeeYn" value="Y" /> --%>
								<!-- 										&nbsp;&nbsp;Is Employee? -->
								<!-- 									</p> -->
								<!-- 								</div> -->
								<!-- 																<div class="col-xs-3"> -->
								<!-- 																	<label>Blast Email </label> -->
								<!-- 																	<p class="text-muted"> -->
								<%-- 																		<form:checkbox path="usrBlastEmailYn" --%>
								<%-- 																			 value="Y" />&nbsp;&nbsp;Is Blast Email? --%>
								<!-- 																	</p> -->
								<!-- 																</div> -->
								<!-- 								<div class="col-xs-3"> -->
								<!-- 									<label>Lock </label> -->
								<!-- 									<p class="text-muted"> -->
								<%-- 										<form:checkbox path="usrLockYn" value="Y" /> --%>
								<!-- 										&nbsp;&nbsp;Is Lock? -->
								<!-- 									</p> -->
								<!-- 								</div> -->
								<div class="col-xs-3">
									<label>Active/Inactive</label>
									<!-- 									<label class="text-danger">*</label> -->
									<p class="text-muted">
									<c:if test="${not empty mode}"><form:checkbox path="usrIsactiveYn" value="Y" /></c:if>
									<c:if test="${empty mode}"><form:checkbox path="usrIsactiveYn" value="Y" checked="true"/></c:if>	
										&nbsp;&nbsp;Is Active/Inactive?
									</p>
								</div>
							</div>
						</div>
						<section class="section pull-right">
							<c:if test="${not empty msg}">
								<a href="${pageContext.request.contextPath}/dashboard.do"
									type="button" class="btn btn-secondary">Discard</a>
							</c:if>
							<c:if test="${empty msg}">
								<a href="searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							</c:if>

							<button type="reset" class="btn btn-secondary">Reset</button>
							<c:if test="${not empty roleList && not empty mode}">
								<button type="button" class="btn btn-primary"
									onclick="deactivateUser('${appUser.getUsrId()}')">Deactivate</button>
							</c:if>
							<c:choose>
								<c:when
									test="${not empty appUser && not empty appUser.getUsrId() && not empty mode}">
									<button type="submit" name="edit" value="edit"
										class="btn btn-primary">Save</button>
								</c:when>
								<c:otherwise>
									<button type="submit" name="add" value="add"
										class="btn btn-primary">Add</button>
								</c:otherwise>
							</c:choose>
						</section>
					</form:form>
				</div>
			</div>
		</div>
	</section>
</article>

<form:form action="deactiveUser.do" method="post" name="deactiveUser"
	id="deactiveUser">
	<input hidden="userId" name="userId" id="userId">
</form:form>

<script>
	function checkPwd(id) {
		var opwd = document.getElementById("usrPwd").value;
		if (id.value != opwd) {
			document.getElementById("pwdMsg").innerHTML = "Your password and retype password do not match.";
		} else {
			document.getElementById("pwdMsg").innerHTML = "";
		}
	}

	function deactivateUser(userId) {
		var r = confirm("Are you want to sure deactive this user?");
		if (r == true) {
			document.getElementById("userId").value = userId;
			document.getElementById("deactiveUser").submit();
		}
	}
	function getBranches(groupid) {

		if ($('#usrType').val() != 1 && $('#usrType').val() != 4) {
			var gid = groupid.value;
			$("#branchlist_AJ").load(
					"${pageContext.request.contextPath}/branch/getBranches.do",
					{
						id : gid
					});
		}
	}

	function getGroups() {
		var gid = -1;
		$("#branchlist_AJ").load(
				"${pageContext.request.contextPath}/branch/getBranches.do", {
					id : gid
				});
	}
	function getUsertype(utype) {
		if (utype.value == 3 || utype.value == 2) {

			$("#gid").removeAttr("multiple");
			$("#gid").removeAttr("disabled");
			$("#branchlist_AJ").removeAttr("disabled");

		}
		if (utype.value == 4) {

			$("#gid").removeAttr("multiple");
			$("#gid").attr("disabled", "true");
			$("#branchlist_AJ").attr("disabled", "true");

		}
		if (utype.value == 1) {
			$("#gid").removeAttr("disabled");
			$("#gid").attr("multiple", "multiple");
			$("#branchlist_AJ").attr("disabled", "true");

		}

	}
	$(function() {
		if ($("#msg").val() == '') {
			$("ul li").removeClass("active");
			$("#liAdmin").addClass("open");
			$("#liAdmin").addClass("active");
			$("#liAdmin ul").addClass("collapse in");
			$("#liUser").addClass("active");
		}
		var userObj = document.getElementById('usrType');
		getUsertype(userObj);

	});

	var app = angular.module('aveeApp', []);
	app.directive("datepicker", function() {
		return {
			restrict : "A",
			link : function(scope, el, attr) {
				el.datepicker({
					dateFormat : 'dd/MM/yyyy',
					firstDay : 1,
					changeMonth : true,
					changeYear : true
				});
			}
		};
	})
</script>