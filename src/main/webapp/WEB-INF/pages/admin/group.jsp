
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<article class="content dashboard-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="title-block">
						<h3 class="title">
							Search Group <a
								href="${pageContext.request.contextPath}/group/newGroupPg.do"
								class="btn btn-primary pull-right" data-toggle="tooltip"
								title="Add User">Add Group</a>

						</h3>
					</div>
					<form:form class="form-inline" role="form"
						action="${pageContext.request.contextPath}/group/searchPg.do"
						method="GET">
						<div class="form-group">
							<label>Group ID</label> <input class="form-control" id="branch"
								placeholder="Enter Group ID" type="text" name="gid"
								autofocus="autofocus">
						</div>
						<div class="form-group">
							<label>Group Name</label> <input class="form-control" id="gname"
								placeholder="Enter Group Name" type="text" name="gname">
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
							export="false" name="groupList"
							requestURI="${pageContext.request.contextPath}/group/searchPg.do"
							pagesize="25" id="groupList">
							<display:column property="groupName" title="Group Name"
								sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId' />
							<display:column property="groupAddrLine1" title="addrLine1"
								sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId' />
							<display:column property="groupTown" title="Town" sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId' />
							<display:column property="groupPostcode" title="Postcode"
								sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId' />
							<display:column title="Country" sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId'>
								<c:forEach items="${countrycode}" var="countrycode">
									<c:if test="${countrycode.id==groupList.groupCountryCode}">
								${countrycode.countryName}
								</c:if>
								</c:forEach>

							</display:column>
							<display:column property="groupTelephoneNo" title="TelephoneNo"
								sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId' />
							<display:column property="groupFaxNo" title="FaxNo"
								sortable="true"
								href="${pageContext.request.contextPath}/group/editGroupDetails.do"
								paramId="id" paramProperty='groupId' />


						</display:table>
					</div>
				</div>
			</div>
		</div>
	</section>

</article>

<script type="text/javascript">
	$(function() {
		$("ul li").removeClass("active");
		$("#liAdmin").addClass("open");
		$("#liAdmin").addClass("active");
		$("#liAdmin ul").addClass("collapse in");
		$("#liGroup").addClass("active");
	});
</script>