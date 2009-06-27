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
        getRequest().getSession().setAttribute(
                "message", "UserSessionBean.changeRequest");
        return "message";
    }
    
    public String requestNewConcept() {
        NewConceptRequest request = new NewConceptRequest(getRequest());
        return request.submitForm();
    }
    
    public String clearNewConcept() {
        NewConceptRequest request = new NewConceptRequest(getRequest());
        return request.clearForm();  
    }
}
