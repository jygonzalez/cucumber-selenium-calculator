package constants;

import utils.PropertiesReader;

public final class FrameworkConstants {	    
	
	public static final String BROWSER = PropertiesReader.getValue("BROWSER");
    public static final String URL = PropertiesReader.getValue("URL");
    
    public static final int WAIT_DEFAULT_TIMEOUT = Integer.parseInt(PropertiesReader.getValue("WAIT_DEFAULT_TIMEOUT"));
    public static final int WAIT_IMPLICIT_TIMEOUT = Integer.parseInt(PropertiesReader.getValue("WAIT_IMPLICIT_TIMEOUT"));
    public static final int WAIT_EXPLICIT_TIMEOUT = Integer.parseInt(PropertiesReader.getValue("WAIT_EXPLICIT_TIMEOUT"));
    public static final int WAIT_PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesReader.getValue("WAIT_PAGE_LOAD_TIMEOUT"));
    
    private FrameworkConstants() {
		 
	}
}
