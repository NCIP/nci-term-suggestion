<!-- File: content_show_all.jsp (Begin) -->
<%
  String basePath = request.getContextPath();
%>
The following are samples of the forms to request a change:
<ul>
  <li><a href="<%=basePath%>/pages/change_request.jsp">Change Request</a></li>
  <li><a href="<%=basePath%>/pages/new_concept.jsp">New Concept</a></li>
  <li>Modify Concept:
    <a href="<%=basePath%>/pages/modify_concept.jsp?property=Definition">Definition</a>,
    <a href="<%=basePath%>/pages/modify_concept.jsp?property=Synonym">Synonym</a>,
    <a href="<%=basePath%>/pages/modify_concept.jsp?property=Others">Others</a>
  </li>
</ul>
<!-- File: content_show_all.jsp (End) -->
