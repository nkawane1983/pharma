<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<fmt:parseDate pattern="yyyy-MM-dd" value="${fdate}" var="parsedDate" />
<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" var="fdate" />

<fmt:parseDate pattern="yyyy-MM-dd" value="${enddate}" var="parsedDate" />
<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy" var="edate" />

<form action="${pageContext.request.contextPath}/report/getreport.do"
	id="from1" method="post">
	<input type="hidden" name="groupObj" value="${groupid}"> <input
		type="hidden" name="branchObj" value="${branchid}"> <input
		type="hidden" name="fdateObj" value="${fdate}"> <input type="hidden"
		name="edateObj" value="${edate}"> <input type="hidden" name="type"
		id="filetype"> <input type="hidden" name="reportTypeObj" id="reportTypeObj"
		value="${reportTypeObj}">
	<div class="exportlinks">
		<b>Export options:</b> <button
			class="btn btn-link" value="Excel" onclick="setSubmit(this)" id="xls"  style="color: #4f5f6f;"
			 ><i class="fa fa-file-excel-o"></i>&nbsp; Excel</button>
		
		 <button
			class="btn btn-link" value="Excel" onclick="setSubmit(this)" id="xls_vat"  style="color: #4f5f6f;"
			 ><i class="fa fa-file-excel-o"></i>&nbsp;VAT</button>
		<button
			class="btn btn-link" value="Excel" onclick="setSubmit(this)" id="xls_sage"  style="color: #4f5f6f;"
			 ><i class="fa fa-file-excel-o"></i>&nbsp;SageFile</button>
		
	</div>
</form>
<script type="text/javascript">
function setSubmit(str) {
	var idObj=str.id.split("_");
	//alert(idObj.length)
	if(idObj.length==1)
	$('#filetype').val(str.id)
	if(idObj.length==2)
	{
		$('#filetype').val(idObj[0]);
		$('#reportTypeObj').val(idObj[1]);
	}
	
	
	//str.form.submit();
}
</script>