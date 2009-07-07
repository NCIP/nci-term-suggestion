package gov.nih.nci.evs.browser.utils;

import java.util.*;

public class Debug {
    private static final String PREFIX = "DEBUG: ";
    private static boolean _display = true;
    
    public static boolean setDisplay(boolean display) {
        boolean prev = _display;
        _display = display;
        return prev;
    }
    
    public static boolean isDisplay() {
        return _display;
    }

    public static void println(String text) {
        if (_display)
            System.out.println(PREFIX + text);
    }
    
    public static void println() {
        if (_display)
            println("");
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
            println("  * " + (i + 1) + ") " + list[i]);
        }
    }
}
