<!-- File: content_new_concept.jsp (Begin) -->
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  String email = "John.Doe@abc.com";
  String other = "Phone: 987-654-3210";

  String vocabulary = "NCI Thesaurus";
  String term = "Ultra Murine Cell Types";
  String synonyms = "";
  LBUtils.RELATIVE_TO relativeTo = LBUtils.RELATIVE_TO.Parent;
  String parentCode = "C23442";
  String definition =
      "The smallest units of living structure capable of independent" + 
      " existence, composed of a membrane-enclosed mass of protoplasm" +
      " and containing a nucleus or nucleoid. Cells are highly variable" +
      " and specialized in both structure and function, though all must" +
      " at some stage replicate proteins and nucleic acids, utilize" +
      " energy, and reproduce themselves.";
  String reason = "New improved version of the previous type.";
  int i=0;
  String items[] = null;
  String selectedItem = null;
%>
<form method="post">
  <div class="texttitle-blue">Suggest New Concept:</div><br/>
  <table class="datatable">
    <tr>
      <td colspan="2"><b>Contact Information:</b></td>
    </tr>
    <tr>
      <td>Email:</td>
      <td><input name="email" value="<%=email%>" alt="email" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td valign="top">Other:</td>
      <td><textarea class="textbody" name="other" rows="4" cols="30"><%=other%></textarea></td>
    </tr>
    <tr>
      <td><br/></td>
    </tr>

    <tr>
      <td colspan="2"><b>Term Information:</b></td>
    </tr>
    <tr>
      <td>Vocabulary:</td>
      <td>
        <select name="vocabulary">
          <%
            items = LBUtils.getVocabularies();
            selectedItem = vocabulary;
            for (i=0; i<items.length; ++i) {
              String item = items[i];
              String args = "";
              if (item.equals(selectedItem))
                args += "selected=\"true\"";
          %>
              <option value="<%=item%>" <%=args%>><%=item%></option>
          <%
            }
          %>
        </select>
      </td>
    </tr>
    <tr>
      <td>Term:</td>
      <td><input name="term" value="<%=term%>" alt="preferredName" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Synonyms:</td>
      <td><input name="synonyms" value="<%=synonyms%>" alt="synonyms" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td>Parent Concept Code:</td>
      <td><input name="parentCode" value="<%=parentCode%>" alt="parentCode" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td colspan="2">Definition:</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="textbody" name="definition" rows="4" cols="95"><%=definition%></textarea></td>
    </tr>
    <tr>
      <td colspan="2">Reason for adding plus any additional information:</td>
    </tr>
    <tr>
      <td colspan="2"><textarea class="textbody" name="reason" rows="4" cols="95"><%=reason%></textarea></td>
    </tr>
    <tr>
      <td><a href="<%=request.getContextPath()%>/pages/change_request.jsp">Back</a></td>
      <td align="right"><INPUT type="submit" name="submit" value="Submit"></td>
    </tr>
  </table>
</form>
<!-- File: content_new_concept.jsp (end) -->
