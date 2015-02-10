package keymanager;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 * @author Isabelle Tingzon
 */
public class KeyManagerAPI extends javax.swing.JFrame {
    
    private String HOST;
    private String PUBKEY;
    private String PWD;
    private int PORT;
    private Thread client_thread;
    private SSLClient client;
    

    public KeyManagerAPI() {
        HOST = "localhost";
        PUBKEY = null;
        PORT = 4000;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Encrypt = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        host = new javax.swing.JTextField();
        port = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        PubKeyTextField = new javax.swing.JTextField();
        BrowsePubKey = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        PwdTextField = new javax.swing.JPasswordField();
        GenerateKeys = new javax.swing.JButton();
        encryptStatus = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        ChooseFile = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        FileChooserTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        ChooseFileDest = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        EncryptButton = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Key Manager");

        jLabel1.setText("Hostname");

        jLabel2.setText("Port Number");

        host.setText("localhost");

        port.setText("4000");

        jLabel9.setText("Choose Public Key");

        BrowsePubKey.setText("Browse");
        BrowsePubKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BrowsePubKeyActionPerformed(evt);
            }
        });

        jLabel7.setText("Key Generation Center Network Configuration");

        jLabel8.setText("Password");

        PwdTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PwdTextFieldActionPerformed(evt);
            }
        });

        GenerateKeys.setText("Generate Keys");
        GenerateKeys.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerateKeysActionPerformed(evt);
            }
        });

        encryptStatus.setEditable(false);
        encryptStatus.setForeground(new java.awt.Color(140, 140, 140));
        encryptStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                encryptStatusActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(91, 91, 91)
                        .addComponent(host, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(PubKeyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(BrowsePubKey, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(port)
                                .addGap(240, 240, 240))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(GenerateKeys, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PwdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(encryptStatus))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(host, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(port, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(PubKeyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BrowsePubKey))
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(PwdTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(GenerateKeys)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(encryptStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)))
                .addGap(27, 27, 27))
        );

        Encrypt.addTab("Configure", jPanel1);

        ChooseFile.setText("Browse");
        ChooseFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChooseFileActionPerformed(evt);
            }
        });

        jLabel3.setText("Choose File to Encrypt");

        jLabel4.setText("Specify Access Policy");

        jLabel5.setForeground(new java.awt.Color(144, 144, 144));
        jLabel5.setText("e.g. (Sysadmin and Hiredate > 5) or Security_Team");

        jLabel6.setText("Choose File Destination");

        ChooseFileDest.setText("Browse");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        EncryptButton.setText("Encrypt");
        EncryptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EncryptButtonActionPerformed(evt);
            }
        });

        jButton7.setText("Advanced Options");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(57, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(EncryptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 235, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(FileChooserTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                            .addComponent(jTextField3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ChooseFileDest, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                            .addComponent(ChooseFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel5)
                    .addComponent(jScrollPane1))
                .addGap(58, 58, 58))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(FileChooserTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(ChooseFile)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ChooseFileDest))
                .addGap(30, 30, 30)
                .addComponent(jLabel4)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel5)
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(EncryptButton)
                    .addComponent(jButton7))
                .addGap(15, 15, 15))
        );

        Encrypt.addTab("Encrypt", jPanel2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 643, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 310, Short.MAX_VALUE)
        );

        Encrypt.addTab("Decrypt", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Encrypt)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Encrypt, javax.swing.GroupLayout.PREFERRED_SIZE, 348, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EncryptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EncryptButtonActionPerformed
        
    }//GEN-LAST:event_EncryptButtonActionPerformed

    private void ChooseFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChooseFileActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(KeyManagerAPI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            FileChooserTextField.setText(file.getAbsolutePath());
        } 
    }//GEN-LAST:event_ChooseFileActionPerformed

    private void encryptStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_encryptStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_encryptStatusActionPerformed

    private void GenerateKeysActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerateKeysActionPerformed
        HOST = host.getText();
        PORT = Integer.parseInt(port.getText());
        PUBKEY = PubKeyTextField.getText();
        PWD = PwdTextField.getText();

        client = new SSLClient(HOST, PORT, PUBKEY, PWD);

        boolean success = false;
        try {
            success = client.generateKeys();
            if (success){
                encryptStatus.setText("Keys generated successfully");
                GenerateKeys.setEnabled(false);
            }
        } catch (Exception ex) {
            Logger.getLogger(KeyManagerAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!success){
            encryptStatus.setText("Error in generating keys.");
        }
    }//GEN-LAST:event_GenerateKeysActionPerformed

    private void PwdTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PwdTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PwdTextFieldActionPerformed

    private void BrowsePubKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BrowsePubKeyActionPerformed
        final JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(KeyManagerAPI.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            PubKeyTextField.setText(file.getAbsolutePath());
        }
    }//GEN-LAST:event_BrowsePubKeyActionPerformed
   
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
            java.util.logging.Logger.getLogger(KeyManagerAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KeyManagerAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KeyManagerAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KeyManagerAPI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
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
                new KeyManagerAPI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BrowsePubKey;
    private javax.swing.JButton ChooseFile;
    private javax.swing.JButton ChooseFileDest;
    private javax.swing.JTabbedPane Encrypt;
    private javax.swing.JButton EncryptButton;
    private javax.swing.JTextField FileChooserTextField;
    private javax.swing.JButton GenerateKeys;
    private javax.swing.JTextField PubKeyTextField;
    private javax.swing.JPasswordField PwdTextField;
    private javax.swing.JTextField encryptStatus;
    private javax.swing.JTextField host;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField port;
    // End of variables declaration//GEN-END:variables
}
