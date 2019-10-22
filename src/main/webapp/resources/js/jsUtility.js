function GetPhoneFormat(id) {

	var str = document.getElementById(id).value;
	if (str.length == 4) {
		var ind = str.substring(0, 4);
		document.getElementById(id).value = ind + ' ';
	}

}

function GetPostCodeFormat(id) {
	var str = document.getElementById(id).value;
	document.getElementById(id).value = str.toUpperCase();
	/*
	 * if (str.length == 7) { var ind = str.substring(0, 4);
	 * document.getElementById(id).value = ind + ' '; }
	 */

}

function isNumberKey(element, event) {

	var key = window.event ? event.keyCode : event.which;
	var len1 = element.value;

	if (key == 0) {
		return true;
	}

	if (len1.length == 0) {
		if (key == 46) {
			return false;
		}
	}

	if (event.keyCode === 8 || event.keyCode === 37 || event.keyCode === 39
			|| event.keyCode === 45) {
		return true;
	} else if (key > 31 && ((key < 48) || key > 57)
			&& !(key == 46 || key == 8 || key == 45)) {
		return false;
	} else {

		var len = $(element).val().length;
		var index = $(element).val().indexOf('.');

		if (index > 0 && key == 46) {
			return false;
		}
		if (index > 0) {
			var CharAfterdot = (len + 1) - index;
			if (CharAfterdot > 3) {
				return false;
			}
		}
	}

	return true;
}

function isNumber(element, event) {
	var key = window.event ? event.keyCode : event.which;
	var len1 = element.value;

	if (key == 46) {
		return false;
	}

	if (event.keyCode === 8 || event.keyCode === 37 || event.keyCode === 39
			|| event.keyCode === 45) {
		return true;
	} else if (key > 31 && ((key < 48) || key > 57)
			&& !(key == 46 || key == 8 || key == 45)) {
		return false;
	}

	return true;
}
function checkdateRange() {
	if ($('#frm').val() == '') {
		$('#frm').attr('required', 'required');
		$('#to').attr('required', 'required');
		$('#frm').val('');

		return false;
	}
	if ($('#frm').val() > $('#to').val()) {

		$('#to').attr('required', 'required');
		$('#to').val('');
		return false;
	}
}
function checkdateRangeSearch() {
	if ($('#frmDateSearch').val() == '') {
		$('#frmDateSearch').attr('required', 'required');
		$('#toDateSearch').attr('required', 'required');
		$('#frmDateSearch').val('');

		return false;
	}
	if ($('#frmDateSearch').val() > $('#toDateSearch').val()) {

		$('#toDateSearch').attr('required', 'required');
		$('#toDateSearch').val('');
		return false;
	}
}

try {
	var options = {
		segmentShowStroke : false,
		animateScale : true,
		responsive : true,
		legend : {
			display : false,
		},
		tooltips : {
			enabled : true,
		},
		scales : {
			yAxes : [ {
				stacked : true,
				gridLines : {
					display : true
				// false : hide gridlLines
				},
			} ],

			xAxes : [ {
				display : true,
				stacked : true,
				gridLines : {
					display : true
				}
			} ]
		}
	};

} catch (e) {
	alert(e);
}

var dailyChart;
function renderNChart(gid, bid) {

	var canvas = document.getElementById('NChartArea');

	var ctx = canvas.getContext('2d');

	if (dailyChart != undefined || dailyChart != null) { // to clear old
		// chart from canvas
		dailyChart.destroy();
	}

	$
			.ajax({
				dataType : 'json',
				type : "GET",
				data : {
					gid : gid,
					bid : bid
				},
				contentType : 'application/json; charset=utf-8',
				url : "nhsChartDailyData.do",
				success : function(result) {
					console.log(result);

					// $('#dChartDiv').html(result);

					dailyChart = new Chart(ctx, {
						type : 'bar',
						data : result,
						options : options
					});

					$("#NChartArea")
							.click(
									function(e) {
										try {
											var activePoints = dailyChart
													.getElementAtEvent(e);
											var clickedElementindex = activePoints[0]["_index"];

											// get specific label by index
											var label = dailyChart.data.labels[clickedElementindex];

											// get value by index
											var value = dailyChart.data.datasets[0].data[clickedElementindex];

											document
													.getElementById("chartValue").value = label;
											document
													.getElementById("chartTypeId").value = 'DAILY';
											document.getElementById(
													"chartSearchId").submit();

										} catch (ee) {
										}

									});
				},
				complete : function(result) {
					renderTChart(gid, bid);
				}
			});
}
var dailytChart;
function renderTChart(gid, bid) {

	var canvas = document.getElementById('TChartArea');

	var ctx = canvas.getContext('2d');

	if (dailytChart != undefined || dailytChart != null) { // to clear old
		// chart from canvas
		dailytChart.destroy();
	}

	$
			.ajax({
				dataType : 'json',
				type : "GET",
				data : {
					gid : gid,
					bid : bid
				},
				contentType : 'application/json; charset=utf-8',
				url : "takingChartDailyData.do",
				success : function(result) {
					console.log(result);

					// $('#dChartDiv').html(result);

					dailytChart = new Chart(ctx, {
						type : 'bar',
						data : result,
						options : options
					});

					$("#TChartArea")
							.click(
									function(e) {
										try {
											var activePoints = dailytChart
													.getElementAtEvent(e);
											var clickedElementindex = activePoints[0]["_index"];

											// get specific label by index
											var label = dailytChart.data.labels[clickedElementindex];

											// get value by index
											var value = dailytChart.data.datasets[0].data[clickedElementindex];

											document
													.getElementById("chartValue").value = label;
											document
													.getElementById("chartTypeId").value = 'DAILY';
											document.getElementById(
													"chartSearchId").submit();

										} catch (ee) {
										}

									});
				}
			});
}

$(document).ready(
		function() {
			$("form").bind(
					"keypress",
					function(e) {
						if (e.keyCode == 13) {
							var tab = document.activeElement.tabIndex;
							if (tab == 21 || tab == 15 || tab == 16
									|| tab == 17 || tab == 18 || tab == 19
									|| tab == 20) {
								return true;
							} else {
								return false;
							}

						}
					});
			$('.rowClick table tr').click(
					function() {
						if (!$(this).hasClass("total"))
							if ($(this).find("td:eq(0)").children("a").attr(
									"href") != null)
								window.location = $(this).find("td:eq(0)")
										.children("a").attr("href");
					});
			//For Insert Date 
			if ($('#msg').val() != 1){
			$('#frm').datepicker({
				format : 'dd/mm/yyyy',
				weekStart: 1,
				autoclose: true,
				daysOfWeekDisabled: "0",
				daysOfWeekHighlighted: "6",
				todayHighlight: true,
				
			}).on('changeDate', function() {
				checkBranchWorkingDayOrNoByAjax(this) 
	        });
			$("#frm").on('click', function() {
				$("#frm").datepicker("show");
			});
		}
			$('#to').datepicker({
				format : 'dd/mm/yyyy',
				weekStart: 1,
				autoclose: true,
				daysOfWeekDisabled: "0",
				daysOfWeekHighlighted: "6",
				todayHighlight: true,
				
			});
			$("#to").on('click', function() {
				$("#to").datepicker("show");
			});
			//------------------------------End
			//For Search 
			
			$('#frmDateSearch').datepicker({
				format : 'dd/mm/yyyy',
				weekStart: 1,
				autoclose: true,
				daysOfWeekDisabled: "0",
				daysOfWeekHighlighted: "6",
				todayHighlight: true,
				
			});
			$("#frmDateSearch").on('click', function() {
				$("#frmDateSearch").datepicker("show");
			});
			$('#toDateSearch').datepicker({
				format : 'dd/mm/yyyy',
				weekStart: 1,
				autoclose: true,
				daysOfWeekDisabled: "0",
				daysOfWeekHighlighted: "6",
				todayHighlight: true,
				
			});
			$("#toDateSearch").on('click', function() {
				$("#toDateSearch").datepicker("show");
			});
			//------------------------------End
		});
function checkBranchWorkingDayOrNoByAjax(element) {
	var contextPath = document.getElementById("contextPath").value;
	var todaydate = new Date();
	 var day = todaydate.getDate();
	 var month = todaydate.getMonth() + 1;
	 var year = todaydate.getFullYear();
	 var datestring = day + "/" + month + "/" + year;
	$.ajax({
		url : contextPath+"/branch/checkBranchWorkingDayOrNo.do",
		type : 'GET',
		data : {'eventDate' : element.value},
		success : function(data) {
			if (data == "true") {
				alert("PLEASE NOTE THAT SATURDAY DATA INPUT WILL BE ACTIVE ON MONDAY MORNING FOR SUBMISSION.");
				$("#frm").val(datestring);
			}
		},
		complete : function(data){
			if (data == "true") {
				$("#frm").val(datestring);
			}
		}
	});
	
}

