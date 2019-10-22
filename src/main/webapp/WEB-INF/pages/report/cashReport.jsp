<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<article class="content dashboard-page">

	<jsp:include page="SearchReport.jsp" />

	<section>
		<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator   totals = new org.displaytag.decorator.TotalTableDecorator();
		 		
			totals.setTotalLabel("Full Total");
			totals.setSubtotalLabel("Group {0} Total");
			pageContext.setAttribute("totals", totals);
			</jsp:scriptlet>
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll rowClick" style="overflow: auto;">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="cashList"
							requestURI="${pageContext.request.contextPath}/report/cashReportPg.do"
							  id="cashlist1" decorator="totals"  clearStatus="true" >
							<display:column title="Branch No." sortable="true"
								property="branchInternalId" paramId="branchid"
								paramProperty='branchid'
								href="${pageContext.request.contextPath}/report/monthlyCashReportPg.do"
								class="hidden" headerClass="hidden"  />
							<display:column title="groupId" sortable="true"
								property="groupId" 								
								 group="1" class="hidden" headerClass="hidden"/>
							<display:column title="Branch No." sortable="true"
								property="branchInternalId" class="left" headerClass="left"/>

							<display:column title="Branch Name" sortable="true"
								property="branchName" maxWords="1" class="left" headerClass="left"/>

							<display:column title="ZReading" sortable="true"
								property="zReading" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Voids" sortable="true" property="voids"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,###}" />

							<display:column title="Refunds" sortable="true"
								property="refunds" total="true" class="right" headerClass="right"
								format="{0,number,#,###.##}" />

							<display:column title="NHS Levy" property="levy" sortable="true"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />
							<display:column title="Sales" sortable="true" property="sales"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,###}" />
							<display:column title="Avg. Sale" sortable="true" property="avg"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Cash" sortable="true" property="cash"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Cheques" sortable="true"
								property="cheques" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Cards" sortable="true" property="card"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Coupons" sortable="true"
								property="coupons" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="PaidOuts" sortable="true"
								property="expenses" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Takings" sortable="true"
								property="actualtill" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Discrep." sortable="true" property="diff"
								total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column title="Total Items" sortable="true"
								property="totalitem" total="true" class="right" headerClass="right"
								format="{0,number,#,###,###}" />
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
		$("#liCash").addClass("active");
		$('.total').each(function() {
			$(this).find("td:eq(2)").text($(this).find("td:eq(1)").text().trim());
			$($(this).find("td:eq(2)")).attr('colspan',2);
			$($(this).find("td:eq(2)")).next("td").remove();
		});
	});

	function setTotal() {
		var zr = 0, voids = 0, refunds = 0, nhsl = 0, sales = 0, avgsal = 0;
		var cash = 0, Cheques = 0, Cards = 0, coupons = 0, paidout = 0, takings = 0;
		var totalitem = 0, desp = 0;
		$('#cashlist1 tbody tr').each(
				function() {
					zr = parseInt(zr)
							+ parseInt($(this).find("td:eq(2)").text());
					voids = parseInt(voids)
							+ parseInt($(this).find("td:eq(3)").text());
					refunds = parseInt(refunds)
							+ parseInt($(this).find("td:eq(4)").text());
					nhsl = parseInt(nhsl)
							+ parseInt($(this).find("td:eq(5)").text());
					sales = parseInt(sales)
							+ parseInt($(this).find("td:eq(6)").text());
					avgsal = parseInt(avgsal)
							+ parseInt($(this).find("td:eq(7)").text());
					cash = parseInt(cash)
							+ parseInt($(this).find("td:eq(8)").text());
					Cheques = parseInt(Cheques)
							+ parseInt($(this).find("td:eq(9)").text());
					Cards = parseInt(Cards)
							+ parseInt($(this).find("td:eq(10)").text());
					coupons = parseInt(coupons)
							+ parseInt($(this).find("td:eq(11)").text());
					paidout = parseInt(paidout)
							+ parseInt($(this).find("td:eq(12)").text());
					takings = parseInt(takings)
							+ parseInt($(this).find("td:eq(13)").text());
					desp = parseInt(desp)
							+ parseInt($(this).find("td:eq(14)").text());
					totalitem = parseInt(totalitem)
							+ parseInt($(this).find("td:eq(15)").text());

				});
		if (isNaN(zr) == true)
			zr = 0.00;
		if (isNaN(voids) == true)
			voids = 0.00;
		if (isNaN(refunds) == true)
			refunds = 0.00;
		if (isNaN(nhsl) == true)
			nhsl = 0;
		if (isNaN(sales) == true)
			sales = 0;
		if (isNaN(avgsal) == true)
			avgsal = 0;
		if (isNaN(cash) == true)
			cash = 0;
		if (isNaN(Cheques) == true)
			Cheques = 0;
		if (isNaN(Cards) == true)
			Cards = 0;
		if (isNaN(coupons) == true)
			coupons = 0;
		if (isNaN(paidout) == true)
			paidout = 0;
		if (isNaN(takings) == true)
			takings = 0;
		if (isNaN(totalitem) == true)
			totalitem = 0;
		if (isNaN(desp) == true)
			desp = 0;

		var foot = $("#cashlist1").find('tfoot');

		if (!foot.length)
			foot = $('<tfoot>').appendTo("#cashlist1");
		foot.append($('<tr><td colspan="2"><b>Total</b></td><td>' + zr
				+ '</td><td>' + voids + '</td><td>' + refunds + '</td><td>'
				+ nhsl + '</td><td>' + sales + '</td><td>' + avgsal
				+ '</td><td>' + cash + '</td><td>' + Cheques + '</td><td>'
				+ Cards + '</td><td>' + coupons + '</td><td>' + paidout
				+ '</td><td>' + takings + '</td><td>' + desp + '</td><td>'
				+ totalitem + '</td></tr>'));
	}
</script>