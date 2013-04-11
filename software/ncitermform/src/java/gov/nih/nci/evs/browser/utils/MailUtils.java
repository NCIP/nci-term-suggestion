/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.utils;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class MailUtils extends Object {
    private static final long serialVersionUID = 1L;
    private static final int MAX_SUBJECT_CHAR = 256;

    public static boolean isValidEmailAddress(String text) {
        int posOfAtChar = text.indexOf('@');
        int posOfDotChar = text.lastIndexOf('.');

        if (posOfAtChar <= 0 || posOfDotChar <= 0)
            return false;
        if (posOfAtChar > posOfDotChar)
            return false;
        if (posOfAtChar == posOfDotChar - 1)
            return false;
        if (posOfDotChar == text.length() - 1)
            return false;
        return true;
    }

    private static void postMailValidation(String from, String recipients[],
        String subject, String message) throws UserInputException {
        StringBuffer error = new StringBuffer();
        String indent = "    ";
        int ctr = 0;

        if (subject == null || subject.length() <= 0) {
            error.append(indent + "* subject of your email\n");
            ++ctr;
        }
        if (message == null || message.length() <= 0) {
            error.append(indent + "* detailed description\n");
            ++ctr;
        }
        if (from == null || from.length() <= 0) {
            error.append(indent + "* your e-mail address\n");
            ++ctr;
        }
        if (error.length() > 0) {
            String s = "Warning: Your message was not sent.\n";
            if (ctr > 1)
                s += "The following fields were not set:\n";
            else
                s += "The following field was not set:\n";
            error.insert(0, s);
            throw new UserInputException(error.toString());
        }

        if (!isValidEmailAddress(from)) {
            error.append("Warning: Your message was not sent.\n");
            error.append(indent + "* Invalid e-mail address: \"" + from + "\"");
            throw new UserInputException(error.toString());
        }
    }
    
    public static void postMail(String mail_smtp_server, String from, 
        String recipients[], String subject, String message, boolean send) 
            throws MessagingException, Exception {
        if (mail_smtp_server == null || mail_smtp_server.length() <= 0)
            throw new MessagingException("SMTP host not set.");
        
        subject = StringUtils.truncate(MAX_SUBJECT_CHAR, subject);
        postMailValidation(from, recipients, subject, message);

        // Sets the host smtp address.
        Properties props = new Properties();
        props.put("mail.smtp.host", mail_smtp_server);

        // Creates some properties and get the default session.
        Session session = Session.getDefaultInstance(props, null);
        session.setDebug(false);

        // Creates a message.
        Message msg = new MimeMessage(session);

        // Sets the from and to addresses.
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);
        msg.setRecipient(Message.RecipientType.BCC, addressFrom);

        InternetAddress[] addressTo = new InternetAddress[recipients.length];
        for (int i = 0; i < recipients.length; i++)
            addressTo[i] = new InternetAddress(recipients[i]);
        msg.setRecipients(Message.RecipientType.TO, addressTo);

        // Optional: You can set your custom headers in the email if you want.
        msg.addHeader("MyHeaderName", "myHeaderValue");

        // Setting the Subject and Content Type.
        msg.setSubject(subject);
        msg.setContent(message, "text/plain");
        if (send)
            Transport.send(msg);
    }
    
    public static void postMail(String mail_smtp_server, String from, 
        String recipients[], String subject, String message) 
            throws MessagingException, Exception {
        postMail(mail_smtp_server, from, recipients, subject, message, true);
    }
}
