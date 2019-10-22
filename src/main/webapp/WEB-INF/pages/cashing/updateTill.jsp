<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<style>
.form-control {
	line-height: 0.0;
	padding: 0.225rem;
}
</style>
<c:if test="${mode=='amendTill'}">
	<article class="content forms-page">
		<form:form modelAttribute="cashing" method="POST"
			action="${pageContext.request.contextPath}/cashing/managecashing.do"
			role="form" id="myform">
			<section class="section">

				<div class="row sameheight-container">
					<div class="col-md-12">
						<div class="card card-block sameheight-item">

							<div class="col-md-12">
								<div class="col-md-12">
									<fieldset>
										<legend>Cashing Information</legend>

										<fmt:formatDate var="fmtDate" value="${cashing.date}"
											pattern="dd/MM/yyyy" />




										<div class="col-sm-3 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">Date</label>
											<form:input path="date" class="form-control left"
												value="${fmtDate}" autofocus="autofocus" tabindex="1"
												readonly="true" />
											<form:errors path="date" cssClass="error" />
										</div>
										<div class="col-sm-2 col-xs-12 ">
											<label class="control-label" for="formGroupExampleInput2">Till
												No. </label>

											<form:input path="tillNo" class="form-control left"
												id="tillNo" tabindex="2" type="text" readonly="true" />
											<form:errors path="tillNo" cssClass="error" />


										</div>
										<div class="col-sm-3 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">Reference
												No. </label>
											<form:input path="refNo" class="form-control left"
												tabindex="3" readonly="true" />
											<form:errors path="refNo" cssClass="error" />
										</div>


										<form:hidden path="id" />

									</fieldset>
								</div>


							</div>

							<div class="col-md-12">

								<div class="col-md-6">
									<fieldset>
										<legend>Till Reading:</legend>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Z Reading</label>

												</div>
											</div>
											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12">
												<div class="form-group">
													<label>A</label>

												</div>
											</div>

											<div class="col-sm-3 col-xs-12">

												<form:input path="zReading" class="form-control right"
													id="zReading" tabindex="4" onkeyup="setvalue();"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="zReading" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>No.of Voids</label>

												</div>
											</div>
											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>

											<div class="col-sm-3 col-xs-12">

												<form:input path="voids" class="form-control right"
													id="voids" tabindex="5"
													onKeyPress="return isNumber(this, event)" maxlength="8" />
												<form:errors path="voids" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Refunds(Amount)</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="refunds" class="form-control right"
													id="refunds" tabindex="6"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="refunds" cssClass="error" />
											</div>

										</div>

										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Discount</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="discount" class="form-control right"
													id="discount" tabindex="6"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="discount" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>No. of transactions</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">
												<form:input path="sales" class="form-control right"
													id="sales" tabindex="7"
													onKeyPress="return isNumber(this, event)" maxlength="8" />
												<form:errors path="sales" cssClass="error" />
											</div>

										</div>
									</fieldset>

									<fieldset>
										<legend>Private Scripts</legend>

										<div class="col-md-12">
											<div class="col-sm-8 col-xs-12">

												<div class="form-group">
													<label>Private Scripts Amount as On Z-read</label>

												</div>
											</div>

											<div class="col-sm-4 col-xs-12">

												<form:input path="zReadPrivateValue"
													class="form-control right" id="zReadPrivateValue"
													tabindex="13" onkeypress="return isNumberKey(this,event)"
													maxlength="8" />
												<form:errors path="zReadPrivateValue" cssClass="error" />
											</div>


										</div>
										<div class="col-md-12">
											<div class="col-sm-8 col-xs-12">

												<div class="form-group">
													<label>Prescription Charges as on Z-read<span
														class="text-danger">*</span></label>

												</div>
											</div>

											<div class="col-sm-4 col-xs-12">

												<form:input path="zReadLevy" class="form-control right"
													id="zReadLevy" tabindex="14"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="zReadLevy" cssClass="error" />
											</div>


										</div>
									</fieldset>
								</div>
								<div class="col-md-6">

									<fieldset>
										<legend>Actual Takings</legend>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Cash</label>

												</div>
											</div>
											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12">
												<div class="form-group"></div>
											</div>

											<div class="col-sm-3 col-xs-12">

												<form:input path="cash" class="form-control right"
													tabindex="4" onkeyup="setvalue();"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="zReading" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Cheques</label>

												</div>
											</div>
											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>

											<div class="col-sm-3 col-xs-12">

												<form:input path="cheques" class="form-control right"
													id="voids" tabindex="5"
													onKeyPress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="voids" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Cards</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="cards" class="form-control right"
													id="refunds" tabindex="6"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="refunds" cssClass="error" />
											</div>

										</div>

										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Coupons</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="coupuns" class="form-control right"
													id="discount" tabindex="6"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="discount" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Paid Outs</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">
												<form:input path="paidOut1" class="form-control right"
													id="sales" tabindex="7"
													onKeyPress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="sales" cssClass="error" />
											</div>

										</div>
									</fieldset>
									<fieldset style="height: auto;">
										<legend>Note:-</legend>


										<textarea rows="3" class="form-control" id="noteRequest"
											style="line-height: 1.1;" name="noteRequest"
											required="required"></textarea>

									</fieldset>

								</div>

							</div>
							<div class="col-md-12">
								<div class="col-md-12">
									<fieldset>
										<legend></legend>
										<div class="col-md-12">
											<div class="col-sm-12 col-xs-12">
												<section class="section pull-right">
													<a href="${pageContext.request.contextPath}/dashboard.do"
														type="button" class="btn btn-secondary" tabindex="23"><i
														class="fa fa-arrow-left"> &nbsp;Back</i></a>
													<button type="submit" name="updateTill" value="updateTill"
														id="updateButton" class="btn btn-primary" tabindex="21"
														onclick="javascript: return submitRequestSave()">Save</button>
												</section>
											</div>




										</div>
									</fieldset>
								</div>
							</div>



						</div>
					</div>
				</div>

			</section>
			<form:hidden id="tillStatus" path="tillStatus" value="0"
				class="form-control" />
			<form:hidden id="bankingId" path="bankingId" class="form-control" />
			<form:hidden path="branchId" class="form-control" />
			<form:hidden path="floatvalue" class="form-control" />
			<form:hidden path="enett" class="form-control" />
			<form:hidden path="lnett" class="form-control" />
			<form:hidden path="snett" class="form-control" />
			<form:hidden path="znett" class="form-control" />
			<form:hidden path="evat" class="form-control" />
			<form:hidden path="lvat" class="form-control" />
			<form:hidden path="svat" class="form-control" />
			<form:hidden path="zvat" class="form-control" />
			<form:hidden path="userId" class="form-control" />
			<form:hidden path="discrepancy1" class="form-control" />
			<form:hidden path="notes" class="form-control" />
			<form:hidden id="managername" path="manager" class="form-control" />
			<input type="hidden" id="amdId" name="amdId" value="${amdId}" />
		</form:form>
	</article>
	<script>
	$(document).ready(function() {
		$('textarea').bind('copy paste cut', function(e) {
			e.preventDefault(); 
		});
	});
		function submitRequestSave() {
			var strObj = $('#noteRequest').val();
			if (strObj.trim() == '') {
				alert("Please enter a reason !!!");
				document.getElementById("noteRequest").value = "";
				return false;
			}

			return true;
		}
	</script>
</c:if>
<c:if test="${mode=='amend'}">
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

.btn {
	margin-bottom: 5px;
	line-height: 1;
}
</style>

	<article class="content forms-page">
		<jsp:include page="../comman.jsp" />
		<form:form modelAttribute="cashing" method="POST"
			action="${pageContext.request.contextPath}/cashing/managecashing.do"
			role="form" id="myform">
			<section class="section">

				<div class="row sameheight-container">
					<div class="col-md-12">
						<div class="card card-block sameheight-item">
							<div class="col-md-12">
								<div class="section "
									style="margin-bottom: -11px; margin-top: -14px; margin-left: 15px;">
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-12">
									<fieldset>
										<legend>Cashing Information</legend>

										<fmt:formatDate var="fmtDate" value="${cashing.date}"
											pattern="dd/MM/yyyy" />




										<div class="col-sm-3 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">Date</label>
											<form:input path="date" class="form-control left"
												value="${fmtDate}" autofocus="autofocus" tabindex="1"
												readonly="true" />
											<form:errors path="date" cssClass="error" />
										</div>
										<div class="col-sm-2 col-xs-12 ">
											<label class="control-label" for="formGroupExampleInput2">Till
												No. </label>

											<form:input path="tillNo" class="form-control left"
												id="tillNo" tabindex="2" type="text" readonly="true" />
											<form:errors path="tillNo" cssClass="error" />


										</div>
										<div class="col-sm-3 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">Reference
												No. </label>
											<form:input path="refNo" class="form-control left"
												tabindex="3" readonly="true" />
											<form:errors path="refNo" cssClass="error" />
										</div>


										<form:hidden path="id" />

									</fieldset>
								</div>


							</div>
							<form:hidden id="tillStatus" path="tillStatus"
								class="form-control" />
							<form:hidden id="bankingId" path="bankingId" class="form-control" />
							<form:hidden id="branchId" path="branchId" class="form-control" />
							<form:hidden id="managername" path="manager" class="form-control" />


							<div class="col-md-12">

								<div class="col-md-6">
									<fieldset>
										<legend>Till Reading:</legend>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Z Reading</label>

												</div>
											</div>
											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12">
												<div class="form-group">
													<label>A</label>

												</div>
											</div>

											<div class="col-sm-3 col-xs-12">

												<form:input path="zReading" class="form-control"
													id="zReading" tabindex="4" onkeyup="setvalue();"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="zReading" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>No.of Voids</label>

												</div>
											</div>
											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>

											<div class="col-sm-3 col-xs-12">

												<form:input path="voids" class="form-control" id="voids"
													tabindex="5" onKeyPress="return isNumber(this, event)"
													maxlength="8" />
												<form:errors path="voids" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Refunds(Amount)</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="refunds" class="form-control" id="refunds"
													tabindex="6" onkeypress="return isNumberKey(this,event)"
													maxlength="8" />
												<form:errors path="refunds" cssClass="error" />
											</div>

										</div>

										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>Discount</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="discount" class="form-control"
													id="discount" tabindex="6"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="discount" cssClass="error" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-6 col-xs-12">
												<div class="form-group">
													<label>No. of transactions</label>

												</div>
											</div>

											<div class="col-sm-2 col-xs-12"></div>
											<div class="col-sm-1 col-xs-12"></div>
											<div class="col-sm-3 col-xs-12">
												<form:input path="sales" class="form-control" id="sales"
													tabindex="7" onKeyPress="return isNumber(this, event)"
													maxlength="8" />
												<form:errors path="sales" cssClass="error" />
											</div>

										</div>
									</fieldset>

									<fieldset>
										<legend>VAT Analysis & float</legend>

										<div class="col-md-12">
											<div class="col-sm-2 col-xs-12">

												<div class="form-group">
													<br /> <label>Exempt</label>

												</div>
											</div>
											<div class="col-sm-3 col-xs-12">
												<label class="control-label pull-right"
													for="formGroupExampleInput2">Nett Sales </label>
												<form:input path="enett" class="form-control" id="enett"
													tabindex="8" onkeypress="return isNumberKey(this,event)"
													maxlength="8" onkeyup="calculateVAT(this)" />
												<form:errors path="enett" cssClass="error" />
											</div>
											<div class="col-sm-3 col-xs-12">
												<label class="control-label pull-right"
													for="formGroupExampleInput2">VAT </label>
												<form:input path="evat" class="form-control" id="evat"
													readonly="true" tabindex="61" />
												<input type="hidden" value="${exRateVate.parameterValue}"
													id="eRateVate">
												<form:errors path="evat" cssClass="error" />
											</div>
											<div class="col-sm-4 col-xs-12">
												<label class="control-label pull-right"
													for="formGroupExampleInput2">Total </label> <input
													type="text" name="etotal" class="form-control" id="etotal"
													readonly="readonly" tabindex="62" />
											</div>


										</div>
										<div class="col-md-12">
											<div class="col-sm-2 col-xs-12">

												<div class="form-group">
													<label>Lower</label>

												</div>
											</div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="lnett" class="form-control"
													required="required" id="lnett" tabindex="9"
													onkeypress="return isNumberKey(this,event)" maxlength="8"
													onkeyup="calculateVAT(this)" />
												<form:errors path="lnett" cssClass="error" />
											</div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="lvat" class="form-control"
													required="required" id="lvat" readonly="true" tabindex="63" />
												<input type="hidden" value="${lowRateVate.parameterValue}"
													id="lRateVate" />
												<form:errors path="lvat" cssClass="error" />
											</div>
											<div class="col-sm-4 col-xs-12">
												<input type="text" name="ltotal" class="form-control"
													id="ltotal" readonly="readonly" tabindex="64" />
											</div>


										</div>
										<div class="col-md-12">
											<div class="col-sm-2 col-xs-12">

												<div class="form-group">
													<label>Standard</label>

												</div>
											</div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="snett" class="form-control"
													required="required" id="snett" tabindex="10"
													onkeypress="return isNumberKey(this,event)" maxlength="8"
													onkeyup="calculateVAT(this)" />
												<form:errors path="snett" cssClass="error" />
											</div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="svat" class="form-control"
													required="required" id="svat" readonly="true" tabindex="65" />
												<input type="hidden" value="${stdRateVate.parameterValue}"
													id="sRateVate">
												<form:errors path="svat" cssClass="error" />
											</div>
											<div class="col-sm-4 col-xs-12">
												<input type="text" name="stotal" class="form-control"
													id="stotal" readonly="readonly" tabindex="66" />
											</div>


										</div>
										<div class="col-md-12">
											<div class="col-sm-2 col-xs-12">

												<div class="form-group">
													<label>Zero</label>

												</div>
											</div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="znett" class="form-control"
													required="required" id="znett" tabindex="11"
													onkeypress="return isNumberKey(this,event)" maxlength="8"
													onkeyup="calculateVAT(this)" />
												<form:errors path="znett" cssClass="error" readonly="true" />
											</div>
											<div class="col-sm-3 col-xs-12">

												<form:input path="zvat" class="form-control"
													required="required" id="zvat" readonly="true" tabindex="67" />
												<input type="hidden" value="${zeroRateVate.parameterValue}"
													id="zRateVate">
											</div>
											<div class="col-sm-4 col-xs-12">
												<input type="text" name="ztotal" class="form-control"
													id="ztotal" readonly="readonly" tabindex="68" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-8 col-xs-12">

												<div class="form-group">
													<label>Float</label>

												</div>
											</div>

											<div class="col-sm-4 col-xs-12">
												<form:input path="floatvalue" class="form-control"
													id="float1" tabindex="12"
													onKeyPress="return isNumber(this, event)" maxlength="8"
													readonly="true" />
											</div>


										</div>
										<div class="col-md-12">
											<div class="col-sm-8 col-xs-12">

												<div class="form-group">
													<label>Private Scripts Amount as On Z-read</label>

												</div>
											</div>

											<div class="col-sm-4 col-xs-12">

												<form:input path="zReadPrivateValue" class="form-control"
													id="zReadPrivateValue" tabindex="13"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="zReadPrivateValue" cssClass="error" />
											</div>


										</div>
										<div class="col-md-12">
											<div class="col-sm-8 col-xs-12">

												<div class="form-group">
													<label>Prescription Charges as on Z-read</label>

												</div>
											</div>

											<div class="col-sm-4 col-xs-12">

												<form:input path="zReadLevy" class="form-control"
													id="zReadLevy" tabindex="14"
													onkeypress="return isNumberKey(this,event)" maxlength="8" />
												<form:errors path="zReadLevy" cssClass="error" />
											</div>


										</div>
									</fieldset>
								</div>
								<div class="col-md-6">

									<fieldset>
										<legend>Actual Takings</legend>
										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Cash</label>

												</div>
											</div>
											<div class="col-sm-1"></div>
											<div class="col-sm-1 col-xs-12">

												<a href="#" type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#cash-modal" tabindex="15"
													onfocus="">Details</a>

											</div>
											<div class="col-sm-2"></div>

											<c:choose>
												<c:when test="${not empty msg}">
													<div class="col-sm-3 col-xs-12">
														<form:hidden path="cash" class="form-control"
															required="required" id="cashmsg" readonly="true" />
														<input type="text" class="form-control"
															required="required" id="cash" readonly value="0.00" />
														<form:errors path="cash" cssClass="error" />
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-sm-3 col-xs-12">
														<form:input path="cash" class="form-control"
															required="required" id="cash" readonly="true" />
														<form:errors path="cash" cssClass="error" />
													</div>
												</c:otherwise>
											</c:choose>


										</div>

										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Cheques</label>

												</div>
											</div>
											<div class="col-sm-1"></div>
											<div class="col-sm-1 col-xs-12">
												<a href="#" type="button" class="btn btn-primary"
													tabindex="16" data-toggle="modal"
													data-target="#cheques-modal">Details</a>
											</div>
											<div class="col-sm-2"></div>
											<c:choose>
												<c:when test="${not empty msg}">




													<div class="col-sm-3 col-xs-12">
														<form:hidden path="cheques" class="form-control"
															required="required" id="chequeshmsg" readonly="true" />
														<input type="text" class="form-control"
															required="required" id="cheques" readonly value="0.00" />
														<form:errors path="cheques" cssClass="error" />
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-sm-3 col-xs-12">
														<form:input path="cheques" class="form-control"
															required="required" id="cheques" readonly="true" />
														<form:errors path="cheques" cssClass="error" />
													</div>

												</c:otherwise>
											</c:choose>
										</div>
										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Cards</label>

												</div>
											</div>
											<div class="col-sm-1"></div>
											<div class="col-sm-1 col-xs-12">
												<a href="#" type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#cards-modal"
													tabindex="17">Details</a>
											</div>
											<div class="col-sm-2"></div>
											<c:choose>
												<c:when test="${not empty msg}">
													<div class="col-sm-3 col-xs-12">
														<form:hidden path="cards" class="form-control"
															required="required" id="cardmsg" readonly="true" />
														<input type="text" class="form-control"
															required="required" id="cards" readonly value="0.00" />
														<form:errors path="cards" cssClass="error" />
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-sm-3 col-xs-12">

														<form:input path="cards" class="form-control"
															required="required" id="cards" readonly="true" />
														<form:errors path="cards" cssClass="error" />
													</div>

												</c:otherwise>
											</c:choose>


										</div>
										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Coupons/Tokens/Vouchers</label>

												</div>
											</div>
											<div class="col-sm-1"></div>
											<div class="col-sm-1 col-xs-12">
												<a href="#" type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#coupons-modal"
													tabindex="18">Details</a>
											</div>
											<div class="col-sm-2"></div>
											<c:choose>
												<c:when test="${not empty msg}">
													<div class="col-sm-3 col-xs-12">
														<form:hidden path="coupuns" class="form-control"
															required="required" id="coupunsmsg" readonly="true" />
														<input type="text" class="form-control"
															required="required" id="coupuns" readonly value="0.00" />
														<form:errors path="coupuns" cssClass="error" />
													</div>
												</c:when>
												<c:otherwise>
													<div class="col-sm-3 col-xs-12">
														<form:input path="coupuns" class="form-control"
															required="required" id="coupuns" readonly="true" />
														<form:errors path="coupuns" cssClass="error" />
													</div>
												</c:otherwise>
											</c:choose>
										</div>



										<!--  
									--------By nish kawane ----------30-08-2017
									
									
									<div class="col-md-12">
										<div class="col-sm-5 col-xs-12">
											<div class="form-group">
												<label>Other Income</label>

											</div>
										</div>
										<div class="col-sm-1"></div>
										<div class="col-sm-1 col-xs-12">
											<a href="#" type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#other-modal" tabindex="19">Details</a>
										</div>
										<div class="col-sm-2"></div>

										<div class="col-sm-3 col-xs-12">

											<input type="text" name="otherincome" class="form-control"
												id="otherincome" readonly="readonly" value="0.0" />
										</div>

									</div>-->

										<!-- -----talk with Mahnvirbhai ----- -->
										<!-- 
									<div class="col-md-12">
										<div class="col-sm-5 col-xs-12">
											<div class="form-group">
												<label>Discount</label>

											</div>
										</div>
										<div class="col-sm-1"></div>
										<div class="col-sm-1 col-xs-12">
											<a href="#" type="button" class="btn btn-primary"
												data-toggle="modal" data-target="#discount-modal" tabindex="19">Details</a>
										</div>
										<div class="col-sm-2"></div>

										<div class="col-sm-3 col-xs-12">

											<input type="text" name="discountpercent" class="form-control"
												id="discountpercent" readonly="readonly" value="0.0" />
										</div>

									</div> -->

										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Total Paid Outs </label>

												</div>
											</div>
											<div class="col-sm-1">
												<div class="form-group">
													<label> C </label>

												</div>
											</div>
											<div class="col-sm-1 col-xs-12">
												<a href="#" type="button" class="btn btn-primary"
													data-toggle="modal" data-target="#totalPaidOuts-modal"
													tabindex="20">Details</a>
											</div>
											<div class="col-sm-2"></div>
											<c:choose>
												<c:when test="${not empty msg}">
													<div class="col-sm-3 col-xs-12">

														<form:hidden path="paidOut1" class="form-control"
															required="required" id="paidOutmsg" readonly="true" />
														<input type="text" class="form-control"
															required="required" id="paidOut1" readonly value="0.00" />
														<form:errors path="paidOut1" cssClass="error" />

													</div>
												</c:when>
												<c:otherwise>
													<div class="col-sm-3 col-xs-12">

														<form:input path="paidOut1" class="form-control"
															required="required" id="paidOut1" readonly="true" />
														<form:errors path="paidOut1" cssClass="error" />

													</div>
												</c:otherwise>
											</c:choose>
										</div>
										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Total Takings</label>

												</div>
											</div>
											<div class="col-sm-1">
												<div class="form-group">
													<label> B </label>

												</div>
											</div>
											<div class="col-sm-3"></div>

											<div class="col-sm-3 col-xs-12">
												<input name="paidOut" class="form-control" id="paidOut"
													readonly="readonly" type="text" value="0.0" />

											</div>

										</div>
										<div class="col-md-12">
											<div class="col-sm-5 col-xs-12">
												<div class="form-group">
													<label>Discrepancy</label>

												</div>
											</div>
											<div class="col-sm-1">
												<div class="form-group">
													<label> B+C-A</label>

												</div>
											</div>
											<div class="col-sm-3"></div>

											<div class="col-sm-3 col-xs-12">
												<input type="text" name="discrepancy" class="form-control"
													id="discrepancy" value="0.0" readonly="readonly" />
											</div>

										</div>
										<div class="col-md-12">
											<div class="col-xs-6 col-sm-12">
												<c:if test="${not empty msg}">
													<div class="box-placeholder text-danger">
														<table>
															<tr>
																<td colspan="7">${msg}</td>

															</tr>
															<tr>
																<td>Cash</td>
																<td>--></td>
																<td><fmt:formatNumber type="number"
																		maxFractionDigits="3" minFractionDigits="2"
																		value="${cashing.cash}" /></td>
																<td>|</td>
																<td>Cheques</td>
																<td>--></td>
																<td><fmt:formatNumber type="number"
																		maxFractionDigits="3" minFractionDigits="2"
																		value="${cashing.cheques}" /></td>
															</tr>

															<tr>
																<td>Cards</td>
																<td>--></td>
																<td><fmt:formatNumber type="number"
																		maxFractionDigits="3" minFractionDigits="2"
																		value="${cashing.cards}" /></td>
																<td>|</td>
																<td>Coupons</td>
																<td>--></td>
																<td><fmt:formatNumber type="number"
																		maxFractionDigits="3" minFractionDigits="2"
																		value="${cashing.coupuns}" /></td>
															</tr>

															<tr>
																<td>Paid Outs</td>
																<td>--></td>
																<td><fmt:formatNumber type="number"
																		maxFractionDigits="3" minFractionDigits="2"
																		value="${cashing.paidOut1}" /></td>
															</tr>
														</table>

														<input type="hidden" id="msg" value="1" />
													</div>
												</c:if>
												<c:if
													test="${not empty cashing.discrepancy1 or not empty cashing.notes}">
													<div class="box-placeholder text-danger">
														<span><strong>Note:-</strong></span>
														<c:if test="${not empty cashing.discrepancy1}">

															<span>${cashing.discrepancy1}</span>
														</c:if>
														<c:if test="${not empty cashing.notes}">

															<span>${cashing.notes}</span>
														</c:if>
													</div>
												</c:if>
											</div>
										</div>

									</fieldset>
									<fieldset style="margin-top: 3px;">

										<div class="col-md-12">

											<section class="section">
												<label>Notes:</label>
												<div class="box-placeholder">
													<textarea rows="5" class="form-control" id="noteRequest"
														style="line-height: 0.5;" name="noteRequest"
														required="required"></textarea>
												</div>


												<section class="section pull-right" style="margin-top: 8px;">

													<a
														href="${pageContext.request.contextPath}/report/amendmentTill.do"
														type="button" class="btn btn-secondary" tabindex="23"><i
														class="fa fa-arrow-left"> &nbsp;Back</i></a>

													<button type="submit" name="updateTill" value="updateTill"
														class="btn btn-primary" id="updateTill" tabindex="21"
														onclick="javascript: return submitSave()">Save</button>


												</section>
											</section>
										</div>
									</fieldset>
								</div>

							</div>



						</div>
					</div>
				</div>

			</section>

			<!-- ---------------CASH - Model------------------------- -->
			<jsp:include page="./takingsCash.jsp" />
			<!-- ---------------CHEQUES - Model------------------------- -->
			<jsp:include page="./takingsCheques.jsp" />
			<!-- ---------------Cards - Model------------------------- -->
			<jsp:include page="./takingsCards.jsp" />
			<!-- ---------------Coupons - Model------------------------- -->
			<jsp:include page="./takingCoupons.jsp" />
			<!-- ---------------Other Income - Model------------------------- -->
			<jsp:include page="./otherIncome.jsp" />
			<!-- --------------- Paid Outs - Model------------------------- -->
			<jsp:include page="./paidouts.jsp" />
			<input id="modeAmd" name="modeAmd" value="modeAmd" type="hidden" />
		</form:form>
	</article>

	<script>
		$(function() {
			$("ul li").removeClass("active");
			$("#liReport").addClass("open");
			$("#liReport").addClass("active");
			$("#liReport ul").addClass("collapse in");
			$("#liamendmentTill").addClass("active");
			setvalue();
			
				$('textarea').bind('copy paste cut', function(e) {
					e.preventDefault(); 
				});
		
		});
		function submitSave() {
			var strObj = $('#noteRequest').val();
			if (strObj.trim() == '') {
				alert("Please enter a reason !!!");
				document.getElementById("noteRequest").value = "";
				return false;
			}

			return true;
		}
		function calculateVAT(str) {

			var vat = 0.0;
			var temp = str.id.split("n")[0];
			var strVat = temp + "RateVate";
			vat = parseFloat((str.value * parseFloat($('#' + strVat).val())) / 100);
			if (isNaN(vat) == true)
				vat = 0.0;
			$('#' + temp + 'vat').val((vat).toFixed(2));
			var total = parseFloat(vat) + parseFloat(str.value);
			if (isNaN(total) == true)
				total = 0.0;

			$('#' + temp + 'total').val((total).toFixed(2));

		}
	</script>

</c:if>