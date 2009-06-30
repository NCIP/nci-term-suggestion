package gov.nih.nci.evs.browser.utils;

import java.util.*;

public class Prop {
    public static enum Modifiable { 
        Definition, Synonym, Others;
        
        public static Modifiable valueOfOrDefault(String text) {
            try {
                return valueOf(text);
            } catch (Exception e) {
                return Definition;
            }
        }
    }

    public static enum Action { 
        Add, Modify, Delete;
        
        public static ArrayList<String> names() {
            Action[] values = values();
            ArrayList<String> list = new ArrayList<String>();
            for (int i=0; i<values.length; ++i) {
                list.add(values[i].name());
            }
            return list;
        }

        public static Action valueOfOrDefault(String text) {
            try {
                return valueOf(text);
            } catch (Exception e) {
                return Add;
            }
        }
    }
}
