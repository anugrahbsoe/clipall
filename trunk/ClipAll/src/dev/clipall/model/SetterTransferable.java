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
* File      : SetterTransferable.java
* Created   : Nov 2009
*/

package dev.clipall.model;

import dev.clipall.Constants;
import dev.clipall.utils.Utils;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author erol
 */
public class SetterTransferable implements Transferable {

    private String string;
    private List<File> files;
    private int type = Constants.FlavorTypes.STRING;

    public SetterTransferable(Object item, int type) throws Exception{

        this.type = type;

        if(Constants.FlavorTypes.STRING == type){
            string = (String) item;
        } else if(Constants.FlavorTypes.FILE == type){
            files = (List<File>) item;
        } else {
            throw new Exception("invalid type");
        }
    }

    public SetterTransferable(Item item) throws Exception {

        this(Utils.extractObjectFromItem(item), item.getType());
    }

    //-------------------------------------------------------------
    
    public DataFlavor[] getTransferDataFlavors() {

        if(Constants.FlavorTypes.STRING == type){
            return new DataFlavor[]{ DataFlavor.stringFlavor };
        } else {
            return new DataFlavor[]{ DataFlavor.javaFileListFlavor };
        }
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        
        return  DataFlavor.javaFileListFlavor.equals(flavor) ||
                DataFlavor.stringFlavor.equals(flavor);
    }

    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {

        if(Constants.FlavorTypes.STRING == type){
            return string;
        } else {
            return files;
        } 
    }

    //-------------------------------------------------------------

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}
