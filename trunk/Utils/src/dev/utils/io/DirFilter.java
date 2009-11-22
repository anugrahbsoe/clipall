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
* File      : DirFilter.java
* Created   : Mar 7, 2007  5:10:06 PM
*/

package dev.utils.io;

import java.io.File;
import java.io.FileFilter;

/**
 * @author Erol Hira
 *
 */
public class DirFilter implements FileFilter {

    /**
     * iff the pathname is a directory, returns true<br>
     * @see java.io.FileFilter#accept(java.io.File)
     */
    public boolean accept(File pathname) {
        if (pathname.isDirectory() &&
                !pathname.isHidden()) {
            return true;
        } else {
            return false;
        }
    }
}
