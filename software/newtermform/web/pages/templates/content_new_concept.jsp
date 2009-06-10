<!-- File: content_new_concept.jsp (Begin) -->
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  String vocabulary = "NCI Thesaurus";
  String conceptName = "Ultra_Murine_Cell_Types";
  String preferredName = "Ultra Murine Cell Types";
  String semanticType = "Cell Type";
  LBUtils.RELATIVE_TO relativeTo = LBUtils.RELATIVE_TO.Parent;
  String parentCode = "C23442";
  String nearestCode = "C23442";
  String definition =
      "The smallest units of living structure capable of independent" + 
      " existence, composed of a membrane-enclosed mass of protoplasm" +
      " and containing a nucleus or nucleoid. Cells are highly variable" +
      " and specialized in both structure and function, though all must" +
      " at some stage replicate proteins and nucleic acids, utilize" +
      " energy, and reproduce themselves.";
  String message = "New improved version of the previous type.";
  int i=0;
  String items[] = null;
  String selectedItem = null;
%>
<div class="texttitle-blue">Suggest New Concept:</div><br/>
<form method="post">
  <table class="datatable">
    <tr>
      <td></td>
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
      <td></td>
      <td>Concept Name:</td>
      <td><input name="conceptName" value="<%=conceptName%>" alt="conceptName" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td></td>
      <td>Preferred Name:</td>
      <td><input name="preferredName" value="<%=preferredName%>" alt="preferredName" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td></td>
      <td>Semantic Type:</td>
      <td><input name="semanticType" value="<%=semanticType%>" alt="semanticType" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <%
        LBUtils.RELATIVE_TO relativeToTmp = LBUtils.RELATIVE_TO.Parent;
        String checked = relativeTo==relativeToTmp ? "checked=\"checked\" " : ""; 
      %>
      <td><input type="radio" name="relativeTo" value="<%=relativeToTmp.getName()%>" <%=checked%>/></td>
      <td><%=relativeToTmp.getName()%>:</td>
      <td><input name="parentCode" value="<%=parentCode%>" alt="parentCode" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <%
        relativeToTmp = LBUtils.RELATIVE_TO.Nearest;
        checked = relativeTo==relativeToTmp ? "checked=\"checked\" " : ""; 
      %>
      <td><input type="radio" name="relativeTo" value="<%=relativeToTmp.getName()%>" <%=checked%>/></td>
      <td><%=relativeToTmp.getName()%>:</td>
      <td><input name="nearestCode" value="<%=nearestCode%>" alt="nearestCode" <%=INPUT_ARGS%>></td>
    </tr>
    <tr>
      <td></td>
      <td colspan="2">Definition:</td>
    </tr>
    <tr>
      <td></td>
      <td colspan="2"><textarea class="textbody" name="definition" rows="4" cols="95"><%=definition%></textarea></td>
    </tr>
    <tr>
      <td></td>
      <td colspan="2">Enter a description of the term and the reason for adding it:</td>
    </tr>
    <tr>
      <td></td>
      <td colspan="2"><textarea class="textbody" name="message" rows="4" cols="95"><%=message%></textarea></td>
    </tr>
    <tr>
      <td></td>
      <td><a href="<%=request.getContextPath()%>/pages/change_request.jsp">Back</a></td>
      <td align="right"><INPUT type="submit" name="submit" value="Submit"></td>
    </tr>
  </table>
</form>
<!-- File: content_new_concept.jsp (end) -->
