package gov.nih.nci.evs.browser.utils;

import java.util.*;

public class Prop {
    public static enum MODIFIABLE_PROPERTY { 
        Definition, Synonym, Others;
        
        public static MODIFIABLE_PROPERTY valueOfOrDefault(String text) {
            try {
                return valueOf(text);
            } catch (Exception e) {
                return Definition;
            }
        }
    }

    public static enum PROPERTY_ACTION { 
        Add, Modify, Delete;
        
        public static ArrayList<String> names() {
            PROPERTY_ACTION[] values = values();
            ArrayList<String> list = new ArrayList<String>();
            for (int i=0; i<values.length; ++i) {
                list.add(values[i].name());
            }
            return list;
        }

        public static PROPERTY_ACTION valueOfOrDefault(String text) {
            try {
                return valueOf(text);
            } catch (Exception e) {
                return Add;
            }
        }
    }
}
