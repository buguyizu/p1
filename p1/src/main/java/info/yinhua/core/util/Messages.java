package info.yinhua.core.util;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import info.yinhua.core.CommonConst;

//https://murygin.wordpress.com/2010/04/23/parameter-substitution-in-resource-bundles/
public class Messages {
	
	private static final String BUNDLE_NAME = "message";
    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME, CommonConst.LOCALE);
    
    public static String getString(String key) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (MissingResourceException e) {
        	e.printStackTrace();
            return '!' + key + '!';
        }
    }
    public static String getString(String key, Object... params) {
        try {
            return MessageFormat.format(RESOURCE_BUNDLE.getString(key), params);
        } catch (MissingResourceException e) {
        	e.printStackTrace();
            return '!' + key + '!';
        }
    }
}
