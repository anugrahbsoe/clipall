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
* File      : GenericMediator.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import dev.clipall.Constants;
import dev.clipall.business.platform.Platform;
import dev.clipall.model.Category;
import dev.clipall.model.GenericModel;
import dev.clipall.model.Item;
import dev.clipall.utils.Utils;
import dev.clipall.view.SearchFrame;
import dev.clipall.view.SearchPanel;
import java.io.File;
import java.util.List;

/**
 *
 * @author erol
 */
public class GenericMediator extends Constants {
    
    private static GenericMediator mediator = new GenericMediator();
    
    private GenericMediator(){}
    
    public static GenericMediator getInstance(){
        return mediator;
    }
    
    //------------------------------------------------------
    
    public void setNextCategoryEvent(){
        SearchPanel.getInstance().updateSearchPanelFields();
    }
    
    public void setPreviousCategoryEvent(){
        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void setCurrentCategoryEvent(){
        SearchPanel.getInstance().updateSearchPanelFields();
    }   

    public void itemsToClipboardEvent(List<File> list){

        GenericModel.getInstance().getCurrentCategory()
                                        .addItem("", Utils.filesToString(list), FlavorTypes.FILE);

        SearchPanel.getInstance().updateJSearchList();
    }

    public void itemsToClipboardEvent(String item){
        Item itemObj = new Item("", item, FlavorTypes.STRING);
        itemsToClipboardEvent(itemObj);
    }

    public void itemsToClipboardEvent(Item item){
        GenericModel.getInstance().getCurrentCategory().addItem(item);
        SearchPanel.getInstance().updateJSearchList();
    }

    public void deleteItemEvent(Item item){
        GenericModel.getInstance().getCurrentCategory().removeItem(item);
        SearchPanel.getInstance().updateJSearchList();
    }

    public void enterPressedOnSearchListEvent(String searchedItem) {

        Item selectedItem = GenericModel.getInstance().
                getSearchListItemsOfCurrentCategory(searchedItem).get(
                            SearchPanel.getInstance().getSelectedItemIndex());
                            
        itemsToClipboardEvent(selectedItem);

        ClipboardLogic.getInstance().setClipboard(selectedItem);
        
        Platform.getInstance().paste();
    }

    public void createNewCategory(String categoryName) {

        Category category = new Category(categoryName);
        category = GenericModel.getInstance().getCategories().addNewCategory(category);
        GenericModel.getInstance().setCurrentCategory(category);

        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void deleteCurrentCategory(){

        Category category = GenericModel.getInstance().getCurrentCategory();
        GenericModel.getInstance().getCategories().deleteCategory(category);

        category = GenericModel.getInstance().getFirstCategory();
        GenericModel.getInstance().setCurrentCategory(category);

        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void displaySearchPanel(){
        SearchFrame.getInstance().displaySearchFrame();
    }
    
}
