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
* File      : DefaultSystemTray.java
* Created   : Nov 2009
*/

package dev.clipall.business.systray;

import dev.clipall.Constants;
import dev.clipall.business.GenericLogic;
import dev.clipall.business.GenericMediator;
import dev.clipall.business.autostart.AutoStart;
import dev.utils.log.Logger;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * jdk6 implementation of system tray is used.
 * @author Erol Hira
 */
public class DefaultSystemTray {
    
    private TrayIcon trayIcon;
    private PopupMenu popupMenu;

    //------------------------------------------------------
    private static DefaultSystemTray systemTray = new DefaultSystemTray();

    private DefaultSystemTray(){}

    public static DefaultSystemTray getInstance(){
        return systemTray;
    }
    //------------------------------------------------------

    public void run() {

        if (SystemTray.isSupported()) {

            try {
                initSystemTray();
                SystemTray.getSystemTray().add(trayIcon);
            } catch (Exception ex) {
                Logger.getLogger().error("", ex, getClass());
            }

        } else {
            System.err.println("System tray is currently not supported.");
        }
    }

    private void initSystemTray() {

        popupMenu = new PopupMenu();
        
        Image image = null;
        try {
            image = Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constants.Resources.SYSTEM_TRAY_ICON));
        } catch(Exception ex){}

        MouseListener mouseListener = new MouseListener() {

            public void mouseClicked(MouseEvent e) {                
            }

            public void mouseEntered(MouseEvent e) {                
            }

            public void mouseExited(MouseEvent e) {                
            }

            public void mousePressed(MouseEvent e) {                
            }

            public void mouseReleased(MouseEvent e) {                
            }
        };

        addSearchPanelDisplayerItem();
        addUpdaterItem();
        addStartupSwitcherItem();
        addExitItem();

        trayIcon = new TrayIcon(image, "ClipAll - Clipboard History", popupMenu);

        ActionListener actionListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {                
                trayIcon.displayMessage("Check For New Version..",
                        Constants.UPDATE_PAGE,
                        TrayIcon.MessageType.INFO);
            }
        };

        trayIcon.setImageAutoSize(true);
        trayIcon.addActionListener(actionListener);
        trayIcon.addMouseListener(mouseListener);        
    }

    private void addStartupSwitcherItem(){

        final MenuItem defaultItem = new MenuItem("Run On Windows Start");

        ActionListener startupSwitcher = new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                boolean autostartEnabled = AutoStart.getInstance().autoStartEnabled();
                boolean changed = AutoStart.getInstance().setAutoStart(!autostartEnabled);

                if(changed){

                    if(autostartEnabled){
                        defaultItem.setLabel("Run On Windows Start");
                    } else {
                        defaultItem.setLabel("DONOT Run On Windows Start");
                    }
                }
            }
        };

        // init startup String
        boolean autostartEnabled = AutoStart.getInstance().autoStartEnabled();
        if(autostartEnabled){
            defaultItem.setLabel("DONOT Run On Windows Start");
        } else {
            defaultItem.setLabel("Run On Windows Start");
        }

        defaultItem.addActionListener(startupSwitcher);
        popupMenu.add(defaultItem);        
    }

    private void addUpdaterItem(){

        ActionListener updaterListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                GenericMediator.getInstance().runBrowser(Constants.HOME_PAGE);
            }
        };

        MenuItem defaultItem = new MenuItem("Check For Updates");
        defaultItem.addActionListener(updaterListener);        
        popupMenu.add(defaultItem);
    }

    private void addExitItem(){

        ActionListener exitListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {                
                GenericLogic.getInstance().exitApplication();
            }
        };

        MenuItem defaultItem = new MenuItem("Exit");
        defaultItem.addActionListener(exitListener);
        popupMenu.add(defaultItem);
    }

    private void addSearchPanelDisplayerItem() {

        ActionListener displayerListener = new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                GenericMediator.getInstance().displaySearchPanel();
            }
        };

        MenuItem defaultItem = new MenuItem("Search History");
        defaultItem.addActionListener(displayerListener);
        popupMenu.add(defaultItem);
    }
}
