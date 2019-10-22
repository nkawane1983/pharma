<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------Other Income - Model------------------------- -->
			<!-- ------------------------------------------------------- -->



			<div class="modal fade" id="other-modal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
							</a>
							<h4 class="modal-title" id="myModalLabel">Other Income</h4>
						</div>
						<div class="modal-body" style="padding: 0px; height: 500px;">
							<div class="card">
								<div class="col-md-12">

									<div class="col-md-12">

										<div class="col-sm-8 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Category </label><select class="form-control"
												id="otherIncomeCategory"
												style="padding-top: 0.1rem; height: calc(2.1rem - 2px)">
												<option value="">----Select---</option>
												<c:forEach items="${otherIncomelist}" var="otherIncome">
													<option
														value="<c:out value="${otherIncome.categoryName}" />"
														isred="${otherIncome.id}"><c:out
															value="${otherIncome.categoryName}" /></option>
												</c:forEach>

											</select> <input type="hidden" name="otherIncometrindex"
												class="form-control" id="otherIncometrindex"
												style="text-align: left;" />
										</div>
										<div class="col-sm-4 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Amount </label><label class="text-danger">*</label> <input
												type="text" name="otherIncomeAmt" class="form-control"
												id="otherIncomeAmt"
												onkeypress="return isNumberKey(this,event)" maxlength="8" />
										</div>
									</div>
									<div class="col-md-12">
										<div class="col-sm-12 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Description </label> <input type="text" name="otherdescription"
												class="form-control" id="otherdescription"
												style="text-align: left;" />
										</div>
									</div>

									<div class="col-md-12">
										<div class=" col-sm-1"></div>
										<div class="col-sm-2 col-xs-12">

											<button type="button" class="btn btn-primary"
												onclick="addOtherIncome()">Add</button>

										</div>
										<div class=" col-sm-5"></div>
										<div class=" col-sm-2 col-xs-12 ">

											<button type="button" class=" btn btn-primary "
												onclick="delOtherIncome()" id="deleteOtherIncome"
												disabled="disabled">Delete</button>

										</div>
									</div>
								</div>
								<div class="col-md-12">
									<div class="col-md-10">
										<table class="table table-hover flip-content">
											<thead>
												<tr>
													<th>Category</th>
													<th>Amount</th>
													<th>Description</th>
												</tr>
											</thead>
											<tbody id="OtherIncometbody"></tbody>
										</table>
									</div>

								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
			<script>
				var edit = 0.0;
				function addOtherIncome() {

					if ($('#otherIncometrindex').val() != '') {

						$('#otherincome')
								.val(
										(parseFloat($('#otherincome').val()) - parseFloat(edit))
												.toFixed(2));
						document.getElementById("OtherIncometbody").deleteRow(
								$('#otherIncometrindex').val());
						edit = 0.0;
						$('#otherIncometrindex').val('');

					}
					if ($('#otherIncomeCategory').val() != ''
							&& $('#otherIncomeAmt').val() != '') {

						$('#otherincome')
								.val(
										(parseFloat($('#otherIncomeAmt').val()) + parseFloat($(
												'#otherincome').val()))
												.toFixed(2));
						$('#OtherIncometbody').append(
								"<tr><td>" + $('#otherIncomeCategory').val()
										+ "</td><td>"
										+ $('#otherIncomeAmt').val()
										+ "</td><td>"
										+ $('#otherdescription').val()
										+ "</td></tr>");
						$('#otherIncomeCategory').val('');
						$('#otherIncomeAmt').val('');
						$('#otherdescription').val('');
						setvalue();
					}

				}

				function delOtherIncome() {
					$('#otherincome')
							.val(
									(parseFloat($('#otherincome').val()) - parseFloat($(
											'#otherIncomeAmt').val()))
											.toFixed(2));

					document.getElementById("OtherIncometbody").deleteRow(
							$('#otherIncometrindex').val());
					$('#otherIncomeCategory').val('');
					$('#otherIncomeAmt').val('');
					$('#otherdescription').val('');
					$('#deleteOtherIncome').attr("disabled", "disabled");
					$('#otherIncometrindex').val('');
					setvalue();
				}

				$(document)
						.ready(
								function() {

									$("#OtherIncometbody")
											.delegate(
													'tr',
													'click',
													function() {
														$('#otherIncometrindex')
																.val(
																		$(this)
																				.index());
														$(
																'#otherIncomeCategory')
																.val(
																		$(this)
																				.find(
																						"td:eq(0)")
																				.text().trim());
														$('#otherIncomeAmt')
																.val(
																		$(this)
																				.find(
																						"td:eq(1)")
																				.text().trim());
														$('#otherdescription')
																.val(
																		$(this)
																				.find(
																						"td:eq(2)")
																				.text().trim());
														edit = $(this).find(
																"td:eq(1)")
																.text();
														$('#deleteOtherIncome')
																.removeAttr(
																		"disabled");
													});
								});
			</script>
		</section>