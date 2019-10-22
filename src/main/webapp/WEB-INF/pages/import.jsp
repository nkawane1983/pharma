
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<article class="content dashboard-page">
	<section class="section">
		<div class="row sameheight-container">

			<div class="col-md-12">
				<div class="card card-block ">
					<div class="subtitle-block">
						<h3 class="subtitle">Import File</h3>
					</div>
					<c:if test="${not empty msg}">
						<div class="col-md-12" style="text-align: center;">
							<label for="exampleInputEmail1" class="text-danger">${msg}
							</label>
						</div>
					</c:if>
					<!-- ------------------------------Cashing------------------------ -->
					<form:form	action="${pageContext.request.contextPath}/uploadFilePg.do"
						method="post" enctype="multipart/form-data">
						<div class="col-md-12">
							<div class="tab-content">
								<div id="accordion" class="tab-pane fade in active"
									style="margin-top: 10px;">
									<div class="list-group panel " id="accordion">

										<a href="#cashing" class="list-group-item"
											data-toggle="collapse" data-parent="#accordion"
											onclick="onchangeclass(this)"
											style="text-decoration: none; border-top-right-radius: 0rem;"
											title="Please Click here?">Cashing Data<i
											class="fa fa-minus pull-right" id="icn"
											title="Please Click here?"></i></a>
										<div class="panel-collapse collapse in" id="cashing"
											style="border: 1px solid #ddd;">
											<section class="section">
												<div class="row sameheight-container">
													<div class="card-block ">
														<div class="col-md-12">
															<div class="col-sm-12">
																<div class="col-sm-4 col-xs-12">
																	<label for="exampleInputEmail1"></label> <label
																		for="exampleInputEmail1">Drop files or click
																		here.<span class="text-danger">*</span>
																	</label> <input type="File" class="form-control" id="file"
																		placeholder="Enter File Version" name="excelfile"
																		required> <input type="hidden"
																		class="form-control" name="filetype" value="cashing"
																		required>
																</div>
																<div class="col-sm-4 col-xs-12"
																	style="margin-top: 24px;">
																	<button type="Reset" class="btn btn-secondary">Reset</button>
																	<button type="submit" class="btn btn-primary ">Upload</button>
																</div>
															</div>
															<div class="col-sm-12">
																<div class="col-sm-12 col-xs-12"
																	style="margin-top: 22px;">
																	<label for="exampleInputEmail1" class="text-danger">Note:-
																	</label><label for="exampleInputEmail1">Don't know
																		about Excel Format Please Download.&nbsp;->&nbsp; </label><label
																		for="exampleInputEmail1" class="text-danger"><a
																		href="${pageContext.request.contextPath}/downloadDemoFilePg.do?id=cashing"
																		style="text-decoration: none; color: #d9534f;">Click
																			here..</a></label>
																</div>
															</div>

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
					<form:form
						action="${pageContext.request.contextPath}/uploadFilePg.do"
						method="post" enctype="multipart/form-data">
						<!-- ------------------------------NHS----------------------- -->
						<div class="col-md-12">
							<div class="tab-content">
								<div id="accordion" class="tab-pane fade in active"
									style="margin-top: 10px;">
									<div class="list-group panel " id="accordion">

										<a href="#nhs" class="list-group-item" data-toggle="collapse"
											data-parent="#accordion" onclick="onchangeclass(this)"
											style="text-decoration: none; border-top-right-radius: 0rem;"
											title="Please Click here?">NHS Data<i
											class="fa fa-plus pull-right" id="icn"
											title="Please Click here?"></i></a>
										<div class="panel-collapse collapse" id="nhs"
											style="border: 1px solid #ddd;">
											<section class="section">
												<div class="row sameheight-container">
													<div class="card-block ">
														<div class="col-md-12">
															<div class="col-sm-12">
																<div class="col-sm-4 col-xs-12">
																	<label for="exampleInputEmail1"></label> <label
																		for="exampleInputEmail1">Drop files or click
																		here.<span class="text-danger">*</span>
																	</label> <input type="File" class="form-control" id="file"
																		placeholder="Enter File Version" name="excelfile"
																		required> <input type="hidden"
																		class="form-control" name="filetype" value="nhs"
																		required>
																</div>
																<div class="col-sm-4 col-xs-12"
																	style="margin-top: 24px;">
																	<button type="Reset" class="btn btn-secondary">Reset</button>
																	<button type="submit" class="btn btn-primary ">Upload</button>
																</div>
															</div>
															<div class="col-sm-12">
																<div class="col-sm-12 col-xs-12"
																	style="margin-top: 22px;">
																	<label for="exampleInputEmail1" class="text-danger">Note:-
																	</label><label for="exampleInputEmail1">Don't know
																		about Excel Format Please Download.&nbsp;->&nbsp; </label><label
																		for="exampleInputEmail1" class="text-danger"><a
																		href="${pageContext.request.contextPath}/downloadDemoFilePg.do?id=nhs"
																		style="text-decoration: none; color: #d9534f;">Click
																			here..</a></label>
																</div>
															</div>

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
					<!-- - -->

					<!--  -->

				</div>

			</div>

		</div>

	</section>
</article>
<script>
	$(function() {

	});

	function onchangeclass(str) {

		var icon = $(str).parent().find('#icn');

		if (icon.hasClass("fa-plus")) {
			icon.addClass("fa-minus").removeClass("fa-plus");
		} else {
			icon.addClass("fa-plus").removeClass("fa-minus");
		}

	}
</script>