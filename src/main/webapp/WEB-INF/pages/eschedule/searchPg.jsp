<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<article class="content dashboard-page">
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item" data-exclude="xs,sm,lg">
					<div class="title-block">
						<h3 class="title">
							Search<a
								href="${pageContext.request.contextPath}/eschedule/manageFilePg.do"
								class="btn btn-primary pull-right" data-toggle="tooltip"
								title="Add">Import DAT</a>
						</h3>
					</div>
					<form class="form-inline" role="form" action="" method="post">
						<div class="form-group col-xs-3">
							<label>Group</label> <Select class="form-control" name="groupid"
								id="groupid" onchange="getBranches(this)">


								<option value="0">----All Group----</option>
								<c:forEach items="${groupDetails}" var="groupDetails">
									<option value="${groupDetails.getGroupId()}">${groupDetails.getGroupName()}
									</option>
								</c:forEach>

							</select>

						</div>
						<div class="form-group col-xs-3">
							<label>Branch</label> <Select class="form-control"
								id="branchlist_AJ" name="branchid">
								<option value="0">---All Branch---</option>
								<c:forEach items="${branchASlist}" var="branchlist">
									<option value="${branchlist.getBrnachid()}">${branchlist.getBranchName()}</option>
								</c:forEach>
							</Select>

						</div>
						<div id="monthyearlist">
							<div class="form-group col-xs-2">
								<label>Month</label> <Select class="form-control" name="month"
									id="month">
									<option value="0">----Select----</option>
									<c:forEach items="${monthList}" var="monthList">
										<option value="${monthList}">${monthList}</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group col-xs-2">
								<label>Year</label> <Select class="form-control" name="year"
									id="year">
									<option value="0">----Select----</option>
									<c:forEach items="${yearList}" var="yearList">

										<option value="${yearList}">${yearList}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group col-xs-2">
							<br>
							<button type="submit" class="btn btn-primary" data-toggle="modal">Search</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<section class="section" style="margin-top: -25px;">
		<div class=" row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="PpaMasterReportList"
							requestURI="${pageContext.request.contextPath}/cashing/searchCashing.do"
							pagesize="31" defaultsort="1" id="PpaMasterReportList">
							<display:column title="Branch No." sortable="true"
								property="internalId"
								href="${pageContext.request.contextPath}/report/monthlyCashReportPg.do" />

							<display:column title="Branch Name" sortable="true"
								property="branchName" />
							<display:column title="Payment Date" sortable="true"
								property="paymentDate" format="{0,date,dd/MM/yyyy}" />
							<display:column title="Branch Code" sortable="true"
								property="mbOcsCode" />
							<display:column title="Payment" sortable="true"
								property="mbPayment" />
						</display:table>
					</div>
					<form
						action="${pageContext.request.contextPath}/eschedule/generateReportPDFPg.do"
						id="from1" method="post">
						<input type="hidden" name="g" value="${groupid}"> <input
							type="hidden" name="b" value="${branchid}"> <input
							type="hidden" name="m" value="${month}"> <input
							type="hidden" name="y" value="${year}"> <input
							type="hidden" name="type" id="filetype"> <input
							type="hidden" name="reportTypeObj" value="${reportTypeObj}">
						<div >
							Export options: <i class="fa fa-file-pdf-o"></i><button
								   class="btn btn-link" onclick="setSubmit(this)" 
								id="pdf"
								 > PDF</button>
								|&nbsp;<i class="fa fa-file-excel-o"></i> <button
								class="btn btn-link" onclick="setSubmit(this)" 
								id="csv"  
								>CSV</button>


						</div>

					</form>
				</div>
			</div>
		</div>


	</section>
</article>
<input type="hidden" id="gid" value="${groupid}">
<input type="hidden" id="bid" value="${branchid}">
<input type="hidden" id="monthObj" value="${month}">
<input type="hidden" id="yearObj" value="${year}">
<script type="text/javascript">
	$(function() {
		$("ul li").removeClass("active");
		$("#lieschedule").addClass("active");
		$("#groupid").val($("#gid").val());
		$("#branchlist_AJ").val($("#bid").val());
		$("#month").val($("#monthObj").val());
		$("#year").val($("#yearObj").val());
	});
	function getBranches(groupid) {

		if (groupid.value != 0) {
			var gid = groupid.value;
			$("#branchlist_AJ").load(
					"${pageContext.request.contextPath}/branch/getBranches.do",
					{
						id : gid
					});
			//getMonthYearAsList(gid);
		} else {
			groupid.form.submit();
		}
	}
	function getMonthYearAsList(str) {

		if (str != '' && str != 0) {
			$
					.ajax({
						url : '${pageContext.request.contextPath}/eschedule/getMonthYearAsListByAjax.do',
						data : {
							groupid : str
						},
						async : false,
						success : function(data) {
							$("#monthyearlist").html(data);
						},

					});
		} else {
			alert('Please Select Group');
		}
	}
	function setSubmit(str) {
		$('#filetype').val(str.id)
		str.form.submit();
	}
</script>