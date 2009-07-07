package gov.nih.nci.evs.browser.utils;

import java.util.*;

public class ListUtils {
    public static ArrayList<String> sort(ArrayList<String> list) {
        String[] as = new String[list.size()];
        list.toArray(as);
        Arrays.sort(as);
        return new ArrayList<String>(Arrays.asList(as));
    }
    
    public static void debugList(String text, ArrayList<?> list) {
        Iterator<?> iterator = list.iterator();
        Debug.println(StringUtils.SEPARATOR);
        Debug.println("* " + text);
        while (iterator.hasNext()) {
            Debug.println("  * " + iterator.next());
        }
    }
}
