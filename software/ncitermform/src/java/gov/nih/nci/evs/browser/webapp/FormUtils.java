package gov.nih.nci.evs.browser.webapp;

import gov.nih.nci.evs.browser.utils.*;

import javax.servlet.http.HttpServletRequest;

public class FormUtils {
    private static final String VERSION = FormRequest.VERSION;
    
    public static String getBasePath(HttpServletRequest request) {
        return request.getContextPath();
    }
    
    public static String getPagesPath(HttpServletRequest request) {
        String basePath = getBasePath(request);
        return basePath + "/pages";
    }

    public static String getImagesPath(HttpServletRequest request) {
        String basePath = getBasePath(request);
        return basePath + "/images";
    }

    public static String getJSPath(HttpServletRequest request) {
        String basePath = getBasePath(request);
        return basePath + "/js";
    }

    public static String getCSSPath(HttpServletRequest request) {
        String basePath = getBasePath(request);
        return basePath + "/css";
    }

    public static void clearAllAttributes(HttpServletRequest request) {
        HTTPUtils.clearAttributes(request, FormRequest.ALL_PARAMETERS);
        HTTPUtils.clearAttributes(request, SuggestionRequest.MOST_PARAMETERS);
    }

    public static String getIndexPage(HttpServletRequest request) {
        String version = HTTPUtils.getSessionAttributeString(
            request, VERSION, false, false);
        String basePath = getBasePath(request);
        String indexPage = basePath + "/" + Prop.Version.getUrlParameter(version);
        clearAllAttributes(request);
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
          clearAllAttributes(request);
        }
        request.getSession().setAttribute(VERSION, curr_version.name());
        return curr_version;
    }
}
