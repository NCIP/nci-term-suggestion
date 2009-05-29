package gov.nih.nci.evs.newtermform.common;

/**
 * Application constants class
 * @author David Yee
 */
public interface Constants {

    // Application version
    static public final int MAJOR_VER = 1;
    static public final int MINOR_VER = 0;
    static public final String CONFIG_FILE = "NewTermProperties.xml";

    // Application error constants
    static public final String INIT_PARAM_ERROR_PAGE = "errorPage";
    static public final String ERROR_MESSAGE = "systemMessage";
    static public final String ERROR_UNEXPECTED = "Warning: An unexpected processing error has occurred.";  
}
