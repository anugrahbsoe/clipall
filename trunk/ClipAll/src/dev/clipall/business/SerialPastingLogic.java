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
* File      : SearchItemLogic.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import dev.clipall.model.Item;
import dev.clipall.view.SearchPanel;

/**
 *
 * @author Erol Hira
 */
public class SerialPastingLogic {

    private int index = 0;

    private static SerialPastingLogic logic = new SerialPastingLogic();

    private SerialPastingLogic(){}

    public static SerialPastingLogic getInstance(){
        return logic;
    }
    
    public void incrementIndex(){
        index = (++ index) % SearchPanel.getInstance().itemCount();
    }    
    
    public void pasteNext(){
        Item item = SearchPanel.getInstance().getItem(index);
        GenericMediator.getInstance().pasteItemWithoutOrder(item);
        incrementIndex();
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
