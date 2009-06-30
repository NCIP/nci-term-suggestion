<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.*" %>
<%@ page import="gov.nih.nci.evs.browser.newterm.*" %>
<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<script type="text/javascript" src="<%= request.getContextPath() %>/js/utils.js"></script>
<%!
  private static final String INPUT_ARGS =
    "class=\"textbody\" onFocus=\"active=true\" onBlur=\"active=false\"";
    // " onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String LABEL_ARGS = "valign=\"top\"";
%>
<%
  // Session Attribute(s):
  String email = HTTPUtils.getSessionAttributeString(request, "email");
  String other = HTTPUtils.getSessionAttributeString(request, "other");
  String vocabulary = HTTPUtils.getSessionAttributeString(request, "vocabulary");
  String conceptCode = HTTPUtils.getSessionAttributeString(request, "conceptCode");
  String propertyName = HTTPUtils.getSessionAttributeString(request, "propertyName");
  String suggestion = HTTPUtils.getSessionAttributeString(request, "suggestion");
  String reason = HTTPUtils.getSessionAttributeString(request, "reason");
  String warnings = HTTPUtils.getSessionAttributeString(request, "warnings");

  // Member variable(s):
  String imagePath = request.getContextPath() + "/images";
  int i=0;
  String[] items = null;
  String selectedItem = null;
  String css = WebUtils.isUsingIE(request) ? "_IE" : "";
  
  // The following values are used only for testing purposes:
  boolean useTestValues = true;
  if (useTestValues) {
    if (email.length() <= 0)
      email = "John.Doe@abc.com";
    if (other.length() <= 0)
      other = "Phone: 987-654-3210";
    if (vocabulary.length() <= 0)
      vocabulary = "NCI Thesaurus";
    if (conceptCode.length() <= 0)
      conceptCode = "C12434";
    if (propertyName.length() <= 0)
      propertyName = "Definition";
    if (suggestion.length() <= 0)
        suggestion =
        "The smallest units of living structure capable of independent" +
        " existence, composed of a membrane-enclosed mass of protoplasm" +
        " and containing a nucleus or nucleoid. Cells are highly variable" +
        " and specialized in both structure and function, though all must" +
        " at some stage replicate proteins and nucleic acids, utilize" +
        " energy, and reproduce themselves.";
    if (reason.length() <= 0)
      reason = "New improved version of the previous type.";
  }
%>
<f:view>
  <form method="post">
    <div class="texttitle-blue">Suggest Concept Modification:</div><br/>
    <table class="newConceptDT">
      <!-- =================================================================== -->
      <%
          if (warnings.length() > 0) {
                String[] wList = StringUtils.toStrings(warnings, "\n", false, false);
                for (i=0; i<wList.length; ++i) {
          String warning = wList[i];
          warning = StringUtils.toHtml(warning); // For leading spaces (indentation)
          if (i==0) {
      %>
              <tr>
                <td <%=LABEL_ARGS%>><b class="warningMsgColor">Warning:</b></td>
                <td><i class="warningMsgColor"><%=warning%></i></td>
              </tr>
      <%    } else { %>
              <tr>
                <td <%=LABEL_ARGS%>></td>
                <td><i class="warningMsgColor"><%=warning%></i></td>
              </tr>
      <%
            }
          }
      %>
          <tr><td><br/></td></tr>
      <%
        }
      %>
      
      <!-- =================================================================== -->
      <tr><td colspan="2"><b>Contact Information:</b></td></tr>
      <tr>
        <td <%=LABEL_ARGS%>>Email: <i class="warningMsgColor">*</i></td>
        <td colspan="2">
          <input name="email" value="<%=email%>" alt="email"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>>Other:</td>
        <td colspan="2"><textarea name="other" class="newConceptTA<%=css%>"><%=other%></textarea></td>
      </tr>
      <tr>
        <td></td>
        <td colspan="2" class="newConceptNotes"><b>Privacy Notice:</b> Your contact information will only be used to contact you
            <br/>&nbsp;&nbsp;&nbsp;&nbsp;about this topic and not for any other purpose.
        </td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr><td colspan="2"><b>Term Information:</b></td></tr>
      <tr>
        <td <%=LABEL_ARGS%>>Vocabulary: <i class="warningMsgColor">*</i></td>
        <td>
          <select name="vocabulary" id="url" class="newConceptCB<%=css%>">
            <%
              selectedItem = vocabulary;
              ArrayList list = AppProperties.getInstance().getVocabularies();
              Iterator iterator = list.iterator();
              while (iterator.hasNext()) {
                VocabInfo vocab = (VocabInfo) iterator.next();
                String item = vocab.getName();
                String url = vocab.getUrl();
                String args = "";
                if (item.equals(selectedItem))
                  args += "selected=\"true\"";
            %>
                <option value="<%=url%>" <%=args%>><%=item%></option>
            <%
              }
            %>
          </select>
        </td>
        <td align="right">
          <img src="<%=imagePath%>/browse.gif" onclick="javascript:displayLinkInNewWindow('url')" />
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>>Concept Code: <i class="warningMsgColor">*</i></td>
        <td colspan="2"><input name="conceptCode" value="<%=conceptCode%>" alt="conceptCode"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>>Property Name: <i class="warningMsgColor">*</i></td>
        <td colspan="2"><input name="propertyName" value="<%=propertyName%>" alt="propertyName"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>>Suggestion: <i class="warningMsgColor">*</i></td>
        <td colspan="2"><textarea name="suggestion" class="newConceptTA<%=css%>"><%=suggestion%></textarea></td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr><td colspan="2"><b>Additional Information:</b></td></tr>
      <tr>
        <td <%=LABEL_ARGS%>>Reason for modification plus any other additional information:</td>
        <td colspan="2"><textarea name="reason" class="newConceptTA<%=css%>"><%=reason%></textarea></td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr>
        <td class="newConceptNotes"><i class="warningMsgColor">* Required</i></td>
        <td colspan="2" align="right">
          <h:commandButton
            id="clear"
            value="clear"
            action="#{userSessionBean.clearNewConcept}"
            image="#{facesContext.externalContext.requestContextPath}/images/clear.gif"
            alt="clear">
          </h:commandButton>
          <h:commandButton
            id="submit"
            value="submit"
            action="#{userSessionBean.requestNewConcept}"
            image="#{facesContext.externalContext.requestContextPath}/images/submit.gif"
            alt="submit">
          </h:commandButton>
        </td>
      </tr>
    </table>
  </form>
</f:view>
