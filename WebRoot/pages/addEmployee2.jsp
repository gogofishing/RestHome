<%@ page language="java" import="java.util.*,com.resthome.entity.Admin" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
Admin admin=(Admin)session.getAttribute("admin");
if(admin==null){
	response.sendRedirect("/RestHome/pages/login.jsp");
}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
     <title>养老院管理系统</title> 
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<jsp:include page="css.jsp"/>
  </head>
  
  <body>
 	<jsp:include page="Header.jsp"/>
 	
 	
 	 <!-- section content -->
        <section class="section">
            <div class="row-fluid">
              <!-- 左边的菜单 -->
              <jsp:include page="LeftMenu.jsp"/>
               <!-- /左边的菜单 -->
                
                <!-- span content -->
                <div class="span9">
                    <!-- content -->
                    <div class="content">
                        <!-- content-header -->
                        <div class="content-header">
                           
                            <h2><i class="icofont-home"></i>新增员工 <small>养老院管理系统</small></h2>
                        </div><!-- /content-header -->
                        
                        <!-- content-breadcrumb -->
                        <div class="content-breadcrumb">
                          
                            
                            <!--breadcrumb-->
                            <ul class="breadcrumb">
                                <li><a href="index.html"><i class="icofont-home"></i> 主页</a></li>
                                <!-- <li class="active">Data elements</li> -->
                            </ul><!--/breadcrumb-->
                        </div><!-- /content-breadcrumb -->
                        
                        <!--中间主体部分 -->
                        <div class="content-body">
                            <!-- form -->
                            <div class="row-fluid">
                                <div class="span12">
                                    <!--element-->
                                    <div id="element" class="row-fluid">
                                        <!--span-->
                                        <div class="span12">
                                            <!--box-->
                                            <div class="box corner-all">
                                                <!--box header-->
                                               
                                                <div class="box-header grd-white color-silver-dark corner-top">
                                                    <div class="header-control">
                                                        <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                                        <a data-box="close">×</a>
                                                    </div>
                                                    <span>个人信息</span>
                                                </div><!--/box header-->
                                                <!--box body-->
                                                <div class="box-body" id="add_emp_div">
                                                    <!--element-->
                                                    <form class="form-horizontal"  id="form_add">
                                                        <div class="control-group">
                                                            <label class="control-label" for="inputAuto">姓名</label>
                                                            <div class="controls">
                                                                <input type="text" id="inputEmployeeName" class="grd-white" name="employeeVo.employeeName" style="height:30px;">
                                                            </div>
                                                        </div>
                                                        <div class="control-group">
                                                            <label class="control-label" for="inputAuto">身份证号</label>
                                                            <div class="controls">
                                                                <input type="text" id="inputidCard" class="grd-white" name="employeeVo.idCard" style="height:30px;">
                                                            </div>
                                                        </div>
                                                      
                                                        <div class="control-group">
                                                            <label class="control-label" for="inputSelect">性别</label>
                                                            &nbsp; &nbsp; &nbsp; &nbsp;
                                                           <select name="employeeVo.sex" id="inputSex">
                                                           <option value="男">男</option>
                                                           <option value="女">女</option>
                                                           </select>
                                                        </div>
                                                        <div class="control-group">
                                                            <label class="control-label" for="inputAuto">民族</label>
                                                            <div class="controls">
                                                                <input type="text" id="inputAutoNation" class="grd-white" name="employeeVo.nation" style="height:30px;">
                                                            </div>
                                                        </div>
                                                         <div class="control-group">
                                                            <label class="control-label" for="inputAuto">政治面貌</label>
                                                            <div class="controls">
                                                                <input type="text" id="inputAutoParty" class="grd-white" name="employeeVo.party" style="height:30px;">
                                                            </div>
                                                        </div>
                                                         <div class="control-group">
                                                            <label class="control-label" for="inputAuto">婚姻状况</label>
                                                            <div class="controls">
                                                                <input type="text" id="inputAutoMarriage" class="grd-white" name="employeeVo.marriage" style="height:30px;">
                                                            </div>
                                                        </div>
                                                        
                                                        
                                                     
                                                       <div class="form-actions">
                                                            <button  type="button" id="add_emp_but" class="btn btn-primary">Save</button>
                                                            <button type="button" class="btn">Cancel</button>
                                                        </div>
                                                    </form>
                                                    
                                                    <!--/element-->
                                                </div><!--/box body-->
                                                
                                            </div><!--/box-->
                                        </div><!--/span--> 
                                       
                                    </div><!--/element-->
                                    
                                    <!-- =========================================
                                                        VALIDATION
                                    =========================================== -->
                                    <!--validation-->
                                    <div id="validation" class="row-fluid">
                                        <!--span-->
                                        <div class="span12">
                                            <!--box-->
                                            <div class="box corner-all">
                                                <!--box header-->
                                                <div class="box-header grd-white color-silver-dark corner-top">
                                                    <div class="header-control">
                                                        <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                                        <a data-box="close">×</a>
                                                    </div>
                                                    <span>证件照与头像</span>
                                                </div><!--/box header-->
                                                <!--box body-->
                                                <div class="box-body">
                                                    <!--validation-->
                                                   
                                                        <fieldset>
                                                        
												<div class="control-group" id="image_divA">
													<label class="control-label" for="inputAuto">身份证照正面</label>
													
													
													<div class="controls" id="image_div1">
														<input type="file" id="file1"
															class="grd-white" name="image"
															style="height:30px;">
														
														<button class="upload_but" id="idcardImageA_but">上传</button>
													</div>
                                                 
												</div>
												
												<div class="control-group" id="image_divB">
                                                                <label class="control-label" for="minlength">身份证照反面</label>
                                                                <div class="controls" id="image_div2">
                                                                    <input type="file" class="grd-white"  name="image" id="file2">
                                                               <button class="upload_but" id="idcardImageB_but">上传</button>
                                                                </div>
                                                                 
                                                            </div>
                                                            <div class="control-group" id="image_divC">
                                                                <label class="control-label" for="maxlength">头像</label>
                                                                <div class="controls" id="image_div3">
                                                                    <input type="file" class="grd-white"  name="image" id="file3">
                                                                    <button class="upload_but" id="headImg_but">上传</button>
                                                                </div>
                                                                 
                                                            </div>
                                                          
                                                        </fieldset>
                                                   
                                                </div><!--/box body-->
                                            </div><!--/box-->
                                        </div><!--/span--> 
                                    </div><!--/validation-->
                                    
                                    <!-- =========================================
                                                        WIZARD
                                    =========================================== -->
                                     <div id="lianxifangshi" class="row-fluid">
                                        <!--span-->
                                        <div class="span12">
                                            <!--box-->
                                            <div class="box corner-all">
                                                <!--box header-->
                                                <div class="box-header grd-white color-silver-dark corner-top">
                                                    <div class="header-control">
                                                        <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                                        <a data-box="close">×</a>
                                                    </div>
                                                    <span>工作信息</span>
                                                </div><!--/box header-->
                                                <!--box body-->
                                                <div class="box-body" id="emp_work_div">
                                                    <!--validation-->
                                                    <form class="form-horizontal" id="form_work_add" novalidate="novalidate">
                                                    <input type="hidden" name="employeeVo.uuid" id="emp_uuid_hidden1">
                                                        <fieldset>
                                                           
                                                            <div class="control-group">
                                                                <label class="control-label" for="required">部门</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.department" id="departmentInput">
                                                                </div>
                                                            </div>
                                                            <div class="control-group">
                                                                <label class="control-label" for="minlength">职位</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.workName" id="workNameInput">
                                                                </div>
                                                            </div>
                                                            <div class="control-group">
                                                                <label class="control-label" for="maxlength">级别</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.grade" id="gradeInput">
                                                                </div>
                                                            </div>
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">保险基数上限</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.baoxianjishuUp" id="baoxianjishuUpInput">
                                                                </div>
                                                            </div>
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">保险基数上限</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.baoxianjishuDown" id="baoxianjishuDownInput">
                                                                </div>
                                                            </div>
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">保险基数</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.baoxianjishu" id="baoxianjishuInput">
                                                                </div>
                                                            </div>
                                                             
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">住房基数上限</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.zhufangjishuUp" id="zhufangjishuUpInput">
                                                                </div>
                                                            </div>
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">住房基数下限</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.zhufangjishuDown" id="zhufangjishuDownInput">
                                                                </div>
                                                            </div>
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">住房基数</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.zhufangjishu" id="zhufangjishuInput">
                                                                </div>
                                                            </div>
                                                             <div class="control-group">
                                                                <label class="control-label" for="maxlength">保险补偿金</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.baoxianbuchangjin" id="baoxianbuchangjinInput">
                                                                </div>
                                                            </div>
                                                            <div class="control-group">
                                                                <label class="control-label" for="maxlength">基本工资</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.basicSalary" id="basicSalaryInput">
                                                                </div>
                                                            </div>
                                                            <div class="control-group">
                                                                <label class="control-label" for="maxlength">岗位工资</label>
                                                                <div class="controls">
                                                                    <input type="text" class="grd-white"  name="employeeVo.positionSalary" id="positionSalaryInput">
                                                                </div>
                                                            </div>
                                                           <div class="form-actions">
                                                                <button type="button" class="btn btn-primary" id="emp_work_add">Save</button>
                                                                <button type="button" class="btn">Cancel</button>
                                                            </div>
                                                        </fieldset>
                                                    </form><!--/validation-->
                                                </div><!--/box body-->
                                                
                                                
                                                 
                                            </div><!--/box-->
                                        </div><!--/span--> 
                                    </div><!--/validation-->
                                    
                                     <div id="familyHtmlSource" class="row-fluid">
                                        <!--span-->
                                        <div class="span12">
                                            <!--box-->
                                            <div class="box corner-all" >
                                                <!--box header-->
                                                <div class="box-header grd-white color-silver-dark corner-top">
                                                    <div class="header-control">
                                                        <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                                        <a data-box="close">×</a>
                                                    </div>
                                                    <span>家庭信息</span>
                                                </div><!--/box header-->
                                                <!--box body-->
                                                <div class="box-body" id="familyDiv">
                                                   <form id="family_info_div">
                                                   <script id="familyHtml" name="content" type="text/plain">家庭信息</script>
                                                   <button type="button" class="btn btn-primary" id="family_info_save">Save</button>
                                                   <button type="button" class="btn">Cancel</button>
                                                    </form>
                                                </div><!--/box body-->
                                                
                                                
                                                 
                                            </div><!--/box-->
                                        </div><!--/span--> 
                                    </div><!--/validation-->
                                   
                                   
                                    <div id="emergencyPeopleHtmlSource" class="row-fluid">
                                        <!--span-->
                                        <div class="span12">
                                            <!--box-->
                                            <div class="box corner-all" >
                                                <!--box header-->
                                                <div class="box-header grd-white color-silver-dark corner-top">
                                                    <div class="header-control">
                                                        <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                                        <a data-box="close">×</a>
                                                    </div>
                                                    <span>紧急联系人信息</span>
                                                </div><!--/box header-->
                                                <!--box body-->
                                                <div class="box-body" id="emergencyPeopleDiv">
                                                  
                                                   <script id="emergencyPeopleHtml" name="content" type="text/plain">紧急联系人信息</script>
                                                   <button type="button" class="btn btn-primary" id="emergencyPeople_info_save">Save</button>
                                                   <button type="button" class="btn">Cancel</button>
                                                   
                                                </div><!--/box body-->
                                                
                                                
                                                 
                                            </div><!--/box-->
                                        </div><!--/span--> 
                                    </div><!--/validation-->
                                    
                                     <div id="emergencyPeopleHtmlSource" class="row-fluid">
                                        <!--span-->
                                        <div class="span12">
                                            <!--box-->
                                            <div class="box corner-all" >
                                                <!--box header-->
                                                <div class="box-header grd-white color-silver-dark corner-top">
                                                    <div class="header-control">
                                                        <a data-box="collapse"><i class="icofont-caret-up"></i></a>
                                                        <a data-box="close">×</a>
                                                    </div>
                                                    <span>紧急联系人信息</span>
                                                </div><!--/box header-->
                                                <!--box body-->
                                                <div class="box-body" id="emergencyPeopleDiv">
                                                  
                                                   <script id="emergencyPeopleHtml" name="content" type="text/plain">紧急联系人信息</script>
                                                   <button type="button" class="btn btn-primary" id="emergencyPeople_info_save">Save</button>
                                                   <button type="button" class="btn">Cancel</button>
                                                   
                                                </div><!--/box body-->
                                                
                                                
                                                 
                                            </div><!--/box-->
                                        </div><!--/span--> 
                                    </div><!--/validation-->
          
                                    
                         </div><!--/span-->
                            </div><!--/row-fluid-->
                            <!--/form-->
                        </div>
                      
                  		<!--/中间主体部分 -->
                    </div><!-- /content -->
                </div><!-- /span content -->
                
                <!-- 首页右侧 --> 
                <jsp:include page="Right.jsp"/>
                 <!-- /首页右侧 --> 
       
            </div>
        </section>
 	
 	
 	
 	<jsp:include page="Footer.jsp"/>
 	<jsp:include page="Js.jsp"/>
    <!--   <script type="text/javascript" src="<%=basePath %>pages/js/jquery.form.js"></script>-->
     <script type="text/javascript" src="<%=basePath %>pages/js/employee/employeeManager.js"></script>
    <!--  <script src="<%=basePath%>pages/js/jquery-1.7.2.min.js" type="text/javascript"></script> -->
    <script src="<%=basePath%>pages/js/ajaxfileupload.js" type="text/javascript"></script>
 	
 	<script type="text/javascript">
 	 $(function () {
 		 var but_id;
 		 var id;
 		 var uuid;
 		 var imageSelect;
 		 var imageDiv;
 		 var showImageDiv;
 		 
 	       $(".upload_but").click(function () {
 	    	   uuid = $('#emp_uuid').text();
 	    	   /*
 	    	  if(!uuid){
 	    		 alert("请先填写个人信息！"); 
 	    		 return false;
 	    	  }
 	    	   */
 	    	 
 	    	  but_id = $(this).attr('id');
 	    	 
 	    	   if(but_id=="idcardImageA_but"){
 	    		   id="file1";
 	    		   if(!$('#file1').val()){
 	    			   alert("请先选择图片");
 	    			  return false; 
 	    		   }
 	    		  imageSelect="idCardImageA";
 	    		  imageDiv = "image_div1";
 	    		  showImageDiv="image_divA";
 	    	   }
 	    	   if(but_id=="idcardImageB_but"){
 	    		  id="file2";
 	    		 if(!$('#file2').val()){
	    			   alert("请先选择图片");
	    			  return false; 
	    		   }
 	    		 imageSelect="idCardImageB";
 	    		 imageDiv = "image_div2";
 	    		 showImageDiv="image_divB";
 	    	   }
 	    	   if(but_id=="headImg_but"){
 	    		  id="file3";
 	    		 if(!$('#file3').val()){
	    			   alert("请先选择图片");
	    			  return false; 
	    		   }
 	    		 imageSelect="headImage";
 	    		imageDiv = "image_div3";
 	    		 showImageDiv="image_divC";
 	    	   }
 	    	 
 	    	   ajaxFileUpload(id,imageDiv,showImageDiv);
 	       })
 	   })
 	   function ajaxFileUpload(id,imageDiv,showImageDiv) {
 	       $.ajaxFileUpload
 	       (
 	           {
 	               url: '<%=basePath%>employee/uploadImg?imageSelect=idCardImageA&employeeVo.uuid=0260cb0f-22df-4be6-a844-9b630ede9bdc', //用于文件上传的服务器端请求地址
 	               secureuri: false, //是否需要安全协议，一般设置为false
 	               fileElementId: id, //文件上传域的ID
 	               dataType: 'json', //返回值类型 一般设置为json
 	               success: function (data, status)  //服务器成功响应处理函数
 	               {
 	            	   alert("ok");
 	            	   data = eval('('+data+')');
 	            	
 	            	  $("#"+showImageDiv).append("<div><img src='<%=basePath%>"+data.imagePath+"' height='100' width='100'/></div>");
 	                  $("#"+imageDiv).css("display","none");
 	               },
 	               error: function (data, status, e)//服务器响应失败处理函数
 	               {
 	                   alert(e);
 	               }
 	           }
 	       )
 	       return false;
 	   }
 	
 	</script>
 	<script type="text/javascript" src="<%=basePath%>ueditor/ueditor.config.js"></script>
    <!-- 编辑器源码文件 -->
    <script type="text/javascript" src="<%=basePath%>ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath %>ueditor/lang/zh-cn/zh-cn.js"></script>
    <!-- 实例化编辑器 -->
    <script type="text/javascript">

        var familyHtmlUe = UE.getEditor('familyHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                       ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight']
                                                   ], initialFrameHeight:250});
        var emergencyPeopleHtmlUe = UE.getEditor('emergencyPeopleHtml',{toolbars: [['source', 'undo', 'redo','cleardoc','fontfamily','fontsize','paragraph','simpleupload','insertimage','attachment'],
                                                                 ['bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc','justifyleft','justifycenter','justifyright','lineheight']
                                                             ], initialFrameHeight:250});

        
    </script>
  </body>
</html>
