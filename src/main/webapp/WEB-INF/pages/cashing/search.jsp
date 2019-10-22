<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<article class="content dashboard-page">
	<jsp:include page="../commanSearch.jsp" />
	<section class="section" style="margin-top: -25px;">
		<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
		totals.setTotalLabel("Full Total");
		totals.setSubtotalLabel("Week Total");
			pageContext.setAttribute("totals", totals);</jsp:scriptlet>
		<div class=" row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll rowClick">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="cashingList" pagesize="60"
							requestURI="${pageContext.request.contextPath}/cashing/searchPg.do"
							id="cashingList" decorator="totals" defaultsort="1"
							keepStatus="true">
							<display:column property="weekNo" title="weekNo" sortable="true"
								class="hidden" headerClass="hidden" media="html" group="1"
								href="${pageContext.request.contextPath}/cashing/editCashingPg.do"
								paramId="id" paramProperty='id' />
							<display:column property="date" title="Date" sortable="true"
								format="{0,date,dd/MM/yyyy}" class="left" headerClass="left" />

							<display:column property="tillNo" title="Till" sortable="true"
								class="right" headerClass="right" total="true"
								format="{0,number,#,##0}" />

							<display:column property="refNo" title="Ref No." sortable="true"
								class="left" headerClass="left" />
							<display:column property="zReading" title="Z-Reading"
								sortable="true" total="true" format="{0,number,#,##0.00}"
								class="right" headerClass="right" />
							<display:column property="diff" title="Discrepancy"
								sortable="true" total="true" format="{0,number,#,##0.00}"
								class="right" headerClass="right" />


							<display:column property="voids" title="Voids" sortable="true"
								format="{0,number,#,##0}" class="right" headerClass="right"
								total="true" />
							<display:column property="sales" title="Sales" sortable="true"
								format="{0,number,#,##0}" class="right" headerClass="right"
								total="true" />
							<display:column property="refunds" title="Refunds"
								sortable="true" format="{0,number,#,##0.00}" class="right"
								headerClass="right" total="true" />

							<display:column property="actualTill" title="ActualTill"
								sortable="true" total="true" format="{0,number,#,##0.00}"
								class="right" headerClass="right" />

							<display:column property="cash" title="Cash" sortable="true"
								format="{0,number,#,##0.00}" class="right" headerClass="right"
								total="true" />
							<display:column property="cheques" title="Cheques"
								sortable="true" format="{0,number,#,##0.00}" class="right"
								headerClass="right" total="true" />
							<display:column property="cards" title="Cards" sortable="true"
								format="{0,number,#,##0.00}" class="right" headerClass="right"
								total="true" />
							<display:column property="coupuns" title="Coupuns"
								sortable="true" format="{0,number,#,##0.00}" class="right"
								headerClass="right" total="true" />
							<display:column property="paidOut1" title="Expenses"
								sortable="true" format="{0,number,#,##0.00}" class="right"
								headerClass="right" total="true" />
						</display:table>
					</div>
				</div>
			</div>
		</div>
	</section>
</article>

<script type="text/javascript">
	$(function() {
		$("ul li").removeClass("active");
		$("#liCashing").addClass("active");
		$('.total').each(function() {
			$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());

		});
	});
</script>
