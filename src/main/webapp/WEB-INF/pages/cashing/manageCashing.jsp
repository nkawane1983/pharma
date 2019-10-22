<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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

.btn {
	margin-bottom: 5px;
	line-height: 1;
}
</style>

<article class="content forms-page">
	<jsp:include page="../comman.jsp" />
	<form:form modelAttribute="cashing" method="POST"
		action="${pageContext.request.contextPath}/cashing/managecashing.do"
		role="form" id="myform" name="myform">
		<section class="section">

			<div class="row sameheight-container">
				<div class="col-md-12">
					<div class="card card-block sameheight-item">
						<div class="col-md-12">
							<div class="section "
								style="margin-bottom: -11px; margin-top: -14px; margin-left: 15px;">

								<span class="text-danger"
									style="margin-right: 16px; font-size: 14px"
									id="msgRequiredNote">* Denotes Required Field </span>
							</div>
						</div>
						<div class="col-md-12">
							<div class="col-md-7">
						<c:if test="${fn:contains(msg, 'Incomplete Till')}">
						<input type="hidden" value='<c:out value="${msg}"/>' name="tillStatusIncomplete">
						</c:if>



								<fieldset>
									<legend>Cashing Information</legend>
									<c:choose>
										<c:when test="${empty cashing.date}">
											<fmt:formatDate var="fmtDate"
												value="<%=new java.util.Date()%>" pattern="dd/MM/yyyy" />
										</c:when>
										<c:otherwise>
											<fmt:formatDate var="fmtDate" value="${cashing.date}"
												pattern="dd/MM/yyyy" />
										</c:otherwise>
									</c:choose>


									<c:choose>
										<c:when
											test="${not empty cashing 
													&& not empty mode && cashing.bankingId !=0}">

											<fmt:formatDate var="fmtDate1" value="${cashing.date}"
												pattern="dd/MM/yyyy" />

											<div class="col-sm-3 col-xs-12">
												<label class="control-label" for="formGroupExampleInput2">Date</label>
												<form:input path="date" class="form-control"
													value="${ fmtDate1}" readonly="true" 
													style="text-align: left;" tabindex="1" />
												<form:errors path="date" cssClass="error" />

											</div>

											<div class="col-sm-2 col-xs-12">
												<label class="control-label" for="formGroupExampleInput2">Till
													No. </label>

												<form:input path="tillNo" class="form-control" id="tillNo"
													tabindex="2" style="text-align: left;" type="text"
													readonly="true" />
												<form:errors path="tillNo" cssClass="error" />

											</div>

											<div class="col-sm-3 col-xs-12">
												<label class="control-label" for="formGroupExampleInput2">Reference
													No. </label>
												<form:input path="refNo" class="form-control"
													required="required" id="refno" readonly="true" tabindex="3"
													style="text-align: left;" />
												<form:errors path="refNo" cssClass="error" />
											</div>
											<form:hidden path="id" />

										</c:when>
										<c:otherwise>
											<div class="col-sm-3 col-xs-12">
											
												<label class="control-label" for="formGroupExampleInput2">Date<span
													class="text-danger">*</span></label>
												<form:input path="date" class="form-control"
													value="${fmtDate}" 
													style="text-align: left;" tabindex="1" id="frm" />
												<form:errors path="date" cssClass="error" />
											</div>
											<div class="col-sm-2 col-xs-12 ">
												<label class="control-label" for="formGroupExampleInput2">Till
													No.<span class="text-danger">*</span>
												</label>
												<c:if test="${not empty msg || not empty report}">
												<form:input path="tillNo" class="form-control" id="tillNo"
													tabindex="2" style="text-align: left;" type="text"
													readonly="true" />
												<form:errors path="tillNo" cssClass="error" />
												</c:if>
												<c:if test="${empty msg &&  empty report}">
												
												<form:select path="tillNo" class="" id="tillNo"
													style="height:25.2px;width: 100%;">

													<c:forEach var="i" begin="1" end="${tillno}">
														<form:option value="${i}">${i}</form:option>
													</c:forEach>
												</form:select>
												<form:errors path="tillNo" cssClass="error" />
												</c:if>
											</div>
											<div class="col-sm-3 col-xs-12">
												<label class="control-label" for="formGroupExampleInput2">Reference
													No.<span class="text-danger">*</span>
												</label>
												<form:input path="refNo" class="form-control tooltipButton"
													tabindex="3" style="text-align: left;"
													onkeypress="viewButton1();" id="refno"
													data-original-title="Take from Zread report on Till Receipt"
													data-toggle="tooltip" data-placement="bottom" />
												<form:errors path="refNo" cssClass="error" />
											</div>

											<c:if test="${cashing.id ==0}">
												<div class="col-sm-2 col-xs-12" id="ft">
													<button type="button" class="btn btn-primary"
														onclick="alert('Manager must authorise the reporting of the faulty Till.');addNote('fault');openManagerauth()">Faulty
														Till</button>
												</div>
											</c:if>
											<form:hidden path="id" />
										</c:otherwise>
									</c:choose>

								</fieldset>
							</div>
							<c:if test="${not empty rembanking}">
								<c:forEach items="${rembanking}" var="rembanking">
								<div class="col-md-5">
									<fieldset>
										<legend>Banking</legend>


										<div class="col-sm-4 col-xs-12">
											<label class="control-label pull-right"
												for="formGroupExampleInput2"> Cash </label><input
												type="text" name="Cash1" class="form-control"
												required="required" id="Cash1" readonly="readonly"
												value="<fmt:formatNumber pattern="0.00" value="${rembanking[0]}"/>" />

										</div>
										<div class="col-sm-4 col-xs-12">
											<label class="control-label pull-right"
												for="formGroupExampleInput2"> Cheques </label><input
												type="text" name="Cheques1" class="form-control"
												required="required" id="Cheques1" readonly="readonly"
												value="<fmt:formatNumber pattern="0.00" value="${rembanking[1]}"/>" />
										</div>


										<div class="col-sm-2 col-xs-12" id="ft">

											<a
												href="${pageContext.request.contextPath}/banking/newBankingPg.do"
												type="button" class="btn btn-primary">Bank</a>

										</div>

									</fieldset>

								</div>
							</c:forEach>
							</c:if>
							<c:if test="${not empty report}">
								<div class="col-md-5 " style="padding-top: 15px;">
									<fieldset>
												
												<section class="section pull-right" id="ft">
												<a
													href="${pageContext.request.contextPath}/report/monthlyCashReportPg.do"
													type="button" class="btn btn-danger-outline " tabindex="23"><i class="fa fa-arrow-left"> &nbsp;Back</i></a>
											</section>

										

									</fieldset>

								</div>
								
							</c:if>
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
												<label>Z Reading<span class="text-danger">*</span></label>

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
												<label>No. of transactions<span class="text-danger">*</span></label>

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
												<label>Prescription Charges as on Z-read<span
													class="text-danger">*</span></label>

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
													<input type="text" class="form-control" required="required"
														id="cash" readonly value="0.00" />
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
													<input type="text" class="form-control" required="required"
														id="cheques" readonly value="0.00" />
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
												data-toggle="modal" data-target="#cards-modal" tabindex="17">Details</a>
										</div>
										<div class="col-sm-2"></div>
										<c:choose>
											<c:when test="${not empty msg}">
												<div class="col-sm-3 col-xs-12">
													<form:hidden path="cards" class="form-control"
														required="required" id="cardmsg" readonly="true" />
													<input type="text" class="form-control" required="required"
														id="cards" readonly value="0.00" />
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
													<input type="text" class="form-control" required="required"
														id="coupuns" readonly value="0.00" />
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
													<input type="text" class="form-control" required="required"
														id="paidOut1" readonly value="0.00" />
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
								<c:if test="${not empty report}">
								<legend>Audit</legend>
								</c:if>
									<div class="col-md-12">
									<c:if test="${ empty report}">
											<c:set value="pull-right" var="rightYes"/>
									</c:if>
									<c:if test="${not empty report}">
											<c:set value="" var="rightYes"/>
									</c:if>
															
									<section class="section <c:out value="${rightYes}"/>">

											<c:if test="${not empty editmode or cashing.getId()==0}">
												<a
													href="${pageContext.request.contextPath}/cashing/searchPg.do"
													type="button" class="btn btn-secondary" tabindex="23"><i class="fa fa-arrow-left"> &nbsp;Back</i></a>
											</c:if>
											<c:if test="${not empty report}">
											
											<div class="box-placeholder text-danger">
											<table>
														<tr>
															<td colspan="7">${msg}</td>

														</tr>
														<tr>
															<td>Created By</td>
															<td>--></td>
															<td><c:out value="${cashing.createdBy}"/></td>
															
														</tr>

														<tr>
															<td>Created Date</td>
															<td>--></td>
															<td><fmt:formatDate  value="${cashing.createdDt}"
																	pattern="dd/MM/yyyy HH:mm:ss" /></td>
															
														</tr>

														<tr>
															<td>Updated By</td>
															<td>--></td>
															<td><c:out value="${cashing.updatedBy}"/></td>
														</tr>
														<tr>
															<td>Updated Date</td>
															<td>--></td>
															<td><fmt:formatDate  value="${cashing.updatedDt}"
																	pattern="dd/MM/yyyy HH:mm:ss" /></td>
														</tr>
														<c:if test="${not empty cashing.manager}">
														<tr>
															<td>Manager</td>
															<td>--></td>
															<td><c:out value="${cashing.manager}"/></td>
														</tr>
														</c:if>
													</table>
											</div>
											
											</c:if>
											<c:if test="${ empty report}">
												<c:choose>
													<c:when
														test="${not empty cashing && not empty cashing.getId() && not empty mode}">
														<c:if test="${cashing.bankingId==0}">
															<c:if test="${not empty msg}">
																<button type="submit" name="edit" value="edit"
																	class="btn btn-primary" tabindex="21" id="toggle"
																	onclick="return validationCashing();javascript: return submitform()">Save</button>
															</c:if>
															<c:if test="${empty msg}">
																<button type="submit" name="edit" value="edit"
																	class="btn btn-primary" tabindex="21"
																	onclick="return validationCashing();javascript: return submitform()">Save</button>
															</c:if>
														</c:if>
														<c:if test="${cashing.bankingId!=0}">
															<button type="submit" name="update" value="update"
																id="updateButton" class="btn btn-primary" tabindex="21"
																onclick="return ValidationCashingUpdate();javascript: return submitform()">Edit</button>
														</c:if>
													</c:when>
													<c:otherwise>
														<button class="btn btn-secondary" type="reset"
															tabindex="22">Reset</button>
														<button type="submit" name="add" value="add"
															class="btn btn-primary" id="add" disabled="disabled"
															tabindex="21"
															onclick="return validationCashing();javascript: return submitform()">Add</button>
													</c:otherwise>
												</c:choose>
											</c:if>
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





		<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------Note------------------------- -->
			<!-- ------------------------------------------------------- -->

			<div class="modal fade" id="note-modal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog"
					style="max-width: 465px; margin: 125px auto;">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">

							<h6 class="modal-title" id="myModalLabel">Cashing Up</h6>
						</div>
						<div class="modal-body" style="padding: 0px; height: 90px;">
							<div class="col-md-12">
								<div class="form-group">
									<label for="username">Please enter a reason of the
										fault<span class="pull-right text-danger">*(Minimum 8 Character)</span></label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<form:input path="notes" class="form-control" id="notes1"
										placeholder="enter reason" style="text-align: left;" />
										<div id="errmsgg"></div>
								</div>
							</div>
						
						</div>
							<div class="modal-footer" style="padding-bottom: 0px;padding-top: 7px;">
							<div class="col-md-12">
								<div class="form-group pull-right">
								<c:if test="${cashing.id!=0}">
									<input type="submit" class="btn btn-primary" value="add"
										name="edit" onclick="javascript: return  submitFaulty()"/>
									</c:if>
									<c:if test="${cashing.id==0}">
									<input type="submit" class="btn btn-primary" value="OK"
										name="add" onclick="javascript: return  submitFaulty()"/>
									</c:if>
								</div>
							</div>
							</div>
					</div>
				</div>
			</div>

			<!-- ------------------------------------------------------- -->
			<!-- ---------------Discrepancy------------------------- -->
			<!-- ------------------------------------------------------- -->

			<div class="modal fade" id="Discrepancy-modal" tabindex="-1"
				role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog"
					style="max-width: 465px; margin: 125px auto;">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">

							<h6 class="modal-title" id="myModalLabel">Cashing Up</h6>
						</div>
						<div class="modal-body" style="padding: 0px; height: 90px;">
							<div class="col-md-12">
								<div class="form-group">
									<label for="discrepancy">Please enter a reason for the
										Discrepancy${msg}<span class="pull-right text-danger">*(Minimum 8 Character)</span></label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<form:input path="discrepancy1" class="form-control"
										id="discrepancy1" placeholder="enter reason"
										style="text-align: left;" />
									<div id="errmsggdis"></div>
								</div>
							</div>
							
						</div>
						<div class="modal-footer" style="padding-bottom: 0px;padding-top: 7px;">
						<div class="col-md-12">
								<div class="form-group pull-right">
									<c:if test="${cashing.id!=0}">
										<input type="submit" name="edit" value="add"
											class="btn btn-primary" tabindex="21"
											onclick="javascript: return  submitf()" />
									</c:if>
									<c:if test="${cashing.id==0}">
										<input type="submit" class="btn btn-primary" value="add"
											name="add" onclick="javascript: return  submitf()" />
									</c:if>

								</div>
							</div>
					</div>
					</div>
				</div>
			</div>

			<!-- ------------------------------------------------------- -->
			<!-- ---------------Updatecashing------------------------- -->
			<!-- ------------------------------------------------------- -->

			<div class="modal fade" id="Updatecashing-modal" tabindex="-1"
				role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog"
					style="max-width: 465px; margin: 125px auto;">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">

							<h6 class="modal-title" id="myModalLabel">Cashing Up</h6>
						</div>
						<div class="modal-body" style="padding: 0px; height: 90px;">
							<div class="col-md-12">
								<div class="form-group">
									<label for="discrepancy">Please enter a reason for
										changing the banked record<span class="pull-right text-danger">*(Minimum 8 Character)</span></label>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<form:input path="deleteComment" class="form-control"
										id="deleteComment" placeholder="enter reason"
										style="text-align: left;" />
									<div id="errmsggcom"></div>
								</div>
							</div>
						
						</div>
						<div class="modal-footer" style="padding-bottom: 0px;padding-top: 7px;">
							<div class="col-md-12">
								<div class="form-group pull-right">

									<c:if test="${cashing.bankingId!=0}">
										<input type="submit" class="btn btn-primary" value="add"
											name="update" onclick="javascript: return  submitUpdate()" />
									</c:if>
								</div>
							</div>
							</div>
					</div>
				</div>
			</div>

		</section>
		<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------Total Paid Outs - Model------------------------- -->
			<!-- -------------------------talk with Manvirnbhai----------------------------- -->
			<!-- 
<div class="modal fade" id="discount-modal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header" style="padding: 8px;">
				<a type="button" class="close" data-dismiss="modal"
					aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
				</a>
				<h4 class="modal-title" id="myModalLabel">Discount</h4>
			</div>
			<div class="modal-body" style="padding: 0px; height: 500px;">
				<div class="card">
					<div class="col-md-12">

						<div class="col-md-12">

							<div class="col-sm-8 col-xs-12">
								<label class="control-label" for="formGroupExampleInput2">
									Category </label><select class="form-control" id="dicountCategory"
									style="padding-top: 0.1rem; height: calc(2.1rem - 2px)">
									<option value="">----Select---</option>
									<option value="LOYALTY DISCOUNT">LOYALTY DISCOUNT</option>
									<option value="PGD PRO SERVICE DISCOUNT 10%">PGD PRO SERVICE DISCOUNT 10%</option>
									<option value="PGD PRO SERVICE DISCOUNT 15%">PGD PRO SERVICE DISCOUNT 15%</option>
									<option value="PGD PRO SERVICE DISCOUNT 17.5%">PGD PRO SERVICE DISCOUNT 17.5%</option>
									<option value="STAFF DISCOUNT">STAFF DISCOUNT</option>
								</select> <input type="hidden" name="diconttrindex"
									class="form-control" id="diconttrindex"
									style="text-align: left;" />
							</div>
							<div class="col-sm-4 col-xs-12">
								<label class="control-label" for="formGroupExampleInput2">
									Percentage% </label><label class="text-danger">*</label> <input type="text"
									name="otherIncomeAmt" class="form-control" id="discountper"
									onkeypress="return isNumberKey(this,event)" maxlength="8" />
							</div>
						</div>
						<div class="col-md-12">
							<div class=" col-sm-1"></div>
							<div class="col-sm-2 col-xs-12">

								<button type="button" class="btn btn-primary"
									onclick="addDiscount()">Add</button>

							</div>
							<div class=" col-sm-5"></div>
							<div class=" col-sm-2 col-xs-12 ">

								<button type="button" class=" btn btn-primary "
									onclick="delDiscount" id="deleteDiscount"
									disabled="disabled">Delete</button>

							</div>
						</div>
					</div>
					<div class="col-md-12">

						<table class="table  table-hover flip-content"
							>
							<thead>
								<tr>
									<th>Category</th>
									<th>Percentage%</th>
								</tr>
							</thead>
							<tbody id="Discountbody"></tbody>
						</table>


					</div>

				</div>
			</div>
		</div>
	</div>
</div>
-->
			<script>
				/*	
				 function addDiscount() {
				
				
				 if ($('#dicountCategory').val() != '' && $('#discountper').val() != '') {

				
				 $('#discountpercent')
				 .val(
				 (parseFloat($('#discountpercent').val()) + parseFloat($(
				 '#discountper').val())).toFixed(2));
				 $('#Discountbody').append(
				 '<tr><td>'
				 + $('#dicountCategory').val()+
				 '</td><td>'
				 + $('#discountper').val() + '</td></tr>');
				 $('#dicountCategory').val('');
				 $('#discountper').val('');
				
				 }

				 }

				 /*function delDiscount() {
				 $('#cards').val(
				 (parseFloat($('#cards').val()) - parseFloat($(
				 '#cardAmt').val())).toFixed(2));

				 document.getElementById("Cardtbody").deleteRow(
				 $('#cardtrindex').val());
				 $('#cardname').val('');
				 $('#cardAmt').val('');
				 $('#deletecard').attr("disabled", "disabled");
				 $('#cardtrindex').val('');
				 setvalue();
				 }
				 $(document).ready(function() {

				 $("#Cardtbody").delegate('tr', 'click', function() {
				 $('#cardtrindex').val($(this).index());
				 $('#cardname').val($(this).find("td:eq(0)").text());
				 $('#cardAmt').val($(this).find("td:eq(1)").text());
				 edit = $(this).find("td:eq(1)").text();
				 $('#deletecard').removeAttr("disabled");
				 });
				 });

				 function checkCard() {

				 if (parseFloat($('#cardmsg').val()) == parseFloat($(
				 '#cards').val())) {
				 return true;

				 } else {
				 alert("Please Take Cards from Zread report on Till Receipt");
				 return false;
				 }
				 }*/
			</script>

		</section>
	</form:form>
</article>

<script>
	$(document).ready(function() {
		
			$('input').bind('copy paste cut', function(e) {
				e.preventDefault(); 
			});
		
		
		if ($("#status").val() == 'add') {
			$('#baserefNo').val('');
			$('#sales').val('');
			$('#zReadLevy').val('');
		} else {
			setvalue();
		}

		$("ul li").removeClass("active");
		$("#liCashing").addClass("active");

	});
	function viewButton1() {
		if ($('#baserefNo').val() != '') {
			$('#add').removeAttr("disabled");
		} else {
			$('#add').attr("disabled", "disabled");
		}

	}
	function calculateVAT(str) {
		if ($('#msg').val() != 1) {
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
	}
</script>







<div class="modal fade" id="cashingup-modal" tabindex="-1" role="dialog"
	aria-labelledby="basicModal" aria-hidden="true">
	<div class="modal-dialog" style="width: 300px;">
		<div class="modal-content">
			<div class="modal-header" style="padding: 8px;">

				<h4 class="modal-title" id="myModalLabel">Cashing Data Dates</h4>
			</div>
			<div class="modal-body"
				style="padding: 0px; height: 500px; margin-top: -10px;">
				<div class="card">
					<div class="col-md-12"
						style="background-color: #A52A2A; color: white">
						<h6>Data must be entered for these dates.Please select a date
							to enter the data.</h6>
					</div>
					<div class="col-md-12" style="height: 438px; overflow: auto;">
						<input type="hidden" id="CashReminderPeriod"
							value="${CashReminderPeriod.parameterValue}" /> <input
							type="hidden" id="cashsize" value="${cashsize}" />
						<table class="table" id="myTable"
							style="margin-top: 5px; margin-left: -5px;">
							<thead>
								<tr>
									<th>Date</th>
									<th>Till</th>
									<th></th>
									<th>Action</th>
								</tr>
							</thead>
							<tbody id="reaminingcashBody">
								<c:forEach items="${remainingCashingupList}" var="item">

									<tr>
										<td><c:out value="${item[0]}" /></td>
										<td><c:out value="${item[1]}" /></td>
										<td>--></td>
										<c:choose>
											<c:when test="${item[2]==0 && item[3]==0 }">
												<td>
													<button class="btn btn-primary btnSelect"
														style="padding: 0.09rem 1.58rem; margin-bottom: 0px">Missing
														Till</button>
												</td>
											</c:when>
											<c:otherwise>
											<c:if test="${item[2]!=0 && item[3]==0 }">
												<td><a
													href="${pageContext.request.contextPath}/cashing/editCashingPg.do?id=${item[2]}&msg=message"><button
															type="button" name="delete" value="delete"
															class="btn btn-primary"
															style="padding: 0.09rem 0.8rem; margin-bottom: 0px">Incomplete
															Till</button></a></td>
												</c:if>
												<c:if test="${item[2]!=0 && item[3]==-1 }">
															<td><span class="btn btn-success  btn-sm" style="padding: 0.2rem 0.2rem;">Request pending</span></td>
												</c:if>
											</c:otherwise>
										</c:choose>
									</tr>

								</c:forEach>

							</tbody>
						</table>
						<input type="hidden" name="reaminingcashdate" class="form-control"
							id="reaminingcashdate" style="text-align: left;" />
					</div>



				</div>
			</div>
		</div>
	</div>
</div>
<input type="hidden" name="listSize" class="form-control"
							id="listSize" value="${listSize}" />

<script>
	$(document).ready(function() {

		if ($('#msg').val() == 1) {
			$('#msgRequiredNote').html("Note:- Don't Change");
			$("#msgRequiredNote").removeClass("text-danger");
			$('#msgRequiredNote').css("background-color", "#b0eaef");
			var  all = document.getElementById("myform").getElementsByTagName("input");
			for (var i = 0;i < all.length; i++) {
				if(all[i].id!=''){
				var n = document.getElementById(all[i].id).tabIndex;
				if ((n >= 1 && n <= 14) || (n >= 61 && n <= 68)) {
					
					$('#' + all[i].id).attr('readonly', 'readonly');
					$('#' + all[i].id).css("background-color", "#b0eaef");
				}
				if(n==1)
					document.getElementById("frm").removeAttribute("id");
				
				
			}
						
			}
			if ($('#zReading').val() == 0 && $('#discrepancy').val() == 0 && $('#paidOut').val()==0){
			
				document.getElementById("toggle").innerText="Faulty Till";
		
				}
			
			
			//document.getElementById("frm").removeAttribute("id");
			
		}

		if (($("#status").val() != 'add' && $("#bankingId").val() != 0)) {
			$('input').attr('readonly', 'readonly');
			$('button').attr("disabled", "disabled");
			$('#updateButton').removeAttr("disabled");
		}
		var a = parseInt($('#CashReminderPeriod').val());
		var b = parseInt($('#cashsize').val());

		if ($("#status").val() == 'add') {
			$("ul li").removeClass("active");
			$("#liCashing").addClass("active");
			if($('#listSize').val()>0){
				$('#cashingup-modal').modal({
					backdrop : 'static',
					keyboard : false
				});
			}
		}
		if ($('#reportSide').val() != '') {
			$("ul li").removeClass("active");
			$("#liReport").addClass("open");
			$("#liReport").addClass("active");
			$("#liReport ul").addClass("collapse in");
			$("#liMonthlyCash").addClass("active");
		} else {
			$("ul li").removeClass("active");
			$("#liCashing").addClass("active");
		}
		$("#myTable").on('click', '.btnSelect', function() {
			// get the current row
			var currentRow = $(this).closest("tr");

			var col1 = currentRow.find("td:eq(0)").text(); // get current row 1st TD value
			var col2 = currentRow.find("td:eq(1)").text(); // get current row 2nd TD
			// get current row 3rd TD
			// alert(col1); 

			$('#cashingup-modal').modal('hide');
			$('#frm').val(col1);
			$('#tillNo').val(col2);
			$('#refno').focus();

		});
		$('.tooltipButton').tooltip();
	});
	function addremaining() {

		//alert($(this).closest("tr").find("td:eq(0)").text())
		if ($('#reaminingcashdate').val() != '') {
			$('#cashingup-modal').modal('hide');
			$('#frm').val($('#reaminingcashdate').val());
			$('#refno').focus();
		}
	}
	function addNote(str) {
		if ('Discrepancy' != str) {
			$('#both').val(str);
			$('#sales').val(0);
			$('#float1').val(0);
			$('#zReadLevy').val(0);
			$('#refno').val("faulty Till");
		} else {
			$('#both').val(str);
		}

	}
	function validationCashing() {
		if ($('#msg').val() != 1) {
		if ($('#frm').val() != '') {
			var today = new Date().getTime(), idate = $('#frm').val()
					.split("/");
			idate = new Date(idate[2], idate[1] - 1, idate[0]).getTime();
			if ((today - idate) < 0) {
				alert('Please select valid date .');
				return false;
			}
		}
	}
		
		if ($('#msg').val() == 1) {
			if (checkCash() == false)
				return false;
			if (checkCheques() == false)
				return false;
			if (checkCard() == false)
				return false;
			if (checkCoupons() == false)
				return false;
			if (checkPaidOut() == false)
				return false;
			if ($('#discrepancy').val() > 4.99 || $('#discrepancy').val() < -4.99) {
				alert("Discrepancy exceeds the set limit of 4.99. Manager's authorisation is required.");
				addNote('Discrepancy');
				openManagerauth();

				return false;
			}
			
			if ($('#zReading').val() == 0 && $('#discrepancy').val() == 0 && $('#paidOut').val()==0){
				alert('Manager must authorise the reporting of the faulty Till.');
				$('#both').val('fault');
				openManagerauth();
				return false;
				}
		
		}else{

		if ($('#zReading').val() == 0.0) {
			alert('Z Reading Value must be Entered.');

			return false;
		}
		if ($('#sales').val() == '') {
			alert('Number of transactions must be entered even if it is Zero.');

			return false;
		}
		/*if ($('#zReadPrivateValue').val() == '') {
			alert('Private Prescription value must be entered even if it is zero.');

			return false;
		}*/
		if ($('#zReadLevy').val() == '') {
			alert('Prescription Charges as per Z-Read Value must be entered even if it is zero.');

			return false;
		}
		if ($('#discrepancy').val() > 4.99 || $('#discrepancy').val() < -4.99) {
			alert("Discrepancy exceeds the set limit of 4.99. Manager's authorisation is required.");
			addNote('Discrepancy');
			openManagerauth();

			return false;
		}
		}
		//submitform();
		return true;
	}
	function ValidationCashingUpdate() {
		//addNote('Updatecashing');
		//;;openManagerauth();
		//return false;
		if (confirm("This cash has been banked.Are you sure you want to edit the Cash Record?") == true) {
			if (confirm("This will delete the Banking Record.Cash will need to be banked again.Are you sure want to proceed?") == true) {
				addNote('Updatecashing');
				openManagerauth();
				$('#updateButton').removeAttr("disabled");
				$('input').removeAttr('readonly');
				$('button').removeAttr("disabled");
				return false;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	function submitform() {

		if (document.myform.onsubmit()) {//this check triggers the validations
			document.myform.submit();
		}

	}
	function submitFaulty() {
		var strObj=document.getElementById("notes1").value;
		if (strObj.trim() == '') {
			document.getElementById("errmsgg").innerHTML = "Please enter a reason !!!";
			document.getElementById("errmsgg").style.color = "red";
			document.getElementById("notes1").value="";
			return false;
		}
		if (strObj.trim().length <8) {
			document.getElementById("errmsgg").innerHTML = "Please enter a minimum 8 character !!!";
			document.getElementById("errmsgg").style.color = "red";
			document.getElementById("notes1").value="";
			return false;
		}

		return true;
	}
	function submitf() {
		var strObj=document.getElementById("discrepancy1").value;
		if (strObj.trim() == '') {
			document.getElementById("errmsggdis").innerHTML = "Please enter a reason !!!";
			document.getElementById("errmsggdis").style.color = "red";
			document.getElementById("discrepancy1").value="";
			return false;
		}
		if (strObj.trim().length <8) {
			document.getElementById("errmsggdis").innerHTML = "Please enter a minimum 8 character !!!";
			document.getElementById("errmsggdis").style.color = "red";
			document.getElementById("discrepancy1").value="";
			return false;
		}
		return true;
	}
	function submitUpdate() {
		var strObj=document.getElementById("deleteComment").value;
		if (strObj.trim() == '') {
			document.getElementById("errmsggcom").innerHTML = "Please enter a reason !!!";
			document.getElementById("errmsggcom").style.color = "red";
			document.getElementById("deleteComment").value="";
			return false;
		}
		if (strObj.trim().length <8) {
			document.getElementById("errmsggcom").innerHTML = "Please enter a minimum 8 character !!!";
			document.getElementById("errmsggcom").style.color = "red";
			document.getElementById("deleteComment").value="";
			return false;
		}
		return true;
	}
</script>
<style type="text/css">
.highlighted {
	background: CornflowerBlue;
}
</style>
<input type="hidden" id="status" value="${status}" />
<input type="hidden" id="both" value="" />
	<input type="hidden" id="reportSide" value="${report}" />