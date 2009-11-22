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
* File      : CategoryForm.java
* Created   : Nov 7, 2009, 10:41:32 AM
*/

package dev.clipall.view;

import dev.clipall.business.GenericMediator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Erol Hira
 */
public class CategoryForm extends javax.swing.JFrame {

    /** Creates new form CategoryForm */
    public CategoryForm() {
        JPanel contentPanel = new JPanel();
        setContentPane(contentPanel);
        initComponents();
        contentPanel.registerKeyboardAction(new EscapeKeyActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JPanel.WHEN_IN_FOCUSED_WINDOW);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextCategoryName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setTitle("Yeni Kategori");
        setAlwaysOnTop(true);

        jLabel1.setText("Kategori Ad?");

        jButton1.setText("Ekle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jTextCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextCategoryName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addComponent(jButton1)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String categoryName = jTextCategoryName.getText();
        GenericMediator.getInstance().createNewCategory(categoryName);

        hideCategoryForm();
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void displayNewInstance(){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                CategoryForm categoryForm = new CategoryForm();
                categoryForm.setVisible(true);
                //categoryForm.toFront();
            }
        });
    }
        
    public void hideCategoryForm(){

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextCategoryName;
    // End of variables declaration//GEN-END:variables

    class EscapeKeyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            hide();
        }

    }
}