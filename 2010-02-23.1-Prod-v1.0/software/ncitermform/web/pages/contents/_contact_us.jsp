<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%!
  private static final String TELEPHONE = "301.451.4384 or Toll-Free: 888.478.4423";
  private static final String MAIL_TO = "ncicb@pop.nci.nih.gov";
  private static final String NCICB_URL = "http://ncicb.nci.nih.gov/support";
  
  // List of attribute name(s):
  private static final String SUBJECT = ContactUsRequest.SUBJECT;
  private static final String EMAIL_MSG = ContactUsRequest.EMAIL_MSG;
  private static final String EMAIL_ADDRESS = ContactUsRequest.EMAIL_ADDRESS;
  private static final String WARNINGS = ContactUsRequest.WARNINGS;
  private static final String WARNING_TYPE = ContactUsRequest.WARNING_TYPE;
%>
<%
  String ncicb_contact_url = AppProperties.getInstance().getContactUrl();
  String subject = HTTPUtils.getAttributeString(request, SUBJECT);
  String email_msg = HTTPUtils.getAttributeString(request, EMAIL_MSG);
  String email_address = HTTPUtils.getSessionAttributeString(request, EMAIL_ADDRESS);
  String warnings = HTTPUtils.getAttributeString(request, WARNINGS);
  String warningType = HTTPUtils.getAttributeString(request, WARNING_TYPE);
  boolean isUserError = Prop.WarningType.valueOfOrDefault(warningType) == Prop.WarningType.User;
%>
<f:view>
  <div class="texttitle-blue">Contact Us</div>
  <hr></hr>

  <div>
    <b>You can request help or make suggestions by filling out the
      online form below, or by using any one of these contact points:
    </b>
  </div>
  <br/>

  <table class="textbody">
    <tr>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
      <td>Telephone:</td>
      <td><%=TELEPHONE%></td>
    </tr>
    <tr>
      <td/>
      <td>Email:</td>
      <td><a href="mailto:<%=MAIL_TO%>"><%=MAIL_TO%></a></td>
    </tr>
    <tr>
      <td/>
      <td>Web Page:</td>
      <td><a href="<%=NCICB_URL%>" target="_blank"><%=NCICB_URL%></a></td>
    </tr>
  </table>
  <br/>

  <div>
    Telephone support is available Monday to Friday, 8 am - 8 pm 
    Eastern Time, excluding government holidays. You may leave a 
    message, send an email, or submit a support request via the Web
    at any time.  Please include: 
    <ul>
      <li>Your contact information;</li>
      <li>Reference to the Term Suggestions Application; and</li>
      <li>A detailed description of your problem or suggestion.</li>
    </ul>

    For questions related to NCI's Cancer.gov Web site,
    see the
    <a href="http://www.cancer.gov/help" target="_blank">
      Cancer.gov help page</a>. &nbsp;
    For help and other questions concerning NCI Enterprise Vocabulary
    Services (EVS),
    see the <a href="http://evs.nci.nih.gov/" target="_blank">
      EVS Web site</a>.
  </div>

  <%
    String color = ""; 
    if (isUserError)
      color = "style=\"color:#FF0000;\"";
  %>
  <p><b>Online Form</b></p>
  <p <%= color %>>
    To use this web form, please fill in every box below and then click on 'Submit'. 
    <%
      if (warnings != null && warnings.length() > 0) {
          warnings = warnings.replaceAll("&lt;br/&gt;", "\n");
          String[] list = StringUtils.toStrings(warnings, "\n", false, false);
          for (int i=0; i<list.length; ++i) {
            String text = list[i];
            text = StringUtils.toHtml(text); // For leading spaces (indentation)
    %>
            <br/><i style="color:#FF0000;"><%= text %></i>
    <%
          }
      }
    %>
  </p>
  
  <form method="post">
    <p>
      <% if (isUserError) %> <i style="color:#FF0000;">* Required)</i>
      <i>Subject of your email:</i>
    </p>
    <input class="textbody" size="100" name="subject" alt="Subject" value="<%= subject %>" onFocus="active = true" onBlur="active = false" onKeyPress="return ifenter(event,this.form)">
    <p>
      <% if (isUserError) %> <i style="color:#FF0000;">* Required)</i>
      <i>Detailed description of your problem or suggestion (no attachments):</i>
    </p>
    <TEXTAREA class="textbody" Name="<%= EMAIL_MSG %>" rows="4" cols="98"><%= email_msg %></TEXTAREA>
    <p>
      <% if (isUserError) %> <i style="color:#FF0000;">* Required)</i>
      <i>Your e-mail address:</i>
    </p>
    <input class="textbody" size="100" name="<%= EMAIL_ADDRESS %>" alt="<%= EMAIL_ADDRESS %>" value="<%= email_address %>" onFocus="active = true" onBlur="active = false" onKeyPress="return ifenter(event,this.form)">
    <br/><br/>
    
    <h:commandButton
      id="clear"
      value="clear"
      image="#{facesContext.externalContext.requestContextPath}/images/clear.gif"
      action="#{userSessionBean.clearContactUs}"
      alt="clear">
    </h:commandButton>
    <h:commandButton
      id="mail"
      value="submit"
      image="#{facesContext.externalContext.requestContextPath}/images/submit.gif"
      action="#{userSessionBean.contactUs}"
      alt="submit" >
    </h:commandButton>
  </form>
  <br/>
  
  <a href="http://www.cancer.gov/policies/page3" target="_blank">
    <i>Privacy Policy on E-mail Messages Sent to the NCI Web Site</i>
  </a>
  
</f:view>
