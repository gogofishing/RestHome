<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

    <link rel="stylesheet" type="text/css" href="<%=basePath %>codebase/GooUploader.css"/>
	<script  type="text/javascript" src="<%=basePath %>codebase/jquery-1.3.2.min.js"></script>
	<script  type="text/javascript" src="<%=basePath %>codebase/GooUploader.js"></script>
	<script type="text/javascript" src="<%=basePath %>codebase/swfupload/swfupload.js"></script>
   <script type="text/javascript">
var demo;

var  post_params = {'imageSelect':'idCardImageA',
		'employeeVo.uuid':'0260cb0f-22df-4be6-a844-9b630ede9bdc'
		
		};
		

var property={
	width:300,
	height:200,
	multiple:false,
	
	post_params:post_params,
    btn_add_text:"添加",
    btn_up_text:"上传",
    btn_cancel_text:"放弃",
    btn_clean_text:"清空",
    op_del_text:"单项删除",
    op_up_text:"单项上传",
    op_fail_text:"上传失败",
    op_ok_text:"上传成功",
    op_no_text:"取消上传",
	upload_url:"/RestHome/employee/uploadImg",
	flash_url :"codebase/swfupload.swf",
};

$(document).ready(function(){
  demo=$.createGooUploader($("#demo"),property);

});
 </script>
	
  </head>
  
  <body>
  <form action="<%=basePath%>employee/uploadImg" method="post">
   <label>图片上传:</label>
    <!--  <input type="hidden" name="imageSelect" value="idcardImageA" id="photoadd" />
     <input type="hidden" name="employeeVo.uuid" value="0260cb0f-22df-4be6-a844-9b630ede9bdc" id="photoadd" />-->
    <div id="demo"></div>
	       <a href="javascript:$('#picshow').html('');$('#photoadd').html('')"  >清除图片</a><br>  <br> 
			<div id="picshow"></div> 
	<!-- <input type="submit" value="提交"/> -->
</form>
  </body>
</html>
