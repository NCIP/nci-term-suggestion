package gov.nih.nci.evs.newtermform.properties;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class AppProperties {
    private static final String PROPERTY_FILE = 
        "gov.nih.nci.evs.browser.NewTermFormProperties";
    
    private static final String DEBUG_ON = "DEBUG_ON";
    private static final String MAIL_SMTP_SERVER = "MAIL_SMTP_SERVER";
    private static final String NCICB_CONTACT_URL = "NCICB_CONTACT_URL";
    private static final String BUILD_INFO = "NEWTERMFORM_BUILD_INFO";

    private static AppProperties appProperties = null;
    private Logger log = Logger.getLogger(AppProperties.class);
    private HashMap<String, String> configurableItemMap;
    private String buildInfo = null;

    private AppProperties() { // Singleton Pattern
        loadProperties();
    }

    public static AppProperties getInstance() throws Exception {
        if (appProperties == null)
            appProperties = new AppProperties();
        return appProperties;
    }

    private void loadProperties() {
        synchronized (AppProperties.class) {
            String propertyFile = System.getProperty(PROPERTY_FILE);
            log.info("AppProperties File Location= " + propertyFile);
            
            PropertyFileParser parser = new PropertyFileParser(propertyFile);
            parser.run();
            configurableItemMap = parser.getConfigurableItemMap();
        }
    }

    private String getProperty(String key) {
        String value = (String) configurableItemMap.get(key);
        if (value == null)
            return null;
        if (value.compareToIgnoreCase("null") == 0)
            return null;
        return value;
    }

    public String getBuildInfo() {
        if (buildInfo != null)
            return buildInfo;
        try {
            buildInfo = getProperty(AppProperties.BUILD_INFO);
            if (buildInfo == null)
                buildInfo = "null";
        } catch (Exception ex) {
            buildInfo = ex.getMessage();
        }

        System.out.println("getBuildInfo returns " + buildInfo);
        return buildInfo;
    }
    
    public boolean getDebugOn() {
        return Boolean.parseBoolean(getProperty(DEBUG_ON));
    }
    
    public String getContactUrl() {
        return getProperty(NCICB_CONTACT_URL);
    }
    
    public String getMailSmtpServer() {
        return getProperty(MAIL_SMTP_SERVER);
    }
}
