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
* File      : HiddenFilter.java
* Created   : October 24, 2007
*/

package dev.utils.io;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Erol Hira
 *
 */
public class HiddenFilter implements FileFilter {

    /**
     *  true : only to return hidden files
     *  false: only to return non-hidden files
     */
    private boolean hiddenFiles = true;

    public HiddenFilter(){        
    }

    public HiddenFilter(boolean hiddenFiles){
        this.hiddenFiles = hiddenFiles;
    }

    /*
     * if the file is a hidden file, eliminates it<br>
     * @see java.io.FileFilter#accept(java.io.File)
     */
    public boolean accept(File pathName) {
        if (pathName.isHidden()) {
            return hiddenFiles;
        } else {
            return !hiddenFiles;
        }
    }
}
