<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<section>
	<!-- ------------------------------------------------------- -->
	<!-- ---------------Coupons - Model------------------------- -->
	<!-- ------------------------------------------------------- -->



	<div class="modal fade" id="coupons-modal" tabindex="-1" role="dialog"
		aria-labelledby="basicModal" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header" style="padding: 8px;">
					<a type="button" class="close" data-dismiss="modal"
						aria-hidden="true" tabindex="-1"> <i class="fa fa-times"></i>
					</a>
					<h4 class="modal-title" id="myModalLabel">Coupons</h4>
				</div>
				<div class="modal-body" style="padding: 0px; height: 500px;">
					<div class="card">
						<div class="col-md-12">

							<div class="col-md-12">

								<div class="col-sm-6 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">
										Name </label><select class="form-control" id="couponsname"
										style="padding-top: 0.1rem; height: calc(2.1rem - 2px)">
										<option value="">----Select---</option>
										<c:forEach items="${couponslist}" var="coupon">
											<option value="<c:out value="${coupon.categoryName}"/>"
												isred="${coupon.id}"><c:out
													value="${coupon.categoryName}" /></option>
										</c:forEach>

									</select> <input type="hidden" name="couponstrindex"
										class="form-control" id="couponstrindex"
										style="text-align: left;" />
								</div>
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">
										Amount </label><label class="text-danger">*</label> <input type="text"
										name="couponsAmt" class="form-control" id="couponsAmt"
										onkeypress="return isNumberKey(this,event)" maxlength="8" />
								</div>
								<div class="col-sm-2 col-xs-12" id="ft">

									<button type="button" class="btn btn-primary"
										onclick="addCoupons()">Add</button>

								</div>
							</div>
							<div class="col-md-12">
								<div class="col-md-10" style="height: 430px; overflow: auto;">
									<table class="table table-bordered table-hover flip-content"
										border="1" id="">
										<thead>
											<tr>
												<th>Coupons</th>
												<th>Amount</th>
											</tr>
										</thead>
										<tbody id="Couponstbody">
											<c:choose>
												<c:when
													test="${not empty cashing && not empty cashing.getId() && not empty mode}">
													<c:forEach items="${cashing.getTakingcoupons()}"
														var="tachingcoupons" varStatus="count">
														<tr>
															<td><c:forEach items="${couponslist}" var="coupon">
																	<c:if test="${tachingcoupons.ctype==coupon.id}">${coupon.categoryName}</c:if>
																</c:forEach> <form:hidden path="takingcoupons[${count.index}].ctype"
																	value="" /></td>
															<td>${tachingcoupons.amount}<form:hidden
																	path="takingcoupons[${count.index}].amount" value="" />
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
											<c:forEach items="${cashing.getTakingcoupons()}"
												var="takingcoupons" varStatus="count">

												<form:hidden path="takingcoupons[${count.index}].id" />


											</c:forEach>
										</c:when>

									</c:choose>
									<input type="hidden" id="couponscount"
										value="${fn:length(cashing.getTakingcoupons())}" />
								</div>
								<div class="col-sm-2 col-xs-12" style="padding: 5px;">

									<button type="button" class="btn btn-primary"
										onclick="delcoupons()" id="deletecoupons" disabled="disabled">Delete</button>

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
		function addCoupons() {
			var count;
			if ($('#couponstrindex').val() != '') {
				$('#coupuns').val(
						(parseFloat($('#coupuns').val()) - parseFloat(edit))
								.toFixed(2));
				document.getElementById("Couponstbody").deleteRow(
						$('#couponstrindex').val());
				edit = 0.0;
				$("#couponscount").val(
						parseInt($("#couponscount").val()) - parseInt(1));
				count = $('#couponstrindex').val();
				$('#couponstrindex').val('');

			} else {
				count = $("#couponscount").val();
			}
			if ($('#couponsname').val() != '' && $('#couponsAmt').val() != '') {

				var txt1 = "takingcoupons[" + count + "].ctype";
				var txt11 = "takingcoupons" + count + ".ctype";
				var txt2 = "takingcoupons[" + count + "].amount";
				var txt22 = "takingcoupons" + count + ".amount";

				$('#coupuns').val(
						(parseFloat($('#couponsAmt').val()) + parseFloat($(
								'#coupuns').val())).toFixed(2));
				$('#Couponstbody').append(
						'<tr><td>'
								+ $('#couponsname').val()
								+ '<input name="'
								+ txt1
								+ '" id="'
								+ txt11
								+ '" value="'
								+ $("#couponsname").find(':selected').attr(
										'isred') + '" type="hidden"></td><td>'
								+ $('#couponsAmt').val() + '<input name="'
								+ txt2 + '" id="' + txt22 + '" value="'
								+ $('#couponsAmt').val()
								+ '" type="hidden"></td></tr>');
				$('#couponsname').val('');
				$('#couponsAmt').val('');
				$("#couponscount").val(
						parseInt($("#couponscount").val()) + parseInt(1));
				setvalue();
			}

		}

		function delcoupons() {
			$('#coupuns').val(
					(parseFloat($('#coupuns').val()) - parseFloat($(
							'#couponsAmt').val())).toFixed(2));

			document.getElementById("Couponstbody").deleteRow(
					$('#couponstrindex').val());
			$('#couponsname').val('');
			$('#couponsAmt').val('');
			$('#deletecoupons').attr("disabled", "disabled");
			$('#couponstrindex').val('');
			setvalue();
		}
		$(document).ready(function() {

			$("#Couponstbody").delegate('tr', 'click', function() {
				$('#couponstrindex').val($(this).index());
				$('#couponsname').val($(this).find("td:eq(0)").text().trim());
				$('#couponsAmt').val($(this).find("td:eq(1)").text().trim());
				edit = $(this).find("td:eq(1)").text();
				$('#deletecoupons').removeAttr("disabled");
			});
		});

		function checkCoupons() {
			if (parseFloat($('#coupunsmsg').val()) == parseFloat($('#coupuns')
					.val())) {
				return true;

			} else {
				alert("Please Take Coupons from Zread report on Till Receipt");
				return false;
			}
		}
	</script>
</section>