<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="title-block">
						<h3 class="title">
							Search Branch <a
								href="${pageContext.request.contextPath}/branch/newBranchDetails.do"
								class="btn btn-primary pull-right" data-toggle="tooltip"
								title="Add User">Add Branch</a>

						</h3>
					</div>
					<form:form class="form-inline" role="form"
						action="${pageContext.request.contextPath}/branch/searchPg.do"
						method="GET">
						<div class="form-group">
							<label>Select Branch</label> <input class="form-control"
								id="branch" placeholder="Select Branch" type="text"
								name="branchName" autofocus="autofocus">
						</div>
						<div class="form-group">
							<label>Group Name</label> <input class="form-control"
								id="groupid" placeholder="Enter Group Name" type="text"
								name="groupname">
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
							export="false" name="branchDetailsList"
							requestURI="${pageContext.request.contextPath}/branch/searchPg.do"
							pagesize="30" id="branchDetailsList">
							<display:column property="internalBranchId" title="BranchId"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />
							<display:column property="branchName" title="Branch Name"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />
							<display:column property="branchAddrline1" title="Address"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />
							<display:column property="branchTown" title="Town"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />
							<display:column property="branchPostcode" title="Postcode"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />

							<display:column title="County" sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid'>
								<c:forEach items="${countrycode}" var="countrycode">
									<c:if
										test="${countrycode.id==branchDetailsList.branchCounty_code}">
								${countrycode.countryName}
								</c:if>
								</c:forEach>
							</display:column>

							<display:column property="branchCdMontlyTarget" title="C&DMontly"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />
							<display:column property="branchTelephoneNo" title="TelephoneNo"
								sortable="true"
								href="${pageContext.request.contextPath}/branch/editBranch.do"
								paramId="id" paramProperty='brnachid' />
							<display:column title="" sortable="true"
								href="${pageContext.request.contextPath}/branch/deleteBranch.do"
								paramId="id" paramProperty="brnachid">
								<button type="submit" name="delete" value="delete"
									class="btn btn-primary"
									style="padding: 0.09rem 0.8rem; margin-bottom: 0px">Delete</button>
							</display:column>
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
		$("#liBranch").addClass("active");
	});
</script>