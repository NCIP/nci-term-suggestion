<!-- File: content_modify_concept.jsp (Begin) -->
<%@ page import="gov.nih.nci.evs.newtermform.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String TEXTAREA_ARGS = "rows=\"4\" cols=\"80\"";
%>
<%
  String propertyParam = request.getParameter("property");
  String vocabulary = "CDISC_0902D";
  String conceptCode = "C12434";
  LBUtils.MODIFIABLE_PROPERTY property = LBUtils.MODIFIABLE_PROPERTY.valueOfOrDefault(propertyParam);
  String selectedProperty = LBUtils.getProperty(property, 1);
  String modification = "modification";
  LBUtils.PROPERTY_ACTION action = LBUtils.PROPERTY_ACTION.Modify;
  String description = "description";
  String notes = "notes";
  int i=0;
  String[] items = null;
  String selectedItem = null;
%>
<div class="texttitle-blue">Suggest Concept Modification:</div><br/>
<form method="post">
  <!-- --------------------------------------------------------------------- -->
  <b>Concept Information:</b>
  <table>
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
      <td>Concept Code:</td>
      <td>
        <input name="conceptCode" value="<%=conceptCode%>" alt="conceptCode" <%=INPUT_ARGS%>>
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
              LBUtils.MODIFIABLE_PROPERTY mprop = mprops[i];
              String args = "";
              if (mprop.equals(property))
                  args += "selected=\"true\"";
          %>
              <option value="<%=mprop%>" <%=args%>><%=mprop%></option>
          <%
            }
          %>
        </select>
      </td>
    </tr>
  </table>
  
  <!-- --------------------------------------------------------------------- -->
  <%
    String propertyNameLC = property.name().toLowerCase();
    if (property != LBUtils.MODIFIABLE_PROPERTY.Others) {
  %>
      <!-- ----------------------------------------------------------------- -->
      <br/><b>Select a <%=propertyNameLC%>:</b>
      <table width="70%">
        <%
          items = LBUtils.getProperties(property);
          selectedItem = selectedProperty;
          for (i=0; i<items.length; ++i) {
            String item = items[i];
            String checked = item==selectedItem ? "checked=\"checked\" " : "";
            String rowColor = (i%2 == 0) ? "dataRowDark" : "dataRowLight";
        %>
          <tr class="<%=rowColor%>">
            <td valign="top"><input type="radio" name="selectedProperty" value="<%=item%>" <%=checked%>/></td>
            <td colspan="2"><%=item%></td>
          </tr>
        <%
          }
        %>
      </table>
    
      <!-- ----------------------------------------------------------------- -->
      <br/><b>Suggest a new <%=propertyNameLC%> or modify an existing one:</b>
      <table>
        <tr>
          <% modification = selectedProperty; %>
          <td><textarea class="textbody" name="modification" <%=TEXTAREA_ARGS%>><%=modification%></textarea></td>
          <td valign="top">
            <table>
              <%
                LBUtils.PROPERTY_ACTION[] pActions = LBUtils.PROPERTY_ACTION.values();
                for (i=0; i<pActions.length; ++i) {
                  LBUtils.PROPERTY_ACTION pAction = pActions[i];
                  String checked = pAction==action ? "checked=\"checked\" " : "";
                  String pActionName = pAction.name();
              %>
                <tr>
                  <td valign="top"><input type="radio" name="action" value="<%=pActionName%>" <%=checked%>/></td>
                  <td colspan="2"><%=pActionName%></td>
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
      <!-- ----------------------------------------------------------------- -->
      <br/><b>Brief description of your modification:</b>
      <table>
        <tr>
          <td><textarea class="textbody" name="description" <%=TEXTAREA_ARGS%>><%=description%></textarea></td>
        </tr>
      </table>
  <%
    }
  %>

  <!-- --------------------------------------------------------------------- -->
  <br/><b>Notes or comments (if any):</b>
  <table>
    <tr>
      <td><textarea class="textbody" name="notes" <%=TEXTAREA_ARGS%>><%=notes%></textarea></td>
    </tr>
  </table>
  
  <!-- --------------------------------------------------------------------- -->
  <table>
    <tr>
     <td><a href="<%=request.getContextPath()%>/pages/change_request.jsp">Back</a></td>
     <td align="right"><INPUT type="submit" name="submit" value="Submit"></td>
    </tr>
  </table>
</form>
<!-- File: content_modify_concept.jsp (End) -->