<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<article class="content dashboard-page">
	<jsp:include page="SearchReport.jsp" />

	<section>
		<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
			totals.setTotalLabel("full total");
			pageContext.setAttribute("totals", totals);</jsp:scriptlet>
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll" style="overflow: auto;">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="custstatslist"
							requestURI="${pageContext.request.contextPath}/report/cashReportPg.do"
							  id="custstatslist1"
							decorator="totals">
							<display:column title="Branch No." sortable="true"
								property="branchInternalId" />

							<display:column title="Branch Name" sortable="true"
								property="branchName" paramId="branchid"
								paramProperty='branchid' maxWords="1"/>

							<display:column title="NHSItems" sortable="true"
								property="nhsItems" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0}" />

							<display:column title="noOfSales" sortable="true"
								property="noOfSales" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0}" />

							<display:column title="salesIncActual" sortable="true"
								property="salesIncActual" total="true"
								class="right" headerClass="right" format="{0,number,#,###,##0.00}" />

							<display:column title="salesIncAvg" property="salesIncAvg"
								sortable="true" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />


							<display:column title="salesExcActual" sortable="true"
								property="salesExcActual" total="true"
								class="right" headerClass="right" format="{0,number,#,###,##0.00}" />

							<display:column title="salesExcAvg" sortable="true"
								property="salesExcAvg" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="counterSale" sortable="true"
								property="counterSale" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="excVat" sortable="true" property="excVat"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="nhslevy" sortable="true"
								property="nhslevy" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />


						</display:table>
					</div>
					<jsp:include page="getReport.jsp" />
				</div>
			</div>
		</div>
	</section>
</article>

<script type="text/javascript">
	$(function() {

		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#licustomerStats").addClass("active");


		setthead();
		$('.total').each(function() {
			$(this).find("td:eq(1)").text("Total");

		});
	});
	function setthead() {
		var thead = $("#custstatslist1").find('thead');
		thead.append($('<tr><th colspan="2">Branch</th><th>NHS</th><th>No.Of</th><th colspan="2">Sales inc VAT & Levy </th><th colspan="2">Sales exc VAT inc Levy </th><th>Nett</th><th>Avg Sales</th><th>NHS</th></tr>'));
		thead
				.append($('<tr><th>NO.</th><th>Name</th><th>Items</th><th>Sales</th><th>Actual</th><th>Average</th><th>Actual</th><th>Average</th><th>Counter sale</th><th>exc VAT exc Levy</th><th>Levy</th></tr>'));
		$("#custstatslist1 thead tr:eq(0)").remove();
	}
</script>