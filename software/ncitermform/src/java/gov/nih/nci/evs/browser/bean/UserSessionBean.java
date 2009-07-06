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

    public String requestModifyConcept() {
        ModifyConceptRequest request = new ModifyConceptRequest(getRequest());
        return request.submitForm();
    }
    
    public String clearModifyConcept() {
        ModifyConceptRequest request = new ModifyConceptRequest(getRequest());
        return request.clearForm();  
    }
    
    public String requestSuggestion() {
        SuggestionRequest request = new SuggestionRequest(getRequest());
        return request.submitForm();
    }
    
    public String clearSuggestion() {
        SuggestionRequest request = new SuggestionRequest(getRequest());
        return request.clearForm();  
    }
}
