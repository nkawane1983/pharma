<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.form-inline .form-control {
	width: auto;
}
</style>
<jsp:include page="./comman.jsp" />
<section class="section">
	<div class="row sameheight-container">
		<div class="col-md-12">
			<div class="card card-block sameheight-item">
				<div class="col-md-12">
					<div class="title-block">
						<h3 class="title">
							${searchTitle}<a
								href="${pageContext.request.contextPath}/${addBtnUrl}"
								class="btn btn-primary pull-right" data-toggle="tooltip"
								title="Add">Add</a>

						</h3>
					</div>
					<form:form class="form-inline" role="form"
						action="${pageContext.request.contextPath}/${searchUrl}"
						method="GET">

						<div class="form-group">
							<fmt:parseDate pattern="yyyy-MM-dd" value="${fdate}"
								var="parsedDate" />
							<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy"
								var="fdate" />
							<label>Date Range</label> <input class="form-control" id="frmDateSearch"
								placeholder="DD/MM/YYYY" type="text" name="fDate"
								onchange="checkdateRangeSearch()" value="${fdate}">

						</div>
						<div class="form-group">
							<fmt:parseDate pattern="yyyy-MM-dd" value="${enddate}"
								var="parsedDate" />
							<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy"
								var="tdate" />
							<label>-</label> <input class="form-control" id="toDateSearch"
								placeholder="DD/MM/YYYY" type="text" name="tDate"
								onchange="checkdateRangeSearch()" value="${tdate}">

						</div>
						<c:if test="${searchTitle eq'Banking'}">
							<div class="form-group">
								<label>Ref No.</label> <input class="form-control" id="refNo"
									placeholder="Enter Reference No." type="text" name="refNo">

							</div>
						</c:if>
						<div class="form-group">
							<button type="reset" class="btn btn-success-1" title="Reset">Reset</button>
							<button type="submit" class="btn btn-primary" data-toggle="modal"
								title="Search">Search</button>
						</div>

					</form:form>
				</div>
			</div>

		</div>
	</div>

</section>
