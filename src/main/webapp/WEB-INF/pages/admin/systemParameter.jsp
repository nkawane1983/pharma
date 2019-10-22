<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="title-block">
						<h3 class="title">
							<!--                         	<label style="color: #52BCD3 "> -->
							Search System Parameter
							<!--                         	</label>  -->
							<a
								href="${pageContext.request.contextPath}/sysParameter/newSysParam.do"
								class="btn btn-primary pull-right" data-toggle="tooltip"
								title="Add System Parameter">Add System Parameter</a>
						</h3>
					</div>
					<form:form class="form-inline" role="form"
						action="${pageContext.request.contextPath}/sysParameter/searchSysParameter.do"
						method="GET">
<!-- 						<div class="col-xs-3"> -->
<!-- 							<label>Enter Id</label> <input class="form-control" -->
<!-- 								placeholder="Enter Id" type="text" name="sysId" -->
<!-- 								autofocus="autofocus" onkeypress="return isNumber(this,event)"> -->
<!-- 						</div> -->

						<div class="col-xs-6">
							<label>Enter name</label> <input class="form-control"
								placeholder="Enter Name" type="text" name="sysParamName">
						</div>

<!-- 						<div class="col-xs-3"> -->
<!-- 							<label>Enter value</label> <input class="form-control" -->
<!-- 								placeholder="Enter value" type="text" name="sysParamValue"> -->
<!-- 						</div> -->
						<div class="col-xs-3" style="margin-top: 32px">
							<button type="reset" class="btn btn-success-1">Reset</button>
							<button type="submit" class="btn btn-primary">Search</button>
						</div>
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
							export="false" name="sysParamList"
							requestURI="${pageContext.request.contextPath}/sysParameter/searchSysParameter.do"
							pagesize="25" defaultsort="1">

							<display:column property="id" title="Id" sortable="true"
								href="${pageContext.request.contextPath}/sysParameter/editSysParam.do"
								paramId="id" paramProperty='id' />

							<display:column property="parameterName" title="Name"
								sortable="true"
								href="${pageContext.request.contextPath}/sysParameter/editSysParam.do"
								paramId="id" paramProperty='id' />

							<display:column property="parameterValue" title="Value"
								sortable="true"
								href="${pageContext.request.contextPath}/sysParameter/editSysParam.do"
								paramId="id" paramProperty='id' format="{0,number,00.000}"/>

							<display:column property="description" title="Description"
								sortable="true"
								href="${pageContext.request.contextPath}/sysParameter/editSysParam.do"
								paramId="id" paramProperty='id' />
							<display:column title="" sortable="true"
								href="${pageContext.request.contextPath}/sysParameter/deleteSysParam.do"
								paramId="id" paramProperty='id'>
								<button type="submit" class="btn btn-primary" style="padding: 0.09rem 0.8rem ;margin-bottom: 0px">Delete</button>
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
		$(function() {
			$("ul li").removeClass("active");
			$("#liAdmin").addClass("open");
			$("#liAdmin").addClass("active");
			$("#liAdmin ul").addClass("collapse in");
			$("#liSystem").addClass("active");
		});
	});
</script>