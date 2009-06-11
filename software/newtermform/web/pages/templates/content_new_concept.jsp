<!-- File: content_new_concept.jsp (Begin) -->
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%!
  private static final String INPUT_ARGS = 
    "class=\"textbody\" onFocus=\"active=true\" onBlur=\"active=false\"" +
    " onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String LABEL_ARGS = "valign=\"top\"";
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
  <table class="newConceptDT">
    <!-- =================================================================== -->
    <tr><td colspan="2"><b>Contact Information:</b></td></tr>
    <tr>
      <td <%=LABEL_ARGS%>>Email:</td>
      <td><input class="newConceptTF" name="email" value="<%=email%>" alt="email" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td <%=LABEL_ARGS%>>Other:</td>
      <td><textarea class="newConceptTA" name="other"><%=other%></textarea></td>
    </tr>

    <!-- =================================================================== -->
    <tr><td><br/></td></tr>
    <tr><td colspan="2"><b>Term Information:</b></td></tr>
    <tr>
      <td <%=LABEL_ARGS%>>Vocabulary:</td>
      <td>
        <select class="newConceptCB" name="vocabulary">
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
      <td <%=LABEL_ARGS%>>Term:</td>
      <td><input class="newConceptTF"  name="term" value="<%=term%>" alt="preferredName" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td <%=LABEL_ARGS%>>Synonyms:</td>
      <td><input class="newConceptTF"  name="synonyms" value="<%=synonyms%>" alt="synonyms" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td <%=LABEL_ARGS%>>Parent Concept Code:</td>
      <td><input class="newConceptTF"  name="parentCode" value="<%=parentCode%>" alt="parentCode" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td <%=LABEL_ARGS%>>Definition:</td>
      <td><textarea class="newConceptTA" name="definition" rows="4" cols="95"><%=definition%></textarea></td>
    </tr>

    <!-- =================================================================== -->
    <tr><td><br/></td></tr>
    <tr><td colspan="2"><b>Additional Information:</b></td></tr>
    <tr>
      <td <%=LABEL_ARGS%>>Reason for adding plus any additional information:</td>
      <td><textarea class="newConceptTA" name="reason" rows="4" cols="95"><%=reason%></textarea></td>
    </tr>

    <!-- =================================================================== -->
    <tr><td><br/></td></tr>
    <tr>
      <td colspan="2" align="right">
        <INPUT type="submit" name="submit" value="Submit">
      </td>
    </tr>
  </table>
</form>
<!-- File: content_new_concept.jsp (end) -->
