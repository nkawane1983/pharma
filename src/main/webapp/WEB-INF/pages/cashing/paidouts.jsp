<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------Total Paid Outs - Model------------------------- -->
			<!-- ------------------------------------------------------- -->



			<div class="modal fade" id="totalPaidOuts-modal" tabindex="-1"
				role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
							</a>
							<h4 class="modal-title" id="myModalLabel">Paid Outs</h4>
						</div>
						<div class="modal-body" style="padding: 0px; height: 500px;">
							<div class="card">
								<div class="col-md-12">

									<div class="col-md-12">

										<div class="col-sm-8 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Category </label><select class="form-control" id="paidOut1Category"
												style="padding-top: 0.1rem; height: calc(2.1rem - 2px)">
												<option value="">----Select---</option>
												<c:forEach items="${paidoutlist}" var="paidoutlist">
													<option
														value="<c:out value="${paidoutlist.categoryName}"/>"
														isred="${paidoutlist.id}"><c:out
															value="${paidoutlist.categoryName}" /></option>
												</c:forEach>

											</select> <input type="hidden" name="paidOut1Categorytrindex"
												class="form-control" id="paidOut1Categorytrindex"
												style="text-align: left;" />
										</div>
										<div class="col-sm-4 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Amount </label><label class="text-danger">*</label> <input
												type="text" name="paidOut1Amt" class="form-control"
												id="paidOut1Amt" onkeypress="return isNumberKey(this,event)"
												maxlength="8" />
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-sm-12 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Description </label> <input type="text" name="paidOut1description"
												class="form-control" id="paidOut1description"
												style="text-align: left;" />
										</div>
									</div>

									<div class="col-md-12">
										<div class=" col-sm-1"></div>
										<div class="col-sm-2 col-xs-12">

											<button type="button" class="btn btn-primary"
												onclick="addPaidOut()">Add</button>

										</div>
										<div class=" col-sm-5"></div>
										<div class=" col-sm-2 col-xs-12 ">

											<button type="button" class=" btn btn-primary "
												onclick="deletePaidOut1()" id="deletePaidOut"
												disabled="disabled">Delete</button>

										</div>
									</div>
								</div>
								<div class="col-md-12" style="height: 340px; overflow: auto;">

									<table class="table  table-hover flip-content">
										<thead>
											<tr>
												<th>Category</th>
												<th>Amount</th>
												<th>Description</th>
											</tr>
										</thead>
										<tbody id="PaidOuttbody">
											<c:choose>
												<c:when
													test="${not empty cashing && not empty cashing.getId() && not empty mode}">
													<c:forEach items="${cashing.getPaidouts()}" var="paidouts1"
														varStatus="count">
														<tr>
															<td><c:forEach items="${paidoutlist}"
																	var="paidoutlist">
																	<c:if test="${paidouts1.ctype==paidoutlist.id}">${paidoutlist.categoryName}</c:if>
																</c:forEach> <form:hidden path="paidouts[${count.index}].ctype"
																	value="" /></td>
															<td>${paidouts1.amount}<form:hidden
																	path="paidouts[${count.index}].amount" value="" />
															</td>
															<td>${paidouts1.description}<form:hidden
																	path="paidouts[${count.index}].description" value="" />
															</td>
														</tr>

													</c:forEach>
												</c:when>

											</c:choose>
										</tbody>
									</table>

									<c:choose>
										<c:when
											test="${not empty cashing && not empty cashing.getId() && not empty mode}">
											<c:forEach items="${cashing.getPaidouts()}"
												var="paidouts1coupons" varStatus="count">

												<form:hidden path="paidouts[${count.index}].id" />


											</c:forEach>
										</c:when>

									</c:choose>
									<input type="hidden" id="paidoutscount"
										value="${fn:length(cashing.getPaidouts())}" />


								</div>

							</div>
						</div>
					</div>
				</div>
			</div>

			<script type="text/javascript">
				var edit = 0.0;
				function addPaidOut() {
					var count;
					if ($('#paidOut1Categorytrindex').val() != '') {
						$('#paidOut1')
								.val(
										(parseFloat($('#paidOut1').val()) - parseFloat(edit))
												.toFixed(2));
						document.getElementById("PaidOuttbody").deleteRow(
								$('#paidOut1Categorytrindex').val());
						edit = 0.0;
						$("#paidoutscount").val(
								parseInt($("#paidoutscount").val())
										- parseInt(1));
						count = $('#paidOut1Categorytrindex').val();
						$('#paidOut1Categorytrindex').val('');

					} else {
						count = $("#paidoutscount").val();
					}

					if ($('#paidOut1Category').val() != ''
							&& $('#paidOut1Amt').val().trim() != ''
							&& $('#paidOut1description').val().trim() != '') {

						var txt1 = "paidouts[" + count + "].ctype";
						var txt11 = "paidouts" + count + ".ctype";
						var txt2 = "paidouts[" + count + "].amount";
						var txt22 = "paidouts" + count + ".amount";
						var txt3 = "paidouts[" + count + "].description";
						var txt33 = "paidouts" + count + ".description";

						$('#paidOut1')
								.val(
										(parseFloat($('#paidOut1Amt').val()) + parseFloat($(
												'#paidOut1').val())).toFixed(2));
						$('#PaidOuttbody').append(
								'<tr><td>'
										+ $('#paidOut1Category').val()
										+ '<input name="'
										+ txt1
										+ '" id="'
										+ txt11
										+ '" value="'
										+ $("#paidOut1Category").find(
												':selected').attr('isred')
										+ '" type="hidden"></td><td>'
										+ $('#paidOut1Amt').val()
										+ '<input name="' + txt2 + '" id="'
										+ txt22 + '" value="'
										+ $("#paidOut1Amt").val()
										+ '" type="hidden"></td><td>'
										+ $('#paidOut1description').val()
										+ '<input name="' + txt3 + '" id="'
										+ txt33 + '" value="'
										+ $("#paidOut1description").val()
										+ '" type="hidden"></td></tr>');
						$('#paidOut1Category').val('');
						$('#paidOut1Amt').val('');
						$('#paidOut1description').val('');
						$("#paidoutscount").val(
								parseInt($("#paidoutscount").val())
										+ parseInt(1));
						setvalue();
					} else {
						if ($('#paidOut1Category').val() == '') {
							alert("PaidOut Category be selected");
							return false;
						}
						if ($('#paidOut1description').val().trim() == '') {
							alert("Description must be entered");
							$('#paidOut1description').val("");
							return false;
						}

					}

				}

				function deletePaidOut1() {

					$('#paidOut1').val(
							(parseFloat($('#paidOut1').val()) - parseFloat($(
									'#paidOut1Amt').val())).toFixed(2));

					document.getElementById("PaidOuttbody").deleteRow(
							$('#paidOut1Categorytrindex').val());
					$('#paidOut1Category').val('');
					$('#paidOut1Amt').val('');
					$('#paidOut1description').val('');
					$('#deletePaidOut').attr("disabled", "disabled");
					$('#paidOut1Categorytrindex').val('');
					setvalue();
				}
				$(document)
						.ready(
								function() {

									$("#PaidOuttbody")
											.delegate(
													'tr',
													'click',
													function() {
														$(
																'#paidOut1Categorytrindex')
																.val(
																		$(this)
																				.index());
														$('#paidOut1Category')
																.val(
																		$(this)
																				.find(
																						"td:eq(0)")
																				.text().trim());
														$('#paidOut1Amt')
																.val(
																		$(this)
																				.find(
																						"td:eq(1)")
																				.text().trim());
														edit = $(this).find(
																"td:eq(1)")
																.text();
														$(
																'#paidOut1description')
																.val(
																		$(this)
																				.find(
																						"td:eq(2)")
																				.text().trim());
														$('#deletePaidOut')
																.removeAttr(
																		"disabled");
													});
								});
				function checkNumber(cashval) {
					if (isNaN(cashval.value)) {
						$('#' + cashval.id).val(cashval.value.slice(0, -1));
					}
				}
				function checkPaidOut() {
					if (parseFloat($('#paidOutmsg').val()) == parseFloat($(
							'#paidOut1').val())) {
						return true;

					} else {
						alert("Please Take PaidOut from Zread report on Till Receipt");
						return false;
					}

				}
				
			</script>
		</section>