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
* File      : FifoList.java
* Created   : November 25, 2006, 1:07 AM
*/

package dev.utils.collections.fifo;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public interface FifoList {
    public void add(Object object);
    public Object get();
    public Object remove() throws InterruptedException;
    public int getSize();
}
