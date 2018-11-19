$(function(){
	$('[data-form=datepicker]').datepicker();
//	$('#healthinfo_oldPeople_search').show('normal');
	oldPeople_createtable(1,5,null);
	
	$('#healthinfo_oldPeople_search').click(function(){
		var healthinfo_oldPeople_opName = $.trim($('#healthinfo_oldPeople_opName_search').val());
		oldPeople_createtable(1,5,healthinfo_oldPeople_opName);
	});
});
/*检查信息添加 */
$('#checkRecord_oldPeople_add').click(function(){	
	oldPeopleCheckReord_table_add();
});
/*病例信息添加 */
$('#sickRecord_oldPeople_add').click(function(){
	var sickRecord_oldPeople_uuid = $('#oUuid').text();
	var oName = $('#oName').text();
//	alert(sickRecord_oldPeople_uuid);
	$('#add_sickRecord_Uuid').val(sickRecord_oldPeople_uuid);
	$('#sickRecord_table_toolbar').hide("normal");
	$('#sickRecord_form_toolbar').show("normal");
	$('#oName').text(oName);
	/*$('#oUuid').text(oUuid);*/
	
	//病症类型下拉列表系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getSickTypeByCombobox",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				$('#add_sickRecord_sickRecord option').remove();
				$.each(data, function(index, item) {
					$('#add_sickRecord_sickRecord').append($("<option value=\""+item.metaValue+"\">"+item.metaValue+"</option>"));
					$('#add_sickRecord_sickRecord option').eq(0).attr("selected","selected");
				});
			}
		}
	});
	
	//疾病原因系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getSickReasonByParam",
		dataType:"json",
		async:false,
		success:function(data){
//			var data = $.parseJSON(data);
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				sickRecord_sickReson_editor.setContent("");
				$.each(data, function(index, item) {
					sickRecord_sickReson_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	//治疗方案系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getCureInfoByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				sickRecord_cureInfo_editor.setContent("");
				$.each(data, function(index, item) {
					sickRecord_cureInfo_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	//陪同人信息系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getCarePeopleInfoByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				sickRecord_carePeopleInfo_editor.setContent("");
				$.each(data, function(index, item) {
					sickRecord_carePeopleInfo_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	//检查项目系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getCheckProjectInfoByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				sickRecord_checkProjectInfo_editor.setContent("");
				$.each(data, function(index, item) {
					sickRecord_checkProjectInfo_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	//检查信息系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getCheckInfoByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				sickRecord_checkInfo_editor.setContent("");
				$.each(data, function(index, item) {
					sickRecord_checkInfo_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	//检查结果系统参数获取
	$.ajax({
		type:"post",
		url:"sickRecord/getCheckResulByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				sickRecord_checkResult_editor.setContent("");
				$.each(data, function(index, item) {
					sickRecord_checkResult_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
});
/*健康信息添加*/
$('#healthInfo_oldPeople_add').click(function(){
//	alert("healthInfo_oldPeople_add");
	var oldUuid = $('#oUuid').text();
	var oName = $('#oName').text();
	
	$.ajax({
		type:"post",
		url:"HealthInfo/getCureInfoHtmlSourceByParam",
		dataType:"json",
		async:false,
		success:function(data){
//			var data = $.parseJSON(data);
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{				
				healthinfo_mtm_ue.setContent("");
				$.each(data, function(index, item) {
					healthinfo_mtm_ue.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
//	
	$('#oldPeopleUuid').val(oldUuid);
	$('#healthInfo_table_toolbar2').hide("normal");
	$('#healthInfo_table_toolbar').hide("normal");
	$('#healthInfo_form_toolbar').show("normal");

	$('#oName').text(oName);
});

/*
 * 老人健康检查信息表格显示
 */
this.checkRecord_createtable = function(oUuid){
	$.ajax({
		url:"oldPeopleCheckRecord/getOldPeopleCheckRecord",
		data:"checkRecord.oldPeople.uuid="+oUuid,
		type:"post",
		dataType:"json",
		success:function(data){
			var table = $('#checkRecord_table');
			$("#checkRecord_table tr").remove();
			table.append($(
					"<tr><th style=\"display:none\"><span class=\"badge badge-success\">uuid</span></th>"+
					"<th style=\"display:none\"><span class=\"badge badge-success\">老人uuid</span></th>"+
					"<th><span class=\"badge\">序号</span></th>"+
					"<th><span class=\"badge\">检查项目</span></th>"+
					"<th><span class=\"badge\">检查人编号</span></th>"+
					"<th><span class=\"badge\">检查人姓名</span></th>"+
					"<th><span class=\"badge\">复查日期</span></th>"+
					"<th style=\"display:none\"><span class=\"\">检查结果</span></th>"+
					"<th style=\"display:none\"><span class=\"badge\">体检信息</span></th>"+
					"<th><span class=\"badge\">其他</span></th>"+
					"<th colspan=\"2\"><span class=\"badge\">操作</span></th></tr>"
			));
			if(data=="error"){
            	location.href = "pages/500.jsp";
            }else{
//            	alert(data);
//            	d= $.parseJSON(data);
//            	alert(d);
            	$.each(data,function(index,item){
            		var j=index+1;
            		table.append($("<tr><td style=\"display:none\">"+
                        			item.uuid
                        			+ " </td><td style=\"display:none\">"+
                        			oUuid+"</td><td>"+
                        			j+"</td><td width=\"100px\">"+
                        			item.checkProject
                        			+ "</td><td width=\"100px\">" +
                        			item.workPeopleNo
                        			+ "</td><td width=\"100px\">" +
                        			item.workPeopleName
                        			+ "</td><td width=\"100px\">" +
                        			item.nextCheckTime
                        			+ "</td><td width=\"100px\" style=\"display:none\">" +
                        			item.checkResultHtmlSource
                        			+"</td><td width=\"100px\" style=\"display:none\">" +
                        			item.checkInfoHtmlSource
                        			+"</td><td width=\"100px\"> <a href=\"javascript:void(0)\"><img src=\"pages/img/detail.png\"></a></td>"
            						+"<td width=\"80px\"> <a href=\"javascript:void(0)\"><i class=\"icofont-edit\"></i> </a></td>"
            						+"<td width=\"80px\"> <a href=\"javascript:void(0)\"><i class=\"icon-trash\"></i></a></td></tr>"
            		));
            		$('#checkRecord_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(9).find('a').click(oldPeopleCheckRecord_table_scanDetail);
            		$('#checkRecord_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(10).find('a').click(oldPeopleCheckRecord_table_modify);
            		$('#checkRecord_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(11).find('a').click(oldPeopleCheckRecord_table_del);
//            		$('#checkRecord_table tr:eq('+j+') td:eq(9) a').click(oldPeopleCheckRecord_table_scanDetail);
//            		$('#checkRecord_table tr:eq('+j+') td:eq(10) a').click(oldPeopleCheckRecord_table_modify);
//            		$('#checkRecord_table tr:eq('+j+') td:eq(11) a').click(oldPeopleCheckRecord_table_del);
            		
            	});
            }
		}
	});
}
 
//老人检查信息详细显示
this.oldPeopleCheckRecord_table_scanDetail = function(){
//	alert("oldPeopleCheckRecord_table_scanDetail");
	var cr_checkResultHtmlSource = $(this).parent().prevAll('td').get(1).innerHTML;
	var cr_checkInfoHtmlSource = $(this).parent().prevAll('td').get(0).innerHTML;
	
//	alert(cr_checkInfoHtmlSource);
	$('#crDetail_checkResultHtmlSource').empty();
	$('#crDetail_checkInfoHtmlSource').empty();
	$('#crDetail_checkResultHtmlSource').append(cr_checkResultHtmlSource);
	$('#crDetail_checkInfoHtmlSource').append(cr_checkInfoHtmlSource);
	
	$('#oldPeopleCheckRecord_detail').show('normal');
	$('#checkRecord_table_toolbar').hide('normal');
};

//老人检查信息修改
this.oldPeopleCheckRecord_table_modify = function(){
//	alert("oldPeopleCheckRecord_table_modify");
	var uuid = $(this).parent().prevAll('td').get(9).innerHTML;
	var oUuid = $(this).parent().prevAll('td').get(8).innerHTML;
	var cr_checkProject = $(this).parent().prevAll('td').get(6).innerHTML;
	var cr_workPeopleNo = $(this).parent().prevAll('td').get(5).innerHTML;
	var cr_workPeopleName = $(this).parent().prevAll('td').get(4).innerHTML;
	var cr_nextCheckTime = $(this).parent().prevAll('td').get(3).innerHTML;
	var cr_checkResultHtmlSource = $(this).parent().prevAll('td').get(2).innerHTML;
	var cr_checkInfoHtmlSource = $(this).parent().prevAll('td').get(1).innerHTML;
	
	$('#modify_checkRecordUuid').val(uuid);
	$('#modify_oldPeopleCheckRecord_oldPeopleUuid').val(oUuid);
	$('#modify_oldPeopleCheckRecord_checkProject').val(cr_checkProject);
	$('#modify_oldPeopleCheckRecord_workPeopleNo').val(cr_workPeopleNo);
	$('#modify_oldPeopleCheckRecord_workPeopleName').val(cr_workPeopleName);
	$('#modify_oldPeopleCheckRecord_nextCheckTime').val(cr_nextCheckTime);
	modify_oldPeopleCheckRecord_checkResultHtmlSource.setContent(cr_checkResultHtmlSource);
	modify_oldPeopleCheckRecord_checkInfoHtmlSource.setContent(cr_checkInfoHtmlSource);
	
	$('#oldPeopleCheckRecord_modify').show('normal');
	$('#checkRecord_table_toolbar').hide('normal');
	$('#oldPeopleCheckRecord_detail').hide('normal');
	$('#healthInfo_table_toolbar').hide("normal");
	

};

//老人检查信息删除
this.oldPeopleCheckRecord_table_del = function(){
	if(!confirm("确定删除该记录吗？")){
		return false;
  	}
	var uuid = $(this).parent().prevAll('td').get(10).innerHTML;
	var oUuid = $(this).parent().prevAll('td').get(9).innerHTML;
//	alert(uuid);
	$.ajax({
		url: "oldPeopleCheckRecord/delOldPeopleCheckRecord",
		data:"checkRecord.uuid="+uuid,
		type: "post",
		success:function(data){
			if(data == "error"){
				showMessage("删除失败，请重试!!!",$('#healthInfo_showMessage'));
			}else{
				showMessage("删除成功!",$('#healthInfo_showMessage'));
				checkRecord_createtable(oUuid);
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
		
	});
};
/*
 * 动态表格的显示(老人病例信息表格)
 */
this.sickRecord_createtable = function(oUuid){
	$.ajax({
		url:"sickRecord/getOldPeopleSickRecord",
		data:"sickRecord.oldPeople.uuid="+oUuid,
		type:"post",
		dataType:"json",
		success:function(data){
			var table = $("#sickRecord_table");
			$("#sickRecord_table tr").remove();
			table.append($(
				"<tr><th style=\"display:none\"><span class=\"badge badge-success\">uuid</span></th>"+
				"<th style=\"display:none\"><span class=\"badge badge-success\">老人uuid</span></th>"+
				"<th><span class=\"badge\">序号</span></th>"+
				"<th><span class=\"badge\">检查日期</span></th>"+
				"<th><span class=\"badge\">住院日期</span></th>"+
				"<th><span class=\"badge\">出院日期</span></th>"+
				"<th><span class=\"badge\">病症类型</span></th>"+
				"<th><span class=\"badge\">主治医师</span></th>"+
				"<th style=\"display:none\"><span class=\"badge\">发病原因</span></th>"+
				"<th style=\"display:none\"><span class=\"badge\">治疗方案</span></th>"+
				"<th style=\"display:none\"><span class=\"badge\">陪同人信息</span></th>"+
				"<th style=\"display:none\"><span class=\"badge\">检查项目</span></th>"+
				"<th style=\"display:none\"><span class=\"badge\">检查结果</span></th>"+
				"<th style=\"display:none\"><span class=\"badge\">检查资料</span></th>"+
				"<th width=\"100px\"><span class=\"badge\">其他</span></th>"+
				"<th colspan=\"2\"><span class=\"badge\">操作</span></th>"+
				"</tr>"
			));
			if(data=="error"){
            	location.href = "pages/500.jsp";
            }else{
            	$.each(data, function(index, item) {
            		var j= index+1;
            		table.append(
                    	$("<tr><td style=\"display:none\">"+
                    			item.uuid
                    			+ " </td><td style=\"display:none\">"+
                    			oUuid+"</td><td>"+
                    			j+"</td><td width=\"100px\">"+
                    			item.checkDate
                    			+ "</td><td width=\"100px\"> " +
                    			item.inHospitalDate
                    			+ "</td><td width=\"100px\"> " +
                    			item.outHospitalDate
                    			+ "</td><td width=\"100px\"> " +
                    			item.sickType
                    			+ "</td><td width=\"100px\"> " +
                    			item.doctor
                    			+ "</td><td width=\"100px\" style=\"display:none\"> " +
                    			item.sickResonHtmlSource
                    			+ "</td><td width=\"100px\" style=\"display:none\"> " +
                    			item.cureInfoHtmlSource
                    			+ "</td><td width=\"100px\" style=\"display:none\"> " +
                    			item.carePeopleInfoHtmlSource
                    			+ "</td><td width=\"100px\" style=\"display:none\"> " +
                    			item.checkProjectInfoHtmlSource
                    			+ "</td><td width=\"100px\" style=\"display:none\"> " +
                    			item.checkResultHtmlSource
                    			+ "</td><td width=\"100px\" style=\"display:none\"> " +
                    			item.checkInfoHtmlSource
                    			+"</td><td width=\"100px\"> <a href=\"javascript:void(0)\"><img src=\"pages/img/detail.png\"></a></td>"
        						+"<td width=\"80px\"> <a href=\"javascript:void(0)\"><i class=\"icofont-edit\"></i></a></td>"
        						+"<td width=\"80px\">  <a href=\"javascript:void(0)\"><i class=\"icon-trash\"></i></a></td></tr>"
        					));
            		$('#sickRecord_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(14).find('a').click(sickRecord_table_scanDetail);
            		$('#sickRecord_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(15).find('a').click(sickRecord_table_modify);
            		$('#sickRecord_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(16).find('a').click(sickRecord_table_del);
//            		$('#sickRecord_table tr:eq('+j+') td:eq(14) a').click(sickRecord_table_scanDetail);
//            		$('#sickRecord_table tr:eq('+j+') td:eq(15) a').click(sickRecord_table_modify);
//            		$('#sickRecord_table tr:eq('+j+') td:eq(16) a').click(sickRecord_table_del);
            	});
            }
		}
	});
};
//查看详细病例
this.sickRecord_table_scanDetail = function(){
	var sickRecord_sickResonHtmlSource = $(this).parent().prevAll('td').get(5).innerHTML;
	var sickRecord_cureInfoHtmlSource = $(this).parent().prevAll('td').get(4).innerHTML;
	var sickRecord_carePeopleInfoHtmlSource = $(this).parent().prevAll('td').get(3).innerHTML;
	var sickRecord_checkProjectInfoHtmlSource = $(this).parent().prevAll('td').get(2).innerHTML;
	var sickRecord_checkResultHtmlSource = $(this).parent().prevAll('td').get(1).innerHTML;
	var sickRecord_checkInfoHtmlSource = $(this).parent().prevAll('td').get(0).innerHTML;
	
	$('#srDetail_sickResonHtmlSource').empty();
	$('#srDetail_cureInfoHtmlSource').empty();
	$('#srDetail_carePeopleInfoHtmlSource').empty();
	$('#srDetail_checkProjectInfoHtmlSource').empty();
	$('#srDetail_checkResultHtmlSource').empty();
	$('#srDetail_checkInfoHtmlSource').empty();
	
	$('#srDetail_sickResonHtmlSource').append(sickRecord_sickResonHtmlSource);
	$('#srDetail_cureInfoHtmlSource').append(sickRecord_cureInfoHtmlSource);
	$('#srDetail_carePeopleInfoHtmlSource').append(sickRecord_carePeopleInfoHtmlSource);
	$('#srDetail_checkProjectInfoHtmlSource').append(sickRecord_checkProjectInfoHtmlSource);
	$('#srDetail_checkResultHtmlSource').append(sickRecord_checkResultHtmlSource);
	$('#srDetail_checkInfoHtmlSource').append(sickRecord_checkInfoHtmlSource);
	$('#sickRecord_detail').show("normal");
	$('#sickRecord_table_toolbar').hide("normal");
};
//修改病例信息
this.sickRecord_table_modify = function(){
//	alert("sickRecord_table_modify");
	var sickRecord_uuid = $(this).parent().prevAll('td').get(14).innerHTML;
	var sickRecord_checkDate = $(this).parent().prevAll('td').get(11).innerHTML;
	var sickRecord_inHospitalDate = $(this).parent().prevAll('td').get(10).innerHTML;
	var sickRecord_outHospitalDate = $(this).parent().prevAll('td').get(9).innerHTML;
	var sickRecord_sickType = $(this).parent().prevAll('td').get(8).innerHTML;
	var sickRecord_doctor = $(this).parent().prevAll('td').get(7).innerHTML;
	var sickRecord_sickResonHtmlSource = $(this).parent().prevAll('td').get(6).innerHTML;
	var sickRecord_cureInfoHtmlSource = $(this).parent().prevAll('td').get(5).innerHTML;
	var sickRecord_carePeopleInfoHtmlSource = $(this).parent().prevAll('td').get(4).innerHTML;
	var sickRecord_checkProjectInfoHtmlSource = $(this).parent().prevAll('td').get(3).innerHTML;
	var sickRecord_checkResultHtmlSource = $(this).parent().prevAll('td').get(2).innerHTML;
	var sickRecord_checkInfoHtmlSource = $(this).parent().prevAll('td').get(1).innerHTML;

	$('#modify_sickRecordUuid').val(sickRecord_uuid);
	$('#modify_sickRecord_checkDate').val(sickRecord_checkDate);
	$('#modify_sickRecord_inHospitalDate').val(sickRecord_inHospitalDate);
	$('#modify_sickRecord_outHospitalDate').val(sickRecord_outHospitalDate);
	$('#modify_sickRecord_sickType').val(sickRecord_sickType);
	$('#modify_sickRecord_doctor').val(sickRecord_doctor);
	
	SR_sickResonHtmlSource.setContent(sickRecord_sickResonHtmlSource);
	SR_cureInfoHtmlSource.setContent(sickRecord_cureInfoHtmlSource);
	SR_carePeopleInfoHtmlSource.setContent(sickRecord_carePeopleInfoHtmlSource);
	SR_checkProjectInfoHtmlSource.setContent(sickRecord_checkProjectInfoHtmlSource);
	SR_checkResultHtmlSource.setContent(sickRecord_checkResultHtmlSource);
	SR_checkInfoHtmlSource.setContent(sickRecord_checkInfoHtmlSource);
	
	$('#sickRecord_detail').hide("normal");
	$('#sickRecord_table_toolbar').hide("normal");
	$('#healthInfo_table_toolbar').hide("normal");
	$('#sickRecord_modify').show("normal");
	
	$.ajax({
		type:"post",
		url:"sickRecord/getSickTypeByCombobox",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				$('#modify_sickRecord_sickRecord option').remove();
				$.each(data, function(index, item) {
					$('#modify_sickRecord_sickRecord').append($("<option value=\""+item.metaValue+"\">"+item.metaValue+"</option>"));
					$('#modify_sickRecord_sickRecord option').eq(0).attr("selected","selected");
				});
			}
		}
	});
	
};
//删除病例信息
this.sickRecord_table_del = function(){
	if(!confirm("确定删除该记录吗？")){
		return false;
  	}
	var uuid = $(this).parent().prevAll('td').get(15).innerHTML;
	var oUuid = $(this).parent().prevAll('td').get(14).innerHTML;
//	alert(oUuid);
	$.ajax({
		url: "sickRecord/delSickRecord",
		data:"sickRecord.uuid="+uuid,
		type: "post",
		success:function(data){
			if(data == "error"){
				showMessage("删除失败，请重试!!!",$('#healthInfo_showMessage'));
			}else{
				showMessage("删除成功!",$('#healthInfo_showMessage'));
				sickRecord_createtable(oUuid);
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
		
	});
};

/*
 * 动态表格的显示(老人健康状况信息表格)
 */
this.healthInfo_createtable = function(oUuid) {
    $.ajax({
        url: "HealthInfo/getHealthInfo",
        data:"healthinfo.oldPeople.uuid="+oUuid,
        type: "post",
        dataType:"json",
        success: function (data) {
        	//alert(data);
            var table = $("#healthinfo_table");
            $("#healthinfo_table tr").remove();
            table.append($("<tr><th style=\"display:none\"><span class=\"badge badge-success\">uuid</span></th>" +
            		"<th style=\"display:none\"><span class=\"badge badge-success\">老人uuid</span></th>"+
            		"<th><span class=\"badge\">序号</span></th>"+
            		"<th><span class=\"badge\">病例名称</span></th>" +
            		"<th><span class=\"badge\">发病日期</span></th>" +
            		"<th><span class=\"badge\">治愈日期</span></th>" +
            		"<th style=\"display:none\"><span class=\"badge\">治疗方案</span></th>" +
            		"<th align=\"center\"><span class=\"badge\">治疗方案 与 后遗症 </span></th>" +
            		"<th colspan=\"2\" align=\"center\"><span class=\"badge\">操作 </span></th></tr>"
            ));
            if(data=="error"){
            	location.href = "pages/500.jsp";
            }else{
            	//var healthInfojson = $.parseJSON(data);
            	
            	$.each(data, function(index, item) {
            		var j= index+1;
            		table.append(
                    	$("<tr><td style=\"display:none\">"+
                    			item.uuid
                    			+"</td><td style=\"display:none\">"+
                    			oUuid
                    			+"</td><td>"+
                    			j
                    			+"</td><td width=\"100px\">"+
                    			item.sickName
                    			+ "</td><td width=\"100px\"> " +
                    			item.sickDate
                    			+ "</td><td width=\"100px\"> " +
                    			item.cureDate
                    			+ "</td><td style=\"display:none\" width=\"100px\">" +
                    			item.sequela
                    			+ "</td><td style=\"display:none\" width=\"100px\">"+
                    			item.cureInfoHtmlSource
                    			+ "</td>" 
                    			+"<td width=\"100px\"> <a href=\"javascript:void(0)\"><img src=\"pages/img/detail.png\"></a> </td>"
        						+"<td width=\"100px\"> <a href=\"javascript:void(0)\"><i class=\"icofont-edit\"></i></a> </td>"
        						+"<td width=\"100px\"> <a href=\"javascript:void(0)\"><i class=\"icon-trash\"></i></a></td></tr>"
        					));
            			$('#healthinfo_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(8).find('a').click(healthinfo_table_detail);
            			$('#healthinfo_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(9).find('a').click(healthInfo_table_modify);
            			$('#healthinfo_table tr:eq('+0+')').nextAll().eq(index).children('td').eq(10).find('a').click(healthInfo_table_del);
            			
//            			$('#healthinfo_table tr:eq('+j+') td:eq(10) a').click(healthInfo_table_del);
//            			$('#healthinfo_table tr:eq('+j+') td:eq(8) a').click(healthinfo_table_detail);
                });
            }
        }
    });
};

//查看详细健康信息
this.healthinfo_table_detail = function(){
	
	var hdSickName = $(this).parent().prevAll('td').get(4).innerHTML;
	var hdCureInfoHtmlSource = $(this).parent().prevAll('td').get(0).innerHTML;
	var hdSequela = $(this).parent().prevAll('td').get(1).innerHTML;

	$('#hdCureInfoHtmlSource').empty();
	$('#hdSequela').empty();
	
	$('#hdSickName').text(hdSickName);
	$('#hdCureInfoHtmlSource').append(hdCureInfoHtmlSource);
	$('#hdSequela').append(hdSequela);
	$('#healthInfo_table_toolbar2').hide("normal");
	$('#healthInfo_form_detail_toolbar').show("normal");
	
	
};

//修改健康信息
this.healthInfo_table_modify = function(){ 	
	var oUuid=$(this).parent().prevAll('td').get(7).innerHTML;
	var hUuid=$(this).parent().prevAll('td').get(8).innerHTML;
	var hSickName=$(this).parent().prevAll('td').get(5).innerHTML;
	var hSickDate=$(this).parent().prevAll('td').get(4).innerHTML;
	var hCureDate=$(this).parent().prevAll('td').get(3).innerHTML;
	var hCureInfoHtmlSource=$(this).parent().prevAll('td').get(1).innerHTML;
	var hSequela=$(this).parent().prevAll('td').get(2).innerHTML;
	
	$('#oldPeopleUuid1').val(oUuid);
	$('#healthInfoUuid').val(hUuid);
	$('#healthInfoSickName').val(hSickName);
	$('#healthInfoSickDate').val(hSickDate);
	$('#healthInfoCureDate').val(hCureDate);
	healthinfo_mtm_ue1.setContent(hCureInfoHtmlSource);
	$('#healthInfoSequela').val(hSequela);
	
	$('#healthInfo_form_detail_toolbar').hide("normal");
	$('#healthInfo_table_toolbar').hide("normal");
	$('#healthInfo_table_toolbar2').hide("normal");
	$('#healthInfo_form_modify_toolbar').show("normal");
};

//删除健康信息
this.healthInfo_table_del = function(){
	if(!confirm("确定删除该记录吗？")){
		return false;
  	}
	var hUuid=$(this).parent().prevAll('td').get(9).innerHTML;
	var oUuid=$(this).parent().prevAll('td').get(8).innerHTML;
//	alert(hUuid);
//	alert(oUuid);
	$.ajax({
		type: "post",
		url: "HealthInfo/delHealthInfo",
		data:"healthinfo.uuid="+hUuid,
		success:function(data){
			if(data == "error"){				
					showMessage("删除失败，请重试!!!",$('#healthInfo_showMessage'));	
			}else{				
					showMessage("删除成功!",$('#healthInfo_showMessage'));
					healthInfo_createtable(oUuid);				
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
		
	});
};

/*
 * 动态表格的显示和分页(老人信息表格)
 */
this.oldPeople_createtable = function(pageIndex, spanInterval,oldPeopleName) {
    $.ajax({
        url: "HealthInfo/queryAllPeopleByPage",
        data: { "nowPage": pageIndex,"oldPeopleName": oldPeopleName},
        type: "post",
        success: function (data) {
            var table = $("#healthInfo_oldPeople_table");
            $("#healthInfo_oldPeople_table tr").remove();
            table.append($("<tr><th style=\"display:none\"><span class=\"badge\">老人uuid</span></th>" +
            		"<th><span class=\"badge\">老人编号</span></th>" +
            		"<th><span class=\"badge\">老人姓名</span></th>" +
            		"<th colspan=\"4\"><span class=\"badge\">操作</span></th></tr>"));
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
            				"<tr class=\"gradeA\"><td style=\"display:none\">" +
            						listData[i].uuid
            						+ "</td><td>" +
            						listData[i].oldPeopleNo
            						+ "</td><td>" +
            						listData[i].oldPeopleName
            						+ "</td>"
            						+"<td><a href=\"javascript:void(0)\"><span><button>查看病史</button></span></a></td>"
            						+"<td><a href=\"javascript:void(0)\"><span><button>查看病例</button></span></a><td>"
            						+"<td><a href=\"javascript:void(0)\"><span><button>检查记录</button></span></a></td>"
            						/*+"<div class='btn-toolbar' style='margin: 0;'>"
                                    	+"<div class='btn-group'>"
	                                     +"<button class='btn dropdown-toggle' data-toggle='dropdown'>查看 <span class='caret'></span></button>"
	                                     +"<ul class='dropdown-menu'>"
	                                     	 +"<li><a href=\"oldPeople/getMoreInfo?opUuid="+listData[i].uuid+"\" target=\"_blank\">查看</a></li>"
	                                         +"<li><a href='javascript:void(0)'>老人健康信息</a></li>"
	                                         +"<li><a href='javascript:void(0)'>老人病例</a></li>"
	                                         +"<li><a href='javascript:void(0)'>老人检查信息</a></li>"
	                                     +"</ul>"
	                                    +"</div>"
	                                +"</div>"*/
            						
            						/*+"<td><a href='javascript:void(0)'><span><button>添加健康信息</button></span></a></td>"
    								+"<td><a href='javascript:void(0)'><span><button>添加病例</button></span></a></td>"
    								+"<td><a href='javascript:void(0)'><span><button>添加检查信息</button></span></a></td>"*/
    								+"</tr>"
            		);
//            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(3) div:eq(0) div:eq(0) ul:eq(0) li:eq(0) a').click(healthInfo_oldPeople_table_scan);
//            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(3) div:eq(0) div:eq(0) ul:eq(0) li:eq(1) a').click(sickRecord_table_scan);
//            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(3) div:eq(0) div:eq(0) ul:eq(0) li:eq(2) a').click(checkRecord_table_scan);
            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(3) a').click(healthInfo_oldPeople_table_scan);
            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(4) a').click(sickRecord_table_scan);
            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(6) a').click(checkRecord_table_scan);
//            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(7) a').click(healthInfo_oldPeople_table_add);
//            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(8) a').click(sickRecord_table_add);
//            		$('#healthInfo_oldPeople_table tr:eq('+j+') td:eq(9) a').click(oldPeopleCheckReord_table_add);
            		
            	}
            	$('#healthInfo_oldPeople_paging ul').remove();
            	$('#healthInfo_oldPeople_paging').append("<ul></ul>");
            	var $pager_ul = $('#healthInfo_oldPeople_paging ul');
            	paggin($pager_ul,nowPage,totalPage,totalNum,spanInterval,oldPeople_createtable,oldPeopleName);
            }
        }
    });
};

//检查信息的查看
this.checkRecord_table_scan = function(){
//	alert("checkRecord_table_scan");
	var oUuid = $(this).parent().prevAll('td').get(5).innerHTML;
	var oName = $(this).parent().prevAll('td').get(3).innerHTML;
	
	$('#main_checkrecord').show('normal');
	$('#healthInfo_table_toolbar').hide("normal");
	$('#checkRecord_table_toolbar').show("normal");
	$('#oName').text(oName);
	$('#oUuid').text(oUuid);
	checkRecord_createtable(oUuid);
};


/**
 * 病例查看
 */
this.sickRecord_table_scan = function(){
//	alert("sickRecord_table_scan");
	var oUuid = $(this).parent().prevAll('td').get(3).innerHTML;
	var oName = $(this).parent().prevAll('td').get(1).innerHTML;
//	alert(oUuid+"=="+oName);
	$('#main_sickrecord').show("normal");
	$('#healthInfo_table_toolbar').hide("normal");
	$('#sickRecord_table_toolbar').show("normal");
	
	sickRecord_createtable(oUuid);
	
	$('#oName').text(oName);
	$('#oUuid').text(oUuid);
};



//检查记录添加
var oldPeopleCheckReord_table_add = function(){

//	var checkRecord_oldPeople_uuid = $(this).parent().prevAll('td').get(7).innerHTML;
//	var oName = $(this).parent().prevAll('td').get(5).innerHTML;
	var checkRecord_oldPeople_uuid = $('#oUuid').text();
	var oName = $('#oName').text();
	//alert(checkRecord_oldPeople_uuid);
	$('#checkRecord_oldPeople_uuid').val(checkRecord_oldPeople_uuid);
	$('#checkRecord_table_toolbar').hide("normal");
	$('#oldPeopleCheckRecord_form_toolbar').show("normal");
	$('#oName').text(oName);
	
	//检查结果系统参数获取
	$.ajax({
		type:"post",
		url:"oldPeopleCheckRecord/getCheckResultByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				chaeckRecord_checkResult_editor.setContent("");
				$.each(data, function(index, item) {
					chaeckRecord_checkResult_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	//检查信息系统参数获取
	$.ajax({
		type:"post",
		url:"oldPeopleCheckRecord/getCheckInfoByParam",
		dataType:"json",
		async:false,
		success:function(data){
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				checkRecord_checkInfo_editor.setContent("");
				$.each(data, function(index, item) {
					checkRecord_checkInfo_editor.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
};

//检查检查人编号是否存在
this.checkExistEmp = function(empNo,messageLabel){
	if(!(empNo==null||empNo=="")){
		$.ajax({
			type:"post",
			url:"oldPeopleCheckRecord/checkIsExistEmployee",
			data:"empNo="+empNo,
			success:function(data){
				var msg = data;
				if(msg == "error"){
					showMessage("检查人编号不存在，请检查后重新输入！",$(messageLabel));
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
	}
}

//健康信息查看
this.healthInfo_oldPeople_table_scan = function(){
//	alert("healthInfo_oldPeople_table_scan");
//	var oUuid = $(this).parent().parent().parent().parent().parent().prevAll('td').get(2).innerHTML;
//	var oName = $(this).parent().parent().parent().parent().parent().prevAll('td').get(0).innerHTML;
	var oUuid = $(this).parent().prevAll('td').get(2).innerHTML;
	var oName = $(this).parent().prevAll('td').get(0).innerHTML;

	$('#main_healthinfo').show("normal");
	$('#healthInfo_table_toolbar').hide("normal");
	$('#healthInfo_add').show("normal");
	$('#healthInfo_table_toolbar2').show("normal");
	healthInfo_createtable(oUuid);
	
	$('#oUuid').text(oUuid);
	$('#oName').text(oName);
};
//健康信息添加
/*this.healthInfo_oldPeople_table_add = function(){
	var oldUuid = $(this).parent().prevAll('td').get(5).innerHTML;
	var oName = $(this).parent().prevAll('td').get(3).innerHTML;
	//alert(oldUuid);
	$.ajax({
		type:"post",
		url:"HealthInfo/getCureInfoHtmlSourceByParam",
		dataType:"json",
		async:false,
		success:function(data){
//			var data = $.parseJSON(data);
			if(data=="error"){
				location.href = "pages/500.jsp";
			}else{
				healthinfo_mtm_ue.setContent("");
				$.each(data, function(index, item) {
					healthinfo_mtm_ue.setContent(item.metaValueHtmlSource);
				});
			}
		}
	});
	
	$('#oldPeopleUuid').val(oldUuid);
	$('#healthInfo_table_toolbar').hide("normal");
	$('#healthInfo_form_toolbar').show("normal");

	$('#oName').text(oName);
	
	
};*/

/*
 * 表格分页方法
 */
this.paggin = function($pager_ul,nowPage,totalPage,totalNum,spanInterval,PageClick,oldPeopleName){
	//添加第一页
    /*if (nowPage > 1){
        var first = $("<li><a href='javascript:void(0)'>第一页</a></li>").click(function () {
        	PageClick(1, spanInterval,oldPeopleName);
        	return false;
        });
        $pager_ul.append(first);
    }else {
    	$pager_ul.append("<li><span class='disabled'>第一页</span></li>");
    }*/
    //添加上一页
    if (nowPage > 1){
    	var pre = $("<li id='prev' class='prev'><a href='javascript:void(0)' class='pageA' style='cursor:pointer;'>上一页</a></li>").click(function () {
    		PageClick(nowPage-1, spanInterval,oldPeopleName);
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
                PageClick($(this).text(), spanInterval,oldPeopleName);
                return false;
            });
            $pager_ul.append(a);
        }
    }
    //下一页
    if (nowPage < totalPage) {
        var next = $("<li id='next' class='prev'><a href='javascript:void(0)' value='1' class='pageA' style='cursor:pointer;'>下一页</a></li>").click(function () {
        	PageClick(nowPage+1,spanInterval,oldPeopleName);
        	return false;
        });
        $pager_ul.append(next);
    }
    else {
    	$pager_ul.append("<li id='next' class='prev disabled'><span>下一页</span></li>");
    }
    $('#totalNum').text(totalNum);
    //最后一页
    /*if (nowPage >= totalPage) {
        $pager_ul.append("<li><span class='disabled'>最后一页</span></li>");
    }
    else {
        var last = $("<li><a href='javascript:void(0)'>最后一页</a></li>").click(function () {
            PageClick(totalPage,spanInterval,oldPeopleName);
            return false;
        });
        $pager_ul.append(last);
    }*/
};
