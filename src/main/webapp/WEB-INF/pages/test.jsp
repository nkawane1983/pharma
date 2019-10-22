<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
#ft {
	padding-top: 15px;
}

input {
	text-align: right;
}

.subtitle-block {
	padding-bottom: 5px;
	margin-bottom: 5px;
}

.btn {
	margin-bottom: 5px;
	line-height: 1;
}

.table th, .table td {
	padding: 0.25rem
}

#pharmtbody tr td {
	margin-bottom: 10px;
}
</style>
<article class="content dashboard-page">
	<section class="section">
		<div class="row sameheight-container">

			<div class="col-md-12">
				<div class="card card-block ">
					<div class="subtitle-block">
						<h3 class="subtitle">Default Setting</h3>
					</div>

					<div class="col-md-12">

						<div class="col-md-4">
							<div class="form-group row"
								style="margin-top: 0.5rem; margin-bottom: 0.5rem;">
								<label class="col-sm-5 form-control-label"
									for="formGroupExampleInput2" style="margin-top: 9px;">Select
									Group:</label>
								<form action="${pageContext.request.contextPath}/settingPg.do"
									method="POST">
									<div class="col-sm-8" style="margin-left: -46px;">
										<select class="form-control" id="gid" name="groupid"
											onchange="this.form.submit()">
											<option value="0">----Select----</option>
											<c:forEach items="${groupList}" var="groupList">
												<option value="${groupList.id}">${groupList.description}</option>
											</c:forEach>
										</select>
									</div>
								</form>
							</div>

						</div>

					</div>


					<form
						action="${pageContext.request.contextPath}/manageSettingPg.do"
						method="post" id="formuser" onsubmit="return checkMakeFalse()">
						<div class="col-md-12">

							<div class="subtitle-block">
								<h3 class="subtitle">
									User Access
								</h3>
							</div>
							<div >
								<input type="hidden" id="groupid1" value="${groupid}"
									name="groupid1" />
								<div class="form-group col-md-3">
									<label>User Type</label> <select class="form-control"
										name="utype" onchange="selctOrBranch(this)">
										<option>----Select----</option>
										<c:forEach items="${usertype}" var="usertype">
											<c:if test="${usertype.name!='SuperAdmin' OR usertype.name!='Admin'}">
														
											<option value="${usertype.name}">${usertype.name}</option>
											</c:if>
										</c:forEach>
									</select>
								</div>
								<div class="form-group col-md-3" id="branchlist" hidden="true">
									<label>Select Branch</label>
									<div>
										<select class="form-control" id="branchlist_AJ" name="branchId">

										</select>
									</div>
								</div>
								<div class="form-group col-md-2">
									<label>Start Date</label> <input class="form-control"
										id="sdate" placeholder="DD/MM/YYYY" type="text" name="sdate"
										style="text-align: left;" onclick="return myFunction1()">
								</div>
								<div class="form-group col-md-2">

									<p class="text-muted" style="margin-top: 30px;">
										<input type="checkbox" value="True" name="markdelete" />
										&nbsp;&nbsp;Is Mark Delete?
									</p>
								</div>
								<div class="form-group col-md-2">
									<br>
									<button type="button" name="add" value="add"
										class="btn btn-primary" onclick="this.form.submit()">Add</button>
								</div>
								<c:if test="${not empty defaultvalue}">
									<table class="table"
										style="margin-bottom: 12px; border: 1px solid #52BCD3;">
										<thead>
											<tr>
												<th>No</th>
												<th>Branch Name</th>
												<th>Start Date</th>
												<th>Value</th>
												<th>Mark Delete</th>
											</tr>
										</thead>
										<c:forEach items="${defaultvalue}" var="defaultvalue">
											<c:if test="${defaultvalue[6]=='useraccess'}">
												<tr>
													<td style="border: 1px solid #52BCD3;">${defaultvalue[0]}</td>
													<td style="border: 1px solid #52BCD3;">${defaultvalue[1]}</td>
													<td style="border: 1px solid #52BCD3;">${defaultvalue[2]}</td>
													<td style="border: 1px solid #52BCD3;">${defaultvalue[5]}</td>
													<c:choose>
														<c:when test="${defaultvalue[3]=='True'}">
														<td style="border: 1px solid #52BCD3;"><input type="checkbox" value="${defaultvalue[4]},${defaultvalue[7]}" name="valueId" checked="checked" onclick="deleteUserSetting(this)"/>
														<input type="hidden" value="No" id="mrkuser"/>
														</td>
														</c:when>
														<c:otherwise>
														<td style="border: 1px solid #52BCD3;"><input type="checkbox"  value="True" name="valueId" disabled/></td>
														</c:otherwise>
													</c:choose>
												</tr>
											</c:if>
										</c:forEach>
									</table>
								</c:if>
							</div>
						</div>
					</form>
				</div>

			</div>

		</div>
		<fmt:formatDate var="fmtDate" value="<%=new java.util.Date()%>"
			pattern="dd/MM/yyyy" />
		<input type="hidden" id="today" value="${fmtDate}" />
	</section>
</article>
<script>
	$(function() {
		$('#sdate').datepicker({
			format : 'dd/mm/yyyy'
		});
		$('#gid').val($('#groupid1').val());
	});
	function  checkMakeFalse()
	{
		alert($('#mrkuser').val())
		return false;
	}
	function selctOrBranch(utype) {

		if ($('#gid').val() == 0) {
			alert('Please Select Group');
		} else {
			if (utype.value == 'Users' || utype.value == 'Managers') {

				$("#branchlist_AJ")
						.load(
								"${pageContext.request.contextPath}/branch/getBranches.do",
								{
									id : $('#gid').val()
								});
				$('#branchlist').removeAttr("hidden");
				alert('Please Select branch');
			} else {

			}
		}
	}
	function deleteUserSetting(str)
	{
		window.location.href = "${pageContext.request.contextPath}/deleteSettingPg.do?valId="+str.value
	}
	function myFunction1() {
		if ($('#sdate').val() < $('#today').val()) {

			$('#sdate').attr('required', 'required');
			$('#sdate').val('');
			return false;
		}
	}
</script>