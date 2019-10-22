<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<style>
.btnwidth {
	width: 209px
}

.dropdownGroup {
	left: auto;
	min-width: 13rem;
	height: 300px;
	overflow: auto;
	border: 1px #373a3c solid;
}

.dropdownBranch {
	left: auto;
	min-width: 20rem;
	height: 300px;
	overflow: auto;
	border: 1px #373a3c solid;
}

.dropdownGroupItems {
	display: block;
	clear: both;
	font-weight: 400;
	color: #4f5f6f;
	white-space: nowrap;
	-webkit-transition: none;
	transition: none;
	padding: 3px 15px;
}
</style>
<section class="section">
	<div class="row sameheight-container">
		<div class="col-md-12">
			<div class="card card-block sameheight-item" data-exclude="xs,sm,lg">
				<div class="title-block">
					<h3 class="title">${reportTitle}</h3>
				</div>
				<form class="form-inline" role="form"
					action="${pageContext.request.contextPath}/report/${reportUrl}"
					method="post">
					<div class="form-group col-xs-3">
						<label><b>Groups</b></label>
						<!-- START button group-->
						<div class="btn-group ">
							<button type="button"
								class="btn btn-secondary dropdown-toggle btnwidth"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" id="groupBtn">Select Group</button>
							<div class="dropdown-menu dropdownGroup " >
							<span class="dropdownGroupItems"><input type="checkbox"
										name="allGroupidBox" id="allGroupidBox"
										value="0"
										onclick="selectAllOrDselect('allGroupidBox','groupidBox','groupid')"><b>&nbsp;Select All Groups</b></span>
							<div id="groupSelect"> 
								<c:forEach items="${groupDetails}" var="groupDetails">
									<span class="dropdownGroupItems"><input type="checkbox"
										name="groupidBox" id="${groupDetails.getGroupId()}"
										value="${groupDetails.getGroupId()}"
										onclick="selectedGroup(this)">&nbsp;${groupDetails.getGroupName()}</span>
								</c:forEach>
								</div>
							</div>
							<input type="hidden" name="groupid" id="groupid" value="${groupid}"/>
						</div>

					</div>
					<div class="form-group col-xs-3">
						<label><b>Branches</b></label>

						<div class="btn-group">
							<button type="button"
								class="btn btn-secondary dropdown-toggle btnwidth"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" id="branchBtn">Select Branch</button>
							<div class="dropdown-menu dropdownBranch " >
							<span class="dropdownGroupItems"><input type="checkbox"
										name="allBranchidBox" id="allBranchidBox"
										value="0"
										onclick="selectAllOrDselect('allBranchidBox','branchidBox','branchid')"><b>&nbsp;Select All Branches</b></span>
							<div id="branchlist">
								<c:forEach items="${branchASlist}" var="branchlist">
									<span class="dropdownGroupItems"><input type="checkbox"
										name="branchidBox" id="${branchlist.getBrnachid()}"
										value="${branchlist.getBrnachid()}" onclick="selectedBranch(this)">&nbsp;${branchlist.getBranchName()}</span>
								</c:forEach></div>
								<input type="hidden" name="branchid" id="branchid" value="${branchid}"/>
							</div>
						</div>
					</div>
					<div class="form-group col-xs-2">
						<fmt:parseDate pattern="yyyy-MM-dd" value="${fdate}"
							var="parsedDate" />
						<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy"
							var="fdate" />

						<label><b>Start Date</b></label> <input class="form-control"
							id="frmDateSearch" placeholder="DD/MM/YYYY" type="text"
							name="sDate" onchange="checkdateRangeSearch()" value="${fdate}">

					</div>
					<div class="form-group col-xs-2">
						<fmt:parseDate pattern="yyyy-MM-dd" value="${enddate}"
							var="parsedDate" />
						<fmt:formatDate value="${parsedDate}" pattern="dd/MM/yyyy"
							var="edate" />
						<label><b>End Date</b></label> <input class="form-control"
							id="toDateSearch" placeholder="DD/MM/YYYY" type="text"
							name="endDate" onchange="checkdateRangeSearch()" value="${edate}">
					</div>
					<div class="form-group col-xs-2">
						<br>
						<button type="submit" class="btn btn-primary" data-toggle="modal">Search</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</section>

<input type="hidden" id="contextPath"
	value="${pageContext.request.contextPath}">
<script type="text/javascript">
	$(function() {

		//$("#groupid").val($("#gid").val());
		//$("#branchid").val($("#bid").val());
		var selectedgroup= []; 
			selectedgroup=$("#groupid").val().split(",");
			setcolorGroup(selectedgroup);
			selectingGroup(selectedgroup);
			getBranchesList($("#groupid").val());
			
		var selectedbranch=[];
		selectedbranch=$("#branchid").val().split(",");
		setcolorBranch(selectedbranch);
		selectingbranch(selectedbranch);
		buttonVisible();
		//selectAllOrDselect('allGroupidBox','groupidBox',selectedgroup);
	});
	function selectingGroup(str) 
	{
		for(var i=0;i< str.length;i++)
			{
			$('#groupSelect :input[name=groupidBox]').each(function() {
				if (this.id == str[i]) {
					$(this).prop('checked', true);
				}
			});
			}
		var strobj=document.getElementsByName('groupidBox');
		if(str.length==strobj.length || $("#groupid").val()==0){
			$("input[name=allGroupidBox]").prop('checked', true);
			$("input[name=groupidBox]").prop('checked', true);
		}else
			{
			$("input[name=allGroupidBox]").prop('checked', false);
			}
	}
	function selectedGroup(str) {
		var selected = [];
		if ($(str).prop("checked") == true) {
			$('#groupSelect :input[name=groupidBox]').each(function() {
				if (this.id == str.id) {
					$(this).prop('checked', true);
				}
			});
		}
		$('#groupSelect :input[name=groupidBox]').each(function() {
			if ($(this).prop("checked") == true) {
				selected.push($(this).val());
			}
		});

		$("#groupid").val(selected);
		setcolorGroup(selected);
		getBranchesList($("#groupid").val());
		var strobj=document.getElementsByName('groupidBox');
		//alert(strid.length);
		//alert(strobj.length);
		if(selected.length==strobj.length || $("#groupid").val()==0){
			$("input[name=allGroupidBox]").prop('checked', true);
		}else
			{
			$("input[name=allGroupidBox]").prop('checked', false);
			}
	}
	function setcolorGroup(Groupval) {
	//	alert(Groupval)
		if (Groupval.length > 0) {
			if(Groupval=='0'){
				$("#groupBtn").text("All Group Selected");
				//$("#branchBtn").removeAttr("data-toggle");	
			}else{
				$("#groupBtn").text("(" + Groupval.length + ") Group Selected  ");
				$("#branchBtn").attr("data-toggle","dropdown");
			}
			$("#groupBtn").css("border-color", "#52BCD3");
		} else {
			$("#groupBtn").text("All Group Selected");
			$("#groupBtn").css("border-color", "#d7dde4");
			$("#groupid").val("0")
			$("#branchBtn").removeAttr("data-toggle");	
		}
		buttonVisible();
	}
	function getBranchesList(groupidVal) {
		if (groupidVal.length > 0) {
			$.ajax({
				url : $('#contextPath').val() + '/branch/getBranchesList',
				data : {
					id : groupidVal
				},
				async : false,
				success : function(data) {
					$("#branchlist").html(data);
				},
				complete : function(data) {
					var selectedbranch=[];
					selectedbranch=$("#branchid").val().split(",");
					setcolorBranch(selectedbranch);
					selectingbranch(selectedbranch);
				}
			});
		} else {
			$("#branchlist").html('');
		}
	}
	
	function selectedBranch(str) {
		var selected = [];
		if ($(str).prop("checked") == true) {
			$('#branchlist :input[name=branchidBox]').each(function() {
				if (this.id == str.id) {
					$(this).prop('checked', true);
				}
			});
		}
		$('#branchlist :input[name=branchidBox]').each(function() {
			if ($(this).prop("checked") == true) {
				selected.push($(this).val());
			}
		});

		$("#branchid").val(selected);
		setcolorBranch(selected);
		var strobj=document.getElementsByName('branchidBox');
		if(str.length==strobj.length || $("#branchid").val()==0){
			$("input[name=allBranchidBox]").prop('checked', true);
			$("input[name=branchidBox]").prop('checked', true);
		}else
			{
			$("input[name=allBranchidBox]").prop('checked', false);
			}
	}
	function setcolorBranch(branchval) {
		if (branchval.length > 0) {
			if(branchval=='0')
				$("#branchBtn").text("All Branch Selected");
			else
				$("#branchBtn").text("(" + branchval.length + ") Branch Selected");
			$("#branchBtn").css("border-color", "#52BCD3");
		} else {
			$("#branchBtn").text("All Branch Selected");
			$("#branchBtn").css("border-color", "#d7dde4");
			$("#branchid").val("0")
		}
	}
	function selectingbranch(str) 
	{
		for(var i=0;i< str.length;i++)
			{
			$('#branchlist :input[name=branchidBox]').each(function() {
				if (this.id == str[i]) {
					$(this).prop('checked', true);
				}
			});
			}
		var strobj=document.getElementsByName('branchidBox');
		if(str.length==strobj.length || $("#branchid").val()==0){
			$("input[name=allBranchidBox]").prop('checked', true);
			$("input[name=branchidBox]").prop('checked', true);
		}else
			{
			$("input[name=allBranchidBox]").prop('checked', false);
			}
	}
	function buttonVisible()
	{ 
		var groupidVal=$("#groupid").val();
		if(groupidVal.length != 1)
		{
			$('#xls_vat').css("display", "none");
			$('#xls_sage').css("display", "none");
		}
		
	}
	
	
 function selectAllOrDselect(strAll,str,strObj)
 {
				if ($("input[name="+strAll+"]").prop("checked") == true) {
					$("input[name="+str+"]").prop('checked', true);
					$("#"+strObj).val("0");
	
				} else {
					$('input[name='+str+']').prop('checked', false);
					$("#"+strObj).val("0");
	
				}
		
 }
</script>