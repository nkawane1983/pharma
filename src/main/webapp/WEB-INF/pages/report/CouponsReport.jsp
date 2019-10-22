<article class="content dashboard-page">
	<jsp:include page="SearchReport.jsp" />
</article>

<script type="text/javascript">
	$(function() {
		$("ul li").removeClass("active");
		$("#liReport").addClass("open");
		$("#liReport").addClass("active");
		$("#liReport ul").addClass("collapse in");
		$("#licouponss").addClass("active");
	});
	
</script>