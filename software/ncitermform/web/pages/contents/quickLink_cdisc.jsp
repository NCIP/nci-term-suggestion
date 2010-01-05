<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<%
  String imagesPath = FormUtils.getImagesPath(request);
%>
<div id="quicklinksholder">
  <ul id="quicklinks"
    onmouseover="document.quicklinksimg.src='<%=imagesPath%>/quicklinks-active.gif';"
    onmouseout="document.quicklinksimg.src='<%=imagesPath%>/quicklinks-inactive.gif';">
    <li>
      <a href="#"><img src="<%=imagesPath%>/quicklinks-inactive.gif" width="162"
        height="18" border="0" name="quicklinksimg" alt="Quick Links" />
      </a>
      <ul>
        <li><a href="http://gforge.nci.nih.gov/tracker/?func=browse&group_id=129&atid=2128" target="_blank"
          alt="Enterprise Vocabulary Services">New Term Request: Browse</a></li>
      </ul>
    </li>
  </ul>
</div>
