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
* File      : ThreadPoolWorker.java
* Created   : November 26, 2006, 8:23 PM
*/

package dev.utils.concurrent.pool;

import dev.utils.collections.CollectionFactory;
import dev.utils.collections.fifo.FifoList;
import dev.utils.log.Logger;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class ThreadPoolWorker {
    
    private static int nextWorkerID = 0;
    private FifoList idleWorkers;
    private int workerID;
    private FifoList handOfBox;
    private Thread internalThread;
    private volatile boolean noStopRequested;
    
    /** Creates a new instance of ThreadPoolWorker */
    public ThreadPoolWorker(FifoList idleWorkers) {        
        this.idleWorkers = idleWorkers;
        workerID = getNextWorkerID();
        handOfBox = CollectionFactory.getSynchronizedFifoList();
        noStopRequested = true;
        
        
        Runnable runnable = new Runnable(){
            public void run(){
                runWork();
            }
        };
        
        internalThread = new Thread(runnable);
        internalThread.start();
    }
    
    
    public static synchronized int getNextWorkerID(){
        int id = nextWorkerID;
        nextWorkerID++;
        return id;
    }
    
    
    public void process(Task task){
        handOfBox.add(task);
    }
        
    
    private void runWork(){
        while(noStopRequested){
            idleWorkers.add(this);
            
            try {
                Task task = (Task) handOfBox.remove();
                task.perform();
            } catch (InterruptedException ex) {
                Logger.getLogger().error("", ex, getClass());
            }            
        }
    }
    
    
    public void stopRequest(){
        noStopRequested = false;
        internalThread.interrupt();
    }
    
    
    public boolean isAlive(){
        return internalThread.isAlive();
    }
    
}
