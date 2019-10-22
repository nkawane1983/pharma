<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<article class="content dashboard-page">
	<jsp:include page="../commanSearch.jsp" />
	<section>
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="carhomeList"
							requestURI="${pageContext.request.contextPath}/proService/searchCareHomePg.do"
							pagesize="31" defaultsort="1" id="carhomeList1">
							<display:column property="edate" title="Date"
								format="{0,date,dd/MM/yyyy}" sortable="true"
								style="text-align: left;"
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'  />
							<display:column property="form7day" title="Form" sortable="true"
								style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'  />
							<display:column property="items7day" title="items"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'/>
							<display:column property="form28day" title="Form" sortable="true"
								style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'  />
							<display:column property="items28day" title="items"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'  />
							<display:column property="comm7form" title="form" sortable="true"
								style="text-align: right;"
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'  />
							<display:column property="comm7items" title="items"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id' />
							<display:column property="comm28form" title="form"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id'  />
							<display:column property="comm28items" title="items"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id' />
							<display:column property="total7form" title="form"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id' />
							<display:column property="total7items" title="items"
								sortable="true" style="text-align: right; "
								href="${pageContext.request.contextPath}/proService/editCareHomePg.do"
								paramId="id" paramProperty='id' />

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
		$("#liProfessionService").addClass("open");
		$("#liProfessionService ul").addClass("collapse in");
		$("#liProfessionService").addClass("active");
		$("#liCareHomesMDS").addClass("active");
		settheadeditmode();
	});
	function settheadeditmode() {
		var thead = $("#carhomeList1").find('thead');
		thead
				.append($('<tr><th></th><th colspan="4">Home MDS</th><th colspan="4">Community MDS</th><th colspan="2">Total</th></tr>'));
		thead
				.append($('<tr><th></th><th colspan="2">7 Day</th><th colspan="2">28 Day</th><th colspan="2">7 Day</th><th colspan="2">28 Day</th><th colspan="2"></th></tr>'));
		thead
				.append($('<tr><th>Date</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th></tr>'));
		$("#carhomeList1 thead tr:eq(0)").remove();
	}
</script>