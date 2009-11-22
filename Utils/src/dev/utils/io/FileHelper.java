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
* File      : FileHelper.java
* Created   : November 28, 2006, 5:13 PM
*/

package dev.utils.io;

import dev.utils.log.Logger;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *  @version 1.0
 *  @author Erol Hira
 */
public class FileHelper {

    /** Creates a new instance of FileHelper */
    public FileHelper() {
    }

    public static String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf('.');
        if (lastIndexOfDot <= -1) {
            return null;
        } else {
            return fileName.substring(lastIndexOfDot + 1);
        }
    }

    public static String getExtension(URL url) {
        String fileName = dev.utils.net.URLUtils.getFile(url);
        return FileHelper.getExtension(fileName);
    }

    /**
     *  fileName : fullpath name of a file
     */
    public static BufferedReader getBufferedReader(String fileName) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException ex) {
            Logger.getLogger().error("", ex, FileHelper.class);
        }

        return br;
    }

    /**
     *  creates the file and its directories of the <br>
     *  given full named file: fileName 
     */
    public static File createFile(String fileName) {
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

    public static File[] getFilesInDir(String dir, FileFilter fileFilter) {
        File root = new File(dir);
        File[] files = root.listFiles(fileFilter);

        return files;
    }

    /**
     *	@param fileName full name of the file
     */
    public static byte[] getFileInBytes(String fileName) {
        return getFileInBytes(new File(fileName));
    }

    /**
     *  modified for buffering, but ot tested...
     */
    public static byte[] getFileInBytes(File fileName) {
        byte bytes[] = null;
        try {
            FileInputStream fs = new FileInputStream(fileName);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            int b;
            byte[] byteArr = new byte[1024];
            while ((b = fs.read(byteArr)) != -1) {
                bos.write(byteArr, 0, b);
            }

            bytes = bos.toByteArray();

            fs.close();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static void writeBytes(byte[] bytes, String file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(bytes);
        fos.close();
    }

    public static void copyFile(String target, String source) {
        byte[] bytes = getFileInBytes(source);
        try {
            writeBytes(bytes, target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String file) {
        return new File(file).getName();
    }

    private static String[] getFileNames(File[] files) {
        String[] fileNames = new String[files.length];

        for (int i = 0; i < fileNames.length; i++) {
            fileNames[i] = files[i].getName();
        }

        return fileNames;
    }

    public static List<File> getFilesOfExtension(File dir, String extension){

        File[] files = dir.listFiles(new ExtensionFilter(extension));
        if(files == null){
            return new ArrayList<File>();
        }

        ArrayList<File> fileList = new ArrayList<File>();

        for(int i = 0; i < files.length; i++){
            fileList.add(files[i]);
        }

        return fileList;
    }
}
