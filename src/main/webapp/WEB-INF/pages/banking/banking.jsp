<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<article class="content dashboard-page">
	<jsp:include page="../commanSearch.jsp" />
	<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
			totals.setTotalLabel("Full Total");
			totals.setSubtotalLabel("Week Total");
			pageContext.setAttribute("totals", totals);</jsp:scriptlet>
	<section class="section" style="margin-top: -25px;">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="table-flip-scroll rowClick">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="bankingList"
							requestURI="${pageContext.request.contextPath}/banking/searchPg.do"
							pagesize="60" defaultsort="1" id="bankingList" decorator="totals"
							keepStatus="true">
							<display:column property="weekNo" title="weekNo" sortable="true"
								class="hidden" headerClass="hidden" media="html" group="1"
								href="${pageContext.request.contextPath}/banking/editBanking.do"
								paramId="id" paramProperty='id' />

							<display:column property="bankDate" title="Date" sortable="true"
								format="{0,date,dd/MM/yyyy}" />

							<display:column property="bankingRef" title="Reference No."
								sortable="true" class="center" headerClass="center" />


							<display:column property="cash" title="Cash" sortable="true"
								total="true" format="{0,number,#,###,##0.00}" class="right"
								headerClass="right" />

							<display:column property="cheques" title="Cheques"
								sortable="true" total="true" class="right" headerClass="right"
								format="{0,number,#,###,##0.00}" />

							<display:column property="miscCash" title="Oth. Inc"
								sortable="true" class="right" headerClass="right" total="true"
								format="{0,number,#,###,##0.00}" />

							<display:column property="totalBanking" title="Total Banking"
								sortable="true" class="right" headerClass="right" total="true"
								format="{0,number,#,###,##0.00}" />
							<display:column property="userId" title="User" sortable="true" />
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
		$("#liBanking").addClass("active");

		$('#from').datepicker({
			format : 'dd/mm/yyyy'
		});
		$('#to').datepicker({
			format : 'dd/mm/yyyy'
		});
		$('.total').each(function() {
			$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());

		});
	});
</script>
<script>
	function myFunction1() {
		if ($('#from').val() == '') {
			$('#from').attr('required', 'required');
			$('#to').attr('required', 'required');
			$('#from').val('');

			return false;
		}
		if ($('#from').val() > $('#to').val()) {

			$('#to').attr('required', 'required');
			$('#to').val('');
			return false;
		}
	}
</script>
