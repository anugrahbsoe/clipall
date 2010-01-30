/*
 * QueryForm.java
 *
 * Created on Dec 26, 2009, 9:23:24 AM
 */
package dev.clipall.plugins.clipdb;

import dev.clipall.Constants;
import dev.utils.db.dbcp.ManualPoolingDataSource;
import dev.utils.db.dbcp.Sql;
import dev.utils.db.dbcp.SqlJob;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

/**
 *
 * @author Erol Hira
 */
public class QueryForm extends javax.swing.JFrame {

    private JPanel panel;
    private QueryTableModel tableModel = new QueryTableModel();

    /** Creates new form QueryForm */
    public QueryForm() {

        panel = new JPanel();
        setContentPane(panel);
        initComponents();
        setListeners();
        jResultTable.setModel(tableModel);

        //setLocationRelativeTo(SearchFrame.getInstance());
        try {
            setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(Constants.Resources.SYSTEM_TRAY_ICON)));
        } catch (Exception ex) {
        }        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jQueryField = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jResultTable = new javax.swing.JTable();

        setTitle("Query Panel");

        jQueryField.setColumns(20);
        jQueryField.setRows(5);
        jScrollPane1.setViewportView(jQueryField);

        jResultTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jResultTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new QueryForm().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea jQueryField;
    private javax.swing.JTable jResultTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    public void setListeners() {

        panel.registerKeyboardAction(new EscapeKeyActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JPanel.WHEN_IN_FOCUSED_WINDOW);
        panel.registerKeyboardAction(new QueryActionListener(), KeyStroke.getKeyStroke(KeyEvent.VK_F8, 0), JPanel.WHEN_IN_FOCUSED_WINDOW);
    }

    class QueryActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {

            String query = jQueryField.getSelectedText().trim();
            runQuery(query);
        }
    }

    public void runQuery(String query) {

        try {

            ManualPoolingDataSource.getInstance().init("/db.properties");

            Sql sql = Sql.createInstance(ManualPoolingDataSource.getInstance().getDataSource());

            SqlJob sqlJob = new SqlJob(sql) {

                @Override
                public void processQueryResult() throws SQLException {

                    tableModel.setQuery(rset);
                }
            };

            sql.executeQuery(query, sqlJob);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void runQueryAndDisplay(String query){

        jQueryField.setText(query);
        runQuery(query);
        display();
    }

    public void display() {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                setVisible(true);
            }
        });
    }

    class EscapeKeyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            hide();
        }
    }
}