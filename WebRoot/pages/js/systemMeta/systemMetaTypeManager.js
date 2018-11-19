$(function() {
	var pageNow = 1;
	
	$("#btnClose").click(function(){
		$("#DialogDiv").css("display", "none");
	});
	
	$("#btnClosed").click(function(){
		$("#addlink1").css("display", "none");
	});
	
	$("#add").click(function(){
		var parentMetaName=$.trim($("#pn").val());
		//var result=isNull(parentMetaName);
		if(isNull(parentMetaName)){
			$('#addSystemMeta_form').removeAttr('action');
			$("#pn_Lable").text("参数名称不能为空！请重新添加。").show("normal");			
		}else{
			$("#pn_Lable").hide("normal");
			$('#addSystemMeta_form').attr('action','/RestHome/SystemMeta/addSystemMetaType');
			$(this).attr('type','submit');
			$('#addSystemMeta_form').submit();
		}
		
		
	});

	
	var findSystemMetaTypeByPage = function() {
		$
				.ajax({
					url : '/RestHome/SystemMeta/findSystemMetaTypeList',
					data : {
						'pageVo.nowPage' : pageNow,
					},
					dataType : 'json',
					type : 'post',
					success : function(json) {
						var data = json.listData;
						var pageLine = json.pageLine;
						var totalPage = json.totalPage;
						var totalNum=json.totalNum;
						pageNow = json.nowPage;
						
						$("table tbody tr").remove();
						$(".gradeA").remove();
						$('#sysm_paging').find('ul').find('li').remove();
						for (var i = 0; i < data.length; i++) {
							var ii=i;
							var tr = "<tr class='gradeA' id='"+data[i].uuid+"'>"
									+ "<td style='display:none;'>"
									+ data[i].uuid
									+ "</td>"
									+ "<td>"
									+ data[i].parentMetaName
									+ "</td>"
									+ "<td>"
									+ data[i].metaNickName
									+ "</td>"					
									+ "<td>"
									+ data[i].insertTime
									+ "</td>"
									+ "<td><a class='delete' style='cursor:pointer'><i class='icon-trash'></i></a></td>"
									+ "<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>"
							+"</tr>";
							var clickTr = $("table tbody").append(tr)
									.children().eq(i);
							
							clickTr.children().eq(4).children().eq(0).click(
									function() {
										if (!confirm("确定删除这个参数类型？")) {
											return false;
										}
										deleteSystemMetaType(this);
										
									});
							clickTr.children().eq(5).children().eq(0).click(
									function() {	
										if(modifySystemMetaType(this)){
											return true;
										}
										return false;
										
									});
							clickTr.click(function(){
								window.location="/RestHome/pages/SystemMetaManager.jsp?uuid="+$(this).children().eq(0).text();
							});
							
						}
						
						// 写出分页条
						if (pageNow != 1) {
							$('#sysm_paging')
									.find('ul')
									.append(
											"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
						} else {
							$('#sysm_paging')
									.find('ul')
									.append(
											"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
						}
						
						for (var i = 0; i < pageLine.length; i++) {
							if (pageNow == pageLine[i]) {
								$('#sysm_paging').find('ul').append(
										"<li class='active'><a class='pageA' style='cursor:pointer;'>"
												+ pageLine[i] + "</a></li>");
							} else {
								$('#sysm_paging').find('ul').append(
										"<li><a class='pageA' style='cursor:pointer;'>"
												+ pageLine[i] + "</a></li>");
							}

						}
						
						if (pageNow != totalPage) {
							$('#sysm_paging')
									.find('ul')
									.append(
											"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
						} else {
							$('#sysm_paging')
									.find('ul')
									.append(
											"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
						}						
						$('#totalNum').text(totalNum);
						
						$('#sysm_paging').find('ul').find('li').not('.prev')
						.click(								
								function() {									
									var page = $.trim($(this).text());															
									pageNow = page;
									findSystemMetaTypeByPage();
								});
						
						$('#prev').click(function() {
							pageNow = json.nowPage;										
							if(pageNow>1){
								pageNow--;
								findSystemMetaTypeByPage();
							}						
						});
						
						$('#next').click(function() {
							pageNow = json.nowPage;						
							if(pageNow<totalPage){
							pageNow++;
							findSystemMetaTypeByPage();	
							}																														
					});
	}
				});
	}
	findSystemMetaTypeByPage();

});








var addLink=function(){
	$("#addlink1").css("top", "230px");
	$("#addlink1").css("display", "inline");
}

var modifySystemMetaType=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var parentMetaName=($(e).parent().parent().children().get(1).innerHTML).trim();
	var metaNickName=($(e).parent().parent().children().get(2).innerHTML).trim();
	$("#DialogDiv").css("top", "230px");
	$("#DialogDiv").css("display", "block");
	$("#uuid").val(uuid);
	$("#parentMetaName").val(parentMetaName);
	$("#metaNickName").val(metaNickName);	
	
}


var deleteSystemMetaType = function(e) {
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();

	$
			.ajax({
				url : '/RestHome/SystemMeta/delSystemMeta',
				type : 'post',
				data : {
					'systemMetaVo.uuid' : uuid,
					'pageVo.nowPage' : pageNow
				},
				dataType : 'text',
				async:false,
				success : function(data) {
					if (data == -1) {
						alert("删除失败");
					} else {
						alert("删除成功");
						// alert(data);
						$(e).parent().parent().remove();

						var trNum = $("#datatablestools").find("tbody").find(
								"tr").length;
						if (trNum == 0) {
							window.location.href =  "../pages/SystemMetaTypeManager.jsp";
						}

					}
					return true;
				}
			});
}


var modify=function(){
	
	var uuid=$.trim($("#uuid").val());
	var parentMetaName=$.trim($("#parentMetaName").val());
	var metaNickName=$.trim($("#metaNickName").val());
	
	var result=isNull(parentMetaName);
	if(result=="isNull"){
		$("#parentMetaName_Lable").text("参数名称不能为空！请重新添加。").show("normal");			
	}else{
		$("#parentMetaName_Lable").hide("normal");
		$.ajax({
			url : '/RestHome/SystemMeta/modifySystemMeta',
			type : 'post',
			async:false,
			data : {
				'systemMetaVo.uuid' : uuid,
				'systemMetaVo.parentMetaName' : parentMetaName,
				'systemMetaVo.metaNickName' : metaNickName
			},
			dataType : 'text',
			success : function() {
				window.location.href = "/RestHome/pages/SystemMetaTypeManager.jsp";
				return true;
			}
		});
	}
	
	
}


var isNull=function(str){
	if(str==""||str==null){
		return "isNull";
	}
}


