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
* File      : AutoStart.java
* Created   : Nov 2009
*/

package dev.clipall.business.autostart;

import dev.clipall.Constants;

/**
 *
 * @author Erol Hira
 */
public abstract class AutoStart {

    private static AutoStart autoStart;

    static
    {
        try {

            autoStart = (AutoStart) Class.forName(Constants.AUTO_START_IMPL).newInstance();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static AutoStart getInstance(){
        return autoStart;
    }

    public abstract boolean autoStartEnabled();

    public abstract boolean setAutoStart(boolean enable);
}
