<%@ page contentType="text/html" %>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<html>
  <body>
    <form>
      Submit:
<%--      
      <INPUT type="submit" name="submit" value="Submit">
--%>
      <h:commandButton
        id="submit"
        value="submit"
        action="#{userSessionBean.changeRequest}"
        image="#{facesContext.externalContext.requestContextPath}/images/search.gif"
        alt="submit">
      </h:commandButton>
    </form>
  </body>
</html>
