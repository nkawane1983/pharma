
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style>
input {
	text-align: right;
	padding: 0.3rem;
}

.form-control {
	padding: 0.1rem;
}

.hidden {
	display: none;
}
</style>

<article class="content dashboard-page">
	<jsp:include page="../comman.jsp" />
	<form:form modelAttribute="banking" method="POST"
		action="${pageContext.request.contextPath}/banking/managebanking.do"
		role="form" id="myform">
		<section class="section">
			<div class="row sameheight-container">

				<div class="col-md-12">


					<div class="card card-block">
						<div class="col-md-12">
							<div class="section "
								style="margin-bottom: 6px; margin-top: -14px; margin-left: -18px;">
								<span class="text-danger"
									style="margin-right: 16px; font-size: 13px">* Note:-
									Carry forward cash range must be <i class="fa fa-gbp"
									aria-hidden="true"></i> ${cfamountLimitMin} to <i
									class="fa fa-gbp" aria-hidden="true"></i> ${cfamountLimitMAX}.
								</span>
							</div>
						</div>
						<div class="subtitle-block">
							<h3 class="subtitle">Banking Details</h3>
						</div>
						<div class="col-md-12">
							<div class="col-sm-4 col-xs-12">
								<table class="table" style="margin-bottom: 0.0rem;">
									<thead>
										<tr>
											<th style="width: 10.3%;"><c:if test="${empty mode}">
													<input type="checkbox" name="select" onclick="selectall()" />
												</c:if></th>
											<th style="width: 15%;"><label class="control-label"
												for="formGroupExampleInput2">Day </label></th>

											<th style="width: 30%;"><label class="control-label"
												for="formGroupExampleInput2">Date </label></th>

											<th style="width: 10%;"><label class="control-label"
												for="formGroupExampleInput2">Till </label></th>
											<th style="width: 35%;"><label class="control-label"
												for="formGroupExampleInput2">Amount </label></th>
										</tr>
									</thead>
								</table>

								<c:set var="totalzread" value="${0.0}" />
								<c:set var="totaltaking" value="${0.0}" />

								<div
									style="height: 460px; overflow: auto; border: 1px solid #52BCD3 !important;">
									<table class="table" style="text-align: center;" id="allbank">
										<tbody>

											<c:forEach items="${unbanking}" var="unbanking">
												<c:if test="${not empty mode}">
													<c:set var="totalzread"
														value="${totalzread + unbanking.zReading}" />
													<c:set var="totaltaking"
														value="${totaltaking + unbanking.cash + unbanking.cheques + unbanking.cards + unbanking.coupuns + unbanking.paidOut1}" />
												</c:if>
												<tr>

													<td style="width: 10%; text-align: center;"><c:choose>
															<c:when test="${ empty mode}">
																<input type="checkbox" name="bankingbox"
																	value="${unbanking.id}"
																	id="<fmt:formatDate value="${unbanking.date}" pattern="MM/dd/yyyy" />"
																	onclick="selectbankingbox(this)">
															</c:when>

														</c:choose></td>
													<td style="width: 15%;"><fmt:formatDate
															value="${unbanking.date}" pattern="E" /></td>
													<td style="width: 30.5%;"><fmt:formatDate
															value="${unbanking.date}" pattern="dd/MM/yyyy" /></td>
													<td style="width: 10%; text-align: center;">${unbanking.tillNo}</td>
													<td style="width: 34.5%; text-align: right;"><fmt:formatNumber
															type="number" groupingUsed="false" maxFractionDigits="3"
															minFractionDigits="2" value="${unbanking.cash}" /></td>
													<td style="display: none;"><fmt:formatNumber
															type="number" groupingUsed="false" maxFractionDigits="3"
															minFractionDigits="2" value="${unbanking.zReading}" /></td>
													<td style="display: none;"><fmt:formatNumber
															type="number" groupingUsed="false" maxFractionDigits="3"
															minFractionDigits="2"
															value="${unbanking.cash + unbanking.cheques + unbanking.cards + unbanking.coupuns + unbanking.paidOut1}" /></td>
												</tr>
												<input type="hidden" id="desc" value="" />


											</c:forEach>


										</tbody>
									</table>
								</div>

								<div></div>
								<input type="hidden" id="cashid" name="cashid" value="${cashid}" />

							</div>
							<div id="cashbody">

								<input type="hidden" id="taking" value="${totaltaking }" /> <input
									type="hidden" id="zread" value="${totalzread}" />
								<div class="col-sm-4 col-xs-12 table-flip-scroll">
									<table class="table" id="cashtable"
										style="border: 1px solid #52BCD3 !important;">
										<thead>
											<tr>

												<th><label class="control-label"
													for="formGroupExampleInput2">Denomination </label></th>
												<th></th>
												<th><label class="control-label"
													for="formGroupExampleInput2">Quantity </label></th>
												<th></th>
												<th><label class="control-label"
													for="formGroupExampleInput2">Amount </label></th>
											</tr>
										</thead>



										<tbody>
											<c:forEach items="${cash}" var="cash" varStatus="count">
												<tr>
													<td><label>${cash.denominations}</label></td>
													<td>&nbsp;x&nbsp;</td>
													<td><input name="enett1" class="form-control"
														required="required" id="qut_${cash.multiplier}"
														onkeyup="cashCalculate(this)" value="${cash.quantity}"
														readonly="readonly"></td>
													<td>&nbsp;=&nbsp;</td>
													<td><input name="text[]" class="form-control"
														required="required" id="amt_${cash.multiplier}"
														readonly="readonly"
														value="<fmt:formatNumber
															type="number"   groupingUsed = "false" maxFractionDigits = "2"
															value="${cash.amount}" />"></td>
												</tr>

											</c:forEach>



										</tbody>

										<tfoot>
											<tr>
												<td colspan="4"><label><strong> Cash</strong></label></td>

												<td><input name=cashobj class="form-control"
													required="required" id="cashobj" tabindex="10" value="0.0"
													readonly="readonly" />
											</tr>
											<tr>
												<td colspan="4"><label><strong>Total
															Discrepancy</strong></label></td>

												<td><input id="discr" name="discr" class="form-control"
													readonly="readonly" type="text" value="0.0"></td>
											</tr>
											<tr>
												<td colspan="4"><label><strong>Total
															outstanging Cash</strong></label></td>

												<td><input id="outsatanding" name="outsatanding"
													class="form-control" type="text" readonly="true"
													value="${outstangingAmount.outstanging}"
													readonly="readonly"></td>
											</tr>
											<tr>
												<td colspan="4"><label><strong>Total
															carry/forward Cash</strong></label></td>

												<td><input id="cfamount" name="cfamount"
													class="form-control" type="text"
													onkeypress="return isNumberKey(this,event)"
													value="${outstangingAmount.amount}" readonly="readonly"></td>
											</tr>
										</tfoot>
									</table>

								</div>

								<div class="col-sm-4 col-xs-12 table-flip-scroll">
									<div class="col-xs-6 col-sm-12" style="margin-bottom: 1.0rem;">
										<table class="table">
											<thead>
												<tr>

													<th colspan="2"><label class="control-label"
														for="formGroupExampleInput2">Cheques </label></th>


												</tr>
											</thead>
										</table>
										<div
											style="height: 130px; overflow: auto; border: 1px solid #52BCD3 !important;">
											<table class="table" id="tblCheques">
												<tbody>
													<c:forEach items="${unbanking}" var="unbanking">
														<c:forEach items="${unbanking.takingscheques}"
															var="unbankingcheques">
															<tr>
																<td><label class="control-label"
																	for="formGroupExampleInput2">${unbankingcheques.accountNo}</label></td>
																<td><label class="control-label"
																	for="formGroupExampleInput2">${unbankingcheques.cname}</label></td>
																<td style="text-align: right; width: 80px;"><label
																	class="control-label" for="formGroupExampleInput2"><fmt:formatNumber
																			type="number" groupingUsed="false"
																			maxFractionDigits="2"
																			value="${unbankingcheques.amount}" /> </label>&nbsp;&nbsp;</td>


															</tr>
														</c:forEach>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div>
											<table class="table">
												<tfoot>
													<tr>

														<td
															style="width: 150px; vertical-align: bottom; border: 1px solid #52BCD3 !important;"><label
															class="control-label" for="formGroupExampleInput2"><strong>Total
																	Cheques bank</strong></label></td>
														<td><form:input path="cheques" class="form-control"
																id="totalCheques" readonly="true" value="0.0" /></td>


													</tr>

												</tfoot>
											</table>
										</div>
									</div>

									<div class="col-xs-6 col-sm-12 table-flip-scroll"
										style="margin-bottom: 1.0rem;">
										<table class="table" style="margin-bottom: 0.0rem;">
											<thead>
												<tr>

													<th colspan="2"><label class="control-label"
														for="formGroupExampleInput2">Other Income </label></th>


												</tr>
											</thead>
										</table>
										<div
											style="height: 130px; overflow: auto; border: 1px solid #52BCD3 !important;">
											<table class="table" id="tblPaidOut">
												<tbody>
													<c:forEach items="${unbanking}" var="unbanking">
														<c:forEach items="${unbanking.paidouts}"
															var="unbankingpaidouts">
															<tr>
																<td><label class="control-label"
																	for="formGroupExampleInput2"><c:forEach
																			items="${paidoutlist}" var="paidoutlist">
																			<c:if
																				test="${unbankingpaidouts.ctype==paidoutlist.id}">${paidoutlist.categoryName}</c:if>
																		</c:forEach></label></td>
																<td style="text-align: right; width: 80px;"><label
																	class="control-label" for="formGroupExampleInput2"><fmt:formatNumber
																			type="number" groupingUsed="false"
																			maxFractionDigits="2"
																			value="${unbankingpaidouts.amount}" /></label></td>
															</tr>
														</c:forEach>
													</c:forEach>
												</tbody>
											</table>
										</div>
										<div>
											<table class="table">
												<tfoot>
													<tr>

														<td style="width: 150px; vertical-align: bottom;"><label
															class="control-label" for="formGroupExampleInput2"><strong>Total
																	Other Income </strong> </label></td>
														<td><form:input path="miscCash" class="form-control"
																required="required" id="miscCash" readonly="true" /></td>


													</tr>
												</tfoot>
											</table>
										</div>
									</div>
									<div class="col-sm-12 col-xs-12">
										<label class="control-label" for="formGroupExampleInput2">Comment:</label>

									</div>
									<div class="col-sm-12 col-xs-12">

										<form:textarea path='notes' id='notes' readonly="true" />
									</div>
								</div>

							</div>
							<form:hidden path="id" id="bankid" />
						</div>
						<div class="col-md-12">

							<c:choose>
								<c:when
									test="${not empty banking && not empty banking.getId() && not empty mode}">
									<div class="col-sm-2 col-xs-12">

										<label class="control-label" for="formGroupExampleInput2">Banking
											Date </label>
										<form:input path="bankDate" class="form-control" type="text"
											required="required" id="enett" tabindex="8"
											style="padding: 0.5rem; text-align: left;"
											autofocus="autofocus" readonly="true" />

									</div>
									<div class="col-sm-2 col-xs-12">
										<label class="control-label" for="formGroupExampleInput2">Reference
											No.</label>
										<form:input path="bankingRef" class="form-control"
											id="bankingRef" tabindex="9"
											style="padding: 0.5rem; text-align: left;" readonly="true" />
									</div>
								</c:when>
								<c:otherwise>
									<div class="col-sm-2 col-xs-12">
										<fmt:formatDate var="fmtDate"
											value="<%=new java.util.Date()%>" pattern="dd/MM/yyyy" />
										<label class="control-label" for="formGroupExampleInput2">Banking
											Date </label>
										<form:input path="bankDate" class="form-control" type="text"
											required="required" id="enett" tabindex="8"
											value="${fmtDate}" style="padding: 0.5rem; text-align: left;"
											autofocus="autofocus" readonly="true" />

									</div>
									<div class="col-sm-2 col-xs-12">
										<label class="control-label" for="formGroupExampleInput2">Reference
											No.</label><label class="text-danger">*</label>
										<form:input path="bankingRef" class="form-control"
											id="bankingRef" tabindex="9"
											style="padding: 0.5rem; text-align: left;" readonly="true" />
									</div>
								</c:otherwise>
							</c:choose>
							<div class="col-sm-2 col-xs-12" >
								<label class="control-label pull-right" for="formGroupExampleInput2">Total
									Banking </label> <input name="totalbanking" class="form-control"
									required="required" id="totalbanking" tabindex="10"
									style="padding: 0.5rem;" value="0.0" readonly="readonly" />
							</div>
							<c:if test="${ empty mode }">
								<div class="col-sm-2 col-xs-12" >
									<label class="control-label pull-right" for="formGroupExampleInput2">Total
										Cash banked</label>
									<form:input path="cash" class="form-control" id="cash"
										value="0.0" style="padding: 0.5rem;"
										onkeypress="return isNumberKey(this,event);"
										onkeydown="carryForwrdcash() " />
								</div>
							</c:if>
							<c:if test="${not empty mode }">
								<div class="col-sm-2 col-xs-12" >
									<label class="control-label pull-right" for="formGroupExampleInput2">Total
										Cash banked</label>
									<form:input path="cash" class="form-control" id="cash"
										value="0.0" style="padding: 0.5rem;"
										onkeypress="return isNumberKey(this,event);"
										onkeydown="carryForwrdcash() " readonly="true" />
								</div>
							</c:if>


							<div class="col-sm-4 col-xs-12">

								<section class="section pull-right" style="margin-top: -1px;">
									<c:choose>
										<c:when test="${not empty report}">

											<section class="section pull-right">
												<br /> <a
													href="${pageContext.request.contextPath}/report/bankingReportPg.do"
													type="button" class="btn btn-secondary">Discard</a> <input
													type="hidden" id="reportid" value="${report}">
											</section>
										</c:when>
										<c:otherwise>

											<section class="section pull-right">

												<br />
												<c:if test="${empty mode}">
													<button type="button" name="editcash" value="editcash"
														id="editcash" class="btn btn-secondary" tabindex="11"
														onclick="ediableTextBox('edit')">
														<i class="fa fa-pencil-square-o"></i>EditCash
													</button>
													<button type="button" name="savecash" value="savecash"
														id="savecash" class="btn btn-secondary hidden"
														tabindex="11" onclick="ediableTextBox('save')">
														<i class="fa fa-save"></i>SaveCash
													</button>
												</c:if>
												<a
													href="${pageContext.request.contextPath}/banking/searchPg.do"
													type="button" class="btn btn-secondary">Discard</a><input
													type="hidden" id="reportid" value="${mode}">
												<c:choose>
													<c:when test="${not empty mode}">

														<!-- 														<button type="submit" name="edit" value="edit" -->
														<!-- 															class="btn btn-primary" tabindex="12">Edit</button> -->
													</c:when>
													<c:otherwise>

														<button type="submit" name="add" value="add" id="add"
															class="btn btn-primary" tabindex="11"
															onclick="return validationBanking();javascript:return submitform()">Add</button>
													</c:otherwise>
												</c:choose>
											</section>
										</c:otherwise>
									</c:choose>


								</section>
							</div>
						</div>
					</div>

				</div>
			</div>
		</section>
		<input type="hidden" id="BankingdisLimit" value="${BankingdisLimit}" />
		<input type="hidden" id="cfamountLimitMin" value="${cfamountLimitMin}" />
		<input type="hidden" id="cfamountLimitMAX" value="${cfamountLimitMAX}" />

		<input type="hidden" id="mode" value="${mode}" />
		<form:hidden id="managername" path="manager" class="form-control" />
		<!-- ------------------------------------------------------- -->
		<!-- ---------------Discrepancy------------------------- -->
		<!-- ------------------------------------------------------- -->

		<div class="modal fade" id="Discrepancy-modal" tabindex="-1"
			role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog"
				style="max-width: 465px; margin: 125px auto;">
				<div class="modal-content">
					<div class="modal-header" style="padding: 8px;">

						<h6 class="modal-title" id="myModalLabel">Banking Notes</h6>
					</div>
					<div class="modal-body" style="padding: 0px; height: 138px;">
						<div class="col-md-12">
							<div class="form-group">
								<label for="discrepancy">Please enter a reason for the
									Discrepancy</label>
							</div>
						</div>
						<div class="col-md-12">
							<div class="form-group">
								<form:input path="notes" class="form-control" id="discrepancy1"
									placeholder="enter reason" style="text-align: left;" />
								<div id="errmsgg"></div>
							</div>
						</div>

						<div class="col-md-12">
							<div class="form-group pull-right">
								<button type="submit" name="add" value="add"
									class="btn btn-primary" onclick="javascript: return  submitf()"
									tabindex="13">Add</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form:form>
</article>

<script>
	var cal = 0;

	function cashCalculate(cash) {

		var str = cash.id;
		var str1 = str.split('_');
		var val1 = 0;
		var val2 = 0;

		if (str1[0] == 'qut') {

			val1 = parseFloat(str1[1]) * parseFloat(cash.value);

			if (isNaN(val1))
				val1 = 0.0;

			document.getElementById('amt_' + str1[1]).value = val1.toFixed(2);
			;

		}

		if (str1[0] == 'qutx') {
			val2 = parseFloat(str1[1]) * (parseFloat(cash.value) / 100);

			if (isNaN(val2))
				val2 = 0.0;

			//document.getElementById('amtx_' + str1[1]).value = val2;

		}
		var itemCount = document.getElementsByName("text[]");
		var total = 0;
		for (var i = 0; i < itemCount.length; i++)

		{
			var sm = parseFloat(itemCount[i].value);
			if (isNaN(sm))
				sm = 0.0;
			total = sm + parseFloat(total);
		}
		var totaltaking = 0.0;
		var dirc = 0.0;
		totaltaking = document.getElementById('taking').value;
		if(totaltaking >=total)
			{
			document.getElementById('discr').value=dirc.toFixed(2);
			dirc=parseFloat(totaltaking)-parseFloat(total);
			document.getElementById('cfamount').value=dirc.toFixed(2);
			}
		else{
			document.getElementById('discr').value=0.0;
			dirc=parseFloat(totaltaking)-parseFloat(total);
			document.getElementById('discr').value=dirc.toFixed(2);
		}
			
	
		document.getElementById('cashobj').value = total.toFixed(2);
		setallvalue();
	}
	function ediableTextBox(str) {
		var itemCount = document.getElementsByName("enett1");
		if (str == 'edit') {
			for (var i = 0; i < itemCount.length; i++) {
				document.getElementById(itemCount[i].id).removeAttribute(
						"readonly");

			}

			document.getElementById("editcash").setAttribute("class", "hidden");
			document.getElementById("savecash").removeAttribute("class");
			document.getElementById("savecash").setAttribute("class",
					"btn btn-secondary");
		} else {
			for (var i = 0; i < itemCount.length; i++) {
				document.getElementById(itemCount[i].id).setAttribute(
						"readonly", "readonly");

			}

			document.getElementById("savecash").setAttribute("class", "hidden");
			document.getElementById("editcash").removeAttribute("class");
			document.getElementById("editcash").setAttribute("class",
					"btn btn-secondary");
		}
	}

	function selectall1() {
		if ($("#cashid").val() == 'all') {
			$('input[name=select]').prop('checked', true);
			selectall();
		}
	}
	function selectdeall() {
		if ($("#cashid").val() == '') {
			$('input[name=select]').prop('checked', false);
			selectall();
		}
	}
	function selectall() {
		var selected = [];

		if ($('input[name=select]').prop("checked") == true) {
			$('input[name=bankingbox]').prop('checked', true);
			$("#cashid").val("all");

			ajaxby();
			setallvalue()
			return false;
		} else {
			$('input[name=bankingbox]').prop('checked', false);
			$("#cashid").val("deall");

			ajaxby();
			setallvalue();
		}
	}

	function selectbankingbox(bank) {
		var selected = [];
		if ($(bank).prop("checked") == true) {
			$('#allbank :input[name=bankingbox]').each(function() {
				if (this.id == bank.id) {
					$(this).prop('checked', true);
				}
			});
		} else {
			$('#allbank :input[name=bankingbox]').each(function() {
				if (this.id == bank.id) {
					$(this).prop('checked', false);
				}
			});
		}

		$('#allbank :checked').each(function() {
			selected.push(this.value);
		});

		$("#cashid").val(selected);
		//var temp=$("#cashid").val().split(',');
		if ($("#cashid").val() == '')
			selectdeall();
		else {
			var len = $('#allbank :input[name=bankingbox]').length;

			var len1 = $("#cashid").val().split(',').length;
			if (len == len1) {
				$('input[name=select]').prop('checked', true);
			} else {

				$('input[name=select]').prop('checked', false);

			}

			ajaxby();

		}
		//setDiscrepancy();

	}
	function setDiscrepancy() {
		var disc;
		$('#allbank :checked')
				.each(
						function() {

							var cell = $(this).closest('td');
							var cellIndex = cell[0].cellIndex
							var row = cell.closest('tr');
							var rowIndex = row[0].rowIndex;
							var takVal = $('#allbank tr').eq(rowIndex).find(
									'td').eq(cellIndex - 1).text();
							var zreadVal = $('#allbank tr').eq(rowIndex).find(
									'td').eq(cellIndex - 2).text();
							if (isNaN(disc))
								disc = 0.0;
							disc = (parseFloat(disc) + (parseFloat(zreadVal) - parseFloat(takVal)))
									.toFixed(2);
						});
		if (isNaN(disc))
			disc = 0.0;

		$('#discr').val(parseFloat(disc).toFixed(2));
	}
	function setallvalue() {

		var texts = document.getElementsByName("text[]");
		var table = document.getElementById("tblCheques");
		var tablePaidOut = document.getElementById("tblPaidOut");
		var rows = table.getElementsByTagName("tr");
		var rowspaidout = tablePaidOut.getElementsByTagName("tr")
		var totalcash = 0.0;
		var totalchequ = 0.0;
		var totalpaid = 0.0;
		var totalbank = 0.0;
		var outsatanding = 0.0;
		for (var i = 0; i < texts.length; i++) {

			totalcash = parseFloat(totalcash) + parseFloat(texts[i].value);

		}
		for (var i = 0; i < rows.length; i++) {
			totalchequ = parseFloat(totalchequ)
					+ parseFloat($(rows[i]).find("td:eq(2)").text());
		}
		for (var i = 0; i < rowspaidout.length; i++) {
			totalpaid = parseFloat(totalpaid)
					+ parseFloat($(rowspaidout[i]).find("td:eq(1)").text());
		}
		outsatanding = parseFloat($('#outsatanding').val());

		$('#cashobj').val(totalcash.toFixed(2));
		$('#cash').val((totalcash + outsatanding).toFixed(2));
		var cfamountLimitMAX = 0.0;
		var cfamountLimitMin = 0.0;
		var cfamount = 0.0;
		cfamountLimitMAX = $('#cfamountLimitMAX').val();
		cfamountLimitMin = $('#cfamountLimitMin').val();
		if ($('#mode').val() == '') {

			cfamount = (totalcash + outsatanding) % 5.0;
			$('#cfamount').val(cfamount.toFixed(2));
		} else {
			cfamount = $('#cfamount').val();
		}

		$('#cash').val(((totalcash + outsatanding) - cfamount).toFixed(2));
		$('#totalCheques').val(totalchequ.toFixed(2));
		$('#miscCash').val(totalpaid.toFixed(2));
		$('#totalbanking').val(
				(parseFloat($('#cash').val()) + totalchequ).toFixed(2));

		
		
		if (isNaN($('#discr').val()))
			$('#discr').val('0.0')
		if ($('#discr').val() < 0.0) {
			$('#discr').css("color", "red");
		}
		if ($('#discr').val() > 0.0) {
			$('#discr').css("color", "#52BCD3");
		}

	}
	function validationBanking() {

		if ($('#discr').val() > $('#BankingdisLimit').val()
				|| $('#discr').val() < -$('#BankingdisLimit').val()) {
			alert("Discrepancy exceeds the set limit of 4.99. Manager's authorisation is required.");
			addNote('Discrepancy');
			openManagerauth();
			return false;
		}
		if ($('#bankid').val() != 0) {
			alert("The BankingID for this transaction is "
					+ $('#bankingRef').val()
					+ " .Please write this ID on the Giro Slip.");
			return true;
		}
		return true;
	}
	function addNote(str) {
		$('#both').val(str);
	}
	function submitform() {
		if ($('#bankid').val() != 0) {
			alert("The BankingID for this transaction is "
					+ $('#bankingRef').val()
					+ " .Please write this ID on the Giro Slip.");
			return true;
		}

	}
	function submitf() {
		if ($('#discrepancy1').val() != '') {
			if ($('#bankid').val() != 0) {
				alert("The BankingID for this transaction is "
						+ $('#bankingRef').val()
						+ " .Please write this ID on the Giro Slip.");
				return true;
			}
		} else {
			document.getElementById("errmsgg").innerHTML = "Please enter a reason !!!";
			document.getElementById("errmsgg").style.color = "red";
			//alert("Please Enter a reason for the discrepancy !!!");
			return false;
		}
	}

	function carryForwrd() {
		var totalcashobj = 0.0;
		var totalchequ = 0.0;
		var totalpaid = 0.0;
		var totalcashbank = 0.0;
		var outsatanding = 0.0;
		var cfamount = 0.0;
		var totalbank = 0.0;
		totalcashobj = parseFloat($('#cashobj').val());
		totalcashbank = parseFloat($('#cash').val());
		outsatanding = parseFloat($('#outsatanding').val());
		cfamount = parseFloat($('#cfamount').val());

		totalcashbank = (totalcashobj + outsatanding) - cfamount;
		if (isNaN(totalcashbank))
			totalcashbank = 0.0;
		$('#cash').val(totalcashbank.toFixed(2));

		totalbank = (parseFloat(totalcashbank) + parseFloat(totalchequ));
		if (isNaN(totalbank))
			totalbank = 0.0;
		$('#totalbanking').val(totalbank.toFixed(2));

	}
	function carryForwrdcash() {
		var totalcashobj = 0.0;
		var totalchequ = 0.0;
		var totalpaid = 0.0;
		var totalcashbank = 0.0;
		var outsatanding = 0.0;
		var cfamount = 0.0;
		var totalbank = 0.0;
		totalcashobj = parseFloat($('#cashobj').val());
		totalcashbank = parseFloat($('#cash').val());
		outsatanding = parseFloat($('#outsatanding').val());
		cfamount = parseFloat($('#cfamount').val());

		cfamount = (totalcashobj + outsatanding) - totalcashbank;
		if (isNaN(cfamount))
			cfamount = 0.0;
		$('#cfamount').val(cfamount.toFixed(2));

		totalbank = (parseFloat(totalcashbank) + parseFloat($('#totalCheques')
				.val()));
		if (isNaN(totalbank))
			totalbank = 0.0;
		//alert(totalbank)
		$('#totalbanking').val(totalbank.toFixed(2));

	}
</script>
<script type="text/javascript">
	$(function() {
		$("ul li").removeClass("active");
		$("#liBanking").addClass("active");
		selectall1();
		if ($("#reportid").val() == 'update') {
			setallvalue();
			$('#totalbanking').attr('readonly', 'readonly');
			$('#bankingRef').attr('readonly', 'readonly');
			$('input[name=select]').attr('hidden', 'true');
			$('input[name=bankingbox]').attr('hidden', 'true');
		}

	});
	function ajaxby() {

		var cashid = $("#cashid").val();

		$
				.ajax({
					url : '${pageContext.request.contextPath}/banking/getbankingList.do',
					data : {
						cashid : cashid
					},
					async : false,
					success : function(data) {
						$("#cashbody").html(data);
					},
					complete : function(data) {
						setallvalue();
					}
				});

	}
</script>
<input type="hidden" id="both" value="" />