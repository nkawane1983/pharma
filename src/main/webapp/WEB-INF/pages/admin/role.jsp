<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<style>
.table th, .table td {
    padding: 0.3rem;
    }
</style>
<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                    <div class="title-block">
                        <h3 class="title"> Search Role 
							<a href="${pageContext.request.contextPath}/role/newRole.do" class="btn btn-primary pull-right"
								data-toggle="tooltip" title="Add Role">Add Role</a>

						</h3>
                    </div>
                    <form:form class="form-inline" role="form" action="${pageContext.request.contextPath}/role/searchRole.do" method="GET">
						<div class="form-group">
							<label>Enter id </label>
							<input class="form-control" id="roleId" placeholder="Enter id"
								type="text" name="roleId" autofocus="autofocus" onkeypress="return isNumber(this,event)">
						</div>
						<div class="form-group">
							<label>Enter name </label>
							<input class="form-control" id="roleName" placeholder="Enter Name"
								type="text" name="roleName">
						</div>
						<button type="reset" class="btn btn-success-1" >Reset</button>
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
						<display:table class="table table-bordered table-hover flip-content"  export="false" name="roleList" 
								requestURI="${pageContext.request.contextPath}/role/searchRole.do" pagesize="10" defaultorder="descending">
					        <display:column property="rolId" title="Role Id"
									sortable="true" href="${pageContext.request.contextPath}/role/editRole.do" paramId="rolId"
									paramProperty='rolId' />
							<display:column property="rolName" title="Role Name"
								sortable="true" href="${pageContext.request.contextPath}/role/editRole.do" paramId="rolId"
								paramProperty='rolId' />
							<display:column property="rolDescription" title="Role Description"
								sortable="true" href="${pageContext.request.contextPath}/role/editRole.do" paramId="rolId"
								paramProperty='rolId' />
								<display:column title="" sortable="true" 
					        	href="${pageContext.request.contextPath}/role/deleteRole.do" paramId="rolId"  paramProperty="rolId">
					        	<button type="submit" name="delete" value="delete"
										class="btn btn-primary"style="padding: 0.09rem 0.8rem ;margin-bottom: 0px">Delete</button>
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
		$("#liRole").addClass("active");
	});
});
</script>