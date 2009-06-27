package gov.nih.nci.evs.browser.utils;

import java.util.HashMap;

import javax.servlet.http.*;

public class RequestBase {
    protected final String[] EMPTY_PARAMETERS = new String[] {};
    protected final String INDENT = "    ";
    protected HttpServletRequest _request = null;
    protected String[] _parameters = EMPTY_PARAMETERS;
    protected HashMap<String, String> _parametersHashMap = null;

    public RequestBase(HttpServletRequest request) {
        _request = request;
    }

    protected HashMap<String, String> getParametersHashMap(String[] parameters) {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (int i = 0; i < parameters.length; ++i) {
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
    
    protected String debugParameters(String text) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(text);
        for (int i = 0; i < _parameters.length; ++i) {
            String parameter = _parameters[i];
            buffer.append("\n* ");
            buffer.append(parameter + ": ");
            buffer.append(_parametersHashMap.get(parameter));
        }
        return buffer.toString();
    }
    
    protected void updateSessionAttributes(String[] parameters) {
        for (int i = 0; i < parameters.length; ++i) {
            String parameter = parameters[i];
            _request.getSession().setAttribute(parameter,
                    _parametersHashMap.get(parameter));
        }
    }

    protected void updateSessionAttributes() {
        updateSessionAttributes(_parameters);
    }
    
    protected void clearSessionAttributes(String[] parameters) {
        for (int i = 0; i < parameters.length; ++i) {
            String parameter = parameters[i];
            _request.getSession().setAttribute(parameter, null);
        }
    }
    
    protected void clearSessionAttributes() {
        clearSessionAttributes(_parameters);
    }
}
