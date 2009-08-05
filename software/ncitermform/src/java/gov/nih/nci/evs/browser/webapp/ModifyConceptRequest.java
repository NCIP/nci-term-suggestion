package gov.nih.nci.evs.browser.webapp;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class ModifyConceptRequest extends FormRequest {
    // List of attribute name(s):
    private static final String EMAIL = "email";
    private static final String OTHER = "other";
    private static final String VOCABULARY = "vocabulary";
    private static final String CONCEPT_CODE = "conceptCode";
    private static final String CONCEPT_NAME = "conceptName";
    private static final String SUGGESTIONS = "suggestions";
    private static final String REASON = "reason";

    public ModifyConceptRequest(HttpServletRequest request) {
        super(request, VOCABULARY);
        setParameters(new String[] { EMAIL, OTHER, VOCABULARY, 
            CONCEPT_CODE, CONCEPT_NAME, SUGGESTIONS, REASON });
    }

    public String submitForm() {
        _request.setAttribute(WARNINGS, null);
        _request.setAttribute(MESSAGE, null);
        updateAttributes();
        
        String warnings = validate();
        if (warnings.length() > 0) {
            _request.setAttribute(WARNINGS, warnings);
            return WARNING_STATE;
        }

        AppProperties appProperties = AppProperties.getInstance();
        String vocabulary = _parametersHashMap.get(VOCABULARY);
        String mailServer = appProperties.getMailSmtpServer();
        String from = _parametersHashMap.get(EMAIL);
        String[] recipients = appProperties.getVocabularyEmails(vocabulary);
        String subject = getSubject();
        String emailMsg = getEmailMessage();

        try {
            if (_isSendEmail)
                MailUtils.postMail(mailServer, from, recipients, subject, emailMsg);
        } catch (Exception e) {
            _request.setAttribute(WARNINGS,
                    e.getLocalizedMessage());
            e.printStackTrace();
            return WARNING_STATE;
        }

        clearAttributes(new String[] { /* EMAIL, OTHER, VOCABULARY, */
            CONCEPT_CODE, CONCEPT_NAME, SUGGESTIONS, REASON });
        String msg = "FYI: The following request has been sent:\n";
        msg += "    * " + getSubject();
        _request.setAttribute(MESSAGE, msg);
        printSendEmailWarning();
        return SUCCESSFUL_STATE;
    }
    
    private String validate() {
        StringBuffer buffer = new StringBuffer();
        String value = _parametersHashMap.get(EMAIL);
        validate(buffer, MailUtils.isValidEmailAddress(value), 
            "* Please enter a valid email address.");

        value = _parametersHashMap.get(VOCABULARY);
        validate(buffer, value != null && value.length() > 0, 
            "* Please select a vocabulary.");

        value = _parametersHashMap.get(CONCEPT_CODE);
        validate(buffer, value != null && value.length() > 0,
            "* Please enter a concept code.");

        value = _parametersHashMap.get(SUGGESTIONS);
        validate(buffer, value != null && value.length() > 0,
            "* Please enter a suggestion.");
        return buffer.toString();
    }

    private String getSubject() {
        String term = _parametersHashMap.get(CONCEPT_CODE);
        String value = "Suggest Concept Modification";
        if (term.length() > 0)
            value += ": " + term;
        return value;
    }
    
    private String getEmailMessage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getSubject() + "\n\n");
        itemizeParameters(buffer, "Contact information:", null,
            new String[] { EMAIL, OTHER });
        itemizeParameters(buffer, "Term Information:", null,
            new String[] { VOCABULARY, CONCEPT_CODE, CONCEPT_NAME, SUGGESTIONS });
        itemizeParameters(buffer, "Additional information:", null,
            new String[] { REASON });
        
        return buffer.toString();
    }
}
