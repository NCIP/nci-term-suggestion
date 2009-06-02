<!-- File: content_modify_concept.jsp (Begin) -->
<%@ page import="java.util.*" %>
<%@ page import="gov.nih.nci.evs.newtermform.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  List list = null;
  Iterator iterator = null;
  String selectedItem = null;
  String vocabulary = "CDISC_0902D";
  String conceptCode = "conceptCode";
  String property = LBUtils.MODIFIABLE_PROPERTIES.SYNONYM.getName();
%>
<h:form method="post">
  <table>
    <tr>
      <td>Vocabulary:</td>
      <td>
        <select name="vocabulary">
          <%
            list = LBUtils.getVocabularyList();
            iterator = list.iterator();
            selectedItem = vocabulary;
            while (iterator.hasNext()) {
              String item = (String) iterator.next();
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
      <td>Concept Code:</td>
      <td>
        <input name="conceptCode" value="<%=conceptCode%>" alt="conceptCode" <%=INPUT_ARGS%>>
        <a href="http://www.google.com">Search</a>
        <a href="http://www.yahoo.com">Display</a>
      </td>
    </tr>
    <tr>
      <td>Property</td>
      <td>
        <select name="property">
          <%
            list = LBUtils.MODIFIABLE_PROPERTIES.getNameList();
            iterator = list.iterator();
            selectedItem = property;
            while (iterator.hasNext()) {
              String item = (String) iterator.next();
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
  </table>
</h:form>
<!-- File: content_modify_concept.jsp (End) -->