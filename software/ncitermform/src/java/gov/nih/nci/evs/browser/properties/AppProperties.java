package gov.nih.nci.evs.browser.properties;

import gov.nih.nci.evs.browser.utils.*;
import gov.nih.nci.evs.browser.webapp.*;

import java.util.*;

import org.apache.log4j.*;

public class AppProperties {
    private static final String PROPERTY_FILE = "NCITermFormPropertiesFile";
    private static final String BUILD_INFO = "NCITERMFORM_BUILD_INFO";
    private static final String DEBUG_ON = "DEBUG_ON";
    private static final String SEND_EMAIL = "SEND_EMAIL";
    private static final String MAIL_SMTP_SERVER = "MAIL_SMTP_SERVER";
    private static final String NCICB_CONTACT_URL = "NCICB_CONTACT_URL";
    private static final String VOCABULARY_PREFIX = "VOCABULARY_";
    private static final int VOCABULARY_MAX = 20;
    private static final String SOURCES = "SOURCES";
    private static final String CADSR_TYPES = "CADSR_TYPES";
    private static final String CDISC_REQUEST_TYPES = "CDISC_REQUEST_TYPES";
    private static final String CDISC_CODES = "CDISC_CODES";

    private static AppProperties _appProperties = null;
    private Logger _log = Logger.getLogger(AppProperties.class);
    private HashMap<String, String> _configurableItemMap;
    private String _buildInfo = null;
    private ArrayList<VocabInfo> _vocabList = null;
    private String[] _sourceList = null;
    private String[] _caDSRTypeList = null;
    private String[] _cdiscRequestTypeList = null;
    private String[] _cdiscCodeList = null;

    private AppProperties() { // Singleton Pattern
        loadProperties();
    }

    public static AppProperties getInstance() {
        if (_appProperties == null) {
            _appProperties = new AppProperties();
            Debug.setDisplay(_appProperties.isDebugOn());
        }
        return _appProperties;
    }

    private void loadProperties() {
        synchronized (AppProperties.class) {
            String propertyFile = System.getProperty(PROPERTY_FILE);
            _log.info("AppProperties File Location= " + propertyFile);

            PropertyFileParser parser = new PropertyFileParser(propertyFile);
            parser.run();
            _configurableItemMap = parser.getConfigurableItemMap();
        }
    }

    private String getProperty(String key) {
        String value = (String) _configurableItemMap.get(key);
        if (value == null)
            return null;
        if (value.compareToIgnoreCase("null") == 0)
            return null;
        return value;
    }

    public String getBuildInfo() {
        if (_buildInfo != null)
            return _buildInfo;
        try {
            _buildInfo = getProperty(AppProperties.BUILD_INFO);
            if (_buildInfo == null)
                _buildInfo = "null";
        } catch (Exception ex) {
            _buildInfo = ex.getMessage();
        }

        System.out.println("getBuildInfo returns " + _buildInfo);
        return _buildInfo;
    }

    public boolean isDebugOn() {
        return Boolean.parseBoolean(getProperty(DEBUG_ON));
    }

    public boolean isSendEmail() {
        //if (true) return true;
        return Boolean.parseBoolean(getProperty(SEND_EMAIL));
    }

    public String getContactUrl() {
        return getProperty(NCICB_CONTACT_URL);
    }

    public String[] getContactUsRecipients() {
        String value = getContactUrl();
        return StringUtils.toStrings(value, ";", false);
    }

    public String getMailSmtpServer() {
        return getProperty(MAIL_SMTP_SERVER);
    }

    private ArrayList<VocabInfo> parseVocabList() {
        ArrayList<VocabInfo> list = new ArrayList<VocabInfo>();
        for (int i=0; i<VOCABULARY_MAX; ++i) {
            String value = getProperty(VOCABULARY_PREFIX + i);
            VocabInfo vocab = VocabInfo.parse(value);
            if (vocab != null)
                list.add(vocab);
        }
        return list;
    }

    public ArrayList<VocabInfo> getVocabularies() {
        if (_vocabList == null) {
            _vocabList = parseVocabList();
            VocabInfo.debug(_vocabList);
        }
        return _vocabList;
    }

    public String[] getVocabularyNames() {
        ArrayList<VocabInfo> list = getVocabularies();
        Iterator<VocabInfo> iterator = list.iterator();
        ArrayList<String> names = new ArrayList<String>();
        while (iterator.hasNext()) {
            VocabInfo info = iterator.next();
            names.add(info.getName());
        }
        return names.toArray(new String[names.size()]);
    }

    public String getVocabularyName(String url) {
        ArrayList<VocabInfo> list = getVocabularies();
        Iterator<VocabInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            VocabInfo info = iterator.next();
            if (info.getUrl().equals(url))
                return info.getName();
        }
        return null;
    }

    public String[] getVocabularyEmails(String vocabularyName) {
        ArrayList<VocabInfo> list = getVocabularies();
        Iterator<VocabInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            VocabInfo info = iterator.next();
            if (info.getName().equals(vocabularyName)) {
                ArrayList<String> emails = info.getEmails();
                return emails.toArray(new String[emails.size()]);
            }
        }
        return new String[0];
    }
    
    public String[] getList(String propertyName, String[] list, String debugText) {
        if (list != null)
            return list;
        String value = getProperty(propertyName);
        list = StringUtils.toStrings(value, ";", false);
        if (debugText != null)
            Debug.printList(debugText, list);
        return list;
    }

    public String getSources() {
        return getProperty(SOURCES);
    }

    public String[] getSourceList() {
        return _sourceList = getList(
            SOURCES, _sourceList, "_sourceList");
    }
    
    public String getCADSRTypes() {
        return getProperty(CADSR_TYPES);
    }

    public String[] getCADSRTypeList() {
        return _caDSRTypeList = getList(
            CADSR_TYPES, _caDSRTypeList, "_caDSRTypeList");
    }

    public String getCDISCRequestTypes() {
        return getProperty(CDISC_REQUEST_TYPES);
    }

    public String[] getCDISCRequestTypeList() {
        return _cdiscRequestTypeList = getList(
            CDISC_REQUEST_TYPES, _cdiscRequestTypeList, "_cdiscRequestTypeList");
    }
    
    public String getCDISCCodes() {
        return getProperty(CDISC_CODES);
    }

    public String[] getCDISCCodeList() {
        return _cdiscCodeList = getList(
            CDISC_CODES, _cdiscCodeList, "_cdiscCodeList");
    }
}
