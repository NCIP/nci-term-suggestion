<%--L
  Copyright Northrop Grumman Information Technology.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
L--%>

<%@ page import="gov.nih.nci.evs.browser.common.*" %>
<html>
  <body>        
	<% String message = (String) request.getSession().getAttribute(Constants.ERROR_MESSAGE); %>
	<b><%=message%></b>
  </body>
</html>        
