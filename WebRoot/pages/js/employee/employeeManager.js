 
$(function() {
	$('[data-form=datepicker]').datepicker();
	var pageNow = 1;
	var addEmployee = function(){
		
		$.ajax({
			url : 'employee/addEmployee',
			data : $('#employee_form').serialize(),
			type : 'post',
			dataType : 'json',
			async:false,
			success : function(data) {
				
	          $('#emp_uuid').val(data.uuid);
	          $('#ecv_empUuid').val(data.uuid);
	          $('#employee_no').val(data.employeeNo);
	          //$('.nav .active').removeClass("active").next().addClass("active");
	         var tmp =  $('.nav .active').removeClass("active").next().addClass("active");
             $('.tab-content .active').removeClass('active').next().addClass('active');

			}

		});	
	}
	var modifyEmployee = function(){
		var root=$.trim($('#rootpath').text());
    	root = root.substring(0,root.length-1);//应该提取出来。。。。这里应该没人看到了吧
		
		$.ajax({
			url : 'employee/updateEmployee',
			data : $('#employee_form').serialize(),
			type : 'post',
			async:false,
//			dataType:'json',
			success : function() {
				var tmp =  $('.nav .active').removeClass("active").next().addClass("active");
			   
				 if(tmp.attr('id')=='final'){
		        	$('#employee_table_toolbar').show("normal");
			      	$('#employee_form_toolbar').hide("fast");
			      	$('#employee_form')[0].reset();
			      	$('#certificate_div').hide('fast');
					$('#certificate_tbody').find('tr').remove();
					$('#imageA').attr('src',root+'/img/home_png_icon-24.png');
					$('#imageB').attr('src',root+'/img/home_png_icon-24.png');
					$('#imageC').attr('src',root+'/img/home_png_icon-27.png');

		         }
				 $('.tab-content .active').removeClass('active').next().addClass('active');
		        
			}
		});
		
	}

	var check = function(){
		if(!$('#emp_name').val()){
			$('#emp_name').prev('label').show('normal');
			$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
			return false;
		}
		if(!/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/.test($('#employee_idCard').val())){
			$('#employee_idCard').prev('label').show('normal');
			$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
			return false;
		}
		if(!/^1\d{10}$/.test($('#employee_phone').val())){
			$('#employee_phone').prev('label').show('normal');
			$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
			return false;
		}
		if($('#employee_email').val()&&!(testEmail_Util($('#employee_email').val()))){
			$('#employee_email').prev('label').show('normal');
			$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
			return false;
		}
		
		return true;
		
	}
	/*
	 * 判断输入是否是数值
	 */
	var isNumber = function(num){
		var reg = /^([\d]+|([\d]+[.]?|[\d]+[.]?[\d]+))$/;
		if(!reg.test(num)){
			return false;
		}
		return true;
	}
	/*
	 * 保险基数约束
	 */
	var biaoXianConstraint = function(){
		if($('#employee_baoxianjishu').val()){
			
			var baoxianjishuUp=0;
			var baoxianjishuDown=0;
			var baoxianjishu = parseFloat($('#employee_baoxianjishu').val());
			
			if($('#employee_baoxianjishuUp').val()){
				baoxianjishuUp = parseFloat($('#employee_baoxianjishuUp').val());
			}
			if($('#employee_baoxianjishuDown').val()){
				baoxianjishuDown = parseFloat($('#employee_baoxianjishuDown').val());
			}
			if(baoxianjishu>baoxianjishuUp||baoxianjishu<baoxianjishuDown||baoxianjishu<0){
				$('#employee_baoxianjishu_Label').show('normal');
				return false;
			}

			return true;
		}
		return true;
		
	}
	/*
	 * 保险基数上下限约束
	 */

	
	/*
	 * 住房基数约束
	 */
	
	var zhuFangConstraint = function(){
		
		if($('#employee_zhufangjishu').val()){
			var zhufangjishuUp=0;
			var zhufangjishuDown=0;
			var zhufangjishu = parseFloat($('#employee_zhufangjishu').val());
			if($('#employee_zhufangjishuUp').val()){
				zhufangjishuUp = parseFloat($('#employee_zhufangjishuUp').val());
			}
			if($('#employee_zhufangjishuDown').val()){
				zhufangjishuDown = parseFloat($('#employee_zhufangjishuDown').val());
			}
			if(zhufangjishu<zhufangjishuDown||zhufangjishu>zhufangjishuUp||zhufangjishu<0){
				$('#employee_zhufangjishu_Label').show('normal');
				return false;
			}
			
			return true;
		}
		return true;
		
	}
	/*
	 * 上下限的约束
	 */
	var upDown = function(){
		var baoxianjishuup = $.trim($('#employee_baoxianjishuUp').val())?$.trim($('#employee_baoxianjishuUp').val()):0;
		var baoxianjishudown = $.trim($('#employee_baoxianjishuDown').val())?$.trim($('#employee_baoxianjishuDown').val()):0;
		var zhufangjishuup = $.trim($('#employee_zhufangjishuUp').val())?$.trim($('#employee_zhufangjishuUp').val()):0;
		var zhufangjishudown = $.trim($('#employee_zhufangjishuDown').val())?$.trim($('#employee_zhufangjishuDown').val()):0;

		if(baoxianjishuup<baoxianjishudown){
			$('#baoxianupdown').text("保险基数上限低于保险基数下限").show('normal');
			return false;
		}
		if(zhufangjishuup<zhufangjishudown){
			
			$("#zhufangupdown").text('住房基数上限低于住房基数下限').show('normal');
			return false;
		}
		
		return true;

		
	}
	/*
	 * 删除资格信息
	 */
	var delCertificate = function($tr){
		
		var uuid = $.trim($tr.children('td').eq(0).text());
	
		$.ajax({
			url:'employee/delCertificate',
			data:{
				'ecv.uuid':uuid
			},
		    dataType:'text',
		    type:'post',
		    success:function(data){
		    	if($.trim(data)=="success"){
		    		$tr.remove();
		    	}
		    	
		    }
		});
		
	}
	/*
	 * 修改时填充数据
	 */
	var fillForm = function(data){
		var root=$.trim($('#rootpath').text());
    	root = root.substring(0,root.length-1);
		$('#emp_uuid').val(data.uuid);
		$('#emp_name').val(data.employeeName);
		$('#employee_idCard').val(data.idCard);
		$('#employee_birthday').val(data.birthday);
		$('input[type=radio][value='+data.sex+']').attr('checked','checked');
		$('#employee_nation').val(data.nation);
		$('#employee_party').val(data.party);
		$('#employee_marriage').val(data.marriage);
		$('#employee_phone').val(data.phone);
		$('#employee_homeTel').val(data.homeTel);
		$('#employee_email').val(data.email);
		$('#employee_address').val(data.address);
		
		$('#employee_no').val(data.employeeNo);
		$('#employee_department').val(data.department);
		$('#employee_workName').val(data.workName);
		$('#employee_grade').val(data.grade);
		$('#employee_beginWorkTime').val(data.beginWorkTime);
		$('#employee_workStatus').val(data.workStatus);
		
		
		$('#employee_basicSalary').val(data.basicSalary);
		$('#employee_positionSalary').val(data.positionSalary);
		$('#employee_baoxianjishuUp').val(data.baoxianjishuUp);
		$('#employee_baoxianjishuDown').val(data.baoxianjishuDown);
		$('#employee_baoxianjishu').val(data.baoxianjishu);
		$('#employee_zhufangjishuUp').val(data.zhufangjishuUp);
		$('#employee_zhufangjishuDown').val(data.zhufangjishuDown);
		$('#employee_zhufangjishu').val(data.zhufangjishu);
		$('#employee_baoxianbuchangjin').val(data.baoxianbuchangjin);
		
		if(data.idCardImageA!=""){
			$('#imageA').attr('src',root+data.idCardImageA);
		}
		if(data.idCardImageB!=""){
			$('#imageB').attr('src',root+data.idCardImageB);
		}
		if(data.headImage!=""){
			$('#imageC').attr('src',root+data.headImage);
		}
		familyHtmlUe.setContent(data.familyHtmlSource);
		emergencyPeopleHtmlUe.setContent(data.emergencyPeopleHtmlSource);
		mianWorkHtmlUe.setContent(data.mianWorkHtmlSource);
		workExperienceHtmlUe.setContent(data.workExperienceHtmlSource);
		educationExperienceHtmlUe.setContent(data.educationExperienceHtmlSource);
		
		
		//emergencyPeopleHtmlUe.setContent(data.emergencyPeopleHtmlSource);
		
//		$('#emp_uuid').val(data.uuid);
//		$('#emp_uuid').val(data.uuid);
	}
	
	/*
	 * 查询下拉列表
	 */
	var findSelect = function(){
		/*获得政治面貌*/
		$.ajax({
			url:'employee/findSelectOption',
			data:{
				'systemMetaVo.parentMetaName':'9'
			},
			async:false,
			dataType:'json',
			success:function(data){
				for(var i=0;i<data.length;i++){
					$('#employee_party').append("<option value='"+data[i].metaNickName+"'>"+data[i].metaNickName+"</option>");
				}
				
			}
			
		});
		/*获得部门*/
		$.ajax({
			url:'employee/findSelectOption',
			data:{
				'systemMetaVo.parentMetaName':'42'
			},
			async:false,
			dataType:'json',
			success:function(data){
				for(var i=0;i<data.length;i++){
					$('#employee_department').append("<option value='"+data[i].metaNickName+"'>"+data[i].metaNickName+"</option>");
				}
				
			}
			
		});
        /*获得工作*/
		$.ajax({
			url:'employee/findSelectOption',
			data:{
				'systemMetaVo.parentMetaName':'43'
			},
			async:false,
			dataType:'json',
			success:function(data){
				for(var i=0;i<data.length;i++){
					$('#employee_workName').append("<option value='"+data[i].metaNickName+"'>"+data[i].metaNickName+"</option>");
				}
				
			}
			
		});
	}
	$('#certificate_add').click(function(){
		
		var certificateName = $('#certificateName').val();
		var certificateNo = $('#certificateNo').val();
		var certificateGrade = $('#certificateGrade').val();
		var publishPart = $('#publishPart').val();
		var getTime = $('#getTime').val();
		var endTime = $('#endTime').val();
		var empUuid = $('#emp_uuid').val();
	
		$.ajax({
			url:'employee/addCertificate',
			data:{
				'ecv.empUuid':empUuid,
				'ecv.certificateName':certificateName,
				'ecv.certificateGrade':certificateGrade,
				'ecv.certificateNo':certificateNo,
				'ecv.publishPart':publishPart,
				'ecv.getTime':getTime,
				'ecv.endTime':endTime
			},
			type:'post',
			dataType:'json',
			async:false,
			success:function(data){
				$('#certificate_div').show('fast');
				
				$('#certificate_tbody').append("<tr><td style='display:none;'>"+data.uuid+"</td><td class='myCenter'>"+data.certificateName+"</td>" +
						"<td class='myCenter'>"+data.certificateNo+"</td><td class='myCenter'>"+data.certificateGrade+"</td><td class='myCenter'>"+data.publishPart+"</td><td class='myCenter'>"+data.getTime+"</td><td class='myCenter'>"+data.endTime+"</td><td class='myCenter'>未过期</td>" +
					"<td class='myCenter'><a class='delCertificate' style='cursor:pointer'><i class='icon-trash'></i></a></td>"+			
				"</tr>");
				
				$('.delCertificate').click(function(){
					delCertificate($(this).parent().parent());
				});
				
				$('#ecv_empUuid').val("");
				$('#certificateName').val("");
				$('#certificateNo').val("");
				$('#certificateGrade').val("");
				$('#publishPart').val("");
				$('#getTime').val("");
				$('#endTime').val("");
				
			}
			
		});
		
	});
	
	
	/*
	 * 图片上传
	 * 
	 */
	  $(".upload_but").click(function() {
      	var $this = $(this);
      	var empUuid = $('#emp_uuid').val();
        var id = $this.attr('id');
        var imageSelect;//用来标明是正面、反面、头像
        var showImg;//用来上传成功后显示图片
        var fileId;
        if(id=="f1"){
        	fileId="file1";
        	//alert($('#file1').val()=="");
        	if($('#file1').val()==""){
        		alert("请先选择图片");
        		return false;
        	}
        	imageSelect="idCardImageA";
        	showImg=$('#imageA');
        }else if(id=="f2"){
        	fileId="file2";
        	if($('#file2').val()==""){
        		alert("请先选择图片");
        		return false;
        	}
        	imageSelect="idCardImageB";
        	showImg=$('#imageB');
        }else if(id=="f3"){
        	fileId="file3";
        	if($('#file3').val()==""){
        		alert("请先选择图片");
        		return false;
        	}
        	imageSelect="headImage";
        	showImg=$('#imageC');
        }
        
              ajaxFileUpload(empUuid,fileId,imageSelect,showImg);
      });

	/*
	 * 保险基数、住房基数、保险补偿金之间约束
	 */
	/*
	 * 分组
	 */
	$('.order').click(function(){
		$this=$(this);
	    var tmp = $this.attr('class').split(" ")[1];
	    var no = $.trim($("#empNo").val());
		var name = $.trim($("#empName").val());
		var dept = $.trim($("#empDept").val());
		var work = $.trim($("#empWork").val());
		var grade = $.trim($("#empGrade").val());
		var type = $this.attr('class').split(" ")[0].split("_")[1];
		
		if(type=="asc"){
			$this.attr("class","sorting_desc "+tmp+" order");
			
		}else if(type="desc"){
			$this.attr("class","sorting_asc "+tmp+" order");
		}
		findEmployeeByPage(no, name, dept, work, grade);
		
		
		
	  
	});
	/*
	 * 要先保存基本信息，才能点击下个tab页
	 */
	$('.afterBase').click(function(){
		var emp_uuid = $.trim($('#emp_uuid').val());
		if(!emp_uuid){
			alert("请先保存员工基本信息");
			return false;
		}
	});
	
   $('.jishu').blur(function(){
	   if($(this).val()){
		   $('#employee_baoxianbuchangjin').attr('disabled','disabled');
	   }else{
		   $('#employee_baoxianbuchangjin').removeAttr('disabled');
	   }
   });
   
   $('.buchangjin').blur(function(){
	   if($(this).val()){
		   $('.jishu').attr('disabled','disabled');
	   }else{
		   $('.jishu').removeAttr('disabled');
	   }
   });
	$('.shuzhi').blur(function(){
		var $this = $(this);
		var num = $.trim($this.val());
		if(!isNumber(num)){
		   $this.parent().find('.shuzhilabel').text("输入的不是数值").show('normal');
		}else{
		   $this.parent().find('.shuzhilabel').hide('normal');
		}
		
	});

	$('.require').blur(function(){
		$this = $(this);
		if(!$this.val()){
			$this.prev('label').show('normal');
		}else{
			
			 if($this.attr('id')=="employee_idCard"){
			    	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			        if(!reg.test($.trim($this.val()))){
			        	$this.prev('label').show('normal');
			        	$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
			        	return false;
			        }
			        
			        //$('#flag').val($('#flag').val()+1);
			    }
			    else if($this.attr('id')=="employee_phone"){
			    	var reg= /^1\d{10}$/;  
			    	if(!reg.test($.trim($this.val()))){
			    		$this.prev('label').show('normal');
			    		$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
			    		return false;
			    	}
			     //$('#flag').val($('#flag').val()+1);
			       
			    }
			    else if($this.attr('id')=="employee_email"){
			    	//var reg=/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
			    	if($('#employee_email').val()&&!testEmail_Util($.trim('#employee_email').val())){
			    		$this.prev('label').show('normal');
						$("html,body").animate({scrollTop: $("#employee_form").offset().top}, 1000);
						return false;
					}
			    }
			    $this.prev('label').hide('normal');
			
		}
	   

	});
	
	
	$('#employee_search').click(function() {
		pageNow = 1;
		var no = $.trim($("#empNo").val());
		var name = $.trim($("#empName").val());
		var dept = $.trim($("#empDept").val());
		var work = $.trim($("#empWork").val());
		var grade = $.trim($("#empGrade").val());
		findEmployeeByPage(no, name, dept, work, grade);
	});
	$('#employee_add').click(function(){
		findSelect();
		
		$.ajax({
			type:"post",
			url:"employee/findFamilyParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到家庭信息参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						familyHtmlUe.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		
		$.ajax({
			type:"post",
			url:"employee/findEmergencyPeopleParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到紧急联系人参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						emergencyPeopleHtmlUe.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		
		$.ajax({
			type:"post",
			url:"employee/findWorkExperienceParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到工作经历参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						workExperienceHtmlUe.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		
		$.ajax({
			type:"post",
			url:"employee/findEducationExperienceParam",
			async:"false",
			success:function(data){
				if(data=="error"){
					alert("未找到教育经历参数");
				}else{
					var json = $.parseJSON(data);
					for(var i = 0; i<json.length; i++){
						educationExperienceHtmlUe.setContent(json[i].htmlsource);
					}
				}
			},
			error:function(){
				location.href="pages/500.jsp";
			}
		});
		
		$('#employee_table_toolbar').hide("normal");
		$('#employee_modify').hide('fast');
	  
		$('#employee_submit').show('fast');
		$('#employee_form_toolbar').show("fast");
		
	});
	/*
	 * 添加
	 */
     
	$('#employee_submit').click(
			
		  function() {
			    if(!check()){
				    return false;
			    }
			    if(!biaoXianConstraint()){
			    	
					return false;
				}
				if(!zhuFangConstraint()){
					return false;
				}
				if(!upDown()){
					return false;
				}
				var familyhtml = familyHtmlUe.getContent();
				if($.trim(familyhtml)){
					$('#familyHtmlSource').val($.trim(familyhtml));
				}
				var emergencyPeopleHtml = emergencyPeopleHtmlUe.getContent();
				if($.trim(emergencyPeopleHtml)){
					$('#emergencyPeopleHtmlSource').val($.trim(emergencyPeopleHtml));
				}
				var mianWorkHtml = mianWorkHtmlUe.getContent();
				if($.trim(mianWorkHtml)){
					$('#mianWorkHtmlSource').val($.trim(mianWorkHtml));
				}
				var workExperienceHtml = workExperienceHtmlUe.getContent();
				if($.trim(workExperienceHtml)){
					$('#workExperienceHtmlSource').val($.trim(workExperienceHtml));
				}
				var educationExperienceHtml = educationExperienceHtmlUe.getContent();
				if($.trim(educationExperienceHtml)){
					$('#educationExperienceHtmlSource').val($.trim(educationExperienceHtml));
				}
				
				$('#employee_zhufangjishu_Label').hide();
				$('#employee_baoxianjishu_Label').hide();
				var emp_uuid = $.trim($('#emp_uuid').val());
				if(emp_uuid){
					
					
					modifyEmployee();
					
				}else{
					
					addEmployee();
				
					
				}

			});
	$("#employee_cancel").click(function(){
		var root=$.trim($('#rootpath').text());
		root = root.substring(0,root.length-1);
		$('#employee_form')[0].reset(); 
		$('#employee_table_toolbar').show("normal");
		$('#employee_form_toolbar').hide("fast");
		$('#certificate_div').hide('fast');
		$('#certificate_tbody').find('tr').remove();
		$('.nav .active').removeClass("active");
		$('#employee_tab_ul').find('li').eq(0).addClass('active');
		$('.box-body .tab-content .active').removeClass('active');
		$('#boxtabpill-1').addClass('active').addClass('in');
		$('#imageA').attr('src',root+'/img/home_png_icon-24.png');
		$('#imageB').attr('src',root+'/img/home_png_icon-24.png');
	    $('#imageC').attr('src',root+'/img/home_png_icon-27.png');
		
	   
	});
	
	$("#employee_modify").click(function(){
		if(!check()){
		    return false;
	    }
		var familyhtml = familyHtmlUe.getContent();
		if($.trim(familyhtml)){
			$('#familyHtmlSource').val($.trim(familyhtml));
		}
		var emergencyPeopleHtml = emergencyPeopleHtmlUe.getContent();
		if($.trim(emergencyPeopleHtml)){
			$('#emergencyPeopleHtmlSource').val($.trim(emergencyPeopleHtml));
		}
		var mianWorkHtml = mianWorkHtmlUe.getContent();
		if($.trim(mianWorkHtml)){
			$('#mianWorkHtmlSource').val($.trim(mianWorkHtml));
		}
		var workExperienceHtml = workExperienceHtmlUe.getContent();
		if($.trim(workExperienceHtml)){
			$('#workExperienceHtmlSource').val($.trim(workExperienceHtml));
		}
		var educationExperienceHtml = educationExperienceHtmlUe.getContent();
		if($.trim(educationExperienceHtml)){
			$('#educationExperienceHtmlSource').val($.trim(educationExperienceHtml));
		}
		modifyEmployee();
	});
	
	
/*
 * 保存资格信息
 */
	
    
	var findEmployeeByPage = function(no, name, department, workname, grade) {
		var dept_sort_type = $('#dept_sort').attr("class").split(" ")[0].split("_")[1];
		var post_sort_type = $('#post_sort').attr("class").split(" ")[0].split("_")[1];
		var grade_sort_type = $('#grade_sort').attr("class").split(" ")[0].split("_")[1];
		//alert(dept b_sort_type+" "+post_sort_type+" "+grade_sort_type);
				$.ajax({
					url : 'employee/findEmployeeByPage',
					data : {
						'pageVo.nowPage' : pageNow,
						'employeeVo.employeeName' : name,
						'employeeVo.employeeNo' : no,
						'employeeVo.department' : department,
						'employeeVo.grade' : grade,
						'employeeVo.workName':workname,
						'deptOrderType':dept_sort_type,
						'postOrderType':post_sort_type,
						'gradeOrderType':grade_sort_type
					},
					dataType : 'json',
					type : 'post',
					success : function(json) {
						var data = json.listData;
						var pageLine = json.pageLine;
						var totalPage = json.totalPage;
						var totalNum = json.totalNum;
						pageNow = json.nowPage;
						$(".gradeA").remove();
						$("#emp_paging").find('ul').find('li').remove();
						for (var i = 0; i < data.length; i++) {
							var ii = i;
 						$("#empList")
									.append(
									 		"<tr id='"
													+ ii
													+ "' class='gradeA'><td style='display:none;'>"
													+ data[i].uuid
													+ "</td>"
													+ "<td>"
													+ data[i].employeeNo
													+ "</td><td><a href=\"employee/findEmployeeOnly?employee.uuid="+data[i].uuid+"\" target=\"_blank\">" 															
													+ data[i].employeeName
													+ "</a></td>"
													+ "<td>"
													+ data[i].department
													+ "</td>"
													+ "<td>"
													+ data[i].workName
													+ "</td>"
													+ "<td>"
													+ data[i].grade
													+ "</td>"
													+ "<td>"
													+"<a class="+i+" style='cursor:pointer'><i class='icon-trash'></i></a>"
													+"</td>"
													+ "<td>"
													+"<a class="+i+" style='cursor:pointer'><i class='icofont-edit'></i></a>"
													+"</td>"
													+ "</tr>");
							
							$("#empList").find('tr').eq(i).find('td').eq(6).find('a').eq(0).click(							
									function() {										
										var $this  = $(this);
										var lineNum = $.trim($this.attr('class'));
										var tr = $(this).parent().parent();
										var uuid = $.trim($("#empList tr:eq("+lineNum+") td:eq(0)").text());
										
									
										if (!confirm('确定删除吗?')) {
										
											return false;
										}
										$.ajax({
														url : 'employee/delEmployee',
														data : {
															'employee.uuid' : uuid
														},
														type : 'post',
														success : function() {
															alert("删除成功");
														  //$('#empList tr:eq('+lineNum+')').remove();
															tr.remove();

														}
													});

									
									});
							/*
							 * 修改
							 */
							$("#empList").find('tr').eq(i).find('td').eq(7).find('a').eq(0).click(function(){	
								findSelect();
								var $this = $(this);
								var lineNum = $.trim($this.attr('class'));
								
								var uuid = $.trim($("#empList tr:eq("+lineNum+") td:eq(0)").text());
								
								
								$.ajax({
									url:'employee/findEmployee',
									data:{
										'employee.uuid':uuid
									},
									dataType:'json',
									type:'post',
									success:function(json){
										//alert(json.employeeName);
										$('#employee_table_toolbar').hide("normal");
										$('#employee_submit').hide('fast');
										$('#employee_modify').show('fast');
										$('#employee_form_toolbar').show("fast");
										fillForm(json);
										var data = json.ecvList;
										if(data.length!=0){
											for(var i=0;i<data.length;i++){
											$('#certificate_tbody').append("<tr><td style='display:none;'>"+data[i].uuid+"</td><td class='myCenter'>"+data[i].certificateName+"</td>" +
													"<td class='myCenter'>"+data[i].certificateNo+"</td><td class='myCenter'>"+data[i].certificateGrade+"</td><td class='myCenter'>"+data[i].publishPart+"</td><td class='myCenter'>"+data[i].getTime+"</td><td class='myCenter'>"+data[i].endTime+"</td><td class='myCenter'>"+data[i].certificateStatus+"</td>" +
													"<td class='myCenter'><a class='delCertificate' style='cursor:pointer'><i class='icon-trash'></i></a></td>"+		
											        "</tr>");
										}
										$('.delCertificate').click(function(){
											delCertificate($(this).parent().parent());
										});
										$('#certificate_div').show('fast');
										}
										
										
									}
								});
								
							});
							/*
							 * 员工详细信息
							 */
							/*$("#empList").find('tr').eq(i).click(function(){							
								var $this = $(this);
								var lineNum = $.trim($this.attr('id'));
								var uuid = $.trim($this.find('td').eq(0).text());
								window.location.href='employee/findEmployeeOnly?employee.uuid='+uuid;
								
							});*/
							
							
							
									
			
						}
						// 写出分页条
						if (pageNow != 1) {
							$('#emp_paging')
									.find('ul')
									.append(
											"<li><a id='prev' class='prev' style='cursor:pointer;'>上一页</a></li>");
						} else {
							$('#emp_paging')
									.find('ul')
									.append(
											"<li id='prev' class='prev disabled'><a  class='pageA' style='cursor:pointer;'>上一页</a></li>");
						}

						for (var i = 0; i < pageLine.length; i++) {
							if (pageNow == pageLine[i]) {
								$('#emp_paging').find('ul').append(
										"<li class='active'><a class='pageA' style='cursor:pointer;'>"
												+ pageLine[i] + "</a></li>");
							} else {
								$('#emp_paging').find('ul').append(
										"<li><a class='pageA' style='cursor:pointer;'>"
												+ pageLine[i] + "</a></li>");
							}

						}
						if (pageNow != totalPage) {
							$('#emp_paging')
									.find('ul')
									.append(
											"<li id='next' class='prev'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
						} else {
							$('#emp_paging')
									.find('ul')
									.append(
											"<li  id='next' class='prev disabled'><a class='pageA' style='cursor:pointer;'>下一页</a></li>");
						}
						$('#totalNum').text(totalNum);

						$('#emp_paging').find('ul').find('li').not('.prev')
								.click(
										function() {

											var page = $.trim($(this).text());
											var no = $.trim($("#empNo").val());
											var name = $.trim($("#empName").val());
													
											var dept = $.trim($("#empDept").val());
													
											var work = $.trim($("#empWork").val());
													
											var grade = $.trim($("#empGrade").val());
													
											pageNow = page;
											findEmployeeByPage(no, name, dept,
													work, grade);
										});
						$('#prev').click(function() {

							// var page = $(this).text().trim();
							pageNow = json.nowPage;
							
							var no = $.trim($("#empNo").val());
							var name = $.trim($("#empName").val());
							var dept = $.trim($("#empDept").val());
							var work = $.trim($("#empWork").val());
							var grade = $.trim($("#empGrade").val());
							
							if(pageNow>1){
								pageNow--;
								findEmployeeByPage(no, name, dept, work, grade);
							}
							
						});
						$('#next').click(function() {

							//var page = $(this).text().trim();
							pageNow = json.nowPage;
							
							var no = $.trim($("#empNo").val());
							var name = $.trim($("#empName").val());
							var dept = $.trim($("#empDept").val());
							var work = $.trim($("#empWork").val());
							var grade = $.trim($("#empGrade").val());
							if(pageNow<totalPage){
							pageNow++;
							findEmployeeByPage(no, name, dept, work, grade);
							}
							
						});

					}
				});

	}
	

    
	findEmployeeByPage();

	

});
function ajaxFileUpload(uuid,fileId,imageSelect,showImg){
	 $.ajaxFileUpload({
        url: "employee/uploadImg?employeeVo.uuid="+uuid+"&imageSelect="+imageSelect, 
        type: 'post',
        secureuri: false, //一般设置为false
        fileElementId: fileId, // 上传文件的id、name属性名
        dataType: 'json', //返回值类型，一般设置为json、application/json
        success: function(data, status){ 
        	var root=$.trim($('#rootpath').text());
        	root = root.substring(0,root.length-1);
        	data = eval('('+data+')');        	//$(showImg).children('div').eq(1).remove();
        	
        	showImg.attr('src',root+data.imagePath);
           
        },
        error: function(data, status, e){ 
            alert(e);
        }
    });
}

