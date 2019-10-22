<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------CASH - Model------------------------- -->
			<!-- ------------------------------------------------------- -->
			<div class="modal fade" id="cash-modal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
							</a>
							<h4 class="modal-title" id="myModalLabel">Cash</h4>
						</div>
						<div class="modal-body" style="padding: 0px;">
							<div class="card-block">

								<div class="col-md-12">
									<div class="col-sm-3 col-xs-12">

										<div class="form-group">
											<label class="control-label">Denomination </label>

										</div>
									</div>
									<div class="col-sm-1 col-xs-12">

										<div class="form-group">
											<label class="control-label"> </label>

										</div>
									</div>
									<div class="col-sm-3 col-xs-12">
										<label class="control-label"> Quantity</label>

									</div>
									<div class="col-sm-1 col-xs-12">

										<div class="form-group">
											<label class="control-label"> </label>

										</div>
									</div>
									<div class="col-sm-4 col-xs-12">
										<label class="control-label">Amount </label>

									</div>



								</div>

								<c:forEach items="${cash}" var="cash" varStatus="count">
									<div class="col-md-12">
										<div class="col-sm-3 col-xs-12">

											<div class="form-group">
												<label>${cash.denominations}</label>

											</div>
										</div>
										<div class="col-sm-1 col-xs-12">

											<div class="form-group">
												<label class="control-label"> </label><label>x</label>

											</div>
										</div>
										<c:choose>
											<c:when
												test="${not empty cashing && not empty cashing.getId() && not empty mode}">
												<div class="col-sm-3 col-xs-12">
													<form:input path="takingscash[${count.index}].quantity"
														class="form-control" id="${cash.multiplier}"
														onkeyup="cashCalculate(this)" tabindex="26"
														onkeypress="return isNumber(this, event)" maxlength="5"
														value="${cash.quantity}" />

													<form:hidden
														path="takingscash[${count.index}].denominationId"
														value="${cash.id}" />
													<form:hidden path="takingscash[${count.index}].id" />
												</div>
											</c:when>
											<c:otherwise>
												<div class="col-sm-3 col-xs-12">
													<form:input path="takingscash[${count.index}].quantity"
														class="form-control" id="${cash.multiplier}"
														onkeyup="cashCalculate(this)" tabindex="0"
														onkeypress="return isNumber(this, event)" maxlength="5"
														value="" />

													<form:hidden
														path="takingscash[${count.index}].denominationId"
														value="${cash.id}" />

												</div>
											</c:otherwise>
										</c:choose>




										<div class="col-sm-1 col-xs-12">

											<div class="form-group">
												<label>=</label>

											</div>
										</div>
										<div class="col-sm-4 col-xs-12">

											<input name="text[]" class="form-control"
												id="a<c:out value="${cash.multiplier}"/>"
												readonly="readonly"
												value="<fmt:formatNumber type="number" 
										minFractionDigits="2" maxFractionDigits="3" value="${cash.amount}" />"
												tabindex="-25" />

										</div>
									</div>
								</c:forEach>



							</div>
						</div>


					</div>
				</div>
			</div>
			<script>
				var cal = 0;
				function cashCalculate(cash) {

					var str = cash.id;

					var val1 = 0;
					val1 = parseFloat(str) * parseFloat(cash.value);

					if (isNaN(val1))
						val1 = 0.0;

					document.getElementById('a' + str).value = val1.toFixed(2);

					var itemCount = document.getElementsByName("text[]");
					var total = 0;
					for (var i = 0; i < itemCount.length; i++)

					{
						var sm = parseFloat(itemCount[i].value);
						if (isNaN(sm))
							sm = 0.0;
						total = sm + parseFloat(total);
					}

					document.getElementById('cash').value = total.toFixed(2);

					setvalue();

				}
				function setvalue() {
					$('#paidOut')
							.val(
									(parseFloat($('#cash').val())
											+ parseFloat($('#cheques').val())
											+ parseFloat($('#cards').val())
											+ parseFloat($('#coupuns').val()) + parseFloat($(
											'#paidOut1').val())).toFixed(2));
					$('#etotal').val(
							(parseFloat($('#enett').val()) + parseFloat($(
									'#evat').val())).toFixed(2));
					$('#ltotal').val(
							(parseFloat($('#lnett').val()) + parseFloat($(
									'#lvat').val())).toFixed(2));
					$('#stotal').val(
							(parseFloat($('#snett').val()) + parseFloat($(
									'#svat').val())).toFixed(2));
					$('#ztotal').val(
							(parseFloat($('#znett').val()) + parseFloat($(
									'#zvat').val())).toFixed(2));
					/*+ parseFloat($('#otherincome').val()) + parseFloat($(
					'#paidOut1').val())).toFixed(2));*/
					if (isNaN($('#paidOut').val()))
						$('#paidOut').val('0.0')
					$('#discrepancy').val(
							(parseFloat($('#paidOut').val()) - parseFloat($(
									'#zReading').val())).toFixed(2));

					if (isNaN($('#discrepancy').val()))
						$('#discrepancy').val('0.0')
					if ($('#discrepancy').val() < 0.0) {
						$('#discrepancy').css("color", "red");
					}
					if ($('#discrepancy').val() > 0.0) {
						$('#discrepancy').css("color", "#52BCD3");
					}

				}
				function checkCash() {
					if (parseFloat($('#cashmsg').val()) == parseFloat($('#cash')
							.val())) {
						return true;

					} else {
						alert("Please Take Cash from Zread report on Till Receipt");
						return false;
					}

				}
			</script>

		</section>