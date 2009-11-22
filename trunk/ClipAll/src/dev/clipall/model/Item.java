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
* File      : Item.java
* Created   : Nov 2009
*/

package dev.clipall.model;

import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author erol
 */

@XmlType
public class Item {

    private int type;

    /**
     *  if file list : 
     *  item = "C:\tmp\file1;D:\music\mozart.mp3";
     */
    private String item;

    private String bookmark;
    

    public Item(){}

    public Item(String bookmark) {
        this.bookmark = bookmark;
    }

    public Item(dev.clipall.model.jaxb.Item jaxbItem){
        this.type = jaxbItem.getType();
        this.item = jaxbItem.getItem();
        this.bookmark = jaxbItem.getBookmark();
    }
    
    public Item(String bookmark, String item, int type) {
        this.bookmark = bookmark;
        this.type = type;
        this.item = item;
    }
    
    //----------------------------------------------------------------

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.type != other.type) {
            return false;
        }
        if ((this.item == null) ? (other.item != null) : !this.item.equals(other.item)) {
            return false;
        }
        if ((this.bookmark == null) ? (other.bookmark != null) : !this.bookmark.equals(other.bookmark)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + this.type;
        hash = 47 * hash + (this.item != null ? this.item.hashCode() : 0);
        hash = 47 * hash + (this.bookmark != null ? this.bookmark.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return item;
    }
    
    //----------------------------------------------------------------

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
    
}
