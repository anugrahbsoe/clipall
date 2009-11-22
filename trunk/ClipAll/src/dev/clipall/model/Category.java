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
* File      : Category.java
* Created   : Nov 2009
*/

package dev.clipall.model;

import dev.clipall.Constants;
import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author erol
 */

@XmlType(propOrder={"name", "items", "maxItemCount"})
public class Category {

    private String name;
    private LinkedList<Item> items;

    private int maxItemCount = Constants.MAX_ITEM_COUNT_IN_CATEGORY;
    
    private Category(){
        items = new LinkedList<Item>();
    }    

    public Category(String name){
        this();
        setName(name);
    }

    public Category(dev.clipall.model.jaxb.Category category){
        this();
        this.name = category.getName();
        this.maxItemCount = category.getMaxItemCount();
        setupCategoryFromJaxb(category);
    }

    public Category(String name, LinkedList<Item> items){
        this();
        this.name = name;
        this.items = items;
    }

    /**
     *  adds new item or resorts the contained item.
    */
    public void addItem(String bookmark, String itemString, int type){
        Item item = new Item(bookmark, itemString, type);
        items.remove(item);
        items.addFirst(item);
    }

    public void addItem(Item item){
        items.remove(item);
        items.addFirst(item);
    }

    public void moveItemTop(Item item){
        addItem(item);
    }

    public Item removeItem(int index){
        return items.remove(index);
    }

    public Item removeItem(Item item){
        return removeItem(items.indexOf(item));
    }

    public static Category createDefaultCategory(){
        Category category = new Category(Constants.DEFAULT_CATEGORY_NAME);
        return category;
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
        final Category other = (Category) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }
    
    //----------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMaxItemCount() {
        return maxItemCount;
    }

    public void setMaxItemCount(int maxItemCount) {
        this.maxItemCount = maxItemCount;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    private void setupCategoryFromJaxb(dev.clipall.model.jaxb.Category category) {

        List<dev.clipall.model.jaxb.Item> jaxbItemList = category.getItems();

        for(dev.clipall.model.jaxb.Item jaxbItem : jaxbItemList){
            Item item = new Item(jaxbItem);
            items.add(item);
        }
    }

}
