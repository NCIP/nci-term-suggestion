<script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
<%
  String basePath = request.getContextPath();
%>
<div class="texttitle-blue">Change Request:</div><br/>
Do you want to suggest:
<br/>
<br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="group1" onclick="go('<%=basePath%>/pages/new_concept.jsf')">A new concept, or
<br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="group1" onclick="go('<%=basePath%>/pages/modify_concept.jsp')">A modification to a concept
<br/>
