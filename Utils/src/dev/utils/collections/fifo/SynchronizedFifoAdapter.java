/*
 * SynchronizedFifoAdapter.java
 * Created on November 26, 2006, 9:12 PM
 */

package dev.utils.collections.fifo;

import java.util.LinkedList;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class SynchronizedFifoAdapter implements FifoList{
    
    private LinkedList  list;
    
    /** Creates a new instance of SynchronizedFifoAdapter */
    public SynchronizedFifoAdapter() {
        list = new LinkedList();
    }

    public synchronized  void add(Object object) {
        list.addLast(object);
        notifyAll();
    }

    public synchronized Object get() {
        return list.getFirst();
    }

    public synchronized Object remove() throws InterruptedException {
        while(list.size() == 0){            
            wait();            
        }
        
        Object obj = list.removeFirst();
        notifyAll();
        return obj;
    }

    public synchronized int getSize() {
        return list.size();
    }
    
}
