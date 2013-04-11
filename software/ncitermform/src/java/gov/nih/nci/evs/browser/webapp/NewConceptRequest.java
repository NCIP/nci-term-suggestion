/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.webapp;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

public class NewConceptRequest extends FormRequest {
    // List of attribute name(s):
    private static final String EMAIL = "email";
    private static final String OTHER = "other";
    private static final String VOCABULARY = "vocabulary";
    private static final String TERM = "term";
    private static final String SYNONYMS = "synonyms";
    private static final String PARENT_CODE = "parentCode";
    private static final String DEFINITION = "definition";
    private static final String REASON = "reason";

    public NewConceptRequest() {
        super(VOCABULARY);
        setParameters(new String[] { EMAIL, OTHER, VOCABULARY, 
            TERM, SYNONYMS, PARENT_CODE, DEFINITION, REASON });
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
            TERM, SYNONYMS, PARENT_CODE, DEFINITION, REASON });
        String msg = "FYI: The following request has been sent:\n";
        msg += "    * " + getSubject();
        _request.setAttribute(MESSAGE, msg);
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

    private String getSubject() {
        String term = _parametersHashMap.get(TERM);
        String value = "Suggest New Concept";
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
            new String[] { VOCABULARY, TERM, SYNONYMS, PARENT_CODE, DEFINITION });
        itemizeParameters(buffer, "Additional information:", null,
            new String[] { REASON });
        
        return buffer.toString();
    }
}
