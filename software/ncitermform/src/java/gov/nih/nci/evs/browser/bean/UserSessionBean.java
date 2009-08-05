package gov.nih.nci.evs.browser.bean;

import gov.nih.nci.evs.browser.utils.*;
import gov.nih.nci.evs.browser.webapp.*;

public class UserSessionBean {
    public String changeRequest() {
        HTTPUtils.getRequest().setAttribute(
                FormRequest.MESSAGE, "UserSessionBean.changeRequest");
        return FormRequest.MESSAGE_STATE;
    }
    
    public String requestNewConcept() {
        NewConceptRequest request = new NewConceptRequest();
        return request.submitForm();
    }
    
    public String clearNewConcept() {
        NewConceptRequest request = new NewConceptRequest();
        return request.clearForm();  
    }

    public String requestModifyConcept() {
        ModifyConceptRequest request = new ModifyConceptRequest();
        return request.submitForm();
    }
    
    public String clearModifyConcept() {
        ModifyConceptRequest request = new ModifyConceptRequest();
        return request.clearForm();  
    }
    
    public String requestSuggestion() {
        SuggestionRequest request = new SuggestionRequest();
        return request.submitForm();
    }
    
    public String clearSuggestion() {
        SuggestionRequest request = new SuggestionRequest();
        return request.clearForm();  
    }

    public String contactUs() {
        ContactUsRequest request = new ContactUsRequest();
        return request.submitForm();
    }
    
    public String clearContactUs() {
        ContactUsRequest request = new ContactUsRequest();
        return request.clearForm();  
    }
}
