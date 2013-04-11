/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.utils;

import java.util.*;

import javax.servlet.http.*;

public class BaseRequest {
    protected final String[] EMPTY_PARAMETERS = HTTPUtils.EMPTY_PARAMETERS;
    protected final String INDENT = "    ";
    protected HttpServletRequest _request = HTTPUtils.getRequest();
    protected String[] _parameters = EMPTY_PARAMETERS;
    protected HashMap<String, String> _parametersHashMap = null;

    protected void setParameters(String[] parameters) {
        _parameters = parameters;
        _parametersHashMap = HTTPUtils.getParametersHashMap(_request, parameters);
    }
    
    protected String debugParameters(String text) {
        return HTTPUtils.debugParameters(text, _parameters, _parametersHashMap);
    }
    
    public void clear() {
        clearAttributes();
        setParameters(EMPTY_PARAMETERS);
    }
    
    protected void updateAttributes(String[] parameters) {
        HTTPUtils.updateAttributes(_request, parameters, 
            _parametersHashMap);
    }

    protected void updateAttributes() {
        updateAttributes(_parameters);
    }
    
    protected void clearAttributes(String[] parameters) {
        HTTPUtils.clearAttributes(_request, parameters);
    }
    
    protected void clearAttributes() {
        clearAttributes(_parameters);
    }
    
    protected void updateSessionAttributes(String[] parameters) {
        HTTPUtils.updateSessionAttributes(_request, parameters);
    }
    
    protected void clearSessionAttributes(String[] parameters) {
        HTTPUtils.clearSessionAttributes(_request, parameters);
    }
}
