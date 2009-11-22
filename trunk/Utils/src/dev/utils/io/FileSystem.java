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
* File      : FileSystem.java
* Created   : November 28, 2006
*/

package dev.utils.io;

import java.io.File;
import java.util.LinkedList;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

/**
 * 
 * @author Erol Hira
 */
public class FileSystem {

    private static FileSystem fileSystem = new FileSystem();

    private FileSystem() {
    }

    public static FileSystem getInstance() {
        return fileSystem;
    }

    public void traverseDir(File root, DefaultMutableTreeNode node) {
        File[] files = root.listFiles(new HiddenFilter(false));
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            File newFile = files[i];
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
                                                            newFile.getName());
            if (newFile.isFile()) {
                newNode.setAllowsChildren(false);
            }

            node.add(newNode);

            traverseDir(newFile, newNode);
        }
    }

    /**
     *	delete the file recursively completely.
     */
    public void delete(File root) {
        File[] files = root.listFiles();
        if (files == null) {
            root.delete();
            return;
        }

        for (int i = 0; i < files.length; i++) {
            delete(files[i]);
        }

        root.delete();
    }

    public void traverseDir(File root, LinkedList<File> list) {
        File[] files = root.listFiles(new DirFilter());
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            if (file.isFile()) {
                list.add(file);
            }

            traverseDir(file, list);
        }
    }

    public void traverseFirstOrderDirs(File root, DefaultMutableTreeNode node) {
        File[] files = root.listFiles(new DirFilter());
        if (files == null) {
            return;
        }

        for (int i = 0; i < files.length; i++) {
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(
                                                        files[i].getName());
            node.add(newNode);
        }
    }

    public TreeNode getFilesInTreeModel(File root) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                root.getName());
        traverseDir(root, node);

        return node;
    }

    public LinkedList<File> getFiles(File root) {

        LinkedList<File> list = new LinkedList<File>();
        traverseDir(root, list);

        return list;
    }

    public TreeNode getFirstOrderDirsInTreeModel(File root) {
        DefaultMutableTreeNode node = new DefaultMutableTreeNode(
                root.getName());
        traverseFirstOrderDirs(root, node);
        return node;
    }

    /**
     *	returns the names of the dirs in a root
     */
    public String[] getFirstOrderDirs(File root) {
        File[] files = root.listFiles(new DirFilter());
        if (files == null) {
            return new String[0];
        }

        int length = files.length;
        String[] dirs = new String[length];
        for (int i = 0; i < length; i++) {
            dirs[i] = files[i].getName();
        }

        return dirs;
    }
    
}
