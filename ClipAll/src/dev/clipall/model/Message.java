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
* File      : Message.java
* Created   : Nov 2009
*/

package dev.clipall.model;

/**
 *
 * @author erol
 */
public abstract class Message {

    public static final int SUCCESS     = 10;
    public static final int FAIL        = 11;

    private int resultCode;
    private String message;

    public Message(){}

    public Message(int resultCode, String message){
        this.resultCode = resultCode;
        this.message = message;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
