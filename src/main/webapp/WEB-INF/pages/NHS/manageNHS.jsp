<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
#no1 {
	border-bottom: 1px dashed #e0e5ea;
	font-size: 0.94rem;
	margin-top: 0.55rem;
	text-align: center;
	margin-bottom: 5px;
}

.form-control {
	line-height: 0.0;
	padding: 0.225rem;
}

input {
	text-align: right;
	padding: 0.3rem;
}
</style>
<article class="content forms-page">
	<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
					<div class="col-md-12">
						<div class="section "
							style="margin-bottom: 6px; margin-top: -14px; margin-left: 10px;">
							<span class="text-danger"
								style="margin-right: 16px; font-size: 12px">* Denotes
								Required Field</span>
						</div>
					</div>

					<form:form modelAttribute="nshscript" method="POST"
						action="${pageContext.request.contextPath}/nhs/manageNHSScript.do"
						role="form">
						<form:hidden path="branchId" />
						<div class="col-md-12">

							<div class="col-md-12">
								<div class="subtitle-block">
									<h3 class="subtitle">NHS Script Details</h3>
								</div>
								<fieldset>
									<div class="col-md-12">

										<div class="col-sm-3">
											<div class="form-group">
												<br /> <label>Prescription Charges</label>

											</div>
										</div>
										<c:choose>
											<c:when test="${empty nshscript.date}">
												<fmt:formatDate var="fmtDate"
													value="<%=new java.util.Date()%>" pattern="dd/MM/yyyy" />
											</c:when>
											<c:otherwise>
												<fmt:formatDate var="fmtDate" value="${nshscript.date}"
													pattern="dd/MM/yyyy" />
											</c:otherwise>
										</c:choose>



										<c:choose>
											<c:when
												test="${not empty nshscript 
													&& not empty mode}">
												<fmt:formatDate var="fmtDate" value="${nshscript.date}"
													pattern="dd/MM/yyyy" />
												<div class="col-sm-2">
													<label class="control-label" for="formGroupExampleInput2">Date
													</label><label class="text-danger">*</label>
													<form:input path="date" class="form-control"
														required="required" value="${fmtDate}" readonly="true"
														style="text-align: left;" id="frm" />
													<form:hidden path="id" />
													<form:errors path="date" cssClass="error" />
												</div>
											</c:when>
											<c:otherwise>
												<div class="col-sm-2">
													<label class="control-label" for="formGroupExampleInput2">Date
													</label><label class="text-danger">*</label>
													<c:choose>
														<c:when test="${ not empty status && status=='m'}">
															<form:input path="date" class="form-control"
																required="required" id="frm" style="text-align: left;" />
														</c:when>
														<c:otherwise>
															<form:input path="date" class="form-control"
																required="required" id="frm" value="${fmtDate}"
																style="text-align: left;" />
														</c:otherwise>
													</c:choose>


													<form:errors path="date" cssClass="error" />
												</div>
											</c:otherwise>
										</c:choose>
										<div class="col-sm-2 ">
											<label class="control-label pull-right" for="formGroupExampleInput2">Current
											</label>
											<fmt:formatNumber type="number" minFractionDigits="2"
												value="${currentPrescriptionCharge.parameterValue}" var="cp" />
											<form:input path="prescriptionCharge" class="form-control"
												required="required" readonly="true" id="prescriptionCharge"
												value="${cp}" onKeyPress="return isNumberKey(this,event)"
												maxlength="8" />
											<form:errors path="prescriptionCharge" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Previous
											</label>
											<fmt:formatNumber type="number" minFractionDigits="2"
												value="${previousPrescriptionCharge.parameterValue}"
												var="pp" />
											<form:input path="prescriptionChargeOld" class="form-control"
												required="required" readonly="true"
												id="prescriptionChargeOld" value="${pp}"
												onKeyPress="return isNumberKey(this,event)" maxlength="8" />
											<form:errors path="prescriptionChargeOld" cssClass="error" />

										</div>
										<div class="col-sm-3">
											<label class="control-label pull-right" for="formGroupExampleInput2">Private
												Prescription Amount </label>
											<form:input path="privatePrescriptionValue"
												class="form-control" id="privatePrescriptionValue"
												onKeyPress="return isNumberKey(this,event)" maxlength="8" />
											<form:errors path="privatePrescriptionValue" cssClass="error" />

										</div>

									</div>


									<div class="col-md-12">
										<div class="col-sm-3">
											<br />
											<div class="form-group">
												<label>Exempt PAPER </label>

											</div>
										</div>

										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Forms
											</label>
											<form:input path="exemptForm" class="form-control"
												required="required" id="exemptForm" onkeyup="formaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="exemptForm" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Items
											</label>
											<form:input path="exemptItem" class="form-control"
												required="required" id="exemptItem" onkeyup="itemaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="exemptItem" cssClass="error" />
										</div>

									</div>

									<div class="col-md-12">
										<div class="col-sm-3">
											<div class="form-group">
												<label>Exempt EPS</label>

											</div>
										</div>

										<div class="col-sm-2">

											<form:input path="contraceptiveForm" class="form-control"
												required="required" id="contraceptiveForm"
												onkeyup="formaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="contraceptiveForm" cssClass="error" />
										</div>
										<div class="col-sm-2">

											<form:input path="contraceptiveItem" class="form-control"
												required="required" id="contraceptiveItem"
												onkeyup="itemaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="contraceptiveItem" cssClass="error" />
										</div>



									</div>
									<div class="col-md-12">
										<div class="col-sm-3">
											<br />
											<div class="form-group">
												<label>Paid PAPER</label>

											</div>
										</div>

										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Forms
											</label>
											<form:input path="paidForm" class="form-control"
												required="required" id="paidForm" onkeyup="formaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="paidForm" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Items
											</label>
											<form:input path="paidItem" class="form-control"
												required="required" id="paidItem" onkeyup="itemaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="paidItem" cssClass="error" />
										</div>
										<!-- 										<div class="col-sm-2"> -->
										<!-- 											<label class="control-label" for="formGroupExampleInput2">No -->
										<!-- 												Charge Items </label> -->
										<%-- 											<form:input path="noChargeItem" class="form-control" --%>
										<%-- 												required="required" id="noChargeItem" onkeyup="itemaddtion()" --%>
										<%-- 												onKeyPress="return isNumber(this, event)" maxlength="4" /> --%>
										<%-- 											<form:errors path="noChargeItem" cssClass="error" /> --%>

										<!-- 										</div> -->

									</div>

									<div class="col-md-12">
										<div class="col-sm-3">
											<div class="form-group">
												<label>Paid EPS</label>

											</div>
										</div>

										<div class="col-sm-2">

											<form:input path="paidFormOld" class="form-control"
												required="required" id="paidFormOld" onkeyup="formaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="paidFormOld" cssClass="error" />
										</div>
										<div class="col-sm-2">

											<form:input path="paidItemOld" class="form-control"
												required="required" id="paidItemOld" onkeyup="itemaddtion()"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="paidItemOld" cssClass="error" />
										</div>
										<!-- 										<div class="col-sm-2"> -->

										<%-- 											<form:input path="noChargeItemOld" class="form-control" --%>
										<%-- 												required="required" id="noChargeItemOld" onkeyup="itemaddtion()" --%>
										<%-- 												onKeyPress="return isNumber(this, event)" maxlength="4" /> --%>
										<%-- 											<form:errors path="noChargeItemOld" cssClass="error" /> --%>

										<!-- 										</div> -->

									</div>
									<div class="col-md-12">
										<div class="col-sm-3" style="padding-top: 19px;">

											<label>Totals</label>


										</div>

										<div class="col-sm-2" style="padding-top: 19px;">
											<input type="text" name="totalform" id="totalform"
												class="form-control" id="totalform"
												onKeyPress="return isNumber(this, event)" maxlength="4"
												readonly="readonly" />

										</div>
										<div class="col-sm-2" style="padding-top: 19px;">
											<input type="text" name="totalitem" id="totalitem"
												class="form-control"
												onKeyPress="return isNumber(this, event)" maxlength="4"
												readonly="readonly" />


										</div>
										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">NHS
												Levy </label><input type="text" name="NHSlevy" class="form-control"
												id="NHSlevy" onKeyPress="return isNumber(this, event)"
												maxlength="4" readonly="readonly" />


										</div>

									</div>
								</fieldset>
							</div>

							<div class="col-md-12">
								<div class="col-md-12 text-info" id="no1">Of which(NB: the
									figures below will NOT be Counted in Your totals)</div>

								<fieldset>

									<div class="col-md-12">
										<div class="col-sm-3">
											<div class="form-group">
												<br /> <label>Substance Misuse</label>
											</div>
										</div>

										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Forms
											</label>
											<form:input path="subMisForm" class="form-control"
												id="subMisForm" onKeyPress="return isNumber(this, event)"
												maxlength="4" />
											<form:errors path="subMisForm" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">Items
											</label>
											<form:input path="subMisItems" class="form-control"
												id="subMisItems" onKeyPress="return isNumber(this, event)"
												maxlength="4" />
											<form:errors path="subMisItems" cssClass="error" />


										</div>
										<div class="col-sm-2">
											<label class="control-label pull-right" for="formGroupExampleInput2">No.
												of Patients </label> <form:input path="subMisPatients"
												class="form-control" readonly="true"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
										</div>

									</div>

									<div class="col-md-12">
										<div class="col-sm-3">
											<div class="form-group">
												<label>Repeat Dispensing</label>

											</div>
										</div>

										<div class="col-sm-2">

											<form:input path="repDisForm" class="form-control"
												id="repDisForm" onKeyPress="return isNumber(this, event)"
												maxlength="4" />
											<form:errors path="repDisForm" cssClass="error" />

										</div>
										<div class="col-sm-2">
											<form:input path="repDisItems" class="form-control"
												id="repDisItems" onKeyPress="return isNumber(this, event)"
												maxlength="4" />
											<form:errors path="repDisItems" cssClass="error" />

										</div>
										<div class="col-sm-2">
											<form:input path="repDisPatients" class="form-control"
												readonly="true"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
										</div>

									</div>
									<!--
									<div class="col-md-12">
										<div class="col-sm-3">

											<div class="form-group">
												<br /> <label>Private Prescriptions </label>

											</div>
										</div>

										<div class="col-sm-2">
											<label class="control-label" for="formGroupExampleInput2">Items
											</label>
											<form:input path="privatePrescriptionItems"
												class="form-control" required="required"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="privatePrescriptionItems" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label" for="formGroupExampleInput2">Value
											</label>
											<form:input path="privatePrescriptionValue"
												class="form-control" required="required"
												onKeyPress="return isNumber(this, event)" maxlength="8" />
											<form:errors path="privatePrescriptionValue" cssClass="error" />
										</div>


									</div>
								
									<div class="col-md-12">
										<div class="col-sm-3">

											<div class="form-group">
												<br /> <label>Emergency Supplies/VET Prescription </label>

											</div>
										</div>

										<div class="col-sm-2">
											<label class="control-label" for="formGroupExampleInput2">Items
											</label>
											<form:input path="otherPrescriptionItems"
												class="form-control" required="required"
												onKeyPress="return isNumber(this, event)" maxlength="4" />
											<form:errors path="otherPrescriptionItems" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label" for="formGroupExampleInput2">Value
											</label>
											<form:input path="otherPrescriptionValue"
												class="form-control" required="required"
												onKeyPress="return isNumber(this, event)" maxlength="8" />
											<form:errors path="otherPrescriptionValue" cssClass="error" />
										</div>
										<div class="col-sm-2">
											<label class="control-label" for="formGroupExampleInput2">VAT
											</label>
											<form:input path="otherPrescriptionVat" class="form-control"
												required="required"
												onKeyPress="return isNumberKey(this, event)" maxlength="8" />
											<form:errors path="otherPrescriptionVat" cssClass="error" />

										</div>

									</div>
									 -->
								</fieldset>
								<fieldset>
									<c:choose>
										<c:when test="${not empty report}">
											<section class="section pull-right">
												<br /> <a
													href="${pageContext.request.contextPath}/report/nhsReportPg.do"
													type="button" class="btn btn-secondary"><i class="fa fa-arrow-left"> &nbsp;Back</i></a> <input
													type="hidden" id="report" value="${report}">
											</section>
										</c:when>
										<c:otherwise>

											<section class="section pull-right">
												<br /> <a
													href="${pageContext.request.contextPath}/nhs/searchPg.do"
													type="button" class="btn btn-secondary"><i class="fa fa-arrow-left"> &nbsp;Back</i></a>
												<button class="btn btn-secondary" type="reset">Reset</button>
												<c:choose>
													<c:when test="${not empty mode}">
														<button type="submit" name="edit" value="edit"
															class="btn btn-primary" onclick="return validate();">Save</button>
														<input type="hidden" id="mode" value="${mode}">
													</c:when>
													<c:otherwise>
														<button type="submit" name="add" value="add"
															class="btn btn-primary" onclick="return validate();">Add</button>
													</c:otherwise>
												</c:choose>
											</section>
										</c:otherwise>
									</c:choose>

								</fieldset>
							</div>
						</div>



					</form:form>
				</div>
			</div>
		</div>
	</section>
</article>

<input type="hidden" value="${nhsRemDay}" id="nhssize" />
<script>
	$(document).ready(function() {
		itemaddtion();
		formaddtion();
	

		if ($('#report').val() == 'report') {
			$('input').attr('readonly', 'readonly');
		}
	});
	function formaddtion() {
		var paidForm=0;
		var paidFormOld=0;
		var exemptForm=0;
		var contraceptiveForm=0;
		var totalForm=0;
		paidForm=$('#paidForm').val();
		paidFormOld=$('#paidFormOld').val();
		exemptForm=$('#exemptForm').val();
		contraceptiveForm=$('#contraceptiveForm').val();
		totalForm=parseInt(paidForm)+parseInt(paidFormOld)+parseInt(exemptForm)+parseInt(contraceptiveForm);
		if (isNaN(totalForm))
			totalForm = 0;
		$('#totalform').val(parseInt(totalForm).toFixed(0));
		

	}
	function itemaddtion() {

		var paidItem=0;
		var paidItemOld=0;
		var exemptItem=0;
		var contraceptiveItem=0;
		var totalitems=0;
		var nhslevy=0.0;
		var prescriptionCharge=0.0;
		paidItem=$('#paidItem').val();
		paidItemOld=$('#paidItemOld').val();
		exemptItem=$('#exemptItem').val();
		contraceptiveItem=$('#contraceptiveItem').val();
		prescriptionCharge=$('#prescriptionCharge').val();
		totalitems=parseInt(paidItem)+parseInt(paidItemOld)+parseInt(exemptItem)+parseInt(contraceptiveItem);
		if (isNaN(totalitems))
			totalitems = 0;
		$('#totalitem').val(parseInt(totalitems).toFixed(0));
		nhslevy=parseFloat((parseInt(paidItem)+parseInt(paidItemOld))*prescriptionCharge);
		if (isNaN(nhslevy))
			nhslevy = 0.0;
		$('#NHSlevy').val(parseFloat(nhslevy).toFixed(2))
	}
	function validate() {
		if ($('#frm').val() != '') {
			var today = new Date().getTime(), idate = $('#frm').val()
					.split("/");
			idate = new Date(idate[2], idate[1] - 1, idate[0]).getTime();
			if ((today - idate) < 0) {
				alert('Please select valid date .');

				return false;
			}
			return true;
		}
		// 		if (($("#paidForm").val() == 0 || $("#paidForm").val() == '')
		// 				|| ($("#paidItem").val() == 0 || $("#paidItem").val() == '')) {
		// 			alert("No. of Forms or Items cannot be 0");
		// 			return false;
		// 		}

		// 		if (($("#paidForm").val() > 0)
		// 				&& ($("#paidItem").val() < $("#paidForm").val())) {
		// 			alert("No. of Paid Forms Cannot be greater than No. of Paid Items ");
		// 			return false;
		// 		}

		// 		if (($("#exemptForm").val() < $("#paidForm").val())) {
		// 			alert("No. of Exempt Forms Cannot be less than No. of Paid forms ");
		// 			return false;
		// 		}
		// 		if (($("#exemptForm").val() > $("#exemptItem").val())) {
		// 			alert("No. of Exempt Forms Cannot be greater than No. of Paid Items ");
		// 			return false;
		// 		}

	}
</script>
<div class="modal fade" id="nhs-modal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog" style="width: 300px;">
		<div class="modal-content">
			<div class="modal-header" style="padding: 8px;">

				<h4 class="modal-title" id="myModalLabel">NHS Data Dates</h4>
			</div>
			<div class="modal-body"
				style="padding: 0px; height: 500px; margin-top: -10px;">
				<div class="card">
					<div class="col-md-12"
						style="background-color: #A52A2A; color: white">
						<h6>Data must be entered for these dates.Please select a date
							to enter the data.</h6>
					</div>
					<div class="col-md-12" style="height: 391px; overflow: auto;">

						<table class="table" id="myTable"
							style="margin-top: 5px; margin-left: -5px;">
							<thead>
								<tr>
									<th style="text-align: center;">Date</th>
									<th></th>
									<th style="text-align: center;">Action</th>
								</tr>
							</thead>
							<tbody id="reaminingNhsBody">
								<c:forEach items="${remainingNHSList}" var="item">
									<tr>
										<td style="text-align: center;"><c:out value="${item}" /></td>
										<td style="text-align: center;">--></td>
										<td style="text-align: center;">
											<button class="btn btn-primary btnSelect"
												style="padding: 0.09rem 2.8rem; margin-bottom: 0px">Add
											</button>
										</td>
									</tr>

								</c:forEach>

							</tbody>
						</table>
						<input type="hidden" name="reaminingnhsdate" class="form-control"
							id="reaminingnhsdate" style="text-align: left;" />
					</div>


				</div>
			</div>
		</div>
	</div>
</div>
	<input type="hidden" id="nhsReminderPeriod"
							value="${nhsReminderPeriod.parameterValue}" />
							<input type="hidden" name="listSize" class="form-control"
							id="listSize" value="${listSize}" />
<script>
	$(document).ready(function() {
	
		var a = parseInt($('#nhsReminderPeriod').val());
		var b = parseInt($('#nhssize').val());
		if ($("#status").val() == 'add') {
			if($('#listSize').val()>0){
			$('#nhs-modal').modal({
				backdrop : 'static',
				keyboard : false
			// to prevent closing with Esc button (if you want this too)

			})
			}
		}
		
			//alert($("#mode").val());
		if ($("#report").val() == 'report') {
			$("ul li").removeClass("active");
			$("#liReport").addClass("open");
			$("#liReport").addClass("active");
			$("#liReport ul").addClass("collapse in");
			$("#liNHS").addClass("active");
		}else 
			{
			
			$("ul li").removeClass("active");
			$("#liNhs").addClass("active");	
			$("#myTable").on('click', '.btnSelect', function() {
				// get the current row
				var currentRow = $(this).closest("tr");

				var col1 = currentRow.find("td:eq(0)").text().trim(); // get current row 1st TD value
				$('#nhs-modal').modal('hide');
				$('#frm').val(col1);
				$('#exemptForm').focus();
				getPrivateValue();
			});
			if ($('#nhssize').val() == 0)
				getPrivateValue();
			}
		
	});
	function addremaining() {
		if ($('#reaminingnhsdate').val() != '') {
			$('#nhs-modal').modal('hide');
			$('#frm').val($('#reaminingnhsdate').val());
			$('#exemptForm').focus();

		}
	}
	function getPrivateValue() {
		var fdate = $('#frm').val();
		if (fdate != '') {
			$
					.ajax({
						url : '${pageContext.request.contextPath}/nhs/getPrivatePrescriptionValueForNHS.do',
						data : {
							fDate : fdate
						},
						async : false,
						success : function(data) {
							$('#privatePrescriptionValue').val(data.toFixed(2));
						}
					});
		}
	}
</script>
<style type="text/css">
.highlighted {
	background: CornflowerBlue;
}
</style>
<input type="hidden" id="status" value="${status}" />