package gov.nih.nci.evs.browser.bean;

import java.util.*;

import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class NewConceptRequest {
	private HttpServletRequest _request = null;
	
	private final String EMAIL = "email";
	private final String OTHER = "other";
	private final String VOCABULARY = "vocabulary";
	private final String TERM = "term";
	private final String SYNONYMS = "synonyms";
	private final String PARENT_CODE = "parentCode";
	private final String DEFINITION = "definition";
	private final String REASON = "reason";
	
	private String[] PARAMETERS = new String[] { EMAIL, OTHER, VOCABULARY,
	        TERM, SYNONYMS, PARENT_CODE, DEFINITION, REASON };
	private HashMap<String, String> _parametersHM  = null;
	
	public NewConceptRequest(HttpServletRequest request) {
		_request = request;
		_parametersHM = getParametersHashMap(PARAMETERS);
	}
	
	private HashMap<String, String> getParametersHashMap(String[] parameters) {
	    HashMap<String, String> hashMap = new HashMap<String, String>();
	    for (int i=0; i<parameters.length; ++i) {
	        String key = parameters[i];
	        String value = (String) _request.getParameter(key);
	        if (value == null)
	            value = "[Not Set]";
	        hashMap.put(key, value);
	    }
	    return hashMap;
	}

	public String submit() {
        String email = (String) _request.getParameter("email");
        String other = (String) _request.getParameter("other");
        String vocabulary = (String) _request.getParameter("vocabulary");
        String term = (String) _request.getParameter("term");
        String synonyms = (String) _request.getParameter("synonyms");
        String parentCode = (String) _request.getParameter("parentCode");
        String definition = (String) _request.getParameter("definition");
        String reason = (String) _request.getParameter("reason");
        _request.getSession().setAttribute("message", 
            Utils.toHtml("Request New Concept " 
        		+ "\n  * email: " + email
        		+ "\n  * other: " + other
        		+ "\n  * vocabulary: " + vocabulary
        		+ "\n  * term: " + term
                + "\n  * synonyms: " + synonyms
                + "\n  * parentCode: " + parentCode
                + "\n  * definition: " + definition
                + "\n  * reason: " + reason
            )
        );
    	return "message";
	}
	
	private void formatEmailMessage() {
	    
	}
}
