package gov.nih.nci.evs.browser.utils;

import java.util.HashMap;

import javax.servlet.http.*;

public class RequestBase {
    protected HttpServletRequest _request = null;
    protected String[] _parameters = new String[] { };
    protected HashMap<String, String> _parametersHashMap  = null;
    
    public RequestBase(HttpServletRequest request) {
        _request = request;
    }
    
    protected HashMap<String, String> getParametersHashMap(String[] parameters) {
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
    
    protected void setParameters(String[] parameters) {
        _parameters = parameters;
        _parametersHashMap = getParametersHashMap(parameters);   
    }
}
