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
  LBUtils.MODIFIABLE_PROPERTY property = LBUtils.MODIFIABLE_PROPERTY.DEFINITION;
%>
<h:form method="post">
  <b>Concept Information:</b>
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
            list = LBUtils.MODIFIABLE_PROPERTY.getNameList();
            iterator = list.iterator();
            selectedItem = property.getName();
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
  
  <br/>
  <b>List of <%=property.getName()%>s:</b>
  <table>
    <%
      list = LBUtils.getPropertyList(property);
      iterator = list.iterator();
      int i=0;
      while (iterator.hasNext()) {
        String item = (String) iterator.next();
        String rowColor = (i%2 == 0) ? "dataRowDark" : "dataRowLight"; ++i;
    %>
      <tr class="<%=rowColor%>">
        <td valign="top"><input type="radio" name="selectedProperty" value="<%=item%>"/></td>
        <td colspan="2"><%=item%></td>
      </tr>
    <%
      }
    %>
  </table>
</h:form>
<!-- File: content_modify_concept.jsp (End) -->