<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page import="gov.nih.nci.evs.browser.properties.*" %>
<%@ page import="gov.nih.nci.evs.browser.utils.*" %>
<%!
  private static final String TELEPHONE = "301.451.4384 or Toll-Free: 888.478.4423";
  private static final String MAIL_TO = "ncicb@pop.nci.nih.gov";
  private static final String NCICB_URL = "http://ncicb.nci.nih.gov/support";
%>
<%
  String ncicb_contact_url = AppProperties.getInstance().getContactUrl();
  String subject = HTTPUtils.getParameter(request, "subject");
  String message = HTTPUtils.getParameter(request, "message");
  String emailaddress = HTTPUtils.getParameter(request, "message");
  String errorMsg = HTTPUtils.getSessionAttributeString(request, "errorMsg");
  String errorType = HTTPUtils.getSessionAttributeString(request, "errorType");
  boolean userError = errorType.equalsIgnoreCase("user");
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
      <td><a href="<%=NCICB_URL%>" target="_blank" alt="NCICB Support"><%=NCICB_URL%></a></td>
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
    <a href="http://www.cancer.gov/help" target="_blank" alt="Cancer.gov help">
      Cancer.gov help page</a>. &nbsp;
    For help and other questions concerning NCI Enterprise Vocabulary
    Services (EVS),
    see the <a href="http://evs.nci.nih.gov/" target="_blank" alt="EVS">
      EVS Web site</a>.
  </div>

  <%
    String color = ""; 
    if (userError)
      color = "style=\"color:#FF0000;\"";
  %>
  <p><b>Online Form</b></p>
  <p <%= color %>>
    To use this web form, please fill in every box below and then click on 'Submit'. 
    <%
      if (errorMsg != null && errorMsg.length() > 0) {
          errorMsg = errorMsg.replaceAll("&lt;br/&gt;", "\n");
          String[] list = StringUtils.toStrings(errorMsg, "\n", false, false);
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
      <% if (userError) %> <i style="color:#FF0000;">* Required)</i>
      <i>Subject of your email:</i>
    </p>
    <input class="textbody" size="100" name="subject" alt="Subject" value="<%= subject %>" onFocus="active = true" onBlur="active = false" onKeyPress="return ifenter(event,this.form)">
    <p>
      <% if (userError) %> <i style="color:#FF0000;">* Required)</i>
      <i>Detailed description of your problem or suggestion (no attachments):</i>
    </p>
    <TEXTAREA class="textbody" Name="message" alt="Message" rows="4" cols="98"><%= message %></TEXTAREA>
    <p>
      <% if (userError) %> <i style="color:#FF0000;">* Required)</i>
      <i>Your e-mail address:</i>
    </p>
    <input class="textbody" size="100" name="emailaddress" alt="Email Address" value="<%= emailaddress %>" onFocus="active = true" onBlur="active = false" onKeyPress="return ifenter(event,this.form)">
    <br/><br/>
    
    <h:commandButton
      id="mail"
      value="Submit"
      alt="Submit"
      action="#{userSessionBean.contactUs}" >
    </h:commandButton>
    &nbsp;&nbsp;<INPUT type="reset" value="Clear" alt="Clear">
  </form>
  <br/>
  
  <a href="http://www.cancer.gov/policies/page3" target="_blank"
      alt="National Cancer Institute Policies">
    <i>Privacy Policy on E-mail Messages Sent to the NCI Web Site</i>
  </a>
  
</f:view>
