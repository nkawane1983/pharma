<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
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
						<div class="table-flip-scroll rowClick">
							<display:table
								class="table table-bordered table-hover flip-content"
								name="nhslist"
								requestURI="${pageContext.request.contextPath}/report/${reportUrl}"
								 id="nhslist2" decorator="totals">
								 <c:if test="${reportType=='report'}">
								 <display:column property="branchInternalId" title="Branch No."
									sortable="true" paramId="id" paramProperty='id'
									href="${pageContext.request.contextPath}/report/nhsMonthlyReportPg.do"  class="hidden" headerClass="hidden" />
								</c:if>
								<c:if test="${reportType=='amend'}">
								 <display:column property="branchInternalId" title="Branch No."
									sortable="true" paramId="id" paramProperty='id'
									href="${pageContext.request.contextPath}/report/amendmentsNhsPg.do"  class="hidden" headerClass="hidden" />
								</c:if>
								<display:column property="branchInternalId" title="Branch No."
									sortable="true"  >
								</display:column>

								<display:column property="branchName" title="Branch Name"
									sortable="true"  maxWords="1">
								</display:column>
								<display:column property="totalform" title="Form"
									sortable="true" total="true" class="right" headerClass="right" format="{0,number,00}">
								</display:column>
								<display:column property="totalitems" title="Items"
									sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="paidform" title="Form" sortable="true"
									class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="paiditems" title="Items"
									sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="nocharge" title="No Chg"
									sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="exeform" title="Form" sortable="true"
									class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="exeitems" title="Items"
									sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="otheritems" title="Items"
									sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="othervalue" title="Value"
									sortable="true" format="{0,number,00.00}"
									class="right" headerClass="right" total="true">
								</display:column>
								<display:column property="othervat" title="Vat" sortable="true"
									format="{0,number,00.00}" class="right" headerClass="right"
									total="true">
								</display:column>
								<display:column property="privateitems" title="Items"
									sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
								</display:column>
								<display:column property="privatevalue" title="Value"
									sortable="true" format="{0,number,00.00}"
									class="right" headerClass="right" total="true">
								</display:column>
								<display:column property="nhslevy" title="Levy" sortable="true"
									format="{0,number,00.00}" class="right" headerClass="right"
									total="true">
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
				<h3 class="title">${branch}<a href="${pageContext.request.contextPath}/report/${reportUrl}"
									type="button" class="btn btn-danger-outline pull-right"  style="padding: 0.09rem 0.8rem;"><i class="fa fa-times"></i></a></h3>
			</div>

			<jsp:scriptlet>org.displaytag.decorator.TotalTableDecorator totals = new org.displaytag.decorator.TotalTableDecorator();
				totals.setTotalLabel("Monthly Total");
				totals.setSubtotalLabel("Weekly Total");
				pageContext.setAttribute("totals", totals);</jsp:scriptlet>

			<div class="col-md-12">
				<div class="card card-block">
					<div class="table-flip-scroll rowClick">
						<display:table
							class="table table-bordered table-hover flip-content"
							 name="nhslist"
							requestURI="${pageContext.request.contextPath}/report/nhsMonthlyReportPg.do"
							 decorator="totals"  id="nhslist3">
							<display:column title="Date" sortable="true"
								format="{0,date,dd/MM/yyyy}"  class="hidden" headerClass="hidden">
								<c:choose>
									<c:when test="${nhslist3.nhsid != '0'}">
	 <c:if test="${reportType=='report'}">
										<a
											href="${pageContext.request.contextPath}/nhs/editNHSPg.do?reportid=${nhslist3.nhsid}">
											<fmt:formatDate value="${nhslist3.edate}"
												pattern="dd/MM/yyyy" />
										</a>
										</c:if>
			 <c:if test="${reportType=='amend'}">
										<a
											href="${pageContext.request.contextPath}/nhs/amendmentsNHSPg.do?reportid=${nhslist3.nhsid}">
											<fmt:formatDate value="${nhslist3.edate}"
												pattern="dd/MM/yyyy" />
										</a>
										</c:if>
									</c:when>
									<c:otherwise>
										<a> <fmt:formatDate value="${nhslist3.edate}"
												pattern="dd/MM/yyyy" />
										</a>
									</c:otherwise>
								</c:choose>
							</display:column>
							<display:column property="weekday" title="weekday"
								sortable="true" class="hidden" headerClass="hidden" group="1"
								media="html" />
								<display:column title="Date" sortable="true" property="edate"
								format="{0,date,dd/MM/yyyy}" />
								
								
							<display:column property="totalform" title="Form" sortable="true"
								total="true" class="right" headerClass="right" format="{0,number,00}">
							</display:column>
							<display:column property="totalitems" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="paidform" title="Form" sortable="true"
								class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="paiditems" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="nocharge" title="No Chg"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="exeform" title="Form" sortable="true"
								class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="exeitems" title="Items" sortable="true"
								class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="otheritems" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="othervalue" title="Value"
								sortable="true" format="{0,number,00.00}"
								class="right" headerClass="right" total="true">
							</display:column>
							<display:column property="othervat" title="Vat" sortable="true"
								format="{0,number,00.00}" class="right" headerClass="right"
								total="true">
							</display:column>
							<display:column property="privateitems" title="Items"
								sortable="true" class="right" headerClass="right" total="true" format="{0,number,00}">
							</display:column>
							<display:column property="privatevalue" title="Value"
								sortable="true" format="{0,number,00.00}"
								class="right" headerClass="right" total="true">
							</display:column>
							<display:column property="nhslevy" title="Levy" sortable="true"
								format="{0,number,00.00}" class="right" headerClass="right"
								total="true">
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

<script>
	$(function() {
	
		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#liNHS").addClass("active");
	
		if ($("#mode").val() == 'Edit') {
			$('.total').each(function() {
				$(this).find("td:eq(2)").text($(this).find("td:eq(1)").text());
				
			});
		settheadeditmode();
		} else {
			$('.total').each(function() {
				$(this).find("td:eq(1)").text("Total");
			});
			setthead();

		}
	});
	
	function setthead()
	{
		var thead = $("#nhslist2").find('thead');
		thead.append($('<tr><th colspan="2">Branch</th><th colspan="2">Total </th><th colspan="3">Paid </th><th colspan="2">Exempt </th><th colspan="3">Other Script</th><th colspan="2">Private</th><th>NHS</th></tr>'));
		thead.append($('<tr><th>Branch No.</th><th>Branch Name</th><th class="right">Form</th><th class="right">Items</th><th class="right">Form</th><th class="right">Items</th><th class="right">No Chg</th><th class="right">Form</th><th class="right">Items</th><th class="right">Items</th><th>Value</th><th>Vat</th><th>Items</th><th>Value</th><th>Levy</th></tr>'));
		$("#nhslist2 thead tr:eq(0)").remove();
	}
	function settheadeditmode()
	{
		var thead = $("#nhslist3").find('thead');
		thead.append($('<tr><td class="hidden"></td><th></th><th colspan="2" class="center">Total </th><th colspan="3" class="center">Paid </th><th colspan="2" class="center">Exempt </th><th colspan="3"class="center">Other Script</th><th colspan="2">Private</th><th>NHS</th></tr>'));
		thead.append($('<tr><td class="hidden">weekday</td><th class="left">Date</th><th class="right">Form</th><th class="right">Items</th><th class="right">Form</th><th class="right">Items</th><th class="right">No Chg</th><th class="right">Form</th><th class="right">Items</th><th class="right">Items</th><th class="right">Value</th><th class="right">Vat</th><th class="right">Items</th><th class="right">Value</th><th class="right">Levy</th></tr>'));
		$("#nhslist3 thead tr:eq(0)").remove();
	}
	
</script>