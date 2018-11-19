$(function() {
	var pageNow = 1;
	
	$("#volunteer_search").click(function(){
		var volunteerNo=$.trim($("#volunteer_volunteerNo").val());
		var volunteerName=$.trim($("#volunteer_volunteerName").val());
		findVolunteerByPage(volunteerNo,volunteerName);
	});	
	
	$("#volunteer_add").click(function(){		
		$("#volunteer_table_toolbar").hide("normal");
		$("#volunteer_form_toolbar").show("normal");	
		$('#volunteer_modify').hide("fast");
		$('#volunteer_submit').show("fast");
	});
	
	$("#volunteer_cancel").click(function(){
		$("#volunteer_table_toolbar").show("normal");
		$("#volunteer_form_toolbar").hide("normal");	
	});
	
	$("#volunteer_modify").click(function(){
		var vlt_name=$.trim($("#vlt_name").val());
		var vlt_idCard=$.trim($("#vlt_idCard").val());
		var vlt_phone=$.trim($("#vlt_phone").val());
		var vlt_email=$.trim($("#vlt_email").val());
		var vlt_address=$.trim($("#vlt_address").val());
		
		if(isNull(vlt_name)){
			showMessage("志愿者姓名不能为空!",$('#vlt_name_Label'));
		}else if(isNull(vlt_idCard)){
			showMessage("身份证号不能为空!",$('#vlt_idCard_Label'));
		}else if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(vlt_idCard)){
			showMessage("身份证格式错误，请重新填写.",$('#vlt_idCard_Label'));
		}else if(isNull(vlt_phone)){
			showMessage("移动电话不能为空!",$('#vlt_phone_Label'));
		}else if(!/^1\d{10}$/.test(vlt_phone)){
			showMessage("移动电话格式错误，请重新填写.",$('#vlt_phone_Label'));
		}else if((isNull(vlt_email)||vlt_email!="")&&(!testEmail_Util(vlt_email))){
			showMessage("邮箱格式格式错误，请重新填写.",$('#vlt_email_Label'));
		}else {		
			$('#volunteer_form').removeAttr('action');
			$('#volunteer_form').attr({'method':'post'});
			$('#volunteer_form').attr({'action':'Volunteer/modifyVolunteer'}).submit();
		}
	});
	
	
	$("#volunteer_submit").click(function(){
		var vlt_name=$.trim($("#vlt_name").val());
		var vlt_idCard=$.trim($("#vlt_idCard").val());
		var vlt_phone=$.trim($("#vlt_phone").val());
		var vlt_email=$.trim($("#vlt_email").val());
		var vlt_address=$.trim($("#vlt_address").val());
		
		if(isNull(vlt_name)){
			showMessage("志愿者姓名不能为空!",$('#vlt_name_Label'));
		}else if(isNull(vlt_idCard)){
			showMessage("身份证号不能为空!",$('#vlt_idCard_Label'));
		}else if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(vlt_idCard)){
			showMessage("身份证格式错误，请重新填写.",$('#vlt_idCard_Label'));
		}else if(isNull(vlt_phone)){
			showMessage("移动电话不能为空!",$('#vlt_phone_Label'));
		}else if(!/^1\d{10}$/.test(vlt_phone)){
			showMessage("移动电话格式错误，请重新填写.",$('#vlt_phone_Label'));
		}else if((isNull(vlt_email)||vlt_email!="")&&(!testEmail_Util(vlt_email))){
			showMessage("邮箱格式格式错误，请重新填写.",$('#vlt_email_Label'));
		}else {		
			$('#volunteer_form').removeAttr('action');
			$('#volunteer_form').attr({'method':'post'});
			$('#volunteer_form').attr({'action':'Volunteer/addVolunteer'}).submit();
		}
	});
	
	var findVolunteerByPage=function(volunteerNo,volunteerName){
		$.ajax({
			url : '/RestHome/Volunteer/findVolunteerByPage',
			data :{
				'pageVo.nowPage' : pageNow,			
				'volunteer.volunteerNo':volunteerNo,
				'volunteer.volunteerName':volunteerName
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {	
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;		
				
				$("#volunteer_table").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#volunteer_paging').find('ul').find('li').remove();
				
				for(var i=0;i<data.length;i++){
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].volunteerNo+"</td>"+
					"<td>"+data[i].volunteerName+"</td>"+
					"<td>"+data[i].sex+"</td>"+						
					"<td>"+data[i].phone+"</td>"+	
					"<td>"+data[i].workHours+"</td>"+	
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
					"<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>"+					
					"</tr>";
					var clickTr = $("#volunteer_table").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(6).children().eq(0).click(function(){
						if(!confirm("确定删除这个用户？")){
							return false;
						}					
						deleteVolunteer(this);
					});	
					clickTr.children().eq(7).children().eq(0).click(function(){
						modifyVolunteer(this);
					});
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#volunteer_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#volunteer_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#volunteer_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#volunteer_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#volunteer_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#volunteer_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#volunteer_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {
							var volunteerNo=$.trim($("#volunteer_volunteerNo").val());	
							var volunteerName=$.trim($("#volunteer_volunteerName").val());								
							var page = $.trim($(this).text());															
							pageNow = page;
							findVolunteerByPage(volunteerNo,volunteerName);
						});
				
				$('#prev').click(function() {
					var volunteerNo=$.trim($("#volunteer_volunteerNo").val());	
					var volunteerName=$.trim($("#volunteer_volunteerName").val());				
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findVolunteerByPage(volunteerNo,volunteerName);
					}						
				});
				
				$('#next').click(function() {
					var volunteerNo=$.trim($("#volunteer_volunteerNo").val());	
					var volunteerName=$.trim($("#volunteer_volunteerName").val());					
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findVolunteerByPage(volunteerNo,volunteerName);
					}																														
			});				
			}
		});
	}
	findVolunteerByPage();
});


var deleteVolunteer = function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/Volunteer/delVolunteer',
		type:'post',
		data:{
			'volunteer.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();
				
				var trNum = $("#volunteer_table").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="/RestHome/pages/VolunteerManager.jsp";
				}			
			}
		}
	});
}


var modifyVolunteer = function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	$.ajax({		
		url:'/RestHome/Volunteer/getVolunteerBy',
		type:'post',
		data:{
			'volunteer.uuid':uuid,			
		},
		dataType:'text',
		success:function(json){	
			var volunteer=$.parseJSON(json);
			$("#vlt_uuid").attr("value",volunteer.uuid);
			$("#vlt_no").attr("value",volunteer.volunteerNo);
			$("#vlt_name").attr("value",volunteer.volunteerName);
			$("#vlt_idCard").attr("value",volunteer.idCard);
			$('input[type=radio][value='+volunteer.sex+']').attr('checked','checked');
			$("#vlt_phone").attr("value",volunteer.phone);
			$("#vlt_email").attr("value",volunteer.email);
			$("#vlt_address").attr("value",volunteer.address);
			$('#volunteer_modify').show("fast");
			$('#volunteer_submit').hide("fast");
			$("#volunteer_table_toolbar").hide("normal");
			$("#volunteer_form_toolbar").show("normal");	
		},
		error:function(){
			window.href="pages/500.jsp";
		}
	});
}