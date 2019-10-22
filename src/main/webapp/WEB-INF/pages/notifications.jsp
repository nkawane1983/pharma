<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<article class="content dashboard-page">
	<section class="section">
		<div class="row sameheight-container">

			<div class="col-md-12">
				<div class="card card-block ">
					<div class="subtitle-block">
						<h3 class="subtitle">Notification</h3>
					</div>
					<sec:authorize
						access="hasAnyRole('ROLE_Supervisor','ROLE_Users','ROLE_Managers')">
						<form action="${pageContext.request.contextPath}/cashing/sendRequstToHO.pg" role="form" id="requstform">
						<div class="col-md-12">
							<c:if test="${not empty notifications}">
							<div style="margin-bottom: 20px;">
							<fieldset style="border-color: #d9534f !important;">
							<legend class="text-danger">Note</legend>
							<span class="text-danger"> 
							If you have any problem regarding Zreading,Cash,Cheques,Cards,Coupuns and Paidouts</b> the please press the <b>"Send Request to..."</b>.
							</span>
							</fieldset>
							</div>	<table class="table"
									style="margin-bottom: 12px; border: 1px solid #52BCD3;">
									<thead>
										<tr>
											<th class="center">No.</th>
											<th>Date</th>
											<th class="center">Till</th>
											<th class="center">Ref No.</th>
											<th class="right">Zreading</th>
											<th class="right">Cash</th>
											<th class="right">Cheques</th>
											<th class="right">Cards</th>
											<th class="right">Coupuns</th>
											<th class="right">Paidouts</th>
											<th class="center">Action/Status</th>
											<th class="center">Head office</th>
										</tr>
									</thead>
									<c:forEach items="${notifications}" var="notification"
										varStatus="count">

										<tr>
											<td style="border: 1px solid #52BCD3;" class="center">${count.index+1}</td>
											<td style="border: 1px solid #52BCD3;">${notification[1]}</td>
											<td style="border: 1px solid #52BCD3;" class="center">${notification[2]}</td>
											<td style="border: 1px solid #52BCD3;" class="center">${notification[3]}</td>
											<td style="border: 1px solid #52BCD3;" class="right">${notification[4]}</td>
											<td style="border: 1px solid #52BCD3;" class="right">${notification[5]}</td>
											<td style="border: 1px solid #52BCD3;" class="right">${notification[6]}</td>
											<td style="border: 1px solid #52BCD3;" class="right">${notification[7]}</td>
											<td style="border: 1px solid #52BCD3;" class="right">${notification[8]}</td>
											<td style="border: 1px solid #52BCD3;" class="right">${notification[9]}</td>
											<c:choose>
											<c:when test="${notification[10]==-1}">
												<c:if test="${notification[12]==0}">
												<td style="border: 1px solid #52BCD3;" class="center"><span class="btn btn-success  btn-sm" style="padding: 0.2rem 0.2rem;">pending</span></td>
												<td style="border: 1px solid #52BCD3;" class="center"><span class="btn btn-success  btn-sm" style="padding: 0.2rem 0.2rem;">Request pending</span></td>
												</c:if>
												<c:if test="${notification[12]==1}">
												<td style="border: 1px solid #52BCD3;" class="center"><span class="btn btn-warning  btn-sm" style="padding: 0.2rem 0.2rem;">Working By</span></td>
												<td style="border: 1px solid #52BCD3;" class="center"><span class="btn btn-warning  btn-sm" style="padding: 0.2rem 0.2rem;">${notification[11]}</span></td>
												</c:if>
											</c:when>
											<c:otherwise>
													<td style="border: 1px solid #52BCD3;" class="center">
														<a href="${pageContext.request.contextPath}/cashing/editCashingPg.do?id=${notification[0]}&msg=message">
														<span class="btn btn-primary"
															style="padding: 0.09rem 0.8rem; margin-bottom: 0px">Incomplete Till</span>
														</a>
													</td>
														
														<c:if test="${notification[12]==2}">
														<td style="border: 1px solid #52BCD3;" class="center"><span class="btn btn-info  btn-sm" style="padding: 0.2rem 0.2rem;">Approved</span></td>
														</c:if>
														<c:if test="${notification[12]==0}">
														<td class="center"><a
														href="#" onclick="javascript:amendmentTill();setCashId(${notification[0]})"><button
														type="button" class="btn btn-primary"
														style="padding: 0.09rem 0.8rem; margin-bottom: 0px">Send
														Request to...</button></a></td>
														</c:if>
											</c:otherwise>
											</c:choose>
									
											

										</tr>

									</c:forEach>
								</table>

							</c:if>
							<c:if test="${empty notifications}">
								<span class="text-info">No record Found.</span>

							</c:if>
						</div>
						
						<section>
	<!-- ------------------------------------------------------- -->
			<!-- ---------------SendReqToHo------------------------- -->
			<!-- ------------------------------------------------------- -->

			<div class="modal fade" id="SendReqToHo-modal" tabindex="-1"
				role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog"
					style="max-width: 465px; margin: 125px auto;">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">

							<h6 class="modal-title" id="myModalLabel">Comment:-</h6>
						</div>
						<div class="modal-body" style="padding: 0px; height:180px;">
							<div class="col-md-12">
								<div class="form-group">
									<label for="discrepancy">Please enter a reason for
										send request</label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
								
										<textarea rows="3" class="form-control boxed" id="noteRequest" name="noteRequest"></textarea>
									<div id="errmsggReq"></div>
								</div>
							</div>
						
						</div>
						<div class="modal-footer" style="padding-bottom: 0px;padding-top: 7px;">
							<div class="col-md-12">
								<div class="form-group pull-right">

									
										<input type="submit" class="btn btn-primary" value="add"
											name="update" onclick="javascript: return  submitRequest()" />
									
								</div>
							</div>
							</div>
					</div>
				</div>
			</div>
			</section>
			<input type="hidden" id="both" value="" />
		<input type="hidden" id="managername" name="manager" class="form-control" />
		<input type="hidden" id="cashId" name="cashId" class="form-control" />
						</form>
					</sec:authorize>
					<sec:authorize
						access="hasAnyRole('ROLE_SuperAdmin','ROLE_Admin')">
						<div class="col-md-12">
						<c:if test="${not empty notifications}">
							<table class="table"
								style="margin-bottom: 12px; border: 1px solid #52BCD3;">
								<thead>
									<tr>
										<th class="center">No.</th>
										<th class="center">BranchName</th>
										<th>Date</th>
										<th class="center">Till</th>
										<th class="center">Ref No.</th>
										<th class="center">Comment</th>
										<th class="center">Manager</th>
										<th class="center">Status</th>
										<th class="center">Action</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${notifications}" var="notification"
										varStatus="count">
									<tr>
										<td class="center">${count.index+1}</td>
										<td style="border: 1px solid #52BCD3;">${notification[16]}</td>
										<td style="border: 1px solid #52BCD3;">${notification[1]}</td>
										<td style="border: 1px solid #52BCD3;" class="center">${notification[2]}</td>
										<td style="border: 1px solid #52BCD3;" class="center">${notification[3]}</td>
										<td class="center"><c:out value="${notification[13]}"/></td>
										<td class="center"><c:out value="${notification[14]}"/></td>
										<c:if test="${notification[12]==0}">
										<td class="center"><span class="btn btn-success  btn-sm"
											style="padding: 0.2rem 0.2rem;">pending</span></td>
										</c:if>
										
										<c:if test="${notification[12]==1}">
										<td class="center"><span class="btn btn-success  btn-sm"
											style="padding: 0.2rem 0.2rem;">Working By: <c:out value="${notification[11]}"/></span></td>
										</c:if>
											<td class="center"><a
											href="${pageContext.request.contextPath}/cashing/updateCashingTillPg.do?id=${notification[0]}&msg=${notification[15]}"><button
													type="button" class="btn btn-primary"
													style="padding: 0.09rem 0.8rem; margin-bottom: 0px">Amendment
													Till</button></a></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
							</c:if>
							<c:if test="${empty notifications}">
								<span class="text-info">No record Found.</span>

							</c:if>
						</div>
					</sec:authorize>
				</div>
			</div>
		</div>
	</section>
	
</article>

<script>
function submitRequest() {
	var strObj=$('#noteRequest').val();
	if (strObj.trim() == '') {
		document.getElementById("errmsggReq").innerHTML = "Please enter a reason !!!";
		document.getElementById("errmsggReq").style.color = "red";
		return false;
	}

	return true;
}
function setCashId(str) {
	document.getElementById("cashId").value=str;
}
</script>