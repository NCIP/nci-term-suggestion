<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%
  Prop.Version version = FormUtils.getVersion(request);
%>
<html>
  <body>
    <% if (version == Prop.Version.CDISC) { %>
      <jsp:forward page="/pages/main/suggestion_cdisc.jsf"/>
    <% } else { %>
      <jsp:forward page="/pages/main/suggestion.jsf"/>
    <% } %>
  </body>
</html>
