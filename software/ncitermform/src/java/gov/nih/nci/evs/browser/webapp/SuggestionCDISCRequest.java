package gov.nih.nci.evs.browser.webapp;

import java.util.*;

import javax.servlet.http.*;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

public class SuggestionCDISCRequest extends FormRequest {
    public static final HashMap<String, String> LABELS_HASHMAP = getLabelsHashMap();

    // List of parameter/attribute name(s):
    public static final String EMAIL = "email";
    public static final String NAME = "name";
    public static final String PHONE_NUMBER = "phone";
    public static final String ORGANIZATION = "organization";
    //public static final String OTHER = "other";
    public static final String VOCABULARY = "vocabulary";
    public static final String CDISC_REQUEST_TYPE = "cdiscRequestType";
    public static final String CDISC_CODES = "cdiscCodeList";
    public static final String TERM = "term";
    public static final String REASON = "reason";
    
    // List of field label(s):
    public static final String EMAIL_LABEL = "Business Email";
    public static final String NAME_LABEL = "Name";
    public static final String PHONE_NUMBER_LABEL = "Business Phone Number";
    public static final String ORGANIZATION_LABEL = "Organization";
    // public static final String OTHER_LABEL = "Other";
    public static final String VOCABULARY_LABEL = "Vocabulary";
    public static final String CDISC_REQUEST_TYPE_LABEL = "Request Type";
    public static final String CDISC_CODES_LABEL = "CDISC Code List";
    public static final String TERM_LABEL = "Enter Term or Codelist Request Information";
    public static final String REASON_LABEL = "Reason for suggestion plus any" + 
        " other additional information";

    // Parameter list(s):
    public static final String[] ALL_PARAMETERS = new String[] { 
        EMAIL, NAME, PHONE_NUMBER, ORGANIZATION, /* OTHER, */ VOCABULARY, 
        CDISC_REQUEST_TYPE, CDISC_CODES, TERM, REASON };
    public static final String[] MOST_PARAMETERS = new String[] { 
        /* EMAIL, OTHER, VOCABULARY, */ 
        CDISC_REQUEST_TYPE, CDISC_CODES, TERM, REASON, };
    public static final String[] SESSION_ATTRIBUTES = new String[] {
        EMAIL, NAME, PHONE_NUMBER, ORGANIZATION, /* OTHER, */ VOCABULARY };
    
    public SuggestionCDISCRequest() {
        super(VOCABULARY);
        setParameters(ALL_PARAMETERS);
    }
    
    private static HashMap<String, String> getLabelsHashMap() {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(EMAIL, EMAIL_LABEL);
        hashMap.put(NAME, NAME_LABEL);
        hashMap.put(PHONE_NUMBER, PHONE_NUMBER_LABEL);
        hashMap.put(ORGANIZATION, ORGANIZATION_LABEL);
        // hashMap.put(OTHER, OTHER_LABEL);
        hashMap.put(VOCABULARY, VOCABULARY_LABEL);
        hashMap.put(CDISC_REQUEST_TYPE, CDISC_REQUEST_TYPE_LABEL);
        hashMap.put(CDISC_CODES, CDISC_CODES_LABEL);
        hashMap.put(TERM, TERM_LABEL);
        hashMap.put(REASON, REASON_LABEL);
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
        String mailServer = appProperties.getMailSmtpServer();
        String from = _parametersHashMap.get(EMAIL);
        String recipients = getRecipients();
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
    
    protected String getRecipients() {
        AppProperties appProperties = AppProperties.getInstance();
        String vocabulary = _parametersHashMap.get(VOCABULARY);
        Prop.Version version = (Prop.Version) 
            _request.getSession().getAttribute(VERSION);
        return appProperties.getVocabularyEmailsString(version, vocabulary);
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
        String vocabulary = _parametersHashMap.get(VOCABULARY);
        String value = "Term Suggestion for";
        if (term.length() > 0)
            value += " " + vocabulary + ": " + term;
        return value;
    }
    
    private String getEmailMessage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getSubject() + "\n\n");
        buffer.append("Contact information:\n");
        buffer_append(buffer, EMAIL_LABEL, EMAIL);
        buffer_append(buffer, NAME_LABEL, NAME);
        buffer_append(buffer, PHONE_NUMBER_LABEL, PHONE_NUMBER);
        buffer_append(buffer, ORGANIZATION_LABEL, ORGANIZATION);
        // buffer_append(buffer, OTHER_LABEL, OTHER);
        buffer.append("\n");
        buffer.append("Term Information:\n");
        buffer_append(buffer, VOCABULARY_LABEL, VOCABULARY);
        buffer_append(buffer, CDISC_REQUEST_TYPE_LABEL, CDISC_REQUEST_TYPE);
        buffer_append(buffer, CDISC_CODES_LABEL, CDISC_CODES);
        buffer_append(buffer, TERM_LABEL, TERM);
        buffer.append("\n");
        buffer.append("Additional Information:\n");
        buffer_append(buffer, REASON_LABEL, REASON);
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

    public static void setupTestData() {
        boolean useTestData = false;
        //useTestData = true; //DYEE
        if (! useTestData)
            return;
        
        HttpServletRequest request = HTTPUtils.getRequest();
        HTTPUtils.setDefaulSessiontAttribute(request, EMAIL, "John.Doe@abc.com");
        HTTPUtils.setDefaulSessiontAttribute(request, NAME, "John Doe");
        HTTPUtils.setDefaulSessiontAttribute(request, PHONE_NUMBER, "987-654-3210\n987-654-3211");
        HTTPUtils.setDefaulSessiontAttribute(request, ORGANIZATION, "Google");
        // HTTPUtils.setDefaulSessiontAttribute(request, OTHER, "987-654-3210");
        // HTTPUtils.setDefaulSessiontAttribute(request, VOCABULARY, "NCI Thesaurus");
        HTTPUtils.setDefaultAttribute(request, CDISC_REQUEST_TYPE,
            AppProperties.getInstance().getCDISCRequestTypeList()[1]);
        HTTPUtils.setDefaultAttribute(request, CDISC_CODES, 
            AppProperties.getInstance().getCDISCCodeList()[1]);
        HTTPUtils.setDefaultAttribute(request, TERM, "Ultra Murine Cell Types");
        HTTPUtils.setDefaultAttribute(request, REASON, 
            "New improved version of the previous type.");
    }
}
