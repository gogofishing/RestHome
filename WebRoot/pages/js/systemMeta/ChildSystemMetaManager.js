 

// 获取request请求参数：父节点的uuid
	var mY_url=window.location.search;
	if(mY_url.indexOf("?") != -1){
		mY_url=mY_url.substring(1, (mY_url.length) );
		var arr_url=mY_url.split("&");
		var systemMetaTypeUuid=arr_url[0].split("=")[1];
		var systemMetaUuid=arr_url[1].split("=")[1];
		//var arr_url=mY_url.split("=");
	}
	
$(function() {
	var pageNow = 1;
	var totalPage;	
	
	
	$("#add1").click(function(){			
		addChildSystemMeta1();
	});
	
	$("#add2").click(function(){			
		addChildSystemMeta2();
	});
	
	$("#btnClose1").click(function(){
		$("#DialogDiv1").css("display", "none");
	});
	
	$("#btnClose2").click(function(){
		$("#DialogDiv2").css("display", "none");
	});
	
	$("#btnClosed1").click(function(){
		$("#addlink1").css("display", "none");
	});
	
	$("#btnClosed2").click(function(){
		$("#addlink2").css("display", "none");
	});

	$("#SystemMetaManager").click(function(){
		$(this).attr('href','/RestHome/pages/SystemMetaManager.jsp?uuid='+systemMetaTypeUuid);
	});

	
	var findChildSystemMetaByPage = function() {
		
				$.ajax({
					url : '/RestHome/SystemMeta/findChildSystemMetaList',
					data : {
						'pageVo.nowPage' : pageNow,
						'systemMetaVo.uuid':systemMetaUuid
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
							var ss=31;
							var tr = "<tr class='gradeA'>"
									+ "<td style='display:none;'>"
									+ data[i].uuid
									+ "</td>"
									+ "<td style='display:none;'>"
									+ data[i].metaValueHtmlSource
									+ "</td>"
									+ "<td>"
									+ data[i].metaNickName
									+ "</td>"
									+ "<td>"
									+ data[i].metaValue
									+ "</td>"
									+ "<td>"
									+ data[i].listOrder
									+ "</td>"
									+ "<td>"
									+ data[i].insertTime
									+ "</td>"
									+ "<td><a class='delete' style='cursor:pointer'><i class='icon-trash'></i></a></td>"
									+ "<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>";
																
									
									tr+="</tr>";
							//var clickTr = $("table tbody").append(tr)
								//	.children().eq(i);
							var clickTr = $("#tbody1").append(tr)
									.children().eq(i);
							clickTr.children().eq(6).children().eq(0).click(
									function() {
										if (!confirm("确定删除这个用户？")) {
											return false;
										}
										deleteSystemMeta(this);
									});
							clickTr.children().eq(7).children().eq(0).click(									
									function() {
										modifyChildSystemMeta(this);										
										
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
									findChildSystemMetaByPage();
								});
						
						$('#prev').click(function() {
							pageNow = json.nowPage;										
							if(pageNow>1){
								pageNow--;
								findChildSystemMetaByPage();
							}						
						});
						
						$('#next').click(function() {							
							pageNow = json.nowPage;						
							if(pageNow<totalPage){
							pageNow++;
							findChildSystemMetaByPage();	
							}																														
					});
					}
				});
	}
	
	
	findChildSystemMetaByPage();
	
	
	var addChildSystemMeta1 = function(){
		var metaNickName=$.trim($("#metaNickName1").val());
		var metaValue=$.trim($("#metaValue").val());
		
		var result=isNull(metaValue);
		if(result=="isNull"){			
			$("#metaValue_Lable").text("参数值不能为空！请重新添加。").show("normal");			
		}else{
			$("#metaValue_Lable").hide("normal");
			$.ajax({
				url : '/RestHome/SystemMeta/addChildSystemMeta',
				type : 'post',
				data : {
					'systemMetaVo.uuid' : systemMetaUuid,				
					'systemMetaVo.metaNickName':metaNickName,
					'systemMetaVo.metaValue':metaValue,
				},
				dataType : 'text',
				success : function() {
					window.location.href = "/RestHome/pages/ChildSystemMetaManager.jsp?uuid="+systemMetaUuid;
				}
			});
		}	
		
	}
	
	
	var addChildSystemMeta2 = function(){
		var metaNickName=$.trim($("#metaNickName2").val());
		var htmlSource=familyHtmlUe.getContent();
		
		var result=isNull(metaNickName);
		if(result=="isNull"){			
			$("#metaNickName2_Lable").text("参数昵称不能为空！请重新添加。").show("normal");			
		}else{
			$("#metaNickName2_Lable").hide("normal");
			$.ajax({
				url : '/RestHome/SystemMeta/addChildSystemMeta',
				type : 'post',
				data : {
					'systemMetaVo.uuid' : systemMetaUuid,				
					'systemMetaVo.metaNickName':metaNickName,
					'systemMetaVo.metaValue':"RichTextBox",
					'systemMetaVo.metaValueHtmlSource':htmlSource
				},
				dataType : 'text',
				success : function() {
					window.location.href = "/RestHome/pages/ChildSystemMetaManager.jsp?uuid="+systemMetaUuid;
				}
			});
		}
		
	
		
	}

});





var addLink1=function(){
	$("#addlink1").css("top", "230px");
	$("#addlink1").css("display", "inline");
}

var addLink2=function(){
	$("#addlink2").css("top", "230px");
	$("#addlink2").css("display", "inline");
}


var modifyChildSystemMeta=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var nickName=($(e).parent().parent().children().get(2).innerHTML).trim();
	var value=($(e).parent().parent().children().get(3).innerHTML).trim();
	var listOrder=($(e).parent().parent().children().get(4).innerHTML).trim();
	var htmlSource = ($(e).parent().parent().children().get(1).innerHTML).trim();
	
	if(value=="RichTextBox"){
		$("#DialogDiv2").css("top", "230px");
		$("#DialogDiv2").css("display", "inline");
		$("#uuid2").val(uuid);	
		$("#nickName2").val(nickName);
		familyHtmlModifyUe.setContent(htmlSource);		
	}else{
		$("#DialogDiv1").css("top", "230px");
		$("#DialogDiv1").css("display", "inline");
		$("#uuid1").val(uuid);	
		$("#nickName1").val(nickName);
		$("#value").val(value);
		$("#listOrder").val(listOrder);
	}
	
	
	
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
							window.location.href = "/RestHome/pages/ChildSystemMetaManager.jsp?uuid="+systemMetaUuid;
						}

					}
				}
			});
}



var modify1=function(){
	
	var uuid=$.trim($("#uuid1").val());
	var nickName=$.trim($("#nickName1").val());
	var value=$.trim($("#value").val());
	var listOrder=$.trim($("#listOrder").val());

	var result1=isNull(value);
	var result2=isNull(listOrder);
	if(result1=="isNull"||result2=="isNull"||isNaN(listOrder)){
		if(result1=="isNull"){
			$("#value_Lable").text("参数值不能为空！请重新添加。").show("normal");			
		}
		if(result2=="isNull"){
			$("#value_Lable").hide("normal");
			$("#listOrder_Lable").text("序号不能为空！请重新添加。").show("normal");	
		}
		if(isNaN(listOrder)){
			$("#value_Lable").hide("normal");
			$("#listOrder_Lable").text("请输入数字!").show("normal");	
		}
	}else{
		$("#value_Lable").hide("normal");
		$("#listOrder_Lable").hide("normal");
		$.ajax({
			url : '/RestHome/SystemMeta/modifySystemMeta',
			type : 'post',
			data : {
				'systemMetaVo.uuid' : uuid,
				'systemMetaVo.metaValue' : value,
				'systemMetaVo.metaNickName' : nickName
			},
			dataType : 'text',
			success : function() {
				window.location.href = "/RestHome/pages/ChildSystemMetaManager.jsp?uuid="+systemMetaUuid;
			}
		});
	}
	
}

var modify2=function(){
	
	var uuid=$.trim($("#uuid2").val());
	var nickName=$.trim($("#nickName2").val());
	var htmlSource=familyHtmlModifyUe.getContent();

	var result=isNull(nickName);
	if(result=="isNull"){
		$("#nickName2_Lable").text("参数昵称不能为空！请重新添加。").show("normal");			
	}else{
		$.ajax({
			url : '/RestHome/SystemMeta/modifySystemMeta',
			type : 'post',
			data : {
				'systemMetaVo.uuid' : uuid,			
				'systemMetaVo.metaNickName' : nickName,
				'systemMetaVo.metaValueHtmlSource':htmlSource
			},
			dataType : 'text',
			success : function() {
				window.location.href = "/RestHome/pages/ChildSystemMetaManager.jsp?uuid="+systemMetaUuid;
			}
		});
	}	
	
}



var isNull=function(str){
	if(str==""||str==null){
		return "isNull";
	}
}




