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
* File      : SearchPanel.java
* Created   : Oct 28, 2009, 8:52:30 PM
*/

package dev.clipall.view;

import dev.clipall.Constants;
import dev.clipall.business.GenericLogic;
import dev.clipall.business.GenericMediator;
import dev.clipall.model.GenericModel;
import dev.clipall.model.Item;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.KeyStroke;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Erol Hira
 */
public class SearchPanel extends javax.swing.JPanel {

    private static SearchPanel searchPanel = new SearchPanel();

    private SearchPanel(){
        initComponents();        
        setListeners();
    }

    public static SearchPanel getInstance(){
        return searchPanel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bookmarkPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jBookmarkField = new javax.swing.JTextField();
        jSearchField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jSearchList = new javax.swing.JList();
        jPanel1 = new javax.swing.JPanel();
        jCategoryLabel = new javax.swing.JLabel();
        jCategoryUpLabel = new javax.swing.JLabel();
        jCategoryDownLabel = new javax.swing.JLabel();
        jButtonNewKategory = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonDeleteItem = new javax.swing.JButton();

        setMinimumSize(new java.awt.Dimension(300, 180));

        bookmarkPanel.setPreferredSize(new java.awt.Dimension(565, 24));

        jLabel1.setText("Bookmark");

        jBookmarkField.setEditable(false);
        jBookmarkField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jBookmarkFieldMouseClicked(evt);
            }
        });
        jBookmarkField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jBookmarkFieldFocusLost(evt);
            }
        });
        jBookmarkField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jBookmarkFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout bookmarkPanelLayout = new javax.swing.GroupLayout(bookmarkPanel);
        bookmarkPanel.setLayout(bookmarkPanelLayout);
        bookmarkPanelLayout.setHorizontalGroup(
            bookmarkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookmarkPanelLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jBookmarkField, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE))
        );
        bookmarkPanelLayout.setVerticalGroup(
            bookmarkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookmarkPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
                .addComponent(jBookmarkField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jSearchList.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jSearchList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jSearchListMouseClicked(evt);
            }
        });
        jSearchList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                jSearchListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(jSearchList);

        jPanel1.setLayout(null);

        jCategoryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jCategoryLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(93, 93, 214), 1, true));
        jCategoryLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jCategoryLabel.setOpaque(true);
        jPanel1.add(jCategoryLabel);
        jCategoryLabel.setBounds(30, 0, 190, 23);

        jCategoryUpLabel.setBackground(new java.awt.Color(255, 153, 153));
        jCategoryUpLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jCategoryUpLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/arrow_top.png"))); // NOI18N
        jCategoryUpLabel.setMaximumSize(new java.awt.Dimension(22, 24));
        jCategoryUpLabel.setMinimumSize(new java.awt.Dimension(22, 24));
        jCategoryUpLabel.setPreferredSize(new java.awt.Dimension(22, 24));
        jCategoryUpLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCategoryUpLabelMouseClicked(evt);
            }
        });
        jPanel1.add(jCategoryUpLabel);
        jCategoryUpLabel.setBounds(10, 0, 20, 24);

        jCategoryDownLabel.setBackground(new java.awt.Color(255, 153, 153));
        jCategoryDownLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/arrow_down.png"))); // NOI18N
        jCategoryDownLabel.setIconTextGap(0);
        jCategoryDownLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jCategoryDownLabelMouseClicked(evt);
            }
        });
        jPanel1.add(jCategoryDownLabel);
        jCategoryDownLabel.setBounds(220, 0, 20, 20);

        jButtonNewKategory.setText("New Category");
        jButtonNewKategory.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonNewKategory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNewKategoryActionPerformed(evt);
            }
        });

        jButtonSave.setText("Save");
        jButtonSave.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonSave.setMaximumSize(new java.awt.Dimension(89, 23));
        jButtonSave.setMinimumSize(new java.awt.Dimension(89, 23));
        jButtonSave.setPreferredSize(new java.awt.Dimension(89, 23));
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });

        jButtonDeleteItem.setText("Delete");
        jButtonDeleteItem.setMargin(new java.awt.Insets(2, 7, 2, 7));
        jButtonDeleteItem.setMaximumSize(new java.awt.Dimension(89, 23));
        jButtonDeleteItem.setMinimumSize(new java.awt.Dimension(89, 23));
        jButtonDeleteItem.setPreferredSize(new java.awt.Dimension(89, 23));
        jButtonDeleteItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bookmarkPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonNewKategory)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSearchField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonDeleteItem, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButtonNewKategory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookmarkPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonNewKategoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNewKategoryActionPerformed
        newCategoryEvent();
    }//GEN-LAST:event_jButtonNewKategoryActionPerformed

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GenericLogic.getInstance().saveTheCurrentCategory(Constants.DEFAULT_HISTORY_FILE);
            }
        });
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jButtonDeleteItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteItemActionPerformed
        deleteItem();
    }//GEN-LAST:event_jButtonDeleteItemActionPerformed

    private void jCategoryDownLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCategoryDownLabelMouseClicked
        GenericModel.getInstance().setNextCategory();
    }//GEN-LAST:event_jCategoryDownLabelMouseClicked

    private void jCategoryUpLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jCategoryUpLabelMouseClicked
        GenericModel.getInstance().setPreviousCategory();
    }//GEN-LAST:event_jCategoryUpLabelMouseClicked

    private void jSearchListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jSearchListMouseClicked
        if(evt.getClickCount() == 2 && evt.isConsumed() == false){
            evt.consume();
            GenericMediator.getInstance().enterPressedOnSearchListEvent(jSearchField.getText());
        }
    }//GEN-LAST:event_jSearchListMouseClicked

    private void jBookmarkFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jBookmarkFieldKeyPressed

        if(KeyEvent.VK_ENTER == evt.getKeyCode()){

            String bookmark = jBookmarkField.getText();
            Item item = getSelectedItem();
            if(item == null){
                jBookmarkField.setText("");
                return;
            }

            item.setBookmark(bookmark);

            jBookmarkField.setEditable(false);
        }
    }//GEN-LAST:event_jBookmarkFieldKeyPressed

    private void jBookmarkFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jBookmarkFieldMouseClicked
        if(evt.getClickCount() == 2 && evt.isConsumed() == false){
            evt.consume();
            if(getSelectedItem() != null){
                jBookmarkField.setEditable(true);
            }
        }
    }//GEN-LAST:event_jBookmarkFieldMouseClicked

    private void jSearchListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_jSearchListValueChanged

        setCurrentBookmark();
    }//GEN-LAST:event_jSearchListValueChanged

    private void jBookmarkFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jBookmarkFieldFocusLost
        jBookmarkField.setEditable(false);
    }//GEN-LAST:event_jBookmarkFieldFocusLost

    public void setCurrentBookmark(){

        Item item = getSelectedItem();
        if(item == null){
            jBookmarkField.setText("");
            return;
        }

        jBookmarkField.setText(item.getBookmark());
    }

    public void deleteItem(){

        int selectedIndex = jSearchList.getSelectedIndex();
        if(selectedIndex >= 0){
            Item item = getSelectedItem();
            GenericMediator.getInstance().deleteItemEvent(item);
        }

        int listSize = GenericModel.getInstance().getSearchListItemsOfCurrentCategory(jSearchField.getText()).size();
        if(listSize > 0){
            jSearchList.setSelectedIndex(selectedIndex);
        }        
    }

    public Item getSelectedItem(){

        int selectedIndex = jSearchList.getSelectedIndex();
        Item item = null;
        if(selectedIndex >= 0){
            item = GenericModel.getInstance().getSearchListItemsOfCurrentCategory(jSearchField.getText()).get(selectedIndex);
        }

        return item;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bookmarkPanel;
    private javax.swing.JTextField jBookmarkField;
    private javax.swing.JButton jButtonDeleteItem;
    private javax.swing.JButton jButtonNewKategory;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JLabel jCategoryDownLabel;
    private javax.swing.JLabel jCategoryLabel;
    private javax.swing.JLabel jCategoryUpLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jSearchField;
    private javax.swing.JList jSearchList;
    // End of variables declaration//GEN-END:variables

    public void setCurrentCategoryLabel(){
        jCategoryLabel.setText(GenericModel.getInstance().getCurrentCategory().getName());
    }

    public void updateJSearchList(){
        String str = jSearchField.getText();
        updateJSearchList(str);
    }

    public void updateJSearchList(String str){
        jSearchList.setListData(GenericModel.getInstance().getSearchListItemsOfCurrentCategory(str).toArray());
    }

    public void updateSearchPanelFields(){

        setCurrentCategoryLabel();
        updateJSearchList();
    }

    public int getSelectedItemIndex(){
        return jSearchList.getSelectedIndex();
    }

    private void newCategoryEvent() {
        CategoryForm.displayNewInstance();
    }

    public void focusOnSearchTextField(){        
        jSearchField.requestFocusInWindow();
    }

    private void setListeners() {

        registerKeyboardAction(new EscapeKeyActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(new CategoryNavigateActionListener(KeyEvent.VK_UP), KeyStroke.getKeyStroke(KeyEvent.VK_UP, InputEvent.CTRL_MASK), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(new CategoryNavigateActionListener(KeyEvent.VK_DOWN), KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, InputEvent.CTRL_MASK), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(new SearchListNavigateActionListener(KeyEvent.VK_DOWN), KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), WHEN_IN_FOCUSED_WINDOW);
        registerKeyboardAction(new SearchListNavigateActionListener(KeyEvent.VK_UP), KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), WHEN_IN_FOCUSED_WINDOW);
        jSearchList.addKeyListener(new SearchListListener());

        jSearchField.getDocument().addDocumentListener(

            new DocumentListener() {

                private void updateTextField(){
                    try {
                        javax.swing.text.Document doc = jSearchField.getDocument();
                        String text = doc.getText(0, doc.getLength());
                        updateJSearchList(text);
                    } catch (BadLocationException ex) {
                        ex.printStackTrace();
                    }
                }

                public void insertUpdate(DocumentEvent e) { // FIXME  think about calling outside from event-dispatch thred
                    updateTextField();
                }                

                public void changedUpdate(DocumentEvent e) {// FIXME  think about calling outside from event-dispatch thred
                    updateTextField();
                }

                public void removeUpdate(DocumentEvent e) {
                    updateTextField();
                }
            }
        );
    }
    
    //------------------------------------------------------

    class SearchListListener implements KeyListener {

        public void keyTyped(KeyEvent e) {            
        }

        public void keyPressed(KeyEvent e) {
            if(KeyEvent.VK_ENTER == e.getKeyCode()){
                GenericMediator.getInstance().enterPressedOnSearchListEvent(jSearchField.getText());
            } else if(KeyEvent.VK_DELETE == e.getKeyCode()){
                deleteItem();
            }
        }
        
        public void keyReleased(KeyEvent e) {
        }
    }

    class EscapeKeyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            SearchFrame.getInstance().hideSearchFrame();
        }
        
    }

    class CategoryNavigateActionListener implements ActionListener {

        private int key;

        public CategoryNavigateActionListener(int key){
            this.key = key;
        }

        public void actionPerformed(ActionEvent e) {

            if(KeyEvent.VK_UP == key){
                
                GenericModel.getInstance().setPreviousCategory();

            } else if(KeyEvent.VK_DOWN == key){

                GenericModel.getInstance().setNextCategory();
            }
        }
    }

    class SearchListNavigateActionListener implements ActionListener {

        private int key;

        public SearchListNavigateActionListener(int key){
            this.key = key;
        }

        public void actionPerformed(ActionEvent e) {
            
            if(jSearchList.isFocusOwner() == false){
                jSearchList.requestFocusInWindow();                
            }
        }
    }
    
}
