package gov.nih.nci.evs.browser.webapp;

import gov.nih.nci.evs.browser.properties.*;
import gov.nih.nci.evs.browser.utils.*;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ContactUsRequest extends FormRequest {
    public ContactUsRequest(HttpServletRequest request) {
        super(request);
    }
    
    public String submitForm() {
        String msg = "Your message was successfully sent.";
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        AppProperties appProperties = AppProperties.getInstance();
        
        try {
            String mailServer = appProperties.getMailSmtpServer();
            String subject = request.getParameter("subject");
            String emailMsg = request.getParameter("message");
            String from = request.getParameter("emailaddress");
            String recipients[] = appProperties.getContactUsRecipients();
            MailUtils.postMail(mailServer, from, recipients, subject, emailMsg);
        } catch (UserInputException e) {
            msg = e.getMessage();
            request.setAttribute("errorMsg", StringUtils.toHtml(msg));
            request.setAttribute("errorType", "user");
            return "error";
        } catch (Exception e) {
            msg = "System Error: Your message was not sent.\n";
            msg += "    (If possible, please contact NCI systems team.)\n";
            msg += "\n";
            msg += e.getMessage();
            request.setAttribute("errorMsg", StringUtils.toHtml(msg));
            request.setAttribute("errorType", "system");
            e.printStackTrace();
            return "error";
        }

        request.getSession().setAttribute("message", StringUtils.toHtml(msg));
        return "message";
    }
}

