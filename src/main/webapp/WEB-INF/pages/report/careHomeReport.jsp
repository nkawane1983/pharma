<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	
<article class="content dashboard-page">
<c:if test="${empty mode}">
<jsp:include page="SearchReport.jsp" />
	<section>
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							name="carehomelist"
							requestURI="${pageContext.request.contextPath}/report/carehomeReportPg.do"
							  id="carehomelist">
							<display:column >
							<a href="${pageContext.request.contextPath}/report/monthlyCarehomeReportPg.do?id=${carehomelist[0]}">${carehomelist[1]}</a>
							</display:column>
							<display:column >
							<a href="${pageContext.request.contextPath}/report/monthlyCarehomeReportPg.do?id=${carehomelist[0]}" >${carehomelist[2]}</a>
							</display:column>
							<display:column  class="center" headerClass="center" format="{0,number,#,##0}">
							<c:out value="${carehomelist[3]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[4]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[5]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[6]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[7]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[8]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[9]}"/>
							</display:column>
								<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[10]}"/>
							</display:column>
							<display:column  class="right" headerClass="right" format="{0,number,#,##0}">
							<c:out value="${carehomelist[11]}"/>
							</display:column>
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
				<h3 class="title">${branch}<a href="${pageContext.request.contextPath}/report/carehomeReportPg.do"
									type="button" class="btn btn-danger-outline pull-right"  style="padding: 0.09rem 0.8rem;"><i class="fa fa-times"></i></a>
				</h3> 
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
							 name="carehomesreportlist"
							requestURI="${pageContext.request.contextPath}/report/monthlyCarehomeReportPg.do"
							defaultsort="1" decorator="totals"  id="carehomesreportlist">
							<display:column property="weekday" title="weekday"
								sortable="true" class="hidden" headerClass="hidden" group="1"
								media="html" />

							<display:column  title="Date" sortable="true"
								format="{0,date,dd/MM/yyyy}" >
								
							<c:choose>
									<c:when test="${carehomesreportlist.id != '0'}">

										<a
											href="${pageContext.request.contextPath}/proService/editCareHomePg.do?reportid=${carehomesreportlist.id}&branchid=${carehomesreportlist.branchid}">
											<fmt:formatDate value="${carehomesreportlist.edate}"
												pattern="dd/MM/yyyy" />
										</a>
									</c:when>
									<c:otherwise>
										<a> <fmt:formatDate value="${carehomesreportlist.edate}"
												pattern="dd/MM/yyyy" />
										</a>
									</c:otherwise>
								</c:choose>
							
							</display:column>
							<display:column property="comm7form" title="Form" sortable="true"
								class="right" headerClass="right" total="true" format="{0,number,#,##0}">
							</display:column>
							<display:column property="comm7items" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,#,##0}">
							</display:column>
							<display:column property="comm28form" title="Form" sortable="true"
								class="right" headerClass="right" total="true" format="{0,number,#,##0}">
							</display:column>
							<display:column property="comm28items" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,#,##0}">
							</display:column>
							<display:column property="total7form" title="Form" sortable="true"
								total="true" class="right" headerClass="right" format="{0,number,#,##0}">
							</display:column>
							<display:column property="total7items" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,#,##0}">
							</display:column>
							<display:column property="total28form" title="Form" sortable="true"
								total="true" class="right" headerClass="right" format="{0,number,#,##0}">
							</display:column>
							<display:column property="total28items" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,#,##0}">
							</display:column>
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
		$("#liCareHomesMDS").addClass("active");
		
		if ($("#mode").val() == 'edit') {
			$('.total').each(function() {
				$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());
			});
			settheadeditmode();
		} else {
		
			setthead();
		}
		
	
	});
	
	function setthead()
	{
		var thead = $("#carehomelist").find('thead');
		thead.append($('<tr><th></th><th></th><th></th><th colspan="4">Home MDS</th><th colspan="4">Community MDS</th></tr>'));
		thead.append($('<tr><th></th><th></th><th>No.of</th><th colspan="2">7 Day</th><th colspan="2">28 Day</th><th colspan="2">7 Day</th><th colspan="2">28 Day</th></tr>'));
		thead.append($('<tr><th>Branch No.</th><th>Branch Name</th><th>CareHomes</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th></tr>'));
		$("#carehomelist thead tr:eq(0)").remove();
	}
	function settheadeditmode()
	{
		var thead = $("#carehomesreportlist").find('thead');
		thead.append($('<tr><th class="hidden"></th><th></th><th colspan="4">Community MDS</th><th colspan="4">Total</th></tr>'));
		thead.append($('<tr><th class="hidden"></th><th></th><th colspan="2">7 Day</th><th colspan="2">28 Day</th><th colspan="2">7 Day</th><th colspan="2">28 Day</th></tr>'));
		thead.append($('<tr><th class="hidden"></th><th>Date</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th><th>Forms</th><th>Items</th></tr>'));
		$("#carehomesreportlist thead tr:eq(0)").remove();
	}

	</script>