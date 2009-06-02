<!-- File: content_new_concept.jsp (Begin) -->
<%@ page import="java.util.*" %>
<%@ page import="gov.nih.nci.evs.newtermform.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
  private static final int NoneConcept = 0;
  private static final int ParentConcept = 1;
  private static final int NearestConcept = 2;
%>
<%
  List list = LBUtils.getVocabularyList();
  String vocabulary = "CDISC_0902D";
  String conceptName = "concept name";
  byte radioConcept = ParentConcept;
  String parentConcept = "parent concept";
  String nearestConcept = "nearest concept";
  String message = "message";
%>
<h:form method="post">
  <table>
    <tr>
      <td></td>
      <td>Vocabulary:</td>
      <td>
        <select name="vocabulary">
          <%
            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
              String item = (String) iterator.next();
              String args = "";
              if (item.equals(vocabulary))
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
      <td></td>
      <td>New Concept Name:</td>
      <td><input name="conceptName" value="<%=conceptName%>" alt="conceptName" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <% String checked = radioConcept==ParentConcept ? "checked=\"checked\" " : ""; %>
      <td><input type="radio" name="radioConcept" value="Parent Concept" <%=checked%>/></td>
      <td>Parent Concept:</td>
      <td><input name="parentConcept" value="<%=parentConcept%>" alt="parentConcept" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <% checked = radioConcept==NearestConcept ? "checked=\"checked\" " : ""; %>
      <td><input type="radio" name="radioConcept" value="Nearest Concept" <%=checked%>/></td>
      <td>Nearest Concept:</td>
      <td><input name="nearestConcept" value="<%=nearestConcept%>" alt="nearestConcept" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td></td>
      <td colspan="2">Enter a description of the term and the reason for adding it:</td>
    </tr>
    <tr>
      <td></td>
      <td colspan="2"><textarea class="textbody" name="message" alt="message" rows="10" cols="50"><%=message%></textarea></td>
    </tr>
    <tr>
      <td></td>
      <td><a href="<%=request.getContextPath()%>/pages/change_request.jsp">Back</a></td>
      <td align="right"><INPUT type="submit" name="submit" value="Submit"></td>
    </tr>
  </table>
</h:form>
<!-- File: content_new_concept.jsp (end) -->