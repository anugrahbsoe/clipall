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
* File      : ExtensionFilter.java
* Created   : October 24, 2007
*/

package dev.utils.io;

import java.io.File;
import java.io.FileFilter;

/**
 * if the file has its extension as the <extension>,
 * then returns true.
 *
 * @author Erol Hira
 */
public class ExtensionFilter implements FileFilter {

    private String extension;

    public ExtensionFilter(String extension) {
        this.extension = extension;
    }

    public boolean accept(File pathname) {

        String fileName = pathname.getName();
        String ext = "." + extension;
        int extensionIndex  = fileName.length() - ext.length();
        
        if(extensionIndex < 0 || fileName.substring(extensionIndex).equals(ext) == false){
            return false;
        } else {
            return true;
        }
    }
}
