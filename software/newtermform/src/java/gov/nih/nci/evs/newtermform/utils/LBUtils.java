package gov.nih.nci.evs.newtermform.utils;

import java.util.*;

public class LBUtils {
    public static enum MODIFIABLE_PROPERTY { 
        DEFINITION("Definition"), SYNONYM("Synonym"), OTHERS("Others");
        
        private String _name = "";
        private void setName(String name) { _name = name; }
        public String getName() { return _name; }
        
        private MODIFIABLE_PROPERTY(String name){
            setName(name);
        }
        
        public static List<String> getNameList() {
            ArrayList<String> list = new ArrayList<String>();
            for (MODIFIABLE_PROPERTY item : values())
                list.add(item.getName());
            return list;
        }
    };
    
    public static enum PROPERTY_ACTION { Add, Modify, Delete }

    public static List<String> getVocabularyList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("BioC_0902D");
        list.add("CBO2007_06");
        list.add("CDC_0902D");
        list.add("CDISC_0902D");
        list.add("...");
        return list;
    }
    
    public static List<String> getPropertyList(MODIFIABLE_PROPERTY property) {
        ArrayList<String> list = new ArrayList<String>();
        if (property == MODIFIABLE_PROPERTY.DEFINITION) {
            list.add("Definition: A liquid tissue; its major function is to transport oxygen throughout the body. It also supplies the tissues with nutrients, removes waste products, and contains various components of the immune system defending the body against infection. Several hormones also travel in the blood.");
            list.add("CDISC Definition: A liquid tissue with the primary function of transporting oxygen and carbon dioxide. It supplies the tissues with nutrients, removes waste products, and contains various components of the immune system defending the body against infection.");
            list.add("NCI-GLOSS Definition: Blood circulating throughout the body.");
        } else if (property == MODIFIABLE_PROPERTY.SYNONYM) {
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
        return list;
    }
}
