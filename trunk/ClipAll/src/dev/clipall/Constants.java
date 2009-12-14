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
* File      : Constants.java
* Created   : Nov 2009
*/

package dev.clipall;

import dev.clipall.business.GenericLogic;

/**
 *
 * @author Erol Hira
 */
public class Constants {
    
    public static final int     MAX_ITEM_COUNT_IN_CATEGORY = 5; // TODO use this
    public static final String  DEFAULT_HISTORY_FILE = "clipall_history.xml";
    public static final String  DEFAULT_CATEGORY_NAME = "Default";
    public static final char    FILES_ITEM_SEPERATOR = ';';
    public static final String  PLATFORM_IMPL = "dev.clipall.business.platform.DefaultPlatform";
    public static final String  AUTO_START_IMPL = "dev.clipall.business.autostart.AutoStartWin32Impl";

    public static final String  LOG_FILE = "log.txt";
    public static final String  DEFAULT_APP_FILE_NAME = "ClipAll.jar";
    public static final String  APP_LAUNCHER_FILE = GenericLogic.getInstance().launcherFile();

    public static final String  UPDATE_PAGE = "http://code.google.com/p/clipall/downloads/list";
    public static final String  HOME_PAGE   = "http://code.google.com/p/clipall";

    public class Resources {

        public static final String SYSTEM_TRAY_ICON = "/resources/images/console.png";
    }

    public class Win32 {

        public class Registry {

            public static final String APP_ROOT = "SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Run";
            public static final String AUTO_START_KEY_NAME = "ClipAll"; // true,false(DWORD)
            public static final String AUTO_STAR_KEY = APP_ROOT + "\\" + AUTO_START_KEY_NAME;
        }
    }

    public class FlavorTypes {
        
        public static final int STRING = 100;
        public static final int FILE = 101;
    }

    public class Hotkeys {

        public static final int WIN_A = 1;
    }
}
