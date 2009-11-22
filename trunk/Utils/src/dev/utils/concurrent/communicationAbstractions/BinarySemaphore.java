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
* File      : BinarySemaphore.java
* Created   : November 5, 2006, 2:17 PM
*/

package dev.utils.concurrent.communicationAbstractions;

/**
 *  use synchronized methods instead of binary semaphores. 
 * 
 *  @version 1.0
 *  @author Erol Hira
 */
public class BinarySemaphore extends Semaphore{
    
    /** Creates a new instance of BinarySemaphore */
    public BinarySemaphore(int initial) {
        super(initial);

        if(value > 1 | value < 0) throw new
            IllegalArgumentException(
                    "Binary semaphore must be initialized to 0 or 1");
    }

    
    public synchronized void release() {
        value = 1;
        notify();
    }
    
}
