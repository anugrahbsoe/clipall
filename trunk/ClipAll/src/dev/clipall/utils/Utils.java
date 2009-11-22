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
* File      : Utils.java
* Created   : Nov 2009
*/

package dev.clipall.utils;

import dev.clipall.Constants;
import dev.clipall.model.Item;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author erol
 */
public class Utils extends Constants {

    public static String filesToString(List<File> list){

        StringBuilder buf = new StringBuilder();

        for(File file : list){
            buf.append(file.getAbsolutePath());
            buf.append(FILES_ITEM_SEPERATOR);
        }

        String str = buf.toString();
        return str.substring(0, str.length() - 1);
    }

    public static List<File> stringToFiles(String fileItems){

        List<File> files = new ArrayList<File>();
        StringTokenizer tk = new StringTokenizer(fileItems, Character.toString(FILES_ITEM_SEPERATOR));
        
        while(tk.hasMoreTokens()){
            files.add(new File(tk.nextToken().trim()));
        }

        return files;
    }

    public static Object extractObjectFromItem(Item item){

        if(FlavorTypes.STRING == item.getType()){
            return item.getItem();
        } else {
            return stringToFiles(item.getItem());
        }
    }
}
