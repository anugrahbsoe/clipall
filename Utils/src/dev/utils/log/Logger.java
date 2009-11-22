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
* File      : Logger.java
* Created   : Nov 20, 2006
*/

package dev.utils.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.SimpleLayout;

/**
 *
 * @author Erol Hira
 */
public class Logger {

    private static Logger log = new Logger();
    private org.apache.log4j.Logger logger;

    private Logger() {
        logger = org.apache.log4j.Logger.getLogger(Logger.class);
    }

    public static Logger getLogger() {
        return log;
    }

    public void initLogger(Level level, String outputFile) {

        logger.setLevel(Level.DEBUG);
        FileAppender appender = new FileAppender();
        try {
            appender.setWriter(new PrintWriter(
                    new FileOutputStream(createFile(outputFile), true)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        appender.setLayout(new SimpleLayout());
        logger.addAppender(appender);
    }

    private File createFile(String fileName) {

        int lastIndexOfSlash = fileName.lastIndexOf(java.io.File.separatorChar);
        if(lastIndexOfSlash > 0){
            String directory = fileName.substring(0, lastIndexOfSlash);
            new File(directory).mkdirs();
        }
        
        try {
            new File(fileName).createNewFile();
            return new File(fileName);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public void debug(String log, Class claz) {
        logger.debug(claz.getName() + ": " + log);
    }

    public void error(String msg, Throwable th, Class claz) {
        logger.error(claz.getName() + ": " + msg, th);
    }
}
