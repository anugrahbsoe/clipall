/**
* File      : PropertiesUtils.java
* Created   : Dec 25, 2009, 11:19:04 PM
*/

package dev.utils.resources;

import java.util.Properties;

/**
 * @author Erol Hira
 */
public class PropertiesUtils {

    public static int getIntValue(String key, int defaultValue, Properties props){
        String value = props.getProperty(key);
        return value != null ? Integer.parseInt(value) : defaultValue;
    }

    public static long getLongValue(String key, long defaultValue, Properties props){
        String value = props.getProperty(key);
        return value != null ? Long.parseLong(value) : defaultValue;
    }

    public static boolean getBooleanValue(String key, boolean defaultValue, Properties props){
        String value = props.getProperty(key);
        return value != null && value.equalsIgnoreCase("true") ? true : defaultValue;
    }

    public static String getStringValue(String key, String defaultValue, Properties props){
        String value = props.getProperty(key);
        return value != null ? value : defaultValue;
    }    
}
