<!-- File: header.jsp (Begin) -->
<%
  String imagePath = request.getContextPath() + "/images";
%>
<div class="ncibanner">
  <a href="http://www.cancer.gov" target="_blank"
     alt="National Cancer Institute" >
    <img src="<%=imagePath%>/logotype.gif"
      width="440" height="39" border="0"
      alt="National Cancer Institute" />
  </a>
  <a href="http://www.cancer.gov" target="_blank"
     alt="National Cancer Institute" >
    <img src="<%=imagePath%>/spacer.gif"
      width="48" height="39" border="0" 
      alt="National Cancer Institute" class="print-header" /></a>
  <a href="http://www.nih.gov" target="_blank"
      alt="U.S. National Institutes of Health">
    <img src="<%=imagePath%>/tagline_nologo.gif"
      width="173" height="39" border="0"
      alt="U.S. National Institutes of Health" />
  </a>
  <a href="http://www.cancer.gov" target="_blank"
      alt="www.cancer.gov">
    <img src="<%=imagePath%>/cancer-gov.gif"
      width="99" height="39" border="0"
      alt="www.cancer.gov" />
  </a>
</div>
<!-- File: header.jsp (End) -->
