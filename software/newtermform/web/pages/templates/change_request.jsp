<!-- File: change_request.jsp (Begin) -->
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  String firstName = "firstName";
  String lastName = "lastName";
  String affiliation = "affiliation";
  String phone = "phone";
  String email = "email";
%>
<form method="post">
  <table>
    <tr>
      <td>First Name:</td>
      <td><input name="firstName" value="<%=firstName%>" alt="First Name" <%=INPUT_ARGS%>></td>
      <td>Phone:</td>
      <td><input name="phone" value="<%=phone%>" alt="Phone" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Last Name:</td>
      <td><input name="lastName" value="<%=lastName%>" alt="Last Name" <%=INPUT_ARGS%>></td>
      <td>Email:</td>
      <td><input name="email" value="<%=email%>" alt="Email Address" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Affiliation:</td>
      <td><input name="affiliation" value="<%=affiliation%>" alt="Affiliation" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Request:</td>
      <td>
        <select>
         <option>New Concept
         <option>Concept Modification
        </select>
      </td>
      <td/>
      <td align="right"><INPUT type="submit" name="submit" value="Proceed"></td>
    </tr>
  </table>
</form>
<!-- File: change_request.jsp (End) -->
