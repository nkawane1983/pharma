<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                	<form:form modelAttribute="userType" method="POST"
						action="${pageContext.request.contextPath}/userType/manageUserType" role="form">
						
						<form:hidden path="id"/>
						
						<div class="subtitle-block">
                       		<h3 class="subtitle">User Type Information </h3>
                  		</div>
                  		
                  		<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 ">
									<label>User Type Name </label>
									<form:input path="name" class="form-control" required="required"/>
									<form:errors path="name" cssClass="error"/>
								</div>
								<div class="col-sm-6 ">
									<label>User Type Description </label>
									<form:input path="description" class="form-control" />
								</div>
							</div>
						</div>
						
						
						<c:if test="${not empty userType && not empty userType.getId() && not empty mode}">
							<div class="subtitle-block">
                       			<h3 class="subtitle">Role(s) Assign</h3>
                  			</div>
	                   		<div class="col-md-12">
	                   			<c:set var="counter" value="1"></c:set>
								<c:choose>
									<c:when test="${not empty userTypeRoleList}">
										<c:forEach var="role" items="${roleList}">
											<c:set value="1" var="flag"/>
											<input type="hidden" name="roleId_${counter}" value="${role.rolId}">
											<c:choose>
												<c:when test="${not empty role.rolParentId && role.rolParentId != 0}">
													<c:forEach var="userRole" items="${userTypeRoleList}">
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
													<c:forEach var="userRole" items="${userTypeRoleList}">
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
												<c:when test="${not empty role.rolParentId && role.rolParentId != 0}">
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
						<section class="section pull-right">
							<a href="${pageContext.request.contextPath}/userType/searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							<button class="btn btn-secondary" type="reset">Reset</button>
							<c:choose>
								<c:when test="${not empty userType && not empty userType.getId() && not empty mode}">
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
<script>
$(function() {
	$(function() {
		$("ul li").removeClass("active");
		$("#liAdmin").addClass("open");
		$("#liAdmin").addClass("active");
		$("#liAdmin ul").addClass("collapse in");
		$("#liUsertype").addClass("active");
	});
});
</script>