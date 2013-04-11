/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.properties;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;

/**
 * 
 */

/**
 * @author EVS Team (David Yee)
 * @version 1.0
 */

public class PropertyFileParser {
    private HashMap<String, String> _configurableItemMap;
    private String _xmlfile;
    private Document _dom;

    public PropertyFileParser() {
        _configurableItemMap = new HashMap<String, String>();
    }

    public PropertyFileParser(String xmlfile) {
        _configurableItemMap = new HashMap<String, String>();
        _xmlfile = xmlfile;
    }

    public void run() {
        parseXmlFile(_xmlfile);
        parseDocument();
    }

    public HashMap<String, String> getConfigurableItemMap() {
        return _configurableItemMap;
    }

    private void parseXmlFile(String xmlfile) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            _dom = db.parse(xmlfile);
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (SAXException se) {
            se.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    private void parseDocument() {
        Element docEle = _dom.getDocumentElement();
        NodeList list = docEle.getElementsByTagName("ConfigurableItem");
        if (list != null && list.getLength() > 0) {
            for (int i = 0; i < list.getLength(); i++) {
                Element el = (Element) list.item(i);
                getConfigurableItem(el);
            }
        }
    }

    private void getConfigurableItem(Element displayItemElement) {
        String key = getTextValue(displayItemElement, "key");
        String value = getTextValue(displayItemElement, "value");
        _configurableItemMap.put(key, value);
    }

    private String getTextValue(Element ele, String tagName) {
        String textVal = null;
        NodeList nl = ele.getElementsByTagName(tagName);
        if (nl != null && nl.getLength() > 0) {
            Element el = (Element) nl.item(0);
            if (el == null || el.getFirstChild() == null)
                return "";
            textVal = el.getFirstChild().getNodeValue();
        }
        return textVal = textVal.trim();
    }

//    private boolean getBooleanValue(Element ele, String tagName) {
//        String textVal = getTextValue(ele, tagName);
//        if (textVal.compareToIgnoreCase("true") == 0)
//            return true;
//        return false;
//    }

    public static void main(String[] args) {
        PropertyFileParser parser = new PropertyFileParser();
        parser.run();
    }
}
