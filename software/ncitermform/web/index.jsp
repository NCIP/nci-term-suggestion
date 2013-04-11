<%--L
  Copyright Northrop Grumman Information Technology.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
L--%>

<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.utils.*" %>
<%
  String action = HTTPUtils.cleanXSS((String) request.getParameter("action"));
  String version = null;
  if (action == null) {
      version = BaseRequest.getVersion(request);
  } 
  System.out.println("(*) index.jsp version= " + version);
%>
<html>
  <body>
    <% if (version == null || version.compareToIgnoreCase("") == 0 || version.compareToIgnoreCase("null") == 0 || version.compareToIgnoreCase("Default") == 0) { 
    %>
       <jsp:forward page="/pages/main/suggestion.jsf"/>
      <% } else if (version != null && version.compareToIgnoreCase("CDISC") == 0) { 
      
      %>
           <jsp:forward page="/pages/main/suggestion_cdisc.jsf"/>
      <% } else { 
      %>
           <jsp:forward page="/pages/main/suggestion.jsf?version=cadsr"/>
    <% } %>
  </body>
</html>
