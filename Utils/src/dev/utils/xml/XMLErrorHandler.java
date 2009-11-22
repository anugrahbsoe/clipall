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
* File      : XMLErrorHandler.java
* Created   : October 24, 2007
*/

package dev.utils.xml;

import dev.utils.log.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class XMLErrorHandler implements ErrorHandler {

    public void warning(SAXParseException exception) throws SAXException {

        Logger.getLogger().error("[ERROR] " + getLocationString(exception) + " : ", exception, getClass());
    }

    /**
     *  XML 1.0 spec : 
     *  
     *  An error is a violation of the rules of the specification. 
     *  A conforming XML processor may detect and report an error 
     *  and may recover from it. 
     */
    public void error(SAXParseException exception) throws SAXException {
        
        Logger.getLogger().error("[ERROR] " + getLocationString(exception) + " : ", exception, getClass());
    }

    /**
     *  XML 1.0 spec : 
     * 
     *  Violations of well-formedness constraints are fatal errors. 
     *  Once a fatal error is detected, the processor must not continue 
     *  normal processing.
     */
    public void fatalError(SAXParseException exception) throws SAXException {

        Logger.getLogger().error("[FATAL ERROR] " + getLocationString(exception) + " : ", exception, getClass());
    }

    /** Returns a string of the location. */
    private String getLocationString(SAXParseException ex) {
        StringBuffer str = new StringBuffer();

        String systemId = ex.getSystemId();
        if (systemId != null) {
            int index = systemId.lastIndexOf('/');
            if (index != -1) {
                systemId = systemId.substring(index + 1);
            }
            str.append(systemId);
        }
        str.append(':');
        str.append(ex.getLineNumber());
        str.append(':');
        str.append(ex.getColumnNumber());

        return str.toString();
    }
}
