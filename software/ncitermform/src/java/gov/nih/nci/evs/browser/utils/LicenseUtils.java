package gov.nih.nci.evs.browser.utils;

import java.util.*;

import org.LexGrid.LexBIG.DataModel.Core.*;
import org.LexGrid.LexBIG.LexBIGService.*;

public class LicenseUtils extends Object {
    private static HashMap<String, String> copyrightHashMap = new HashMap<String, String>();
    private static final String UNAVAILABLE = "Copyright information unavailable";

    private static String getHashMapKey(String codingSchemeName, String version) {
        if (version == null || version.trim().length() <= 0)
            return codingSchemeName.trim();
        return codingSchemeName.trim() + " [" + version.trim() + "]";
    }

    public static boolean isLicensed(String codingSchemeName, String version) {
        String value = getCopyright(codingSchemeName, version);
        return value != null && value.length() > 0
            && !value.contains(UNAVAILABLE);
    }

    public static String getCopyright(String codingSchemeName, String version) {
        System.out.println("LicenseUtils.getCopyright");
        String key = getHashMapKey(codingSchemeName, version);
        String value = copyrightHashMap.get(key);
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
        copyrightHashMap.put(key, value);
        return value;
    }

    private static void debug(String codingSchemeName, String version) {
        boolean isLicensed = isLicensed(codingSchemeName, version);
        System.out.println("* " + codingSchemeName + ", " + version + ": "
            + isLicensed);
        if (isLicensed)
            System.out.println("  * copyright: " + getCopyright(codingSchemeName, version));
    }

    public static void main(String[] args) {
        String vocabulary = "MedDRA";
        String version = "10.1";

        debug(vocabulary, version);
        debug(vocabulary, version);
        debug(vocabulary, "10.2");
        debug(vocabulary, "10.2");
        debug(vocabulary, null);
        debug(vocabulary, null);
        debug(vocabulary, version);
        debug(vocabulary, version);
    }
}