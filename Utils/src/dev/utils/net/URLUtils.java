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
* File      : URLUtils.java
* Created   : November 23, 2006, 8:02 PM
*/

package dev.utils.net;

import dev.utils.io.FileConstants;
import dev.utils.log.Logger;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class URLUtils {
    
    /**
     *  this method saves the binary file that the url points, to the <br>
     *  place that is pointed with localDirectory in the client host. 
     * 
     *  todo : expand with using FileHelper.createFile
     */
    public static void saveBinaryFile(URL url, String localDirectory) 
                                                        throws IOException {
        URLConnection uc = url.openConnection( );
        String contentType = uc.getContentType( );
        int contentLength = uc.getContentLength( );
        
        if (contentType.startsWith("text/") || contentLength == -1 ) {
            throw new IOException("This is not a binary file.");
        }
        
        InputStream raw = uc.getInputStream( );
        InputStream in = new BufferedInputStream(raw);
        byte[] data = new byte[contentLength];
        int bytesRead = 0;
        int offset = 0;
        while (offset < contentLength) {
            bytesRead = in.read(data, offset, data.length-offset);
            if (bytesRead == -1) break;
                offset += bytesRead;
        }
            
        in.close( );
            
        if (offset != contentLength) {
            throw new IOException("Only read " + offset
            + " bytes; Expected " + contentLength + " bytes");
        }

        String filename = url.getFile( );
        filename = filename.substring(filename.lastIndexOf('/') + 1);
        
        if(localDirectory != null){
            filename = localDirectory + filename;
        }
        
        FileOutputStream fout = new FileOutputStream(filename);
        fout.write(data);
        fout.flush( );
        fout.close( );
    }
            
    
    
    public static boolean isURL(String url){
        try {
            
            new URL(url);
            
        } catch (MalformedURLException ex) {
            
            return false;
        }
        
        return true;
    }
    
    /**
     *  return filename relative to host name of the URL
     */
    public static String getFile(URL url){
        
        String fileName = url.getFile(); 
        
        if(fileName.length() == 0 || fileName == null){
        
            return "/" + FileConstants.UNKNOWN_HTML_FILENAME;
            
        } else if(dev.utils.io.FileHelper.getExtension(fileName) == null){
            
            fileName = dev.utils.string.StringUtils.
                                                    trimRight(fileName, '/');
            return fileName + "/" + FileConstants.UNKNOWN_HTML_FILENAME;
            
        }else{
            
            return fileName;
        }        
    }
    
    /**
     *  for http://www.a.com/b/c.html   <br>
     *  returns  http://www.a.com/b
     */
    public static String getURLDirectory(URL url){
        
        String urlDirectory = null;
        String directory = null;
        
        try {
            
            URL tmpUrl = new URL(url.getProtocol(), url.getHost(),
                                    url.getPort(), "");
            String file = url.getFile();
            
            if(dev.utils.io.FileHelper.getExtension(file) == null){
                directory = file;
                directory = dev.utils.string.StringUtils.
                                                    trimRight(directory,'/');
            }else{
                directory = file.substring(0, file.lastIndexOf('/'));
            }
                                    
            urlDirectory = tmpUrl.toString();
            urlDirectory += directory;
            
        } catch (MalformedURLException ex) {
            
            Logger.getLogger().error("", ex, URLUtils.class);
            
        }                
        
        return urlDirectory;
    }
    
}
