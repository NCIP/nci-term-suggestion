<%@ page import="gov.nih.nci.evs.browser.webapp.*" %>
<script type="text/javascript" src="<%=FormUtils.getJSPath(request)%>/utils.js"></script>
<%
String URL="http://bioportal.nci.nih.gov/ncbo/html/Medical_Dictionary_for_Regulatory_Activities_Terminology_(MedDRA).html";
%>

<div class="texttitle-blue">License/Copyright Agreement</div><br/>
Please review the License/Copyright Agreement available at:     
<ul>
  <li><a href="javascript:void(0)" onclick="javascript:displayNewWindow('<%=URL%>')"><%=URL%></a>
</ul>
If and only if you agree to these terms/conditions, click the Accept link to proceed.   
Accept
