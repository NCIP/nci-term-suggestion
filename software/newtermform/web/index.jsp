<%
  String basePath = request.getContextPath();
%>
<html>
  <body>
    <!-- <dyee:jsp:forward page="/pages/change_request.jsp"/> -->
    <a href="<%=basePath%>/pages/change_request.jsp">Change Request</a>
    <br/><a href="<%=basePath%>/pages/new_concept.jsp">New Concept</a>
  </body>
</html>
