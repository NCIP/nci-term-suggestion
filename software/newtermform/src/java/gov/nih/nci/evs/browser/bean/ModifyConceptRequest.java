package gov.nih.nci.evs.browser.bean;

import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class ModifyConceptRequest extends RequestBase{
    private final String MESSAGE = "message";
    private final String MESSAGE_STATE = "message";
    
    public ModifyConceptRequest(HttpServletRequest request) {
        super(request);
    }
    
    public String clearForm() {
        _request.getSession().setAttribute(MESSAGE, "ModifyConceptRequest.clearForm");
        return MESSAGE_STATE;
    }
    
    public String submitForm() {
        _request.getSession().setAttribute(MESSAGE, "ModifyConceptRequest.submitForm");
        return MESSAGE_STATE;
    }
}
