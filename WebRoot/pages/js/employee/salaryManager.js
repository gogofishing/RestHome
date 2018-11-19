  

$(function(){
	
	var pageNow=1;
	
	$("#sousuo").click(function(){
		var empNo=$.trim($("#empNo").val());
		findSalaryByPage(empNo);
	});
	
	var findSalaryByPage=function(empNo){
		$.ajax({
			url : '/RestHome/Salary/findSalarysByPage',
			data :{
				'pageVo.nowPage' : pageNow,
				'employee.employeeNo':empNo
			},
			type : 'post',
			dataType : 'json',
			success : function(json) {
				var data = json.listData;				
				var pageLine = json.pageLine;
				var totalPage = json.totalPage;
				var totalNum=json.totalNum;
				pageNow = json.nowPage;
				
				$("table tbody tr").remove();
				$(".gradeA").remove();
				$('#salary_paging').find('ul').find('li').remove();
				for(var i = 0;i < data.length;i++){	
					var tr="<tr class='gradeA'>"+
					"<td style='display:none;'>"+data[i].uuid+"</td>"+
					"<td style='display:none;'>"+data[i].employeeUuid+"</td>"+
					"<td>"+data[i].employeeNo+"</td>"+
					"<td>"+data[i].employeeName+"</td>"+					
					"<td>"+fixed(data[i].realSalary)+"</td>"+
					"<td>"+data[i].insertTime+"</td>"+
					"<td><a class='delete' style='cursor:pointer'><i class='icon-trash'></i></a></td>"+
					"<td><a class='modify' style='cursor:pointer' href='/RestHome/pages/ModifySalary.jsp?salaryuuid="+data[i].uuid+"&empuuid="+data[i].employeeUuid+"'><i class='icofont-edit'></i></a></td>"
					"</tr>";
					var clickTr = $("table tbody").append(tr).children().eq(i);
					clickTr.children().eq(6).children().eq(0).click(function(){
						if(!confirm("确定删除这个用户？")){
							return false;
						}					
						deleteEmployeeSalary(this);
					});				
					
				}
				
				// 写出分页条
				if (pageNow != 1) {
					$('#salary_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev'><a class='pageA' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#salary_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#salary_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#salary_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#salary_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#salary_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
				$('#totalNum').text(totalNum);
				
				$('#salary_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {
							var empNo=$.trim($("#empNo").val());							
							var page = $.trim($(this).text());															
							pageNow = page;
							findSalaryByPage(empNo);
						});
				
				$('#prev').click(function() {
					var empNo=$.trim($("#empNo").val());	
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findSalaryByPage(empNo);
					}						
				});
				
				$('#next').click(function() {					
					var empNo=$.trim($("#empNo").val());	
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findSalaryByPage(empNo);
					}																														
			});
			}
		});
	}
	findSalaryByPage();

});


String.prototype.trim=function() {

    return this.replace(/(^\s*)|(\s*$)/g,'');
}

var fixed=function(num){
	return num.toFixed(2);
}


var deleteEmployeeSalary=function(e) {
	var uuid = ($(e).parent().parent().children().get(0).innerHTML).trim();
	var pageNow = $("#nowPage").text().trim();
	
	$.ajax({
		url:'/RestHome//Salary/delSalary',
		type:'post',
		data:{
			'salary.uuid':uuid,
			'pageVo.nowPage':pageNow,
			'employee.employeeNo':null,
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
					window.location.href="/RestHome//Salary/findSalarysByPage?pageVo.nowPage="+tempNowpage;
				}
				
				
			}
		}
	});
}


