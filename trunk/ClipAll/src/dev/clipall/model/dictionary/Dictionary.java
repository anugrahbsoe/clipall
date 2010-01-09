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
* File      : Dictionary.java
* Created   : Jan 7, 2010, 8:11:10 PM
*/

package dev.clipall.model.dictionary;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Erol Hira
 */

@XmlRootElement
public class Dictionary {

    private String name;
    private String desc;
    private ArrayList<String> prefixChars;
    private ArrayList<String> postfixChars;
    private Keyword rootKeyword;

    public Dictionary(){        
    }    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Keyword getRootKeyword() {
        return rootKeyword;
    }

    public void setRootKeyword(Keyword rootKeyword) {
        this.rootKeyword = rootKeyword;
    }

    public ArrayList<String> getPrefixChars() {
        return prefixChars;
    }

    public void setPrefixChars(ArrayList<String> prefixChars) {
        this.prefixChars = prefixChars;
    }

    public ArrayList<String> getPostfixChars() {
        return postfixChars;
    }

    public void setPostfixChars(ArrayList<String> postfixChars) {
        this.postfixChars = postfixChars;
    }
    
}
