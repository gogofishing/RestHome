<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <!-- javascript -->
       <!--  <script type="text/javascript" src="http://platform.twitter.com/widgets.js"></script> -->
        <script src="<%=basePath%>pages/js/jquery-2.1.1.min.js"></script>
        <script src="<%=basePath%>pages/js/jquery-ui.min.js"></script> 
        <script src="<%=basePath%>pages/js/bootstrap.js"></script>
        <script src="<%=basePath%>pages/js/uniform/jquery.uniform.js"></script>
        <script src="<%=basePath%>pages/js/peity/jquery.peity.js"></script>

        <script src="<%=basePath%>pages/js/select2/select2.js"></script>
        <script src="<%=basePath%>pages/js/knob/jquery.knob.js"></script>
        <script src="<%=basePath%>pages/js/flot/jquery.flot.js"></script>
        <script src="<%=basePath%>pages/js/flot/jquery.flot.resize.js"></script>
        <script src="<%=basePath%>pages/js/flot/jquery.flot.categories.js"></script>
        <script src="<%=basePath%>pages/js/wysihtml5/wysihtml5-0.3.0.js"></script>
        <script src="<%=basePath%>pages/js/wysihtml5/bootstrap-wysihtml5.js"></script>
        <script src="<%=basePath%>pages/js/calendar/fullcalendar.js"></script> <!-- this plugin required jquery ui-->

        <!-- required stilearn template js, for full feature-->
        <script src="<%=basePath%>pages/js/holder.js"></script>
        <script src="<%=basePath%>pages/js/stilearn-base.js"></script>
        <script src="<%=basePath%>pages/js/jsJsp.js"></script>
        <script src="<%=basePath%>pages/js/util/util.js"></script>
        <script src="<%=basePath%>pages/js/RestHomeGlobal.js"></script>
        
        <script src="<%=basePath%>pages/js/datatables/jquery.dataTables.min.js"></script>
        <script src="<%=basePath%>pages/js/datatables/extras/ZeroClipboard.js"></script>
        <script src="<%=basePath%>pages/js/datatables/extras/TableTools.min.js"></script>
        <script src="<%=basePath%>pages/js/datatables/DT_bootstrap.js"></script>
        <script src="<%=basePath%>pages/js/responsive-tables/responsive-tables.js"></script>
<!--

//-->
</script>
        
        
