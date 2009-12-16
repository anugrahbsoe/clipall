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
* File      : HotKeyLogic.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;
import dev.clipall.Constants;
import dev.clipall.view.SearchFrame;

/**
 *
 * @author erol
 */
public class HotKeyLogic implements HotkeyListener {

    private static HotKeyLogic logic = new HotKeyLogic();

    private HotKeyLogic(){}

    public static HotKeyLogic getInstance(){
        return logic;
    }

    //------------------------------------------------------
    
    public void onHotKey(int keyIdentifier) {

        System.out.println("key pressed");
        if(Constants.Hotkeys.WIN_A == keyIdentifier) {
            System.out.println("ctrl + i  : is pressed");
            GenericMediator.getInstance().displaySearchPanel();
        }
    }

    public void initializeHotKeyListener(String appTitle){
        
        JIntellitype.getInstance();

        if(JIntellitype.checkInstanceAlreadyRunning(appTitle)){
            System.out.println("An instance of this application is already running.");
            //System.exit(1);
        }
        
        JIntellitype.getInstance().addHotKeyListener(this);
        
        // assign global hotkeys
        //JIntellitype.getInstance().registerSwingHotKey(Constants.Hotkeys.CTRL_i, Event.CTRL_MASK + Event.SHIFT_MASK, (int)'k');
        JIntellitype.getInstance().registerHotKey(Constants.Hotkeys.WIN_A, JIntellitype.MOD_WIN, (int)'A');
    }

    public void cleanup(){
        JIntellitype.getInstance().unregisterHotKey(Constants.Hotkeys.WIN_A);
        JIntellitype.getInstance().cleanUp();
    }
}
