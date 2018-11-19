$().ready(function(){
	// datepicker
	$('[data-form=datepicker]').datepicker();
	/*init table*/
	oldPeopleOutInRecord_createtable(1,5,null,null);
	/*click 'add' button in table toolbar will trigger this function*/
	$('#oldPeopleOutInRecord_add').click(function(){
		$('#oldPeopleOutInRecord_table_toolbar').hide("normal");
		$('#oldPeopleOutInRecord_form_toolbar').fadeIn("normal");
	});
	/*click 'cancel' button in form will trigger this function*/
	$('#oldPeopleOutInRecord_cancel').click(function(){
		$('#oldPeopleOutInRecord_opNo').val("");
		lostFocusGlobal($('#oldPeopleOutInRecord_opNo'));
		$('#oldPeopleOutInRecord_empNo').val("");
		lostFocusGlobal($('#oldPeopleOutInRecord_empNo'));
		$('#oldPeopleOutInRecord_beginTime2').val("");
		lostFocusGlobal($('#oldPeopleOutInRecord_beginTime2'));
		$('#oldPeopleOutInRecord_endTime2').val("");
		lostFocusGlobal($('#oldPeopleOutInRecord_endTime2'));
		$('#oldPeopleOutInRecord_reason').val("");
		lostFocusGlobal($('#oldPeopleOutInRecord_reason'));
		$('#oldPeopleOutInRecord_familyInfo').val("");
		lostFocusGlobal($('#oldPeopleOutInRecord_familyInfo'));
		$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
		$('#oldPeopleOutInRecord_form_toolbar').hide("normal");
		$('#oldPeopleOutInRecord_table_toolbar').show("normal");
	});
	/*click 'submit' button in form will trigger this function*/
	$('#oldPeopleOutInRecord_submit').click(function(){
		var opNo = $('#oldPeopleOutInRecord_opNo').val();
		var empNo = $('#oldPeopleOutInRecord_empNo').val();
		var recordDate = $('#oldPeopleOutInRecord_recordDate').val();
		var beginTime = $('#oldPeopleOutInRecord_beginTime').val();
		var beginTime2 = $('#oldPeopleOutInRecord_beginTime2').val();
		var endTime = $('#oldPeopleOutInRecord_endTime').val();
		var endTime2 = $('#oldPeopleOutInRecord_endTime2').val();
		var reason = $('#oldPeopleOutInRecord_reason').val();
		var familyInfo = $('#oldPeopleOutInRecord_familyInfo').val();
		$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_form").offset().top}, 1000);
		if(isNull(opNo)){
			showMessage("老人编号不能为空，请填写",$('#oldPeopleOutInRecord_opNo_Label'));
		}else if(isNull(empNo)){
			showMessage("员工编号不能为空，请填写",$('#oldPeopleOutInRecord_empNo_Label'));
		}else if(isNull(recordDate)){
			showMessage("登记时间不能为空，请填写",$('#oldPeopleOutInRecord_recordDate_Label'));
		}else if(isNull(beginTime)){
			showMessage("开始时间不能为空，请填写",$('#oldPeopleOutInRecord_beginTime_Label'));
		}else if(!isTimeHHMM_Util(beginTime2)){
			showMessage("具体开始时间格式不正确，请填写",$('#oldPeopleOutInRecord_beginTime_Label'));
		}else if(isNull(endTime)){
			showMessage("结束时间不能为空，请填写",$('#oldPeopleOutInRecord_endTime_Label'));
		}else if(!isTimeHHMM_Util(endTime2)){
			showMessage("具体结束时间格式不正确，请填写",$('#oldPeopleOutInRecord_endTime_Label'));
		}else if(isNull(reason)){
			showMessage("外出原因不能为空，请填写",$('#oldPeopleOutInRecord_reason_Label'));
		}else if(isNull(familyInfo)){
			showMessage("陪同家属不能为空，请填写",$('#oldPeopleOutInRecord_familyInfo_Label'));
		}else{
			beginTime = beginTime +" "+ beginTime2;
			endTime = endTime +" "+ endTime2;
			if(beginTime>=endTime){
				alert("对不起。开始时间要小于结束时间");
			}else{
				$.ajax({
					type:"post",
					url:"oldPeopleOutInRecord/addOldPeopleOutInRecord",
					data:{ "oldPeopleOutInRecordVo.oldPeopleNo": opNo,"oldPeopleOutInRecordVo.empNo": empNo,"oldPeopleOutInRecordVo.recordDate": recordDate,"oldPeopleOutInRecordVo.beginTime": beginTime,"oldPeopleOutInRecordVo.endTime": endTime,"oldPeopleOutInRecordVo.reason": reason,"oldPeopleOutInRecordVo.familyInfo": familyInfo},
					success:function(data){
						var result = data;
						if(result=="notexist1"){
							showMessage("老人编号不存在，请重新填写",$('#oldPeopleOutInRecord_opNo_Label'));
						}else if(result=="notexist2"){
							showMessage("员工编号不存在，请重新填写",$('#oldPeopleOutInRecord_empNo_Label'));
						}else if(result=="success"){
							showMessage("添加成功。。。",$('#oldPeopleOutInRecord_showMessage'));
							oldPeopleOutInRecord_createtable(1,5,null,null);
							$('#oldPeopleOutInRecord_form_toolbar').hide("normal");
							$('#oldPeopleOutInRecord_table_toolbar').show("normal");
						}else{
							location.href = "pages/500.jsp";
						}
						$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
					},
					error:function(){
						location.href = "pages/500.jsp";
					}
				});
			}
		}
	});
	/*click 'search' button will trigger this function*/
	$('#oldPeopleOutInRecord_search').click(function(){
		var oldPeopleName = $.trim($('#oldPeopleOutInRecord_opName_search').val());
		var oldPeopleNo = $.trim($('#oldPeopleOutInRecord_opNo_search').val());
		oldPeopleOutInRecord_createtable(1,5,oldPeopleName,oldPeopleNo);
	});
	/*click 'return' button will trigger this function*/
	$('#oldPeopleOutInRecord_scan_return').click(function(){
		$('#oldPeopleOutInRecord_moreInfo_toolbar').hide("normal");
		$('#oldPeopleOutInRecord_table_toolbar').show("normal");
	});
	
});
/*function to check if exist the oldPeople with oldPeopleNo*/
this.isExistOldPeople = function(oldPeopleNo,messageLabel){
	if(!(oldPeopleNo==null||oldPeopleNo=="")){
		$.ajax({
			type:"post",
			url:"oldPeopleOutInRecord/isExistOldPeople",
			data:"oldPeopleNo="+oldPeopleNo,
			success:function(data){
				var msg = data;
				if(msg == "error"){
					showMessage("不存在该老人，请重新输入。。。",$(messageLabel));
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
	}
};
/*function to check if exist the employee with empNo*/
this.isExistEmployee = function(empNo,messageLabel){
	if(!(empNo==null||empNo=="")){
		$.ajax({
			type:"post",
			url:"oldPeopleOutInRecord/isExistEmployee",
			data:"empNo="+empNo,
			success:function(data){
				var msg = data;
				if(msg == "error"){
					showMessage("不存在该员工，请重新输入。。。",$(messageLabel));
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
	}
};

/*function to clear the form value*/
this.form_clear = function(oldPeopleOutInRecordForm){
	$(':input',oldPeopleOutInRecordForm)
    .not(':button, :submit, :reset, :radio')
    .val('')
    .removeAttr('checked')
    .removeAttr('selected');
};
/*function to get more information about OldPeopleOutinRecord*/
this.oldPeopleOutInRecord_table_scan = function(){
	var uuid = $(this).parent().prevAll('td').get(1).innerHTML;
	var tr = $(this).parent().parent();
	$.ajax({
		type:"post",
		url:"oldPeopleOutInRecord/findMoreInfo",
		data:"uuid="+uuid,
		success:function(data){
			var oldPeopleOutInRecord = $.parseJSON(data);
			$('#oldPeopleOutInRecord_moreInfo_recordDate').text(oldPeopleOutInRecord.recordDate);
			$('#oldPeopleOutInRecord_moreInfo_opName').text(oldPeopleOutInRecord.oldPeopleName);
			$('#oldPeopleOutInRecord_moreInfo_opNo').text(oldPeopleOutInRecord.oldPeopleNo);
			$('#oldPeopleOutInRecord_moreInfo_empName').text(oldPeopleOutInRecord.empName);
			$('#oldPeopleOutInRecord_moreInfo_empNo').text(oldPeopleOutInRecord.empNo);
			$('#oldPeopleOutInRecord_moreInfo_beginTime').text(oldPeopleOutInRecord.beginTime);
			$('#oldPeopleOutInRecord_moreInfo_endTime').text(oldPeopleOutInRecord.endTime);
			$('#oldPeopleOutInRecord_moreInfo_reason').text(oldPeopleOutInRecord.reason);
			$('#oldPeopleOutInRecord_moreInfo_familyInfo').text(oldPeopleOutInRecord.familyInfo);
			$('#oldPeopleOutInRecord_table_toolbar').hide("normal");
			$('#oldPeopleOutInRecord_moreInfo_toolbar').show("normal");
		},
		error:function(){
			window.href="pages/500.jsp";
		}
	});
};


/*function to delete a data*/
this.oldPeopleOutInRecord_table_delete = function(){
	if(!confirm("确定删除该记录吗？")){
		return false;
  	}
	var uuid = $(this).parent().prevAll('td').get(6).innerHTML;
	var tr = $(this).parent().parent();
	$.ajax({
		type:"post",
		url:"oldPeopleOutInRecord/deleteOldPeopleOutInRecord",
		data:"uuid="+uuid,
		success:function(data){
			var result = data;
			if(result=="success"){
				showMessage("删除成功",$('#oldPeopleOutInRecord_showMessage'));
				tr.remove();
			}else{
				showMessage("删除失败，请刷新后重试。。",$('#oldPeopleOutInRecord_showMessage'));
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
	});
	
};

/*
 * 动态表格的显示和分页(老人进出信息表格)
 * @pageIndex The current page number
 * @spanInterval The number of pages in the navigation
 * @oldPeopleName The value of a form
 * @oldPeopleNo The value of a form
 */
this.oldPeopleOutInRecord_createtable = function(pageIndex, spanInterval, oldPeopleName, oldPeopleNo) {
    $.ajax({
    	type: "post",
        url: "oldPeopleOutInRecord/queryAllByPage",
        data: { "nowPage": pageIndex,"oldPeopleName": oldPeopleName,"oldPeopleNo": oldPeopleNo},
        success: function (data) {
            var table = $("#oldPeopleOutInRecord_table");
            $("#oldPeopleOutInRecord_table tr").remove();
            table.append($("<tr><th style=\"display:none\"><span class=\"badge badge-success\">老人uuid</span></th>" +
            		"<th><span class=\"badge \">老人编号</span></th>" +
            		"<th><span class=\"badge \">老人名称</span></th>" +
            		"<th><span class=\"badge \">开始时间</span></th>" +
            		"<th><span class=\"badge \">结束时间</span></th>" +
            		"<th><span class=\"badge \">负责员工</span></th>" +
            		"<th><span class=\"badge \">员工编号</span></th>" +
            		"<th><span class=\"badge \">操作</span></th></tr>"));
            if(data=="error"){
            	location.href = "pages/500.jsp";
            }else{
            	var json = $.parseJSON(data);
            	//当前页,初始页1
            	var nowPage = json.nowPage;
            	//表格数据
            	var listData = json.listData;
            	//总记录数
            	var totalNum = json.totalNum;
            	//没页行数
            	var rowsPerPage = json.rowsPerPage;
            	//总页数
            	var totalPage = json.totalPage;
            	for (var i = 0; i < listData.length; i++) {
            		var j = i + 1;
            		table.append(
            				$("<tr class=\"gradeA\"><td style=\"display:none\">" +
            						listData[i].uuid
            						+ "</td><td>" +
            						listData[i].oldPeopleNo
            						+ "</td><td><a href=\"javascript:void(0)\">" +
            						listData[i].oldPeopleName
            						+ "</a></td><td>" +
            						listData[i].beginTime
            						+ "</td><td>" +
            						listData[i].endTime
            						+ "</td><td>" +
            						listData[i].empName
            						+ "</td><td>" +
            						listData[i].empNo
            						+"</td><td>"
                                    +"<a href=\"javascript:void(0)\"><i class=\"icon-trash\"></i></a>"
                                    +"</td></tr>")
            		);
            		$('#oldPeopleOutInRecord_table tr:eq('+j+') td:eq(2) a').click(oldPeopleOutInRecord_table_scan);
            		$('#oldPeopleOutInRecord_table tr:eq('+j+') td:eq(7) a').click(oldPeopleOutInRecord_table_delete);
            	}
            	$('#oldPeopleOutInRecord_paging ul').remove();
            	$('#oldPeopleOutInRecord_paging').append("<ul></ul>");
            	var $pager_ul = $('#oldPeopleOutInRecord_paging ul');
            	paggin($pager_ul,nowPage,totalPage,totalNum,spanInterval,oldPeopleOutInRecord_createtable,oldPeopleName,oldPeopleNo);
            }
        }
    });
};


/*
 * 表格分页方法
 */
this.paggin = function($pager_ul,nowPage,totalPage,totalNum,spanInterval,PageClick,oldPeopleName,oldPeopleNo){
	//添加第一页
    /*if (nowPage > 1){
        var first = $("<li><a href='javascript:void(0)'>第一页</a></li>").click(function () {
        	PageClick(1, spanInterval,oldPeopleName,oldPeopleNo);
        	return false;
        });
        $pager_ul.append(first);
    }else {
    	$pager_ul.append("<li><span class='disabled'>第一页</span></li>");
    }*/
    //添加上一页
    if (nowPage > 1){
    	var pre = $("<li id='prev' class='prev'><a href='javascript:void(0)' class='pageA' style='cursor:pointer;'>上一页</a></li>").click(function () {
    		PageClick(nowPage-1, spanInterval,oldPeopleName,oldPeopleNo);
    		return false;
    	});
    	$pager_ul.append(pre);
    }else {
    	$pager_ul.append("<li id='prev' class='prev disabled'><a class='pageA' style='cursor:pointer;'>上一页</a></li>");
    }
    var interval = parseInt(spanInterval); //设置间隔
    var start = Math.max(1, nowPage - interval); //设置起始页
    var end = Math.min(nowPage + interval, totalPage)//设置末页
    if (nowPage < interval + 1) {
        end = (2 * interval + 1) > totalPage ? totalPage : (2 * interval + 1);
    }
    if ((nowPage + interval) > totalPage) {
        start = (totalPage - 2 * interval) < 1 ? 1 : (totalPage - 2 * interval);
    }
    //生成页码
    for (var j = start; j < end + 1; j++) {
        if (j == nowPage) {
            var spanSelectd = $("<li class='active'><a class='pageA' style='cursor:pointer;'>" + j + "</a></li>");
            $pager_ul.append(spanSelectd);
        } //if 
        else {
            var a = $("<li><a href='javascript:void(0)' class='pageA' style='cursor:pointer;'>" + j + "</a></li>").click(function () {
                PageClick($(this).text(), spanInterval,oldPeopleName,oldPeopleNo);
                return false;
            });
            $pager_ul.append(a);
        }
    }
    //下一页
    if (nowPage < totalPage) {
        var next = $("<li id='next' class='prev'><a href='javascript:void(0)' value='1' class='pageA' style='cursor:pointer;'>下一页</a></li>").click(function () {
        	PageClick(nowPage+1,spanInterval,oldPeopleName,oldPeopleNo);
        	return false;
        });
        $pager_ul.append(next);
    }
    else {
    	$pager_ul.append("<li id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
    }
    $('#totalNum').text(totalNum);
    //最后一页
    /*if (nowPage >= totalPage) {
        $pager_ul.append("<li><span class='disabled'>最后一页</span></li>");
    }
    else {
        var last = $("<li><a href='javascript:void(0)'>最后一页</a></li>").click(function () {
            PageClick(totalPage,spanInterval,oldPeopleName,oldPeopleNo);
            return false;
        });
        $pager_ul.append(last);
    }*/
};