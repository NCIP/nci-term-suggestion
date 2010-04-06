<%@ page import="gov.nih.nci.evs.browser.common.*" %>
<html>
  <body>
	<%
      //String errorMsg = Constants.ERROR_MESSAGE;
	  String errorMsg = "systemMessage";
      String message = (String) request.getSession().getAttribute(errorMsg); 
    %>
	<b><%=message%></b>
  </body>
</html>        
