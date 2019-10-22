<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
#ft {
	padding-top: 15px;
}

.form-control {
	line-height: 0.0;
	padding: 0.225rem;
}

input {
	text-align: right;
	padding: 0.3rem;
}

.subtitle-block {
	padding-bottom: 5px;
	margin-bottom: 5px;
}

.btn {
	margin-bottom: 5px;
	line-height: 1;
}

.table th, .table td {
	padding: 0.1rem
}
</style>
<article class="content dashboard-page">

	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<form:form method="POST"
					action="${pageContext.request.contextPath}/proService/manageCollDelvPg.do"
					role="form" onsubmit="return validationCol()"
					modelAttribute="collDelvSingup">
					<div class="card card-block">
						<div class="subtitle-block">
							<h3 class="subtitle">C & D Details</h3>
						</div>
						<div class="col-md-12">
							<div class="col-sm-2">
								<c:choose>
									<c:when test="${empty eventDate &&  empty mode}">
										<fmt:formatDate var="fmtDate"
											value="<%=new java.util.Date()%>" pattern="dd/MM/yyyy" />
										<label class="control-label" for="formGroupExampleInput2">Date
										</label>
										<input name="eventdate" class="form-control" type="text"
											autofocus="autofocus" style="text-align: left;" tabindex="1"
											value="${fmtDate}" id="frm" />
									</c:when>
									<c:otherwise>
										<fmt:formatDate var="fmtDate" value="${eventDate}"
											pattern="dd/MM/yyyy" />
										<label class="control-label" for="formGroupExampleInput2">Date
										</label>
										<input name="eventdate" class="form-control" type="text"
											autofocus="autofocus" style="text-align: left;" tabindex="1"
											value="${fmtDate}" readonly="readonly"  />
									</c:otherwise>
								</c:choose>


							</div>
							<div class=" pull-right col-sm-3">
								<div class="form-group row"
									style="margin-top: 0.5rem; margin-bottom: 0.5rem;">
									<label class="col-sm-7 form-control-label"
										for="formGroupExampleInput2">Monthly Target </label>
									<div class="col-sm-5" style="margin-left: -36px;">
										<input name="monthlytarget" class="form-control" type="text"
											readonly="readonly" style="text-align: right;" value="220" />
									</div>
								</div>
								<div class="form-group row" style="margin-bottom: 0.0rem;">
									<label class="col-sm-7 form-control-label"
										for="formGroupExampleInput2">Daily Target </label>
									<div class="col-sm-5" style="margin-left: -36px;">
										<input name="dailytarget" class="form-control" type="text"
											readonly="readonly" style="text-align: right;" value="10"
											id="dailytarget" />
									</div>
								</div>

							</div>
						</div>
						<div class="col-md-12">
							<div class="col-sm-4">
								<label class="control-label" for="formGroupExampleInput2">Staff
								</label> <select class="form-control"
									style="padding: 0.25px; height: 25.2px;" tabindex="2"
									id="staffName">
									<option value="0">----select------</option>
									<c:forEach items="${userlist}" var="userlist">
										<option value="${userlist.getAppUser().usrId}">${userlist.getAppUser().usrId}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-2">
								<label class="control-label" for="formGroupExampleInput2">Collection
								</label> <input name="collection" class="form-control" type="text"
									style="text-align: right;" tabindex="3"
									onkeypress="return isNumberKey(event)" onkeyup="calculation()"
									id="collection" />
								<div style="margin-left: 35px; margin-top: 10px;">
									<button class="btn btn-primary" type="button" tabindex="5"
										onclick="addCD()" disabled="disabled" id="add">Add</button>
								</div>
							</div>
							<div class="col-sm-2">
								<label class="control-label" for="formGroupExampleInput2">Delivery
								</label> <input name="delivery" class="form-control" type="text"
									style="text-align: right;" tabindex="4"
									onkeypress="return isNumberKey(event)" onkeyup="calculation()"
									id="delivery" />
								<div style="margin-left: 35px; margin-top: 10px;">
									<button class="btn btn-secondary" type="button" tabindex="6">Cancel</button>
								</div>
							</div>
							<div class="col-sm-2">
								<label class="control-label" for="formGroupExampleInput2">Total
								</label> <input name="total" class="form-control" type="text"
									readonly="readonly" style="text-align: right;" id="total"
									value="0" />
								<div style="margin-left: 35px; margin-top: 10px;">
									<button class="btn btn-secondary" disabled="disabled"
										type="button" tabindex="7" onclick="delCD()" id="delete">Delete</button>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-sm-10">
								<c:if test="${not empty collDelvSingup.getCollDelv()}">
									<c:forEach items="${collDelvSingup.getCollDelv()}"
										var="colldelv" varStatus="count">
										<form:hidden path="collDelv[${count.index}].id"
											value="${colldelv.id}" />
									</c:forEach>
								</c:if>
								<table class="table">

									<thead>
										<tr>
											<th style="text-align: center; width: 320px">Staff Name</th>
											<th style="text-align: center; width: 173px">Collection</th>
											<th style="text-align: center; width: 173px">Delivery</th>
											<th style="text-align: center; width: 173px">Total</th>
										</tr>

									</thead>
								</table>
								<div
									style="height: 182px; overflow: auto; border: 1px solid #52BCD3 !important;">
									<table class="table" style="">
										<tbody id="cdbody">
											<c:choose>
												<c:when test="${not empty collDelvSingup.getCollDelv()}">
													<c:forEach items="${collDelvSingup.getCollDelv()}"
														var="colldelv" varStatus="count">
														<tr>
															<td
																style='width: 319px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${colldelv.userId}</td>
															<form:hidden path="collDelv[${count.index}].userId"
																value="${colldelv.userId}" />
															<td
																style='text-align: right; width: 173px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${colldelv.collItems}</td>
															<form:hidden path="collDelv[${count.index}].collItems"
																value="${colldelv.collItems}" />
															<td
																style='text-align: right; width: 173px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${colldelv.delvItems}</td>
															<form:hidden path="collDelv[${count.index}].delvItems"
																value="${colldelv.delvItems}" />
															<td
																style='text-align: right; width: 173px; border-bottom: 1px solid #52BCD3;'>${colldelv.collItems+colldelv.delvItems}</td>
														</tr>
													</c:forEach>
												</c:when>
											</c:choose>
										</tbody>
									</table>
									<input type="hidden" id="cdtrindex">
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-sm-4" style="text-align: right;">
								<label class="control-label" for="formGroupExampleInput2">Actual
									Totals </label>
							</div>
							<div class="col-sm-2">
								<input name="atualcol" class="form-control" type="text"
									readonly="readonly" style="text-align: right;" id="atualcol"
									value="0" />

							</div>
							<div class="col-sm-2">
								<input name="atualdel" class="form-control" type="text"
									readonly="readonly" style="text-align: right;" id="atualdel"
									value="0" />

							</div>
							<div class="col-sm-2">
								<input name="atualtotal" class="form-control" type="text"
									readonly="readonly" style="text-align: right;" id="atualtotal"
									value="0" />
							</div>
							<div class="col-sm-2" style="margin-top: -18px;">
								<label class="control-label" for="formGroupExampleInput2">%
									Of Target </label> <input name="target" class="form-control"
									type="text" readonly="readonly" style="text-align: right;"
									id="target" value="0%" />
							</div>
						</div>
						<div class="col-md-12" style="margin-top: 12px;">
							<section class="section pull-right">
								<a
									href="${pageContext.request.contextPath}/proService/searchCDPg.do"
									type="button" class="btn btn-secondary" tabindex="10"><i class="fa fa-arrow-left"> &nbsp;Back</i></a>
								<button class="btn btn-secondary" type="reset" tabindex="9">Reset</button>
								<c:choose>
									<c:when test="${empty eventDate &&  empty mode}">
										<button type="submit" name="save" value="save"
											class="btn btn-primary" id="save" disabled="disabled"
											tabindex="8">Save</button>
									</c:when>
									<c:otherwise>
										<button type="submit" name="edit" value="edit"
											class="btn btn-primary" id="edit" disabled="disabled"
											tabindex="8">Save</button>
									</c:otherwise>
								</c:choose>
							</section>
						</div>


					</div>
				</form:form>
			</div>
		</div>
	</section>
</article>

<script>
	$(document).ready(function() {
		$("ul li").removeClass("active");
		$("#liProfessionService").addClass("open");
		$("#liProfessionService ul").addClass("collapse in");
		$("#liProfessionService").addClass("active");
		$("#liCD").addClass("active");
		trset();
		$("#cdbody").delegate('tr', 'click', function() {
			$("#cdbody tr").css('background-color', '');
			$(this).css('background-color', 'CornflowerBlue');
			$('#cdtrindex').val($(this).index());
			$('#staffName').val($(this).find("td:eq(0)").text());
			$('#collection').val($(this).find("td:eq(1)").text());
			$('#delivery').val($(this).find("td:eq(2)").text());
			$('#total').val($(this).find("td:eq(3)").text());
			$("#delete").removeAttr("disabled");
		});
		var len=$('#cdbody tr').length;
		if(len>=0)
			$("#edit").removeAttr("disabled");
	});
	function addCD() {
	
			if ($('#cdtrindex').val()!=''){
				document.getElementById("cdbody").deleteRow($('#cdtrindex').val());
				trset();
			}
		
		
		if ($("#staffName").val() != '') {
			if(checkstaffName($("#staffName").val())==1)
				{
				alert("Staff Name already selected.");
				}else{
					
				
			var len=$('#cdbody tr').length;
				var userid = "collDelv[" + len + "].userId"
				var useridid = "collDelv" + len + ".userId"
				
				var delvItems = "collDelv[" + len + "].delvItems"
				var delvItemsid = "collDelv" + len + ".delvItems"

				var collItems = "collDelv[" + len + "].collItems";
				var collItemsid = "collDelv" + len + ".collItems";
			
			$('#cdbody').append(
					"<tr><td style='width: 319px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>" + $("#staffName").val()
							+ "</td><input id="+useridid+" name="+userid+" value="+$("#staffName").val()+" type='hidden'/>"
								+"<td style='text-align: right; width: 173px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
							+ $("#collection").val()
							+ "</td><input id="+collItemsid+" name="+collItems+" value="+$("#collection").val()+" type='hidden'/><td style='text-align: right; width: 173px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
							+ $("#delivery").val()
							+ "</td><input id="+delvItemsid+" name="+delvItems+" value="+$("#delivery").val()+" type='hidden'/><td style='text-align: right; width: 173px;border-bottom: 1px solid #52BCD3;'>"
							+ $("#total").val() + "</td></tr>");
			
			
			if(len>=0)
				$("#save").removeAttr("disabled");
			$("#staffName").val('');
			$("#collection").val('')
			$("#cdtrindex").val('')
			$("#delivery").val('')
			$("#total").val('');
			
			trset();
			}
		} else {
			alert("Staff Name be select.");
		}
	}
	function delCD() {
			document.getElementById("cdbody").deleteRow($('#cdtrindex').val());
			$("#staffName").val("");
			$("#collection").val('');
			$("#cdtrindex").val('');
			$("#delivery").val('');
			$("#total").val('');
			trset();
		var len=$('#cdbody tr').length;
			
			if(len==0)
				$("#save").attr("disabled","disabled");
			if(len==0)
				$("#edit").attr("disabled","disabled");
		
	}
function trset()
{
	var collsum=0,delsum=0;
	$('#cdbody tr').each(function() {
		collsum=parseInt(collsum)+parseInt($(this).find("td:eq(1)").text());
		delsum=parseInt(delsum)+parseInt($(this).find("td:eq(2)").text());
	});
	$("#atualcol").val(collsum);
	$("#atualdel").val(delsum);
	$("#atualtotal").val(collsum+delsum);
	var targ=parseInt(((collsum+delsum)/parseInt($("#dailytarget").val()))*100);
	$("#target").val(targ+"%");
	}
	function calculation() {
		
		if($("#collection").val()!='' ||$("#delivery").val()!='')
			$("#add").removeAttr("disabled");
		
		var a, b;
		a = parseInt($("#collection").val())
		if (isNaN(a) == true)
			a = 0;
		b = parseInt($("#delivery").val())
		if (isNaN(b) == true)
			b = 0;
		$("#total").val(a + b);

	}
	function isNumberKey(evt)
    {
       var charCode = (evt.which) ? evt.which : event.keyCode;
    		  
    		   if(charCode==47)
    			   return false;
       if (charCode > 31 && (charCode < 46 || charCode > 57))
          return false;
       
       return true;
    }
	function checkstaffName(staffName)
	{var str
		$('#cdbody tr').each(function() {
			if($(this).find("td:eq(0)").text()==staffName)
				{
				str=1;
				}else
					{
					str=0;
					}
		});
	return str;
	}
	function validationCol() {
		if ($('#frm').val() == '') {
			alert('Please select  date .');
			return false;
		}
		if ($('#frm').val() != '') {
			var today = new Date().getTime(), idate = $('#frm').val()
					.split("/");
			idate = new Date(idate[2], idate[1] - 1, idate[0]).getTime();
			if ((today - idate) < 0) {
				alert('Please select valid date .');

				return false;
			}
		}

		return true;
	}
</script>