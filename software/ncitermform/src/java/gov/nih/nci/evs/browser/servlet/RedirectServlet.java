/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.servlet;

import gov.nih.nci.evs.browser.webapp.*;

/**
 * 
 */

/**
 * @author EVS Team
 * @version 1.0
 *
 * Modification history
 *     Initial implementation kim.ong@ngc.com
 *
 */


import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import gov.nih.nci.evs.utils.*;
//import org.apache.log4j.*;



public final class RedirectServlet extends HttpServlet {
    //private static Logger _logger = Logger.getLogger(RedirectServlet.class);

    /**
     * Validates the Init and Context parameters, configures authentication URL
     *
     * @throws ServletException if the init parameters are invalid or any other
     *         problems occur during initialisation
     */
    public void init() throws ServletException {

    }

    /**
     * Route the user to the execute method
     *
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        execute(request, response);
    }

    /**
     * Route the user to the execute method
     *
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        execute(request, response);
    }

    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     *
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */


    public void resetSessionVariables(HttpServletRequest request) {
		request.getSession().removeAttribute(SuggestionRequest.EMAIL);
		request.getSession().removeAttribute(SuggestionRequest.TERM);
		request.getSession().removeAttribute(SuggestionRequest.OTHER);
		request.getSession().removeAttribute(SuggestionRequest.VOCABULARY);
		request.getSession().removeAttribute(SuggestionRequest.SYNONYMS);
		request.getSession().removeAttribute(SuggestionRequest.NEAREST_CODE);
		request.getSession().removeAttribute(SuggestionRequest.DEFINITION);
		request.getSession().removeAttribute(SuggestionRequest.CADSR_SOURCE);
		request.getSession().removeAttribute(SuggestionRequest.CADSR_TYPE);
		request.getSession().removeAttribute(SuggestionRequest.REASON);
		request.getSession().removeAttribute(SuggestionRequest.PROJECT);
		request.getSession().removeAttribute(SuggestionRequest.VERSION);
	}


    public void resetCDISCSessionVariables(HttpServletRequest request) {
		request.getSession().removeAttribute(SuggestionCDISCRequest.EMAIL);
		request.getSession().removeAttribute(SuggestionCDISCRequest.NAME);
		request.getSession().removeAttribute(SuggestionCDISCRequest.PHONE_NUMBER);
		request.getSession().removeAttribute(SuggestionCDISCRequest.ORGANIZATION);
		request.getSession().removeAttribute(SuggestionCDISCRequest.VOCABULARY);
		request.getSession().removeAttribute(SuggestionCDISCRequest.CDISC_REQUEST_TYPE);
		request.getSession().removeAttribute(SuggestionCDISCRequest.CDISC_CODES);
		request.getSession().removeAttribute(SuggestionCDISCRequest.TERM);
		request.getSession().removeAttribute(SuggestionCDISCRequest.REASON);
	}


    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String version = HTTPUtils.cleanXSS((String) request.getParameter("version"));
        if (version != null && version.compareToIgnoreCase("CDISC") == 0) {
            resetCDISCSessionVariables(request);
			String url = request.getContextPath() + "/pages/main/suggestion_cdisc.jsf?version=" + version;
			response.sendRedirect(response.encodeRedirectURL(url));
		} else {
			resetSessionVariables(request);
			String url = request.getContextPath() + "/pages/main/suggestion.jsf?version=" + version;
			response.sendRedirect(response.encodeRedirectURL(url));
		}
	}
}
