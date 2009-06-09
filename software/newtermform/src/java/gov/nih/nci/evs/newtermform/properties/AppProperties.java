package gov.nih.nci.evs.newtermform.properties;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class AppProperties {
    private static HashMap<String, String> configurableItemMap;

    public static final String DEBUG_ON = "DEBUG_ON";
    public static final String MAIL_SMTP_SERVER = "MAIL_SMTP_SERVER";
    public static final String NCICB_CONTACT_URL = "NCICB_CONTACT_URL";
    public static final String BUILD_INFO = "NEWTERMFORM_BUILD_INFO";

    private static Logger log = Logger.getLogger(AppProperties.class);
    private static AppProperties appProperties = null;

    private boolean debugOn = false;
    private String mail_smtp_server = null;
    private String ncicb_contact_url = null;
    private String buildInfo = null;

    /**
     * Private constructor for singleton pattern.
     */
    private AppProperties() {
        loadProperties();

        debugOn = Boolean.parseBoolean(getProperty(DEBUG_ON));
        ncicb_contact_url = getProperty(NCICB_CONTACT_URL);
        mail_smtp_server = getProperty(MAIL_SMTP_SERVER);
    }

    public static AppProperties getInstance() throws Exception {
        if (appProperties == null) {
            synchronized (AppProperties.class) {
                appProperties = new AppProperties();
            }
        }
        return appProperties;
    }

    private String getProperty(String key) {
        String value = (String) configurableItemMap.get(key);
        if (value == null)
            return null;
        if (value.compareToIgnoreCase("null") == 0)
            return null;
        return value;
    }

    private void loadProperties() {
        String propertyFile = System
            .getProperty("gov.nih.nci.evs.browser.NewTermFormProperties");

        log.info("AppProperties File Location= " + propertyFile);
        PropertyFileParser parser = new PropertyFileParser(propertyFile);
        parser.run();
        configurableItemMap = parser.getConfigurableItemMap();
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
}
