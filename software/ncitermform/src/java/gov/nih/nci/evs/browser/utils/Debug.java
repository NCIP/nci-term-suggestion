package gov.nih.nci.evs.browser.utils;

import java.util.*;

import org.apache.log4j.*;

public class Debug {
    private static Logger _log = Logger.getLogger(Debug.class);
    private static boolean _display = _log.isDebugEnabled();
    
    public static boolean setDisplay(boolean display) {
        boolean prev = _display;
        _display = display && _log.isDebugEnabled();
        return prev;
    }
    
    public static boolean isDisplay() {
        return _display;
    }

    public static void println(String text) {
        if (! isDisplay())
            return;
        if (text != null)
            _log.debug(text);
        else _log.debug("");
    }
    
    public static void println() {
        if (isDisplay())
            println(null);
    }
    
    public static void printList(String text, ArrayList<?> list) {
        if (! isDisplay())
            return;
        println(StringUtils.SEPARATOR);
        if (text != null && text.length() > 0)
            println("* " + text);
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            println("  * " + iterator.next());
        }
    }

    public static void printList(String text, String[] list) {
        if (! isDisplay())
            return;
        println(StringUtils.SEPARATOR);
        if (text != null && text.length() > 0)
            println("* " + text);
        if (list == null)
            return;
        for (int i = 0; i < list.length; ++i) {
            println("  " + (i + 1) + ") " + list[i]);
        }
    }
}
