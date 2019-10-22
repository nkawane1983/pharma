<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<div class="title-block">
         <h3 class="title"> User </h3>
     </div>
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                	<c:choose>
						<c:when test="${not empty appUser && not empty appUser.getUsrId() && not empty mode}">
							<c:set var="userId" value="${appUser.getUsrId()}"></c:set>
						</c:when>
					</c:choose>
						
						<form:form modelAttribute="appUser" method="POST" action="${pageContext.request.contextPath}/user/manageUser.do">
							
							<div class="subtitle-block">
                       			<h3 class="subtitle">Login Information </h3>
                  				 </div>
                  				 <div class="col-md-12">
								<div class="row form-group">
									<c:choose>
										<c:when
											test="${not empty appUser && not empty appUser.getUsrId()
													&& not empty mode}">
											<div class="col-xs-2">
												<form:label path="usrId">User Id</form:label><label class="text-danger">*</label>
												<form:input path="usrId" class="form-control"
													placeholder="User Id" disabled="true"
													required="required" maxlength="30"/>
												<form:hidden path="usrId" />
												<form:errors path="usrId" cssClass="error"/>
											</div>
											<div class="col-xs-2">
												<form:label path="usrName">User Name</form:label><label class="text-danger">*</label>
												<form:input path="usrName" class="form-control"
													placeholder="User Name" disabled="true"
													required="required" maxlength="30"/>
												<form:hidden path="usrName" />
												<form:errors path="usrName" cssClass="error"/>
											</div>
										</c:when>
										<c:otherwise>
											<div class="col-xs-2">
												<form:label path="usrId">User Id</form:label><label class="text-danger">*</label>
												<form:input path="usrId" class="form-control"
													placeholder="User Id" required="required" maxlength="30"/>
												<form:errors path="usrId" cssClass="error"/>	
													
											</div>
											<div class="col-xs-2">
												<form:label path="usrName">User Name</form:label><label class="text-danger">*</label>
												<form:input path="usrName" class="form-control"
													placeholder="User Name" required="required" maxlength="30"/>
												<form:errors path="usrName" cssClass="error"/>
											</div>
											<div class="col-xs-2">
												<form:label path="usrPasswd">Password</form:label><label class="text-danger">*</label>
												<form:password path="usrPasswd" class="form-control" maxlength="255"
													placeholder="Password" id="usrPwd" required="required" />
												<form:errors path="usrPasswd" cssClass="error"/>
											</div>

											<div class="col-xs-2">
												<label>Re-type Password</label> <input type="password"
													name="reTypePwd" id="reTypePwd" class="form-control"
													onchange="checkPwd(this)" required="required"> <label
													id="pwdMsg" class="error">${pwdMsg}</label>
											</div>
										</c:otherwise>
									</c:choose>
								<div class="col-xs-2">
									<form:label path="usrType">User Type</form:label>
									<form:input path="usrType" class="form-control"
										placeholder="User Type" maxlength="20"/>
								</div>
							</div>
						</div>
								
						<div class="subtitle-block">
                      		<h3 class="subtitle">Personal Information</h3>
                 		</div>
                   		<div class="col-md-12">
                   			<div class="row form-group">
								<div class="col-xs-4">
									<form:label path="usrFirstName">First Name</form:label><label class="text-danger">*</label>
									<form:input path="usrFirstName" class="form-control"
										placeholder="First Name" maxlength="50"/>
									<form:errors path="usrFirstName" class="error"></form:errors>
								</div>
								<div class="col-xs-4">
									<form:label path="usrMiddleName">Middle Name</form:label>
									<form:input path="usrMiddleName" class="form-control"
										placeholder="MiddleName" maxlength="50"/>
								</div>
								<div class="col-xs-4">
									<form:label path="usrLastName">Last Name</form:label><label class="text-danger">*</label>
									<form:input path="usrLastName" class="form-control"
										placeholder="Last Name" required="required" maxlength="50"/>
									<form:errors path="usrLastName" cssClass="error"/>
								</div>
							</div>
                   			<div class="row form-group">
								<div class="col-xs-2">
									<form:label path="usrTelephone">Telephone</form:label><label class="text-danger">*</label>
									<form:input path="usrTelephone" class="form-control"
										placeholder="Telephone" data-required="true"
										pattern="^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$"
										id="usrTelephone" onkeyup="GetPhoneFormat('usrTelephone')"
										maxlength="12" required="required" />
									<form:errors path="usrTelephone" cssClass="error"/>
								</div>
								<div class="col-xs-2">
									<form:label path="usrMobile">Mobile</form:label><label class="text-danger">*</label>
									<form:input path="usrMobile" class="form-control"
										placeholder="Mobile" data-required="true"
										pattern="^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$"
										id="usrMobile" onkeyup="GetPhoneFormat('usrMobile')"
										maxlength="12" required="required" />
									<form:errors path="usrMobile" cssClass="error"/>
								</div>
								<div class="col-xs-2">
									<form:label path="usrFax">Fax</form:label><label class="text-danger">*</label>
									<form:input path="usrFax" class="form-control"
										placeholder="Fax" data-required="true"
										pattern="^\(?([0-9]{3})\)?[-. ]?([0-9]{3})[-. ]?([0-9]{4})$"
										id="usrFax" onkeyup="GetPhoneFormat('usrFax')"
										maxlength="12" required="required" />
									<form:errors path="usrFax" cssClass="error"/>
								</div>
								<div class="col-xs-2">
									<form:label path="usrEmail">Email</form:label>
									<form:input type="email" path="usrEmail"
										class="form-control" placeholder="Email" />
								</div>
								<div class="col-xs-2">
									<form:label path="usrDepartmentCd">Department</form:label>
									<form:input path="usrDepartmentCd" class="form-control"
										placeholder="Department" maxlength="15"/>
								</div>
								<div class="col-xs-2">
									<form:label path="usrDesignation">Designation</form:label>
									<form:input path="usrDesignation" class="form-control"
										placeholder="Designation" maxlength="100"/>
								</div>
							</div>
							
							<div class="row form-group">
								<div class="col-xs-3">
									<form:label path="usrExpiryDt">Expiry Date</form:label>
									<input class="form-control" data-date-format="mm/dd/yyyy"
										datepicker placeholder="Expiration Date"
										data-required="true" type="text" name="usrExpiryDt"
										value="<fmt:formatDate value='${appUser.usrExpiryDt}' pattern='MM/dd/yyyy' />">
								</div>

								<div class="col-xs-3">
									<form:label path="usrLastLogin">Last Login</form:label>
									<input class="form-control" data-date-format="mm/dd/yyyy"
										datepicker placeholder="Last Login"
										data-required="true" type="text" name="usrLastLogin"
										value="<fmt:formatDate value='${appUser.usrLastLogin}' pattern='MM/dd/yyyy' />">	
										
										
								</div>
								<div class="col-xs-3">
									<form:label path="usrPasswdHistory">Password History</form:label>
									<form:input path="usrPasswdHistory" class="form-control"
										placeholder="Passwd History" maxlength="2000"/>
								</div>

								<div class="col-xs-3">
									<form:label path="usrPasswdExpirationDt">Password Exp. Date</form:label>
									<input class="form-control" data-date-format="mm/dd/yyyy"
										datepicker placeholder="Password Expiration Date"
										data-required="true" type="text"
										name="usrPasswdExpirationDt"
										value="<fmt:formatDate value='${appUser.usrPasswdExpirationDt}' pattern='MM/dd/yyyy' />">
								</div>
							</div>
							
							
							<div class="row form-group">
								<div class="col-xs-3">
									<label>Employee </label>
									<p class="text-muted">
										<form:checkbox path="usrEmployeeYn"
											value="Y" />&nbsp;&nbsp;Is Employee? 
									</p>
								</div>
<!-- 								<div class="col-xs-3"> -->
<!-- 									<label>Blast Email </label> -->
<!-- 									<p class="text-muted"> -->
<%-- 										<form:checkbox path="usrBlastEmailYn" --%>
<%-- 											 value="Y" />&nbsp;&nbsp;Is Blast Email? --%>
<!-- 									</p> -->
<!-- 								</div> -->
								<div class="col-xs-3">
									<label>Lock </label>
									<p class="text-muted">
										<form:checkbox path="usrLockYn"
											value="Y" />&nbsp;&nbsp;Is Lock?
									</p>
								</div>
							</div>
                   		</div>		 
						
						<c:if test="${not empty appUser && not empty appUser.getUsrId() && not empty mode}">
							<div class="subtitle-block">
                       			<h3 class="subtitle">Role(s) Assign</h3>
                  			</div>
	                   		<div class="col-md-12">
	                   			<c:set var="counter" value="1"></c:set>
								<c:choose>
									<c:when test="${not empty userRoleList}">
										<c:forEach var="role" items="${roleList}">
											<c:set value="1" var="flag"/>
											<input type="hidden" name="roleId_${counter}" value="${role.rolId}">
											<c:choose>
												<c:when test="${not empty role.rolParentId}">
													<c:forEach var="userRole" items="${userRoleList}">
												 		<c:choose>
															<c:when test="${role.rolId == userRole.role.rolId}">
																<c:set value="0" var="flag"/>
																<div class="form-group">
																	<div class="col-xs-1">
																	</div>
																
																	<div class="col-xs-1">
																		<input type="checkbox" checked="checked" name="roleCheck_${counter}" id="roleCheck_${counter}">
																	</div>
																	<div class="col-xs-10">
																		<label>${role.rolDescription}</label>
																	</div>
																</div>
															</c:when>
														</c:choose>
													 </c:forEach>
													 <c:choose>
														<c:when test="${flag == '1'}">
															<div class="form-group">
																<div class="col-xs-1">
																	</div>
																<div class="col-xs-1">
																	<input type="checkbox"  name="roleCheck_${counter}" id="roleCheck_${counter}">
																</div>
																<div class="col-xs-10">
																	<label>${role.rolDescription}</label>
																</div>
															</div>
														</c:when>
													</c:choose>
												</c:when>
												<c:otherwise>
													<c:forEach var="userRole" items="${userRoleList}">
												 		<c:choose>
															<c:when test="${role.rolId == userRole.role.rolId}">
																<c:set value="0" var="flag"/>
																<div class="form-group">
																	<div class="col-xs-1">
																		<input type="checkbox" checked="checked" name="roleCheck_${counter}" id="roleCheck_${counter}">
																	</div>
																	<div class="col-xs-11">
																		<label>${role.rolDescription}</label>
																	</div>
																</div>
															</c:when>
														</c:choose>
													 </c:forEach>
													 <c:choose>
														<c:when test="${flag == '1'}">
															<div class="form-group">
																<div class="col-xs-1">
																	<input type="checkbox"  name="roleCheck_${counter}" id="roleCheck_${counter}">
																</div>
																<div class="col-xs-11">
																	<label>${role.rolDescription}</label>
																</div>
															</div>
														</c:when>
													</c:choose>
												</c:otherwise>
											</c:choose>
											<c:set value="1" var="flag"/>
											<c:set var="counter" value="${counter+1}"></c:set>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<c:forEach items="${roleList}" var="role">
											<input type="hidden" name="roleId_${counter}" value="${role.rolId}">
											
											<c:choose>
												<c:when test="${not empty role.rolParentId}">
													<div class="form-group">
														<div class="col-xs-1">
														</div>
														<div class="col-xs-1">
															<input type="checkbox"  name="roleCheck_${counter}" id="roleCheck_${counter}">
														</div>
														<div class="col-xs-10">
															<label>${role.rolDescription}</label>
														</div>
													</div>
												</c:when>
												<c:otherwise>
													<div class="form-group">
														<div class="col-xs-1">
															<input type="checkbox"  name="roleCheck_${counter}" id="roleCheck_${counter}">
														</div>
														<div class="col-xs-11">
															<label>${role.rolDescription}</label>
														</div>
													</div>
												
												</c:otherwise>
											</c:choose>
											
											<c:set var="counter" value="${counter+1}"></c:set>
										</c:forEach>
									</c:otherwise>
								</c:choose>
								<input type="hidden" name="counter" value="${counter}" id="counter">
	                   		</div>
	                   	</c:if>
	                   		
						<section class="section">
							<a href="searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							<button type="reset" class="btn btn-secondary">Reset</button>
							<c:if test="${not empty roleList && not empty mode}">
								<button type="button" class="btn btn-primary" onclick="deactivateUser('${appUser.getUsrId()}')">Deactivate</button>
							</c:if>
							<c:choose>
								<c:when test="${not empty appUser && not empty appUser.getUsrId() && not empty mode}">
										<button type="submit" name="edit" value="edit" class="btn btn-primary">Save</button>
								</c:when>
								<c:otherwise>
									<button type="submit" name="add" value="add" class="btn btn-primary">Add</button>
								</c:otherwise>
							</c:choose>
						</section>
					</form:form>
                </div>
            </div>
        </div>
    </section>
</article>

	<form:form action="deactiveUser.do" method="post" name="deactiveUser" id="deactiveUser">
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
	
	function deactivateUser(userId){
		var r = confirm("Are you want to sure deactive this user?");
		if (r == true) {
			document.getElementById("userId").value = userId;
			document.getElementById("deactiveUser").submit();
		}
	}

	$(function() {
// 		$("ul.sidebar-menu li").removeClass("active");
// 		$("#banking").addClass("active");
	});
	
	
	var app = angular.module('aveeApp', []);
	app.directive("datepicker", function () {
	    return {
	        restrict: "A",
	        link: function (scope, el, attr) {
	            el.datepicker({
	            	dateFormat : 'mm/dd/yy',
					firstDay : 1,
					changeMonth: true,
					changeYear: true
                 });
	        }
	    };
	})
	
</script>