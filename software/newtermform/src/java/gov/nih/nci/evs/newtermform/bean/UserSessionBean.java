package gov.nih.nci.evs.newtermform.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class UserSessionBean {
    public String changeRequest() {
        System.out.println("DYEE: UserSessionBean.changeRequest");
        HttpServletRequest request = (HttpServletRequest) FacesContext.
            getCurrentInstance().getExternalContext().getRequest();
        request.getSession().setAttribute("message", "hello world");
        return "message";
    }
}
