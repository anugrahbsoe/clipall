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
* File      : Semaphore.java
* Created   : November 5, 2006, 1:29 PM
*/

package dev.utils.concurrent.communicationAbstractions;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class Semaphore {
    
    protected int value;
    
    /** Creates a new instance of Semaphore */
    /**
     * initial : max value of semaphore
     */
    public Semaphore(int initial) {
        value = initial;
    }
    
    public synchronized void acquire ()
           throws InterruptedException {
      while (value == 0) wait();
      value--;
    }

    public synchronized void release () {
      value++;
      notify();
    }    
}
