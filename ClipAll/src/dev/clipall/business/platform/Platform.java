/**
* This file is part of dev.clipall.
*
* dev.clipall is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.clipall.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : Platform.java
* Created   : Nov 2009
*/

package dev.clipall.business.platform;

import dev.clipall.Constants;


/**
 *
 * @author erol
 */
public abstract class Platform extends Constants {

    private static Platform platform;

    static
    {
        try {
            
            platform = (Platform) Class.forName(PLATFORM_IMPL).newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Platform getInstance(){
        return platform;
    }

    public abstract void paste();
}
