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
        _request.getSession().setAttribute("message", 
            Utils.toHtml(formatEmailMessage()));
    	return "message";
	}
	
	private String formatEmailMessage() {
	    String indent = "    ";
	    StringBuffer buffer = new StringBuffer();
	    buffer.append("Request New Concept");
	    for (int i=0; i<PARAMETERS.length; ++i) {
	        String parameter = PARAMETERS[i];
	        buffer.append("\n" + indent + "* ");
	        buffer.append(parameter);
	        buffer.append(": ");
	        buffer.append(_parametersHM.get(parameter));
	    }
	    return buffer.toString();
	}
}
