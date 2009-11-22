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
* File      : CollectionFactory.java
* Created   : November 26, 2006, 8:18 PM
*/

package dev.utils.collections;

import dev.utils.collections.fifo.FifoAdapter;
import dev.utils.collections.fifo.FifoList;
import dev.utils.collections.fifo.SynchronizedFifoAdapter;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class CollectionFactory {
        
    public CollectionFactory() {
    }
    
    public static FifoList getFifoList(){
        return new FifoAdapter();
    }
    
    public static FifoList getSynchronizedFifoList(){
        return new SynchronizedFifoAdapter();
    }
    
    public static CircularBinaryList getCircularBinaryList(){
        return new CircularBinaryList();
    }
    
}
