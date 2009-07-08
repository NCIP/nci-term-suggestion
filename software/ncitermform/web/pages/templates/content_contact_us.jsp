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


</f:view>
