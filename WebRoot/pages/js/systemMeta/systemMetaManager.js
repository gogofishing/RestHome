 

// 获取request请求参数
var mY_url=window.location.search;
	if(mY_url.indexOf("?") != -1){
		mY_url=mY_url.substring(1, (mY_url.length) );
		var arr_url=mY_url.split("=");
	}

$(function() {
	var pageNow = 1;
	var totalPage;	
	
	
	$("#add").click(function(){			
		addSystemMeta();
	});
	
	$("#btnClose").click(function(){
		$("#DialogDiv").css("display", "none");
	});
	
	$("#btnClosed").click(function(){
		$("#addlink1").css("display", "none");
	});
	
	

	
	var findSystemMetaByPage = function() {
		
				$.ajax({
					url : '/RestHome/SystemMeta/findSystemMetaList',
					data : {
						'pageVo.nowPage' : pageNow,
						'systemMetaVo.uuid':arr_url[1]
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
							// alert(typeof data);
							var tr = "<tr class='gradeA'>"
									+ "<td style='display:none;'>"
									+ data[i].uuid
									+ "</td>"
									+ "<td>"
									+ data[i].metaNickName
									+ "</td>"
									+"<td>"
									+data[i].parentMetaName
									+"</td>"																
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
										if (!confirm("确定删除这个用户？")) {
											return false;
										}
										deleteSystemMeta(this);
									});
							clickTr.children().eq(5).children().eq(0).click(
									function() {
										if(modifySystemMeta(this)){
											return true;
										}
										return false;
									});
							clickTr.click(function(){
								window.location="/RestHome/pages/ChildSystemMetaManager.jsp?systemMetaTypeUuid="+arr_url[1]
									+"&systemMetaUuid="+$(this).children().eq(0).text();
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
									findSystemMetaByPage();
								});
						
						$('#prev').click(function() {
							pageNow = json.nowPage;										
							if(pageNow>1){
								pageNow--;
								findSystemMetaByPage();
							}						
						});
						
						$('#next').click(function() {
							pageNow = json.nowPage;						
							if(pageNow<totalPage){
							pageNow++;
							findSystemMetaByPage();	
							}																														
					});
						
					}
				});
	}
	
	
	findSystemMetaByPage();
	
	
var addSystemMeta=function(){
		//var parentMetaName=$("#parentMetaName").val();
		var metaNickName=$("#metaNickName").val();
		
		var result=isNull(metaNickName);
		if(result=="isNull"){
			$("#metaNickName_Lable").text("参数昵称不能为空！请重新添加。").show("normal");			
		}else{
			$("#metaNickName_Lable").hide("normal");
			$.ajax({
				url : '/RestHome/SystemMeta/addSystemMeta',
				type : 'post',
				data : {
					'systemMetaVo.uuid' : arr_url[1],					
					'systemMetaVo.metaNickName':metaNickName
				},
				dataType : 'text',
				success : function() {
					window.location.href = "/RestHome/pages/SystemMetaManager.jsp?uuid="+arr_url[1];
				}
			});
		}	
		
	}





});




var addLink=function(){
	$("#addlink1").css("top", "230px");
	$("#addlink1").css("display", "inline");
}


var modifySystemMeta=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var NickName=($(e).parent().parent().children().get(1).innerHTML).trim();
	//alert("==="+uuid+":"+NickName);
	$("#DialogDiv").css("top", "230px");
	$("#DialogDiv").css("display", "block");
	$("#uuid").val(uuid);
	$("#nickName").val(NickName);
	
}




var deleteSystemMeta = function(e) {
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
							window.location.href ="/RestHome/pages/SystemMetaManager.jsp?uuid="+arr_url[1];
						}

					}
					return true;
				}
			});
}




var modify=function(){
	
	var uuid=$.trim($("#uuid").val());
	var NickName=$.trim($("#nickName").val());
	
	var result=isNull(NickName);
	if(result=="isNull"){
		$("#nickName_Lable").text("参数名称不能为空！请重新添加。").show("normal");			
	}else{
		$("#nickName_Lable").hide("normal");
		$.ajax({
			url : '/RestHome/SystemMeta/modifySystemMeta',
			type : 'post',
			data : {
				'systemMetaVo.uuid' : uuid,
				'systemMetaVo.metaNickName' : NickName
			},
			dataType : 'text',
			success : function() {
				window.location.href ="/RestHome/pages/SystemMetaManager.jsp?uuid="+arr_url[1];
					
			}
		});
	}	
	
}



var isNull=function(str){
	if(str==""||str==null){
		return "isNull";
	}
}




