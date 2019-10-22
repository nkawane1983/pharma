<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<article class="content dashboard-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-xl-12">
				<div class="card sameheight-item items" data-exclude="xs,sm,lg">
					<div class="card card-block sameheight-item">
						<div class="title-block">
							<h3 class="title">
								Search Care Home <a
									href="${pageContext.request.contextPath}/careHomes/newCareHomePg.do"
									class="btn btn-primary pull-right" data-toggle="tooltip"
									title="Add User">Add Care Home</a>

							</h3>
						</div>
						<form:form class="form-inline" role="form"
							action="${pageContext.request.contextPath}/careHomes/searchPg.do"
							method="GET">
							<div class="form-group">
								<label>Select Branch</label> <select name="branchId"
									class="form-control" id="branchlist_AJ" tabindex="2">
									<option value="0">--- Select ---</option>
									<c:forEach items="${branchDetailsList}" var="branches">
										<option value="${branches.id}">${branches.description}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label>CareHome Name</label> <input class="form-control"
									id="carehomename" placeholder="Enter CareHome Name" type="text"
									name="carehomename">
							</div>
							<button type="reset" class="btn btn-success-1">Reset</button>
							<button type="submit" class="btn btn-primary" data-toggle="modal">Search</button>
						</form:form>
					</div>
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
							export="false" name="carehomelist"
							requestURI="${pageContext.request.contextPath}/careHomes/searchPg.do"
							pagesize="25" defaultsort="1">
							<display:column property="id" title="CareHomeId"
								sortable="true"
								href="${pageContext.request.contextPath}/careHomes/editCareHomePg.do"
								paramId="id" paramProperty='id' />
							<display:column property="description" title="CareHome Name"
								sortable="true"
								href="${pageContext.request.contextPath}/careHomes/editCareHomePg.do"
								paramId="id" paramProperty='id' />
							<display:column property="addLine1" title="Address"
								sortable="true"
								href="${pageContext.request.contextPath}/careHomes/editCareHomePg.do"
								paramId="id" paramProperty='id' />
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
	$("#licarehome").addClass("active");
});
</script>