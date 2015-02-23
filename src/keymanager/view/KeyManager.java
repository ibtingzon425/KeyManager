package keymanager.view;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import keymanager.AWSAPI;
import keymanager.dao.CommandFailedException;

/**
 * @author Isabelle Tingzon
 */
public class KeyManager extends javax.swing.JFrame {
    
    private DefaultListModel bucketListModel;   
    private DefaultListModel fileListModel;
    private final AWSAPI awsapi;
    
    public KeyManager() {
        initComponents();

        String ACCESSKEYID= JOptionPane.showInputDialog(this, "Input Access Key Id", "Message", JOptionPane.PLAIN_MESSAGE);
        String SECERTACCESSKEY = JOptionPane.showInputDialog(this, "Input Secret Access Key", "Message", JOptionPane.PLAIN_MESSAGE);
        awsapi = new AWSAPI(ACCESSKEYID, SECERTACCESSKEY);
        
        initializeBucketList(bucketList);
        initializeBucketList(bucketList2);
        initializeFileList();
        ListenerClass listener = new ListenerClass();
        bucketList2.addListSelectionListener(listener);
        setResizable(false);
    }
    
    public void initializeFileList(){
        fileList.setModel(new DefaultListModel());
        fileListModel = (DefaultListModel) fileList.getModel();
        fileListModel.removeAllElements();
        if (bucketList2.getSelectedValue() != null){
        List<String> fileName = awsapi.listFiles(bucketList2.getSelectedValue().toString());
            for (int i = 0; i< fileName.size(); i++){
                fileListModel.addElement(fileName.get(i));
            }
        }
    }
    
    public void initializeBucketList(JList bucketList){
        bucketList.setModel(new DefaultListModel());
        bucketListModel = (DefaultListModel) bucketList.getModel();
        bucketListModel.removeAllElements();
        List<String> bucketName = awsapi.getBucketList();
        for (int i = 0; i< bucketName.size(); i++){
            bucketListModel.addElement(bucketName.get(i));
        }
    }
    
    private class ListenerClass implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent lse) {
           initializeFileList();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        fileTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        EncryptUploadButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        bucketList = new javax.swing.JList();
        EncRefreshButton = new javax.swing.JButton();
        accessPolicyTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        DownloadDecrypt = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bucketList2 = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList();
        DecRefreshButton = new javax.swing.JButton();
        DecRefreshButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        fileDestinationTextField = new javax.swing.JTextField();
        browseDirectoriesButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel3.setText("Choose file");

        browseButton.setText("Browse");
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Specify Access Policy");

        EncryptUploadButton.setText("Encrypt and Upload");
        EncryptUploadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncryptUploadButtonActionPerformed(evt);
            }
        });

        jLabel5.setText("Choose Bucket");

        bucketList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(bucketList);

        EncRefreshButton.setText("Refresh");
        EncRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncRefreshButtonActionPerformed(evt);
            }
        });

        jLabel1.setForeground(new java.awt.Color(158, 158, 158));
        jLabel1.setText("e.g. (Dean or Professor) and CS_department");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(466, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(accessPolicyTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(fileTextField)
                                .addGap(18, 18, 18)
                                .addComponent(browseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(EncryptUploadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 351, Short.MAX_VALUE)
                                .addComponent(EncRefreshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(51, 51, 51))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(fileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(33, 33, 33)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(EncRefreshButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addGap(8, 8, 8)
                .addComponent(accessPolicyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addGap(28, 28, 28)
                .addComponent(EncryptUploadButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Encryption", jPanel2);

        DownloadDecrypt.setText("Download and Decrypt");
        DownloadDecrypt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DownloadDecryptActionPerformed(evt);
            }
        });

        jLabel2.setText("Choose Bucket");

        bucketList2.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(bucketList2);

        jLabel6.setText("Choose File");

        fileList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(fileList);

        DecRefreshButton.setText("Refresh");
        DecRefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecRefreshButtonActionPerformed(evt);
            }
        });

        DecRefreshButton2.setText("Refresh");
        DecRefreshButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DecRefreshButton2ActionPerformed(evt);
            }
        });

        jLabel7.setText("Choose File Destination");

        fileDestinationTextField.setEditable(false);

        browseDirectoriesButton.setText("Browse");
        browseDirectoriesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseDirectoriesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(DownloadDecrypt)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(fileDestinationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(browseDirectoriesButton, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DecRefreshButton))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(69, 69, 69)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DecRefreshButton2))
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel6)
                    .addComponent(DecRefreshButton)
                    .addComponent(DecRefreshButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(fileDestinationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseDirectoriesButton))
                .addGap(28, 28, 28)
                .addComponent(DownloadDecrypt)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Decryption", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EncryptUploadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncryptUploadButtonActionPerformed
        String filename = fileTextField.getText();
        String policy = accessPolicyTextField.getText();
        String bucketname = bucketList.getSelectedValue().toString();
        awsapi.uploadFile(filename, policy, bucketname);
    }//GEN-LAST:event_EncryptUploadButtonActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(KeyManager.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            fileTextField.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void DownloadDecryptActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DownloadDecryptActionPerformed
        try {
            String bucket = bucketList2.getSelectedValue().toString();
            String filename = fileList.getSelectedValue().toString();
            String filedest = fileDestinationTextField.getText();
            awsapi.downloadFile(bucket, filename, filedest);
        } catch (IOException | CommandFailedException ex) {
            Logger.getLogger(KeyManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_DownloadDecryptActionPerformed

    private void EncRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncRefreshButtonActionPerformed
        initializeBucketList(bucketList);
    }//GEN-LAST:event_EncRefreshButtonActionPerformed

    private void DecRefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecRefreshButtonActionPerformed
        initializeBucketList(bucketList2);
    }//GEN-LAST:event_DecRefreshButtonActionPerformed

    private void DecRefreshButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DecRefreshButton2ActionPerformed
        initializeFileList();
    }//GEN-LAST:event_DecRefreshButton2ActionPerformed

    private void browseDirectoriesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseDirectoriesButtonActionPerformed
        final JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(KeyManager.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            fileDestinationTextField.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_browseDirectoriesButtonActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(KeyManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeyManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeyManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeyManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KeyManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DecRefreshButton;
    private javax.swing.JButton DecRefreshButton2;
    private javax.swing.JButton DownloadDecrypt;
    private javax.swing.JButton EncRefreshButton;
    private javax.swing.JButton EncryptUploadButton;
    private javax.swing.JTextField accessPolicyTextField;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton browseDirectoriesButton;
    private javax.swing.JList bucketList;
    private javax.swing.JList bucketList2;
    private javax.swing.JTextField fileDestinationTextField;
    private javax.swing.JList fileList;
    private javax.swing.JTextField fileTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
