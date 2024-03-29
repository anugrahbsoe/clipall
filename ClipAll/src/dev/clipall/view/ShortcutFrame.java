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
* File      : ShortcutFrame.java
* Created   : Mar 20, 2010, 11:57:21 PM
*/

package dev.clipall.view;

import dev.clipall.Constants;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.table.TableColumn;

/**
 *
 * @author Erol Hira
 */
public class ShortcutFrame extends javax.swing.JFrame {
    
    public ShortcutFrame() {
        JPanel contentPanel = new JPanel();
        setContentPane(contentPanel);
        initComponents();
        setLocationRelativeTo(SearchFrame.getInstance());
        
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constants.Resources.SYSTEM_TRAY_ICON)));
        } catch(Exception ex){}

        contentPanel.registerKeyboardAction(new EscapeKeyActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JPanel.WHEN_IN_FOCUSED_WINDOW);

        //jTableShortcuts.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF); // Set the first visible column to 100 pixels wide
        int vColIndex = 0;
        TableColumn col = jTableShortcuts.getColumnModel().getColumn(vColIndex);
        int width = 150;
        col.setPreferredWidth(width);
        col.setMinWidth(width);
        col.setMaxWidth(width);

        width = 440;
        col = jTableShortcuts.getColumnModel().getColumn(1);
        col.setPreferredWidth(width);
        col.setMinWidth(width);
        col.setMaxWidth(width);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableShortcuts = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Shortcut Descriptions");
        setAlwaysOnTop(true);
        setMinimumSize(new java.awt.Dimension(587, 199));
        setResizable(false);

        jTableShortcuts.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Win + A", "Display search panel."},
                {"Win + V", "Serial paste."},
                {"Enter on an item", "Paste the selected items."},
                {"Double click on an item", "Paste the selected item."},
                {"Ctrl + up/down", "Switch among categories."},
                {"F2", "Reverse paste selected items."},
                {"F3", "Compare 2 items with Tortoise Svn Diff(Tortoise Svn is needed to be installed)."},
                {"F4", "Compare 2 items with DiffMerge (DiffMerge is needed in the system environment variables)."},
                {"Enter when in search field", "Google the text in the search field that you typed."},
                {"Right button on an item", "Edit the item and press F5 to paste the edited item."},
                {"Esc", "Close any panel."}
            },
            new String [] {
                "Shortcut", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableShortcuts);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ShortcutFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableShortcuts;
    // End of variables declaration//GEN-END:variables

    public static void displayNewInstance(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ShortcutFrame frame = new ShortcutFrame();
                frame.setVisible(true);
            }
        });
    }

    class EscapeKeyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            java.awt.EventQueue.invokeLater(new Runnable() {

                public void run() {                    
                    dispose();
                }
            });
        }
    }
}
