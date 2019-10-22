
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>
.wrongRight {
	text-align: right;
	background-color: #FF4444;
	color: #4f5f6f;
}
.warningRight {
	text-align: right;
	background-color: #fe974b;
	color: #4f5f6f;
}
</style>
<article class="content dashboard-page">
	<jsp:include page="SearchReport.jsp" />
	<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
			totals.setTotalLabel("Monthly Total");
			totals.setSubtotalLabel("Weekly Total");
			pageContext.setAttribute("totals", totals);
			</jsp:scriptlet>
	<section style="margin-top: -25px;">
		<div class="row sameheight-container">
			<div class="col-md-12">
			
				<div class="card card-block">
				<div class="card">
				<fieldset class="center">
				<strong>VAT:</strong><span class="wrongRight">Wrong</span> | <span class="warningRight">Warning</span>
				</fieldset>
				</div>
				
				<div class="table-flip-scroll rowClick" style="overflow: auto;">
						<display:table name="monthlyReportList" export="false"
							requestURI="${pageContext.request.contextPath}/report/monthlyCashReportPg.do"
							decorator="totals"  id="monthlyReportList"
							class="table table-bordered table-hover flip-content">
							<c:choose>
								<c:when
									test="${(groupid=='0' && branchid !='0')||(groupid!='0' && branchid !='0')}">
									<c:if test="${mode=='cashing'}">
									<display:column property="weekday" title="weekday"
										sortable="true" group="1" class="hidden" headerClass="hidden"
										media="html" paramId="id" paramProperty='cashid'
										href="${pageContext.request.contextPath}/cashing/editCashingPg.do" />
									</c:if>
									<c:if test="${mode=='amend'}">
									<display:column property="weekday" title="weekday"
										sortable="true" group="1" class="hidden" headerClass="hidden"
										media="html" paramId="modeid" paramProperty='cashid'
										href="${pageContext.request.contextPath}/cashing/updateCashingTillPg.do" />
									</c:if>
									<display:column property="edate" title="Date"
										format="{0,date,dd/MM/yyyy}" sortable="true" class="left"
										headerClass="left" />

									<display:column property="ref" title="Ref" sortable="true"
										class="left" headerClass="left" />
									<display:column property="tillno" title="Till" sortable="true"
										class="right" headerClass="right" group="2" />

								</c:when>
								<c:otherwise>
									<display:column property="weekday" title="weekday"
										sortable="true" group="1" class="hidden" headerClass="hidden"
										media="html" />
									<display:column property="edate" title="Date"
										format="{0,date,dd/MM/yyyy}" sortable="true" class="left"
										headerClass="left" />
								</c:otherwise>
							</c:choose>
							<display:column property="zReading" title=" Till Reading"
								sortable="true" total="true" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" />

							<display:column property="diff" title="Diff" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />
							<display:column property="voids" title="Void" sortable="true"
								total="true" format="{0,number,#,###,###}" class="right"
								headerClass="right" />

							<display:column property="refunds" title="Refunds"
								sortable="true" total="true" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" />

							<display:column property="coupons" title="Coupons"
								sortable="true" total="true" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" />
						
								<display:column property="actualtill" title="Actualtill"
									sortable="true" total="true" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" />
						
							<display:column property="expenses" title="Expenses"
								sortable="true" total="true" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" />

							<display:column property="card" title="Card" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />

							<display:column property="cash" title="Cash" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />
									<c:if
								test="${(groupid!='0' && branchid !='0')}">
							<display:column property="actualCash" title="actual" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />
								</c:if>
						<c:choose>	
						<c:when	test="${monthlyReportList.cashid !=0 && monthlyReportList.zero==0 && monthlyReportList.zReading>=1}">
							<display:column property="zero" title="zero" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="warningRight"
								headerClass="right" />
						</c:when>
						<c:otherwise>
						<display:column property="zero" title="zero" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />
						</c:otherwise>
						</c:choose>
							<c:choose>	
						<c:when	test="${monthlyReportList.cashid !=0 && monthlyReportList.low==0 && monthlyReportList.zReading>=1}">
							<display:column property="low" title="low" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="warningRight"
								headerClass="right" />
								</c:when>
								<c:otherwise>
								<display:column property="low" title="low" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />
								</c:otherwise>
								</c:choose>
								<c:choose>	
						<c:when	test="${monthlyReportList.cashid !=0 && monthlyReportList.std==0 && monthlyReportList.zReading>=1}">
							<display:column property="std" title="std" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="wrongRight"
								headerClass="right" />
								</c:when>
								<c:otherwise>
								<display:column property="std" title="std" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />
								</c:otherwise>
								</c:choose>
							<display:column property="sales" title="Cash" sortable="true"
								total="true" format="{0,number,#,###,###}" class="right"
								headerClass="right" />
							<display:column property="avg" title="avg" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />


						</display:table>
					</div>

					<jsp:include page="getReport.jsp" />

				</div>
			</div>
		</div>
	</section>

	<fmt:parseDate value="${enddate}" var="parsedEmpDate"
		pattern="yyyy-MM-dd" />
	<fmt:formatDate value="${parsedEmpDate}" type="date" pattern="MMMM"
		var="md" />
	<fmt:formatDate value="${parsedEmpDate}" type="date" pattern="yyyy"
		var="my" />
	<input type="hidden" id="mid" value="${fn:toUpperCase(md)} ${my}">
	<input type="hidden" id="modereport" value="${mode}">
</article>
<script type="text/javascript">
	$(function() {

		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		if($("#modereport").val()=='cashing')
			$("#liMonthlyCash").addClass("active");
		if($("#modereport").val()=='amend')
			$("#liamendmentTill").addClass("active");
		$('.total').each(function() {
			$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());
		});

		if (($('#groupid').val() != '0' && $('#branchid').val() != '0')
				|| ($('#groupid').val() == '0' && $('#branchid').val() != '0')) {
			$('#monthlyReportList tr').each(function() {

				if ($(this).find("td:eq(2)").text() == 'None') {
					$(this).find("td:gt(1)").text("");
					$(this).find("td:eq(0)").children("a").removeAttr("href");
				}

			});
			setthead();
		} else {
			settheadeditmode();
		}

	});

	function setthead() {
		var thead = $("#monthlyReportList").find('thead');
	

		thead
				.append($('<tr ><th class="hidden"></th><th colspan="18" class="center">MONTHLY SUMMARY</th></tr>'));
		thead
				.append($('<tr><th class="hidden"></th><th colspan="18" class="center">'
						+ $("#mid").val() + '</th></tr>'));

		thead
				.append($('<tr><th class="hidden"></th><th class="left">Date</th><th class="left">Ref</th><th colspan="2" class="center">Till</th><th class="right">Diff</th><th class="right">Voids</th><th class="right">Refunds</th><th class="right">Coupons</th><th class="right">Actual</th><th class="right">Expenses</th><th colspan="3" class="center">Banking</th><th colspan="3" class="center">VAT</th><th colspan="2"  class="center">Sales</th></tr>'));
		thead
				.append($('<tr><th class="hidden"></th><th></th><th></th><th class="right">No.</th><th class="right">Reading</th><th class="right">(+or-)</th><th></th><th></th><th></th><th class="right">Till</th><th></th><th class="right">Card</th><th class="right">Giro</th><th class="right">Actual</th><th class="right">Zero</th><th class="right">Low</th><th class="right">Std</th><th class="right">No.</th><th class="right">Avg</th>'));
		$("#monthlyReportList thead tr:eq(0)").remove();
	}
	function settheadeditmode() {

		var thead = $("#monthlyReportList").find('thead');
		thead
				.append($('<tr class="center"><th class="hidden" ></th><th colspan="18" class="center">MONTHLY SUMMARY</th></tr>'));
		thead
				.append($('<tr><th class="hidden"></th><th colspan="18" class="center">'
						+ $("#mid").val() + '</th></tr>'));

		thead
				.append($('<tr><th class="hidden"></th><th>Date</th><th class="right">Till</th><th class="right">Diff</th><th class="right">Voids</th><th class="right">Refunds</th><th class="right">Coupons</th><th class="right">Actual</th><th class="right">Expenses</th><th colspan="2"  class="center">Banking</th><th colspan="3"  class="center">VAT</th><th colspan="2"  class="center">Sales</th>'));
		thead
				.append($('<tr><th class="hidden"></th><th></th><th class="right">Reading</th><th class="right">(+or-)</th><th></th><th></th><th></th><th class="right">Till</th><th></th><th class="right">Card</th><th class="right">Giro</th><th class="right">Zero</th><th class="right">Low</th><th class="right">Std</th><th class="right">No.</th><th class="right">Avg.</th>'));
		$("#monthlyReportList thead tr:eq(0)").remove();
	}
</script>