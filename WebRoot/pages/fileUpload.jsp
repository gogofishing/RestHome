<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'fileUpload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="/RestHome/uploadify-v2.1.0/example/css/default.css"
     rel="stylesheet" type="text/css" />
    <link href="/RestHome/uploadify-v2.1.0/uploadify.css"
     rel="stylesheet" type="text/css" />
     <script type="text/javascript"
     src="/RestHome/uploadify-v2.1.0/jquery-1.3.2.min.js"></script>

    <script type="text/javascript"
     src="/RestHome/uploadify-v2.1.0/swfobject.js"></script>

    <script type="text/javascript"
   src="/RestHome/uploadify-v2.1.0/jquery.uploadify.v2.1.0.min.js"></script>
  <script type="text/javascript">
  $(document).ready(function()
	        {
	 
	            $("#uploadify").uploadify({
	                'uploader': '/RestHome/uploadify-v2.1.0/uploadify.swf',
	                'script': '/RestHome/employee/uploadImg',
	                'cancelImg': '/RestHome/uploadify-v2.1.0/cancel.png',
	                'folder': 'UploadFile',
	                'fileDataName': 'image',
	                'queueID': 'fileQueue',
	                'auto': false,
	                'multi': true
	            });
	        });  
  </script>
  </head>
  
  <body>

       <div id="fileQueue"></div>
    <input type="file" name="image" id="uploadify" />
    <p>
      <a href="javascript:$('#uploadify').uploadifyUpload()">上传</a>| 
      <a href="javascript:$('#uploadify').uploadifyClearQueue()">取消上传</a>
    </p>
 

  </body>
</html>
