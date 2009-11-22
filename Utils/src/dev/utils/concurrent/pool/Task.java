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
* File      : Task.java
* Created   : November 26, 2006, 10:09 PM
*/

package dev.utils.concurrent.pool;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public interface Task {
    public void perform();
}
