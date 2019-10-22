<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<article class="content dashboard-page">
	<jsp:include page="SearchReport.jsp" />
	<section>
		<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
			totals.setTotalLabel("full total");
			pageContext.setAttribute("totals", totals);</jsp:scriptlet>
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll" style="overflow-x: auto;">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="couponlist"
							requestURI="${pageContext.request.contextPath}/report/couponsReportPg.do"
							 id="couponlist" decorator="totals">
							<display:column title="Branch No" sortable="true"
								property="branchInternalId" maxWords="10" />
							<display:column title="Branch Name" sortable="true"
								property="branchName" maxWords="1" />
							<c:forEach var="cl" items="${couponlist.listCoupon}"
								varStatus="index">
								<display:column title="${cl.listName}" sortable="true"
									property="listCoupon[${index.index}].listValue"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true"  />
							</c:forEach>
							<display:column title="Total" sortable="true"
								property="branchTotal" total="true"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right" />
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
		$("#licoupon").addClass("active");
		$('.total').each(function() {
			$(this).find("td:eq(0)").text("Total");

		});
	});
</script>