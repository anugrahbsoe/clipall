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
* File      : FifoAdapter.java
* Created   : November 25, 2006
*/

package dev.utils.collections.fifo;

import java.util.LinkedList;

/**
 *  This is the adapter class that implements FIFO queue <br>
 *  by adapting the LinkedList. 
 *  
 *  @version 1.0
 *  @author Erol Hira
 */
public class FifoAdapter implements FifoList{
    
    private LinkedList  list;
            
    /** Creates a new instance of FifoAdapter */
    public FifoAdapter() {
        list = new LinkedList();
    }
    
    
    public void add(Object object){
        list.addLast(object);
    }
    
    public Object get(){
        return list.getFirst();
    }
    
    public Object remove(){      
        return list.removeFirst();
    }

    public int getSize() {
        return list.size();
    }

}
