<!-- File: template.jsp (Begin) -->
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="gov.nih.nci.evs.newtermform.properties.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  String imagePath = request.getContextPath() + "/images";
  String content_title = request.getParameter("content_title");
  if (content_title == null || content_title.trim().length() <= 0)
      content_title = "Suggest New Term";
  String content_page = "/pages/templates/"; 
  content_page += request.getParameter("content_page");
  String buildInfo = AppProperties.getInstance().getBuildInfo();
%>
<!-- Build info: <%=buildInfo%> -->
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title><%=content_title%></title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/css/styleSheet.css" />
  </head>
  <body>
    <jsp:include page="/pages/templates/header.jsp" />
    <div class="center-page">
      <jsp:include page="/pages/templates/sub_header.jsp" />
      <div class="mainbox-top"><img src="<%=imagePath%>/mainbox-top.gif" 
        width="745" height="5" alt="Mainbox Top" /></div>
      <div id="main-area">
        <div class="pagecontent">
          <jsp:include page="<%=content_page%>" />
          <jsp:include page="/pages/templates/footer.jsp" />
        </div>
      </div>          
      <div class="mainbox-bottom"><img src="<%=imagePath%>/mainbox-bottom.gif"
        width="745" height="5" alt="Mainbox Bottom" /></div>
    </div>
  </body>
</html>
<!-- File: template.jsp (End) -->
