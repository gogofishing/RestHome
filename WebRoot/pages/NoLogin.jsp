<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP '500.jsp' starting page</title>
    
	 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
     <meta name="description" content="" />
     <meta name="author" content="stilearning" />
 
	<jsp:include page="css.jsp"/>

  </head>
  
  <body>
  
  <!-- section header -->
        <header class="header" data-spy="affix" data-offset-top="0">
            <!--nav bar helper-->
            <div class="navbar-helper">
                <div class="row-fluid">
                    <!--panel site-name-->
                    <div class="span2">
                        <div class="panel-sitename">
                            <h2><a href="index.html"><span class="color-teal">OA</span>网上办公</a></h2>
                        </div>
                    </div>
                    <!--/panel name-->
                </div>
            </div><!--/nav bar helper-->
        </header>
   
     <!-- section content -->
        <section class="section">
            <div class="container">
                <div class="error-page">
                    <h1 class="error-code color-red">您还未登陆</h1>
                    <p class="error-description muted">请先登陆，再进行当前操作</p>
                    <form action="#" method="get" >
                        <div class="control-group">
                            <div class="input-append input-icon-prepend">
                               <!--  <div class="add-on">
                                    <a title="search" style="" class="icon"><i class="icofont-search"></i></a>
                                    <input class="input-xxlarge animated grd-white" type="text" placeholder="what you looking for?" />
                                </div>
                                <input type="submit" class="btn" value="Search" /> -->
                            </div>
                        </div>
                    </form>
                    <a href="<%=basePath %>pages/login.jsp" class="btn btn-small btn-primary"><i class="icofont-arrow-left"></i>返回登录</a>
                   <!--  <a href="index.html" target="_blank" class="btn btn-small btn-success">Back to Site <i class="icofont-arrow-right"></i></a> -->
                </div>
            </div>
        </section>
        
        <jsp:include page="Footer.jsp"/>
        
        <!-- <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script> -->
        <script src="<%=basePath %>pages/js/jquery.js"></script>
        <script src="<%=basePath %>pages/js/bootstrap.js"></script>
  </body>
</html>
