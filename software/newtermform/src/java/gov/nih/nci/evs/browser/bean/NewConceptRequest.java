package gov.nih.nci.evs.browser.bean;

import gov.nih.nci.evs.browser.properties.AppProperties;
import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class NewConceptRequest extends RequestBase {
    // List of session attribute name(s):
    private final String EMAIL = "email";
    private final String OTHER = "other";
    private final String VOCABULARY = "vocabulary";
    private final String TERM = "term";
    private final String SYNONYMS = "synonyms";
    private final String PARENT_CODE = "parentCode";
    private final String DEFINITION = "definition";
    private final String REASON = "reason";
    private final String MESSAGE = "message";
    private final String WARNINGS = "warnings";
    
    // List of return state(s):
    private final String WARNING_STATE = "warnings";
    private final String SUCCESSFUL_STATE = "successful";

    // List of member variable(s):
    private final boolean isSendEmail = AppProperties.getInstance().isSendEmail();

    public NewConceptRequest(HttpServletRequest request) {
        super(request);
        setParameters(new String[] { EMAIL, OTHER, VOCABULARY, TERM, SYNONYMS,
                PARENT_CODE, DEFINITION, REASON });
    }

    protected void setParameters(String[] parameters) {
        super.setParameters(parameters);

        // Hack: In content_new_concept.jsp page, the vocabulary comboBox
        // stores the value as the URL instead of the vocabulary name.
        // This is done so that the "Browse" button can display the
        // vocabulary's URL in a separate window kicked off by using
        // javascript displayLinkInNewWindow method. The following
        // lines manually replaces the URL with the vocabulary name.
        // Note: In order for this to work, the URL must be unique.
        String url = _parametersHashMap.get(VOCABULARY);
        String name = AppProperties.getInstance().getVocabularyName(url);
        _parametersHashMap.put(VOCABULARY, name);
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
        _request.getSession().setAttribute(WARNINGS, null);
        _request.getSession().setAttribute(MESSAGE, null);
        updateSessionAttributes();
        
        String warnings = validate();
        if (warnings.length() > 0) {
            _request.getSession().setAttribute(WARNINGS, warnings);
            return WARNING_STATE;
        }

        AppProperties appProperties = AppProperties.getInstance();
        String vocabulary = _parametersHashMap.get(VOCABULARY);
        String mailServer = appProperties.getMailSmtpServer();
        String from = _parametersHashMap.get(EMAIL);
        String[] recipients = appProperties.getVocabularyEmails(vocabulary);
        String subject = getSubject();
        String emailMsg = getEmailMesage();

        try {
            if (isSendEmail)
                MailUtils.postMail(mailServer, from, recipients, subject, emailMsg);
        } catch (Exception e) {
            _request.getSession().setAttribute(WARNINGS,
                    e.getLocalizedMessage());
            e.printStackTrace();
            return WARNING_STATE;
        }

        clearSessionAttributes(new String[] { /* EMAIL, OTHER, VOCABULARY, */
                TERM, SYNONYMS, PARENT_CODE, DEFINITION, REASON });
        String msg = "FYI: The following request has been sent:\n";
        msg += "    * " + getSubject();
        _request.getSession().setAttribute(MESSAGE, msg);
        printSendEmailWarning();
        return SUCCESSFUL_STATE;
    }
    
    private String validate() {
        StringBuffer buffer = new StringBuffer();
        String email = _parametersHashMap.get(EMAIL);
        validate(buffer, MailUtils.isValidEmailAddress(email), 
            "* Please enter a valid email address.");

        String vocabulary = _parametersHashMap.get(VOCABULARY);
        validate(buffer, vocabulary != null && vocabulary.length() > 0, 
            "* Please select a vocabulary.");

        String term = _parametersHashMap.get(TERM);
        validate(buffer, term != null && term.length() > 0,
            "* Please enter a term.");
        return buffer.toString();
    }
    
    private void validate(StringBuffer buffer, boolean validValue, String message) {
        if (validValue)
            return;
        if (buffer.length() > 0)
            buffer.append("\n");
        buffer.append(message);
    }

    private String getSubject() {
        String term = _parametersHashMap.get(TERM);
        String value = "Suggest New Concept";
        if (term.length() > 0)
            value += ": " + term;
        return value;
    }
    
    private String getEmailMesage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getSubject() + "\n\n");
        itemizeParameters(buffer, "Contact information:",
            new String[] { EMAIL, OTHER });
        itemizeParameters(buffer, "Term Information:",
            new String[] { VOCABULARY, TERM, SYNONYMS, PARENT_CODE, DEFINITION });
        itemizeParameters(buffer, "Additional information:",
            new String[] { REASON });
        
        return buffer.toString();
    }

    private void itemizeParameters(StringBuffer buffer, String header,
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

    private void printSendEmailWarning() {
        if (isSendEmail)
            return;
        String[] recipients = AppProperties.getInstance().getVocabularyEmails(
            _parametersHashMap.get(VOCABULARY));
        
        StringBuffer buffer = new StringBuffer();
        buffer.append("Warning: Email was never sent:\n");
        buffer.append("    * send.email configuration flag = " + isSendEmail + ".\n");
        buffer.append("    * This flag allows us to design and implement our web pages\n");
        buffer.append("      without having to send a bunch of bogus emails.\n");
        buffer.append("Debug:\n");
        buffer.append("    * recipient(s): " + StringUtils.toString(recipients, ", ") + "\n");
        _request.getSession().setAttribute(WARNINGS, buffer.toString());
    }
}
