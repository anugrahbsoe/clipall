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
* File      : ExtendedItem.java
* Created   : December 19, 2009
*/

package dev.clipall.model;

/**
 *
 * @author Erol Hira
 */
public class ExtendedItem extends Item {

    private Category category;

    public ExtendedItem(Item item) {
        super(item.getBookmark(), item.getItem(), item.getType());
    }

    public ExtendedItem(Item item, Category category) {
        this(item);
        this.category = category;
    }

    public ExtendedItem(dev.clipall.model.jaxb.Item jaxbItem) {
        super(jaxbItem);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ExtendedItem other = (ExtendedItem) obj;
        if (this.type != other.type) {
            return false;
        }
        if ((this.item == null) ? (other.item != null) : !this.item.equals(other.item)) {
            return false;
        }
        if ((this.bookmark == null) ? (other.bookmark != null) : !this.bookmark.equals(other.bookmark)) {
            return false;
        }
        if (this.category != other.category && (this.category == null || !this.category.equals(other.category))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.category != null ? this.category.hashCode() : 0);
        hash = 47 * hash + this.type;
        hash = 47 * hash + (this.item != null ? this.item.hashCode() : 0);
        hash = 47 * hash + (this.bookmark != null ? this.bookmark.hashCode() : 0);
        return hash;
    }

    

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
}
