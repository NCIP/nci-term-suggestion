package gov.nih.nci.evs.browser.bean;

import javax.servlet.http.*;

public class ModifyConceptRequest extends NewTermRequest {
    // List of session attribute name(s):
    private static final String VOCABULARY = "vocabulary";
    
    public ModifyConceptRequest(HttpServletRequest request) {
        super(request, VOCABULARY);
    }
}
