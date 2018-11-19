$().ready(function(){
	// datepicker
	$('[data-form=datepicker]').datepicker();

	
	/*init table*/
	oldPeople_createtable(1,5,null,null);
	
	$('#oldPeople_add').click(function(){
		$.ajax({
			type:"post",
			url:"oldPeople/findGrade",
			async:false,
			success:function(data){
				if(data=="error"){
					alert("因系统繁忙，操作失败，请稍后刷新重试");
				}else{
					$('#oldPeople_grade option').remove();
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						$('#oldPeople_grade').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
					}
					$('#oldPeople_grade option').eq(0).attr("selected","selected");
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
		$.ajax({
			type:"post",
			url:"oldPeople/findParty",
			async:false,
			success:function(data){
				if(data=="error"){
					alert("因系统繁忙，操作失败，请稍后刷新重试");
				}else{
					$('#oldPeople_party option').remove();
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						$('#oldPeople_party').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
					}
					$('#oldPeople_party option').eq(0).attr("selected","selected");
					if($('#oldPeople_party').val()=="其他"){
						$('#oldPeople_party_other').show("normal");
					}else{
						$('#oldPeople_party_other').hide("normal");
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		$.ajax({
			type:"post",
			url:"oldPeople/findMarriage",
			async:false,
			success:function(data){
				if(data=="error"){
					alert("因系统繁忙，操作失败，请稍后刷新重试");
				}else{
					$('#oldPeople_marriage option').remove();
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						$('#oldPeople_marriage').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
					}
					$('#oldPeople_marriage option').eq(0).attr("selected","selected");
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
		$.ajax({
			type:"post",
			url:"oldPeople/findFamilyParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到家庭信息参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						oldPeople_fihs_ue.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		$.ajax({
			type:"post",
			url:"oldPeople/findEmergencyPeopleParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到紧急联系地址参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						oldPeople_ephs_ue.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		$.ajax({
			type:"post",
			url:"oldPeople/findMoreHtmlSourceParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到附加项参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						oldPeople_mtm_ue.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		$('#oldPeople_table_toolbar').hide("normal");
		$('#oldPeople_form_toolbar').show("normal");
		$('#oldPeople_modify').hide("fast");
		$('#oldPeople_submit').show("fast");
	});
	

	
	/*click button which id is oldPeople_cancel will trigger the function event*/
	$('#oldPeople_cancel').click(function(){
		/*clear form*/
		form_clear($('#oldPeople_form'));
		$('#oldPeople_form_toolbar').hide("normal");
		$('#oldPeople_table_toolbar').show("normal");
	});
	
	/*click button which id is oldPeople_submit will trigger the function event*/
	$('#oldPeople_submit').click(function(){
		var oldPeople_opName = $('#oldPeople_opName').val();
		var oldPeople_idCard = $('#oldPeople_idCard').val();
		var oldPeople_roomNo = $('#oldPeople_roomNo').val();
		var oldPeople_bedNo = $('#oldPeople_bedNo').val();
		var oldPeople_grade = $('#oldPeople_grade').val();
		var oldPeople_empNo1 = $('#oldPeople_empNo1').val();
		var oldPeople_headImage = $('#oldPeople_headImage').val();
		var oldPeople_birthday = $('#oldPeople_birthday').val();
		var party = $('#oldPeople_party').val();
		if($('#oldPeople_party option:selected').val()=="其他"){
			party = $('#oldPeople_party_other').val();
		}
		var oldPeople_phone = $('#oldPeople_phone').val();
		//var oldPeople_walletMoney = $('#oldPeople_walletMoney').val();
		var oldPeople_email = $('#oldPeople_email').val();
		$('#oldPeople_tab_ul li').removeClass("active");
		$('div.tab-pane').removeClass("active in");
		$('#oldPeople_tab_ul li').eq(0).addClass("active");
		$('#boxtabpill-1').addClass("active in");
		/*judge the value whether is "" or not */
		if(isNull(oldPeople_opName)){
			showMessage("老人姓名不能为空，请填写.",$('#oldPeople_opName_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_idCard)){
			showMessage("身份证不能为空，请填写.",$('#oldPeople_idCard_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test(oldPeople_idCard)){
			showMessage("身份证格式错误，请重新填写.",$('#oldPeople_idCard_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_roomNo)){
			showMessage("房间号不能为空,请填写.",$('#oldPeople_roomNo_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_bedNo)){
			showMessage("床号不能为空，请填写.",$('#oldPeople_bedNo_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_grade)){
			showMessage("看护等级不能为空，请填写.",$('#oldPeople_grade_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_headImage)){
			showMessage("图片不能为空，请上传.",$('#oldPeople_headImage_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_empNo1)){
			showMessage("看护人1不能为空，请填写.",$('#oldPeople_empNo1_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_birthday)){
			showMessage("出生日期不能为空，请填写.",$('#oldPeople_birthday_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(party)){
			showMessage("政治面貌不能为空，请填写.",$('#oldPeople_party_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_phone)){
			showMessage("移动电话不能为空，请填写.",$('#oldPeople_phone_Label'));
		}else if(!/^1\d{10}$/.test(oldPeople_phone)){
			showMessage("移动电话格式错误，请重新填写.",$('#oldPeople_phone_Label'));
		}/*else if((oldPeople_walletMoney!="")&&(!isMoney(oldPeople_walletMoney))){
			showMessage("一卡通余额格式错误，请重新填写.",$('#oldPeople_walletMoney_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_phone").offset().top}, 1000);
		}*/else if((oldPeople_email!="")&&(!testEmail_Util(oldPeople_email))){
			showMessage("邮箱格式格式错误，请重新填写.",$('#oldPeople_email_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_phone").offset().top}, 1000);
		}else{
			$('#oldPeople_party').append($("<option selected=\"\" value=\""+party+"\">"+party+"</option>"));
			$('#oldPeople_form').removeAttr('action');
			$('#oldPeople_form').attr({'action':'oldPeople/addOldPeople'}).submit();			
			
		}
	});
	
	/*click button which id is oldPeople_modify will trigger the function event*/
	$('#oldPeople_modify').click(function(){
		var oldPeople_opName = $('#oldPeople_opName').val();
		var oldPeople_idCard = $('#oldPeople_idCard').val();
		var oldPeople_roomNo = $('#oldPeople_roomNo').val();
		var oldPeople_bedNo = $('#oldPeople_bedNo').val();
		var oldPeople_grade = $('#oldPeople_grade').val();
		var oldPeople_empNo1 = $('#oldPeople_empNo1').val();
		var oldPeople_headImage = $('#oldPeople_headImage').val();
		var oldPeople_birthday = $('#oldPeople_birthday').val();
		var party = $('#oldPeople_party').val();
		if($('#oldPeople_party option:selected').val()=="其他"){
			party = $('#oldPeople_party_other').val();
		}
		var oldPeople_phone = $('#oldPeople_phone').val();
		//var oldPeople_walletMoney = $('#oldPeople_walletMoney').val();
		$('#oldPeople_tab_ul li').removeClass("active");
		$('div.tab-pane').removeClass("active in");
		$('#oldPeople_tab_ul li').eq(0).addClass("active");
		$('#boxtabpill-1').addClass("active in");
		/*judge the value whether is "" or not */
		if(isNull(oldPeople_opName)){
			showMessage("老人姓名不能为空，请填写。。。",$('#oldPeople_opName_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_idCard)&&(oldPeople_idCard.length>=17)){
			showMessage("身份证输入错误，请重新填写。。。",$('#oldPeople_idCard_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_roomNo)){
			showMessage("房间号不能为空,请填写。。。",$('#oldPeople_roomNo_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_bedNo)){
			showMessage("床号不能为空，请填写...",$('#oldPeople_bedNo_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_grade)){
			showMessage("看护等级不能为空，请填写...",$('#oldPeople_grade_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_empNo1)){
			showMessage("看护人1不能为空，请填写...",$('#oldPeople_empNo1_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_birthday)){
			showMessage("出生日期不能为空，请填写...",$('#oldPeople_birthday_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_form").offset().top}, 1000);
		}else if(isNull(oldPeople_nation)){
			showMessage("国籍不能为空，请填写...",$('#oldPeople_nation_Label'));
		}else if(isNull(party)){
			showMessage("政治面貌不能为空，请填写...",$('#oldPeople_party_Label'));
		}else if(isNull(oldPeople_marriage)){
			showMessage("婚姻状况不能为空，请填写...",$('#oldPeople_marriage_Label'));
		}else if(isNull(oldPeople_phone)){
			showMessage("移动电话不能为空，请填写...",$('#oldPeople_phone_Label'));
		}/*else if((oldPeople_walletMoney!="")&&(!isMoney(oldPeople_walletMoney))){
			showMessage("一卡通余额格式不正确，请重新填写...",$('#oldPeople_walletMoney_Label'));
			$("html,body").animate({scrollTop: $("#oldPeople_bloodType").offset().top}, 1000);
		}*/else{
			$('#oldPeople_party').append($("<option selected=\"\" value=\""+party+"\">"+party+"</option>"));
			$('#oldPeople_form').removeAttr('action');
			$('#oldPeople_form').attr({'action':'oldPeople/modifyOldPeople'}).submit();
		}
	
	});
	/*click button  which id is oldPeople_search will trigger the function event*/
	$('#oldPeople_search').click(function(){
		var oldPeople_opNo  = $.trim($('#oldPeople_opNo_search').val());
		var oldPeople_opName = $.trim($('#oldPeople_opName_search').val());
		oldPeople_createtable(1,5,oldPeople_opName,oldPeople_opNo);
	});
	
});

/*
 * 要先保存基本信息，才能点击下个tab页
 */
$('.afterBase').click(function(){
	var oldPeople_uuid = $.trim($('#oldPeople_uuid').val());
	if(!oldPeople_uuid){
		alert("请先保存老人基本信息");
		return false;
	}
});

/*yyyy-mm-dd to mm/dd/yyyy*/
/*this.timeFormat = function(str){
	var a=str;
	var b=a.split('-');
	var a=b[1]+"/"+b[2]+"/"+b[0];
	return a;
};*/

/*this function is to listen combobox event*/
this.comboboxEvent = function(thisId,showId){
	if($(thisId).find("option:selected").val()=="其他"){
		$(showId).show("normal");
	}else{
		$(showId).hide("normal");
	}
};

/*this.onlyMoney = function(){
	var walletMoney = $('#oldPeople_walletMoney').val();
	if(!isMoney(walletMoney)){
		showMessage("金额格式不正确，请重新输入。。。",$('#oldPeople_walletMoney_Label'));
	}
};*/
/**check if exist the employee with the value of empNo*/
this.checkExistEmp = function(empNo,messageLabel){
	if(!(empNo==null||empNo=="")){
		$.ajax({
			type:"post",
			url:"oldPeople/isExistEmp",
			data:"empNo="+empNo,
			success:function(data){
				var msg = data;
				if(msg == "error"){
					showMessage("不存在该看护人，请重新输入。。。",$(messageLabel));
				}
			},
			error:function(){
				location.href = "pages/500.jsp";
			}
		});
	}
}
/**function to clear the form value*/
this.form_clear = function(oldPeopleForm){
	$(':input',oldPeopleForm)
    .not(':button, :submit, :reset, :radio')
    .val('')
    .removeAttr('checked')
	oldPeople_fihs_ue.execCommand('cleardoc');
	oldPeople_ephs_ue.execCommand('cleardoc');
	oldPeople_mtm_ue.execCommand('cleardoc');
};


/*
 * 动态表格的显示和分页(老人信息表格)
 */
this.oldPeople_createtable = function(pageIndex, spanInterval,oldPeopleName,oldPeopleNo) {
    $.ajax({
        url: "oldPeople/queryAllByPage",
        data: { "nowPage": pageIndex,"oldPeopleName": oldPeopleName,"oldPeopleNo": oldPeopleNo},
        type: "post",
        success: function (data) {
            var table = $("#oldPeople_table");
            $("#oldPeople_table tr").remove();
            table.append($("<tr><th style=\"display:none\"><span class=\"badge badge-success\">老人uuid</span></th>" +
            		"<th><span class=\"badge \">老人编号</span></th>" +
            		"<th><span class=\"badge \">老人名称</span></th>" +
            		"<th><span class=\"badge \">房间</span></th>" +
            		"<th><span class=\"badge \">床位</span></th>" +
            		"<th><span class=\"badge \">性别</span></th>" +
            		"<th><span class=\"badge \">等级</span></th>" +
            		"<th colspan=\"2\"><span class=\"badge \">操作</span></th></tr>"));
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
            						+ "</td><td><a href=\"oldPeople/getMoreInfo?opUuid="+listData[i].uuid+"\" target=\"_blank\">" +
            						listData[i].oldPeopleName
            						+ "</a></td><td>" +
            						listData[i].roomNo
            						+ "</td><td>" +
            						listData[i].bedNo
            						+ "</td><td>" +
            						listData[i].sex
            						+ "</td><td>" +
            						listData[i].grade
            						+"</td><td>"
                                    +"<a href=\"javascript:void(0)\"><i class=\"icofont-edit\"></i></a></td>"
                                    +"<td><a href=\"javascript:void(0)\"><i class=\"icon-trash\"></i></a>"
                                    +"</td></tr>")
            		);
            		$('#oldPeople_table tr:eq('+j+') td:eq(7) a').click(oldPeople_table_modify);
            		$('#oldPeople_table tr:eq('+j+') td:eq(8) a').click(oldPeople_table_delete);
            	}
            	$('#oldPeople_paging ul').remove();
            	$('#oldPeople_paging').append("<ul></ul>");
            	var $pager_ul = $('#oldPeople_paging ul');
            	paggin($pager_ul,nowPage,totalPage,totalNum,spanInterval,oldPeople_createtable,oldPeopleName,oldPeopleNo);
            }
        }
    });
};
/*function modify a data of oldPeople*/
this.oldPeople_table_modify = function(){
	var uuid = $(this).parent().prevAll('td').get(6).innerHTML;
	//clear the form value
	form_clear();
	$.ajax({
		type:"post",
		url:"oldPeople/findGrade",
		async:false,
		success:function(data){
			if(data=="error"){
				alert("因系统繁忙，操作失败，请稍后刷新重试");
			}else{
				$('#oldPeople_grade option').remove();
				var json = $.parseJSON(data);
				for(var i = 0; i<json.length; i++){
					$('#oldPeople_grade').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
				}
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
	});
	$.ajax({
		type:"post",
		url:"oldPeople/findParty",
		async:false,
		success:function(data){
			if(data=="error"){
				alert("因系统繁忙，操作失败，请稍后刷新重试");
			}else{
				$('#oldPeople_party option').remove();
				var json = $.parseJSON(data);
				for(var i = 0; i<json.length; i++){
					$('#oldPeople_party').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
				}
				if($('#oldPeople_party').val()=="其他"){
					$('#oldPeople_party_other').show("normal");
				}else{
					$('#oldPeople_party_other').hide("normal");
				}
			}
		},
		error:function(){
			location.href="pages/500.jsp";
		}
	});
	$.ajax({
		type:"post",
		url:"oldPeople/findMarriage",
		async:false,
		success:function(data){
			if(data=="error"){
				alert("因系统繁忙，操作失败，请稍后刷新重试");
			}else{
				$('#oldPeople_marriage option').remove();
				var json = $.parseJSON(data);
				for(var i = 0; i<json.length; i++){
					$('#oldPeople_marriage').append($("<option value=\""+json[i].metaValue+"\">"+json[i].metaValue+"</option>"));
				}
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
	});
	$.ajax({
		async:false,
		type:"post",
		url:"oldPeople/getOldPeople",
		data:"opUuid="+uuid,
		success:function(data){
			if(data == "error"){
				location.href = "pages/500.jsp";
			}else{
				$('#oldPeople_submit').hide("fast");
				$('#oldPeople_modify').show("fast");
				$("#oldPeople_table_toolbar").hide("normal");
				$('#oldPeople_form_toolbar').show("normal");
				var json = $.parseJSON("[" + data + "]");
				var oldPeople = json[0];
				$('#oldPeople_uuid').val(oldPeople.uuid);
				$('#oldPeople_opName').val(oldPeople.oldPeopleName);
				$('#oldPeople_idCard').val(oldPeople.idCard);
				$('#oldPeople_roomNo').val(oldPeople.roomNo);
				$('#oldPeople_bedNo').val(oldPeople.bedNo);
				$('#oldPeople_grade').val(oldPeople.grade);
				$('#oldPeople_empNo1').val(oldPeople.empNo1);
				$('#oldPeople_empNo2').val(oldPeople.empNo2);
				$('#oldPeople_birthday').val(oldPeople.birthday);
				if(oldPeople.sex=="男"){
					document.getElementById('oldPeople_sex_radios1').checked=true;
				}else{
					document.getElementById('oldPeople_sex_radios2').checked=true;
				}
				$('#oldPeople_nation').val(oldPeople.nation);
				var flag = false;
				$("#oldPeople_party option").each(function(){
				    if($(this).val()==oldPeople.party){
				    	$('#oldPeople_party').val(oldPeople.party);
				    	flag = true;
				    }
				});
				if(flag==false){
					$('#oldPeople_party').append($("<option selected=\"\" value=\""+oldPeople.party+"\">"+oldPeople.party+"</option>"));
				}
				$('#oldPeople_marriage').val(oldPeople.marriage);
				$('#oldPeople_phone').val(oldPeople.phone);
				$('#oldPeople_homeTel').val(oldPeople.homeTel);
				$('#oldPeople_email').val(oldPeople.email);
				$('#oldPeople_bloodType').val(oldPeople.bloodType);
				//$('#oldPeople_walletMoney').val(oldPeople.walletMoney);
				$('#oldPeople_allergicHistory').val(oldPeople.allergicHistory);
				$('#oldPeople_sugarSick').val(oldPeople.sugarSick);
				$('#oldPeople_bloodPressure').val(oldPeople.bloodPressure);
				$('#oldPeople_heartSick').val(oldPeople.heartSick);
				$('#oldPeople_brainBloodSick').val(oldPeople.brainBloodSick);
				$('#oldPeople_eyeSick').val(oldPeople.eyeSick);
				$('#oldPeople_getUpEarly').val(oldPeople.getUpEarly);
				$('#oldPeople_shitTime').val(oldPeople.shitTime);
				$('#oldPeople_canNotEat').val(oldPeople.canNotEat);
				$('#oldPeople_oldWorkCompany').val(oldPeople.oldWorkCompany);
				$('#oldPeople_oldWork').val(oldPeople.oldWork);
				$('#oldPeople_oldWorkName').val(oldPeople.oldWorkName);
				$('#oldPeople_oldWorkContent').val(oldPeople.oldWorkContent);
				$('#oldPeople_hobby').val(oldPeople.hobby);
				$('#oldPeople_liveAddress').val(oldPeople.liveAddress);
				$('#oldPeople_idCardAddress').val(oldPeople.idCardAddress);
				$('#oldPeople_emergencyPeople1').val(oldPeople.emergencyPeople1);
				$('#oldPeople_emergencyPeople2').val(oldPeople.emergencyPeople2);
				oldPeople_fihs_ue.setContent(oldPeople.fihs);
				oldPeople_ephs_ue.setContent(oldPeople.ephs);
				oldPeople_mtm_ue.setContent(oldPeople.mhs);
				showMessage("若不修改图片则无需上传图片...",$('#oldPeople_headImage_Label'));
			}
		},
		error:function(){
			location.href = "pages/500.jsp";
		}
	});
};

/*function delete a data of oldPeople*/
this.oldPeople_table_delete = function(){
	if(!confirm("确定删除该记录吗？")){
		return false;
  	}
	var uuid = $(this).parent().prevAll('td').get(7).innerHTML;
	var tr = $(this).parent().parent();
	$.ajax({
		type:"post",
		url:"oldPeople/deleteOldPeople",
		data:"opUuid="+uuid,
		success:function(data){
			var msg = data;
			if(msg == "error"){
				showMessage("老人信息删除失败，请刷新页面重试...",$('#oldPeople_showMessage'));
			}else{
				tr.remove();
				showMessage("老人信息删除成功...",$('#oldPeople_showMessage'));
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
   /* if (nowPage > 1){
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
    //最后一页
   /* if (nowPage >= totalPage) {
        $pager_ul.append("<li><span class='disabled'>最后一页</span></li>");
    }
    else {
        var last = $("<li><a href='javascript:void(0)'>最后一页</a></li>").click(function () {
            PageClick(totalPage,spanInterval,oldPeopleName,oldPeopleNo);
            return false;
        });
        $pager_ul.append(last);
    }*/
    $('#totalNum').text(totalNum);
};