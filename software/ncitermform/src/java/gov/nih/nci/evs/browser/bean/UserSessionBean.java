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
    
    public String requestSuggestion() {
        Prop.Version version = getVersion();
        if (version == Prop.Version.CDISC) {
            SuggestionCDISCRequest request = new SuggestionCDISCRequest();
            return request.submitForm();
        }
        SuggestionRequest request = new SuggestionRequest();
        return request.submitForm();
    }
    
    public String clearSuggestion() {
        Prop.Version version = getVersion();
        if (version == Prop.Version.CDISC) {
            SuggestionCDISCRequest request = new SuggestionCDISCRequest();
            return request.clearForm();  
        }
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
