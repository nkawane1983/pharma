
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<form:form
				action="${pageContext.request.contextPath}/branch/manageBranchDetails.do"
				method="POST" modelAttribute="branchDetails">
				
				<form:hidden path="period"/>
				
				
				<div class="col-md-12">
					<div class="card card-block sameheight-item">
						<div class="subtitle-block">
							<h3 class="subtitle">Branch Information</h3>
						</div>
						<form:hidden path="id" />
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-3 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Branch
										Name </label><label class="text-danger">*</label>
									<form:input path="description" class="form-control" required="required"/>
									<form:errors path="description" cssClass="error" />
								</div>
								<div class="col-sm-3 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">InternalID
									</label><label class="text-danger">*</label>
									<form:input path="internalBranchId" class="form-control" required="required"/>
									<form:errors path="internalBranchId" cssClass="error" />
								</div>
								<div class="col-sm-2 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">
										Group</label><label class="text-danger">*</label>

									<form:select path="groupId" class="form-control">
										<form:option value='0' label="----Select----" />

										<c:forEach items="${groupDetails}" var="groupDetails">
											<form:option value='${groupDetails.getGroupId()}'
												label="${groupDetails.getGroupName()}" />
										</c:forEach>
									</form:select>

								</div>
								<div class="col-sm-2 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">No.
										of Tills</label><label class="text-danger">*</label>
									<form:input path="noOfTills" class="form-control" type="number" required="required"/>
									<form:errors path="noOfTills" cssClass="error" />
								</div>
								<div class="col-sm-2 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">C
										& D Monthly</label><label class="text-danger">*</label>
									<form:input path="cdMontlyTarget" class="form-control" required="required"/>
									<form:errors path="cdMontlyTarget" cssClass="error" />
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-3 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Address1
									</label><label class="text-danger">*</label>
									<form:textarea path="addrline1" class="form-control"
										required="required" />
									<form:errors path="addrline1" cssClass="error" />
								</div>
								<div class="col-sm-3 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Address2
									</label>
									<form:textarea path="addrline2" class="form-control" />
									<form:errors path="addrline2" cssClass="error" />
								</div>
								<div class="col-sm-2 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Country
									</label><label class="text-danger">*</label>

									<form:select path="county_code" class="form-control">
										<option value="">--- Select ---</option>
										<form:options items="${countrycode}" itemLabel="countryName"
											itemValue="id" />
									</form:select>

									<form:errors path="county_code" cssClass="error" />
								</div>
								<div class="col-sm-2 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Town
									</label><label class="text-danger">*</label>
									<form:input path="town" class="form-control" required="required"/>
									<form:errors path="town" cssClass="error" />
								</div>
								<div class="col-sm-2 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Postcode
									</label><label class="text-danger">*</label>
									<form:input path="postcode" class="form-control"
										pattern="^\(?([A-Z0-9]{4,3,2})\)?[ ]?([A-Z0-9]{3,4})"
										onkeyup="GetPostCodeFormat('postcode')" maxlength="8" placeholder="AA99 9AA..." required="required"/>
									<form:errors path="postcode" cssClass="error" />
								</div>

							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-3 col-xs-12">
									<label class="control-label">Telephone</label> 
									<form:input path="telephoneNo" class="form-control"
										 data-required="true"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$"
										id="managerTelephone"
										onkeyup="GetPhoneFormat('managerTelephone')" maxlength="12" placeholder="9999 999..."/>
									<form:errors path="telephoneNo" cssClass="error" />
								</div>
								<div class="col-sm-3 col-xs-12">
									<label class="control-label">FaxNo</label> 
									<form:input path="faxNo" class="form-control"
										placeholder="faxNo" data-required="true"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$"
										onkeyup="GetPhoneFormat('faxNo')" maxlength="12" />
									<form:errors path="faxNo" cssClass="error" />
								</div>
								<div class="col-sm-3 col-xs-12">
									<label class="control-label">Email</label><label
										class="text-danger">*</label>
									<form:input type="email" path="email" class="form-control"
										placeholder="Email" required="required" />
								</div>
								<div class="col-sm-3 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Notes
									</label>
									<form:textarea path="notes" class="form-control"
										placeholder="notes" />
									<form:errors path="notes" cssClass="error" />
								</div>

							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">

								<div class="col-sm-12 col-xs-12" id="allbank">
									<label class="control-label" for="formGroupExampleInput2">WorkingDay
									</label><label class="text-danger">*</label> &nbsp;&nbsp;&nbsp;<input
										type="checkbox" id="mo" value="1" title="Monday"
										data-toggle="tooltip" class="tooltipButton"
										onclick="getWorkingDay(this)" name="workingbox"
										checked="checked" /> Mo &nbsp;<input type="checkbox" id="tu"
										value="1" title="Tuesday" data-toggle="tooltip"
										class="tooltipButton" onclick="getWorkingDay(this)"
										name="workingbox" checked="checked" /> Tu &nbsp; <input
										type="checkbox" id="we" value="1" title="Wednesday"
										data-toggle="tooltip" class="tooltipButton"
										onclick="getWorkingDay(this)" name="workingbox"
										checked="checked" /> We &nbsp;<input type="checkbox" id="th"
										value="1" title="Thursday" data-toggle="tooltip"
										class="tooltipButton" onclick="getWorkingDay(this)"
										name="workingbox" checked="checked" /> Th &nbsp;<input
										type="checkbox" id="fr" value="1" title="Friday"
										data-toggle="tooltip" class="tooltipButton"
										onclick="getWorkingDay(this)" name="workingbox"
										checked="checked" /> Fr &nbsp; <input type="checkbox" id="sa"
										value="1" title="Saturday" data-toggle="tooltip"
										class="tooltipButton" onclick="getWorkingDay(this)"
										name="workingbox" /> Sa&nbsp; <input type="checkbox" id="su"
										value="1" title="Sunday" data-toggle="tooltip"
										class="tooltipButton" onclick="getWorkingDay(this)"
										name="workingbox" /> Su
									<form:hidden path="workingDay" class="form-control" />
								</div>


							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 col-xs-12">
									<label>Active/Inactive </label><label
										class="text-danger">*</label>
									<p class="text-muted">
										<form:checkbox path="isActive" value="1" required="required"/>
										&nbsp;&nbsp;Is Active/Inactive?
									</p>
								</div>
							</div>
						</div>
						<section class="section pull-right">
							<a href="searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							<button type="reset" class="btn btn-secondary">Reset</button>
							<c:choose>
								<c:when test="${not empty mode}">
									<button type="submit" name="edit" value="edit"
										class="btn btn-primary">Save</button>
								</c:when>
								<c:otherwise>
									<button type="submit" name="add" value="add"
										class="btn btn-primary">Add</button>
								</c:otherwise>
							</c:choose>
						</section>



					</div>
				</div>
			</form:form>
		</div>
	</section>
</article>
<script type="text/javascript">
	$(function() {
		getloadworking();
		$('.tooltipButton').tooltip();
		$('#startDate').datepicker({
			format : 'dd/mm/yyyy'
		});
		$('#endDate').datepicker({
			format : 'dd/mm/yyyy'
		});
		$("ul li").removeClass("active");
		$("#liAdmin").addClass("open");
		$("#liAdmin").addClass("active");
		$("#liAdmin ul").addClass("collapse in");
		$("#liBranch").addClass("active");

	});
	var app = angular.module('aveeApp', []);
	app.directive("datepicker", function() {
		return {
			restrict : "A",
			link : function(scope, el, attr) {
				el.datepicker({
					dateFormat : 'mm/dd/yy',
					firstDay : 1,
					changeMonth : true,
					changeYear : true
				});
			}
		};
	})
	function validate() {
		$('input[type="text"]').each(function() {
			if (this.value == "") {
				$(this).attr('required', 'required');

			}
		})
		$('select').each(function() {
			if (this.value == "") {
				$(this).attr('required', 'required');

			}
		})
	}
	function getWorkingDay(str) {

		if ($(str).prop("checked") == true) {
			$('#allbank :input[name=workingbox]').each(function() {
				if (this.id == str.id) {
					$(this).prop('checked', true);
				}
			});
		}
		getloadworking();
	}
	function getloadworking() {
		var selected = [];
		$('#allbank :input[name=workingbox]').each(function() {
			if ($(this).prop("checked") == true) {
				selected.push('1');
			} else {
				selected.push('0');
			}
		});
		var myStr;
		myStr = selected.join('');
		$("#workingDay").val(myStr);
	}
</script>