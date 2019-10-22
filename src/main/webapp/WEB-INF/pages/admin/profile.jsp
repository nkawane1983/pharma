<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<article class="content dashboard-page">

	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block ">
					<%
						if (session.getAttribute("gId") != null && session.getAttribute("bId") != null) {
					%>
					<div class="subtitle-block">
						<h3 class="subtitle">Branch Profile</h3>
					</div>


					<div class="col-md-12">
						<div class="form-group row col-sm-6">
							<label class="col-sm-4 form-control-label"
								for="formGroupExampleInput2">Group Name :</label>
							<div class="col-sm-8">
								<label class="col-sm-12 form-control-label"
									for="formGroupExampleInput2"><%=(String) session.getAttribute("gName")%>
								</label>
							</div>
						</div>


					</div>
					<%
						}
					%>
					<c:choose>
						<c:when test="${not empty branchdetails}">

							<div class="col-md-12">
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Branch ID </label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.internalBranchId}" /> </label>
									</div>
								</div>
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Branch Name : </label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.description}" /> </label>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Address :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.addrline1}" />,<c:out
												value="${branchdetails.addrline2}" /> </label>
									</div>
								</div>
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Town :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.town}" /> </label>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Country :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:forEach
												items="${countrycode}" var="countrycode">
												<c:if test="${countrycode.id==branchdetails.county_code}">
													<c:out value="${countrycode.countryName}" />
												</c:if>
											</c:forEach> </label>
									</div>
								</div>
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">PostCode :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.postcode}" /> </label>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Telephone No. :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.telephoneNo}" /> </label>
									</div>
								</div>
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Fax No. :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><c:out
												value="${branchdetails.faxNo}" /> </label>
									</div>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group row col-sm-6">
									<label class="col-sm-4 form-control-label"
										for="formGroupExampleInput2">Current Period :</label>
									<div class="col-sm-8">
										<label class="col-sm-12 form-control-label"
											for="formGroupExampleInput2"><fmt:parseDate
												value="${branchdetails.period}" dateStyle="long" pattern="M"
												var="monthDate"></fmt:parseDate> <fmt:formatDate
												value="${monthDate}" pattern="MMMM" /></label>
									</div>
								</div>

							</div>
						</c:when>
					</c:choose>
					<c:set var="actionPara" value="${pageContext.request.contextPath}/user/editUser.do" />
					<sec:authorize access="hasRole('ROLE_Users')">
					<c:set var="actionPara" value="${pageContext.request.contextPath}/user/editUser.do?msg=update" />
					</sec:authorize>
					<form method="POST"
						action="${actionPara}">
						<input type="hidden" value="${userInfo.usrId}" name="usrId" />
						<div class="subtitle-block">
							<h3 class="subtitle">
								User Profile
								<button type="submit" class="btn btn-primary pull-right"
									style="padding: 0.09rem 2.8rem;">Edit</button>

							</h3>
						</div>
					</form>
					<div class="col-md-12">
						<div class="form-group row col-sm-6">
							<label class="col-sm-4 form-control-label"
								for="formGroupExampleInput2">Name :</label>
							<div class="col-sm-8">
								<label class="col-sm-12 form-control-label"
									for="formGroupExampleInput2">${userInfo.usrFirstName}
									${userInfo.usrMiddleName} ${userInfo.usrLastName}</label>
							</div>
						</div>
					</div>
					<div class="col-md-12">
						<div class="form-group row col-sm-6">
							<label class="col-sm-4 form-control-label"
								for="formGroupExampleInput2"> Job Title:</label>
							<div class="col-sm-8">
								<label class="col-sm-12 form-control-label"
									for="formGroupExampleInput2">
									<fmt:parseNumber var = "i" type = "number" value = "${userInfo.usrDesignation}" />
									<c:forEach
										items="${jobtitle}" var="jobtitle">
										
										<c:if test="${i==jobtitle.id}">${jobtitle.categoryName}</c:if>
									</c:forEach> </label>
							</div>
						</div>

					</div>

					<div class="col-md-12">
						<div class="form-group row col-sm-6">
							<label class="col-sm-4 form-control-label"
								for="formGroupExampleInput2">User Type:</label>
							<c:if test="${userInfo.usrType==1}">
								<div class="col-sm-8">
									<label class="col-sm-12 form-control-label"
										for="formGroupExampleInput2">Admin </label>
								</div>
							</c:if>
							<c:if test="${userInfo.usrType==2}">
								<div class="col-sm-8">
									<label class="col-sm-12 form-control-label"
										for="formGroupExampleInput2">User </label>
								</div>
							</c:if>
							<c:if test="${userInfo.usrType==3}">
								<div class="col-sm-8">
									<label class="col-sm-12 form-control-label"
										for="formGroupExampleInput2">Manager </label>
								</div>
							</c:if>
							<c:if test="${userInfo.usrType==4}">
								<div class="col-sm-8">
									<label class="col-sm-12 form-control-label"
										for="formGroupExampleInput2">Super Admin </label>
								</div>
							</c:if>
						</div>

					</div>
				</div>
			</div>

		</div>
	</section>
</article>