<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
							export="false" name="nhsList"
							requestURI="${pageContext.request.contextPath}/nhs/searchPg.do"
							pagesize="60" defaultsort="1" id="nhsList" decorator="totals"
							keepStatus="true">
							<display:column property="weekNo" title="weekNo" sortable="true"
								class="hidden" headerClass="hidden" media="html" group="1"
								href="${pageContext.request.contextPath}/nhs/editNHSPg.do"
								paramId="id" paramProperty='id' />
							<display:column property="date" title=" Date" sortable="true"
								format="{0,date,dd/MM/yyyy}" />

							<display:column property="totalForm" title="Total Form"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="totalItems" title="Total Item"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />


							<display:column property="paidForm" title="Paid Paper Form"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="paidItem" title="Paid Paper Item"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />
							<display:column property="paidFormOld" title="Paid EPS Form"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="paidItemOld" title="Paid EPS Item"
								sortable="true"  format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="exemptForm" title="ex.Paper Form"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="exemptItem" title="ex. Paper Items"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="contraceptiveForm" title="ex.EPS Form"
								sortable="true" format="{0,number,#,##0}" class="right"
								headerClass="right" total="true" />

							<display:column property="contraceptiveItem"
								title="ex. EPS Items" sortable="true" format="{0,number,#,##0}"
								class="right" headerClass="right" total="true" />
								
							<display:column property="repDisForm"		title="Rpt.Dip Forms" 
								sortable="true" format="{0,number,#,##0}"
								class="right" headerClass="right" total="true" />
							<display:column property="repDisItems"
								title="Rpt.Dip Items" sortable="true" format="{0,number,#,##0}"
								class="right" headerClass="right" total="true" />

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
		$("#liNhs").addClass("active");
		$('.total').each(function() {
			$(this).find("td:eq(1)").text($(this).find("td:eq(0)").text());
			
		});
	});
</script>
