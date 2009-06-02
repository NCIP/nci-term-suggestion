<!-- File: content_modify_concept.jsp (Begin) -->
<%@ page import="java.util.*" %>
<%@ page import="gov.nih.nci.evs.newtermform.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
%>
<%
  String propertyParam = request.getParameter("property");
  String vocabulary = "CDISC_0902D";
  String conceptCode = "C12434";
  LBUtils.MODIFIABLE_PROPERTY property = LBUtils.MODIFIABLE_PROPERTY.valueOfOrDefault(propertyParam);
  String modification = "modification";
  LBUtils.PROPERTY_ACTION action = LBUtils.PROPERTY_ACTION.Modify;
  String description = "description";
  String notes = "notes";
  int i=0;
  List list = null;
  Iterator iterator = null;
  String selectedItem = null;
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
        <a href="http://localhost:19280/ncitbrowser/ConceptReport.jsp?dictionary=NCI%20Thesaurus&code=C12434" target="_blank">Display</a>
      </td>
    </tr>
    <tr>
      <td>Property</td>
      <td>
        <select name="property">
          <%
            LBUtils.MODIFIABLE_PROPERTY[] mprops = LBUtils.MODIFIABLE_PROPERTY.values();
            for (i=0; i<mprops.length; ++i) {
              LBUtils.MODIFIABLE_PROPERTY item = mprops[i];
              String args = "";
              if (item.equals(property))
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
  
  <%
    if (property != LBUtils.MODIFIABLE_PROPERTY.Others) {
  %>
      <br/><b>Select a <%=property.name().toLowerCase()%>:</b>
      <table>
        <%
          list = LBUtils.getPropertyList(property);
          iterator = list.iterator();
          i=0;
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
    
      <br/><b>Add or modify a selected property:</b>
      <table>
        <tr>
          <td><textarea class="textbody" name="modification" alt="modification" rows="4" cols="50"><%=modification%></textarea></td>
          <td valign="top">
            <table>
              <%
                LBUtils.PROPERTY_ACTION[] items = LBUtils.PROPERTY_ACTION.values();
                for (i=0; i<items.length; ++i) {
                  String item = items[i].name();
                  String checked = item==action.name() ? "checked=\"checked\" " : "";
              %>
                <tr>
                  <td valign="top"><input type="radio" name="action" value="<%=item%> <%=checked%>"/></td>
                  <td colspan="2"><%=item%></td>
                </tr>
              <%
                }
              %>
            </table>
          </td>
        </tr>
      </table>
  <%
    } else {
  %>
      <br/><b>Brief description of your modification:</b>
      <table>
        <tr>
          <td><textarea class="textbody" name="description" alt="description" rows="4" cols="50"><%=description%></textarea></td>
        </tr>
      </table>
  <%
    }
  %>

  <br/><b>Notes/Comments (if any):</b>
  <table>
    <tr>
      <td><textarea class="textbody" name="notes" alt="notes" rows="4" cols="50"><%=notes%></textarea></td>
    </tr>
  </table>
  
  <table>
    <tr>
     <td><a href="<%=request.getContextPath()%>/pages/change_request.jsp">Back</a></td>
     <td align="right"><INPUT type="submit" name="submit" value="Submit"></td>
    </tr>
  </table>
</h:form>
<!-- File: content_modify_concept.jsp (End) -->