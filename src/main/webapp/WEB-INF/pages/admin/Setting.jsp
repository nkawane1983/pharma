
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<article class="content dashboard-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">

			<div class="col-md-12">
				<div class="card card-block ">
					<div class="subtitle-block">
						<h3 class="subtitle">Setting</h3>
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
									Cashing Setting
								</h3>
							</div>
							<div >
								<input type="hidden" id="groupid1" value="${groupid}"
									name="groupid1" />
								<div class="form-group col-md-3">
									<label>FloatValue</label> <input class="form-control"
										id="dfvalue" placeholder="Enter float" type="text" name="dafultvalue"
										style="text-align: right ;" required="required">
								</div>
								
								<div class="form-group col-md-2">
									<label>Start Date</label> <input class="form-control"
										id="sdate" placeholder="DD/MM/YYYY" type="text" name="sdate"
										style="text-align: left;" onclick="return myFunction1()" required="required">
								</div>
								<div class="form-group col-md-2">

									<p class="text-muted" style="margin-top: 30px;">
										<input type="checkbox" value="True" name="markdelete" id="mrk"/>
										&nbsp;&nbsp;Is Mark Delete?
									</p>
								</div>
								<div class="form-group col-md-2">
									<br>
									<button type="button" name="add" value="add"
										class="btn btn-primary" onclick="checkMakeFalse()">Add</button>
								</div>
								
							</div>
						</div>
					</form>
				</div>

			</div>

		</div>
	
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
		if($('#mrk').prop("checked") == false)
		{
		    alert('You must check Mark Delete.');
		    return false;
		}
		 return true;
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

</script>