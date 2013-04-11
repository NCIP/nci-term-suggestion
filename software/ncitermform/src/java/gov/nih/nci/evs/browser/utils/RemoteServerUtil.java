/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.utils;

import gov.nih.nci.system.client.*;
import org.LexGrid.LexBIG.caCore.interfaces.*;

import org.LexGrid.LexBIG.LexBIGService.*;
import org.LexGrid.LexBIG.Impl.*;

/**
 * 
 */

public class RemoteServerUtil {
    private static String _serviceURL = null;

    public static LexBIGService createLexBIGService() {
        String url = null;
//        url = "http://lexevsapi-qa.nci.nih.gov/lexevsapi50";
//        url = "http://lexevsapi-stage.nci.nih.gov/lexevsapi50";
//        url = "http://lexevsapi-data-qa.nci.nih.gov/lexevsapi50";
//        url = "http://cbvapp-q1014.nci.nih.gov:19180/lexevsapi50";
//        url = "http://lexevsapi.nci.nih.gov/lexevsapi50";
//        url = "http://ncias-d177-v.nci.nih.gov:19280/lexevsapi51";

        url = "http://lexevsapi-stage.nci.nih.gov/lexevsapi50";
        return createLexBIGService(url);
    }

    public static LexBIGService createLexBIGService(String serviceUrl) {
        try {
            if (serviceUrl == null || serviceUrl.compareTo("") == 0) {
                LexBIGService lbSvc = new LexBIGServiceImpl();
                return lbSvc;
            }
            LexEVSApplicationService lexevsService = (LexEVSApplicationService) ApplicationServiceProvider
                .getApplicationServiceFromUrl(serviceUrl, "EvsServiceInfo");
            System.out.println("* Connected to " + serviceUrl);
            return (LexBIGService) lexevsService;
        } catch (Exception e) {
            System.out.println("* Unable to connected to " + serviceUrl);
            e.printStackTrace();
        }
        return null;
    }

    public static String getServiceURL() {
        return _serviceURL;
    }
}
