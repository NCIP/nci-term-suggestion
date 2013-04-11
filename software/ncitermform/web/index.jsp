<%--L
  Copyright Northrop Grumman Information Technology.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
L--%>

<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.utils.*" %>
<%
  Prop.Version version = BaseRequest.getVersion(request);
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
