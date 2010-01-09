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
* File      : DictionaryLogic.java
* Created   : Jan 7, 2010, 10:28:44 PM
*/

package dev.clipall.business;

import dev.clipall.Constants;
import dev.clipall.model.jaxb.dictionary.Dictionary;
import dev.utils.io.ExtensionFilter;
import dev.utils.io.FileHelper;
import dev.utils.log.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;

/**
 * @author Erol Hira
 */
public class DictionaryLogic {

    private static DictionaryLogic logic = new DictionaryLogic();

    private DictionaryLogic(){}

    public static DictionaryLogic getInstance(){
        return logic;
    }

    public LinkedList<Dictionary> loadDictionaries(){

        LinkedList<Dictionary> dictionaries = new LinkedList<Dictionary>();

        File[] dictFiles = FileHelper.getFilesInDir(Constants.DICTIONARY_DIR, new ExtensionFilter(Constants.DICTIONARY_FILE_EXT));

        if(dictFiles == null){ return dictionaries; }

        for(File file : dictFiles){
            
            Dictionary dict = loadDictionary(file);
            if(dict != null){
                dictionaries.add(dict);
            }
        }

        return dictionaries;
    }

    public Dictionary loadDictionary(File file){
        
        try {

            JAXBContext jc = JAXBContext.newInstance("dev.clipall.model.jaxb.dictionary");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement jaxbElement = (JAXBElement) unmarshaller.unmarshal(
                                                    new FileInputStream(file));

            Dictionary dictionary = (Dictionary) jaxbElement.getValue();

            return dictionary;

        } catch (Exception ex) {
            Logger.getLogger().debug("ex: " + ex.toString(), DictionaryLogic.class);            
        }

        return null;
    }
}
