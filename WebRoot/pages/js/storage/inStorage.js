$(function(){
	$('[data-form=datepicker]').datepicker();
	var pageNow = 1;
	
	$("#inStorage_search").click(function(){
		var goodsName=$.trim($("#inStorage_goodsName").val());
		var inDate=$.trim($("#inStorage_inDate").val());
		findInStorage(goodsName,inDate);
	});
	
	var findInStorage=function(goodsName,inDate){
		$.ajax({
			url : '/RestHome/Goods/findInStorageByPage',
			data :{
				'pageVo.nowPage' : pageNow,				
				'good.goodsName':goodsName,
				'inStorage.inDate':inDate
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;			
				
				$("#inStorage_table").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#inStorage_paging').find('ul').find('li').remove();
				
				for(var i=0;i<data.length;i++){
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].goodsNo+"</td>"+
					"<td>"+data[i].goodsName+"</td>"+
					"<td>"+data[i].empNo+"</td>"+
					"<td>"+data[i].empName+"</td>"+						
					"<td>"+data[i].inNum+"</td>"+		
					"<td>"+data[i].inDate+"</td>"+		
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+					
					"</tr>";
					var clickTr = $("#inStorage_table").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(7).children().eq(0).click(function(){
						if(!confirm("确定删除这个用户？")){
							return false;
						}					
						deleteInStorage(this);
					});			
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#inStorage_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#inStorage_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#inStorage_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#inStorage_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#inStorage_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#inStorage_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#inStorage_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {
							var goodsName=$.trim($("#inStorage_goodsName").val());
							var inDate=$.trim($("#inStorage_inDate").val());
							var page = $.trim($(this).text());															
							pageNow = page;
							findInStorage(goodsName,inDate);
						});
				
				$('#prev').click(function() {
					var goodsName=$.trim($("#inStorage_goodsName").val());	
					var inDate=$.trim($("#inStorage_inDate").val());
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findInStorage(goodsName,inDate);
					}						
				});
				
				$('#next').click(function() {
					var goodsName=$.trim($("#inStorage_goodsName").val());		
					var inDate=$.trim($("#inStorage_inDate").val());
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findInStorage(goodsName,inDate);
					}																														
			});
			}
		});
	}
	
	findInStorage();
});

var deleteInStorage=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/Goods/delInStorage',
		type:'post',
		data:{
			'inStorage.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();
				
				var trNum = $("#inStorage_table").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="/RestHome/pages/InStorage.jsp";
				}
			}
		}
	});
}