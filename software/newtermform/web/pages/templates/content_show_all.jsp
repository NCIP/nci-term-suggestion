<!-- File: content_show_all.jsp (Begin) -->
<%
  String basePath = request.getContextPath();
%>
<a href="<%=basePath%>/pages/change_request.jsp">Change Request</a>
<br/><a href="<%=basePath%>/pages/new_concept.jsp">New Concept</a>
<br/><a href="<%=basePath%>/pages/modify_concept.jsp?property=Definition">Modify Concept (Definition)</a>
<br/><a href="<%=basePath%>/pages/modify_concept.jsp?property=Synonym">Modify Concept (Synonym)</a>
<br/><a href="<%=basePath%>/pages/modify_concept.jsp?property=Others">Modify Concept (Others)</a>
<!-- File: content_show_all.jsp (End) -->
