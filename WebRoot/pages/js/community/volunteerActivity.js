$(function() {
	$('[data-form=datepicker]').datepicker();
	var pageNow = 1;
	
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
		url : "/RestHome/volunteerActivity/findActivityTypes",
		type : 'post',
		dataType : 'json',
		success : function(json) {
			$("#vltActivity_type option").remove();
			for (var i = 0; i < json.length; i++) {
				$('#vltActivity_type').append(
						"<option value='" + json[i].metaValue + "' >"
								+ json[i].metaValue + "</option>");
			}
		}
	});
	
	
	$("#vltActivity_search").click(function(){
		var volunteerNo=$.trim($("#vltActivity_volunteerNo").val());
		var startDate=$.trim($("#startDate").val());
		var endDate=$.trim($("#endDate").val());
		findVolunteerActivityByPage(volunteerNo,startDate,endDate);
	});	
	
	$("#vltActivity_add").click(function(){		
		$("#vltActivity_table_toolbar").hide("normal");
		$("#vltActivity_form_toolbar").show("normal");	
		$('#vltActivity_modify').hide("fast");
		$('#vltActivity_submit').show("fast");
	});
	
	$("#vltActivity_cancel").click(function(){
		$("#vltActivity_table_toolbar").show("normal");
		$("#vltActivity_form_toolbar").hide("normal");	
	});
	
	$("#vltActivity_modify").click(function(){
		var vltActivity_vno=$.trim($('#vltActivity_vno').val());
		var vltActivity_ono=$.trim($('#vltActivity_ono').val());
		var vltActivity_content=$.trim($('#vltActivity_content').val());
		var vltActivity_place=$.trim($('#vltActivity_place').val());
		var vltActivity_startTime=$.trim($('#vltActivity_startTime').val());
		var vltActivity_endTime=$.trim($('#vltActivity_endTime').val());
		var vltActivity_startDate=$.trim($('#vltActivity_startDate').val());
		var vltActivity_endDate=$.trim($('#vltActivity_endDate').val());
		var vltActivity_hours=$.trim($('#vltActivity_hours').val());
		var vltActivity_empNo=$.trim($('#vltActivity_empNo').val());
		var vltActivity_empName=$.trim($('#vltActivity_empName').val());
		
		if(isNull(vltActivity_vno)){
			showMessage("志愿者编号不能为空!",$('#vltActivity_vno_label'));
		}else if(isNull(vltActivity_ono)){
			showMessage("老人编号不能为空!",$('#vltActivity_ono_label'));
		}else if(isNull(vltActivity_content)){
			showMessage("活动内容不能为空!",$('#vltActivity_content_label'));
		}else if(isNull(vltActivity_place)){
			showMessage("活动地点不能为空!",$('#vltActivity_place_label'));
		}else if(isNull(vltActivity_startTime)){
			showMessage("活动开始时间不能为空!",$('#vltActivity_startTime_label'));
		}else if(isNull(vltActivity_endTime)){
			showMessage("活动结束时间不能为空!",$('#vltActivity_endTime_label'));
		}else if(isNull(vltActivity_startDate)){
			showMessage("活动开始日期不能为空!",$('#vltActivity_startDate_label'));
		}else if(isNull(vltActivity_endDate)){
			showMessage("活动结束日期不能为空!",$('#vltActivity_endDate_label'));
		}else if(vltActivity_endDate<vltActivity_startDate){
			showMessage("活动结束日期不能小于活动开始日期!",$('#vltActivity_endDate_label'));
		}else if(isNull(vltActivity_hours)){
			showMessage("活动时长不能为空!",$('#vltActivity_hours_label'));
		}else if(!/^[0-9]*$/.test(vltActivity_hours)){
			showMessage("活动时长必须为正整数，请重新输入!",$('#vltActivity_hours_label'));
		}else if(isNull(vltActivity_empNo)){
			showMessage("活动负责人编号不能为空!",$('#vltActivity_empNo_label'));
		}else{
			$('#vltActivity_form').removeAttr('action');
			$('#vltActivity_form').attr({'method':'post'});
			$('#vltActivity_form').attr({'action':'volunteerActivity/modifyVolunteerActivity'}).submit();
		}
		
	});
	
	$("#vltActivity_submit").click(function(){
		var vltActivity_vno=$.trim($('#vltActivity_vno').val());
		var vltActivity_ono=$.trim($('#vltActivity_ono').val());
		var vltActivity_content=$.trim($('#vltActivity_content').val());
		var vltActivity_place=$.trim($('#vltActivity_place').val());
		var vltActivity_startTime=$.trim($('#vltActivity_startTime').val());
		var vltActivity_endTime=$.trim($('#vltActivity_endTime').val());
		var vltActivity_startDate=$.trim($('#vltActivity_startDate').val());
		var vltActivity_endDate=$.trim($('#vltActivity_endDate').val());
		var vltActivity_hours=$.trim($('#vltActivity_hours').val());
		var vltActivity_empNo=$.trim($('#vltActivity_empNo').val());
		var vltActivity_empName=$.trim($('#vltActivity_empName').val());
		
		if(isNull(vltActivity_vno)){
			showMessage("志愿者编号不能为空!",$('#vltActivity_vno_label'));
		}else if(isNull(vltActivity_ono)){
			showMessage("老人编号不能为空!",$('#vltActivity_ono_label'));
		}else if(isNull(vltActivity_content)){
			showMessage("活动内容不能为空!",$('#vltActivity_content_label'));
		}else if(isNull(vltActivity_place)){
			showMessage("活动地点不能为空!",$('#vltActivity_place_label'));
		}else if(isNull(vltActivity_startTime)){
			showMessage("活动开始时间不能为空!",$('#vltActivity_startTime_label'));
		}else if(isNull(vltActivity_endTime)){
			showMessage("活动结束时间不能为空!",$('#vltActivity_endTime_label'));
		}else if(isNull(vltActivity_startDate)){
			showMessage("活动开始日期不能为空!",$('#vltActivity_startDate_label'));
		}else if(isNull(vltActivity_endDate)){
			showMessage("活动结束日期不能为空!",$('#vltActivity_endDate_label'));
		}else if(vltActivity_endDate<vltActivity_startDate){
			showMessage("活动结束日期不能小于活动开始日期!",$('#vltActivity_endDate_label'));
		}else if(isNull(vltActivity_hours)){
			showMessage("活动时长不能为空!",$('#vltActivity_hours_label'));
		}else if(!/^[0-9]*$/.test(vltActivity_hours)){
			showMessage("活动时长必须为正整数，请重新输入!",$('#vltActivity_hours_label'));
		}else if(isNull(vltActivity_empNo)){
			showMessage("活动负责人编号不能为空!",$('#vltActivity_empNo_label'));
		}else{
			$('#vltActivity_form').removeAttr('action');
			$('#vltActivity_form').attr({'method':'post'});
			$('#vltActivity_form').attr({'action':'volunteerActivity/addVolunteerActivity'}).submit();
		/*	$.ajax({
				url : '/RestHome/volunteerActivity/addVolunteerActivity',
				data : $('#vltActivity_form').serialize(),
				type : 'post',
				dataType : 'json',
				async:false,
				success : function(data) {
					var result = data;
					alert(result);
					if(result=="vltNull"){
						showMessage("志愿者编号不存在，请重新输入。",$('#vltActivity_vno_label'));
					}else if(result=="oldNull"){
						showMessage("老人编号不存在，请重新输入。",$('#vltActivity_ono_label'));
					}else if(result=="empNull"){
						showMessage("员工编号不存在，请重新输入。",$('#vltActivity_empNo_label'));
					}else if(result=="success"){
						showMessage("添加成功！！！",$('#vltActivity_showMessage'));
						findVolunteerActivityByPage();
					}else{
						location.href = "pages/500.jsp";
					}
					$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
				},
				error:function(){
					alert("error");
					location.href = "pages/500.jsp";
				}
				
			});*/
		}
	});
	
	
	
	var findVolunteerActivityByPage=function(volunteerNo,startDate,endDate){
		
		$.ajax({
			url : '/RestHome/volunteerActivity/findVolunteerActivityByPage',
			data :{
				'pageVo.nowPage' : pageNow,			
				'volunteer.volunteerNo':volunteerNo,
				'vltActivity.beginDate':startDate,
				'vltActivity.endDate':endDate,
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {	
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;	
				
				$("#vltActivity_table").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#vltActivity_paging').find('ul').find('li').remove();
				
				for(var i=0;i<data.length;i++){
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].vltNo+"</td>"+
					"<td>"+data[i].vltName+"</td>"+
					"<td>"+data[i].oldNo+"</td>"+
					"<td>"+data[i].oldName+"</td>"+
					"<td>"+data[i].activityType+"</td>"+						
					"<td>"+data[i].hours+"</td>"+	
					"<td>"+data[i].empName+"</td>"+	
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
					"<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>"+					
					"</tr>";
					var clickTr = $("#vltActivity_table").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(8).children().eq(0).click(function(){
						if(!confirm("确定删除这个活动？")){
							return false;
						}					
						deleteVolunteerActivity(this);
					});	
					clickTr.children().eq(9).children().eq(0).click(function(){
						modifyVolunteerActivity(this);
					});
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#vltActivity_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#vltActivity_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#vltActivity_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#vltActivity_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#vltActivity_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#vltActivity_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#vltActivity_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {													
							var page = $.trim($(this).text());
							var volunteerNo=$.trim($("#vltActivity_volunteerNo").val());
							var startDate=$.trim($("#vltActivity_startDate").val());
							var endDate=$.trim($("#vltActivity_endDate").val());
							pageNow = page;
							findVolunteerActivityByPage(volunteerNo,startDate,endDate);
						});
				
				$('#prev').click(function() {
					var volunteerNo=$.trim($("#vltActivity_volunteerNo").val());
					var startDate=$.trim($("#vltActivity_startDate").val());
					var endDate=$.trim($("#vltActivity_endDate").val());		
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findVolunteerActivityByPage(volunteerNo,startDate,endDate);
					}						
				});
				
				$('#next').click(function() {
					var volunteerNo=$.trim($("#vltActivity_volunteerNo").val());
					var startDate=$.trim($("#vltActivity_startDate").val());
					var endDate=$.trim($("#vltActivity_endDate").val());				
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findVolunteerActivityByPage(volunteerNo,startDate,endDate);
					}																														
			});
			}
		});
			
	}


	findVolunteerActivityByPage();
	
	
});



var deleteVolunteerActivity=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/volunteerActivity/delVolunteerActivity',
		type:'post',
		data:{
			'vltActivity.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();
				
				var trNum = $("#vltActivity_table").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="/RestHome/pages/VolunteerActivity.jsp";
				}			
			}
		}
	});
}


var modifyVolunteerActivity=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({		
		url:'/RestHome/volunteerActivity/getVolunteerActivityByUuid',
		type:'post',
		data:{
			'vltActivity.uuid':uuid,			
		},
		dataType:'text',
		success:function(json){	
			var vaVo=$.parseJSON(json);
			$("#vltActivity_uuid").attr("value",vaVo.uuid);
			$("#vltActivity_vno").attr("value",vaVo.vltNo);
			$("#vltActivity_vname").attr("value",vaVo.vltName);
			$("#vltActivity_ono").attr("value",vaVo.oldNo);			
			$("#vltActivity_oname").attr("value",vaVo.oldName);
			$("#vltActivity_type").val(vaVo.activityType);
			$("#vltActivity_content").val(vaVo.activityContent);
			$("#vltActivity_place").attr("value",vaVo.activityPlace);
			$("#vltActivity_startTime").attr("value",vaVo.startTime);
			$("#vltActivity_endTime").attr("value",vaVo.endTime);
			$("#vltActivity_startDate").attr("value",vaVo.beginDate);
			$("#vltActivity_endDate").attr("value",vaVo.endDate);
			$("#vltActivity_hours").attr("value",vaVo.hours);
			$("#vltActivity_empNo").attr("value",vaVo.empNo);
			$("#vltActivity_empName").attr("value",vaVo.empName);
			$('#vltActivity_modify').show("fast");
			$('#vltActivity_submit').hide("fast");
			$("#vltActivity_table_toolbar").hide("normal");
			$("#vltActivity_form_toolbar").show("normal");	
		},
		error:function(){
			window.href="pages/500.jsp";
		}
	});
}