/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.webapp;

import java.util.*;
import javax.servlet.http.*;
import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.utils.*;

/**
 * 
 */

/**
 * @author EVS Team (David Yee)
 * @version 1.0
 */

public class FormRequest extends BaseRequest implements IFormRequest {
    // List of attribute name(s):
    public static final String VERSION = "version";
    public static final String MESSAGE = "message";
    public static final String WARNINGS = "warnings";

    // Parameter list(s):
    public static final String[] ALL_PARAMETERS = 
        new String[] { MESSAGE, WARNINGS };
    
    // List of return state(s):
    public static final String SUCCESSFUL_STATE = "successful";
    public static final String MESSAGE_STATE = "message";
    public static final String WARNING_STATE = "warning";
    
    // List of member variable(s):
    protected String _vocabularyParameter = null;
    protected boolean _isSendEmail = AppProperties.getInstance().isSendEmail();
    
    public FormRequest() {
        super();
    }
    
    public FormRequest(String vocabularyParameter) {
        _vocabularyParameter = vocabularyParameter;
    }
    
    public void clear() {
        super.clear();
        clearAttributes(ALL_PARAMETERS);
    }
    
    public String clearForm() {
        clear();
        return WARNING_STATE;
    }
    
    public String submitForm() {
        _request.setAttribute(MESSAGE, "NewTermRequest.submitForm");
        return MESSAGE_STATE;
    }
    
    protected void setParameters(String[] parameters) {
        super.setParameters(parameters);

        if (_vocabularyParameter == null)
            return;
        // Hack: In content_new_concept.jsp page, the vocabulary comboBox
        // stores the value as the URL instead of the vocabulary name.
        // This is done so that the "Browse" button can display the
        // vocabulary's URL in a separate window kicked off by using
        // javascript displayLinkInNewWindow method. The following
        // lines manually replaces the URL with the vocabulary name.
        // Note: In order for this to work, the URL must be unique.
        String url = _parametersHashMap.get(_vocabularyParameter);
        if (url == null)
            return;
        
        HttpServletRequest request = HTTPUtils.getRequest();
        Prop.Version version = (Prop.Version) 
            request.getSession().getAttribute(FormRequest.VERSION);

        String name = AppProperties.getInstance().getVocabularyName(version, url);
        _parametersHashMap.put(_vocabularyParameter, name);
    }
    
    protected void validate(StringBuffer buffer, boolean validValue, String message) {
        if (validValue)
            return;
        if (buffer.length() > 0)
            buffer.append("\n");
        buffer.append(message);
    }

    protected void itemizeParameters(StringBuffer buffer, String header,
        HashMap<String, String> labels, String[] parameters) {
        buffer.append(header + "\n");
        for (int i = 0; i < parameters.length; ++i) {
            String parameter = parameters[i];
            String label = labels != null ? labels.get(parameter) : parameter;
            buffer_append(buffer, label, parameter);
        }
        buffer.append("\n");
    }

    protected void buffer_append(StringBuffer buffer, String label, 
        String parameter) {
        String value = _parametersHashMap.get(parameter);
        buffer.append("* " + label + ": " + value + "\n");
    }
    
    protected String printSendEmailWarning() {
        if (_isSendEmail)
            return "";
        StringBuffer buffer = new StringBuffer();
        buffer.append("Warning: Email was never sent:\n");
        buffer.append("    * send.email configuration flag = " + _isSendEmail + ".\n");
        buffer.append("    * This flag allows us to design and implement our web pages\n");
        buffer.append("      without having to send a bunch of bogus emails.\n");

        if (_vocabularyParameter == null || _vocabularyParameter.length() <= 0)
            return buffer.toString();
        String recipients = getRecipients();
        buffer.append("Debug:\n");
        buffer.append("    * recipient(s): " + recipients + "\n");
        _request.setAttribute(WARNINGS, buffer.toString());
        return buffer.toString();
    }
    
    protected String getRecipients() {
        return "";
    }
}
