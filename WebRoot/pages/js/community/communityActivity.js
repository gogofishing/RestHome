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
		url : "/RestHome/communityActivity/findActivityTypes",
		type : 'post',
		dataType : 'json',
		success : function(json) {
			$("#comActivity_type option").remove();
			for (var i = 0; i < json.length; i++) {
				$('#comActivity_type').append(
						"<option value='" + json[i].metaValue + "' >"
								+ json[i].metaValue + "</option>");
			}
		}
	});
	
	$("#comActivity_search").click(function(){
		var activityName=$.trim($("#activityName").val());
		var startDate=$.trim($("#startDate").val());
		var endDate=$.trim($("#endDate").val());
		findCommunityActivityByPage(activityName,startDate,endDate);
	});	
	
	$("#comActivity_add").click(function(){		
		$("#comActivity_table_toolbar").hide("normal");
		$("#comActivity_form_toolbar").show("normal");	
		$('#comActivity_modify').hide("fast");
		$('#comActivity_submit').show("fast");
	});
	
	$("#comActivity_cancel").click(function(){
		$("#comActivity_table_toolbar").show("normal");
		$("#comActivity_form_toolbar").hide("normal");	
	});
	
	
	$("#comActivity_modify").click(function(){
		var comActivity_activityName=$.trim($('#comActivity_activityName').val());
		var comActivity_content=$.trim($('#comActivity_content').val());
		var comActivity_place=$.trim($('#comActivity_place').val());
		var comActivity_startTime=$.trim($('#comActivity_startTime').val());
		var comActivity_endTime=$.trim($('#comActivity_endTime').val());
		var comActivity_startDate=$.trim($('#comActivity_startDate').val());
		var comActivity_endDate=$.trim($('#comActivity_endDate').val());
		var comActivity_number=$.trim($('#comActivity_number').val());
		var comActivity_empNo=$.trim($('#comActivity_empNo').val());
		
		if(isNull(comActivity_activityName)){
			showMessage("活动名称不能为空!",$('#comActivity_activityName_label'));
		}else if(isNull(comActivity_content)){
			showMessage("活动内容不能为空!",$('#comActivity_content_label'));
		}else if(isNull(comActivity_place)){
			showMessage("活动地点不能为空!",$('#comActivity_place_label'));
		}else if(isNull(comActivity_startTime)){
			showMessage("活动开始时间不能为空!",$('#comActivity_startTime_label'));
		}else if(isNull(comActivity_endTime)){
			showMessage("活动结束时间不能为空!",$('#comActivity_endTime_label'));
		}else if(isNull(comActivity_startDate)){
			showMessage("活动开始日期不能为空!",$('#comActivity_startDate_label'));
		}else if(isNull(comActivity_endDate)){
			showMessage("活动结束日期不能为空!",$('#comActivity_endDate_label'));
		}else if(comActivity_startDate>comActivity_endDate){
			showMessage("活动结束日期不能小于活动开始日期!",$('#comActivity_endDate_label'));
		}else if(isNull(comActivity_number)){
			showMessage("活动参加人数不能为空!",$('#comActivity_number_label'));
		}else if(!/^[0-9]*$/.test(comActivity_number)){
			showMessage("活动参加人数必须为正整数，请重新输入!",$('#comActivity_number_label'));
		}else if(isNull(comActivity_empNo)){
			showMessage("负责人编号不能为空!",$('#comActivity_empNo_label'));
		}else{
			$('#comActivity_form').removeAttr('action');
			$('#comActivity_form').attr({'method':'post'});
			$('#comActivity_form').attr({'action':'communityActivity/modifyCommunityActivity'}).submit();
		}
	});
	
	$("#comActivity_submit").click(function(){
		var comActivity_activityName=$.trim($('#comActivity_activityName').val());
		var comActivity_content=$.trim($('#comActivity_content').val());
		var comActivity_place=$.trim($('#comActivity_place').val());
		var comActivity_startTime=$.trim($('#comActivity_startTime').val());
		var comActivity_endTime=$.trim($('#comActivity_endTime').val());
		var comActivity_startDate=$.trim($('#comActivity_startDate').val());
		var comActivity_endDate=$.trim($('#comActivity_endDate').val());
		var comActivity_number=$.trim($('#comActivity_number').val());
		var comActivity_empNo=$.trim($('#comActivity_empNo').val());
		
		if(isNull(comActivity_activityName)){
			showMessage("活动名称不能为空!",$('#comActivity_activityName_label'));
		}else if(isNull(comActivity_content)){
			showMessage("活动内容不能为空!",$('#comActivity_content_label'));
		}else if(isNull(comActivity_place)){
			showMessage("活动地点不能为空!",$('#comActivity_place_label'));
		}else if(isNull(comActivity_startTime)){
			showMessage("活动开始时间不能为空!",$('#comActivity_startTime_label'));
		}else if(isNull(comActivity_endTime)){
			showMessage("活动结束时间不能为空!",$('#comActivity_endTime_label'));
		}else if(isNull(comActivity_startDate)){
			showMessage("活动开始日期不能为空!",$('#comActivity_startDate_label'));
		}else if(isNull(comActivity_endDate)){
			showMessage("活动结束日期不能为空!",$('#comActivity_endDate_label'));
		}else if(comActivity_startDate>comActivity_endDate){
			showMessage("活动结束日期不能小于活动开始日期!",$('#comActivity_endDate_label'));
		}else if(isNull(comActivity_number)){
			showMessage("活动参加人数不能为空!",$('#comActivity_number_label'));
		}else if(!/^[0-9]*$/.test(comActivity_number)){
			showMessage("活动参加人数必须为正整数，请重新输入!",$('#comActivity_number_label'));
		}else if(isNull(comActivity_empNo)){
			showMessage("负责人编号不能为空!",$('#comActivity_empNo_label'));
		}else{
			$('#comActivity_form').removeAttr('action');
			$('#comActivity_form').attr({'method':'post'});
			$('#comActivity_form').attr({'action':'communityActivity/addCommunityActivity'}).submit();
		}
	});
	
	
	
	var findCommunityActivityByPage=function(activityName,startDate,endDate){
		$.ajax({
			url : '/RestHome/communityActivity/findCommunityActivityByPage',
			data :{
				'pageVo.nowPage' : pageNow,			
				'comActivity.activityName':activityName,
				'comActivity.beginDate':startDate,
				'comActivity.endDate':endDate,
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {	
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;	
				
				$("#comActivity_table").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#comActivity_paging').find('ul').find('li').remove();
				
				for(var i=0;i<data.length;i++){
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].activityName+"</td>"+					
					"<td>"+data[i].activityType+"</td>"+						
					"<td>"+data[i].activityNumber+"</td>"+	
					"<td>"+data[i].empName+"</td>"+	
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
					"<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>"+					
					"</tr>";
					var clickTr = $("#comActivity_table").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(5).children().eq(0).click(function(){
						if(!confirm("确定删除这个活动？")){
							return false;
						}					
						deleteCommunityActivity(this);
					});	
					clickTr.children().eq(6).children().eq(0).click(function(){
						modifyCommunityActivity(this);
					});
				}
				
				
				// 写出分页条
				if (pageNow != 1) {
					$('#comActivity_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#comActivity_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#comActivity_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#comActivity_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#comActivity_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#comActivity_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#comActivity_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {													
							var page = $.trim($(this).text());
							var activityName=$.trim($("#activityName").val());
							var startDate=$.trim($("#vltActivity_startDate").val());
							var endDate=$.trim($("#vltActivity_endDate").val());
							pageNow = page;
							findCommunityActivityByPage(activityName,startDate,endDate);
						});
				
				$('#prev').click(function() {
					var activityName=$.trim($("#activityName").val());
					var startDate=$.trim($("#vltActivity_startDate").val());
					var endDate=$.trim($("#vltActivity_endDate").val());		
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findCommunityActivityByPage(activityName,startDate,endDate);
					}						
				});
				
				$('#next').click(function() {
					var activityName=$.trim($("#activityName").val());
					var startDate=$.trim($("#vltActivity_startDate").val());
					var endDate=$.trim($("#vltActivity_endDate").val());				
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
						pageNow++;
						findCommunityActivityByPage(activityName,startDate,endDate);
					}																														
			});
			}
		});
	}
	findCommunityActivityByPage();
	
});


var deleteCommunityActivity=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/communityActivity/delCommunityActivity',
		type:'post',
		data:{
			'comActivity.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();
				
				var trNum = $("#comActivity_table").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="/RestHome/pages/CommunityActivity.jsp";
				}			
			}
		}
	});
}

var modifyCommunityActivity=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({		
		url:'/RestHome/communityActivity/getCommunityActivity',
		type:'post',
		data:{
			'comActivity.uuid':uuid,			
		},
		dataType:'text',
		success:function(json){	
			var comVo=$.parseJSON(json);
			$("#comActivity_uuid").attr("value",comVo.uuid);
			$("#comActivity_activityName").attr("value",comVo.activityName);
			$("#comActivity_content").val(comVo.activityContent);			
			$("#comActivity_type").val(comVo.activityType);
			$("#comActivity_place").attr("value",comVo.activityPlace);
			$("#comActivity_startTime").attr("value",comVo.startTime);
			$("#comActivity_endTime").attr("value",comVo.endTime);
			$("#comActivity_startDate").attr("value",comVo.beginDate);
			$("#comActivity_endDate").attr("value",comVo.endDate);
			$("#comActivity_number").attr("value",comVo.activityNumber);
			$("#comActivity_empNo").attr("value",comVo.empNo);
			$("#comActivity_empName").attr("value",comVo.empName);
			$('#comActivity_modify').show("fast");
			$('#comActivity_submit').hide("fast");
			$("#comActivity_table_toolbar").hide("normal");
			$("#comActivity_form_toolbar").show("normal");	
		},
		error:function(){
			window.href="pages/500.jsp";
		}
	});
	
}
