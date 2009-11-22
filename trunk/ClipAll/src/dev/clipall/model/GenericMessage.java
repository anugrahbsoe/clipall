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
* File      : GenericMessage.java
* Created   : Nov 2009
*/

package dev.clipall.model;

/**
 *
 * @author erol
 */
public class GenericMessage extends Message {
    
    public GenericMessage(int resultCode, String message) {
        super(resultCode, message);
    }

    public static Message getDefaultSuccessMessage(){
        return new GenericMessage(Message.SUCCESS, "");
    }
}
