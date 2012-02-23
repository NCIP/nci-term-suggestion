package gov.nih.nci.evs.browser.utils;

import java.util.*;

import org.apache.log4j.*;

public class ListUtils {
    public static ArrayList<String> sort(ArrayList<String> list) {
        String[] as = new String[list.size()];
        list.toArray(as);
        Arrays.sort(as);
        return new ArrayList<String>(Arrays.asList(as));
    }

    public static void debug(Logger logger, String text, ArrayList<?> list) {
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

    public static void debug(Logger logger, String text, String[] list) {
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
