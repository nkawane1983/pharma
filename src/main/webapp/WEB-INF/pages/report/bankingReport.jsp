<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<article class="content dashboard-page">
	<c:if test="${empty mode}">
		<jsp:include page="SearchReport.jsp" />
			
			<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
				totals.setTotalLabel("full total");
				pageContext.setAttribute("totals", totals);</jsp:scriptlet>
		<section>
			<div class="row sameheight-container">
				<div class="col-md-12">
					<div class="card card-block">
						<div class="table-flip-scroll" style="overflow-x: auto;">

							<display:table
								class="table table-bordered table-hover flip-content"
								export="false" name="bankReportList"
								requestURI="${pageContext.request.contextPath}/report/bankingReportPg.do"
								  id="bankReportList" decorator="totals">
								<display:column title="BranchId" sortable="true"
									property="internalId" />
								<display:column title="Branch Name" sortable="true"
									property="branchName" paramId="branchId"
									paramProperty='branchId'
									href="${pageContext.request.contextPath}/report/bankingMonthlyReportPg.do" maxWords="1"/>
								<display:column title="Cash" sortable="true"
									property="cashTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="Cheques" sortable="true"
									property="chequesTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="Total" sortable="true"
									property="giroTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="Switch" sortable="true"
									property="switchTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="Other" sortable="true"
									property="otherTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="Total" sortable="true"
									property="cardTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="Total" sortable="true"
									property="bankTotal" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
							</display:table>

						</div>
							<jsp:include page="getReport.jsp" />
					</div>
				</div>
			</div>
		</section>
	</c:if>
	<c:if test="${not empty mode}">
		<div class="card card-block sameheight-item" data-exclude="xs,sm,lg">
			<div class="title-block">
				<h3 class="title">${branch}<a href="${pageContext.request.contextPath}/report/bankingReportPg.do"
									type="button" class="btn btn-danger-outline pull-right"  style="padding: 0.09rem 0.8rem;"><i class="fa fa-times"></i></a></h3>
			</div>

			<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
				totals.setTotalLabel("Monthly Total");
				totals.setSubtotalLabel("Weekly Total");
				pageContext.setAttribute("totals", totals);</jsp:scriptlet>

			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							name="banklist"
							requestURI="${pageContext.request.contextPath}/report/bankingMonthlyReportPg.do"
							 decorator="totals"  id="banklist1">
							<display:column property="weekday" title="weekday"
								sortable="true" class="hidden" headerClass="hidden" group="1"
								media="html" />
							<display:column  title="Date" sortable="true"
								format="{0,date,dd/MM/yyyy}">
								
								<c:choose>
									<c:when test="${banklist1.bankingid != '0'}">

										<a
											href="${pageContext.request.contextPath}/banking/editBanking.do?reportid=${banklist1.bankingid}&branchid=${banklist1.branchId}">
											<fmt:formatDate value="${banklist1.edate}"
												pattern="dd/MM/yyyy" />
										</a>
									</c:when>
									<c:otherwise>
										<a> <fmt:formatDate value="${banklist1.edate}"
												pattern="dd/MM/yyyy" />
										</a>
									</c:otherwise>
								</c:choose>
								</display:column>
							<display:column property="ref" title="ref" sortable="true"
								format="{0,date,dd/MM/yyyy}"  />

							<display:column title="Cash" sortable="true" property="cashTotal"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"
								total="true" />
							<display:column title="Cheques" sortable="true"
								property="chequesTotal" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="Total" sortable="true"
								property="giroTotal" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="Switch" sortable="true"
								property="switchTotal" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="Other" sortable="true"
								property="otherTotal" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="Total" sortable="true"
								property="cardTotal" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="Total" sortable="true"
								property="bankTotal" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
						</display:table>
					</div>
				</div>
			</div>
		</div>
		<input type="hidden" value="${mode}" id="mode" />

		<style>
.card-block {
	padding: 10px;
}
</style>
	</c:if>
</article>

<script>
	$(function() {

		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#liBanking").addClass("active");
		if ($("#mode").val() == 'Edit') {
			$('.total').each(function() {
				$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());

			});
			$('#banklist1 tr').each(function() {

				if ($(this).find("td:eq(2)").text()=='None') {
					$(this).find("td:gt(1)").text(""); 
				}
			

			});
			settheadeditmode();
		} else {
			$('.total').each(function() {
				$(this).find("td:eq(0)").text("Total");

			});
			setthead();

		}

	});
	
	function setthead() {
		var thead = $("#bankReportList").find('thead');
		thead.append($('<tr><th colspan="9">Banking Summary</th></tr>'));
		thead
				.append($('<tr><th colspan="2">Branch</th><th colspan="3">Giro Banking</th><th colspan="3">Credit/Debit Cards</th><th>Total</th></tr>'));
		thead
				.append($('<tr><th>Branch NO.</th><th> Name</th><th>Cash</th><th>Cheques</th><th>Total</th><th>Switch</th><th>Other</th><th>Total</th><th>Banked</th></tr>'));
		$("#bankReportList thead tr:eq(0)").remove();
	}
	function settheadeditmode() {
		var thead = $("#banklist1").find('thead');
		thead
				.append($('<tr><td class="hidden"></td><th colspan="9">Banking Summary</th></tr>'));
		thead
				.append($('<tr><td class="hidden"></td><th colspan="2"></th><th colspan="3">Giro Banking</th><th colspan="3">Credit/Debit Cards</th><th>Total</th></tr>'));
		thead
				.append($('<tr><td class="hidden">weekday</td><th>Date</th><th>Ref</th><th>Cash</th><th>Cheques</th><th>Total</th><th>Switch</th><th>Other</th><th>Total</th><th>Banked</th></tr>'));
		$("#banklist1 thead tr:eq(0)").remove();
	}
	
	
</script>