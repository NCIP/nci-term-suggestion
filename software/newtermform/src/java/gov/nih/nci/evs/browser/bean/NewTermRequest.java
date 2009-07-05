package gov.nih.nci.evs.browser.bean;

import javax.servlet.http.*;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

public class NewTermRequest extends BaseRequest {
    // List of session attribute name(s):
    public static final String MESSAGE = "message";
    public static final String WARNINGS = "warnings";
    
    // List of return state(s):
    protected static final String SUCCESSFUL_STATE = "successful";
    protected static final String MESSAGE_STATE = "message";
    protected static final String WARNING_STATE = "warning";
    
    // List of member variable(s):
    protected String _vocabularyParameter = null;
    protected boolean _isSendEmail = AppProperties.getInstance().isSendEmail();
    
    public NewTermRequest(HttpServletRequest request, String vocabularyParameter) {
        super(request);
        _vocabularyParameter = vocabularyParameter;
    }
    
    public void clear() {
        super.clear();
        _request.getSession().setAttribute(WARNINGS, null);
        _request.getSession().setAttribute(MESSAGE, null);
    }
    
    public String clearForm() {
        clear();
        return WARNING_STATE;
    }
    
    public String submitForm() {
        _request.getSession().setAttribute(MESSAGE, "NewTermRequest.submitForm");
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
        String name = AppProperties.getInstance().getVocabularyName(url);
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
        String[] parameters) {
        buffer.append(header + "\n");
        for (int i = 0; i < parameters.length; ++i) {
            String parameter = parameters[i];
            buffer.append("* " + parameter + ": ");
            buffer.append(_parametersHashMap.get(parameter));
            buffer.append("\n");
        }
        buffer.append("\n");
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
            return "";
        String[] recipients = AppProperties.getInstance().getVocabularyEmails(
            _parametersHashMap.get(_vocabularyParameter));
        buffer.append("Debug:\n");
        buffer.append("    * recipient(s): " + StringUtils.toString(recipients, ", ") + "\n");
        _request.getSession().setAttribute(WARNINGS, buffer.toString());
        return buffer.toString();
    }
}
