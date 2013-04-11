/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.utils;

import java.util.*;

import javax.servlet.http.*;

import org.apache.log4j.*;

public class WebUtils {
    private static Logger _log = Logger.getLogger(WebUtils.class);
    
    public static void debugHeaders(HttpServletRequest request) {
        ArrayList<String> list = getHeaders(request);
        list = ListUtils.sort(list);
        ListUtils.debug(_log, "Request Headers", list);
    }
    
    public static ArrayList<String> getHeaders(HttpServletRequest request) {
        Enumeration<?> enumeration = request.getHeaderNames();
        ArrayList<String> list = new ArrayList<String>();
        while (enumeration.hasMoreElements()) {
            String name = (String) enumeration.nextElement();
            String value = request.getHeader(name);
            list.add(name + ": " + value);
        }
        return list;
    }
    
    public static boolean isClientBrowser(HttpServletRequest request,
            String type) {
        String userAgent = request.getHeader("user-agent");
        if (userAgent == null)
            return false;
        return userAgent.toLowerCase().contains(type);
    }
    
    public static boolean isUsingIE(HttpServletRequest request) {
        return isClientBrowser(request, "msie");
    }

    public static boolean isUsingFirefox(HttpServletRequest request) {
        return isClientBrowser(request, "firefox");
    }
}
