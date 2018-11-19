$(function(){
	
	$('[data-form=datepicker]').datepicker();
	var pageNow = 1;

	$("#btnClose").click(function(){
		$("#inStorage").css("display", "none");
	});
	
	$("#btnClosed").click(function(){
		$("#outStorage").css("display", "none");
	});
	
	$("#inSr").click(function(){		
		var in_empNo=$.trim($("#in_empNo").val());
		var in_inNum=$.trim($("#in_inNum").val());
		var in_inDate=$.trim($("#in_inDate").val());
		
		if(isNull(in_empNo)){
			$('#inStorage_form').removeAttr('action');
			$("#in_empNo_Lable").text("采购员编号不能为空，请填写。").show("normal");	
		}else if(isNull(in_inNum)){
			$('#inStorage_form').removeAttr('action');
			$("#in_inNum_Lable").text("入库数量不能为空，请填写。").show("normal");	
		}else if(isNull(in_inDate)){
			$('#inStorage_form').removeAttr('action');
			$("#in_inDate_Lable").text("入库日期不能为空，请填写。").show("normal");	
		}else{
			$('#inStorage_form').removeAttr('action');
			$('#inStorage_form').attr('action','/RestHome/Goods/in_Storage');
			$(this).attr('type','submit');
			$('#inStorage_form').submit();
		}
	});
	
	$("#outSr").click(function(){
		var out_empNo=$.trim($("#out_empNo").val());
		var out_outNum=$.trim($("#out_outNum").val());
		var out_outDate=$.trim($("#out_outDate").val());
		
		if(isNull(out_empNo)){
			$('#outStorage_form').removeAttr('action');
			$("#out_empNo_Lable").text("采购员编号不能为空，请填写。").show("normal");	
		}else if(isNull(out_outNum)){
			$('#outStorage_form').removeAttr('action');
			$("#out_outNum_Lable").text("出库数量不能为空，请填写。").show("normal");	
		}else if(isNull(out_outDate)){
			$('#outStorage_form').removeAttr('action');
			$("#out_outDate_Lable").text("出库日期不能为空，请填写。").show("normal");	
		}else{
			$('#outStorage_form').removeAttr('action');
			$('#outStorage_form').attr('action','/RestHome/Goods/out_Storage');
			$(this).attr('type','submit');
			$('#outStorage_form').submit();
		}
	});
	
	$("#goods_search").click(function(){
		var goodsName=$.trim($("#goods_goodsName").val());
		findGoodsByPage(goodsName);
	});
	
	$("#goods_cancel").click(function(){
		$("#goods_table_toolbar").show("normal");
		$("#goods_form_toolbar").hide("normal");	
	});
	
	$("#goods_add").click(function(){
		$("#goods_table_toolbar").hide("normal");
		$("#goods_form_toolbar").show("normal");	
		$('#goods_modify').hide("fast");
		$('#goods_submit').show("fast");
	});
	
	
	$("#goods_modify").click(function(){
		$("#goods_empNo").attr("readonly","readonly");
		var goods_inDate=$.trim($("#goods_inDate").val());
		var goods_gdName=$.trim($("#goods_gdName").val());
		var goods_manufacturer=$.trim($("#goods_manufacturer").val());
		var goods_productionDate=$.trim($("#goods_productionDate").val());
		var goods_price=$.trim($("#goods_price").val());
		var goods_num=$.trim($("#goods_num").val());
		
		if(isNull(goods_inDate)){
			showMessage("入库时间不能为空!",$('#goods_inDate_Label'));
		}else if(isNull(goods_gdName)){
			showMessage("物品名称不能为空!",$('#goods_gdName_Label'));
		}else if(isNull(goods_manufacturer)){
			showMessage("生产厂家不能为空!",$('#goods_manufacturer_Label'));
		}else if(isNull(goods_productionDate)){
			showMessage("生产日期不能为空!",$('#goods_productionDate_Label'));
		}else if(isNull(goods_price)){
			showMessage("物品单价不能为空!",$('#goods_price_Label'));
		}else if(isNull(goods_num)){
			showMessage("物品库存不能为空!",$('#goods_num_Label'));
		}else{
			$('#goods_form').removeAttr('action');
			$('#goods_form').attr({'action':'Goods/modifyGoods'}).submit();
		}		
	});
	
	$("#goods_submit").click(function(){
		var goods_inDate=$.trim($("#goods_inDate").val());
		var goods_gdName=$.trim($("#goods_gdName").val());
		var goods_empNo=$.trim($("#goods_empNo").val());
		var goods_manufacturer=$.trim($("#goods_manufacturer").val());
		var goods_productionDate=$.trim($("#goods_productionDate").val());
		var goods_price=$.trim($("#goods_price").val());
		var goods_num=$.trim($("#goods_num").val());
		
		if(isNull(goods_inDate)){
			showMessage("入库时间不能为空!",$('#goods_inDate_Label'));
		}else if(isNull(goods_gdName)){
			showMessage("物品名称不能为空!",$('#goods_gdName_Label'));
		}else if(isNull(goods_empNo)){
			showMessage("采购人编号不能为空!",$('#goods_gdNo_Label'));
		}else if(isNull(goods_manufacturer)){
			showMessage("生产厂家不能为空!",$('#goods_manufacturer_Label'));
		}else if(isNull(goods_productionDate)){
			showMessage("生产日期不能为空!",$('#goods_productionDate_Label'));
		}else if(isNull(goods_price)){
			showMessage("物品单价不能为空!",$('#goods_price_Label'));
		}else if(isNull(goods_num)){
			showMessage("物品库存不能为空!",$('#goods_num_Label'));
		}else{
			$('#goods_form').removeAttr('action');
			$('#goods_form').attr({'action':'Goods/addGoods'}).submit();
		}
	});
	
	
		var findGoodsByPage=function(goodsName){			
			$.ajax({
				url : '/RestHome/Goods/findGoodsByPage',
				data :{
					'pageVo.nowPage' : pageNow,				
					'good.goodsName':goodsName
				},
				type : 'post',
				dataType : 'json',
				success : function(json) {	
					var data = json.listData;
					var pageLine = json.pageLine;
					var totalPage = json.totalPage;
					var totalNum=json.totalNum;
					pageNow = json.nowPage;					

					$("#goods_table").find("tbody").find("tr").remove();
					$(".gradeA").remove();
					$('#goods_paging').find('ul').find('li').remove();
					
					for(var i=0;i<data.length;i++){
						var tr="<tr class='gradeA'>"+
						"<td style='display:none;'>"+data[i].uuid+"</td>"+
						"<td>"+data[i].goodsNo+"</td>"+
						"<td>"+data[i].goodsName+"</td>"+
						"<td>"+data[i].empName+"</td>"+						
						"<td>"+data[i].goodsNum+"</td>"+		
						"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
						"<td><a class='modify' style='cursor:pointer'><i class='icofont-edit'></i></a></td>"+
						"<td><a class='' style='cursor:pointer'>入库</a></td>"+
						"<td><a class='' style='cursor:pointer'>出库</a></td>"+
						"</tr>";
						var clickTr = $("#goods_table").find("tbody").append(tr).children().eq(i);
						clickTr.children().eq(5).children().eq(0).click(function(){
							if(!confirm("确定删除这个用户？")){
								return false;
							}					
							deleteGoods(this);
						});	
						clickTr.children().eq(6).children().eq(0).click(function(){
							modifyGoods(this);
						});
						clickTr.children().eq(7).children().eq(0).click(function(){
							inStorage(this);
						});
						clickTr.children().eq(8).children().eq(0).click(function(){
							outStorage(this);
						});
						
					}
					
					// 写出分页条
						if (pageNow != 1) {
							$('#goods_paging')
									.find('ul')
									.append(
											"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
						} else {
							$('#goods_paging')
									.find('ul')
									.append(
											"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
						}
						
						for (var i = 0; i < pageLine.length; i++) {
							if (pageNow == pageLine[i]) {
								$('#goods_paging').find('ul').append(
										"<li class='active'><a class='pageA' style='cursor:pointer;'>"
												+ pageLine[i] + "</a></li>");
							} else {
								$('#goods_paging').find('ul').append(
										"<li><a class='pageA' style='cursor:pointer;'>"
												+ pageLine[i] + "</a></li>");
							}

						}
						
						if (pageNow != totalPage) {
							$('#goods_paging')
									.find('ul')
									.append(
											"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
						} else {
							$('#goods_paging')
									.find('ul')
									.append(
											"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
						}
						$('#totalNum').text(totalNum);
						
						$('#goods_paging').find('ul').find('li').not('.prev')
						.click(								
								function() {
									var goodsName=$.trim($("#goods_goodsName").val());								
									var page = $.trim($(this).text());															
									pageNow = page;
									findGoodsByPage(goodsName);
								});
						
						$('#prev').click(function() {
							var goodsName=$.trim($("#goods_goodsName").val());						
							pageNow = json.nowPage;										
							if(pageNow>1){
								pageNow--;
								findGoodsByPage(goodsName);
							}						
						});
						
						$('#next').click(function() {
							var goodsName=$.trim($("#goods_goodsName").val());						
							pageNow = json.nowPage;						
							if(pageNow<totalPage){
							pageNow++;
							findGoodsByPage(goodsName);
							}																														
					});
					
				}				
			});
		}
		findGoodsByPage();
});
	
	


var deleteGoods = function(e){	
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/Goods/delGoods',
		type:'post',
		data:{
			'good.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();
				
				var trNum = $("#goods_table").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="/RestHome/pages/GoodsManager.jsp";
				}
				
				
			}
		}
	});
}


var modifyGoods=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	$.ajax({
		url:'/RestHome/Goods/getGoodsByUuid',
		type:'post',
		data:{
			'good.uuid':uuid,			
		},
		dataType:'text',
		success:function(json){			
			var good=$.parseJSON(json);
			$("#goods_uuid").attr("value",good.uuid);
			$("#goods_inDate").attr("value",good.goodsInDate);
			$("#goods_gdName").attr("value",good.goodsName);
			$("#goods_gdNo").attr("value",good.goodsNo);
			$("#goods_empName").attr("value",good.empName);
			$("#goods_empNo").attr("value",good.empNo);
			$("#goods_manufacturer").attr("value",good.goodsManufacturer);
			$("#goods_productionDate").attr("value",good.goodsProductionDate);
			$("#goods_price").attr("value",good.goodsPrice);
			$("#goods_num").attr("value",good.goodsNum);
			$('#goods_modify').show("fast");
			$('#goods_submit').hide("fast");
			$("#goods_table_toolbar").hide("normal");
			$("#goods_form_toolbar").show("normal");		
			
		},
		error:function(){
			window.href="pages/500.jsp";
		}
		
		});
	}


var inStorage=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	$("#inStorage").css("top", "230px");
	$("#inStorage").css("display", "block");
	$("#inUuid").val(uuid);
}


var outStorage=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	$("#outStorage").css("top", "230px");
	$("#outStorage").css("display", "block");
	$("#outUuid").val(uuid);
}


