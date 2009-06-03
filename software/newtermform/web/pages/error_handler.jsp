<%@ page import="gov.nih.nci.evs.newtermform.common.*" %>
<html>
  <body>        
	<% String message = (String) request.getSession().getAttribute(Constants.ERROR_MESSAGE); %>
	<b><%=message%></b>
  </body>
</html>        
