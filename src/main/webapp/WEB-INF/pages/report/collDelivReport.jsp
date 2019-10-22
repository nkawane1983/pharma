<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>



<article class="content dashboard-page">
	<c:if test="${empty mode}">
		
		<jsp:include page="SearchReport.jsp" />
		<section>
			<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
				totals.setTotalLabel("full total");
				pageContext.setAttribute("totals", totals);</jsp:scriptlet>
			<div class="row sameheight-container">
				<div class="col-md-12">
					<div class="card card-block">
						<div class="table-flip-scroll">
							<display:table
								class="table table-bordered table-hover flip-content"
								name="colldellist"
								requestURI="${pageContext.request.contextPath}/report/colldelvReportPg.do"
								 id="colldellist">
								<display:column title="Branch No." property="branchInternalId">

								</display:column>
								<display:column property="branchName" title="Branch Name"
									paramId="id" paramProperty='branchid'
									href="${pageContext.request.contextPath}/report/monthlycolldelvReportPg.do" >

								</display:column>
								<display:column property="totalColl" title="Collection"
									class="right" headerClass="right" format="{0,number,#,##0}">

								</display:column>
								<display:column property="totaldelv" title="Delivery"
									class="right" headerClass="right" format="{0,number,#,##0}">

								</display:column>
								<display:column property="total" title="Total"
									class="right" headerClass="right" format="{0,number,#,##0}">

								</display:column>
							</display:table>
						</div>
						<jsp:include page="getReport.jsp" />
					</div>
				</div>
			</div>
		</section>
	</c:if>
	<c:if test="${not empty mode}">
		<div style="display: none">
			<table id="usertable">
				<tbody>
					<tr>
						<c:forEach items="${branchuser}" var="user">
							<td>${fn:toUpperCase(user)}</td>
						</c:forEach>
					</tr>
				</tbody>
			</table>
		</div>
		<input type="hidden" id="userlen" value="${fn:length(branchuser)}">

		<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
				totals.setTotalLabel("full total");
				pageContext.setAttribute("totals", totals);</jsp:scriptlet>
		<div class="card card-block sameheight-item" data-exclude="xs,sm,lg">
			<div class="title-block">
				<h3 class="title">${branch}<a href="${pageContext.request.contextPath}/report/colldelvReportPg.do"
									type="button" class="btn btn-danger-outline pull-right"  style="padding: 0.09rem 0.8rem;"><i class="fa fa-times"></i></a></h3>
			</div>
			<div class="col-md-12">
				<div class="card card-block">

					<div class="table-flip-scroll" style="overflow-x: auto;">

						<display:table
							class="table table-bordered table-hover flip-content"
							name="colldelreport"
							requestURI="${pageContext.request.contextPath}/report/colldelvReportPg.do"
							pagesize="32" id="colldelreport">

							<display:column title="Date">
								<c:out value="${colldelreport[0]}" />
							</display:column>
							<c:set var="i" value="1" scope="page" />
							<c:forEach var="bank" items="${branchuser}">
								<c:set var="string1" value="${colldelreport[i]}" />
								<c:set var="string2" value="${fn:split(string1, ',')}" />
								<display:column title="coll" sortable="true"
									class="right" headerClass="right" format="{0,number,#,##0}">
									<c:out value="${string2[0]}"></c:out>
								</display:column>
								<display:column title="Delv" sortable="true"
									class="right" headerClass="right" format="{0,number,#,##0}">
									${string2[1]}
								</display:column>
								<display:column title="Total" sortable="true"
									class="right" headerClass="right" format="{0,number,#,##0}"> 
									${string2[2]}
								</display:column>
								<c:set var="i" value="${i + 1}" scope="page" />
							</c:forEach>
						</display:table>
					</div>
				</div>
			</div>
		</div>

		<script type="text/javascript">
			$(function() {
				$('#colldelreport tr:last').remove();
			});
			

		</script>
		<style>
.card-block {
	padding: 10px;
}
</style>
	</c:if>
</article>

<script type="text/javascript">
	$(function() {
	
		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#liCD").addClass("active");

	
		setTotal();
		if (($('#groupid').val() != '0' && $('#branchid').val() != '0')
				|| ($('#groupid').val() == '0' && $('#branchid').val() != '0')) {

			settheadeditmode();
		} else {
			setthead();

		}

	});

	function setTotal() {

		var coll = 0, del = 0, totalcd = 0
		$('#colldellist tbody tr').each(
				function() {

					coll = parseInt(coll)
							+ parseInt($(this).find("td:eq(2)").text());
					del = parseInt(del)
							+ parseInt($(this).find("td:eq(3)").text());
					totalcd = parseInt(totalcd)
							+ parseInt($(this).find("td:eq(4)").text());

				});
		if (isNaN(coll) == true)
			coll = 0.00;
		if (isNaN(del) == true)
			del = 0.00;
		if (isNaN(totalcd) == true)
			totalcd = 0.00;
		var foot = $("#colldellist").find('tfoot');

		if (!foot.length)
			foot = $('<tfoot>').appendTo("#colldellist");
		foot
				.append($('<tr><td colspan="2">Total</td><td style="text-align: right;">'
						+ coll
						+ '</td><td style="text-align: right;">'
						+ del
						+ '</td><td style="text-align: right;">'
						+ totalcd
						+ '</td><td></tr>'));
	}
	function settheadeditmode() {
		var gname = $("#groupid>option:selected").html();
		var thead = $("#colldelreport").find('thead');
		var userlen = parseInt($("#userlen").val()) * 3;
		thead
				.append($('<tr ><th></th><th colspan="'+userlen+'">MONTHLY SUMMARY</th></tr>'));
		var tuser;
		$('#usertable tbody tr td').each(function() {

			tuser += '<th colspan="3">';
			tuser += $(this).text();
			tuser += '</th>';
		})
		thead.append($('<tr ><th></th>' + tuser + '</tr>'));
		var tvale = '<tr><th>DATE</th>';
		for (var i = 0; i < userlen / 3; i++) {
			tvale += '<th>coll</th>';
			tvale += '<th>Delv</th>';
			tvale += '<th>Total</th>';
		}
		thead.append($(tvale + '</tr>'));
		$("#colldelreport thead tr:eq(0)").remove();
	}

	
</script>