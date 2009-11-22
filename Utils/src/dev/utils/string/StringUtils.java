/**
* This file is part of dev.utils.
*
* dev.utils is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.utils.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : StringUtils.java
* Created   : December 2, 2006, 12:07 AM
*/

package dev.utils.string;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class StringUtils {
    
    public static String trimLeft(String string, char trim){
        int trimCount = 0;
        for(int i = 0; i < string.length(); i++){
            if(string.charAt(i) == trim){
                trimCount++;
            }else{
                break;
            }            
        }
        
        return string.substring(trimCount);
    }
    
    
    public static String trimRight(String string, char trim){
        int trimCount = 0;
        int length = string.length();
        for(int i = length-1; i >= 0; i-- ){
            if(string.charAt(i) == trim){
                trimCount++;
            }else{
                break;
            }
        }
        
        return string.substring(0,length-trimCount);
    }
    
    
    public static String trim(String string, char trim){
       String trimLefted = StringUtils.trimLeft(string, trim);
       return StringUtils.trimRight(trimLefted, trim);
    }
    
}
