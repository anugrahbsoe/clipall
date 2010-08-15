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
* File      : Tasks.java
* Created   : April 2010
*/
package dev.clipall.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Erol Hira
 */

@XmlRootElement
public class Tasks {

    private ArrayList<Task> taskList;

    public Tasks(){
        taskList = new ArrayList<Task>();
    }

    public Tasks(dev.clipall.model.jaxb.tasks.Tasks tasks){
        super();
        setupTasksFromJaxb(tasks);
    }

    public void addTask(Task task){
        taskList.add(task);
    }

    public void deleteTask(Task task){
        taskList.remove(task);
    }

    public void setupTasksFromJaxb(dev.clipall.model.jaxb.tasks.Tasks tasks){
        
        List<dev.clipall.model.jaxb.tasks.Task> jaxbTaskList = tasks.getTaskList();

        for(dev.clipall.model.jaxb.tasks.Task jaxbTask : jaxbTaskList){
            Task task = new Task(jaxbTask);
            taskList.add(task);
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
}
