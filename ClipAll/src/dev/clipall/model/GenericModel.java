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
* File      : GenericModel.java
* Created   : Nov 2009
*/

package dev.clipall.model;

import dev.clipall.business.GenericMediator;
import dev.clipall.business.SearchItemLogic;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author erol
 */
public class GenericModel {


    private Categories  categories;
    private Category    currentCategory;
    private List<Item>  searchListItems; // items that will be displayed in SearchPanel

    //------------------------------------------------------

    private static GenericModel model = new GenericModel();

    private GenericModel(){}
    
    public static GenericModel getInstance(){
        return model;
    }
    
    //------------------------------------------------------

    public Category getFirstCategory(){
        return categories.getCategories().getFirst();
    }

    public Category getNextCategory(){
        LinkedList<Category> categoryList = categories.getCategories();
        int index = categoryList.indexOf(currentCategory);
        index = (++ index) % categoryList.size();
        return categoryList.get(index);
    }
    
    public Category getPreviousCategory(){
        LinkedList<Category> categoryList = categories.getCategories();
        int index = categoryList.indexOf(currentCategory);
        index --;
        index = index < 0 ? index + categoryList.size() : index;
        return categoryList.get(index);
    }

    public Category setNextCategory(){
        currentCategory = getNextCategory();
        GenericMediator.getInstance().setNextCategoryEvent();
        return currentCategory;
    }

    public Category setPreviousCategory(){
        currentCategory = getPreviousCategory();
        GenericMediator.getInstance().setPreviousCategoryEvent();
        return currentCategory;
    }

    public List<Item> getSearchListItemsOfCurrentCategory(String string) {

        if(string.length() == 0){
            return currentCategory.getItems();
        }

        return SearchItemLogic.getInstance().queryTheCurrentCategory(string);
    }
    
    //------------------------------------------------------

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
        currentCategory = categories.getCategories().getFirst();
        setNextCategory(); // for the event creation
    }

    public Category getCurrentCategory() {
        return currentCategory;
    }

    public void setCurrentCategory(Category currentCategory) {
        this.currentCategory = currentCategory;
    }

    public List<Item> getSearchListItems() {
        return searchListItems;
    }

    public void setSearchListItems(List<Item> searchListItems) {
        this.searchListItems = searchListItems;
    }
    
}
