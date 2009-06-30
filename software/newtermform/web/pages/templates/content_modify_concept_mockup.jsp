<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%!
  private static final String INPUT_ARGS = "class=\"textbody\" size=\"30\" onFocus=\"active=true\" onBlur=\"active=false\" onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String TEXTAREA_ARGS = "rows=\"4\" cols=\"80\"";
%>
<%
  String imagePath = request.getContextPath() + "/images";
  String propertyParam = HTTPUtils.getParameter(request, "property");
  String vocabulary = "NCI Thesaurus";
  String conceptCode = "C12434";
  Prop.MODIFIABLE_PROPERTY property = Prop.MODIFIABLE_PROPERTY.valueOfOrDefault(propertyParam);
  String selectedProperty = LBUtils.getProperty(property, 1);
  String suggestion = "";
  Prop.PROPERTY_ACTION action = Prop.PROPERTY_ACTION.Modify;
  String description = "";
  String notes = "";
  int i=0;
  String[] items = null;
  String selectedItem = null;
%>
<form method="post">
  <div class="texttitle-blue">Suggest Concept Modification (Mock Up):</div><br/>
  <!-- --------------------------------------------------------------------- -->
  <b>Concept Information:</b>
  <table class="modifyConceptDT">
    <tr>
      <td>Vocabulary:</td>
      <td>
        <select name="vocabulary">
          <%
            items = AppProperties.getInstance().getVocabularyNames();
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
          Prop.MODIFIABLE_PROPERTY[] mprops = Prop.MODIFIABLE_PROPERTY.values();
            for (i=0; i<mprops.length; ++i) {
                Prop.MODIFIABLE_PROPERTY mprop = mprops[i];
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
    if (property != Prop.MODIFIABLE_PROPERTY.Others) {
  %>
      <!-- ----------------------------------------------------------------- -->
      <br/><b>Select a <%=propertyNameLC%>:</b>
      <table class="modifyConceptDT">
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
      <table class="modifyConceptDT">
        <tr>
          <% suggestion = selectedProperty; %>
          <td><textarea class="textbody" name="suggestion" <%=TEXTAREA_ARGS%>><%=suggestion%></textarea></td>
          <td valign="top">
            <table class="modifyConceptDT">
              <%
              Prop.PROPERTY_ACTION[] pActions = Prop.PROPERTY_ACTION.values();
                for (i=0; i<pActions.length; ++i) {
                    Prop.PROPERTY_ACTION pAction = pActions[i];
                  String checked = pAction==action ? "checked=\"checked\" " : "";
                  String pActionName = pAction.name();
              %>
                <tr>
                  <td><input type="radio" name="action" value="<%=pActionName%>" <%=checked%>/></td>
                  <td><%=pActionName%></td>
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
      <table class="modifyConceptDT">
        <tr>
          <td><textarea class="textbody" name="description" <%=TEXTAREA_ARGS%>><%=description%></textarea></td>
        </tr>
      </table>
  <%
    }
  %>

  <!-- --------------------------------------------------------------------- -->
  <br/><b>Notes or comments (if any):</b>
  <table class="modifyConceptDT">
    <tr>
      <td><textarea class="textbody" name="notes" <%=TEXTAREA_ARGS%>><%=notes%></textarea></td>
    </tr>
  </table>
  
  <!-- --------------------------------------------------------------------- -->
  <table class="modifyConceptDT">
    <tr>
     <!-- <td><a href="<%=request.getContextPath()%>/pages/change_request.jsp">Back</a></td> -->
     <!-- <td align="right"><INPUT type="submit" name="submit" value="Submit"></td> -->
     <td align="right"><a href="<%=request.getContextPath()%>/pages/change_request.jsp">
       <img src="<%=imagePath%>/submit.gif" width="51" height="21"
         alt="Submit" border="0"/></a></td>
    </tr>
  </table>
</form>
