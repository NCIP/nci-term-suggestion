package gov.nih.nci.evs.browser.bean;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class UserSessionBean {
	private HttpServletRequest getRequest() {
		HttpServletRequest request = (HttpServletRequest) FacesContext.
        getCurrentInstance().getExternalContext().getRequest();
		return request;
	}
	
    public String changeRequest() {
        System.out.println("DYEE: UserSessionBean.changeRequest");
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute("message", "hello world");
        return "message";
    }
    
    public String requestNewConcept() {
        HttpServletRequest request = getRequest();
        NewConceptRequest newConceptRequest = new NewConceptRequest(request);
        return newConceptRequest.submit();
    }
    
}
