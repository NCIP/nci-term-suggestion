package gov.nih.nci.evs.browser.bean;

import javax.servlet.http.*;
import gov.nih.nci.evs.browser.webapp.*;
import gov.nih.nci.evs.utils.*;

import nl.captcha.Captcha;

import java.util.*;

/*
import nl.captcha.servlet.CaptchaServletUtil;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
*/


/**
 * <!-- LICENSE_TEXT_START -->
 * Copyright 2008,2009 NGIT. This software was developed in conjunction
 * with the National Cancer Institute, and so to the extent government
 * employees are co-authors, any rights in such works shall be subject
 * to Title 17 of the United States Code, section 105.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *   1. Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the disclaimer of Article 3,
 *      below. Redistributions in binary form must reproduce the above
 *      copyright notice, this list of conditions and the following
 *      disclaimer in the documentation and/or other materials provided
 *      with the distribution.
 *   2. The end-user documentation included with the redistribution,
 *      if any, must include the following acknowledgment:
 *      "This product includes software developed by NGIT and the National
 *      Cancer Institute."   If no such end-user documentation is to be
 *      included, this acknowledgment shall appear in the software itself,
 *      wherever such third-party acknowledgments normally appear.
 *   3. The names "The National Cancer Institute", "NCI" and "NGIT" must
 *      not be used to endorse or promote products derived from this software.
 *   4. This license does not authorize the incorporation of this software
 *      into any third party proprietary programs. This license does not
 *      authorize the recipient to use any trademarks owned by either NCI
 *      or NGIT
 *   5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED
 *      WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *      OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE
 *      DISCLAIMED. IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE,
 *      NGIT, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT,
 *      INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING,
 *      BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *      LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 *      CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 *      LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN
 *      ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *      POSSIBILITY OF SUCH DAMAGE.
 * <!-- LICENSE_TEXT_END -->
 */

/**
 * @author EVS Team (David Yee, Kim Ong)
 * @version 1.0
 */

public class UserSessionBean {
    public String changeRequest() {
        HTTPUtils.getRequest().setAttribute(
            FormRequest.MESSAGE, "UserSessionBean.changeRequest");
        return FormRequest.MESSAGE_STATE;
    }

    private Prop.Version getVersion() {
        HttpServletRequest request = HTTPUtils.getRequest();
        Prop.Version version = (Prop.Version)
            request.getSession().getAttribute(FormRequest.VERSION);
        return version;
    }

    private IFormRequest getFormRequest() {
        Prop.Version version = getVersion();
        if (version == Prop.Version.CDISC)
            return new SuggestionCDISCRequest();
        return new SuggestionRequest();
    }

    public String requestSuggestion() {
	    HttpServletRequest request = HTTPUtils.getRequest();
	    String retry = (String) request.getSession().getAttribute("retry");

        String email = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.EMAIL);
        String term = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.TERM);
        String other = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.OTHER);
        String vocabulary = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.VOCABULARY);
        String synonyms = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.SYNONYMS);
        String nearest_code = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.NEAREST_CODE);
        String definition = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.DEFINITION);
        String cadsr_source = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.CADSR_SOURCE);
        String cadsr_type = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.CADSR_TYPE);
        String reason = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.REASON);
        String project = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.PROJECT);
      //String warnings = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.WARNINGS);

	    if (retry == null || retry.compareTo("true") != 0) {
			email = (String) request.getParameter(SuggestionRequest.EMAIL);
			term = (String) request.getParameter(SuggestionRequest.TERM);
			other = (String) request.getParameter(SuggestionRequest.OTHER);
			vocabulary = (String) request.getParameter(SuggestionRequest.VOCABULARY);
			synonyms = (String) request.getParameter(SuggestionRequest.SYNONYMS);
			nearest_code = (String) request.getParameter(SuggestionRequest.NEAREST_CODE);
			definition = (String) request.getParameter(SuggestionRequest.DEFINITION);
			cadsr_source = (String) request.getParameter(SuggestionRequest.CADSR_SOURCE);
			cadsr_type = (String) request.getParameter(SuggestionRequest.CADSR_TYPE);
			reason = (String) request.getParameter(SuggestionRequest.REASON);
			project = (String) request.getParameter(SuggestionRequest.PROJECT);

			request.getSession().setAttribute(SuggestionRequest.EMAIL, email);
			request.getSession().setAttribute(SuggestionRequest.TERM, term);
			request.getSession().setAttribute(SuggestionRequest.OTHER, other);
			request.getSession().setAttribute(SuggestionRequest.VOCABULARY, vocabulary);
			request.getSession().setAttribute(SuggestionRequest.SYNONYMS, synonyms);
			request.getSession().setAttribute(SuggestionRequest.NEAREST_CODE, nearest_code);
			request.getSession().setAttribute(SuggestionRequest.DEFINITION, definition);
			request.getSession().setAttribute(SuggestionRequest.CADSR_SOURCE, cadsr_source);
			request.getSession().setAttribute(SuggestionRequest.CADSR_TYPE, cadsr_type);
			request.getSession().setAttribute(SuggestionRequest.REASON, reason);
			request.getSession().setAttribute(SuggestionRequest.PROJECT, project);
		}

        try {
    		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
    		if (captcha == null) {
    			captcha = new Captcha.Builder(200, 50)
					.addText()
					.addBackground()
					//.addNoise()
				    .gimp()
    				//.addBorder()
   					.build();
    			request.getSession().setAttribute(Captcha.NAME, captcha);
    		}
    
    		request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars
    		String answer = request.getParameter("answer");
    		if (answer == null || answer.length() == 0) {
    			String msg = "Please enter the characters appearing in the image.";
    			request.getSession().setAttribute("message", msg);
    			return "incomplete";
    		}
    
    		request.getSession().removeAttribute("reload");
    		if (! captcha.isCorrect(answer))
    		    throw new Exception("WARNING: The string you entered does not match with what is shown in the image.");
    		
    		request.getSession().removeAttribute(Captcha.NAME);
            return new SuggestionRequest().submitForm();
            //IFormRequest req = getFormRequest();
            //return req.submitForm();
        } catch (Exception e) {
            String msg = e.getMessage();
            request.getSession().setAttribute("message", msg);
            request.getSession().setAttribute("reload", "true");
            return "incomplete";
        }
    }

    public String requestSuggestionCDISC() {
	    HttpServletRequest request = HTTPUtils.getRequest();
	    String retry_cdisc = (String) request.getSession().getAttribute("retry_cdisc");

	    String email = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.EMAIL);
	    String name = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.NAME);
	    String phone_number = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.PHONE_NUMBER);
	    String organization = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.ORGANIZATION);
	    String vocabulary = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.VOCABULARY);
	    String cdisc_request_type = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.CDISC_REQUEST_TYPE);
	    String cdisc_codes = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.CDISC_CODES);
	    String term = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.TERM);
	    String reason = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.REASON);
	    //String warnings = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.WARNINGS);

	    if (retry_cdisc == null || retry_cdisc.compareTo("true") != 0) {
			email = (String) request.getParameter(SuggestionCDISCRequest.EMAIL);
			name = (String) request.getParameter(SuggestionCDISCRequest.NAME);
			phone_number = (String) request.getParameter(SuggestionCDISCRequest.PHONE_NUMBER);
			organization = (String) request.getParameter(SuggestionCDISCRequest.ORGANIZATION);
			vocabulary = (String) request.getParameter(SuggestionCDISCRequest.VOCABULARY);
			cdisc_request_type = (String) request.getParameter(SuggestionCDISCRequest.CDISC_REQUEST_TYPE);
			cdisc_codes = (String) request.getParameter(SuggestionCDISCRequest.CDISC_CODES);
			term = (String) request.getParameter(SuggestionCDISCRequest.TERM);
			reason = (String) request.getParameter(SuggestionCDISCRequest.REASON);

			request.getSession().setAttribute(SuggestionCDISCRequest.EMAIL, email);
			request.getSession().setAttribute(SuggestionCDISCRequest.NAME, name);
			request.getSession().setAttribute(SuggestionCDISCRequest.PHONE_NUMBER, phone_number);
			request.getSession().setAttribute(SuggestionCDISCRequest.ORGANIZATION, organization);
			request.getSession().setAttribute(SuggestionCDISCRequest.VOCABULARY, vocabulary);
			request.getSession().setAttribute(SuggestionCDISCRequest.CDISC_REQUEST_TYPE, cdisc_request_type);
			request.getSession().setAttribute(SuggestionCDISCRequest.CDISC_CODES, cdisc_codes);
			request.getSession().setAttribute(SuggestionCDISCRequest.TERM, term);
			request.getSession().setAttribute(SuggestionCDISCRequest.REASON, reason);
		}

		request.getSession().setAttribute("version", Prop.Version.CDISC);
		try {
    	    IFormRequest iFormRequest = getFormRequest();
    		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
    		if (captcha == null) {
    			captcha = new Captcha.Builder(200, 50)
    				.addText()
    				.addBackground()
    				//.addNoise()
    				.gimp()
    				//.addBorder()
    				.build();
    			request.getSession().setAttribute(Captcha.NAME, captcha);
    		}
    
    		request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars
    		String answer = request.getParameter("answer");
    		if (answer == null || answer.length() == 0) {
    			String msg = "Please enter the characters appearing in the image.";
    			request.getSession().setAttribute("message", msg);
    			return "incomplete_cdisc";
    		}
    
    		request.getSession().removeAttribute("reload");
    		if (! captcha.isCorrect(answer))
    		    throw new Exception("WARNING: The string you entered does not match with what is shown in the image.");

    		request.getSession().removeAttribute(Captcha.NAME);
    		//return iFormRequest.submitForm();
    		return new SuggestionCDISCRequest().submitForm();

    		//IFormRequest req = getFormRequest();
            //return req.submitForm();
		} catch (Exception e) {
            String msg = e.getMessage();
            request.getSession().setAttribute("message", msg);
            request.getSession().setAttribute("reload", "true");
            return "incomplete_cdisc";
		}
    }

    public String clearSuggestion() {
        IFormRequest request = getFormRequest();
        return request.clearForm();
    }

    public String contactUs() {
        ContactUsRequest request = new ContactUsRequest();
        return request.submitForm();
    }

    public String clearContactUs() {
        ContactUsRequest request = new ContactUsRequest();
        return request.clearForm();
    }

    public String refreshForm() {
	    HttpServletRequest request = HTTPUtils.getRequest();
	    String retry = (String) request.getSession().getAttribute("retry");

        String email = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.EMAIL);
        String term = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.TERM);
        String other = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.OTHER);
        String vocabulary = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.VOCABULARY);
        String synonyms = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.SYNONYMS);
        String nearest_code = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.NEAREST_CODE);
        String definition = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.DEFINITION);
        String cadsr_source = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.CADSR_SOURCE);
        String cadsr_type = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.CADSR_TYPE);
        String reason = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.REASON);
        String project = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.PROJECT);
      //String warnings = HTTPUtils.getJspSessionAttributeString(request, SuggestionRequest.WARNINGS);

	    if (retry == null || retry.compareTo("true") != 0) {
			email = (String) request.getParameter(SuggestionRequest.EMAIL);
			term = (String) request.getParameter(SuggestionRequest.TERM);
			other = (String) request.getParameter(SuggestionRequest.OTHER);
			vocabulary = (String) request.getParameter(SuggestionRequest.VOCABULARY);
			synonyms = (String) request.getParameter(SuggestionRequest.SYNONYMS);
			nearest_code = (String) request.getParameter(SuggestionRequest.NEAREST_CODE);
			definition = (String) request.getParameter(SuggestionRequest.DEFINITION);
			cadsr_source = (String) request.getParameter(SuggestionRequest.CADSR_SOURCE);
			cadsr_type = (String) request.getParameter(SuggestionRequest.CADSR_TYPE);
			reason = (String) request.getParameter(SuggestionRequest.REASON);
			project = (String) request.getParameter(SuggestionRequest.PROJECT);

			request.getSession().setAttribute(SuggestionRequest.EMAIL, email);
			request.getSession().setAttribute(SuggestionRequest.TERM, term);
			request.getSession().setAttribute(SuggestionRequest.OTHER, other);
			request.getSession().setAttribute(SuggestionRequest.VOCABULARY, vocabulary);
			request.getSession().setAttribute(SuggestionRequest.SYNONYMS, synonyms);
			request.getSession().setAttribute(SuggestionRequest.NEAREST_CODE, nearest_code);
			request.getSession().setAttribute(SuggestionRequest.DEFINITION, definition);
			request.getSession().setAttribute(SuggestionRequest.CADSR_SOURCE, cadsr_source);
			request.getSession().setAttribute(SuggestionRequest.CADSR_TYPE, cadsr_type);
			request.getSession().setAttribute(SuggestionRequest.REASON, reason);
			request.getSession().setAttribute(SuggestionRequest.PROJECT, project);
		}

		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
		if (captcha == null) {
			captcha = new Captcha.Builder(200, 50)
				.addText()
				.addBackground()
				//.addNoise()
				.gimp()
				//.addBorder()
				.build();
			request.getSession().setAttribute(Captcha.NAME, captcha);
		}

		try {
			request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars
		} catch (Exception ex) {

		}

		request.getSession().removeAttribute("reload");
		String msg = "Please press Refresh to generate a new image.";
		request.getSession().setAttribute("message", msg);
		request.getSession().setAttribute("reload", "true");
		request.getSession().setAttribute("refresh", "true");
		return "refresh";
    }

    public String refreshCDISCForm() {
	    HttpServletRequest request = HTTPUtils.getRequest();
	    String retry_cdisc = (String) request.getSession().getAttribute("retry_cdisc");

	    String email = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.EMAIL);
	    String name = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.NAME);
	    String phone_number = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.PHONE_NUMBER);
	    String organization = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.ORGANIZATION);
	    String vocabulary = HTTPUtils.getJspSessionAttributeString(request, SuggestionCDISCRequest.VOCABULARY);
	    String cdisc_request_type = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.CDISC_REQUEST_TYPE);
	    String cdisc_codes = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.CDISC_CODES);
	    String term = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.TERM);
	    String reason = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.REASON);
	    //String warnings = HTTPUtils.getJspAttributeString(request, SuggestionCDISCRequest.WARNINGS);

	    if (retry_cdisc == null || retry_cdisc.compareTo("true") != 0) {

			email = (String) request.getParameter(SuggestionCDISCRequest.EMAIL);
			name = (String) request.getParameter(SuggestionCDISCRequest.NAME);
			phone_number = (String) request.getParameter(SuggestionCDISCRequest.PHONE_NUMBER);
			organization = (String) request.getParameter(SuggestionCDISCRequest.ORGANIZATION);
			vocabulary = (String) request.getParameter(SuggestionCDISCRequest.VOCABULARY);
			cdisc_request_type = (String) request.getParameter(SuggestionCDISCRequest.CDISC_REQUEST_TYPE);
			cdisc_codes = (String) request.getParameter(SuggestionCDISCRequest.CDISC_CODES);
			term = (String) request.getParameter(SuggestionCDISCRequest.TERM);
			reason = (String) request.getParameter(SuggestionCDISCRequest.REASON);

			request.getSession().setAttribute(SuggestionCDISCRequest.EMAIL, email);
			request.getSession().setAttribute(SuggestionCDISCRequest.NAME, name);
			request.getSession().setAttribute(SuggestionCDISCRequest.PHONE_NUMBER, phone_number);
			request.getSession().setAttribute(SuggestionCDISCRequest.ORGANIZATION, organization);
			request.getSession().setAttribute(SuggestionCDISCRequest.VOCABULARY, vocabulary);
			request.getSession().setAttribute(SuggestionCDISCRequest.CDISC_REQUEST_TYPE, cdisc_request_type);
			request.getSession().setAttribute(SuggestionCDISCRequest.CDISC_CODES, cdisc_codes);
			request.getSession().setAttribute(SuggestionCDISCRequest.TERM, term);
			request.getSession().setAttribute(SuggestionCDISCRequest.REASON, reason);
		}

		Captcha captcha = (Captcha) request.getSession().getAttribute(Captcha.NAME);
		if (captcha == null) {
			captcha = new Captcha.Builder(200, 50)
				.addText()
				.addBackground()
				//.addNoise()
				.gimp()
				//.addBorder()
				.build();
			request.getSession().setAttribute(Captcha.NAME, captcha);
		}

		try {
			request.setCharacterEncoding("UTF-8"); // Do this so we can capture non-Latin chars
		} catch (Exception ex) {

		}

		request.getSession().removeAttribute("reload");
		String msg = "Please press Refresh to generate a new image.";
		request.getSession().setAttribute("message", msg);
		request.getSession().setAttribute("reload", "true");
		request.getSession().setAttribute("refresh", "true");
		return "refresh_cdisc";
    }

}
