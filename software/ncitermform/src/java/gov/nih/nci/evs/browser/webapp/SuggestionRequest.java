package gov.nih.nci.evs.browser.webapp;

import java.util.*;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.*;

public class SuggestionRequest extends FormRequest {
    public static final HashMap<String, String> LABELS_HASHMAP = getLabelsHashMap();

    // List of parameter/attribute name(s):
    public static final String EMAIL = "email";
    public static final String OTHER = "other";
    public static final String VOCABULARY = "vocabulary";
    public static final String TERM = "term";
    public static final String SYNONYMS = "synonyms";
    public static final String NEAREST_CODE = "code";
    public static final String DEFINITION = "definition";
    public static final String REASON = "reason";
    public static final String SOURCE = "source";
    public static final String CADSR = "cadsr";
    public static final String CDISC_REQUEST_TYPE = "cdiscRequestType";
    public static final String CDISC_CODES = "cdiscCodeList";
    
    // List of field label(s):
    public static final String EMAIL_LABEL = "Email";
    public static final String OTHER_LABEL = "Other";
    public static final String VOCABULARY_LABEL = "Vocabulary";
    public static final String TERM_LABEL = "Term";
    public static final String SYNONYMS_LABEL = "Synonym(s)";
    public static final String NEAREST_CODE_LABEL = "Nearest Code/CUI";
    public static final String DEFINITION_LABEL = "Definition/Other";
    public static final String REASON_LABEL = "Reason for suggestion plus any" + 
        " other additional information";
    public static final String SOURCE_LABEL = "Source";
    public static final String CADSR_LABEL = "caDSR Type";
    public static final String CDISC_REQUEST_TYPE_LABEL = "Request Type";
    public static final String CDISC_CODES_LABEL = "CDISC Code List";

    // Parameter list(s):
    public static final String[] ALL_PARAMETERS = new String[] { 
        EMAIL, OTHER, VOCABULARY, TERM, SYNONYMS, NEAREST_CODE, 
        DEFINITION, REASON, SOURCE, CADSR, CDISC_REQUEST_TYPE, CDISC_CODES };
    public static final String[] MOST_PARAMETERS = new String[] { 
        /* EMAIL, OTHER, VOCABULARY, */ TERM, SYNONYMS, NEAREST_CODE, 
        DEFINITION, REASON, SOURCE, CADSR, CDISC_REQUEST_TYPE, CDISC_CODES };
    public static final String[] SESSION_ATTRIBUTES = new String[] {
        EMAIL, OTHER, VOCABULARY };
    
    public SuggestionRequest(HttpServletRequest request) {
        super(request, VOCABULARY);
        setParameters(ALL_PARAMETERS);
    }
    
    private static HashMap<String, String> getLabelsHashMap() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(EMAIL, EMAIL_LABEL);
        hashMap.put(OTHER, OTHER_LABEL);
        hashMap.put(VOCABULARY, VOCABULARY_LABEL);
        hashMap.put(TERM, TERM_LABEL);
        hashMap.put(SYNONYMS, SYNONYMS_LABEL);
        hashMap.put(NEAREST_CODE, NEAREST_CODE_LABEL);
        hashMap.put(DEFINITION, DEFINITION_LABEL);
        hashMap.put(REASON, REASON_LABEL);
        hashMap.put(SOURCE, SOURCE_LABEL);
        hashMap.put(CADSR, CADSR_LABEL);
        hashMap.put(CDISC_REQUEST_TYPE, CDISC_REQUEST_TYPE_LABEL);
        hashMap.put(CDISC_CODES, CDISC_CODES_LABEL);
        return hashMap;
    }
    
    public void clear() {
        super.clear();
        clearSessionAttributes(SESSION_ATTRIBUTES);
    }

    public String submitForm() {
        clearAttributes(FormRequest.ALL_PARAMETERS);
        updateAttributes();
        updateSessionAttributes(SESSION_ATTRIBUTES);
        
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

        clearAttributes(MOST_PARAMETERS);
        String msg = "FYI: The following request has been sent:\n";
        msg += "    * " + StringUtils.wrap(80, getSubject());
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
        String value = "Term Suggestion for";
        if (term.length() > 0)
            value += ": " + term;
        return value;
    }
    
    private String getEmailMessage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getSubject() + "\n\n");
        itemizeParameters(buffer, "Contact information:", LABELS_HASHMAP,
            new String[] { EMAIL, OTHER });
        Prop.Version version = Prop.Version.valueOfOrDefault(
            (String) _request.getSession().getAttribute(VERSION));
        if (version == Prop.Version.CADSR) {
            itemizeParameters(buffer, "Term Information:", LABELS_HASHMAP,
                new String[] { VOCABULARY, TERM, SYNONYMS, NEAREST_CODE, 
                    DEFINITION, SOURCE, CADSR });
        } else if (version == Prop.Version.CDISC) {
            itemizeParameters(buffer, "Term Information:", LABELS_HASHMAP,
                new String[] { VOCABULARY, TERM, SYNONYMS, NEAREST_CODE, 
                    DEFINITION, CDISC_REQUEST_TYPE, CDISC_CODES });
        } else {
            itemizeParameters(buffer, "Term Information:", LABELS_HASHMAP,
                new String[] { VOCABULARY, TERM, SYNONYMS, NEAREST_CODE, 
                    DEFINITION });
        }
        itemizeParameters(buffer, "Additional information:", LABELS_HASHMAP,
            new String[] { REASON });
        return buffer.toString();
    }
    
    protected String printSendEmailWarning() {
        if (_isSendEmail)
            return "";
        
        String warning = super.printSendEmailWarning();
        StringBuffer buffer = new StringBuffer(warning);
        buffer.append("Subject: " + getSubject() + "\n");
        buffer.append("Message:\n");
        String emailMsg = getEmailMessage();
        emailMsg = INDENT + emailMsg.replaceAll("\\\n", "\n" + INDENT);
        buffer.append(emailMsg);
        
        _request.setAttribute(WARNINGS, buffer.toString());
        return buffer.toString();
    }
}
