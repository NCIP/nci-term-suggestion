package gov.nih.nci.evs.browser.utils;

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
}
