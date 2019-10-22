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

	<section class="section">
		<div class="row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block sameheight-item">
	
					<form:form modelAttribute="nshscript" method="POST"
						action="${pageContext.request.contextPath}/nhs/manageNHSScript.do"
						role="form">
					
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

												<div class="col-sm-2">
													<label class="control-label" for="formGroupExampleInput2">Date
													</label><label class="text-danger">*</label>
													<form:input path="date" class="form-control"
														required="required" value="${fmtDate}" readonly="true"
														style="text-align: left;" id="frm" />
													
													<form:errors path="date" cssClass="error" />
												</div>
									
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
								
											<section class="section pull-right">
												<br /> <a
													href="${pageContext.request.contextPath}/report/amendNhsReportPg.do"
													type="button" class="btn btn-secondary"><i class="fa fa-arrow-left"> &nbsp;Back</i></a> <input
													type="hidden" id="report" value="${report}">
							
					
														<button type="submit" name="amendUpdate" value="amendUpdate"
															class="btn btn-primary" onclick="return validate();">Add</button>
								
											</section>
							
								</fieldset>
							</div>
						</div>

<form:hidden path="id" />
	<form:hidden path="branchId" />
					</form:form>
				</div>
			</div>
		</div>
	</section>
</article>



<script>
	$(document).ready(function() {
		itemaddtion();
		formaddtion();

			$("ul li").removeClass("active");
			$("#liReport").addClass("open");
			$("#liReport").addClass("active");
			$("#liReport ul").addClass("collapse in");
			$("#liNHS").addClass("active");
		
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
<style type="text/css">
.highlighted {
	background: CornflowerBlue;
}
</style>
<input type="hidden" id="status" value="${status}" />