$(function() {
	var pageNow = 1;
	
	$("#btnClose").click(function(){
		$("#inStorage").css("display", "none");
	});
	
	
	$("#adminManager_search").click(function(){
		var adminName=$.trim($("#adName").val());		
		findAdminByPage(adminName);
	});		
	
	$("#add_authority").click(function(){		
		var oldpeople="";
		var employee="";
		var community="";
		var storage="";
		var systemMeta="";		
		var oldpeopleAuthority=document.getElementsByName("oldpeopleAuthority");		
		var employeeAuthority=document.getElementsByName("employeeAuthority");
		var communityAuthority=document.getElementsByName("communityAuthority");
		var storageAuthority=document.getElementsByName("storageAuthority");
		var systemMetaAuthority=document.getElementsByName("systemMetaAuthority");
		
		for(var i=0;i<oldpeopleAuthority.length;i++){
			if(oldpeopleAuthority[i].checked==true){
				oldpeople="true";
			}
		}
		for(var i=0;i<employeeAuthority.length;i++){
			if(employeeAuthority[i].checked==true){
				employee="true";
			}
		}
		for(var i=0;i<communityAuthority.length;i++){
			if(communityAuthority[i].checked==true){
				community="true";
			}
		}
		for(var i=0;i<storageAuthority.length;i++){
			if(storageAuthority[i].checked==true){
				storage="true";
			}
		}
		for(var i=0;i<systemMetaAuthority.length;i++){
			if(systemMetaAuthority[i].checked==true){
				systemMeta="true";
			}
		}	
		
		var uuid = $.trim($("#admin_uuid").val());		
		$.ajax({
			url:'/RestHome/Admin/modifyAdmin',
			type:'post',
			data:{
				'admin.uuid':uuid,	
				'admin.oldpeopleAuthority':oldpeople,
				'admin.employeeAuthority':employee,
				'admin.communityAuthority':community,
				'admin.storageAuthority':storage,
				'admin.systemMetaAuthority':systemMeta
			},
			dataType:'text',
			success:function(){			
				$("#adminManager_table_toolbar").show("normal");
				$("#addAuthority_form_toolbar").hide("normal");	
			},
			error:function(){
				window.href="pages/500.jsp";
			}	
		});
	});
	
	$("#addAuthority_cancel").click(function(){
		$("#adminManager_table_toolbar").show("normal");
		$("#addAuthority_form_toolbar").hide("normal");	
	});
	
	$("#add_admin").click(function(){
		var adminName=$.trim($("#adminName").val());
		if(isNull(adminName)){
			$('#addAmin_form').removeAttr('action');
			$("#adminName_Lable").text("管理员姓名不能为空，请填写。").show("normal");	
		}else{
			$('#addAmin_form').removeAttr('action');
			$('#addAmin_form').attr('action','/RestHome/Admin/addAdmin');
			$(this).attr('type','submit');
			$('#addAmin_form').submit();
		}
	});
	
	$("#adminManager_add").click(function(){
		$("#addAdmin").css("top", "230px");
		$("#addAdmin").css("display", "block");
	});
	
	
	var findAdminByPage=function(adminName){
		$.ajax({
			url : '/RestHome/Admin/findAdminsByPage',
			data :{
				'pageVo.nowPage' : pageNow,			
				'admin.adminName':adminName
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;	
				
				$("#adminManager_table").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#adminManager_paging').find('ul').find('li').remove();
				
				for(var i=0;i<data.length;i++){
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].adminName+"</td>"+					
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
					"<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>"+					
					"</tr>";
					var clickTr = $("#adminManager_table").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(2).children().eq(0).click(function(){
						if(!confirm("确定删除这个管理员？")){
							return false;
						}					
						deleteAdmin(this);
					});	
					clickTr.children().eq(3).children().eq(0).click(function(){
						modifyAdmin(this);
					});
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#adminManager_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#adminManager_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#adminManager_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#adminManager_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#adminManager_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#adminManager_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#adminManager_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {													
							var page = $.trim($(this).text());
							var adminName=$.trim($("#adminName").val());									
							pageNow = page;
							findAdminByPage(adminName);
						});
				
				$('#prev').click(function() {
					var adminName=$.trim($("#adminName").val());		
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findAdminByPage(adminName);
					}						
				});
				
				$('#next').click(function() {
					var adminName=$.trim($("#adminName").val());				
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
						pageNow++;
						findAdminByPage(adminName);
					}																														
			});
			}
		});
	}
	findAdminByPage();
});

var deleteAdmin = function(e){	
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/Admin/delAdmin',
		type:'post',
		data:{
			'admin.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();			
			}
		}
	});
}


var modifyAdmin=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	$.ajax({
		url:'/RestHome/Admin/getAdminByUuid',
		type:'post',
		data:{
			'admin.uuid':uuid,			
		},
		dataType:'text',
		success:function(json){			
			var admin=$.parseJSON(json);
			$("#admin_uuid").attr("value",admin.uuid);
			if(admin.oldpeopleAuthority!=null&&admin.oldpeopleAuthority!=""){
				$("#oldpeopleAuthority").attr("checked","checked");
			}
			if(admin.employeeAuthority!=null&&admin.employeeAuthority!=""){
				$("#employeeAuthority").attr("checked","checked");
			}
			if(admin.communityAuthority!=null&&admin.communityAuthority!=""){
				$("#communityAuthority").attr("checked","checked");
			}
			if(admin.storageAuthority!=null&&admin.storageAuthority!=""){
				$("#storageAuthority").attr("checked","checked");
			}
			if(admin.systemMetaAuthority!=null&&admin.systemMetaAuthority!=""){
				$("#systemMetaAuthority").attr("checked","checked");
			}
			
			$('#addAuthority_submit').show("fast");
			$("#adminManager_table_toolbar").hide("normal");
			$("#addAuthority_form_toolbar").show("normal");		
			
		},
		error:function(){
			window.href="pages/500.jsp";
		}
		
		});
}