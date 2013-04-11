/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.bean;

import javax.servlet.http.*;
import gov.nih.nci.evs.browser.webapp.*;
import gov.nih.nci.evs.utils.*;

/**
 * 
 */

/**
 * @author EVS Team (David Yee)
 * @version 1.0
 */

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
