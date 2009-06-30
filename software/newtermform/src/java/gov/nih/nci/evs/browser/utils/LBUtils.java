package gov.nih.nci.evs.browser.utils;

import java.util.*;

public class LBUtils {
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
    }
    
    public static String[] getProperties(MODIFIABLE_PROPERTY property) {
        ArrayList<String> list = new ArrayList<String>();
        if (property == MODIFIABLE_PROPERTY.Definition) {
            list.add("Definition: A liquid tissue; its major function is to transport oxygen throughout the body. It also supplies the tissues with nutrients, removes waste products, and contains various components of the immune system defending the body against infection. Several hormones also travel in the blood.");
            list.add("CDISC Definition: A liquid tissue with the primary function of transporting oxygen and carbon dioxide. It supplies the tissues with nutrients, removes waste products, and contains various components of the immune system defending the body against infection.");
            list.add("NCI-GLOSS Definition: Blood circulating throughout the body.");
        } else if (property == MODIFIABLE_PROPERTY.Synonym) {
            list.add("Term: Blood; Source: CTRM; Type: DN");      
            list.add("Term: BLOOD; Source: CDISC; Type: PT");  
            list.add("Term: Blood; Source: NCI; Type: PT");  
            list.add("Term: Blood; Source: CDISC; Type: SY");  
            list.add("Term: peripheral blood; Source: NCI-GLOSS; Type: PT; Code:    CDR0000046011");
            list.add("Term: Peripheral Blood; Source: CTRM; Type: SY");
            list.add("Term: Peripheral Blood; Source: NCI; Type: SY");  
            list.add("Term: Reticuloendothelial System, Blood; Source: CTRM; Type: SY");  
            list.add("Term: Reticuloendothelial System, Blood; Source: NCI; Type: SY");
        }
        return list.toArray(new String[list.size()]);
    }
    
    public static String getProperty(MODIFIABLE_PROPERTY property, int index) {
        String[] list = getProperties(property);
        if (index >= 0 && index < list.length)
            return list[index];
        return "";
    }
}
