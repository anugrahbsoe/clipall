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
* File      : ClipboardLogic.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import dev.clipall.model.Item;
import dev.clipall.model.SetterTransferable;
import dev.utils.log.Logger;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;

/**
 *
 * @author erol
 */
public class ClipboardLogic {

    private static ClipboardLogic logic = new ClipboardLogic();

    private ClipboardLogic(){}

    public static ClipboardLogic getInstance(){
        return logic;
    }

    //------------------------------------------------------

    public void startToListen(){

        Clipboard systemClipboard = Toolkit
				.getDefaultToolkit()
				.getSystemClipboard();
        
        Transferable trans = systemClipboard.getContents(DefaultClipboardOwner.getInstance());
        DefaultClipboardOwner.getInstance().regainOwnership(trans);
    }

    public void setClipboard(Item item){

        try {
            DefaultClipboardOwner.getInstance().regainOwnership(new SetterTransferable(item));
        } catch (Exception ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }
}
