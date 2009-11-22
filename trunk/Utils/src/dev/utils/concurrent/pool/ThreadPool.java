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
* File      : ThreadPool.java
* Created   : November 24, 2007
*/

package dev.utils.concurrent.pool;

import dev.utils.collections.CollectionFactory;
import dev.utils.collections.fifo.FifoList;
import dev.utils.log.Logger;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class ThreadPool {
    
    private FifoList idleWorkers;
    private ThreadPoolWorker[]  poolWorkers;
    
    /** Creates a new instance of ThreadPool */
    public ThreadPool() {
    }
    
    
    public ThreadPool(int numberOfThreads){
        numberOfThreads = Math.max(1, numberOfThreads);
        idleWorkers = CollectionFactory.getSynchronizedFifoList();
        
        poolWorkers = new ThreadPoolWorker[numberOfThreads];        
        for(int i = 0; i < poolWorkers.length; i++){
            poolWorkers[i] = new ThreadPoolWorker(idleWorkers);
        }
    }
    
    
    public void execute(Task task){
        try {
            ThreadPoolWorker poolWorker = (ThreadPoolWorker)idleWorkers.remove();
            poolWorker.process(task);
        } catch (InterruptedException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }
    
    
    public void stopRequestIdleWorkers(){
        for(int i = 0; i < idleWorkers.getSize(); i++){
            try {
                ThreadPoolWorker poolWorker = (ThreadPoolWorker)idleWorkers.remove();
                poolWorker.stopRequest();
            } catch (InterruptedException ex) { 
                Logger.getLogger().error("", ex, getClass());
            }                        
        }
    }
    
    
    public void stopRequestAllWorkers(){
        stopRequestIdleWorkers();
        
        try {
            //wait some for idles to die
            Thread.sleep(250);
        } catch (InterruptedException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
        
        for(int i = 0; i < poolWorkers.length; i++){
            if(poolWorkers[i].isAlive()){
                poolWorkers[i].stopRequest();
            }
        }        
    }
    
}
