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
* File      : SearchItemLogic.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import dev.clipall.model.Category;
import dev.clipall.model.GenericModel;
import dev.clipall.model.Item;
import java.util.ArrayList;
import java.util.List;

/**
 *  This is for search items by filtering.
 *
 * @author erol
 */
public class SearchItemLogic {

    private static SearchItemLogic logic = new SearchItemLogic();

    private SearchItemLogic(){}

    public static SearchItemLogic getInstance(){
        return logic;
    }

    //------------------------------------------------------

    public List<Item> queryTheCurrentCategory(String string){

        Category category = GenericModel.getInstance().getCurrentCategory();
        
        return filterList(category.getItems(), string);
    }

    public List<Item> queryAllCategories(String string){

        List<Item> filteredItems = new ArrayList<Item>();

        List<Category> categories = GenericModel.getInstance().getCategories().getCategories();
        
        for(Category category : categories){
            filteredItems.addAll(filterList(category.getItems(), string));
        }

        return filteredItems;
    }

    //------------------------------------------------------

    /**
     *  CHECK if returns empty list
     */
    public List<Item> filterList(List<Item> items, String string){

        List<Item> filteredItems = new ArrayList<Item>();

        for(Item item : items){
            if(item.getItem().contains(string)){
                filteredItems.add(item);
            }
        }

        return filteredItems;
    }

    
}
