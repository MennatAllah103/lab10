/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.Management;
import Backend.User;
import Backend.UserDataBase;
import Backend.UserLog;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author daree
 */
public class Newsfeed extends javax.swing.JFrame {

     ViewProfile profile;
    UserDataBase database = UserDataBase.getDatabase();
    User user = UserDataBase.getCurrentUser();
    UserLog log = new UserLog(UserDataBase.getDatabase());
    Home home = Home.getInstance();
    Management manage = new Management();
    public Newsfeed() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageFriends = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        manageFriends.setText("Manage Friends");
        manageFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageFriendsActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("        Newsfeed");

        logout.setText("Logout");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(168, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(manageFriends, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(manageFriends)
                .addContainerGap(439, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        log.logOut(user);
       // JOptionPane.showMessageDialog(this, "Logging out","Message",JOptionPane.PLAIN_MESSAGE);
         JLabel messageLabel = new JLabel("  Bye " + user.getUsername() +" !");
       messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
      JOptionPane.showMessageDialog(this, messageLabel, "Logging out !", JOptionPane.PLAIN_MESSAGE);
        setVisible(false);
        home.setVisible(true);
       
    }//GEN-LAST:event_logoutActionPerformed

    private void manageFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageFriendsActionPerformed
        // TODO add your handling code here:
        ManageFriends manager = new ManageFriends(this, user, manage);
        manager.setVisible(true);
        setVisible(false);
    
    }//GEN-LAST:event_manageFriendsActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
 

        
    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
         int choice = JOptionPane.showConfirmDialog(this, "Do you want to log out?", "Are you Sure", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

if (choice == JOptionPane.YES_OPTION)
{
        log.logOut(user);
       JLabel messageLabel = new JLabel("  Bye " + user.getUsername() +" !");
       messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
      JOptionPane.showMessageDialog(this, messageLabel, "Logging out !", JOptionPane.PLAIN_MESSAGE);
       
        setVisible(false);
        home.setVisible(true);
  
} else if (choice == JOptionPane.NO_OPTION) {
  
    JOptionPane.showMessageDialog(this, "You are still logged in.", "Message", JOptionPane.PLAIN_MESSAGE);
    setVisible(true);
   
} 
        
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logout;
    private javax.swing.JButton manageFriends;
    // End of variables declaration//GEN-END:variables
}
