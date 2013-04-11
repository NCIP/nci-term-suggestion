<%--L
  Copyright Northrop Grumman Information Technology.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
L--%>

<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.utils.*" %>
<script type="text/javascript" src="<%= FormUtils.getJSPath(request) %>/utils.js"></script>
<%!
  // List of attribute name(s):
  private static final String MESSAGE = FormRequest.MESSAGE;
  private static final String WARNINGS = FormRequest.WARNINGS;%>
<%
  // Attribute(s):
  String message = HTTPUtils.getJspAttributeString(request, MESSAGE, false, true);
  String warnings = HTTPUtils.getJspAttributeString(request, WARNINGS, false, true);
%>
<div class="texttitle-blue">Change Request:</div><br/>
<%
  String msg = message;
  if (msg != null && msg.length() > 0) {
%>
    <div class="msgColor">
<%
    String[] list = StringUtils.toStrings(msg, "\n", false, false);
    for (int i=0; i<list.length; ++i) {
      String text = list[i];
      text = StringUtils.toHtml(text); // For leading spaces (indentation)
%>
      <%=text%><br/>
<%
    }
%>
   </div><br/>
<%
    }
%>
<%
  msg = warnings;
  if (msg != null && msg.length() > 0) {
%>
    <div class="warningMsgColor">
<%
    String[] list = StringUtils.toStrings(msg, "\n", false, false);
    for (int i=0; i<list.length; ++i) {
      String text = list[i];
      text = StringUtils.toHtml(text); // For leading spaces (indentation)
%>
      <%=text%><br/>
<%
    }
%>
   </div><br/>
<%
  }
%>
Do you want to suggest a new term or suggest a modification to one:
<br/>
<br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="group1" onclick="go('<%=BaseRequest.getIndexPage(request)%>')">Yes
<br/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="group1" onclick="javascript:window.close()">Close Window
<br/>
