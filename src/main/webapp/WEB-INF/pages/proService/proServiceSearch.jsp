<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
 <%@ taglib uri="http://displaytag.sf.net" prefix="display"%> 
<%-- <%@ taglib prefix="sec" --%>
<%-- 	uri="http://www.springframework.org/security/tags"%> --%>


<article class="content dashboard-page">
	<section class="section">
        <div class="row sameheight-container">
            <div class="col-md-12">
                <div class="card card-block sameheight-item">
                    <div class="title-block">
                        <h3 class="title"> Professional Services
							<a href="#" class="btn btn-primary pull-right"
								data-toggle="tooltip" title="Add User">Add</a>

						</h3>
                    </div>
                    	<form:form class="form-inline" role="form" action="" method="GET">
						<div class="form-group">
							<label>Date Range</label> <input class="form-control" id="from"
								placeholder="MM/DD/YYYY" type="text" name="from"onchange="myFunction1()">
								
						</div>
						<div class="form-group">
							<label>-</label> 
							<input class="form-control" id="to"
								placeholder="MM/DD/YYYY" type="date" name="to"
								onchange="myFunction1()">
								
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
						<display:table class="table table-bordered table-hover flip-content"  export="false" name="usersList" 
								requestURI="${pageContext.request.contextPath}/user/searchUser.do" pagesize="10" defaultsort="1">
					        <display:column property="usrId" title="User Id" sortable="true"  
					       		href="${pageContext.request.contextPath}/user/editUser.do" paramId="usrId"  paramProperty='usrId' />
					        <display:column property="usrName" title="User Name" sortable="true"   
					        	href="${pageContext.request.contextPath}/user/editUser.do" paramId="usrId"  paramProperty='usrId'  />
							<display:column property="usrEmail" title="Email" sortable="true"   
								href="${pageContext.request.contextPath}/user/editUser.do" paramId="usrId"  paramProperty='usrId'  />
					        <display:column property="usrMobile" title="Mobile Number" sortable="true" 
					        	href="${pageContext.request.contextPath}/user/editUser.do" paramId="usrId"  paramProperty='usrId'  />
					        <display:column property="usrDepartmentCd" title="Department" sortable="true" 
					        	href="${pageContext.request.contextPath}/user/editUser.do" paramId="usrId"  paramProperty='usrId'  />
					    </display:table>
					</div>
                </div>
            </div>
        </div>
    </section>
</article>

<script type="text/javascript">

	$(function() {
		$('#from').datepicker({
			format : 'mm/dd/yyyy'
		});
		$('#to').datepicker({
			format : 'mm/dd/yyyy'
		});
		$("ul li").removeClass("active");
		$("#liProfessionService").addClass("active");
	});
</script>
<script type="text/javascript">

	

	function myFunction1() {
		if ($('#from').val() == '') {
			$('#from').attr('required','required');
			$('#to').attr('required','required');
			$('#from').val('');
			
			return false;
		}
		if ($('#from').val() > $('#to').val()) {
			
			$('#to').attr('required','required');
			$('#to').val('');
			return false;
		}
	}

</script>