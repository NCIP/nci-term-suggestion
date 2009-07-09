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

    public static String wrap(int maxCharInLine, String text) {
        StringTokenizer tokenizer = new StringTokenizer(text, "\n", true);
        StringBuffer buffer = new StringBuffer();
        while (tokenizer.hasMoreTokens()) {
            String line = tokenizer.nextToken();
            buffer.append(wrapOneLine(maxCharInLine, "\n", line));
        }
        return buffer.toString();
    }

    public static int indexOfLastWhiteSpace(String text) {
        for (int i = text.length() - 1; i >= 0; --i) {
            char c = text.charAt(i);
            if (Character.isWhitespace(c))
                return i;
        }
        return -1;
    }

    public static String wrapOneLine(int maxCharInLine, String endOfLine,
            String text) {
        StringBuffer buffer = new StringBuffer();
        do {
            int start = 0, n = text.length();
            int end = start + maxCharInLine;
            if (end >= n)
                end = n;

            if (end < n && !Character.isWhitespace(text.charAt(end))) {
                String line = text.substring(start, end);
                int i = indexOfLastWhiteSpace(line);
                if (i > 0)
                    end = i;
            }

            String line = text.substring(start, end);
            if (buffer.length() > 0)
                buffer.append(endOfLine);
            buffer.append(line);
            text = text.substring(end).trim();
        } while (text.length() > 0);
        return buffer.toString();
    }

    public static String truncate(int maxChar, String text) {
        if (text.length() <= maxChar)
            return text;
        
        String dots = " ...";
        text = text.substring(0, maxChar - dots.length());
        text += dots;
        return text;
    }
}