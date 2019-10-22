<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
.form-control {
	line-height: 0.0;
	padding: 0.225rem;
}
</style>
<article class="content dashboard-page">

	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<form:form method="POST"
					action="${pageContext.request.contextPath}/proService/manageCareHomePg.do"
					role="form" onsubmit="return validationCareHome()"
					modelAttribute="ScriptItems">
					<div class="card card-block">
						<div class="subtitle-block">
							<h3 class="subtitle">Care Homes & MDS Details</h3>
						</div>
						<div class="col-md-12">
							<div class="col-sm-2">

								<c:choose>
									<c:when test="${empty ScriptItems.eventDate}">
										<fmt:formatDate var="fmtDate"
											value="<%=new java.util.Date()%>" pattern="dd/MM/yyyy" />
									</c:when>
									<c:otherwise>
										<fmt:formatDate var="fmtDate" value="${ScriptItems.eventDate}"
											pattern="dd/MM/yyyy" />
									</c:otherwise>
								</c:choose>
								<label class="control-label" for="formGroupExampleInput2">Date
								</label> <input name="eventDate" class="form-control" type="text"
									 autofocus="autofocus"
									style="text-align: left;" tabindex="1" value="${fmtDate}"
									id="frm" />
							</div>
							<div class="col-sm-10" style="text-align: center;">
								<label class="control-label" for="formGroupExampleInput2"
									style="font-size: 20px;"> (These will not affect your
									overall total,this is just data collection excercise) </label>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-sm-3" style="margin-top: 18px;">
								<label class="control-label" for="formGroupExampleInput2">Home
									Name </label> <select class="form-control"
									style="padding: 0.25px; height: 25.2px;" tabindex="2"
									id="homeName">
									<option value="">----Select----</option>
									<c:forEach items="${carehome}" var="carehome">
										<option value="${carehome.description}" isred="${carehome.id}">${carehome.description}</option>
									</c:forEach>
								</select>
							</div>
							<div class="col-sm-3">
								<div style="text-align: center;">
									<label class="control-label" for="formGroupExampleInput2">
										7 Day Scripts </label>
								</div>
								<div class="col-md-12">
									<div class="col-sm-6">
										<label class="control-label pull-right"
											for="formGroupExampleInput2">forms </label> <input
											name="sevDayForm" class="form-control" type="text"
											style="text-align: right;" tabindex="3" id="sevDayForm"
											onkeyup="calculation()"
											onKeyPress="return isNumber(this, event)" />
									</div>
									<div class="col-sm-6">
										<label class="control-label pull-right"
											for="formGroupExampleInput2">items </label> <input
											name="sevDayItems" class="form-control" type="text"
											style="text-align: right;" tabindex="4" id="sevDayItems"
											onkeyup="calculation()"
											onKeyPress="return isNumber(this, event)" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div style="text-align: center;">
									<label class="control-label" for="formGroupExampleInput2">
										28 Day Scripts </label>
								</div>
								<div class="col-md-12">
									<div class="col-sm-6">
										<label class="control-label pull-right"
											for="formGroupExampleInput2">forms </label> <input
											name="tewDayForm" class="form-control" type="text"
											autofocus="autofocus" style="text-align: right;" tabindex="5"
											id="tewDayForm" onkeyup="calculation()"
											onKeyPress="return isNumber(this, event)" />
									</div>
									<div class="col-sm-6">
										<label class="control-label pull-right"
											for="formGroupExampleInput2">items </label> <input
											name="tewDayItems" class="form-control" type="text"
											autofocus="autofocus" style="text-align: right;" tabindex="6"
											id="tewDayItems" onkeyup="calculation()"
											onKeyPress="return isNumber(this, event)" />
									</div>
								</div>
							</div>
							<div class="col-sm-3">
								<div style="text-align: center;">
									<label class="control-label" for="formGroupExampleInput2">
										Total </label>
								</div>
								<div class="col-md-12">
									<div class="col-sm-6">
										<label class="control-label pull-right"
											for="formGroupExampleInput2">forms </label> <input
											name="totalForms" class="form-control" type="text"
											style="text-align: right;" readonly="readonly"
											id="totalForms" />
									</div>
									<div class="col-sm-6">
										<label class="control-label pull-right"
											for="formGroupExampleInput2">items </label> <input
											name="totalItems" class="form-control" type="text"
											style="text-align: right;" readonly="readonly"
											id="totalItems" />
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-12" style="margin-top: 12px;">
						<c:if test="${empty report}">
							<section class="section pull-right">
								<button type="button" name="add" value="add"
									class="btn btn-primary" id="add" disabled="disabled"
									tabindex="7" onclick="addCareHome()">Add</button>
								<button class="btn btn-secondary" type="button" tabindex="8"
									onclick="resetValue()">Cancel</button>
								<button class="btn btn-secondary" type="button"
									disabled="disabled" id="delete" onclick="deleteCareHome()">Delete</button>

							</section>
							</c:if>
						</div>
						<div class="col-md-12">
							<table class="table">

								<thead>
									<tr>
										<th></th>
										<th colspan="2" style="text-align: center;">7 Day Scripts
										</th>
										<th colspan="2" style="text-align: center;">28 Day
											Scripts</th>
										<th colspan="2" style="text-align: center;">Total</th>
									</tr>
									<tr>
										<th style="text-align: center; width: 276px;">Home Name</th>
										<th style="text-align: center; width: 126px;">Forms</th>
										<th style="text-align: center; width: 124px;">Items</th>
										<th style="text-align: center; width: 126px;">Forms</th>
										<th style="text-align: center; width: 124px;">Items</th>
										<th style="text-align: center; width: 126px;">Forms</th>
										<th style="text-align: center; width: 124px;">Items</th>
									</tr>
								</thead>
							</table>

							<div
								style="height: 182px; overflow: auto; border: 1px solid #52BCD3 !important;">
								<c:if test="${not empty ScriptItems.getCareHomeScriptId()}">
									<c:forEach items="${ScriptItems.getCareHomeScriptId()}"
										var="scriptitems" varStatus="count">
										<form:hidden path="careHomeScriptId[${count.index}].id" />

									</c:forEach>
								</c:if>

								<table class="table">
									<tbody id="careBody">

										<c:if test="${not empty ScriptItems.getCareHomeScriptId()}">
											<c:forEach items="${ScriptItems.getCareHomeScriptId()}"
												var="scriptitems" varStatus="count">

												<tr>
													<c:forEach items="${carehome}" var="carehomes">
														<c:if test="${carehomes.id==scriptitems.careHomeId}">
															<td
																style='width: 276px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${carehomes.description}</td>

															<form:hidden
																path="careHomeScriptId[${count.index}].careHomeId" />
														</c:if>
													</c:forEach>


													<td
														style='text-align: right; width: 126px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${scriptitems.sevenForm}</td>
													<form:hidden
														path="careHomeScriptId[${count.index}].sevenForm" />
													<td
														style='text-align: right; width: 126px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${scriptitems.sevenItems}</td>
													<form:hidden
														path="careHomeScriptId[${count.index}].sevenItems" />
													<td
														style='text-align: right; width: 126px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${scriptitems.twentyEightForm}</td>

													<form:hidden
														path="careHomeScriptId[${count.index}].twentyEightForm" />
													<td
														style='text-align: right; width: 126px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>${scriptitems.twentyEightItems}</td>
													<form:hidden
														path="careHomeScriptId[${count.index}].twentyEightItems" />
													<td
														style='text-align: right; width: 126px; border-bottom: 1px solid #52BCD3; border-right: 1px solid #52BCD3;'>
														0</td>
													<td
														style='text-align: right; width: 124px; border-bottom: 1px solid #52BCD3;'>0
													</td>
												</tr>

											</c:forEach>
										</c:if>
									</tbody>
								</table>
								<input type="hidden" id=caretrindex>
							</div>
							<table>
								<tfoot>
									<tr>
										<td style="width: 276px; text-align: right;">Care Homes
											Sub Total</td>
										<td style="text-align: right;"><input
											name="sevSubtotalForm" class="form-control" type="text"
											id="sevSubtotalForm" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="sevSubtotalItems" class="form-control" type="text"
											style="text-align: right;" id="sevSubtotalItems"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="tewSubtotalForm" id="tewSubtotalForm"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="tewSubtotalItems" id="tewSubtotalItems"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="totalSubtotalForm" id="totalSubtotalForm"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="totalSubtotalItems" id="totalSubtotalItems"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
									</tr>
									<tr>
										<td style="width: 276px; text-align: right;">Community
											MDS</td>
										<td style="text-align: right;"><form:input
												path="sevenForm" id="sevComMDSForm" class="form-control"
												type="text" style="text-align: right;" tabindex="9"
												onkeyup="setMDSTotal()"
												onKeyPress="return isNumber(this, event)" /></td>
										<td style="text-align: right;"><form:input
												path="sevenItems" id="sevComMDSItems" class="form-control"
												type="text" style="text-align: right;" tabindex="10"
												onkeyup="setMDSTotal()"
												onKeyPress="return isNumber(this, event)" /></td>
										<td style="text-align: right;"><form:input
												path="twentyEightForm" id="tewComMDSForm"
												class="form-control" type="text" style="text-align: right;"
												tabindex="11" onkeyup="setMDSTotal()"
												onKeyPress="return isNumber(this, event)" /></td>
										<td style="text-align: right;"><form:input
												path="twentyEightItems" id="tewComMDSItems"
												class="form-control" type="text" style="text-align: right;"
												tabindex="12" onkeyup="setMDSTotal()"
												onKeyPress="return isNumber(this, event)" /></td>
										<td style="text-align: right;"><input
											name="totalComMDSForm" id="totalComMDSForm"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" /></td>
										<td style="text-align: right;"><input
											name="totalComMDSItems" id="totalComMDSItems"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" /></td>
									</tr>
									<tr>
										<td style="width: 276px; text-align: right;">MDS Totals</td>
										<td style="text-align: right;"><input
											name="sevTotalMdsForm" id="sevTotalMdsForm"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="sevTotalMdsItems" id="sevTotalMdsItems"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="tewTotalMdsForm" id="tewTotalMdsForm"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="tewTotalMdsItems" id="tewTotalMdsItems"
											class="form-control" type="text" style="text-align: right;"
											readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input name="totalMdsForm"
											id="totalMdsForm" class="form-control" type="text"
											style="text-align: right;" readonly="readonly" value="0" /></td>
										<td style="text-align: right;"><input
											name="totalMdsItems" id="totalMdsItems" class="form-control"
											type="text" style="text-align: right;" readonly="readonly"
											value="0" /></td>
									</tr>
								</tfoot>
							</table>
							<input type="hidden" id="carehomecount" value="0" />
							<div class="col-md-12" style="margin-top: 12px;">
								<c:choose>
									<c:when test="${not empty report}">
										<section class="section pull-right">
											<br /> <a
												href="${pageContext.request.contextPath}/report/monthlyCarehomeReportPg.do?id=${branchId}"
												type="button" class="btn btn-secondary"><i class="fa fa-arrow-left"> &nbsp;Back</i></a> <input
												type="hidden" id="report" value="${report}">
												<input	type="hidden" id="branchId" value="${branchId}">
										</section>
									</c:when>
									<c:otherwise>
										<section class="section pull-right">
											<a
												href="${pageContext.request.contextPath}/proService/searchCareHomePg.do"
												type="button" class="btn btn-secondary" tabindex="15"><i class="fa fa-arrow-left"> &nbsp;Back</i></a>
											<button class="btn btn-secondary" type="reset" tabindex="14">Reset</button>
											<c:if test="${not empty mode and ScriptItems.getId()>0}">
												<form:hidden path="id" />
												<button type="submit" name="edit" value="edit"
													class="btn btn-primary" id="save" tabindex="13">Save</button>
											</c:if>
											<c:if test="${empty mode}">
												<button type="submit" name="save" value="save"
													class="btn btn-primary" id="save" tabindex="13">Save</button>
											</c:if>

										</section>
									</c:otherwise>
								</c:choose>
							</div>
						</div>

					</div>
				</form:form>
			</div>
		</div>
	</section>
</article>
<script>
	$(document)
			.ready(
					function() {
						$("ul li").removeClass("active");
						$("#liProfessionService").addClass("open");
						$("#liProfessionService ul").addClass("collapse in");
						$("#liProfessionService").addClass("active");
						$("#liCareHomesMDS").addClass("active");
						$('#frm').datepicker({
							format : 'dd/mm/yyyy',
							autoclose : true,
							
						});
					 $("#frm").on('click',function() {
			        $("#frm").datepicker("show");
			    });
						$("#carehomecount").val(
								parseInt($('#careBody tr').length)
										+ parseInt($("#carehomecount").val()));
						$("#careBody").delegate(
								'tr',
								'click',
								function() {
									$("#careBody tr").css('background-color',
											'');
									$(this).css('background-color',
											'CornflowerBlue');
									$('#caretrindex').val($(this).index());

									$('#sevDayForm').val(
											$(this).find("td:eq(1)").text());
									$('#sevDayItems').val(
											$(this).find("td:eq(2)").text());
									$('#tewDayForm').val(
											$(this).find("td:eq(3)").text());
									$('#tewDayItems').val(
											$(this).find("td:eq(4)").text());
									$('#totalForms').val(
											$(this).find("td:eq(5)").text());
									$('#totalItems').val(
											$(this).find("td:eq(6)").text());
									$('#homeName').val(
											$(this).find("td:eq(0)").text());
									$("#delete").removeAttr("disabled");
								})

						$('#careBody tr')
								.each(
										function() {

											$(this)
													.find("td:eq(5)")
													.text(
															parseInt($(this)
																	.find(
																			"td:eq(1)")
																	.text())

																	+ parseInt($(
																			this)
																			.find(
																					"td:eq(3)")
																			.text()));
											$(this)
													.find("td:eq(6)")
													.text(

															parseInt($(this)
																	.find(
																			"td:eq(2)")
																	.text())

																	+ parseInt($(
																			this)
																			.find(
																					"td:eq(4)")
																			.text()));
										})
						setSubTotalANDTotal();
						if ($('#report').val() == 'report') {
							$('input').attr('readonly', 'readonly');
							$("ul li").removeClass("active");
							$("#liReport").addClass("open");
							$("#liReport").addClass("active");
							$("#liReport ul").addClass("collapse in");
							$("#liCareHomesMDS").addClass("active");
						}
						
					});

	function calculation() {
		if ($("#sevDayForm").val() != '' || $("#sevDayItems").val() != '')
			$("#add").removeAttr("disabled");

		var a, a1, b, b1;
		a = parseInt($("#sevDayForm").val())
		if (isNaN(a) == true)
			a = 0;
		a1 = parseInt($("#sevDayItems").val())
		if (isNaN(a1) == true)
			a1 = 0;
		b = parseInt($("#tewDayForm").val())
		if (isNaN(b) == true)
			b = 0;
		b1 = parseInt($("#tewDayItems").val())
		if (isNaN(b1) == true)
			b1 = 0;

		$("#totalForms").val(a + b);
		$("#totalItems").val(a1 + b1);
	}
	function addCareHome() {
		var count;

		if ($('#caretrindex').val() != '') {
			document.getElementById("careBody").deleteRow(
					$('#caretrindex').val());
			setSubTotalANDTotal();
			$("#carehomecount").val(
					parseInt($("#carehomecount").val()) - parseInt(1));
			count = $('#caretrindex').val();
			$('#caretrindex').val('');
		} else {
			count = $("#carehomecount").val();
		}
		if ($("#homeName").val() == '') {
			alert("Home Name be select");
			return false;
		} else if (findTr($("#homeName").val())) {
			alert("Already  Inserted.");
			return false;
		} else {

			//var len = parseInt(count) + 1;

			var carehome = "careHomeScriptId[" + (count) + "].careHomeId"
			var carehomeid = "careHomeScriptId" + (count) + ".careHomeId"

			var form7 = "careHomeScriptId[" + (count) + "].sevenForm";
			var formid7 = "careHomeScriptId" + (count) + ".sevenForm";
			var items7 = "careHomeScriptId[" + (count) + "].sevenItems";
			var itemsid7 = "careHomeScriptId" + (count) + ".sevenItems";

			var form28 = "careHomeScriptId[" + (count) + "].twentyEightForm";
			var formid28 = "careHomeScriptId" + (count) + ".twentyEightForm";
			var items28 = "careHomeScriptId[" + (count) + "].twentyEightItems";
			var itemsid28 = "careHomeScriptId" + (count) + ".twentyEightItems";

			var sevenform = $("#sevDayForm").val();
			var sevenitems = $("#sevDayItems").val();
			var tewform = $("#tewDayForm").val();
			var tewitems = $("#tewDayItems").val();

			if (sevenform == '')
				sevenform = 0;
			if (sevenitems == '')
				sevenitems = 0;
			if (tewform == '')
				tewform = 0;
			if (tewitems == '')
				tewitems = 0;
			$('#careBody')
					.append(
							"<tr><td style='width: 276px; border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
									+ $("#homeName").val()
									+ "</td>"
									+ "<input name="
									+ carehome
									+ " id="
									+ carehomeid
									+ " value="
									+ $("#homeName").find(':selected').attr(
											'isred')
									+ " type='hidden'/><td style='text-align: right; width: 126px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
									+ sevenform
									+ "<input name="
									+ form7
									+ " id="
									+ formid7
									+ " value="
									+ sevenform
									+ " type='hidden'/></td><td style='text-align: right; width: 124px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
									+ sevenitems
									+ "<input name="
									+ items7
									+ " id="
									+ itemsid7
									+ " value="
									+ sevenitems
									+ " type='hidden'/></td><td style='text-align: right; width: 126px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
									+ tewform
									+ "<input name="
									+ form28
									+ " id="
									+ formid28
									+ " value="
									+ tewform
									+ " type='hidden'/></td><td style='text-align: right; width: 124px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
									+ tewitems
									+ "<input name="
									+ items28
									+ " id="
									+ itemsid28
									+ " value="
									+ tewitems
									+ " type='hidden'/></td><td style='text-align: right; width: 126px;border-bottom: 1px solid #52BCD3;border-right: 1px solid #52BCD3;'>"
									+ $("#totalForms").val()
									+ "</td><td style='text-align: right; width: 124px;border-bottom: 1px solid #52BCD3;'>"
									+ $("#totalItems").val() + "</td></tr>");

			resetValue();
			setSubTotalANDTotal();
			$("#carehomecount").val(
					parseInt($("#carehomecount").val()) + parseInt(1));

			//$("#save").removeAttr("disabled");
		}
	}

	function resetValue() {
		$("#homeName").val('');
		$("#sevDayForm").val('');
		$("#sevDayItems").val('');
		$("#tewDayForm").val('');
		$("#tewDayItems").val('');
		$("#totalForms").val('');
		$("#totalItems").val('');
		$("#caretrindex").val('');

	}
	function setSubTotalANDTotal() {
		var sevform = 0, sevitems = 0, tewform = 0, tewitems = 0;
		$('#careBody tr').each(
				function() {
					sevform = parseInt(sevform)
							+ parseInt($(this).find("td:eq(1)").text());
					sevitems = parseInt(sevitems)
							+ parseInt($(this).find("td:eq(2)").text());
					tewform = parseInt(tewform)
							+ parseInt($(this).find("td:eq(3)").text());
					tewitems = parseInt(tewitems)
							+ parseInt($(this).find("td:eq(4)").text());
				});
		$("#sevSubtotalForm").val(sevform);
		$("#sevSubtotalItems").val(sevitems);
		$("#tewSubtotalForm").val(tewform);
		$("#tewSubtotalItems").val(tewitems);
		$("#totalSubtotalForm").val(sevform + tewform);
		$("#totalSubtotalItems").val(sevitems + tewitems);
		setMDSTotal();
	}
	function setMDSTotal() {
		var a, a1, b, b1;
		a = parseInt($("#sevComMDSForm").val())
		if (isNaN(a) == true)
			a = 0;
		a1 = parseInt($("#sevComMDSItems").val())
		if (isNaN(a1) == true)
			a1 = 0;
		b = parseInt($("#tewComMDSForm").val())
		if (isNaN(b) == true)
			b = 0;
		b1 = parseInt($("#tewComMDSItems").val())
		if (isNaN(b1) == true)
			b1 = 0;

		$("#totalComMDSForm").val(a + b);
		$("#totalComMDSItems").val(a1 + b1);

		$("#sevTotalMdsForm").val(parseInt($("#sevSubtotalForm").val()) + a);
		$("#sevTotalMdsItems").val(parseInt($("#sevSubtotalForm").val()) + a1);
		$("#tewTotalMdsForm").val(parseInt($("#tewSubtotalForm").val()) + b);
		$("#tewTotalMdsItems").val(parseInt($("#tewSubtotalItems").val()) + b1);
		$("#totalMdsForm").val(parseInt($("#totalSubtotalForm").val()) + a + b);
		$("#totalMdsItems").val(
				parseInt($("#totalSubtotalItems").val()) + a1 + b1);
	}
	function deleteCareHome() {
		document.getElementById("careBody").deleteRow($('#caretrindex').val());
		setSubTotalANDTotal();
		resetValue();

		if ($("#carehomecount").val() == 0)
			$("#save").attr("disabled", "disabled");
	}
	function findTr(str) {
		var bol = false;
		$('#careBody tr').each(function() {
			if (str == $(this).find("td:eq(0)").text())
				bol = true;
		});
		return bol;
	}

	function validationCareHome() {
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