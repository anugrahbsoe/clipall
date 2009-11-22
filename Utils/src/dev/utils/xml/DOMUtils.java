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
* File      : DOMUtils.java
* Created   : October 24, 2007
*/

package dev.utils.xml;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import dev.utils.log.Logger;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class DOMUtils {
        
    private static final DocumentBuilder builder = DOMUtils.getJAXPParser(false, false, true);    
    
    private static final XMLSerializer serializer = new XMLSerializer();
    
    /**
     *   namespaceAware : not used
     */
    public static DOMParser getXercesParser(
                                boolean validatingProcessor,
                                boolean namespaceAware,
                                boolean ignoreWhiteSpace){
        
        DOMParser parser = new DOMParser();                
        
        if(validatingProcessor){
            
            try {
            
                parser.setErrorHandler(new XMLErrorHandler());

                parser.setFeature("http://xml.org/sax/features/validation", true);                        
                
                parser.setFeature(
                    "http://apache.org/xml/features/dom/include-ignorable-whitespace",
                    !ignoreWhiteSpace);
                
            } catch (SAXNotRecognizedException ex) {
                Logger.getLogger().error("", ex, DOMUtils.class);
            } catch (SAXNotSupportedException ex) {
                Logger.getLogger().error("", ex, DOMUtils.class);
            }
            
        }

        return parser;
    }
    
    public static DocumentBuilder getJAXPParser(
                                boolean validatingProcessor,
                                boolean namespaceAware,
                                boolean ignoreWhiteSpace){
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        
        factory.setValidating(validatingProcessor);
        
        // Tells the parser to be aware of namespaces
        factory.setNamespaceAware(namespaceAware);
        
        factory.setIgnoringElementContentWhitespace(ignoreWhiteSpace);
        
        DocumentBuilder docBuilder = null;
        
        try {                     

            docBuilder = factory.newDocumentBuilder();

            if (validatingProcessor) {

                docBuilder.setErrorHandler(new XMLErrorHandler());
            }

            return docBuilder;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger().error("", ex, DOMUtils.class);
        }
        
        return docBuilder;
    }
    
    public static DocumentBuilder getDocumentBuilder(){
        return builder;
    }
    
    public static Document createDocument(){
        
        return builder.newDocument();
    }
    
    public static void serialize(Document doc, OutputStream out){
        
        OutputFormat formatter = new OutputFormat();
        formatter.setPreserveSpace(true);                        
        formatter.setIndenting(true);        
        
        serializer.setOutputByteStream(out != null ? out : System.out);
        serializer.setOutputFormat(formatter);
        
        try {           
            serializer.serialize(doc);
        } catch (IOException ex) {
            Logger.getLogger().error("", ex, DOMUtils.class);
        }
    }
    
}
