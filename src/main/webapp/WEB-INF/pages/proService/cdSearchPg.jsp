<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<article class="content dashboard-page">
		<jsp:include page="../commanSearch.jsp" />
	<section>
	<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="colldevList"
							requestURI="${pageContext.request.contextPath}/proService/searchCDPg.do"
							pagesize="25" defaultsort="1" id="colldev">
							<display:column property="eventDate" title="Day"
								format="{0,date,dd/MM/yyyy}" sortable="true"
								style="text-align: left; " paramId="id" paramProperty='encodeId'
									href="${pageContext.request.contextPath}/proService/editCDPg.do" />
								<display:column property="collItems" title="Collection"
								sortable="true"
								style="text-align: right; " />
								<display:column property="delvItems" title="Delivery"
								 sortable="true"
								style="text-align: right; " />
								
								<display:column  title="Total"
								 sortable="true"
								style="text-align: right; " >${colldev.collItems+colldev.delvItems}</display:column>
								<display:column  title="% of Target"
								 sortable="true"
								style="text-align: right; " >${((colldev.collItems+colldev.delvItems)*10)}%</display:column>
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
		$("#liCD").addClass("active");

	});
</script>