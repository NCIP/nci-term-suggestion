<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.utils.*" %>
<%
  String version = BaseRequest.getVersion(request);
  
  System.out.println("index.jsp version: " + version);
  
  
%>
<html>
  <body>
    <% if (version == null || version.compareToIgnoreCase("null") == 0 || version.compareToIgnoreCase("Default") == 0) { 
    
    %>
       <jsp:forward page="/pages/main/suggestion.jsf"/>
      <% } else if (version != null && version.compareToIgnoreCase("CDISC") == 0) { 
      
      
      %>
           <jsp:forward page="/pages/main/suggestion_cdisc.jsf"/>
      <% } else { 
           System.out.println("index.jsp suggestion.jsf?version=cadsr??? " + version);
      %>
           <jsp:forward page="/pages/main/suggestion.jsf?version=cadsr"/>
    <% } %>
  </body>
</html>
