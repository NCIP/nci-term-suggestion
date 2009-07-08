<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.*" %>
<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<script type="text/javascript" src="<%=FormUtils.getJSPath(request)%>/utils.js"></script>
<%!
  // List of session attribute name(s):
  private static final String EMAIL = SuggestionRequest.EMAIL;
  private static final String OTHER = SuggestionRequest.OTHER;
  private static final String VOCABULARY = SuggestionRequest.VOCABULARY;
  private static final String TERM = SuggestionRequest.TERM;
  private static final String SYNONYMS = SuggestionRequest.SYNONYMS;
  private static final String NEAREST_CODE = SuggestionRequest.NEAREST_CODE;
  private static final String DEFINITION = SuggestionRequest.DEFINITION;
  private static final String REASON = SuggestionRequest.REASON;
  private static final String SOURCE = SuggestionRequest.SOURCE;
  private static final String CADSR = SuggestionRequest.CADSR;
  private static final String WARNINGS = SuggestionRequest.WARNINGS;

  private static final String INPUT_ARGS =
    "class=\"textbody\" onFocus=\"active=true\" onBlur=\"active=false\"";
    // " onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String LABEL_ARGS = "valign=\"top\"";
%>
<%
  // Member variable(s):
  String imagesPath = FormUtils.getImagesPath(request);
  Prop.Version version = FormUtils.getVersion(request);

  // Session Attribute(s):
  String email = HTTPUtils.getSessionAttributeString(request, EMAIL);
  String other = HTTPUtils.getSessionAttributeString(request, OTHER);
  String vocabulary = HTTPUtils.getSessionAttributeString(request, VOCABULARY);
  String term = HTTPUtils.getSessionAttributeString(request, TERM);
  String synonyms = HTTPUtils.getSessionAttributeString(request, SYNONYMS);
  String nearest_code = HTTPUtils.getSessionAttributeString(request, NEAREST_CODE);
  String definition = HTTPUtils.getSessionAttributeString(request, DEFINITION);
  String source = HTTPUtils.getSessionAttributeString(request, SOURCE);
  String cadsr = HTTPUtils.getSessionAttributeString(request, CADSR);
  String reason = HTTPUtils.getSessionAttributeString(request, REASON);
  String warnings = HTTPUtils.getSessionAttributeString(request, WARNINGS);
  
  // Member variable(s):
  int i=0;
  String[] items = null;
  String selectedItem = null;
  String css = WebUtils.isUsingIE(request) ? "_IE" : "";
  
  // The following values are used only for testing purposes:
  boolean useTestValues = false;
  if (useTestValues) {
    if (email.length() <= 0)
      email = "John.Doe@abc.com";
    if (other.length() <= 0)
      other = "Phone: 987-654-3210";
    if (vocabulary.length() <= 0)
      vocabulary = "NCI Thesaurus";
    if (term.length() <= 0)
      term = "Ultra Murine Cell Types";
    if (synonyms.length() <= 0)
      synonyms = "Cell Types; Cell; Murine Cell Types";
    if (nearest_code.length() <= 0)
    	nearest_code = "C23442";
    if (definition.length() <= 0)
      definition =
        "The smallest units of living structure capable of independent" +
        " existence, composed of a membrane-enclosed mass of protoplasm" +
        " and containing a nucleus or nucleoid. Cells are highly variable" +
        " and specialized in both structure and function, though all must" +
        " at some stage replicate proteins and nucleic acids, utilize" +
        " energy, and reproduce themselves.";
    if (source.length() <= 0)
      source = AppProperties.getInstance().getSourceList()[0];
    if (cadsr.length() <= 0)
      cadsr = AppProperties.getInstance().getCADSRTypeList()[0];
    if (reason.length() <= 0)
      reason = "New improved version of the previous type.";
  }
%>
<f:view>
  <form method="post">
    <div class="texttitle-blue">Term Suggestion:</div><br/>
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
      <%
          } else {
      %>
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
      <tr><td class="newConceptSubheader" colspan="2">Contact Information:</td></tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=EMAIL%>: <i class="warningMsgColor">*</i></td>
        <td colspan="2">
          <input name="<%=EMAIL%>" value="<%=email%>" alt="<%=EMAIL%>"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=OTHER%>:</td>
        <td colspan="2"><textarea name="<%=OTHER%>" class="newConceptTA4<%=css%>"><%=other%></textarea></td>
      </tr>
      <tr>
        <td></td>
        <td colspan="2" class="newConceptNotes"><b class="warningMsgColor">Privacy Notice: </b>
          Your email, name, phone, or other contact information will only be used
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;
          to contact you about this topic and not for any other purpose.
        </td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr>
        <td class="newConceptSubheader">Term Information:</td>
        <td>Fill in the following fields as appropriate:</td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=VOCABULARY%>: <i class="warningMsgColor">*</i></td>
        <td>
          <select name="<%=VOCABULARY%>" id="url" class="newConceptCB<%=css%>">
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
          <img src="<%=imagesPath%>/browse.gif" onclick="javascript:displayLinkInNewWindow('url')" />
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=TERM%>: <i class="warningMsgColor">*</i></td>
        <td colspan="2"><textarea name="<%=TERM%>" class="newConceptTA2<%=css%>"><%=term%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=SYNONYMS%>:</td>
        <td colspan="2"><textarea name="<%=SYNONYMS%>" class="newConceptTA2<%=css%>"><%=synonyms%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=NEAREST_CODE%>:</td>
        <td colspan="2"><textarea name="<%=NEAREST_CODE%>" class="newConceptTA2<%=css%>"><%=nearest_code%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=DEFINITION%>:</td>
        <td colspan="2"><textarea name="<%=DEFINITION%>" class="newConceptTA6<%=css%>"><%=definition%></textarea></td>
      </tr>
      
      <%
         if (version == Prop.Version.CADSR) {
      %>
          <tr>
            <td <%=LABEL_ARGS%>><%=SOURCE%>:</td>
            <td colspan="2">
              <select name="<%=SOURCE%>" class="newConceptCB2<%=css%>">
                <%
                  selectedItem = source;
                  items = AppProperties.getInstance().getSourceList();
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
            <td <%=LABEL_ARGS%>><%=CADSR%>:</td>
            <td colspan="2">
              <select name="<%=CADSR%>" class="newConceptCB2<%=css%>">
                <%
                  selectedItem = cadsr;
                  items = AppProperties.getInstance().getCADSRTypeList();
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
      <%
        }
      %>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr><td class="newConceptSubheader" colspan="2">Additional Information:</td></tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=REASON%>:</td>
        <td colspan="2"><textarea name="<%=REASON%>" class="newConceptTA6<%=css%>"><%=reason%></textarea></td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr>
        <td class="newConceptNotes"><i class="warningMsgColor">* Required</i></td>
        <td colspan="2" align="right">
          <h:commandButton
            id="clear"
            value="clear"
            action="#{userSessionBean.clearContactUs}"
            image="#{facesContext.externalContext.requestContextPath}/images/clear.gif"
            alt="clear">
          </h:commandButton>
          <h:commandButton
            id="submit"
            value="submit"
            action="#{userSessionBean.contactUs}"
            image="#{facesContext.externalContext.requestContextPath}/images/submit.gif"
            alt="submit">
          </h:commandButton>
        </td>
      </tr>
    </table>
  </form>
</f:view>
