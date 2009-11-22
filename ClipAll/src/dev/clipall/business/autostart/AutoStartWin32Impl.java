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
* File      : AutoStartWin32Impl.java
* Created   : Nov 2009
*/

package dev.clipall.business.autostart;

import dev.clipall.Constants;
import dev.utils.log.Logger;
import dev.windows.registry.Key;
import dev.windows.registry.RegistryErrorException;
import dev.windows.registry.RegistryHelper;

/**
 *
 * @author Erol Hira
 */
public class AutoStartWin32Impl extends AutoStart {    

    @Override
    public boolean autoStartEnabled() {

        Key key = null;
        RegistryHelper reg = null;

        try {

            reg = new RegistryHelper();
            key = reg.openKey(RegistryHelper.HKEY_LOCAL_MACHINE, Constants.Win32.Registry.APP_ROOT);

            byte[] bytes = reg.readValue(key, Constants.Win32.Registry.AUTO_START_KEY_NAME);
            if(bytes == null){
                return false;
            } else {
                return true;
            }
            
        } catch (RegistryErrorException ex) {
            Logger.getLogger().error("", ex, getClass());
            return false;
        } finally {
            if(key != null){
                try {
                    reg.closeKey(key);
                } catch (RegistryErrorException ex) { }
            }
        }
    }

    @Override
    public boolean setAutoStart(boolean enable) {

        Key key = null;
        RegistryHelper reg = null;

        try {

            reg = new RegistryHelper();
            key = reg.openKey(RegistryHelper.HKEY_LOCAL_MACHINE, Constants.Win32.Registry.APP_ROOT);           
            
            if(enable){
                reg.setValue(key, Constants.Win32.Registry.AUTO_START_KEY_NAME, Constants.APP_LAUNCHER_FILE);
            } else {
                reg.delValue(key, Constants.Win32.Registry.AUTO_START_KEY_NAME);
            }

            return true;
            
        } catch (RegistryErrorException ex) {
            Logger.getLogger().error("", ex, getClass());
            return false;
        } finally {
            try {
                if(key != null){ reg.closeKey(key); }
            } catch (RegistryErrorException ex) {  }
        }

    }    
    
}
