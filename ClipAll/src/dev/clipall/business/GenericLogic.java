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
* File      : GenericLogic.java
* Created   : Nov 2009
*/

package dev.clipall.business;

import dev.clipall.Constants;
import dev.clipall.model.Categories;
import dev.clipall.model.Category;
import dev.clipall.model.GenericModel;
import dev.clipall.model.Tasks;
import dev.clipall.model.jaxb.dictionary.Dictionary;
import dev.utils.io.FileHelper;
import dev.utils.log.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import javax.swing.UIManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.apache.log4j.Level;

/**
 *
 * @author erol
 */
public class GenericLogic {

    private static GenericLogic logic = new GenericLogic();

    private GenericLogic() {
    }

    public static GenericLogic getInstance() {
        return logic;
    }

    public void initAppSettings() {        
        
        try {
            
            Logger.getLogger().initLogger(Level.DEBUG, Constants.LOG_FILE);

            //set L&F
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
        } catch (Exception ex) {
            //Logger.getLogger().error("", ex, GenericLogic.class);
        }

        loadFromXML(Constants.DEFAULT_HISTORY_FILE);
        loadTasksFromXML(Constants.Tasks.DEFAULT_TASKS_FILE);

        loadDictionaries();       
    }

    public void loadFromXML(String historyFile) {

        Categories categories = newCategoriesInstance(historyFile);
        GenericModel.getInstance().setCategories(categories);
    }

    public void loadFromXML(File historyFile) {

        Categories categories = newCategoriesInstance(historyFile);
        GenericModel.getInstance().setCategories(categories);
    }

    public void loadTasksFromXML(String tasksFile){

        Tasks tasks = newTasksInstance(tasksFile);
        GenericModel.getInstance().setTasks(tasks);
    }    

    public Categories newCategoriesInstance(File historyFile){

        Categories categories = null;        

        try {

            JAXBContext jc = JAXBContext.newInstance("dev.clipall.model.jaxb");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement jaxbElement = (JAXBElement) unmarshaller.unmarshal(
                                            new FileInputStream(historyFile));

            dev.clipall.model.jaxb.Categories jaxbCategories =
                    (dev.clipall.model.jaxb.Categories) jaxbElement.getValue();

            categories = new Categories(jaxbCategories);

        } catch (Exception ex) {
            Logger.getLogger().debug("ex: " + ex.toString() + " -- " +
                                    "default categories is being created...",
                                    GenericLogic.class);

            categories = Categories.createDefaultInstance();
        }

        return categories;
    }

    public Categories newCategoriesInstance(String historyFile){
                
        if(historyFile == null){
            historyFile = Constants.DEFAULT_HISTORY_FILE;
        }

        return newCategoriesInstance(new File(historyFile));
    }

    public Tasks newTasksInstance(String tasksFile){

        Tasks tasks = null;

        try {

            JAXBContext jc = JAXBContext.newInstance("dev.clipall.model.jaxb.tasks");
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            JAXBElement jaxbElement = (JAXBElement) unmarshaller.unmarshal(
                                                new FileInputStream(tasksFile));

            dev.clipall.model.jaxb.tasks.Tasks jaxbTasks =
                    (dev.clipall.model.jaxb.tasks.Tasks) jaxbElement.getValue();

            tasks = new Tasks(jaxbTasks);

        } catch (Exception ex) {
            Logger.getLogger().debug("ex: " + ex.toString() + " -- " +
                                    "default tasks is being created...",
                                    GenericLogic.class);

            tasks = new Tasks();
        }

        return tasks;
    }

    private void loadDictionaries() {

        LinkedList<Dictionary> dicts = DictionaryLogic.getInstance().loadDictionaries();
        GenericModel.getInstance().setDictionaries(dicts);
    }

    public void saveTasks(Tasks tasks, File tasksFile){

        if(tasksFile == null){
            tasksFile = new File(Constants.Tasks.DEFAULT_TASKS_FILE);
        }

        try {
            JAXBContext jc = JAXBContext.newInstance(Tasks.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(tasks, tasksFile);
        } catch (JAXBException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }

    public void save(Categories categories, File historyFile){

        if(historyFile == null){
            historyFile = new File(Constants.DEFAULT_HISTORY_FILE);
        }

        try {
            JAXBContext jc = JAXBContext.newInstance(Categories.class);
            Marshaller m = jc.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            m.marshal(categories, historyFile);
        } catch (JAXBException ex) {
            Logger.getLogger().error("", ex, getClass());
        }
    }

    public void save(Categories categories, String historyFile){

        File file = null;
        if(historyFile != null){
            file = new File(historyFile);
        }

        save(categories, file);
    }

    public void saveAll(String historyFile) {

        save(GenericModel.getInstance().getCategories(), historyFile);
    }

    public void saveAll(File historyFile){
        save(GenericModel.getInstance().getCategories(), historyFile);
    }

    public void saveTheCurrentCategory(String historyFile){

        Categories categories = newCategoriesInstance(historyFile);
        Category currentCategory = GenericModel.getInstance().getCurrentCategory();
        categories.addNewCategory(currentCategory, true);
        
        save(categories, historyFile);
    }    

    private void setDefaultCategories() {

        Categories categories = Categories.createDefaultInstance();
        GenericModel.getInstance().setCategories(categories);
    }

    public void exitApplication(){

        HotKeyLogic.getInstance().cleanup();
        System.exit(0);
    }

    public String getTmpFile(String filename){
        return Constants.TEMP_DIR + File.separator + filename;
    }

    //-----------------------------------------------------------------

    /**
     *  Donot use; used in Constants.java
     *  get tmp dir as: Constants.TEMP_DIR
     */
    public String tmpDir(){
        // create temp directory
        String tmpDir = FileHelper.getRuntimeDirectory() + File.separator + Constants.TEMP_DIR_NAME;
        new File(tmpDir).mkdirs();
        return tmpDir;
    }

    /**
     *  Donot use; used in Constants.java
     *  get launcher file as: Constants.APP_LAUNCHER_FILE
     */
    public String launcherFile() {

        try {

            String appFile = FileHelper.getRuntimeDirectory() + File.separator + Constants.DEFAULT_APP_FILE_NAME;

            if (new File(appFile).exists()) {
                return appFile;
            }

            List<File> files = FileHelper.getFilesOfExtension(new File(""), FileHelper.getExtension(Constants.DEFAULT_APP_FILE_NAME));
            if (files.size() > 0) {
                return files.get(0).getAbsolutePath();
            } else {
                return "";
            }

        } catch (Exception ex) {
            Logger.getLogger().error("", ex, getClass());
            return "";
        }
    }
    
}
