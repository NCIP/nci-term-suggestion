package gov.nih.nci.evs.browser.webapp;

import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.HttpServletRequest;

public class FormUtils {
    private static final String VERSION = FormRequest.VERSION;
    
    public static void clearAllSessionAttributes(HttpServletRequest request) {
        HTTPUtils.clearSessionAttributes(request, FormRequest.ALL_PARAMETERS);
        HTTPUtils.clearSessionAttributes(request, SuggestionRequest.MOST_PARAMETERS);
    }

    public static String getIndexPage(HttpServletRequest request) {
        String version = HTTPUtils.getSessionAttributeString(
            request, VERSION, false, false);
        String basePath = request.getContextPath();
        String indexPage = basePath + "/" + Prop.Version.getUrlParameter(version);
        clearAllSessionAttributes(request);
        return indexPage;
    }
}
