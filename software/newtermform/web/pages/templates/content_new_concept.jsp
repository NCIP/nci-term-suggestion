<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@ page contentType="text/html;charset=windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
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
  String imagePath = request.getContextPath() + "/images";
  String email = "John.Doe@abc.com";
  String other = "Phone: 987-654-3210";

  String vocabulary = "NCI Thesaurus";
  String term = "Ultra Murine Cell Types";
  String synonyms = "";
  String parentCode = "C23442";
  String definition =
      "The smallest units of living structure capable of independent" +
      " existence, composed of a membrane-enclosed mass of protoplasm" +
      " and containing a nucleus or nucleoid. Cells are highly variable" +
      " and specialized in both structure and function, though all must" +
      " at some stage replicate proteins and nucleic acids, utilize" +
      " energy, and reproduce themselves.";
  String reason = "New improved version of the previous type.";
  int i=0;
  String[] items = null;
  String selectedItem = null;
  String css = WebUtils.isUsingIE(request) ? "_IE" : "";
%>
<html>
  <body>
    <f:view>
    <form method="post">
      <div class="texttitle-blue">Suggest New Concept:</div><br/>
      <table class="newConceptDT">
        <!-- =================================================================== -->
        <tr><td colspan="2"><b>Contact Information:</b></td></tr>
        <tr>
          <td <%=LABEL_ARGS%>>Email: <i class="red">*</i></td>
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
          <td <%=LABEL_ARGS%>>Vocabulary: <i class="red">*</i></td>
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
          <td <%=LABEL_ARGS%>>Term: <i class="red">*</i></td>
          <td colspan="2"><input name="term" value="<%=term%>" alt="term"
            class="newConceptTF<%=css%>" <%=INPUT_ARGS%>></td>
        </tr>
        <tr>
          <td <%=LABEL_ARGS%>>Synonyms:</td>
          <td colspan="2"><input name="synonyms" value="<%=synonyms%>" alt="synonyms"
            class="newConceptTF<%=css%>" <%=INPUT_ARGS%>></td>
        </tr>
        <tr>
          <td <%=LABEL_ARGS%>>Parent Concept Code:</td>
          <td colspan="2"><input name="parentCode" value="<%=parentCode%>" alt="parentCode"
            class="newConceptTF<%=css%>" <%=INPUT_ARGS%>></td>
        </tr>
        <tr>
          <td <%=LABEL_ARGS%>>Definition:</td>
          <td colspan="2"><textarea name="definition" class="newConceptTA<%=css%>"><%=definition%></textarea></td>
        </tr>

        <!-- =================================================================== -->
        <tr><td><br/></td></tr>
        <tr><td colspan="2"><b>Additional Information:</b></td></tr>
        <tr>
          <td <%=LABEL_ARGS%>>Reason for adding plus any other additional information:</td>
          <td colspan="2"><textarea name="reason" class="newConceptTA<%=css%>"><%=reason%></textarea></td>
        </tr>

        <!-- =================================================================== -->
        <tr><td><br/></td></tr>
        <tr>
          <td class="newConceptNotes"><i class="red">* Required</i></td>
          <td colspan="2" align="right">
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
  </body>
</html>