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
* File      : KeywordList.java
* Created   : Jan 7, 2010, 8:14:28 PM
*/

package dev.clipall.model.dictionary;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Erol Hira
 */

@XmlType
public class KeywordList {

    private ArrayList<Keyword> keywords;

    public KeywordList(){
        keywords = new ArrayList<Keyword>();
    }

    //-------------------------------------------

    public void addKeyword(String name, String label, String desc){
        Keyword keyword = new Keyword(name, label, desc);
        addKeyword(keyword);
    }

    public void addKeyword(Keyword keyword){
        if(keywords.contains(keyword) == false){
            keywords.add(keyword);
        }
    }

    public int size(){
        return keywords.size();
    }

    //-------------------------------------------

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }
    
}
