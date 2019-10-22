
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<article class="content forms-page">
<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">
			<form:form
				action="${pageContext.request.contextPath}/group/manageGroupDetails.do"
				method="POST" modelAttribute="groupDetails">
				<div class="col-md-12">
					<div class="card card-block sameheight-item">
						<div class="subtitle-block">
							<h3 class="subtitle">Group Information</h3>
						</div>

						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Group
										Name </label><label class="text-danger">*</label>
									<form:input path="description" class="form-control"
										required="required" />
									<form:errors path="description" cssClass="error" />
								</div>
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Address1
									</label><label class="text-danger">*</label>
									<form:textarea path="addrLine1" class="form-control"
										required="required"></form:textarea>
									<form:errors path="addrLine1" cssClass="error" />
								</div>
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Address2
									</label>
									<form:textarea path="addrLin2" class="form-control"></form:textarea>
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Country
									</label><label class="text-danger">*</label>

									<form:select path="countryCode" class="form-control">
										<option>--- Select ---</option>
										<form:options items="${countrycode}" itemLabel="countryName"
											itemValue="id" />
									</form:select>

									<form:errors path="countryCode" cssClass="error" />
								</div>
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Town
									</label><label class="text-danger">*</label>
									<form:input path="town" class="form-control"
										required="required" />
									<form:errors path="town" cssClass="error" />
								</div>
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Postcode
									</label><label class="text-danger">*</label>
									<form:input path="postcode" class="form-control"
										required="required"
										pattern="^\(?([A-Z0-9]{4,3,2})\)?[ ]?([A-Z0-9]{3,4})"
										onkeyup="GetPostCodeFormat('postcode')" maxlength="8" />
									<form:errors path="postcode" cssClass="error" />
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Telephone
									</label><label class="text-danger">*</label>
									<form:input path="telephoneNo" class="form-control"
										required="required"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$"
										onkeyup="GetPhoneFormat('telephoneNo')" maxlength="12" />
									<form:errors path="telephoneNo" cssClass="error" />
								</div>
								<div class="col-sm-4 col-xs-12">
									<label class="control-label" for="formGroupExampleInput2">Fax
									</label><label class="text-danger">*</label>
									<form:input path="faxNo" class="form-control"
										required="required"
										pattern="^\(?([0-9]{4})\)?[ . ]?([0-9]{7})$"
										onkeyup="GetPhoneFormat('faxNo')" maxlength="12" />
									<form:errors path="faxNo" cssClass="error" />
								</div>

							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-xs-2">
									<label class="control-label" for="formGroupExampleInput2">Custom_field1
									</label>
									<form:input path="customField1" class="form-control" />
								</div>
								<div class="col-xs-2">
									<label class="control-label" for="formGroupExampleInput2">Custom_field2
									</label>
									<form:input path="customField2" class="form-control" />
								</div>
								<div class="col-xs-2">
									<label class="control-label" for="formGroupExampleInput2">Custom_field3
									</label>
									<form:input path="customField3" class="form-control" />
								</div>
								<div class="col-xs-2">
									<label class="control-label" for="formGroupExampleInput2">Custom_field4
									</label>
									<form:input path="customField4" class="form-control" />
								</div>
								<div class="col-xs-4">
									<label class="control-label" for="formGroupExampleInput2">Custom_field5
									</label>
									<form:input path="customField5" class="form-control" />
								</div>
							</div>
						</div>
						<div class="col-md-12">
							<div class="row form-group">
								<div class="col-sm-4 col-xs-12">
									<label>Lock </label>
									<p class="text-muted">
										<form:checkbox path="IsActive" value="Y" />
										&nbsp;&nbsp;Is Lock?
									</p>
								</div>
							</div>
						</div>
						<section class="section pull-right">
							<a href="searchPg.do" type="button" class="btn btn-secondary">Discard</a>
							<button type="reset" class="btn btn-secondary">Reset</button>
							<c:choose>
								<c:when
									test="${not empty groupDetails && not empty groupDetails.getId() && not empty mode}">
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
		
		$("ul li").removeClass("active");
		$("#liAdmin").addClass("open");
		$("#liAdmin").addClass("active");
		$("#liAdmin ul").addClass("collapse in");
		$("#liGroup").addClass("active");
		

	});
	


</script>