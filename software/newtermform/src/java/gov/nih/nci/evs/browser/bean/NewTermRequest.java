package gov.nih.nci.evs.browser.bean;

import javax.servlet.http.*;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

public class NewTermRequest extends RequestBase {
    // List of session attribute name(s):
    protected final String MESSAGE = "message";
    protected final String WARNINGS = "warnings";
    
    // List of return state(s):
    protected final String SUCCESSFUL_STATE = "successful";
    protected final String MESSAGE_STATE = "message";
    protected final String WARNING_STATE = "warnings";
    
    // List of member variable(s):
    protected final boolean isSendEmail = AppProperties.getInstance().isSendEmail();
    
    public NewTermRequest(HttpServletRequest request) {
        super(request);
    }
    
    public void clear() {
        clearSessionAttributes();
        setParameters(EMPTY_PARAMETERS);
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

    protected void printSendEmailWarning(String vocabulary) {
        if (isSendEmail)
            return;
        StringBuffer buffer = new StringBuffer();
        buffer.append("Warning: Email was never sent:\n");
        buffer.append("    * send.email configuration flag = " + isSendEmail + ".\n");
        buffer.append("    * This flag allows us to design and implement our web pages\n");
        buffer.append("      without having to send a bunch of bogus emails.\n");

        if (vocabulary == null || vocabulary.length() <= 0)
            return;
        String[] recipients = AppProperties.getInstance().getVocabularyEmails(
            _parametersHashMap.get(vocabulary));
        buffer.append("Debug:\n");
        buffer.append("    * recipient(s): " + StringUtils.toString(recipients, ", ") + "\n");
        _request.getSession().setAttribute(WARNINGS, buffer.toString());
    }
}
