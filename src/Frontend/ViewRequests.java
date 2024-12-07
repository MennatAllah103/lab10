/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;


import Backend.*;

import java.awt.ScrollPane;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author daree
 */
public class ViewRequests extends javax.swing.JFrame {
    

      ManageFriends manageframe;
    User currentuser;
    Management manage;
    UserDataBase userDB =UserDataBase.getDatabase();
    /**
     * Creates new form RequestsManagement
     */
    public ViewRequests(ManageFriends manageframe,User currentuser,Management manage) {
        initComponents();
     this.manageframe=manageframe;
        this.currentuser=currentuser;
        this.manage=manage;
        fillList();
    
    }
    public void fillList()
    {
        ArrayList<Requests> requests =manage.getUserRequests(currentuser.getUserId());
        DefaultListModel<String> model=new DefaultListModel();

     for(Requests r:requests)
        {   
            String userid1=r.getSenderID();
            User u=userDB.getUserById(userid1);
           String username=u.getUsername();
            model.addElement(username);
        }
     
        listreq.setModel(model);
       jScrollPane1.setViewportView(listreq);
      
    
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listreq = new javax.swing.JList<>();
        acceptButton = new javax.swing.JButton();
        DeclineButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Friend Requests");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        listreq.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { " " };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(listreq);

        acceptButton.setBackground(new java.awt.Color(255, 204, 255));
        acceptButton.setText("Accept");
        acceptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });

        DeclineButton.setBackground(new java.awt.Color(255, 204, 255));
        DeclineButton.setText("Decline");
        DeclineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeclineButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("                   Friend Requests");

        back.setBackground(new java.awt.Color(255, 204, 255));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(back))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(acceptButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(DeclineButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(45, 45, 45))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptButton)
                    .addComponent(DeclineButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(back)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        manageframe.setVisible(true);
        
    }//GEN-LAST:event_formWindowClosed

    private void acceptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_acceptButtonActionPerformed
        // TODO add your handling code here:
 
     int index= listreq.getSelectedIndex();
     if(index>-1)
     {
         String selectedusername=listreq.getSelectedValue();
        User userAccepted=userDB.getUserByUsername(selectedusername);
      String senderid=userAccepted.getUserId();
      String receiverid=currentuser.getUserId();
      Requests R=manage.getRequest(senderid, receiverid);
      manage.acceptrequest(R);
      fillList();  
     }
     
     else
     {
          JOptionPane.showMessageDialog(this, "You Should Select a request", "  Message ", JOptionPane.PLAIN_MESSAGE);
     }
     
        
        
        
    }//GEN-LAST:event_acceptButtonActionPerformed

    private void DeclineButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeclineButtonActionPerformed
        // TODO add your handling code here:
        int index= listreq.getSelectedIndex();
     if(index>-1)
     {
         String selectedusername=listreq.getSelectedValue();
          User userDeclined=userDB.getUserByUsername(selectedusername);
        String senderid=userDeclined.getUserId();
      String receiverid=currentuser.getUserId();
      Requests R=manage.getRequest(senderid, receiverid);
       manage.declinerequest(R);
      fillList();  
     }
     
     else
     {
          JOptionPane.showMessageDialog(this, "You Should Select a request", "  Message ", JOptionPane.PLAIN_MESSAGE);
     }
       

    }//GEN-LAST:event_DeclineButtonActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        
          setVisible(false);
        manageframe.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeclineButton;
    private javax.swing.JButton acceptButton;
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listreq;
    // End of variables declaration//GEN-END:variables
}
