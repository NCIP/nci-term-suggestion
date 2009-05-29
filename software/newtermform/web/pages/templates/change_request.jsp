<!-- File: change_request.jsp (Begin) -->
<%!
  private static final String COL1_WIDTH = "70";
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"100\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  String name = "first, name";
  String affiliation = "affiliation";
  String phone = "phone";
  String email = "email";
%>
<form method="post">
  <table class="datatable">
    <tr>
      <td width="<%=COL1_WIDTH%>">Name:</td>
      <td><input name="name" value="<%=name%>" alt="Name" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Affiliation:</td>
      <td><input name="affiliation" value="<%=affiliation%>" alt="Affiliation" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Phone:</td>
      <td><input name="phone" value="<%=phone%>" alt="Phone" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Email:</td>
      <td><input name="email" value="<%=email%>" alt="Email Address" <%=INPUT_ARGS%>></td>
    </tr>
  </table>
</form>
<!-- File: change_request.jsp (End) -->
