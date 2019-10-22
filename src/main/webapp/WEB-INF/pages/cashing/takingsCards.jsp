
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
			<!-- ------------------------------------------------------- -->
			<!-- ---------------Cards - Model------------------------- -->
			<!-- ------------------------------------------------------- -->



			<div class="modal fade" id="cards-modal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header" style="padding: 8px;">
							<a type="button" class="close" data-dismiss="modal"
								aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
							</a>
							<h4 class="modal-title" id="myModalLabel">Cards</h4>
						</div>
						<div class="modal-body" style="padding: 0px; height: 500px;">
							<div class="card">
								<div class="col-md-12">

									<div class="col-md-12">

										<div class="col-sm-6 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Name </label><select class="form-control" id="cardname"
												style="padding-top: 0.1rem; height: calc(2.1rem - 2px)">
												<option value="">----Select---</option>
												<c:forEach items="${cards}" var="item" varStatus="count">
													<option value="<c:out value="${item.categoryName}"/>"
														isred="${item.id}"><c:out
															value="${item.categoryName}" /></option>
												</c:forEach>

											</select> <input type="hidden" name="cardtrindex" class="form-control"
												id="cardtrindex" style="text-align: left;" />
										</div>

										<div class="col-sm-4 col-xs-12">
											<label class="control-label" for="formGroupExampleInput2">
												Amount </label><label class="text-danger">*</label> <input
												type="text" name="cardAmt" class="form-control" id="cardAmt"
												onkeypress="return isNumberKey(this,event)" maxlength="8" />
										</div>
										<div class="col-sm-2 col-xs-12" id="ft">

											<button type="button" class="btn btn-primary"
												onclick="addCard()">Add</button>

										</div>
									</div>
									<div class="col-md-12">
										<div class="col-md-10" style="height: 430px; overflow: auto;">
											<table class="table table-bordered table-hover flip-content"
												border="1" id="">
												<thead>
													<tr>
														<th>Name</th>
														<th>Amount</th>
													</tr>
												</thead>
												<tbody id="Cardtbody">
													<c:choose>
														<c:when
															test="${not empty cashing && not empty cashing.getId() && not empty mode}">
															<c:forEach items="${cashing.getTakingscards()}"
																var="tachingcard" varStatus="count">
																<tr>
																	<td><c:forEach items="${cards}" var="item">
																			<c:if test="${tachingcard.ctype==item.id}">${item.categoryName}</c:if>
																		</c:forEach> <form:hidden
																			path="takingscards[${count.index}].ctype" value="" /></td>
																	<td>${tachingcard.amount}<form:hidden
																			path="takingscards[${count.index}].amount" value="" />
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
													<c:forEach items="${cashing.getTakingscards()}"
														var="tachingcard" varStatus="count">

														<form:hidden path="takingscards[${count.index}].id" />


													</c:forEach>
												</c:when>

											</c:choose>
											<input type="hidden" id="cardscount"
												value="${fn:length(cashing.getTakingscards())}" />
										</div>
										<div class="col-sm-2 col-xs-12" style="padding: 5px;">

											<button type="button" class="btn btn-primary"
												onclick="delcard()" id="deletecard" disabled="disabled">Delete</button>

										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<script>
				var edit = 0.0
				function addCard() {
					var count;
					if ($('#cardtrindex').val() != '') {
						$('#cards')
								.val(
										(parseFloat($('#cards').val()) - parseFloat(edit))
												.toFixed(2));
						document.getElementById("Cardtbody").deleteRow(
								$('#cardtrindex').val());
						edit = 0.0;
						$("#cardscount").val(
								parseInt($("#cardscount").val()) - parseInt(1));
						count = $('#cardtrindex').val();
						$('#cardtrindex').val('');

					} else {
						count = $("#cardscount").val();
					}

					if ($('#cardname').val() != '' && $('#cardAmt').val() != '') {

						var txt1 = "takingscards[" + count + "].ctype";
						var txt11 = "takingscards" + count + ".ctype";
						var txt2 = "takingscards[" + count + "].amount";
						var txt22 = "takingscards" + count + ".amount";
						$('#cards')
								.val(
										(parseFloat($('#cardAmt').val()) + parseFloat($(
												'#cards').val())).toFixed(2));
						$('#Cardtbody').append(
								'<tr><td>'
										+ $('#cardname').val()
										+ '<input name="'
										+ txt1
										+ '" id="'
										+ txt11
										+ '" value="'
										+ $("#cardname").find(':selected')
												.attr('isred')
										+ '" type="hidden"></td><td>'
										+ $('#cardAmt').val() + '<input name="'
										+ txt2 + '" id="' + txt22 + '" value="'
										+ $('#cardAmt').val()
										+ '" type="hidden"></td></tr>');
						$('#cardname').val('');
						$('#cardAmt').val('');
						$("#cardscount").val(
								parseInt($("#cardscount").val()) + parseInt(1));
						setvalue();
					}

				}

				function delcard() {
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
						$('#cardname').val($(this).find("td:eq(0)").text().trim());
						$('#cardAmt').val($(this).find("td:eq(1)").text().trim());
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
				}
			</script>

		</section>