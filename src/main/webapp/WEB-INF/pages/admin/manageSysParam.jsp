<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                	<form:form modelAttribute="systemParameter" method="POST"
						action="${pageContext.request.contextPath}/sysParameter/manageSysParam" role="form">
						
						<form:hidden path="id"/>
						
						<div class="subtitle-block">
                       		<h3 class="subtitle">System Parameter Information </h3>
                  		</div>
                  		
                  		<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4">
									<label>System Parameter Name </label>
									<form:input path="parameterName" class="form-control" required="required"/>
									<form:errors path="parameterName" cssClass="error"/>
								</div>
								
								<div class="col-sm-4 ">
									<label>System Parameter Value </label>
									<form:input path="parameterValue" class="form-control" required="required"/>
									<form:errors path="parameterValue" cssClass="error"/>
								</div>
								
								<div class="col-sm-4">
									<label>Description </label>
									<form:input path="description" class="form-control" />
								</div>
							</div>
						</div>
						<section class="section pull-right">
							<a href="${pageContext.request.contextPath}/sysParameter/searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							<button class="btn btn-secondary" type="reset">Reset</button>
							<c:choose>
								<c:when test="${not empty systemParameter && not empty systemParameter.getId() && not empty mode}">
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
			$("#liSystem").addClass("active");
		});
	});
</script>