<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=windows-1252"%>
<%@ page import="java.util.*" %>
<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.utils.*" %>

<%@ page import="javax.faces.context.FacesContext" %>

<%@ page import="nl.captcha.Captcha" %>


<html>
<head>
    <META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>NCI Term Form</title>

	<script type="text/javascript" src="<%=FormUtils.getJSPath(request)%>/utils.js"></script>
	<link href="<%= request.getContextPath() %>/css/sc.css" type="text/css" rel="stylesheet" />    

</head>
<body>



<%!
  // List of parameter name(s):
  
  private static final String DICTIONARY = "dictionary";
  private static final String CODE = "code";

  // List of attribute name(s):
  private static final String EMAIL = SuggestionRequest.EMAIL;
  private static final String OTHER = SuggestionRequest.OTHER;
  private static final String VOCABULARY = SuggestionRequest.VOCABULARY;
  private static final String TERM = SuggestionRequest.TERM;
  private static final String SYNONYMS = SuggestionRequest.SYNONYMS;
  private static final String NEAREST_CODE = SuggestionRequest.NEAREST_CODE;
  private static final String DEFINITION = SuggestionRequest.DEFINITION;
  private static final String CADSR_SOURCE = SuggestionRequest.CADSR_SOURCE;
  private static final String CADSR_TYPE = SuggestionRequest.CADSR_TYPE;
  private static final String PROJECT = SuggestionRequest.PROJECT;
  private static final String REASON = SuggestionRequest.REASON;
  private static final String WARNINGS = SuggestionRequest.WARNINGS;

  // List of label(s):
  private static final String EMAIL_LABEL = SuggestionRequest.EMAIL_LABEL;
  private static final String OTHER_LABEL = SuggestionRequest.OTHER_LABEL;
  private static final String VOCABULARY_LABEL = SuggestionRequest.VOCABULARY_LABEL;
  private static final String TERM_LABEL = SuggestionRequest.TERM_LABEL;
  private static final String SYNONYMS_LABEL = SuggestionRequest.SYNONYMS_LABEL;
  private static final String NEAREST_CODE_LABEL = SuggestionRequest.NEAREST_CODE_LABEL;
  private static final String DEFINITION_LABEL = SuggestionRequest.DEFINITION_LABEL;
  private static final String CADSR_SOURCE_LABEL = SuggestionRequest.CADSR_SOURCE_LABEL;
  private static final String CADSR_TYPE_LABEL = SuggestionRequest.CADSR_TYPE_LABEL;
  private static final String PROJECT_LABEL = SuggestionRequest.PROJECT_LABEL;
  private static final String REASON_LABEL = SuggestionRequest.REASON_LABEL;

  private static final String INPUT_ARGS =
    "class=\"textbody\" onFocus=\"active=true\" onBlur=\"active=false\"";
    // " onKeyPress=\"return ifenter(event,this.form)\"";
  private static final String LABEL_ARGS = "valign=\"top\"";
%>
<%
    // Member variable(s):
  String imagesPath = FormUtils.getImagesPath(request);
  Prop.Version version = BaseRequest.getVersion(request);
  SuggestionRequest.setupTestData();

  // Attribute(s):
  String email = HTTPUtils.getJspSessionAttributeString(request, EMAIL);
  String other = HTTPUtils.getJspSessionAttributeString(request, OTHER);
  String vocabulary = HTTPUtils.getJspSessionAttributeString(request, VOCABULARY);
  String term = HTTPUtils.getJspSessionAttributeString(request, TERM);
  String synonyms = HTTPUtils.getJspSessionAttributeString(request, SYNONYMS);
  String nearest_code = HTTPUtils.getJspSessionAttributeString(request, NEAREST_CODE);
  String definition = HTTPUtils.getJspSessionAttributeString(request, DEFINITION);
  String cadsr_source = HTTPUtils.getJspSessionAttributeString(request, CADSR_SOURCE);
  String cadsr_type = HTTPUtils.getJspSessionAttributeString(request, CADSR_TYPE);
  String reason = HTTPUtils.getJspSessionAttributeString(request, REASON);
  String project = HTTPUtils.getJspSessionAttributeString(request, PROJECT);
  String warnings = HTTPUtils.getJspAttributeString(request, WARNINGS);
 
  boolean isWarnings = warnings.length() > 0;
  String message = (String) request.getSession().getAttribute("message");
  
  String retry = (String) request.getSession().getAttribute("retry");
  if (retry != null && retry.compareTo("true") == 0) {
      request.getSession().removeAttribute("retry");
  }
  

 Captcha captcha = (Captcha) request.getSession().getAttribute("captcha");
// if (captcha == null) {
  	captcha = new Captcha.Builder(200, 50)
	        .addText()
	        .addBackground()
	        //.addNoise()
		.gimp()
		//.addBorder()
                .build();
	request.getSession().setAttribute(Captcha.NAME, captcha);
// }


  String pVocabulary = HTTPUtils.getJspParameter(request, VOCABULARY);
  if (pVocabulary == null || pVocabulary.length() <= 0) {
    // Note: This is how NCIt/TB and NCIm is passing in this value.  
    pVocabulary = HTTPUtils.getJspParameter(request, DICTIONARY);
  }
  String pCode = HTTPUtils.getJspParameter(request, CODE, false);
  if (! isWarnings && pCode != null)
    nearest_code = pCode;

  // Member variable(s):
  int i=0;
  String[] items = null;
  String selectedItem = null;
  String css = WebUtils.isUsingIE(request) ? "_IE" : "";
%>
<f:view>

  <h:form id="suggestion">
    <table class="newConceptDT">
    
      <!-- =================================================================== -->
      <%
      
          boolean form_completed = true;
          if (isWarnings) {
            form_completed = false;
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
      
     
          if (message != null && message.compareTo("null") != 0) {
              form_completed = false;
              request.getSession().removeAttribute("message");
      %>    
              <tr>
                <td></td>
                <td><i class="warningMsgColor"><%=message%></i></td>
              </tr> 

              <tr>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr> 
              
<%         
             
          }

    if (!form_completed) {
        request.getSession().setAttribute("retry", "true");
              
%>
          <tr>
                <td></td>
                <td>

    
          <%
          
          String refresh = (String) request.getSession().getAttribute("refresh");
          boolean refresh_bool = false;
          if (refresh != null && refresh.compareTo("true") == 0) {
              refresh_bool = true;
          }
          request.getSession().removeAttribute("refresh");

          if (WebUtils.isUsingIE(request)) {
                  if (refresh_bool) {
          %>    
		  <h:commandButton
		    id="back"
		    value="back"
		    action="#{userSessionBean.requestSuggestion}"
		    image="/images/refresh.gif"

		    onclick="parent.history.back(); return false;"

		    alt="Refresh image">
		  </h:commandButton>  
	<%	  
		  } else {
	%>	  

		  <h:commandButton
		    id="back"
		    value="back"
		    action="#{userSessionBean.requestSuggestion}"
		    image="/images/tryagain.gif"

		    onclick="parent.history.back(); return false;"

		    alt="Try again">
		  </h:commandButton> 
	  <%	  
		  }
          } else {
              request.getSession().setAttribute("retry", "true");
              request.getSession().setAttribute(OTHER, other);
              request.getSession().setAttribute(VOCABULARY, vocabulary);
              request.getSession().setAttribute(TERM, term);
              request.getSession().setAttribute(SYNONYMS, synonyms);
              request.getSession().setAttribute(DEFINITION, definition);
              request.getSession().setAttribute(NEAREST_CODE, nearest_code);
              request.getSession().setAttribute(CADSR_SOURCE, cadsr_source);
              request.getSession().setAttribute(CADSR_TYPE, cadsr_type);
              request.getSession().setAttribute(REASON, reason);
              request.getSession().setAttribute(PROJECT, project);
              request.getSession().setAttribute(WARNINGS, warnings);
              
              request.getSession().setAttribute("cdisc", "false");
              
              if (refresh_bool) {
          %>
               <h:outputLink
                  value="/ncitermform/">
                  <h:graphicImage value="/images/refresh.gif" alt="Refresh image"
                  style="border-width:0;" />
              </h:outputLink>            
          
          <%
              } else {
          %>    
                <h:outputLink
                  value="/ncitermform/">
                  <h:graphicImage value="/images/tryagain.gif" alt="Try again"
                  style="border-width:0;" />
                </h:outputLink>                 
          <%    
              }
          }
          %>
          
          

              </td>
              </tr> 
              
     <%     
          } else {
          
 request.getSession().removeAttribute("retry");
         

      %>

      <!-- =================================================================== -->
      <tr><td class="newConceptSubheader" colspan="2">Contact Information:</td></tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=EMAIL_LABEL%>: <i class="warningMsgColor">*</i></td>
        <td colspan="2">
          <!--
          <input name="<%=EMAIL%>" value="<%=email%>" alt="<%=EMAIL%>"
          -->
          <input id="email" name="email" value="<%=email%>" alt="<%=EMAIL%>"
          
          class="newConceptTF<%=css%>" <%=INPUT_ARGS%>>
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=OTHER_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=OTHER%>" class="newConceptTA4<%=css%>"><%=other%></textarea></td>
      </tr>
      <tr>
        <td></td>
        <td colspan="2" class="newConceptNotes"><b class="warningMsgColor">Privacy Notice: </b>
          For term submission purposes we request business contact information only, not personal information.
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;Your business email, and any other business contact information that you enter, will only be used to contact you
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;about this topic if any clarifications are needed.  This information will not be disseminated to others outside the 
          <br/>&nbsp;&nbsp;&nbsp;&nbsp;EVS group of the NCI, or stored except as part of the email communications on this topic.
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
          <select name="<%=VOCABULARY%>" id="url" class="newConceptCB<%=css%>">
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
        <td align="right">
          <img src="<%=imagesPath%>/browse.gif" onclick="javascript:displayVocabLinkInNewWindow('url')" alt="browse" />
        </td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=TERM_LABEL%>: <i class="warningMsgColor">*</i></td>
        <td colspan="2"><textarea id="<%=TERM%>" name="<%=TERM%>" class="newConceptTA2<%=css%>"><%=term%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=SYNONYMS_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=SYNONYMS%>" class="newConceptTA2<%=css%>"><%=synonyms%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=NEAREST_CODE_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=NEAREST_CODE%>" class="newConceptTA2<%=css%>"><%=nearest_code%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=DEFINITION_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=DEFINITION%>" class="newConceptTA6<%=css%>"><%=definition%></textarea></td>
      </tr>

      <!-- =================================================================== -->
      <%
          if (version == Prop.Version.CADSR) {
      %>
          <tr>
            <td <%=LABEL_ARGS%>><%=CADSR_SOURCE_LABEL%>:</td>
            <td colspan="2">
              <select name="<%=CADSR_SOURCE%>" class="newConceptCB2<%=css%>">
                <%
                  selectedItem = cadsr_source;
                  items = AppProperties.getInstance().getCADSRSourceList();
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
            <td <%=LABEL_ARGS%>><%=CADSR_TYPE_LABEL%>:</td>
            <td colspan="2">
              <select name="<%=CADSR_TYPE%>" class="newConceptCB2<%=css%>">
                <%
                  selectedItem = cadsr_type;
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
        <td <%=LABEL_ARGS%>><%=PROJECT_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=PROJECT%>" class="newConceptTA2<%=css%>"><%=project%></textarea></td>
      </tr>
      <tr>
        <td <%=LABEL_ARGS%>><%=REASON_LABEL%>:</td>
        <td colspan="2"><textarea name="<%=REASON%>" class="newConceptTA6<%=css%>"><%=reason%></textarea></td>
      </tr>

      <!-- =================================================================== -->
      <tr><td><br/></td></tr>

      <tr>
      <td></td>
        <td>
             <img src="<c:url value="simpleCaptcha.png"  />" alt="simpleCaptcha.png">
             
             &nbsp;<h:commandLink value="Unable to read this image?" action="#{userSessionBean.refreshForm}" />
             <br/>
        </td>
      </tr>
      <tr>
      
      <td> 
          Enter the characters appearing in the above image: <i class="warningMsgColor">*</i> 
       </td>
       <td>   
          <input id="answer"  name="answer" value="" />&nbsp;
       </td>
      </tr>
      <tr>
      <td class="newConceptNotes"><i class="warningMsgColor">* Required</i></td>
      <td colspan="2" align="right">
          <h:commandButton
            id="clear"
            value="clear"
            action="#"
            image="/images/clear.gif"
            
            
            onclick="return clear_form()"
            
            alt="clear">
          </h:commandButton>
          
  
           <img src="<%=imagesPath%>/spacer.gif" width="1" />
 
           <h:commandButton
             id="submit"
             value="submit"
             action="#{userSessionBean.requestSuggestion}"
             image="/images/submit.gif"
             
             onclick="return check_blank()"
 
             alt="submit">
           </h:commandButton>
          
          
          
      </td>
      </tr>
      
      <%
      }
      %>
      
      
      </table>
      
<%  

if (form_completed) {
%>
      <p class="newConceptNotes">
     
For multiple terms, please consider emailing an Excel, delimited text, or similar file to <a href="mailto:ncithesaurus@mail.nih.gov">ncithesaurus@mail.nih.gov</a>,
which can also respond to any questions.
     
      </p>
<%
}
%>
   <input type="hidden" id="cdisc" name="cdisc" value="false" /> 
   
  </h:form>

</f:view>

</body>
</html>