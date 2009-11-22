/**
* This file is part of dev.utils.
*
* dev.utils is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* You should have received a copy of the GNU General Public License
* along with dev.utils.  If not, see <http://www.gnu.org/licenses/>.
*
* File      : CircularBinaryList.java
* Created   : October 24, 2007, 2:18 PM
*/

package dev.utils.collections;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class CircularBinaryList {
    
    private int[] elements;
    
    public CircularBinaryList() {
        elements = new int[2];
    }        
    
    public int getFirst(){
        return elements[0];
    }
    
    public int getLast(){
        return elements[1];
    }
       
    public int popFirst(){
        
        int element = elements[0];
        elements[0] = elements[1];
        return element;
    }
    
    public int popLast(){
        
        int element = elements[1];
        elements[1] = elements[0];
        return element;
    }
    
    public void pushFirst(int element){
        elements[1] = elements[0];
        elements[0] = element;
    }
    
    public void pushLast(int element){
        elements[0] = elements[1];
        elements[1] = element;
    }
    
    public void fill(int element){
        elements[0] = element;
        elements[1] = element;
    }
    
    public void reset(){
        elements[0] = 0;
        elements[1] = 0;
    }
}
