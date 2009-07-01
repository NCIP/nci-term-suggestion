package gov.nih.nci.evs.browser.bean;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class NewConceptRequest extends NewTermRequest {
    // List of session attribute name(s):
    private final String EMAIL = "email";
    private final String OTHER = "other";
    private final String VOCABULARY = "vocabulary";
    private final String TERM = "term";
    private final String SYNONYMS = "synonyms";
    private final String PARENT_CODE = "parentCode";
    private final String DEFINITION = "definition";
    private final String REASON = "reason";

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
        printSendEmailWarning(VOCABULARY);
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
}
