<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<article class="content dashboard-page">
	<jsp:include page="SearchReport.jsp" />
	<section>
		<div class="row sameheight-container">
		
			<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
				totals.setTotalLabel("Full Total");
				totals.setSubtotalLabel("Week Total");
				pageContext.setAttribute("totals", totals);</jsp:scriptlet>
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll" style="overflow-x: auto;">

						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="monthlybankReportList"
							requestURI="${pageContext.request.contextPath}/report/cashReportPg.do"
							 defaultsort="1" id="monthlybankReportList" decorator="totals">
			
							
							<display:column property="weekday" title="weekday"
								sortable="true" class="hidden" headerClass="hidden" group="1"
								media="html" />
								<display:column title="Date" sortable="true" property="edate"
								format="{0,date,dd/MM/yyyy}" class="left" headerClass="left"/>
									
								<c:forEach var="cl" items="${monthlybankReportList.bankingList}"
								varStatus="index">
								<display:column title="${fn:split(cl.listName, ' ')[0]}" sortable="true"
									property="bankingList[${index.index}].listValue"
									format="{0,number,#,###,##0.00}" class="right" headerClass="right"
									total="true"  />
							</c:forEach>
						</display:table>
					</div>
				</div>
			</div>
		</div>
	</section>
</article>

<script>
	$(function() {
	
		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#limontlyBanking").addClass("active");
		$('.total').each(function() {
			$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());
			
		});
	});
	
</script>