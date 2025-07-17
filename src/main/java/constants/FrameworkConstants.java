package constants;

import utils.ConfigPropertiesUtils;

public final class FrameworkConstants {	    
	
	public static final String BROWSER = ConfigPropertiesUtils.getValue("BROWSER");
    public static final String URL = ConfigPropertiesUtils.getValue("URL");
    
    public static final int WAIT_DEFAULT_TIMEOUT = Integer.parseInt(ConfigPropertiesUtils.getValue("WAIT_DEFAULT_TIMEOUT"));
    public static final int WAIT_IMPLICIT_TIMEOUT = Integer.parseInt(ConfigPropertiesUtils.getValue("WAIT_IMPLICIT_TIMEOUT"));
    public static final int WAIT_EXPLICIT_TIMEOUT = Integer.parseInt(ConfigPropertiesUtils.getValue("WAIT_EXPLICIT_TIMEOUT"));
    public static final int WAIT_PAGE_LOAD_TIMEOUT = Integer.parseInt(ConfigPropertiesUtils.getValue("WAIT_PAGE_LOAD_TIMEOUT"));
    
    private FrameworkConstants() {
		 
	}
}
