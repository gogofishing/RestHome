 
$(function() {

	var pageNow = 1;
	
	
	$("#sousuo").click(function(){
		var empNo=$.trim($("#empNo").val());
		var department=$.trim($("#department").val());
		findEmployeeList(empNo,department);
	});
	
	
	
	var findEmployeeList=function(empNo,department){
		
		$.ajax({
			url : '/RestHome/Salary/findEmployees',
			data :{
				'pageVo.nowPage' : pageNow,
				'employee.employeeNo':empNo,
				'employee.department':department
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
				$('#empl_paging').find('ul').find('li').remove();
				for(var i = 0 ; i < data.length ; i++){					
					var tr= "<tr class='gradeA'>"
						+ "<td style='display:none;'>"
						+ data[i].uuid
						+ "</td>"
						+ "<td>"
						+ data[i].employeeNo
						+ "</td>"
						+ "<td>"
						+ data[i].employeeName
						+ "</td>"					
						+ "<td>"
						+ data[i].department
						+ "</td>"
						+ "<td>"
						+ data[i].workName
						+ "</td>"
						+ "<td><a href='/RestHome/pages/EntrySalary.jsp?employee.uuid="+data[i].uuid+"'><span><button>录入工资</button></span></a></td>"
						+"</tr>";
					 $("table tbody").append(tr);
				}
				
				// 写出分页条				
				if (pageNow != 1) {
					$('#empl_paging')
							.find('ul')
							.append(
									"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
				} else {
					$('#empl_paging')
							.find('ul')
							.append(
									"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
				}
				
				for (var i = 0; i < pageLine.length; i++) {
					if (pageNow == pageLine[i]) {
						$('#empl_paging').find('ul').append(
								"<li class='active'><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					} else {
						$('#empl_paging').find('ul').append(
								"<li><a class='pageA' style='cursor:pointer;'>"
										+ pageLine[i] + "</a></li>");
					}

				}
				
				if (pageNow != totalPage) {
					$('#empl_paging')
							.find('ul')
							.append(
									"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				} else {
					$('#empl_paging')
							.find('ul')
							.append(
									"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
				}
			//	$('#empl_paging').find('ul').append("<li  id='totalNum'><a>总共"+totalNum+"条记录</a></li>");
				$('#totalNum').text(totalNum);
				
				$('#empl_paging').find('ul').find('li').not('.prev')
				.click(								
						function() {
							var page = $.trim($(this).text());
							var empNo=$.trim($("#empNo").val());
							var department=$.trim($("#department").val());							
							pageNow = page;
							findEmployeeList(empNo,department);
						});
				
				$('#prev').click(function() {
					var empNo=$.trim($("#empNo").val());
					var department=$.trim($("#department").val());
					pageNow = json.nowPage;										
					if(pageNow>1){
						pageNow--;
						findEmployeeList(empNo,department);
					}						
				});
				
				$('#next').click(function() {
					var empNo=$.trim($("#empNo").val());
					var department=$.trim($("#department").val());	
					pageNow = json.nowPage;						
					if(pageNow<totalPage){
					pageNow++;
					findEmployeeList(empNo,department);
					}																														
			});
			}
		});
	}
	
	findEmployeeList();
}
);