 
$(function(){	
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
	
	
	$("#sousuo").click(function(){
		var empNo=$.trim($("#empNo").val());
		var startDate=$.trim($("#beginDate").val());
		var endDate=$.trim($("#endDate").val());
		findCheckInByPage(empNo,startDate,endDate);
	});
	
	
	
	var findCheckInByPage=function(empNo,startDate,endDate){
		$.ajax({
			url : '../CheckIn/findCheckInsByPage',
			data :{
				'pageVo.nowPage' : pageNow,			
				'checkIn.employee.employeeNo':empNo,
				'checkIn.beginDate':startDate,
				'checkIn.endDate':endDate
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;
				
				$("#datatablestools").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#checkIn_paging').find('ul').find('li').remove();
				for(var i=0;i<data.length;i++){
					
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].empNo+"</td>"+
					"<td>"+data[i].empName+"</td>"+
					"<td>"+data[i].checkNo+"</td>"+						
					"<td>"+data[i].tiemLength+"</td>"+				
					"<td>"+data[i].resonType+"</td>"+
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
					"<td><a class='modify' style='cursor:pointer' href='/RestHome/pages/ModifyCheckIn.jsp?checkIn.uuid="+data[i].uuid+"'><i class='icofont-edit'></i></a></td>"
					"</tr>";
					var clickTr = $("#datatablestools").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(6).children().eq(0).click(function(){
						if(!confirm("确定删除这个用户？")){
							return false;
						}					
						deleteCheckIn(this);
					});	
					clickTr.children().eq(7).children().eq(0).click(function(){
						modifyCheckIn(this);
					});
					
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#checkIn_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#checkIn_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#checkIn_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#checkIn_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#checkIn_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#checkIn_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#checkIn_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {
							var empNo=$.trim($("#empNo").val());
							var startDate=$.trim($("#beginDate").val());
							var endDate=$.trim($("#endDate").val());
							
							var page = $.trim($(this).text());															
							pageNow = page;
							findCheckInByPage(empNo,startDate,endDate);
						});
				
				$('#prev').click(function() {
					var empNo=$.trim($("#empNo").val());
					var startDate=$.trim($("#beginDate").val());
					var endDate=$.trim($("#endDate").val());
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findCheckInByPage(empNo,startDate,endDate);
					}						
				});
				
				$('#next').click(function() {
					var empNo=$.trim($("#empNo").val());
					var startDate=$.trim($("#beginDate").val());
					var endDate=$.trim($("#endDate").val());
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findCheckInByPage(empNo,startDate,endDate);
					}																														
			});
			}
		});
		
	}
	findCheckInByPage();
}
);



String.prototype.trim=function() {

    return this.replace(/(^\s*)|(\s*$)/g,'');
}


var deleteCheckIn = function(e){
	//var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'../CheckIn/delCheckIn',
		type:'post',
		data:{
			'checkIn.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				//alert(data);
				$(e).parent().parent().remove();
				
				var trNum = $("#datatablestools").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="../CheckIn/findCheckInsByPage?pageVo.nowPage="+tempNowpage;
				}
				
				
			}
		}
	});
}


var modifyCheckIn=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'../CheckIn/getCheckInByUuid',
		type:'post',
		data:{
			'checkIn.uuid':uuid,			
		},
		dataType:'text',
		success:function(json){			
			var checkIn=json.checkIn;
			var sysms=json.sysms;
						
			$("#checkInUuid").attr('value',checkIn.uuid);
			$("#employeeNo").attr('value',checkIn.employeeNo);
			$("#employeeName").attr('value',checkIn.employeeName);
			$("#checkNo").attr('value',checkIn.checkNo);
			$("#beginTime").attr('value',checkIn.beginTime);
			$("#endTime").attr('value',checkIn.endTime);
			$("#beginDate").attr('value',checkIn.beginDate);
			$("#endDate").attr('value',checkIn.endDate);
			$("#tiemLength").attr('value',checkIn.tiemLength);
			$("#reson").attr('value',checkIn.reson);
			
			$("#reasonType option").remove();
			for(var i = 0; i<sysms.length; i++){		
				$('#reasonType').append("<option value='"+sysms[i].metaValue+"' >"+sysms[i].metaValue+"</option>");
			}
			window.location.href="../pages/ModifyCheckIn.jsp";
		}
		
		});
	}

	
		
		

	
