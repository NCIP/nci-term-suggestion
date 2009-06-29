<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%
  String message = HTTPUtils.getSessionAttributeString(request, "message", true);
%>
<b><%=message%></b>
