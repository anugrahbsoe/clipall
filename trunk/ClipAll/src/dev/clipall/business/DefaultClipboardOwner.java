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
* File      : DefaultClipboardOwner.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import dev.clipall.utils.Utils;
import dev.utils.log.Logger;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
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
public class DefaultClipboardOwner implements ClipboardOwner {

    private Clipboard systemClipboard;

    private static DefaultClipboardOwner owner = new DefaultClipboardOwner();

    public static DefaultClipboardOwner getInstance(){
        return owner;
    }

    private DefaultClipboardOwner() {

        systemClipboard  = Toolkit
				.getDefaultToolkit()
				.getSystemClipboard();
    }

    public void lostOwnership(Clipboard clipboard, Transferable contents) {

        try {
            Thread.sleep(100);
        } catch (InterruptedException ex) {
            Logger.getLogger().error("", ex, getClass());
        }

        Transferable tr = systemClipboard.getContents(this);
        try {

            if (tr.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {

                // FIXME java.lang.ClassCastException: java.lang.String cannot be cast to java.util.List
                List<File> list = (List<File>) tr.getTransferData(DataFlavor.javaFileListFlavor);

                GenericMediator.getInstance().itemsToClipboardEvent(list);
                System.out.println("files -->  " + Utils.filesToString(list));

            } else {

                GenericMediator.getInstance().itemsToClipboardEvent((String)tr.getTransferData(DataFlavor.stringFlavor));
                System.out.println("Processing: " + tr.getTransferData(DataFlavor.stringFlavor));
            }

        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger().error("", ex, getClass());
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
        regainOwnership(tr);
    }

    public void regainOwnership(Transferable t) {
        systemClipboard.setContents(t, this);
    }
}
