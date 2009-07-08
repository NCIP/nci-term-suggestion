package gov.nih.nci.evs.browser.utils;

import java.util.*;

import javax.servlet.http.*;

public class BaseRequest {
    protected final String[] EMPTY_PARAMETERS = HTTPUtils.EMPTY_PARAMETERS;
    protected final String INDENT = "    ";
    protected HttpServletRequest _request = null;
    protected String[] _parameters = EMPTY_PARAMETERS;
    protected HashMap<String, String> _parametersHashMap = null;

    public BaseRequest(HttpServletRequest request) {
        _request = request;
    }

    protected void setParameters(String[] parameters) {
        _parameters = parameters;
        _parametersHashMap = HTTPUtils.getParametersHashMap(_request, parameters);
    }
    
    protected String debugParameters(String text) {
        return HTTPUtils.debugParameters(text, _parameters, _parametersHashMap);
    }
    
    public void clear() {
        clearSessionAttributes();
        setParameters(EMPTY_PARAMETERS);
    }
    
    protected void updateSessionAttributes(String[] parameters) {
        HTTPUtils.updateSessionAttributes(_request, parameters, 
            _parametersHashMap);
    }

    protected void updateSessionAttributes() {
        updateSessionAttributes(_parameters);
    }
    
    protected void clearSessionAttributes(String[] parameters) {
        HTTPUtils.clearSessionAttributes(_request, parameters);
    }
    
    protected void clearSessionAttributes() {
        clearSessionAttributes(_parameters);
    }
}
