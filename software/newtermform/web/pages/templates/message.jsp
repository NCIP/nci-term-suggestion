<!-- Begin: templates/message.jsp -->
<% String message = (String) request.getSession().getAttribute("message"); %>
<b><%=message%></b>
<!-- End: templates/message.jsp -->
