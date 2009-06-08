<!-- File: content_change_request.jsp (Begin) -->
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  String firstName = "John";
  String lastName = "Doe";
  String affiliation = "ABC Corporation";
  String phone = "301-443-8000";
  String email = "John.Doe@abc.com";
%>
<div class="texttitle-blue">Contact Information:</div><br/>
<form method="post">
  <table class="datatable">
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
         <option>New Concept</option>
         <option>Concept Modification</option>
        </select>
      </td>
      <td/>
      <td align="right"><INPUT type="submit" name="submit" value="Next"></td>
      </tr>
  </table>
</form>
<!-- File: content_change_request.jsp (End) -->
