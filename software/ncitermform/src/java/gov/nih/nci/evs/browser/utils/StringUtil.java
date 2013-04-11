/*L
 * Copyright Northrop Grumman Information Technology.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/nci-term-suggestion/LICENSE.txt for details.
 */

package gov.nih.nci.evs.browser.utils;

/**
 * 
 */

/**
 * Application constants class
 *
 * @author Kim Ong
 */
public class StringUtil {


    /**
     * Constructor
     */
    public StringUtil() {
        // Prevent class from being explicitly instantiated
    }

   public static String replaceSpecialCharacters(String s) {
	   if (s == null) return null;

	   s = s.replaceAll("&#40;", "(");
	   s = s.replaceAll("&#41;", ")");

	   s = s.replaceAll("&#38;", "&");
	   s = s.replaceAll("&amp;", "&");

	   s = s.replaceAll("&apos;", "'");
	   s = s.replaceAll("&#39;", "'");

	   s = s.replaceAll("&lt;", "<");
	   s = s.replaceAll("&gt;", ">");
	   s = s.replaceAll("&#60;", "<");
	   s = s.replaceAll("&#62;", ">");

	   s = s.replaceAll("&#34;", "\"");
	   s = s.replaceAll("&quot;", "\"");

	   return s;
   }


} // Class Constants
