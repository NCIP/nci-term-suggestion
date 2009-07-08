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
    
    public static Prop.Version getVersion(HttpServletRequest request) {
        String currVersion = HTTPUtils.getSessionAttributeString(
            request, VERSION, false, false);
        String parameterVersion = HTTPUtils.getParameter(
            request, VERSION, false);
        Prop.Version curr_version = Prop.Version.valueOfOrDefault(currVersion);
        Prop.Version parameter_version = Prop.Version.valueOfOrDefault(parameterVersion);
        if (parameter_version != curr_version) {
          curr_version = parameter_version;
          clearAllSessionAttributes(request);
        }
        request.getSession().setAttribute(VERSION, curr_version.name());
        return curr_version;
    }
}
