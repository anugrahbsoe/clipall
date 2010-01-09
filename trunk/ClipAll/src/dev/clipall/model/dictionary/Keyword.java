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
* File      : Keyword.java
* Created   : Jan 7, 2010, 8:14:49 PM
*/

package dev.clipall.model.dictionary;

import javax.xml.bind.annotation.XmlType;

/**
 * @author Erol Hira
 */

@XmlType(propOrder={"name", "label", "desc", "childList"})
public class Keyword {

    private String name;
    private String label;
    private String desc;
    private KeywordList childList;

    public Keyword(){
        childList = new KeywordList();
    }

    public Keyword(String name, String label, String desc){
        this();
        this.name = name;
        this.label = label;
        this.desc = desc;
    }

    //-------------------------------------------

    public int countChildKeywords(){
        return childList.size();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Keyword other = (Keyword) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        if ((this.label == null) ? (other.label != null) : !this.label.equals(other.label)) {
            return false;
        }
        if ((this.desc == null) ? (other.desc != null) : !this.desc.equals(other.desc)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        hash = 67 * hash + (this.label != null ? this.label.hashCode() : 0);
        hash = 67 * hash + (this.desc != null ? this.desc.hashCode() : 0);
        return hash;
    }

    //-------------------------------------------

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

    public KeywordList getChildList() {
        return childList;
    }

    public void setChildList(KeywordList childList) {
        this.childList = childList;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
}
