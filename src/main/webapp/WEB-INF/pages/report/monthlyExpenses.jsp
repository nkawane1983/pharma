<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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
					<div class="table-flip-scroll rowClick" style="overflow-x: auto;">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="expenseslist"
							requestURI="${pageContext.request.contextPath}/report/montlyExpensesReportPg.do"
							 id="expenseslist" decorator="totals">
							<display:column title="Branch No." sortable="true"
								property="branchInternalId" paramId="reportKey"
								paramProperty='reportKey'
								href="${pageContext.request.contextPath}/report/montlyExpensesReportPg.do"
								class="hidden" headerClass="hidden"  />
								
							<display:column title="Branch No" sortable="true"
								property="branchInternalId" maxWords="10" />
								
							<display:column title="Branch Name" sortable="true"
								property="branchName" maxWords="1"  />
							<c:forEach var="cl" items="${expenseslist.listExpenses}"
								varStatus="index">
								<display:column title="${cl.listName}" sortable="true"
									property="listExpenses[${index.index}].listValue"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true"  />
							</c:forEach>
							<display:column title="Total" sortable="true"
								property="branchTotal" total="true"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"/>
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
				<h3 class="title">${branch}<a href="${pageContext.request.contextPath}/report/expensesReportPg.do"
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
							 name="monthlyExpenseslist"
							requestURI="${pageContext.request.contextPath}/report/montlyExpensesReportPg.do"
							defaultsort="1" decorator="totals"  id="monthlyExpenseslist">
							<display:column property="weekday" title="weekday"
								sortable="true" class="hidden" headerClass="hidden" group="1"
								media="html" />
								<display:column title="Date" sortable="true" property="edate"
								format="{0,date,dd/MM/yyyy}"/>
									
								<c:forEach var="cl" items="${monthlyExpenseslist.listExpenses}"
								varStatus="index">
								<display:column title="${cl.listName}" sortable="true"
									property="listExpenses[${index.index}].listValue"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true"  />
							</c:forEach>
							<display:column title="Total" sortable="true"
								property="branchTotal" total="true"
								format="{0,number,#,###,##0.00}" class="right" headerClass="right"/>
										
							
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
		$("#limontlyexpenses").addClass("active");
		
		if ($("#mode").val() == 'Edit') {
			$('.total').each(function() {
				$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());
				
			});
		
		} else
			{
			$('.total').each(function() {
				$(this).find("td:eq(1)").text("Total");

			});
			}
	});
</script>