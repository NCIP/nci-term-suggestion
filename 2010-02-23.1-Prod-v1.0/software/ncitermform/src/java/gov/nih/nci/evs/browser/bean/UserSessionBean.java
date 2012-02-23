package gov.nih.nci.evs.browser.bean;

import javax.servlet.http.*;

import gov.nih.nci.evs.browser.utils.*;
import gov.nih.nci.evs.browser.webapp.*;

public class UserSessionBean {
    public String changeRequest() {
        HTTPUtils.getRequest().setAttribute(
            FormRequest.MESSAGE, "UserSessionBean.changeRequest");
        return FormRequest.MESSAGE_STATE;
    }
    
    private Prop.Version getVersion() {
        HttpServletRequest request = HTTPUtils.getRequest();
        Prop.Version version = (Prop.Version) 
            request.getSession().getAttribute(FormRequest.VERSION);
        return version;
    }
    
    private IFormRequest getFormRequest() {
        Prop.Version version = getVersion();
        if (version == Prop.Version.CDISC)
            return new SuggestionCDISCRequest();
        return new SuggestionRequest();
    }
    
    public String requestSuggestion() {
        IFormRequest request = getFormRequest();
        return request.submitForm();
    }
    
    public String clearSuggestion() {
        IFormRequest request = getFormRequest();
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
