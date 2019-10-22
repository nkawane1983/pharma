<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>

<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                    <div class="title-block">
                        <h3 class="title"> Search User Type
							<a href="${pageContext.request.contextPath}/userType/newUserType.do" class="btn btn-primary pull-right"
								data-toggle="tooltip" title="Add User Type">Add User Type</a>

						</h3>
                    </div>
                    
                    <form:form class="form-inline" role="form" action="${pageContext.request.contextPath}/userType/searchUserType.do" method="GET">
						<div class="form-group">
							<label>Enter id </label>
							<input class="form-control" id="roleId" placeholder="Enter id"
								type="text" name="userTypeId" autofocus="autofocus" onkeypress="return isNumber(this,event)">
						</div>
						<div class="form-group">
							<label>Enter name </label>
							<input class="form-control" id="roleName" placeholder="Enter Name"
								type="text" name="userTypeName">
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
						<display:table class="table table-bordered table-hover flip-content"  export="false" name="userTypeList" 
								requestURI="${pageContext.request.contextPath}/userType/searchUserType.do" pagesize="10" defaultsort="1">
					        <display:column property="id" title="Id"
									sortable="true" href="${pageContext.request.contextPath}/userType/editUserType.do" paramId="id"
									paramProperty='id' />
							<display:column property="name" title="Name"
								sortable="true" href="${pageContext.request.contextPath}/userType/editUserType.do" paramId="id"
								paramProperty='id' />
							<display:column property="description" title="Description"
								sortable="true" href="${pageContext.request.contextPath}/userType/editUserType.do" paramId="id"
								paramProperty='id' />
								<display:column title="" sortable="true"
								href="${pageContext.request.contextPath}/userType/deleteUserType.do"
								paramId="id" paramProperty='id'>
								<button type="submit" class="btn btn-primary"style="padding: 0.09rem 0.8rem ;margin-bottom: 0px">Delete</button>
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
		$("#liUsertype").addClass("active");
	});
});
</script>