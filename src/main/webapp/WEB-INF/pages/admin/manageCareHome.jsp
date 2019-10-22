
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<form:form modelAttribute="careHome" method="POST"
				action="${pageContext.request.contextPath}/careHomes/manageCareHomePg.do">
				<div class="col-md-12">
					<div class="card card-block sameheight-item">
						<div class="subtitle-block">
							<h3 class="subtitle">New Care Home</h3>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-xs-4">
									<form:label path="description">Care Home Name</form:label>
									<label class="text-danger">*</label>
									<form:input path="description" class="form-control"
										placeholder="description" required="required" maxlength="30" tabindex="1" autofocus="autofocus"/>
									<form:errors path="description" cssClass="error" />
								</div>
						
								<div class="col-xs-4">
									<form:label path="branchId">Branch</form:label>
									<label class="text-danger">*</label>
									<form:select path="branchId" class="form-control"  id="branchlist_AJ" tabindex="2">
											<form:option value="0" label="--- Select ---" />
											<form:options items="${branchDetailsList}"
												itemLabel="description" itemValue="id" />
												</form:select>
									<form:errors path="branchId" cssClass="error" />
								</div>
						
								<div class="col-xs-4">
									<form:label path="addLine1">Address</form:label>
								
									<form:textarea path="addLine1" class="form-control"
										placeholder="Address"  maxlength="30" tabindex="3"/>
									<form:errors path="addLine1" cssClass="error" />
								</div>
							</div>
						</div>
						<section class="section pull-right">
							<a href="searchPg.do" type="button" class="btn btn-secondary" tabindex="5">Discard</a>
							<button type="reset" class="btn btn-secondary" tabindex="6">Reset</button>
							
							<c:choose>
								<c:when
									test="${not empty appUser && not empty appUser.getUsrId() && not empty mode}">
									<button type="submit" name="edit" value="edit"
										class="btn btn-primary" tabindex="7">Save</button>
								</c:when>
								<c:otherwise>
									<button type="submit" name="add" value="add"
										class="btn btn-primary" tabindex="4">Add</button>
								</c:otherwise>
							</c:choose>
						</section>
					</div>
				</div>
			</form:form>
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