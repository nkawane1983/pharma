<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<spring:url value="/resources/js/Chart.min.js" var="chartJs" />
<script src="${chartJs}"></script>

<article class="content dashboard-page">
	<section class="section" style="margin-bottom: -15px;">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="title-block" style="margin-bottom: -4px;">
						<h4 class="title">Welcome Pharm@dmin</h4>
					</div>
				</div>
			</div>
		</div>
	</section>


	<section class="section" style="margin-bottom: -15px;">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<sec:authorize
						access="hasAnyRole('ROLE_Users','ROLE_Managers','ROLE_Supervisor')">
						<div class="col-sm-5 form-group row" style="margin-bottom: 0rem;">
							<label class="col-sm-5 form-control-label"
								for="formGroupExampleInput2" style="margin-top: 11px;">Select
								Group:</label>
							<div class="col-sm-7" style="margin-left: -67px;">
								<select class="form-control" id="gid"
									onchange="getBranches(this)" name="groupid">
									<c:forEach items="${groupDetails}" var="groupDetails">
										<option value="${groupDetails.getGroupId()}">${groupDetails.getGroupName()}
										</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="col-sm-5 form-group row" style="margin-bottom: 0rem;">
							<label class="col-sm-5 form-control-label"
								for="formGroupExampleInput2" style="margin-top: 11px;">Select
								Branch:</label>
							<div class="col-sm-7" style="margin-left: -67px;">
								<select class="form-control" id="branchlist_AJ"
									onchange="getBranchesbyid(this)">
									<c:forEach items="${branchlist}" var="branchlist">
										<option value="${branchlist.getBrnachid()}">${branchlist.getBranchName()}</option>
									</c:forEach>

								</select>
							</div>
						</div>
					</sec:authorize>

					<sec:authorize access="hasAnyRole('ROLE_Admin','ROLE_SuperAdmin')">
						<div class="col-sm-3">
							<label class="form-control-label" for="formGroupExampleInput2">Select
								Group</label> <select class="form-control" id="gid"
								onchange="getBranches(this)" name="groupid">
								<sec:authorize
									access="hasAnyRole('ROLE_Admin','ROLE_SuperAdmin')">
									<option value="0">----All Group----</option>
								</sec:authorize>
								<c:forEach items="${groupDetails}" var="groupDetails">
									<option value="${groupDetails.getGroupId()}">${groupDetails.getGroupName()}
									</option>
								</c:forEach>
							</select>

						</div>
						<div class="col-sm-3">
							<label class="form-control-label" for="formGroupExampleInput2">Select
								Branch</label> <select class="form-control" id="branchlist_AJ"
								onchange="getBranchesbyid(this)">
								<sec:authorize
									access="hasAnyRole('ROLE_Admin','ROLE_SuperAdmin')">
									<option value="0">----All Branch----</option>
								</sec:authorize>
								<c:forEach items="${branchlist}" var="branchlist">
									<option value="${branchlist.getBrnachid()}">${branchlist.getBranchName()}</option>
								</c:forEach>

							</select>
						</div>

						<div class="col-sm-6">

							<div class="col-sm-4 form-group">


								<fmt:parseDate pattern="yyyy-MM-dd" value="${fdate}"
									var="parsedDate" />
								<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy"
									var="fdate" />
								<label>Date Range From</label> <input class="form-control"
									id="from" placeholder="dd/MM/yyyy" type="text" name="fDate"
									onchange="myFunction1()" value="${fdate}"
									style="text-align: left;">

							</div>
							<div class="col-sm-4 form-group">
								<fmt:parseDate pattern="yyyy-MM-dd" value="${enddate}"
									var="parsedDate1" />
								<fmt:formatDate value="${parsedDate1}" pattern="dd/MM/yyyy"
									var="edate" />
								<label>Date Range To</label> <input class="form-control" id="to"
									placeholder="dd/MM/yyyy" type="text" name="tDate"
									onchange="checkdateRange()" value="${edate}"
									style="text-align: left;">

							</div>
							<div class="col-sm-4 form-group" style="padding-top: 18px;">
								<button type="button" class="btn btn-primary"
									data-toggle="modal" onclick="getNhsBYDateRang()">Search</button>
							</div>

						</div>
					</sec:authorize>
				</div>
			</div>
		</div>
	</section>


	<section class="section" style="margin-bottom: -15px;">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="col-md-12" id="bran">
						<fmt:formatDate var="fmtDate" value="<%=new java.util.Date()%>"
							pattern="M" />

						<table class="table">
							<thead>
								<tr>
									<th
										style="width: 5%; text-align: left; border: 1px solid #52BCD3;">ID</th>
									<th
										style="width: 30%; text-align: left; border: 1px solid #52BCD3;">&nbsp;&nbsp;Branch
										Name</th>
									<th
										style="width: 10%; text-align: center; border: 1px solid #52BCD3;">Cashing</th>
									<th
										style="width: 10%; text-align: center; border: 1px solid #52BCD3;">NHS</th>
									<th
										style="width: 10%; text-align: center; border: 1px solid #52BCD3;">Banking</th>
									<th
										style="width: 10%; text-align: center; border: 1px solid #52BCD3;">
										Period</th>
										<th
										style="width: 10%; text-align: center; border: 1px solid #52BCD3;">
										Email Id</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${dashgroup}" var="dash" varStatus="count">
									<tr>
										<td
											style="width: 5%; text-align: left; border: 1px solid #52BCD3;"><a
											style="text-decoration: none;">${dash[0]}</a></td>
										<td style="width: 30%; border: 1px solid #52BCD3;"><a
											style="text-decoration: none;">&nbsp;&nbsp;${dash[1]}</a></td>
										<td
											style="width: 10%; text-align: center; border: 1px solid #52BCD3;">
											<fmt:parseDate value="${dateBefore}" var="dBefore"
												pattern="dd/MM/yyyy" /> <c:choose>

												<c:when test="${dash[2] =='No' || dash[2] !='YES'}">
													<p
														style="margin-left: 1px; margin-right: 1px; color: white; background-color: #ff0000;">
														<c:out value="${dash[2]}" />
													</p>

												</c:when>
												<c:otherwise>
													<p
														style="margin-left: 1px; margin-right: 1px; color: white; background-color: #2d862d;">
														Yes</p>
												</c:otherwise>
											</c:choose>

										</td>
										<td
											style="width: 10%; text-align: center; border: 1px solid #52BCD3;">
											<c:choose>
												<c:when test="${dash[3]=='No' || dash[3] !='YES'}">
													<p
														style="margin-left: 1px; margin-right: 1px; color: white; background-color: #ff0000;">
														<c:out value="${dash[3]}" />
													</p>

												</c:when>
												<c:otherwise>
													<p
														style="margin-left: 1px; margin-right: 1px; color: white; background-color: #2d862d;">
														Yes</p>
												</c:otherwise>
											</c:choose>
										</td>

										<td
											style="width: 10%; text-align: center; border: 1px solid #52BCD3;">
											<c:choose>
												<c:when test="${dash[4] =='No'  || dash[4] !='YES'}">
													<p
														style="margin-left: 1px; margin-right: 1px; color: white; background-color: #ff0000;">
														<c:out value="${dash[4]}" />
													</p>

												</c:when>
												<c:otherwise>
													<p
														style="margin-left: 1px; margin-right: 1px; color: white; background-color: #2d862d;">Yes

													</p>
												</c:otherwise>
											</c:choose>
										</td>
										<td
											style="width: 10%; text-align: center; border: 1px solid #52BCD3;">
											<c:choose>
												<c:when test="${dash[5]==fmtDate}">
													<p
														style="background-color: #2d862d; margin-left: 1px; margin-right: 1px; color: white;">${dash[5]}</p>

												</c:when>
												<c:otherwise>
													<p
														style="background-color: #ff0000; margin-left: 1px; margin-right: 1px; color: white;">${dash[5]}</p>
												</c:otherwise>
											</c:choose>
										</td>
										<td
											style="width: 100%; text-align: left; border: 1px solid #52BCD3;"><a href="mailto:<c:out value="${dash[6]}"/>"
													>${fn:substring(dash[6], 0, 30)}</a>
											</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</div>
	</section>


	<section class="section" style="margin-bottom: -15px;">
		<div class="row sameheight-container">
			<div id="nhs">
				<c:forEach items="${nhsStats}" var="nhslist">


					<div class="col col-xs-12 col-sm-12 col-md-6 col-xl-5 stats-col">
						<div class="card stats" data-exclude="xs">
							<div class="card-block">
								<div class="title-block">
									<h4 class="title">NHS Stats</h4>

								</div>

								<div class="row row-sm stats-container">
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-rocket"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[0]}</div>
											<div class="name">Paid PAPER Form</div>
											<div></div>
										</div>
										<progress class="progress stat-progress" value="75" max="100">
											<!-- 									<div class="progress"> -->
											<!-- 										<span class="progress-bar" style="width: 75%;"></span> -->
											<!-- 									</div> -->
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-shopping-cart"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[1]}</div>
											<div class="name">Paid PAPER Items</div>
										</div>
										<progress class="progress stat-progress" value="25" max="100">
											<!-- 									<div class="progress"> -->
											<!-- 										<span class="progress-bar" style="width: 25%;"></span> -->
											<!-- 									</div> -->
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-rocket"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[2]}</div>
											<div class="name">Paid EPS Form</div>
											<div></div>
										</div>
										<progress class="progress stat-progress" value="75" max="100">
											<!-- 									<div class="progress"> -->
											<!-- 										<span class="progress-bar" style="width: 75%;"></span> -->
											<!-- 									</div> -->
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-shopping-cart"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[3]}</div>
											<div class="name">Paid EPS Items</div>
										</div>
										<progress class="progress stat-progress" value="25" max="100">
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-rocket"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[4]}</div>
											<div class="name">Exempt PAPER Form</div>
											<div></div>
										</div>
										<progress class="progress stat-progress" value="75" max="100">
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-shopping-cart"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[5]}</div>
											<div class="name">Exempt PAPER Items</div>
										</div>
										<progress class="progress stat-progress" value="25" max="100">
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-rocket"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[6]}</div>
											<div class="name">Exempt EPS Form</div>
											<div></div>
										</div>
										<progress class="progress stat-progress" value="75" max="100">
										</progress>
									</div>
									<div class="col-xs-12 col-sm-6 stat-col">
										<div class="stat-icon">
											<i class="fa fa-shopping-cart"></i>
										</div>
										<div class="stat">
											<div class="value">${nhslist[7]}</div>
											<div class="name">Exempt EPS Items</div>
										</div>
										<progress class="progress stat-progress" value="25" max="100">

										</progress>
									</div>

								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<div class="col col-xs-12 col-sm-12 col-md-6 col-xl-7 history-col">
				<div class="card">
					<div class="card-header card-header-sm bordered">
						<div class="header-block">
							<h3 class="title">Summary</h3>
						</div>
						<ul class="nav nav-tabs pull-right" role="tablist">
							<li class="nav-item"><a class="nav-link active"
								href="#taking" role="tab" data-toggle="tab">Scripts</a></li>
							<li class="nav-item"><a class="nav-link" href="#downloads"
								role="tab" data-toggle="tab">Takings</a></li>
						</ul>
					</div>
					<div class="card-block">
						<div class="tab-content">
							<div role="tabpanel" class="tab-pane active fade in" id="taking">
								<p class="title-description">Last 7 Days Scripts</p>
								<div id="dChartDiv" class="chart-responsive">
									<canvas id="NChartArea"
										style="display: block; width: 589px; height: 253px;"></canvas>
								</div>
							</div>
							<div role="tabpanel" class="tab-pane fade" id="downloads">
								<p class="title-description">Last 7 Days Takings </p>
								<div class="chart-responsive">
									<canvas id="TChartArea"
										style="display: block; width: 589px; height: 253px;"></canvas>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
		<jsp:include page="notificationModel.jsp" />
</article>

<script>
	
	$(function() {
		$('#from').datepicker({
			format : 'dd/mm/yyyy'
		});
		$('#to').datepicker({
			format : 'dd/mm/yyyy'
		});
		var brnach = $('#branchlist_AJ').val();
		var group = $('#gid').val();

		renderNChart(group, brnach);
		

		if ($('#notification').val() > 0) {
			$('#notification-modal').modal({
				backdrop : 'static',
				keyboard : false
			

			})
		}

	});

	function getBranches(groupid) {

		var gid = groupid.value;

		if (gid != 0) {
			var bid = 0;
			$("#branchlist_AJ").load(
					"${pageContext.request.contextPath}/branch/getBranches.do",
					{
						id : gid
					});
			$("#bran").load(
					"${pageContext.request.contextPath}/getDashboardList.do", {
						gid : gid,
						bid : bid

					});
			$("#nhs").load(
					"${pageContext.request.contextPath}/getNhsStateList.do", {
						gid : gid,
						bid : bid,
						fdate : $('#from').val(),
						tdate : $('#to').val()
					});
			renderNChart(gid, bid);
			//renderTChart(gid, bid);
		} else {
			location.reload();
		}
	}

	function getBranchesbyid(bid) {

		var gid = $('#gid').val();
		var bid = bid.value;
		$("#bran").load(
				"${pageContext.request.contextPath}/getDashboardList.do", {
					gid : gid,
					bid : bid
				});
		$("#nhs").load("${pageContext.request.contextPath}/getNhsStateList.do",
				{
					gid : gid,
					bid : bid,
					fdate : $('#from').val(),
					tdate : $('#to').val()
				});
		renderNChart(gid, bid);
		}
	function getNhsBYDateRang() {

		var gid = $('#gid').val();
		var bid = $('#branchlist_AJ').val();
		$("#bran").load(
				"${pageContext.request.contextPath}/getDashboardList.do", {
					gid : gid,
					bid : bid
				});
		$("#nhs").load("${pageContext.request.contextPath}/getNhsStateList.do",
				{
					gid : gid,
					bid : bid,
					fdate : $('#from').val(),
					tdate : $('#to').val()
				});
		renderNChart(gid, bid);
		}
	
	
</script>