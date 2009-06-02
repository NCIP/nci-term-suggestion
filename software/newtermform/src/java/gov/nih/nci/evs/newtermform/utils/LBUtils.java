package gov.nih.nci.evs.newtermform.utils;

import java.util.*;

public class LBUtils {
    public static enum MODIFIABLE_PROPERTIES { 
        DEFINITION("Definition"), SYNONYM("Synonym"), OTHERS("Others");
        
        private String _name = "";
        private void setName(String name) { _name = name; }
        public String getName() { return _name; }
        
        private MODIFIABLE_PROPERTIES(String name){
            setName(name);
        }
        
        public static List<String> getNameList() {
            ArrayList<String> list = new ArrayList<String>();
            for (MODIFIABLE_PROPERTIES item : values())
                list.add(item.getName());
            return list;
        }
    };
    
    public static List<String> getVocabularyList() {
        ArrayList<String> list = new ArrayList<String>();
        list.add("BioC_0902D");
        list.add("CBO2007_06");
        list.add("CDC_0902D");
        list.add("CDISC_0902D");
        list.add("...");
        return list;
    }
}
