

$(function() {
	// $('[data-form=datepicker]').datepicker();

	$('.form_date').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 2,
		minView : 2,
		forceParse : 0
	});
	$('.form_time').datetimepicker({
		language : 'zh-CN',
		weekStart : 1,
		todayBtn : 1,
		autoclose : 1,
		todayHighlight : 1,
		startView : 1,
		minView : 0,
		maxView : 1,
		forceParse : 0
	});

	$.ajax({
		url : "../CheckIn/findReasonTypes",
		type : 'post',
		dataType : 'json',
		success : function(json) {
			$("#reasonType option").remove();
			for (var i = 0; i < json.length; i++) {
				$('#reasonType').append(
						"<option value='" + json[i].metaValue + "' >"
								+ json[i].metaValue + "</option>");
			}
		}
	});

	$("#addCheckIn_submit").click(
			function() {
				var employeeNo = $.trim($("#employeeNo").val());
				var checkNo = $.trim($("#checkNo").val());
				var beginTime = $.trim($("#beginTime").val());
				var endTime = $.trim($("#endTime").val());
				var beginDate = $.trim($("#beginDate").val());
				var endDate = $.trim($("#endDate").val());
				if (employeeNo == "" || employeeNo == null) {
					$('#employeeNo').parent().parent()
							.find('#employeeNo_Lable').text("员工编号不能为空！").show(
									'normal');
					$('#addCheckIn_form').removeAttr('action');

				}
				if(isNaN(employeeNo)){
					$('#employeeNo').parent().parent()
					.find('#employeeNo_Lable').text("请输入数字！").show(
							'normal');
					$('#addCheckIn_form').removeAttr('action');
				}
				if (checkNo == "" || checkNo == null) {
					$('#checkNo').parent().parent().find('#checkNo_Lable')
							.text("考勤编号不能为空！").show('normal');
					$('#addCheckIn_form').removeAttr('action');
				}
				if(isNaN(checkNo)){
					$('#employeeNo').parent().parent()
					.find('#employeeNo_Lable').text("请输入数字！").show(
							'normal');
					$('#addCheckIn_form').removeAttr('action');
				}
				if (beginTime == "" || beginTime == null) {
					$('#beginTime').parent().parent().find('#beginTime_Lable')
							.text("开始时间不能为空！").show('normal');
					$('#addCheckIn_form').removeAttr('action');
				}
				if (endTime == "" || endTime == null) {
					$('#endTime').parent().parent().find('#endTime_Lable')
							.text("结束时间不能为空！").show('normal');
					$('#addCheckIn_form').removeAttr('action');
				}
				if (beginDate == "" || beginDate == null) {
					$('#beginDate').parent().parent().find('#beginDate_Lable')
							.text("开始日期不能为空！").show('normal');
					$('#addCheckIn_form').removeAttr('action');
				}
				if (endDate == "" || endDate == null) {
					$('#endDate').parent().parent().find('#endDate_Lable')
							.text("结束日期不能为空！").show('normal');
					$('#addCheckIn_form').removeAttr('action');
				} else {
					$('#addCheckIn_form').removeAttr('action');
					$('#addCheckIn_form').attr('action',
							'/RestHome/CheckIn/addCheckIn');
					$('#addCheckIn_form').submit();
				}
			});
});