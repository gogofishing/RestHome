 
$(function(){
	//$('[data-form=datepicker]').datepicker();
	$('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
    });
	$('.form_time').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 1,
		minView: 0,
		maxView: 1,
		forceParse: 0
    });
	
	//var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var mY_url=window.location.search;
	mY_url=mY_url.substring(1, (mY_url.length) );
	var arr_url=mY_url.split("=");
	
	$.ajax({
		url:'../CheckIn/getCheckInByUuid',
		type:'post',
		data:{
			'checkIn.uuid':arr_url[1],			
		},
		dataType:'json',
		success:function(json){			
			var cVo=json.cVo;
			var sysms=json.sysms;
						
			$("#checkInUuid").attr('value',cVo.uuid);
			$("#employeeNo").attr('value',cVo.empNo);
			$("#employeeName").attr('value',cVo.empName);
			$("#checkNo").attr('value',cVo.checkNo);
			$("#beginTime").attr('value',cVo.beginTime);
			$("#endTime").attr('value',cVo.endTime);
			$("#beginDate").attr('value',cVo.beginDate);
			$("#endDate").attr('value',cVo.endDate);
			$("#tiemLength").attr('value',cVo.tiemLength);
			$("#reson").attr('value',cVo.reson);
			
			$("#reasonType option").remove();
			for(var i = 0; i<sysms.length; i++){		
				$('#reasonType').append("<option value='"+sysms[i].metaValue+"' >"+sysms[i].metaValue+"</option>");
			}
		}
		
		});
}
);