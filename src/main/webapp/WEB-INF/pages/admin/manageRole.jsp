<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                	<form:form modelAttribute="role" method="POST"
						action="${pageContext.request.contextPath}/role/manageRole" role="form">
						
						<form:hidden path="rolId"/>
						
						<div class="subtitle-block">
                       		<h3 class="subtitle">Role Information </h3>
                  		</div>
                  		
                  		<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 ">
									<label>Role Name </label>
									<form:input path="rolName" class="form-control" required="required"/>
									<form:errors path="rolName" cssClass="error"/>
								</div>
								<div class="col-sm-6 ">
									<label>Role Description </label>
									<form:input path="rolDescription" class="form-control" />
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-2 ">
									<label>Active </label>
									<p class="text-muted">
										<form:checkbox path="rolIsactive"
											value="Y" />&nbsp;&nbsp;Is Active?
									</p>
								</div>
							</div>
						</div>
						<br/>
						<section class="section pull-right">
							<a href="${pageContext.request.contextPath}/role/searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							<button class="btn btn-secondary" type="reset">Reset</button>
							<c:choose>
								<c:when test="${not empty role && not empty role.getRolId() && not empty mode}">
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
		$("#liRole").addClass("active");
	});
});
</script>