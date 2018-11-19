$(function(){
	$('[data-form=datepicker]').datepicker();
	var pageNow = 1;
	
	$("#outStorage_search").click(function(){
		var goodsName=$.trim($("#outStorage_goodsName").val());
		var outDate=$.trim($("#outStorage_outDate").val());
		findOutStorage(goodsName,outDate);
	});
	
	var findOutStorage=function(goodsName,outDate){
		$.ajax({
			url : '/RestHome/Goods/findOutStorageByPage',
			data :{
				'pageVo.nowPage' : pageNow,				
				'good.goodsName':goodsName,
				'outStorage.outDate':outDate
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {
				var data = json.listData;
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;			
				
				$("#outStorage_table").find("tbody").find("tr").remove();
				$(".gradeA").remove();
				$('#outStorage_paging').find('ul').find('li').remove();
				
				for(var i=0;i<data.length;i++){
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td>"+data[i].goodsNo+"</td>"+
					"<td>"+data[i].goodsName+"</td>"+
					"<td>"+data[i].empNo+"</td>"+
					"<td>"+data[i].empName+"</td>"+						
					"<td>"+data[i].outNum+"</td>"+		
					"<td>"+data[i].outDate+"</td>"+		
					"<td><a class='delete'style='cursor:pointer'><i class='icon-trash'></i></a></td>"+					
					"</tr>";
					var clickTr = $("#outStorage_table").find("tbody").append(tr).children().eq(i);
					clickTr.children().eq(7).children().eq(0).click(function(){
						if(!confirm("确定删除这个用户？")){
							return false;
						}					
						deleteOutStorage(this);
					});			
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#outStorage_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#outStorage_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#outStorage_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#outStorage_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#outStorage_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#outStorage_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#outStorage_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {
							var goodsName=$.trim($("#outStorage_goodsName").val());
							var outDate=$.trim($("#outStorage_inDate").val());
							var page = $.trim($(this).text());															
							pageNow = page;
							findOutStorage(goodsName,outDate);
						});
				
				$('#prev').click(function() {
					var goodsName=$.trim($("#outStorage_goodsName").val());	
					var outDate=$.trim($("#outStorage_inDate").val());
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findOutStorage(goodsName,outDate);
					}						
				});
				
				$('#next').click(function() {
					var goodsName=$.trim($("#outStorage_goodsName").val());		
					var outDate=$.trim($("#outStorage_inDate").val());
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findOutStorage(goodsName,outDate);
					}																														
			});
			}
		});
	}
	
	findOutStorage();
});

var deleteOutStorage=function(e){
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome/Goods/delOutStorage',
		type:'post',
		data:{
			'outStorage.uuid':uuid,
			'pageVo.nowPage':pageNow			
		},
		dataType:'text',
		success:function(data){
			if(data==-1){
				alert("删除失败");
			}else{
				alert("删除成功");
				$(e).parent().parent().remove();
				
				var trNum = $("#outStorage_table").find("tbody").find("tr").length;
		        if(trNum==0){
					window.location.href="/RestHome/pages/OutStorage.jsp";
				}
			}
		}
	});
}