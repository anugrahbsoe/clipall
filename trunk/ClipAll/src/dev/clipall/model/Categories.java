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
* File      : Categories.java
* Created   : Nov 2009
*/

package dev.clipall.model;

import java.util.LinkedList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author erol
 */

@XmlRootElement
public class Categories {

    private LinkedList<Category> categories;

    public Categories(){
        categories = new LinkedList<Category>();
    }    

    public Categories(dev.clipall.model.jaxb.Categories categories){
        this();
        setupCategoriesFromJaxb(categories);
    }

    public Category addNewCategory(Category category){
        
        return addNewCategory(category, false);
    }

    public Category addNewCategory(Category category, boolean overwrite){
        
        if(categories.contains(category)){
            if(overwrite){
                categories.set(categories.indexOf(category), category);
            } else {
                category = categories.get(categories.indexOf(category));
            }
        } else {
            categories.add(category);
        }

        return category;
    }

    public Category deleteCategory(Category category){

        categories.remove(category);
        if(categories.isEmpty()){
            addNewCategory(Category.createDefaultCategory());
        }

        return category;
    }

    public static Categories createDefaultInstance() {
        Categories defaultCategories = new Categories();
        defaultCategories.addNewCategory(Category.createDefaultCategory());
        return defaultCategories;
    }

    private void setupCategoriesFromJaxb(dev.clipall.model.jaxb.Categories jaxbCategories) {

        List<dev.clipall.model.jaxb.Category> jaxbCategoryList = jaxbCategories.getCategories();

        for(dev.clipall.model.jaxb.Category jaxbCategory : jaxbCategoryList){
            Category category = new Category(jaxbCategory);
            categories.add(category);
        }
    }

    //------------------------------------------------------

    public Categories(LinkedList<Category> categories){
        this.categories = categories;
    }

    public LinkedList<Category> getCategories() {
        return categories;
    }

    public void setCategories(LinkedList<Category> categories) {
        this.categories = categories;
    }
    
}
