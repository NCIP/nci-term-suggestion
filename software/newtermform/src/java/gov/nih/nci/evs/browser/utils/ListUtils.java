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
        System.out.println("DEBUG: * " + text);
        while (iterator.hasNext()) {
            System.out.println("DEBUG:   * " + iterator.next());
        }
    }
}
