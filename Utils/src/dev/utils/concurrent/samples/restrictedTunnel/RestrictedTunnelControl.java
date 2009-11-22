/*
 * RestrictedTunnelControl.java
 *
 * Created on November 5, 2006, 1:33 PM
 */

package dev.utils.concurrent.samples.restrictedTunnel;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Erol Hira
 */
public class RestrictedTunnelControl {
    
    private Semaphore tunnelControl;
    private int maxAllowed;
    
    /** Creates a new instance of RestrictedTunnelControl */
    public RestrictedTunnelControl(int maximumAllowed) {
      maxAllowed = maximumAllowed;
      tunnelControl = new Semaphore(maxAllowed);
    }

    public void enterProtocol() {
      while(true) {
        try {
          tunnelControl.acquire();
          break;
        } catch(InterruptedException ie) {
            // No action needed in this example.
        }
      }
    }

    public void exitProtocol() {
      tunnelControl.release();
    }
    
}
