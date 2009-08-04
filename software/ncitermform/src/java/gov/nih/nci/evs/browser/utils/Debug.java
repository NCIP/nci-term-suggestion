package gov.nih.nci.evs.browser.utils;

import java.util.*;

import org.apache.log4j.*;

public class Debug {
    private static Logger _logger = Logger.getLogger(Debug.class);

    public static void println(String text) {
        if (! _logger.isDebugEnabled())
            return;
        if (text != null)
            _logger.debug(text);
        else _logger.debug("");
    }
    
    public static void printList(Logger logger, String text, ArrayList<?> list) {
        if (! logger.isDebugEnabled())
            return;
        logger.debug(StringUtils.SEPARATOR);
        if (text != null && text.length() > 0)
            logger.debug("* " + text);
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            logger.debug("  * " + iterator.next());
        }
    }

    public static void printList(Logger logger, String text, String[] list) {
        if (! logger.isDebugEnabled())
            return;
        logger.debug(StringUtils.SEPARATOR);
        if (text != null && text.length() > 0)
            logger.debug("* " + text);
        if (list == null)
            return;
        for (int i = 0; i < list.length; ++i) {
            logger.debug("  " + (i + 1) + ") " + list[i]);
        }
    }
}
