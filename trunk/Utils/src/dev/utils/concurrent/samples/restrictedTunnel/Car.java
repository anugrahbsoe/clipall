/*
 * Car.java
 *
 * Created on November 5, 2006, 2:13 PM
 */

package dev.utils.concurrent.samples.restrictedTunnel;


/**
 *
 * @author Erol Hira
 */
public class Car extends Thread{
    
    private RestrictedTunnelControl controller;
    
    /** Creates a new instance of Car */
    public Car(RestrictedTunnelControl tc) {
      super();
      controller = tc;
    }

    public void run() {
      // Approach tunnel.

      controller.enterProtocol();
        // Drive through tunnel.
      controller.exitProtocol();
      // Continue journey.
    }
}
