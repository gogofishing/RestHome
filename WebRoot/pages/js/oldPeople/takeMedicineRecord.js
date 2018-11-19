$().ready(function(){
	// datepicker
	$('[data-form=datepicker]').datepicker();
	//init table
	takeMedicineRecord_createtable(1,5,null,null);
	/*click 'add' button will trigger this function to show add_toolbar*/
	$('#takeMedicineRecord_add').click(function(){
		$.ajax({
			type:"post",
			url:"takeMedicineRecord/findTakeTimes",
			success:function(data){
				if(data=="error"){
					alert("因系统繁忙，操作失败，请稍后刷新重试");
				}else{
					$('#takeMedicineRecord_takeTimes option').remove();
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						$('#takeMedicineRecord_takeTimes').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
					}
					$('#takeMedicineRecord_takeTimes option').eq(0).attr("selected","selected");
					if($('#takeMedicineRecord_takeTimes').val()=="其他"){
						$('#tmr_takeTimes_other').show("normal");
					}else{
						$('#tmr_takeTimes_other').hide("normal");
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		$('#takeMedicineRecord_table_toolbar').hide("normal");
		$('#takeMedicineRecord_form_toolbar').show("normal");
		$('#takeMedicineRecord_modify').hide("normal");
		$('#takeMedicineRecord_submit').show("normal");
	});
	/*click 'cancel' button will trigger this function to return*/
	$('#takeMedicineRecord_cancel').click(function(){
		$('#takeMedicineRecord_form_toolbar').hide("normal");
		$('#takeMedicineRecord_table_toolbar').show("normal");
		form_clear();
	});
	/*click 'submit' button will trigger this function to add a data of oldPeople take medicine record*/
	$('#takeMedicineRecord_submit').click(function(){
		var opNo = $('#takeMedicineRecord_opNo').val();
		var beforeEat = $('input[name=beforeEat]:checked').val();
		var takeTimes = $('#takeMedicineRecord_takeTimes').val();
		if($('#takeMedicineRecord_takeTimes option:selected').val()=="其他"){
			takeTimes = $('#tmr_takeTimes_other').val();
		}
		var beginDate = $('#takeMedicineRecord_beginDate').val();		
		var endDate = $('#takeMedicineRecord_endDate').val();
		var ifPrescriptionMedicine = $('input[name=ifPrescriptionMedicine]:checked').val();
		var takeTime = $('#takeMedicineRecord_takeTime').val();
		var medicineNum = $('#takeMedicineRecord_medicineNum').val();
		var medicineName = $('#takeMedicineRecord_medicineName').val();
		if(isNull(opNo)){
			showMessage("老人编号不能为空，请填写",$('#takeMedicineRecord_opNo_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
		}else if(isNull(takeTimes)){
			showMessage("每日用药次数不能为空，请填写",$('#takeMedicineRecord_takeTimes_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
		}else if(isNull(takeTime)){
			showMessage("每日用药时间不能为空，请填写",$('#takeMedicineRecord_takeTime_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_center").offset().top}, 1000);
		}else if(isNull(medicineNum)){
			showMessage("药量不能为空，请填写",$('#takeMedicineRecord_medicineNum_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_center").offset().top}, 1000);
		}else if(isNull(medicineName)){
			showMessage("药品名不能为空，请填写",$('#takeMedicineRecord_medicineName_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_center").offset().top}, 1000);
		}else{
			//beginDate = dateFormat_Util(beginDate);
			//endDate = dateFormat_Util(endDate);
			if(beginDate>endDate){
				alert("开始日期不能晚于结束日期");
			}else{
				$.ajax({
					type:"post",
					url:"takeMedicineRecord/addOne",
					async:false,
					data:{ "tmrVo.oldPeopleNo": opNo,"tmrVo.beforeEat":beforeEat,"tmrVo.takeTimes": takeTimes,"tmrVo.beginDate": beginDate,"tmrVo.endDate": endDate,"tmrVo.ifPrescriptionMedicine":ifPrescriptionMedicine,"tmrVo.takeTime": takeTime,"tmrVo.medicineNum": medicineNum,"tmrVo.medicineName": medicineName},
					success:function(data){
						$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
						var msg = data;
						if(msg=="notexist1"){
							showMessage("不存在该老人编号，请重新输入。。。",$('#takeMedicineRecord_opNo_Label'));
						}else if(msg=="success"){
							form_clear();
							$('#takeMedicineRecord_form_toolbar').hide("normal");
							$('#takeMedicineRecord_table_toolbar').show("normal");
							showMessage("添加成功",$('#takeMedicineRecord_showMessage'));
							takeMedicineRecord_createtable(1,5,null,null);
						}else{
							alert("因系统繁忙，添加失败。");
						}
					},
					error:function(){
						location.href = "pages/500.jsp";
					}
				});
			}
		}
	});
	/*click 'search' button will trigger this function to search oldPeople take Medicine Record*/
	$('#takeMedicineRecord_search').click(function(){
		var opNo = $.trim($('#takeMedicineRecord_opNo_search').val());
		var opName = $.trim($('#takeMedicineRecord_opName_search').val());
		takeMedicineRecord_createtable(1,5,opName,opNo);
	});
	/*click 'modify' button will trigger this function to modify a data*/
	$('#takeMedicineRecord_modify').click(function(){
		var uuid = $('#takeMedicineRecord_uuid').val();
		var opNo = $('#takeMedicineRecord_opNo').val();
		var takeTimes = $('#takeMedicineRecord_takeTimes').val();
		if($('#takeMedicineRecord_takeTimes option:selected').val()=="其他"){
			takeTimes = $('#tmr_takeTimes_other').val();
		}
		var beforeEat = $("input[name='beforeEat']:checked").val();
		var beginDate = $('#takeMedicineRecord_beginDate').val();
		var endDate = $('#takeMedicineRecord_endDate').val();
		var ifPrescriptionMedicine = $("input[name='ifPrescriptionMedicine']:checked").val();
		var takeTime = $('#takeMedicineRecord_takeTime').val();
		var medicineNum = $('#takeMedicineRecord_medicineNum').val();
		var medicineName = $('#takeMedicineRecord_medicineName').val();
		if(isNull(uuid)){
			alert("因系统繁忙，请刷新页面后重试！");
		}else if(isNull(opNo)){
			showMessage("老人编号不能为空，请填写",$('#takeMedicineRecord_opNo_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
		}else if(isNull(takeTimes)){
			showMessage("每日用药次数不能为空，请填写",$('#takeMedicineRecord_takeTimes_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
		}else if(isNull(takeTime)){
			showMessage("每日用药时间不能为空，请填写",$('#takeMedicineRecord_takeTime_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_center").offset().top}, 1000);
		}else if(isNull(medicineNum)){
			showMessage("药量不能为空，请填写",$('#takeMedicineRecord_medicineNum_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_center").offset().top}, 1000);
		}else if(isNull(medicineName)){
			showMessage("药品名不能为空，请填写",$('#takeMedicineRecord_medicineName_Label'));
			$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_center").offset().top}, 1000);
		}else{
			//beginDate = dateFormat_Util(beginDate);
			//endDate = dateFormat_Util(endDate);
			if(beginDate>endDate){
				alert("开始日期不能晚于结束日期");
			}else{
				$.ajax({
					type:"post",
					async:false,
					url:"takeMedicineRecord/modifyTakeMedicineRecord",
					data:{"tmrVo.uuid":uuid,"tmrVo.oldPeopleNo": opNo,"tmrVo.beforeEat":beforeEat,"tmrVo.takeTimes": takeTimes,"tmrVo.beginDate": beginDate,"tmrVo.endDate": endDate,"tmrVo.ifPrescriptionMedicine":ifPrescriptionMedicine,"tmrVo.takeTime": takeTime,"tmrVo.medicineNum": medicineNum,"tmrVo.medicineName": medicineName},
					success:function(data){
						$("html,body").animate({scrollTop: $("#oldPeopleOutInRecord_top").offset().top}, 1000);
						var msg = data;
						if(msg=="success"){
							form_clear();
							showMessage("修改成功",$('#takeMedicineRecord_showMessage'));
							$('#takeMedicineRecord_form_toolbar').hide("normal");
							$('#takeMedicineRecord_table_toolbar').show("normal");
							takeMedicineRecord_createtable(1,5,null,null);
						}else if(msg == "notexist1"){
							showMessage("不存在该老人编号，请重新输入。。。",$('#takeMedicineRecord_opNo_Label'));
						}else{
							alert("因系统繁忙，，请刷新页面后重试！");
						}
					},
					error:function(){
						location.href = "pages/500.jsp";
					}
				});
			}
		}
	});
	
	$('#takeMedicineRecord_scan_return').click(function(){
		$('#takeMedicineRecord_moreInfo_toolbar').hide("normal");
		$("#takeMedicineRecord_table_toolbar").show("normal");
	});
	
	
	
});


/*this function is to listen combobox event*/
this.takeTimes_combobox = function(thisId){
	if($(thisId).find("option:selected").val()=="其他"){
		$('#tmr_takeTimes_other').show("normal");
	}else{
		$('#tmr_takeTimes_other').hide("normal");
	}
};

/*this function is to check if exist a oldPeople with oldPeopleNo*/
this.isExistOldPeople = function(thisValue,thisLabel){
	if(thisValue!=""){
		$.ajax({
			type:"post",
			url:"takeMedicineRecord/isExistOldPeople",
			data:"oldPeopleNo="+thisValue,
			success:function(data){
				var msg = data;
				if(msg == "error"){
					showMessage("不存在该老人编号，请重新输入。。。",$(thisLabel));
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
	}
};

/*function to clear the form value*/
this.form_clear = function(){
	$('#takeMedicineRecord_opNo').val("");
	lostFocusGlobal($('#takeMedicineRecord_opNo'));
	$('#takeMedicineRecord_takeTime').val("");
	lostFocusGlobal($('#takeMedicineRecord_takeTime'));
	$('#takeMedicineRecord_medicineNum').val("");
	lostFocusGlobal($('#takeMedicineRecord_medicineNum'));
	$('#takeMedicineRecord_medicineName').val("");
	lostFocusGlobal($('#takeMedicineRecord_medicineName'));
	$('#tmr_takeTimes_other').val("");
	lostFocusGlobal($('#tmr_takeTimes_other'));
};
/*yyyy-mm-dd to mm/dd/yyyy*/
this.timeFormat = function(str){
	var a=str;
	var b=a.split('-');
	var a=b[1]+"/"+b[2]+"/"+b[0];
	return a;
};

/*
 * 动态表格的显示和分页(老人用药记录表格)
 * @pageIndex The current page number
 * @spanInterval The number of pages in the navigation
 * @oldPeopleName The value of a form
 * @oldPeopleNo The value of a form
 */
this.takeMedicineRecord_createtable = function(pageIndex, spanInterval, oldPeopleName, oldPeopleNo) {
    $.ajax({
    	type: "post",
        url: "takeMedicineRecord/queryAllByPage",
        data: { "nowPage": pageIndex,"oldPeopleName": oldPeopleName,"oldPeopleNo": oldPeopleNo},
        success: function (data) {
            var table = $("#takeMedicineRecord_table");
            $("#takeMedicineRecord_table tr").remove();
            table.append($("<tr><th style=\"display:none\"><span class=\"badge badge-success\">老人uuid</span></th>" +
            		"<th><span class=\"badge \">老人编号</span></th>" +
            		"<th><span class=\"badge \">老人名称</span></th>" +
            		"<th><span class=\"badge \">&nbsp;&nbsp;&nbsp;药品名&nbsp;&nbsp;&nbsp;</span></th>" +
            		"<th><span class=\"badge \">起始时间</span></th>" +
            		"<th><span class=\"badge \">结束时间</span></th>" +
            		"<th><span class=\"badge \">每日次数</span></th>" +
            		"<th colspan=\"2\"><span class=\"badge \">操作</span></th></tr>"));
            if(data=="error"){
            	alert("系统繁忙，请稍后刷新页面重试");
            }else{
            	var json = $.parseJSON(data);
            	//当前页,初始页1
            	var nowPage = json.nowPage;
            	//表格数据
            	var listData = json.listData;
            	//total num
            	var totalNum = json.totalNum;
            	//perPage num
            	var rowsPerPage = json.rowsPerPage;
            	//total page
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
            						listData[i].medicineName
            						+ "</td><td>" +
            						listData[i].beginDate
            						+ "</td><td>" +
            						listData[i].endDate
            						+ "</td><td>" +
            						listData[i].takeTimes
            						+"</td><td>"
                                    +"<a href=\"javascript:void(0)\"><i class=\"icofont-edit\"></i></a>"
                                    +"</td><td>"
                                    +"<a href=\"javascript:void(0)\"><i class='icon-trash'></i></a>"
                                    +"</td></tr>")
            		);
            		$('#takeMedicineRecord_table tr:eq('+j+') td:eq(2) a').click(takeMedicineRecord_table_scan);
            		$('#takeMedicineRecord_table tr:eq('+j+') td:eq(7) a').click(takeMedicineRecord_table_modify);
            		$('#takeMedicineRecord_table tr:eq('+j+') td:eq(8) a').click(takeMedicineRecord_table_delete);
            	}
            	$('#takeMedicineRecord_paging ul').remove();
            	$('#takeMedicineRecord_paging').append("<ul></ul>");
            	var $pager_ul = $('#takeMedicineRecord_paging ul');
            	paggin($pager_ul,nowPage,totalPage,totalNum,spanInterval,takeMedicineRecord_createtable,oldPeopleName,oldPeopleNo);
            }
        }
    });
};
/*click 'scan' button will trigger this function to scan more information*/
this.takeMedicineRecord_table_scan = function(){
	var uuid = $(this).parent().prevAll('td').get(1).innerHTML;
	$.ajax({
		type:"post",
		url:"takeMedicineRecord/findMoreInfo",
		data:"uuid="+uuid,
		success:function(data){
			if(data=="error"){
				showMessage("因系统繁忙，查看失败，请稍后刷新重试。。",$('#takeMedicineRecord_showMessage'));
			}else{
				$("#takeMedicineRecord_table_toolbar").hide("normal");
				$('#takeMedicineRecord_moreInfo_toolbar').show("normal");
				var json = $.parseJSON("[" + data + "]");
				var takeMedicineRecord = json[0];
				$('#takeMedicineRecord_moreInfo_opName').text(takeMedicineRecord.oldPeopleName);
				$('#takeMedicineRecord_moreInfo_opNo').text(takeMedicineRecord.oldPeopleNo);
				$('#takeMedicineRecord_moreInfo_beginDate').text(takeMedicineRecord.beginDate);
				$('#takeMedicineRecord_moreInfo_endDate').text(takeMedicineRecord.endDate);
				$('#takeMedicineRecord_moreInfo_beforeEat').text(takeMedicineRecord.beforeEat);
				$('#takeMedicineRecord_moreInfo_ifPrescriptionMedicine').text(takeMedicineRecord.ifPrescriptionMedicine);
				$('#takeMedicineRecord_moreInfo_takeTimes').text(takeMedicineRecord.takeTimes);
				$('#takeMedicineRecord_moreInfo_insertTime').text(takeMedicineRecord.insertTime);
				$('#takeMedicineRecord_moreInfo_takeTime').text(takeMedicineRecord.takeTime);
				$('#takeMedicineRecord_moreInfo_medicineNum').text(takeMedicineRecord.medicineNum);
				$('#takeMedicineRecord_moreInfo_medicineName').text(takeMedicineRecord.medicineName);
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
	});
};
/*click 'modify' button will trigger this function to modify a data*/
this.takeMedicineRecord_table_modify = function(){
	var uuid = $(this).parent().prevAll('td').get(6).innerHTML;
	var tr = $(this).parent().parent();
	$.ajax({
		type:"post",
		async:false,
		url:"takeMedicineRecord/findTakeTimes",
		success:function(data){
			if(data=="error"){
				alert("因系统繁忙，操作失败，请稍后刷新重试");
			}else{
				$('#takeMedicineRecord_takeTimes option').remove();
				var json = $.parseJSON(data);
				for(var i = 0; i<json.length; i++){
					$('#takeMedicineRecord_takeTimes').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
				}
				if($('#takeMedicineRecord_takeTimes').val()=="其他"){
					$('#tmr_takeTimes_other').show("normal");
				}else{
					$('#tmr_takeTimes_other').hide("normal");
				}
				
				$.ajax({
					type:"post",
					url:"takeMedicineRecord/findTakeMedicineRecord",
					data:"uuid="+uuid,
					success:function(data){
						if(data == "error"){
							showMessage("修改失败，请刷新后重试。。",$('#takeMedicineRecord_showMessage'));
						}else{
							$('#takeMedicineRecord_submit').hide("fast");
							$('#takeMedicineRecord_modify').show("fast");
							$("#takeMedicineRecord_table_toolbar").hide("normal");
							$('#takeMedicineRecord_form_toolbar').show("normal");
							var json = $.parseJSON("[" + data + "]");
							var takeMedicineRecord = json[0];
							$('#takeMedicineRecord_uuid').val(takeMedicineRecord.uuid);
							$('#takeMedicineRecord_opNo').val(takeMedicineRecord.oldPeopleNo);
							if(takeMedicineRecord.beforeEat=="是"){
								document.getElementById('takeMedicineRecord_beforeEat1').checked=true;
							}else{
								document.getElementById('takeMedicineRecord_beforeEat2').checked=true;
							}
							$('#takeMedicineRecord_takeTimes').append($("<option selected=\"\" value=\""+takeMedicineRecord.takeTimes+"\">"+takeMedicineRecord.takeTimes+"</option>"));
							//var beginDate = timeFormat(takeMedicineRecord.beginDate);
							$('#takeMedicineRecord_beginDate').val(takeMedicineRecord.beginDate);
							//timeFormat() is format yyyy-mm-dd to mm/dd/yyyy
							//var endDate = timeFormat(takeMedicineRecord.endDate);
							$('#takeMedicineRecord_endDate').val(takeMedicineRecord.endDate);
							if(takeMedicineRecord.ifPrescriptionMedicine=="是"){
								document.getElementById('takeMedicineRecord_prescription1').checked=true;
							}else{
								document.getElementById('takeMedicineRecord_prescription2').checked=true;
							}
							$('#takeMedicineRecord_takeTime').val(takeMedicineRecord.takeTime);
							$('#takeMedicineRecord_medicineNum').val(takeMedicineRecord.medicineNum);
							$('#takeMedicineRecord_medicineName').val(takeMedicineRecord.medicineName);
							
						}
					},
					error:function(){
						location.href = "pages/500.jsp";
					}
				});
			}
		},
		error:function(){
			location.href="pages/500.jsp";
		}
	});
};

/*click 'delete' button will trigger this function to delete a data*/
this.takeMedicineRecord_table_delete = function(){
	var uuid = $(this).parent().prevAll('td').get(7).innerHTML;
	var tr = $(this).parent().parent();
	if(!confirm("确定删除该记录吗？")){
		return false;
  	}
	$.ajax({
		type:"post",
		url:"takeMedicineRecord/deleteTakeMedicineRecord",
		data:"uuid="+uuid,
		success:function(data){
			var result = data;
			if(result=="success"){
				showMessage("删除成功",$('#takeMedicineRecord_showMessage'));
				tr.remove();
			}else{
				showMessage("删除失败，请刷新后重试。。",$('#takeMedicineRecord_showMessage'));
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
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
