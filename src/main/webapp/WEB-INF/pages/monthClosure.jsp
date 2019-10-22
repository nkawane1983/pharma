<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>

<title>Month closure</title>


<meta charset="utf-8" />
<meta name="description" content="" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />


<spring:url value="/resources/css/vendor.min.css" var="vendorCss" />
<link rel="stylesheet" href="${vendorCss}">

<spring:url value="/resources/css/app-blue.min.css" var="appCss" />
<link rel="stylesheet" href="${appCss}">


<spring:url value="/resources/css/pharmadmin.css" var="pharmadminCss" />
<link rel="stylesheet" href="${pharmadminCss}">


<!-- Font Awesome -->
<spring:url value="/resources/font-awesome-4.1.0/css/font-awesome.min.css" var="fontAwesomeCss" />
<link rel="stylesheet" href="${fontAwesomeCss}">


<spring:url value="/resources/js/jquery.min.js" var="jqueryJs" />
<script type="text/javascript" src="${jqueryJs}"></script>

<spring:url value="/resources/js/vendor_app.min.js" var="vendorAppJs" />
<script type="text/javascript" src="${vendorAppJs}"></script>


<spring:url value="/resources/css/displaytag.css" var="displaytagCss" />
<link href="${displaytagCss}" rel="stylesheet" />

<spring:url value="/resources/js/jsUtility.js" var="jsUtility" />
	<script src="${jsUtility}"></script>

<spring:url value="/resources" var="image" />
<link href="${images}/favicon.ico" rel="icon" type="image/x-icon" />
<link href="${images}/favicon.ico" rel="shortcut icon" type="image/x-icon" />



<style>
input {
	text-align: right;
	/*padding-top: 0.1rem*/
}

.form-control {
	padding: 0.1rem;
	margin-bottom: 0.2rem;
}

.subtitle-block {
	padding-bottom: 8px;
	margin-bottom: 4px;
}

.table th, .table td {
	padding: 0.1rem
}

select {
	height: 25.2px;
	width: 100%;
}

table {
	margin-bottom: 0rem;
}

fieldset {
	padding: 4px;
}

.btn {
	padding-top: 0.1rem;
	padding-bottom: 0.1rem
}

#all {
	height: 32px;
	background-color: #16169b;
	color: white;
	padding: 0.29rem 4.6rem;
	margin-top: 43px;
}
</style>

</head>

<body>
	<div class="content forms-page">
		<div class="section">
			<div class="card">

				<div class="">

					<div class="col-md-12">
						<div class="col-md-12">
							<div class="col-md-10 col-xs-10">
								<label class="control-label" for="formGroupExampleInput2"
									style="font-size: 20px;"><fmt:parseDate
										value="${endmonth}" dateStyle="long" pattern="M"
										var="monthDate"></fmt:parseDate><strong
									style="margin-left: 15px;">End of month confirmation
										for</strong></label> <input class="form" type="text"
									value="<fmt:formatDate value="${monthDate}" pattern="MMMM"/>"
									style="text-align: center; width: 158px; font-size: 20px; background-color: goldenrod;"
									readonly="readonly" />
							</div>
							<div class="col-md-2 col-xs-2 pull-right">
								<a href="${pageContext.request.contextPath}/dashboard.do"
									type="button" class="btn btn-danger-outline btnsmall"
									style="height: 28px; margin-left: 138px"><i
									class="fa fa-times"></i></a>
							</div>
						</div>
						<div class="col-md-10">
							<div class="col-md-12">
								<div class="col-sm-4 col-xs-12">

									<fieldset>
										<legend>Missing Cash Data</legend>
										<table class="table">
											<thead>
												<tr>
													<th style="width: 90px;">Day</th>
													<th style="width: 160px;">Date</th>
													<th style="width: 66.3833px;">Till</th>
												</tr>
											</thead>
										</table>
										<c:choose>
											<c:when test="${not empty remainingCashingupList}">
												<div
													style="height: 132px; overflow: auto; background-color: #d46464; color: white;">
													<form
														action="${pageContext.request.contextPath}/cashing/newcashingPg.do"
														method="post" id="myform">
														<table class="table" id="cashmiss" >
															<tbody>
																<c:forEach items="${remainingCashingupList}"
																	var="remainingCashingupList">
																	<fmt:parseDate pattern="dd/MM/yyyy"
																		value="${remainingCashingupList[0]}" var="cashDate" />
																	<fmt:formatDate value="${cashDate}"
																		pattern="dd/MM/yyyy" var="cashdate" />
																	<tr title="Double Click on the Date to enter Data." ondblclick="addRowHandlers('cashmiss',this)">


																		<td style="width: 90px;"><fmt:formatDate
																				value="${cashDate}" pattern="E" /></td>
																		<td style="width: 160px;">${cashdate}</td>
																		<td>${remainingCashingupList[1]}</td>
																		<td style="display: none;">${remainingCashingupList[2]}</td>


																	</tr>
																</c:forEach>
															</tbody>
														</table>
														<input type="hidden" value="" name="date1" id="date1" />
														<input type="hidden" value="" name="till" id="till" />
													</form>
												</div>
											</c:when>
											<c:otherwise>
												<div style="height: 132px; overflow: auto; color: white;">
													<div id="all">
														<strong>No Missing Data</strong>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</fieldset>
								</div>
								<div class="col-sm-4 col-xs-12">

									<fieldset>
										<legend>Un-Banked Cash</legend>
										<table class="table">
											<thead>
												<tr>
													<th>Day</th>
													<th>Date</th>
													<th>Till</th>
													<th>Amount</th>
												</tr>
											</thead>
										</table>
										<c:choose>
											<c:when test="${not empty unbanking}">
												<div id="unbank1"
													style="height: 132px; overflow: auto; background-color: #d46464; color: white;">
													<form
														action="${pageContext.request.contextPath}/banking/newBankingPg.do"
														method="post" id="myform1">
														<table class="table" id="unbank">
															<tbody>
																<c:forEach items="${unbanking}" var="unbanking">
																	<fmt:formatDate var="datebank"
																		value="${unbanking.date}" pattern="dd/MM/yyyy" />
																	<tr title="Double Click on the Date to enter Data." ondblclick="addRowHandlers('unbank',this)">
																		<td><fmt:formatDate value="${unbanking.date}"
																				pattern="E" /></td>
																		<td><c:out value="${datebank}" /></td>
																		<td><c:out value="${unbanking.tillNo}" /></td>

																		<td><c:out
																				value="${unbanking.cash + unbanking.cheques}" /></td>
																	</tr>
																</c:forEach>

															</tbody>
														</table>
														<input type="hidden" value="" name="monthclosemsg" id="monthclosemsg" />
													</form>
												</div>
											</c:when>
											<c:otherwise>
												<div style="height: 132px; overflow: auto; color: white;">
													<div id="all">
														<strong>No Missing Data</strong>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</fieldset>
								</div>
								<div class="col-sm-4 col-xs-12">


									<fieldset>
										<legend>Missing NHS Data</legend>
										<table class="table">
											<thead>
												<tr>
													<th style="width: 90px;">Day</th>
													<th>Date</th>

												</tr>
											</thead>
										</table>
										<c:choose>
											<c:when test="${not empty remainingNHSList}">
												<div id="nhsmiss1"
													style="height: 132px; overflow: auto; background-color: #d46464; color: white;">
													<form
														action="${pageContext.request.contextPath}/nhs/newNHSPg.do"
														method="post" id="myform2">
														<table class="table" id="nhsmiss">
															<tbody>
																<c:forEach items="${remainingNHSList}"
																	var="remainingNHSList">
																	<fmt:parseDate pattern="dd/MM/yyyy"
																		value="${remainingNHSList}" var="nhsDate" />
																	<fmt:formatDate value="${nhsDate}" pattern="dd/MM/yyyy"
																		var="nhsdate" />
																	<tr title="Double Click on the Date to enter Data." ondblclick="addRowHandlers('nhsmiss',this)">
																		<td style="width: 90px;"><fmt:formatDate
																				value="${nhsDate}" pattern="E" /></td>
																		<td>${nhsdate}</td>

																	</tr>
																</c:forEach>
															</tbody>
														</table>
														<input type="hidden" value="" name="date1" id="date11" />
													</form>
												</div>
											</c:when>
											<c:otherwise>
												<div style="height: 132px; overflow: auto; color: white;">
													<div id="all">
														<strong>No Missing Data</strong>
													</div>
												</div>
											</c:otherwise>
										</c:choose>
									</fieldset>
								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-8">
									<div class="col-md-12">
										<div class="col-sm-6 col-xs-12"
											style="margin-top: 15px; margin-left: -31px;">

											<fieldset style="width: 314px;">
												<legend>Cash Summary</legend>
												<c:set var="totalCash" value="${0}" />
												<c:set var="totalCheques" value="${0}" />

												<c:set var="totalCards" value="${0}" />
												<c:set var="totalCoupuns" value="${0}" />
												<c:set var="totalPaidout" value="${0}" />
												<c:set var="totalBankCash" value="${0}" />
												<c:set var="totalBankCheques" value="${0}" />
												<c:set var="totalZreading" value="${0}" />
												<c:set var="totalNettSales" value="${0}" />
												<c:set var="totalNOCustomers" value="${0}" />
												<c:forEach items="${cashingList}" var="cashingList">
													<c:set var="totalCash"
														value="${totalCash + cashingList.cash}" />
													<c:set var="totalCheques"
														value="${totalCheques+cashingList.cheques}" />
													<c:set var="totalCards"
														value="${totalCards+cashingList.cards}" />
													<c:set var="totalCoupuns"
														value="${totalCoupuns+cashingList.coupuns}" />
													<c:set var="totalPaidout"
														value="${totalPaidout+cashingList.paidOut1}" />
													<c:set var="totalZreading"
														value="${totalZreading+cashingList.zReading}" />
													<c:set var="totalNettSales"
														value="${totalNettSales+(cashingList.enett+cashingList.lnett+cashingList.snett+cashingList.znett)}" />
													<c:set var="totalNOCustomers"
														value="${totalNOCustomers+cashingList.sales}" />
													<c:if test="${cashingList.bankingId !=0}">
														<c:set var="totalBankCash"
															value="${totalBankCash + cashingList.cash}" />
														<c:set var="totalBankCheques"
															value="${totalBankCheques + cashingList.cheques}" />
													</c:if>
												</c:forEach>
												<c:set var="total"
													value="${totalCash+totalCheques+totalCards+totalCoupuns+totalPaidout}" />
												<table>
													<tbody>
														<tr>
															<td style="width: 210px;"><label
																class="control-label" for="formGroupExampleInput2">Cash</label></td>
															<td style="width: 120px;"><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalCash}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Cheques</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalCheques}" />"
																type="text" class="form-control" readonly="readonly" /></td>

														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Sub Total</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalCash+totalCheques}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Cards</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalCards}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Cpns/Tokens/Vouchers</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalCoupuns}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														</tr>

														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Expenses</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPaidout}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Total</label></td>

															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${total}" />"
																type="text" class="form-control" readonly="readonly" /></td>

														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Discrepancy</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${total-totalZreading}" />"
																type="text" class="form-control" readonly="readonly" /></td>

														</tr>
													</tbody>
												</table>
											</fieldset>
										</div>

										<div class="col-sm-6 col-xs-12"
											style="margin-top: 15px; margin-left: 31px;">

											<fieldset style="width: 314px;">
												<legend>Banking Summary</legend>
												<table>
													<tbody>
														<tr>
															<td style="width: 210px;"><label
																class="control-label" for="formGroupExampleInput2">Cash</label></td>
															<td style="width: 120px;"><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalBankCash}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Cheques</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalBankCheques}" />"
																type="text" class="form-control" readonly="readonly" /></td>

														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Total</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalBankCash+totalBankCheques}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Unbanked for the
																	current period</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${(totalCash+totalCheques)-(totalBankCash+totalBankCheques)}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														</tr>
													</tbody>
												</table>
											</fieldset>

											<fieldset style="width: 314px;">
												<legend>Sales Summary</legend>
												<table>
													<tbody>
														<tr>
															<td style="width: 210px;"><label
																class="control-label" for="formGroupExampleInput2">Nett
																	Sales</label></td>
															<td style="width: 120px;"><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalNettSales}" />"
																type="text" class="form-control" readonly="readonly" /></td>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">No. of Customers</label></td>
															<td><input
																value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalNOCustomers}" />"
																type="text" class="form-control" readonly="readonly" /></td>

														</tr>
														<tr>
															<td><label class="control-label"
																for="formGroupExampleInput2">Average Sale</label></td>

															<c:choose>
																<c:when test="${totalNOCustomers==0}">
																	<td><input value="0" type="text"
																		class="form-control" readonly="readonly" /></td>
																</c:when>
																<c:otherwise>
																	<td><fmt:formatNumber type="number"
																			maxFractionDigits="3"
																			value="${totalNettSales/totalNOCustomers}" var="avgs" />

																		<input value="${avgs}" type="text"
																		class="form-control" readonly="readonly" /></td>
																</c:otherwise>
															</c:choose>
														</tr>
													</tbody>
												</table>
											</fieldset>
										</div>
									</div>
									<div class="col-md-12">


										<fieldset style="width: 314px; margin-left: -18px">
											<legend>Care Home Summary</legend>
											<div class="col-sm-12 col-xs-12">
												<table class="table">
													<thead>
														<tr>
															<th style="width: 300px;">Home</th>
															<th style="width: 100px;">Forms</th>
															<th style="width: 100px;">Items</th>
														</tr>
													</thead>
												</table>
												<div style="height: 75px; overflow: auto; width: 620px;">
													<table >
														<tbody>
														<c:if test="${not empty carehomeList}">
														<c:forEach items="${carehomeList}" var="carehomeList">
															<tr>
																<td style="width: 355px;">${carehomeList.carehomeName}</td>
																<td style="width: 100px; text-align: right;">${carehomeList.total7form}</td>
																<td style="width: 100px; text-align: right;">${carehomeList.total7items}</td>
															</tr>
															</c:forEach>
															</c:if>
														</tbody>
													</table>
												</div>
											</div>
<!-- 											<div class="col-sm-2 col-xs-12"> -->
<!-- 												<label class="control-label" for="formGroupExampleInput2">Initials</label> -->
<!-- 												<input type="text" class="form-control" id="Initials" style="text-align: left;"/> -->
<!-- 											</div> -->
										</fieldset>
									</div>
								</div>
								<div class="col-md-4">

									<fieldset style="width: 314px;">
										<legend>Scripts Summary</legend>


										<c:set var="totalPaidForm" value="${0}" />
										<c:set var="totalPaidItem" value="${0}" />
										<c:set var="totalNoChargeItem" value="${0}" />
										<c:set var="totalExemptForm" value="${0}" />
										<c:set var="totalExemptItem" value="${0}" />
										<c:set var="totalPillForm" value="${0}" />
										<c:set var="totalpillItem" value="${0}" />
										<c:set var="totalMDSForm" value="${0}" />
										<c:set var="totalMDSItem" value="${0}" />
										<c:set var="totalPrivateForm" value="${0}" />
										<c:set var="totalPrivateValue" value="${0}" />
										<c:set var="totalOtherForm" value="${0}" />
										<c:set var="totalOtherValue" value="${0}" />
										<c:forEach items="${nhsList}" var="nhsList">
											<c:set var="totalPaidForm"
												value="${totalPaidForm+(nhsList.paidForm+nhsList.paidFormOld)}" />
											<c:set var="totalPaidItem"
												value="${totalPaidItem+(nhsList.paidItem+nhsList.paidItemOld)}" />
											<c:set var="totalNoChargeItem"
												value="${totalNoChargeItem+(nhsList.noChargeItem+noChargeItemOld)}" />
											<c:set var="totalExemptForm"
												value="${totalExemptForm+nhsList.exemptForm}" />
											<c:set var="totalExemptItem"
												value="${totalExemptItem+nhsList.exemptItem}" />
											<c:set var="totalPillForm"
												value="${totalPillForm+nhsList.contraceptiveForm}" />
											<c:set var="totalpillItem"
												value="${totalpillItem+nhsList.contraceptiveItem}" />
										<c:set var="totalPrivateForm" value="${totalPrivateForm+nhsList.privatePrescriptionItems}" />
										<c:set var="totalPrivateValue" value="${totalPrivateValue+nhsList.privatePrescriptionValue}" />
										<c:set var="totalOtherForm" value="${totalOtherForm+nhsList.otherPrescriptionItems}" />
										<c:set var="totalOtherValue" value="${totalOtherValue+nhsList.otherPrescriptionValue}" />
										</c:forEach>
										<c:forEach items="${carehomeASList}" var="carehomeASList">
											<c:set var="totalMDSForm"
												value="${totalMDSForm+(carehomeASList.comm7form+carehomeASList.comm28form+carehomeASList.form7day+carehomeASList.form28day)}" />
											<c:set var="totalMDSItem"
												value="${totalMDSItem+(carehomeASList.comm7items+carehomeASList.comm28items+carehomeASList.items7day+carehomeASList.items28day)}" />
										</c:forEach>
										<table class="table">
											<tbody>
												<tr>
													<td></td>
													<td>Forms</td>
													<td>Items</td>
													<td>No.Chg</td>
												</tr>
												<tr>
													<td><a class="btn btn-primary btnsmall" style="width: 86.41px;"
														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Paid</a></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPaidForm}" />"
														type="text" class="form-control" /></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPaidItem}" />"
														type="text" class="form-control" /></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalNoChargeItem}" />"
														type="text" class="form-control" /></td>
												</tr>
												<tr>
													<td><a class="btn btn-primary btnsmall" style="width: 86.41px;"
														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Exempt</a></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalExemptForm+totalPillForm}" />"
														type="text" class="form-control" /></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalExemptItem+totalpillItem}" />"
														type="text" class="form-control" /></td>
													<td></td>
												</tr>
<!-- 												<tr> -->
<!-- 													<td><a class="btn btn-primary" style="width: 86.41px;" -->
<%-- 														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Pill</a></td> --%>
<%-- 													<td><input value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${}" />" type="text" --%>
<!-- 														class="form-control" /></td> -->
<%-- 													<td><input value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${}" />" type="text" --%>
<!-- 														class="form-control" /></td> -->
<!-- 													<td></td> -->
<!-- 												</tr> -->
												<tr>
													<td><a class="btn btn-primary btnsmall" style="width: 86.41px;"
														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Total</a></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPaidForm+totalExemptForm+totalPillForm}" />"
														type="text" class="form-control" /></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPaidItem+totalExemptItem+totalpillItem}" />"
														type="text" class="form-control" /></td>
													<td><input readonly="readonly"
														value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalNoChargeItem}" />"
														type="text" class="form-control" /></td>
												</tr>
<!-- 												<tr> -->
<!-- 													<td><a class="btn btn-primary" style="width: 86.41px;" -->
<%-- 														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Resubs</a></td> --%>
<!-- 													<td><input value="0.0" type="text" -->
<!-- 														class="form-control" /></td> -->
<!-- 													<td><input value="0.0" type="text" -->
<!-- 														class="form-control" /></td> -->
<!-- 													<td></td> -->
<!-- 												</tr> -->
												<tr>
													<td><a class="btn btn-primary btnsmall" style="width: 86.41px;"
														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">MDS</a></td>
													<td><input readonly="readonly" value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalMDSForm}" />"
														class="form-control" /></td>
													<td><input readonly="readonly" value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalMDSItem}" />"
														class="form-control" /></td>
													<td></td>
												</tr>
											</tbody>
										</table>
										<table>
											<tbody>
												<tr>
													<td style="width: 210px;"></td>
													<td style="width: 120px;">No. of Patients</td>
												</tr>
												<tr>
													<td>Substance Misuse</td>
													<td><input type="text" class="form-control" 
														id="SubstanceMisuse" onkeypress="return isNumber(this, event)" onkeyup="transferData()"/> </td>
												</tr>
												<tr>
													<td>Repeat Dispensing</td>
													<td><input type="text" class="form-control"
														id="RepeatDispensing" onkeypress="return isNumber(this, event)" onkeyup="transferData()"/></td>
												</tr>
												<tr>
													<td>Care Homes</td>
													<td><input type="text" class="form-control"
														id="CareHomes" onkeypress="return isNumber(this, event)" onkeyup="transferData()"/></td>
												</tr>
												<tr>
													<td>Community MDS</td>
													<td><input type="text" class="form-control"
														id="CommunityMDS" onkeypress="return isNumber(this, event)" onkeyup="transferData()"/></td>
												</tr>
												<tr>
													<td>Initials</td>
												
													<td><input type="text" class="form-control" id="Initials" style="text-align: left;"/></td>
												</tr>
											</tbody>
										</table>
										<table class="table">
											<tbody>
												<tr>
													<td></td>
													<td>Forms</td>
													<td>value</td>

												</tr>
												<tr>
													<td><a class="btn btn-primary btnsmall" style="width: 86.41px;"
														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Private</a></td>
													<td><input readonly="readonly" value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalPrivateForm}" />" type="text"
														class="form-control" /></td>
													<td><input readonly="readonly" value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="3" value="${totalPrivateValue}" />" type="text"
														class="form-control" /></td>

												</tr>
<!-- 												<tr> -->
<!-- 													<td><a class="btn btn-primary" style="width: 86.41px;" -->
<%-- 														href="${pageContext.request.contextPath}/nhs/newNHSPg.do">Other</a></td> --%>
<%-- 													<td><input value="<fmt:formatNumber type="number" maxFractionDigits="3" value="${totalOtherForm}" />" type="text" --%>
<!-- 														class="form-control" /></td> -->
<%-- 													<td><input value="<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="3" value="${totalOtherValue}" />" type="text" --%>
<!-- 														class="form-control" /></td> -->

<!-- 												</tr> -->
											</tbody>
										</table>
										<br>
									</fieldset>
								</div>
							</div>


						</div>

						<div class="col-md-2">


							<fieldset>
								<legend>Confirm?</legend>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">NHS</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">

									<select id="NHS" onchange="closebuttonVisible()">
										<option value="select"></option>
										<option value="No">No</option>
										<option value="Yes">Yes</option>
									</select>
								</div>
								<br /> <br />
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">Private</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">

									<select id="Private" onchange="closebuttonVisible()">
										<option></option>
										<option>No</option>
										<option>Yes</option>
									</select>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">Pro.Serv.</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">

									<select id="ProServ" onchange="closebuttonVisible()">
										<option></option>
										<option>No</option>
										<option>Yes</option>
									</select>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">Coll
										& Delv</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">

									<select id="CollDelv" onchange="closebuttonVisible()">
										<option></option>
										<option>No</option>
										<option>Yes</option>
									</select>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">Rpt.
										Disp.</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">

									<select id="RptDisp" onchange="closebuttonVisible()">
										<option></option>
										<option>No</option>
										<option>Yes</option>
									</select>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">Sub.
										Mis.</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">

									<select id="SubMis" onchange="closebuttonVisible()">
										<option></option>
										<option>No</option>
										<option>Yes</option>
									</select>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 6px;">
									<label class="control-label" for="formGroupExampleInput2">NCSO</label>
								</div>
								<div class="col-md-12 col-xs-12" style="margin-bottom: 27px;">

									<select id="NCSO" onchange="closebuttonVisible()">
										<option></option>
										<option>No</option>
										<option>Yes</option>
									</select>
								</div>
								<form
									action="${pageContext.request.contextPath}/manageMonthPg.do"
									onsubmit="return validate()" method="post" id="monthId">
									<input type="hidden" name="nextmonth"
										value="<c:out value="${endmonth+1}"/>" />
										<input type="hidden" name="SubstanceMisuseP" id="SubstanceMisuseP"
										value="0" />
										<input type="hidden" name="RepeatDispensingP" id="RepeatDispensingP"
										value="0"/>
										<input type="hidden" name="CareHomesP" id="CareHomesP"
										value="0" />
										<input type="hidden" name="CommunityMDSP" id="CommunityMDSP"
										value="0"/>
										<input type="hidden" name="nhsId" id="nhsIdObj"
										value="${nhsId}"/>
										<input type="hidden" name="careHomeId" id="careHomeIdObj"
										value="${carehomeId}"/>
												
									<div class="col-md-12 col-xs-12" style="margin-bottom: 10px;">
										<input type="submit" class="btn btn-primary"
											value="Close Period" id="closebtn"
											style="height: 74px; width: 100%; margin-top: 82px;"
											disabled="disabled" />

									</div>
								</form>
								<br>
							</fieldset>
						</div>

					</div>

				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			closebuttonVisible();
		});
		
		function addRowHandlers(tableId,trIndex){
			
			if(tableId=='cashmiss'){
			document.getElementById("date1").value = trIndex.cells[1].innerHTML;
			document.getElementById("till").value = trIndex.cells[2].innerHTML;
			if (trIndex.cells[3].innerHTML != 0) {
					window.location.href = "${pageContext.request.contextPath}/cashing/editCashingPg.do?id="
							+ trIndex.cells[3].innerHTML + "&msg=message";
				} else {
					var form = $('#'+tableId).closest('form')[0];
					document.getElementById(form.id).submit();
				}
			}
			if(tableId=='nhsmiss'){
				document.getElementById("date11").value = trIndex.cells[1].innerHTML;
				var form = $('#'+tableId).closest('form')[0];
				document.getElementById(form.id).submit();
			}
			if(tableId=='unbank'){
				document.getElementById("monthclosemsg").value = "monthcloseUnbank";
				var form = $('#'+tableId).closest('form')[0];
				document.getElementById(form.id).submit();
			}
		}
		function closebuttonVisible() {
			var nhsmiss = $('#nhsmiss tbody tr').length;
			var cashmiss = $('#cashmiss tbody tr').length;
			var unbank = $('#unbank tbody tr').length;
			var nhs = $('#NHS').val();
			var Private = $('#Private').val();
			var ProServ = $('#ProServ').val();
			var CollDelv = $('#CollDelv').val();
			var RptDisp = $('#RptDisp').val();
			var SubMis = $('#SubMis').val();
			var NCSO = $('#NCSO').val();
			if (cashmiss <= 0 && nhsmiss <= 0 && unbank <= 0 && nhs == 'Yes'
					&& Private == 'Yes' && ProServ == 'Yes'
					&& CollDelv == 'Yes' && RptDisp == 'Yes' && NCSO == 'Yes') {
				$('#closebtn').removeAttr("disabled");
			} else {

				$('#closebtn').attr("disabled", "disabled");
			}

		}
		function transferData()
		{
			var SubstanceMisuse = $('#SubstanceMisuse').val();
			var CommunityMDS = $('#CommunityMDS').val();
			var RepeatDispensing = $('#RepeatDispensing').val();
			var CareHomes = $('#CareHomes').val();
			document.getElementById("SubstanceMisuseP").value=SubstanceMisuse;
			document.getElementById("RepeatDispensingP").value=RepeatDispensing;
			document.getElementById("CareHomesP").value=CareHomes;
			document.getElementById("CommunityMDSP").value=CommunityMDS;
		}
		function validate() {
			var SubstanceMisuse = $('#SubstanceMisuse').val();
			var CommunityMDS = $('#CommunityMDS').val();
			var RepeatDispensing = $('#RepeatDispensing').val();
			var CareHomes = $('#CareHomes').val();
			var Initials = $('#Initials').val();
			var boltrue = false;
			document.getElementById("SubstanceMisuseP").value=SubstanceMisuse;
			document.getElementById("RepeatDispensingP").value=RepeatDispensing;
			document.getElementById("CareHomesP").value=CareHomes;
			document.getElementById("CommunityMDSP").value=CommunityMDS;
			if (SubstanceMisuse == '') {
				alert("Need to enter no. of patients for Substance Misuse before closing this period.");
				return false;
			} else {
				boltrue = true;
			}
			if (RepeatDispensing == '') {
				alert("Need to enter no. of patients for Repeat Dispensing before closing this period.");
				return false;
			} else {
				boltrue = true;
			}
			if (CareHomes == '') {
				alert("Need to enter no. of patients for Care Home before closing this period.");
				return false;
			} else {
				boltrue = true;
			}
			if (CommunityMDS == '') {
				alert("Need to enter no. of patients for Community MDS before closing this period.");
				return false;
			} else {
				boltrue = true;
			}
// 			if (SubstanceMisuse == 0) {
// 				alert("No.of patients can't be 0 as Substance Misuse Items exists.");
// 				return false;
// 			} else {
// 				boltrue = true;
// 			}
// 			if (CareHomes == 0) {
// 				alert("No.of patients can't be 0 as Care Home Items exists.");
// 				return false;
// 			} else {
// 				boltrue = true;
// 			}
// 			if (CommunityMDS == 0) {
// 				alert("No.of patients can't be 0 as Community MDS Items exists.");
// 				return false;
// 			}
			if (Initials == '') {
				alert("Have you checked the care Home data and make sure you put your initials.");
				return false;
			} else {
				boltrue = true;
			}

		
			
			if (boltrue) {
				if (confirm("Are you sure you want to close this period?") == true) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
		</script>
