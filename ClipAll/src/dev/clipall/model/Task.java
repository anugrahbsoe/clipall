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
* File      : Task.java
* Created   : April 2010
*/

package dev.clipall.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author Erol Hira
 */

@XmlType(propOrder={"name", "description", "context", "createdDate", "endDate", "notes"})
public class Task {

    private String name;
    private Date createdDate;
    private Date endDate;
    private String description;
    private String notes;
    private String context;
    
    public Task(){
    }

    public Task(dev.clipall.model.jaxb.tasks.Task jaxbTask){

        name = jaxbTask.getName();
        notes = jaxbTask.getNotes();
        context = jaxbTask.getContext();
        endDate = jaxbTask.getEndDate().toGregorianCalendar().getTime();     
        createdDate = jaxbTask.getCreatedDate().toGregorianCalendar().getTime();
        description = jaxbTask.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    
}
