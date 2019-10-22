<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

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
						<div class="table-flip-scroll rowClick">
							<display:table
								class="table table-bordered table-hover flip-content"
								name="vatTaxReportList"
								requestURI="${pageContext.request.contextPath}/report/vatTaxAnalysisReportPg.do"
								decorator="totals"  id="vatTaxReportList">
									<display:column title="Branch No." sortable="true"
								property="branchInternalId" paramId="branchId"
									paramProperty='branchid'
									href="${pageContext.request.contextPath}/report/vatTaxMonthlyReportPg.do"
								class="hidden" headerClass="hidden"  />
								<display:column title="BranchId" sortable="true"
									property="branchInternalId" />
								<display:column title="Branch Name" sortable="true"
									property="branchName"  maxWords="1"/>
								<display:column title="zero" sortable="true" property="zeroNett"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="low" sortable="true" property="lowNett"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="std" sortable="true" property="stdNett"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="total" sortable="true"
									property="totalNett" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="low" sortable="true" property="lowVat"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="std" sortable="true" property="stdVat"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="total" sortable="true"
									property="totalVat" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="zero" sortable="true"
									property="zeroGross" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="low" sortable="true" property="lowGross"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="std" sortable="true" property="stdGross"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="total" sortable="true"
									property="totalGross" format="{0,number,#,###,##0.00}"
									class="right" headerClass="right" total="true" />
								<display:column title="script" sortable="true" property="script"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />
								<display:column title="levy" sortable="true" property="levy"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true" />

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
				<h3 class="title">${branch}<a href="${pageContext.request.contextPath}/report/vatTaxAnalysisReportPg.do"
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
							name="vatTaxlist"
							requestURI="${pageContext.request.contextPath}/report/vatTaxMonthlyReportPg.do"
							defaultsort="1" decorator="totals"  id="vatTaxlist">
							<display:column property="weekday" title="weekday"
								sortable="true" class="hidden" headerClass="hidden" group="1"
								media="html" />
							<display:column property="edate" title="Date" sortable="true"
								format="{0,date,dd/MM/yyyy}" />
							<display:column title="total" sortable="true"
								property="totalNett" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="low" sortable="true" property="lowVat"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"
								total="true" />
							<display:column title="std" sortable="true" property="stdVat"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"
								total="true" />
							<display:column title="total" sortable="true" property="totalVat"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"
								total="true" />

							<display:column title="total" sortable="true"
								property="totalGross" format="{0,number,#,###,##0.00}"
								class="right" headerClass="right" total="true" />
							<display:column title="paid" sortable="true" property="paiditems"
								format="{0,number,#,###,##0}" class="right" headerClass="right"
								total="true" />
							<display:column title="levy" sortable="true" property="levy"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"
								total="true" />
							<display:column title="tax" sortable="true" property="tax"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"
								total="true" />
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

<script type="text/javascript">
	$(function() {
	
		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#livattaxanalysis").addClass("active");

		if ($("#mode").val() == 'Edit') {
			$('.total').each(function() {
				$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());

			});
			settheadeditmode();
		} else {
			$('.total').each(function() {
				$(this).find("td:eq(1)").text("Total");

			});
			setthead();

		}

	});
	
	function setthead() {
		var thead = $("#vatTaxReportList").find('thead');
		thead
				.append($('<tr><th colspan="15">VAT & TAX Analysis Summary</th></tr>'));
		thead
				.append($('<tr><th colspan="2">Branch</th><th colspan="4">Nett Sales</th><th colspan="3">VAT</th><th colspan="4">Gross Sales</th><th>Private</th><th>NHS</th></tr>'));
		thead
				.append($('<tr><th>No.</th><th>Name</th><th>Zero</th><th>Low</th><th>Standard</th><th>Total</th><th>Low</th><th>Standard</th><th>Total</th><th>Zero</th><th>Low</th><th>Standard</th><th>Total</th><th>Scripts</th><th>Levy</th></tr>'));
		$("#vatTaxReportList thead tr:eq(0)").remove();
	}
	function settheadeditmode() {
		var thead = $("#vatTaxlist").find('thead');
		thead
				.append($('<tr><td class="hidden"></td><th colspan="9">Monthly VAT & TAX Analysis Summary</th></tr>'));
		thead
				.append($('<tr><td class="hidden"></td><th></th><th>Nett</th><th colspan="3">VAT</th><th>Gross</th><th colspan="2">NHS</th><th>Total</th></tr>'));
		thead
				.append($('<tr><td class="hidden">weekday</td><th>Date</th><th>Sales</th><th>Low</th><th>Standard</th><th>Total</th><th>Sales</th><th>Paid Items</th><th>Levies</th><th>Tax</th></tr>'));
		$("#vatTaxlist thead tr:eq(0)").remove();
	}
</script>