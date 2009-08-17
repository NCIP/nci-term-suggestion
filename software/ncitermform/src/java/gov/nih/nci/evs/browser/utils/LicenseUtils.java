package gov.nih.nci.evs.browser.utils;

import java.util.*;

import org.LexGrid.LexBIG.DataModel.Core.*;
import org.LexGrid.LexBIG.LexBIGService.*;

public class LicenseUtils extends Object {
    private static HashMap<String, String> licenseHashMap = new HashMap<String, String>();
    private static final String UNAVAILABLE = "Copyright information unavailable";

    private static String getHashMapKey(String codingSchemeName, String version) {
        if (version == null || version.trim().length() <= 0)
            return codingSchemeName.trim();
        return codingSchemeName.trim() + " [" + version.trim() + "]";
    }

    public static boolean isLicensed(String codingSchemeName, String version) {
        String key = getHashMapKey(codingSchemeName, version);
        String value = licenseHashMap.get(key);
        return value != null && value.length() > 0
            && !value.contains(UNAVAILABLE);
    }

    public static String getLicense(String codingSchemeName, String version) {
        String key = getHashMapKey(codingSchemeName, version);
        String value = licenseHashMap.get(key);
        if (value == null) {
            try {
                LexBIGService lbs = RemoteServerUtil.createLexBIGService();
                CodingSchemeVersionOrTag versionOrTag = new CodingSchemeVersionOrTag();
                if (version != null)
                    versionOrTag.setVersion(version);
                value = lbs.resolveCodingSchemeCopyright(codingSchemeName,
                    versionOrTag);
            } catch (Exception e) {
                //e.printStackTrace();
                value = UNAVAILABLE;
            }
        }
        licenseHashMap.put(key, value);
        return value;
    }
    
    private static void isLicensedTest(String codingSchemeName, String version) {
        boolean isLicensed = isLicensed(codingSchemeName, version);
        System.out.println("* " + codingSchemeName + ", " + version + ": "
            + isLicensed);
    }

    private static void acceptLicensedTest(String codingSchemeName, String version) {
        boolean isLicensed = isLicensed(codingSchemeName, version);
        if (isLicensed) {
            System.out.println("  * Alreadied licensed.");
            return;
        }
        
        String license = getLicense(codingSchemeName, version);
        System.out.println("  * Get license: " + license);
        isLicensed = isLicensed(codingSchemeName, version);
        if (isLicensed) 
            System.out.println("  * Got license.");
        else System.out.println("  * Failed to get license.");
    }
    
    public static void main(String[] args) {
        String vocabulary = "MedDRA";
        String version = "10.1";

        System.out.println("-------------------------------------------------");
        vocabulary = "MedDRA";
        version = "10.1";
        isLicensedTest(vocabulary, version);
        acceptLicensedTest(vocabulary, version);
        isLicensedTest(vocabulary, version);

        System.out.println("-------------------------------------------------");
        vocabulary = "MedDRA";
        version = "10.2";
        isLicensedTest(vocabulary, version);
        acceptLicensedTest(vocabulary, version);
        isLicensedTest(vocabulary, version);

        System.out.println("-------------------------------------------------");
        vocabulary = "MedDRA";
        version = null;
        isLicensedTest(vocabulary, version);
        acceptLicensedTest(vocabulary, version);
        isLicensedTest(vocabulary, version);

        System.out.println("-------------------------------------------------");
        vocabulary = "MedDRA"; version = "10.1"; isLicensedTest(vocabulary, version);
        vocabulary = "MedDRA"; version = "10.2"; isLicensedTest(vocabulary, version);
        vocabulary = "MedDRA"; version = null;   isLicensedTest(vocabulary, version);
    }
}