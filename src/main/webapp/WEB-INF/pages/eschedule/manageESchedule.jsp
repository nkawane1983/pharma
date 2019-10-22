<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<article class="content dashboard-page">
	<jsp:include page="../comman.jsp" />
	<section class="section">
		<div class="row sameheight-container">

			<div class="col-md-12">
				<div class="card card-block ">
					<div class="subtitle-block">
						<h3 class="subtitle">Import DAT File</h3>
					</div>
					<c:if test="${not empty msg}">
						<div class="col-md-12" style="text-align: center;">
							<label for="exampleInputEmail1" class="text-danger">${msg}
							</label>
						</div>
					</c:if>

					<form:form
						action="${pageContext.request.contextPath}/eschedule/uploadDatFilePg.do"
						method="post" enctype="multipart/form-data">
						<div class="col-md-12">
							<div class="tab-content">
								<div id="accordion" class="tab-pane fade in active"
									style="margin-top: 10px;">
									<div class="list-group panel " id="accordion">

										<div class="panel-collapse collapse in" id="cashing"
											style="border: 1px solid #ddd;">
											<section class="section">
												<div class="row sameheight-container">
													<div class="card-block ">
														<div class="col-md-12">
															<div class="col-sm-12">
																<div class="form-group col-sm-4 col-xs-12">
																	<label>Group</label> <Select class="form-control"
																		name="groupid" id="groupid" required>

																		<option value="">----Select Group----</option>
																		<c:forEach items="${groupDetails}" var="groupDetails">
																			<option value="${groupDetails.getGroupId()}">${groupDetails.getGroupName()}
																			</option>
																		</c:forEach>

																	</select>

																</div>
																<div class="col-sm-4 col-xs-12">
																	<label for="exampleInputEmail1"></label> <label
																		for="exampleInputEmail1">Drop files or click
																		here.<span class="text-danger">*</span>
																	</label> <input type="File" class="form-control" id="datfile"
																		placeholder="Enter File Version" name="datfile"
																		required>
																</div>
																<div class="col-sm-4 col-xs-12"
																	style="margin-top: 24px;">
																	<button type="Reset" class="btn btn-secondary">Reset</button>

																	<c:if test="${empty ppaDataList}">
																		<button type="submit" class="btn btn-primary ">Upload</button>
																	</c:if>
																	<c:if test="${not empty ppaDataList}">
																		<button type="submit" class="btn btn-primary "
																			disabled="disabled">Upload</button>
																	</c:if>
																</div>
															</div>
															<!-- 															<div class="col-sm-12"> -->
															<!-- 																<div class="col-sm-12 col-xs-12" -->
															<!-- 																	style="margin-top: 22px;"> -->
															<!-- 																	<label for="exampleInputEmail1" class="text-danger">Note:- -->
															<!-- 																	</label><label for="exampleInputEmail1">Don't know -->
															<!-- 																		about Excel Format Please Download.&nbsp;->&nbsp; </label><label -->
															<!-- 																		for="exampleInputEmail1" class="text-danger"><a -->
															<%-- 																		href="${pageContext.request.contextPath}/downloadDemoFilePg.do?id=cashing" --%>
															<!-- 																		style="text-decoration: none; color: #d9534f;">Click -->
															<!-- 																			here..</a></label> -->
															<!-- 																</div> -->
															<!-- 															</div> -->

														</div>
													</div>
												</div>
											</section>
										</div>
									</div>
								</div>
							</div>


						</div>
					</form:form>
				</div>
			</div>
		</div>

	</section>
	<section class="section" style="margin-top: -25px;">
		<div class=" row sameheight-container">
			<div class="col-md-12">
				<div class="card card-block">
					<div class="subtitle-block">
						<h3 class="subtitle">Save Imported Record to Database</h3>
					</div>
					<div class="table-flip-scroll">
						<display:table
							class="table table-bordered table-hover flip-content"
							export="false" name="ppaDataList" pagesize="31" defaultsort="1"
							id="ppaDataList">
							<display:column property="mbShopNo" title="BranchId"
								sortable="true" />
							<display:column property="mbOcsCode" title="BranchCode"
								sortable="true" />
							<display:column property="mbTradename" title="BranchName"
								sortable="true" />
							<display:column property="mbName" title="BranchName"
								sortable="true" />
						</display:table>
					</div>


					<div class="col-xs-12 " style="margin-top: 24px;">
						<section class="section pull-right">
							<a
								href="${pageContext.request.contextPath}/eschedule/searchPg.do"
								type="button" class="btn btn-secondary" tabindex="23">Discard</a>
							<c:if test="${not empty ppaDataList }">
								<a
									href="${pageContext.request.contextPath}/eschedule/importedAllDataPPAPg.do"
									type="button" class="btn btn-primary" tabindex="23">Save</a>
							</c:if>
							<c:if test="${ empty ppaDataList}">
								<button type="submit" class="btn btn-primary "
									disabled="disabled">Save</button>
							</c:if>

						</section>
					</div>

				</div>
			</div>
		</div>
	</section>

</article>
<script>
	$(function() {
		$("ul li").removeClass("active");
		$("#lieschedule").addClass("active");
		$('input[type=file]').change(
				function() {
					var val = $(this).val().toLowerCase(), regex = new RegExp(
							"(.*?)\.(dat)$");

					if (!(regex.test(val))) {
						$(this).val('');
						alert('Please select .dat file format');
					}
				});
	});
</script>