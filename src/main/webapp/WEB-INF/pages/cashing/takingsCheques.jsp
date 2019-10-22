<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------CHEQUES - Model------------------------- -->
			<!-- ------------------------------------------------------- -->



			<div class="modal fade" id="cheques-modal" tabindex="-1"
				role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
							</a>
							<h4 class="modal-title" id="myModalLabel">Cheques</h4>
						</div>
						<div class="modal-body" style="padding: 0px; height: 500px;">
							<div class="">
								<div class="col-md-12">
									<div class="col-md-12">
										<div class="col-sm-10 col-xs-12 text-danger" id="chMsg"></div>
									</div>
									<div class="col-md-12">
										<div class="col-sm-3 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Account No. </label><label class="text-danger">*</label><input
												type="text" name="accountNo" class="form-control"
												id="accountNo" style="text-align: left;" />
										</div>
										<div class="col-sm-4 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Name </label><label class="text-danger">*</label><input type="text"
												name="chequesname" class="form-control" id="chequesname"
												style="text-align: left;" /> <input type="hidden"
												name="trindex" class="form-control" id="trindex"
												style="text-align: left;" />
										</div>
										<div class="col-sm-3 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Amount </label><label class="text-danger">*</label> <input
												type="text" name="chequesAmt" class="form-control"
												id="chequesAmt" onkeypress="return isNumberKey(this,event)"
												maxlength="8" />
										</div>
										<div class="col-sm-2 col-xs-12" id="ft">

											<button type="button" class="btn btn-primary"
												onclick="addCheques()">Add</button>

										</div>
									</div>

									<div class="col-md-12">
										<div class="col-md-10" style="height: 430px; overflow: auto;">
											<table class="table table-bordered table-hover flip-content"
												border="1" id="">
												<thead>
													<tr>

														<th>Account No.</th>
														<th>Name</th>
														<th>Amount</th>
													</tr>
												</thead>

												<tbody id="Chequestbody">
													<c:choose>
														<c:when
															test="${not empty cashing && not empty cashing.getId() && not empty mode}">
															<c:forEach items="${cashing.getTakingscheques()}"
																var="tachingche" varStatus="count">

																<tr>
																	<td>${tachingche.accountNo}<form:hidden
																			path="takingscheques[${count.index}].accountNo"
																			value="" /> 
																	</td>
																	<td>${tachingche.cname}<form:hidden
																			path="takingscheques[${count.index}].cname" value="" />

																	</td>
																	<td>${tachingche.amount}<form:hidden
																			path="takingscheques[${count.index}].amount" value="" />
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
													<c:forEach items="${cashing.getTakingscheques()}"
														var="tachingche" varStatus="count">

														<form:hidden path="takingscheques[${count.index}].id" />


													</c:forEach>
												</c:when>

											</c:choose>
											<input type="hidden" id="chequescount"
												value="${fn:length(cashing.getTakingscheques())}" />
										</div>

										<div class="col-sm-2 col-xs-12" style="padding: 5px;">

											<button type="button" class="btn btn-primary"
												onclick="delCheques()" id="delete" disabled="disabled">Delete</button>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script>
				var edit = 0.0;
				function addCheques() {
					$("#chMsg").text('');
					var count;
					if ($('#trindex').val() != '') {
						$('#cheques')
								.val(
										(parseFloat($('#cheques').val()) - parseFloat(edit))
												.toFixed(2));
						document.getElementById("Chequestbody").deleteRow(
								$('#trindex').val());
						edit = 0.0;
						$("#chequescount").val(
								parseInt($("#chequescount").val())
										- parseInt(1));
						count = $('#trindex').val();
						$('#trindex').val('');

					} else {
						count = $("#chequescount").val();
					}

					if ($('#chequesname').val() != ''
							&& $('#chequesAmt').val() != ''
							&& $('#accountNo').val() != '') {

						var txt0 = "takingscheques[" + count + "].accountNo";
						var txt00 = "takingscheques" + count + ".accountNo";
						var txt1 = "takingscheques[" + count + "].cname";
						var txt11 = "takingscheques" + count + ".cname";
						var txt2 = "takingscheques[" + count + "].amount";
						var txt22 = "takingscheques" + count + ".amount";
						$('#cheques')
								.val(
										(parseFloat($('#chequesAmt').val()) + parseFloat($(
												'#cheques').val())).toFixed(2));
						$('#Chequestbody').append(
								'<tr><td>' + $('#accountNo').val()
										+ '<input name="' + txt0 + '" id="'
										+ txt00 + '" value="'
										+ $('#accountNo').val()
										+ '" type="hidden"></td><td>'
										+ $('#chequesname').val()
										+ '<input name="' + txt1 + '" id="'
										+ txt11 + '" value="'
										+ $('#chequesname').val()
										+ '" type="hidden"></td><td>'
										+ $('#chequesAmt').val()
										+ '<input name="' + txt2 + '" id="'
										+ txt22 + '" value="'
										+ $('#chequesAmt').val()
										+ '" type="hidden"></td></tr>');
						//addformtag(count);
						$('#accountNo').val('');
						$('#chequesname').val('');
						$('#chequesAmt').val('');
						setvalue();
						$("#chequescount").val(
								parseInt($("#chequescount").val())
										+ parseInt(1));
					} else {
						if ($('#chequesname').val() == ''
								&& $('#chequesAmt').val() == ''
								&& $('#accountNo').val() == '')
							$("#chMsg").text('Please enter All fileds.');
						else if ($('#accountNo').val() == '')
							$("#chMsg").text('Please enter account No.');
						else if ($('#chequesname').val() == '')
							$("#chMsg").text('Please enter cheque name');
						else
							$("#chMsg").text('Please enter cheque Amount');
					}

				}

				function delCheques() {
					$('#cheques')
							.val(
									parseFloat(
											($('#cheques').val())
													- parseFloat($(
															'#chequesAmt')
															.val())).toFixed(2));
					document.getElementById("Chequestbody").deleteRow(
							$('#trindex').val());
					$('#accountNo').val('');
					$('#chequesname').val('');
					$('#chequesAmt').val('');
					$('#delete').attr("disabled", "disabled");
					$('#trindex').val('');
					setvalue();
				}
				$(document).ready(function() {

					$("#Chequestbody").delegate('tr', 'click', function() {
						$('#trindex').val($(this).index());
						$('#accountNo').val($(this).find("td:eq(0)").text().trim());
						$('#chequesname').val($(this).find("td:eq(1)").text().trim());
						$('#chequesAmt').val($(this).find("td:eq(2)").text().trim());
						edit = $(this).find("td:eq(1)").text();
						$('#delete').removeAttr("disabled");
					});
				});

				function checkCheques() {
					if (parseFloat($('#chequeshmsg').val()) == parseFloat($(
							'#cheques').val())) {
						return true;

					} else {
						alert("Please Take Cheques from Zread report on Till Receipt");
						return false;
					}

				}
			</script>

		</section>