<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%
  String ncicb_contact_url = AppProperties.getInstance().getContactUrl();
  String subject = HTTPUtils.getParameter(request, "subject");
  String message = HTTPUtils.getParameter(request, "message");
  String emailaddress = HTTPUtils.getParameter(request, "message");
  String errorMsg = HTTPUtils.getSessionAttributeString(request, "errorMsg");
  String errorType = HTTPUtils.getSessionAttributeString(request, "errorType");
  boolean userError = errorType.equalsIgnoreCase("user");
%>
<%=ncicb_contact_url%>
