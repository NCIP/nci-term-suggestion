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
  // List of parameter name(s):
  private static final String DICTIONARY = "dictionary";
  private static final String CODE = "code";

  // List of attribute name(s):
  private static final String EMAIL = SuggestionCDISCRequest.EMAIL;
  private static final String NAME = SuggestionCDISCRequest.NAME;
  private static final String PHONE_NUMBER = SuggestionCDISCRequest.PHONE_NUMBER;
  private static final String ORGANIZATION = SuggestionCDISCRequest.ORGANIZATION;
  //private static final String OTHER = SuggestionCDISCRequest.OTHER;
  private static final String VOCABULARY = SuggestionCDISCRequest.VOCABULARY;
  private static final String CDISC_REQUEST_TYPE = SuggestionCDISCRequest.CDISC_REQUEST_TYPE;
  private static final String CDISC_CODES = SuggestionCDISCRequest.CDISC_CODES;
  private static final String TERM = SuggestionCDISCRequest.TERM;
  private static final String REASON = SuggestionCDISCRequest.REASON;
  private static final String WARNINGS = SuggestionCDISCRequest.WARNINGS;

  // List of label(s):
  private static final String EMAIL_LABEL = SuggestionCDISCRequest.EMAIL_LABEL;
  private static final String NAME_LABEL = SuggestionCDISCRequest.NAME_LABEL;
  private static final String PHONE_NUMBER_LABEL = SuggestionCDISCRequest.PHONE_NUMBER_LABEL;
  private static final String ORGANIZATION_LABEL = SuggestionCDISCRequest.ORGANIZATION_LABEL;
  //private static final String OTHER_LABEL = SuggestionCDISCRequest.OTHER_LABEL;
  private static final String VOCABULARY_LABEL = SuggestionCDISCRequest.VOCABULARY_LABEL;
  private static final String CDISC_REQUEST_TYPE_LABEL = SuggestionCDISCRequest.CDISC_REQUEST_TYPE_LABEL;
  private static final String CDISC_CODES_LABEL = SuggestionCDISCRequest.CDISC_CODES_LABEL;
  private static final String TERM_LABEL = SuggestionCDISCRequest.TERM_LABEL;
  private static final String REASON_LABEL = SuggestionCDISCRequest.REASON_LABEL;

  private static final String INPUT_ARGS =
    "class=\"textbody\" onFocus=\"active=true\" onBlur=\"active=false\"";
    // " onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String LABEL_ARGS = "valign=\"top\"";
%>
<%
    // Member variable(s):
  String imagesPath = FormUtils.getImagesPath(request);
  Prop.Version version = FormUtils.getVersion(request);
  SuggestionCDISCRequest.setupTestData();

  // Attribute(s):
  String email = HTTPUtils.getSessionAttributeString(request, EMAIL);
  String name = HTTPUtils.getSessionAttributeString(request, NAME);
  String phone_number = HTTPUtils.getSessionAttributeString(request, PHONE_NUMBER);
  String organization = HTTPUtils.getSessionAttributeString(request, ORGANIZATION);
  //String other = HTTPUtils.getSessionAttributeString(request, OTHER);
  String vocabulary = HTTPUtils.getSessionAttributeString(request, VOCABULARY);
  String cdisc_request_type = HTTPUtils.getAttributeString(request, CDISC_REQUEST_TYPE);
  String cdisc_codes = HTTPUtils.getAttributeString(request, CDISC_CODES);
  String term = HTTPUtils.getAttributeString(request, TERM);
  String reason = HTTPUtils.getAttributeString(request, REASON);
  String warnings = HTTPUtils.getAttributeString(request, WARNINGS);
  boolean isWarnings = warnings.length() > 0;

  String pVocabulary = HTTPUtils.getParameter(request, VOCABULARY);
  if (pVocabulary == null || pVocabulary.length() <= 0) {
    // Note: This is how NCIt/TB and NCIm is passing in this value.  
    pVocabulary = HTTPUtils.getParameter(request, DICTIONARY);
  }

  // Member variable(s):
  int i=0;
  String[] items = null;
  String selectedItem = null;
  String css = WebUtils.isUsingIE(request) ? "_IE" : "";
%>
<f:view>
  <form method="post">
    <table class="newConceptDT">
      <!-- =================================================================== -->
      <%
          if (isWarnings) {
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
        <td <%=LABEL_ARGS%>><%=EMAIL_LABEL%>: <i class="warningMsgColor">*</i></td>
        <td colspan="2">
          <input name="<%=EMAIL%>" value="<%=email%>" alt="<%=EMAIL%>"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=NAME_LABEL%>:</td>
        <td colspan="2">
          <input name="<%=NAME%>" value="<%=name%>" alt="<%=NAME%>"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=PHONE_NUMBER_LABEL%>:</td>
        <td colspan="2">
          <input name="<%=PHONE_NUMBER%>" value="<%=phone_number%>" alt="<%=PHONE_NUMBER%>"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=ORGANIZATION_LABEL%>:</td>
        <td colspan="2">
          <input name="<%=ORGANIZATION%>" value="<%=organization%>" alt="<%=ORGANIZATION%>"
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
<%--      
      <tr>
        <td <%=LABEL_ARGS%>><%=OTHER_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=OTHER%>" class="newConceptTA4<%=css%>"><%=other%></textarea></td>
      </tr>
--%>
      <tr>
        <td></td>
        <td colspan="2" class="newConceptNotes"><b class="warningMsgColor">Privacy Notice: </b>
          For term submission purposes we request business contact information only, not personal information.
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;Your business email, and any other business contact information that you enter, will be stored in a publicly-accessible
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;web site in support of CDISC term submission tracking.  CDISC personnel may contact you.
        </td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr>
        <td class="newConceptSubheader">Term Information:</td>
        <td>Fill in the following fields as appropriate:</td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=VOCABULARY_LABEL%>:</td>
        <td>
          <select name="<%=VOCABULARY%>" id="url" class="newConceptCB2<%=css%>">
            <%
                selectedItem = vocabulary;
                ArrayList list = AppProperties.getInstance().getVocabularies(version);
                VocabInfo[] vocabs  = (VocabInfo[]) 
                  list.toArray(new VocabInfo[list.size()]);
                boolean isSelected = false;
                int n = vocabs.length;
                for (i=0; i<n; ++i) {
                  VocabInfo vocab = vocabs[i];
                  String displayName = vocab.getDisplayName();
                  String vocabName = vocab.getName();
                  String url = vocab.getUrl();
                  String args = "";
                  if (! isSelected) {
                    if (! isWarnings && vocabName.equalsIgnoreCase(pVocabulary))
                      { args += "selected=\"true\""; isSelected = true; }
                    else if (url.length() > 0 && url.equals(selectedItem))
                      { args += "selected=\"true\""; isSelected = true; }
                    else if (i >= n-1 && pVocabulary.length() > 0) // Default it to the last one.
                      { args += "selected=\"true\""; isSelected = true; }
                  }
            %>
                  <option value="<%=url%>" <%=args%>><%=displayName%></option>
            <%
                }
            %>
          </select>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=CDISC_REQUEST_TYPE_LABEL%>:</td>
        <td colspan="2">
          <select name="<%=CDISC_REQUEST_TYPE%>" class="newConceptCB2<%=css%>">
            <%
              selectedItem = cdisc_request_type;
              items = AppProperties.getInstance().getCDISCRequestTypeList();
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
        <td <%=LABEL_ARGS%>><%=CDISC_CODES_LABEL%>:</td>
        <td colspan="2">
          <select name="<%=CDISC_CODES%>" class="newConceptCB2<%=css%>">
            <%
              selectedItem = cdisc_codes;
              items = AppProperties.getInstance().getCDISCCodeList();
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
        <td colspan="2" class="newConceptNotes"><b class="warningMsgColor">Note to user: </b>
          CDASH and SDTM Terminology are the same and are contained within the SDTM codelists in the drop down list.
        </td>        
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=TERM_LABEL%>: <i class="warningMsgColor">*</i></td>
        <td colspan="2"><textarea name="<%=TERM%>" class="newConceptTA2<%=css%>"><%=term%></textarea></td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>
      <tr><td class="newConceptSubheader" colspan="2">Additional Information:</td></tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=REASON_LABEL%>:</td>
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
            action="#{userSessionBean.clearSuggestion}"
            image="#{facesContext.externalContext.requestContextPath}/images/clear.gif"
            alt="clear">
          </h:commandButton>
          <h:commandButton
            id="submit"
            value="submit"
            action="#{userSessionBean.requestSuggestion}"
            image="#{facesContext.externalContext.requestContextPath}/images/submit.gif"
            alt="submit">
          </h:commandButton>
        </td>
      </tr>
    </table>
  </form>
</f:view>
