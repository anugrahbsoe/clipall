/**
* This file is part of dev.clipall.
*
* dev.clipall is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.clipall.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : DefaultPlatform.java
* Created   : Nov 2009
*/

package dev.clipall.business.platform;

import dev.clipall.view.SearchFrame;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/**
 *
 * @author erol
 */
public class DefaultPlatform extends Platform {


    private Robot robot;

    private final int ROBOT_DELAY = 180;

    //------------------------------------------------------


    public DefaultPlatform(){
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            // TODO  display a message with JOptionPane; centralize this type of messages.
        }
    }

    //------------------------------------------------------

    @Override
    public void paste() {

        SearchFrame.getInstance().hideSearchFrame();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                try {
                    Thread.sleep(ROBOT_DELAY);
                } catch (InterruptedException ex) {
                }
                
                robot.keyPress(KeyEvent.VK_CONTROL);
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_CONTROL);
            }
        });
    }

}
