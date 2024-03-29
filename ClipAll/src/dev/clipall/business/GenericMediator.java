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
import dev.clipall.model.ExtendedItem;
import dev.clipall.model.GenericModel;
import dev.clipall.model.Item;
import dev.clipall.utils.Utils;
import dev.clipall.view.SearchFrame;
import dev.clipall.view.SearchPanel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import dev.utils.collections.Reversed;
import dev.utils.io.FileHelper;
import dev.utils.log.Logger;
import dev.utils.net.URLEncoder;
import java.util.Arrays;

/**
 *
 * @author erol
 */
public class GenericMediator extends Constants {

    private static GenericMediator mediator = new GenericMediator();

    private GenericMediator() {
    }

    public static GenericMediator getInstance() {
        return mediator;
    }

    //------------------------------------------------------
    public void setNextCategoryEvent() {
        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void setPreviousCategoryEvent() {
        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void setCurrentCategoryEvent() {
        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void itemsToClipboardEvent(List<File> list) {

        GenericModel.getInstance().getCurrentCategory().addItem("", Utils.filesToString(list), FlavorTypes.FILE);

        SearchPanel.getInstance().updateJSearchList();
    }

    public Item itemsToClipboardEvent(String item) {
        Item itemObj = new Item("", item, FlavorTypes.STRING);
        return itemsToClipboardEvent(itemObj);
    }

    public Item itemsToClipboardEvent(Item item) {
        ExtendedItem exItem = new ExtendedItem(item);
        exItem.setCategory(GenericModel.getInstance().getCurrentCategory());
        GenericModel.getInstance().getCurrentCategory().addItem(exItem);
        SearchPanel.getInstance().updateJSearchList();
        return exItem;
    }

    public void deleteItemEvent(Item item) {
        //GenericModel.getInstance().getCurrentCategory().removeItem(item);
        ExtendedItem exItem = (ExtendedItem) item;
        exItem.getCategory().removeItem(exItem);
        SearchPanel.getInstance().updateJSearchList();
    }

    public void pasteItemWithoutOrder(Item item){
        ClipboardLogic.getInstance().setClipboard(item);
        Platform.getInstance().paste();
    }

    public void enterPressedForOneSelectedItem() {

        Item selectedItem = SearchPanel.getInstance().getSelectedItem();
        
        if (selectedItem == null) {
            return;
        }
        
        SerialPastingLogic.getInstance().setIndex(SearchPanel.getInstance().getSelectedItemIndex());
        SerialPastingLogic.getInstance().incrementIndex();

        itemsToClipboardEvent(selectedItem);
        ClipboardLogic.getInstance().setClipboard(selectedItem);        

        Platform.getInstance().paste();
    }

    public void enterPressedOnSearchListEvent() {
        enterPressedOnSearchListEvent(false);
    }

    public void enterPressedOnSearchListEvent(boolean reverseOrder) {

        Item[] items = SearchPanel.getInstance().getSelectedItems();

        if (items.length == 0) {
            return;
        }

        if (items.length == 1) {
            enterPressedForOneSelectedItem();
            return;
        }

        //Item item = itemsToClipboardEvent(itemsToString(items));
        Item item = new Item("", itemsToString(items, reverseOrder), FlavorTypes.STRING);

        ClipboardLogic.getInstance().setClipboard(item);

        Platform.getInstance().paste();
    }

    public String itemsToString(Item[] items, boolean reverseOrder) {

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        if (reverseOrder) {
            for (Item item : Reversed.reversed(Arrays.asList(items))) {
                pw.println(item.getItem());
            }
        } else {
            for (Item item : items) {
                pw.println(item.getItem());
            }
        }

        pw.flush();
        pw.close();

        return sw.toString();
    }

    public void createNewCategory(String categoryName) {

        Category category = new Category(categoryName);
        category = GenericModel.getInstance().getCategories().addNewCategory(category);
        GenericModel.getInstance().setCurrentCategory(category);

        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void deleteCurrentCategory() {

        Category category = GenericModel.getInstance().getCurrentCategory();
        GenericModel.getInstance().getCategories().deleteCategory(category);

        category = GenericModel.getInstance().getFirstCategory();
        GenericModel.getInstance().setCurrentCategory(category);

        SearchPanel.getInstance().updateSearchPanelFields();
    }

    public void displaySearchPanel() {
        //SearchPanel.getInstance().focusOnSearchTextField();
        SearchFrame.getInstance().displaySearchFrame();
    }

    public List<Item> getSearchListItems(String string) {

        if (SearchPanel.getInstance().isAllCategoriesSearch()) {
            return SearchItemLogic.getInstance().queryAllCategories(string);
        } else {
            return SearchItemLogic.getInstance().queryTheCurrentCategory(string);
        }
    }

    // TortoiseProc.exe /command:diff /path:"c:/mehmet_tel" /path2:"c:/kyk_ode"
    public void compareItems() {

        Item[] items = SearchPanel.getInstance().getSelectedItems();

        if (items.length != 2) {
            return;
        }

        String file1 = GenericLogic.getInstance().getTmpFile("f1");
        String file2 = GenericLogic.getInstance().getTmpFile("f2");

        FileHelper.createFile(file1, items[0].getItem());
        FileHelper.createFile(file2, items[1].getItem());

        String command = "cmd /c TortoiseProc.exe /command:diff" +
                " /path:" + "\"" + file1 + "\"" +
                " /path2:" + "\"" + file2 + "\"";
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }

    public void compareItemsWithDiffMerge() {

        Item[] items = SearchPanel.getInstance().getSelectedItems();

        if (items.length != 2) {
            return;
        }

        String file1 = GenericLogic.getInstance().getTmpFile("f1");
        String file2 = GenericLogic.getInstance().getTmpFile("f2");

        FileHelper.createFile(file1, items[0].getItem());
        FileHelper.createFile(file2, items[1].getItem());

        String command = "cmd /c diffmerge.exe" +
                " \"" + file1 + "\"" +
                " \"" + file2 + "\"";
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }

    public void runInConsole() {

        Item selectedItem = SearchPanel.getInstance().getSelectedItem();

        if (selectedItem == null) {
            return;
        }

        String command = "cmd /c" + selectedItem.getItem();
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }

    public void runBrowser(String url) {      
        
        String command = "cmd /c start /max " + url;
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
        
        SearchFrame.getInstance().hideSearchFrame();
    }

    public void openDirectory() {

        Item selectedItem = SearchPanel.getInstance().getSelectedItem();

        if (selectedItem == null) {
            return;
        }

        String command = "explorer /root, " + selectedItem.getItem();
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }

    public void google(String textToSearch) {
        String url = "http://www.google.com/search?q=" + textToSearch;
        url = URLEncoder.encode(url);
        //System.out.println("encoded url : " + url);
        runBrowser(url);
    }
}
