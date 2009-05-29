<!-- Begin: templates/message.jsp -->
<% String message = request.getSession().getAttribute("message"); %>
<b><%=message%></b>
<!-- End: templates/message.jsp -->
