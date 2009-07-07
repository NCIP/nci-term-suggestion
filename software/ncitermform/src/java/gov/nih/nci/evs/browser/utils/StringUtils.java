package gov.nih.nci.evs.browser.utils;

import java.util.*;

public class StringUtils {
    public static final String SEPARATOR = 
        "----------------------------------------"
        + "----------------------------------------";

    public static String[] toStrings(String value, String delimiter,
        boolean includeDelimiter, boolean trim) {
        StringTokenizer tokenizer = new StringTokenizer(value, delimiter,
            includeDelimiter);
        ArrayList<String> list = new ArrayList<String>();
        while (tokenizer.hasMoreElements()) {
            String s = tokenizer.nextToken();
            if (trim)
                s = s.trim();
            if (s.length() > 0)
                list.add(s);
        }
        return list.toArray(new String[list.size()]);
    }

    public static String[] toStrings(String value, String delimiter,
        boolean includeDelimiter) {
        return toStrings(value, delimiter, includeDelimiter, true);
    }

    public static String toHtml(String text) {
        text = text.replaceAll("\n", "<br/>");
        text = text.replaceAll("  ", "&nbsp;&nbsp;");
        return text;
    }
    
    public static String toString(String[] values, String delimiter) {
        StringBuffer buffer = new StringBuffer();
        for (int i=0; i<values.length; ++i) {
            if (i>0)
                buffer.append(delimiter);
            buffer.append(values[i]);
        }
        return buffer.toString();
    }
}