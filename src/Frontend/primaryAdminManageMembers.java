/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.*;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author daree
 */
public class primaryAdminManageMembers extends javax.swing.JFrame {

     UserDataBase userDB = UserDataBase.getDatabase();
    primaryAdminFrame primaryFrame;
    GroupManagement groupmanage = new GroupManagement();
    GroupDataBase GDB= GroupDataBase.getInstance();
    Group group ;
    /**
     * Creates new form primaryAdminManageMembers
     */
    public primaryAdminManageMembers(primaryAdminFrame primaryFrame,Group group) {
        initComponents();
        this.primaryFrame=primaryFrame;
         this.group = group;
        filllist();
        
    }

    
    
     public void filllist()
 {
     
     group = GDB.getCurrentGroup();
      ArrayList<String> membersIDs= group.getMembersIDs();
      DefaultListModel<String> model = new DefaultListModel();

     for (String ID : membersIDs)
     {
                User Member = userDB.getUserById(ID);
                String memberName = Member.getUsername();
                model.addElement(memberName);
   

     }
     
      if (model.isEmpty()) {
        list.setModel(new DefaultListModel<>());
        jScrollPane1.setViewportView(list);
    } else {
 
        list.setModel(model);
        jScrollPane1.setViewportView(list);
    }

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
        list = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        removebtn = new javax.swing.JButton();
        promotebtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        addmemberbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jScrollPane1.setViewportView(list);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" Members");

        removebtn.setText("Remove");
        removebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removebtnActionPerformed(evt);
            }
        });

        promotebtn.setText("Promote");
        promotebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promotebtnActionPerformed(evt);
            }
        });

        backbtn.setText("back");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        addmemberbtn.setText("Add");
        addmemberbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addmemberbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(backbtn)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(removebtn)
                            .addComponent(promotebtn)))
                    .addComponent(addmemberbtn))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(removebtn)
                        .addGap(18, 18, 18)
                        .addComponent(promotebtn)
                        .addGap(18, 18, 18)
                        .addComponent(addmemberbtn)
                        .addGap(105, 105, 105)
                        .addComponent(backbtn))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void removebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removebtnActionPerformed
        // TODO add your handling code here:
         int index= list.getSelectedIndex();
      if(index>-1)
     {
       String memberName=list.getSelectedValue();
       User Member = userDB.getUserByUsername(memberName);
      groupmanage.removeMember(Member.getUserId(),group.getGroupName());
        filllist();
       
     }
   
       else
     {
          JOptionPane.showMessageDialog(this, "You Should Select a member to remove", "  Message ", JOptionPane.PLAIN_MESSAGE);
     }
        
    }//GEN-LAST:event_removebtnActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        primaryFrame.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        setVisible(false);
        primaryFrame.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void promotebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promotebtnActionPerformed
        // TODO add your handling code here:
             int index= list.getSelectedIndex();
      if(index>-1)
     {
       String memberName=list.getSelectedValue();
      User Member = userDB.getUserByUsername(memberName);
      groupmanage.promoteToAdmin(Member.getUserId(),group.getGroupName());
       filllist();
       
     }
   
       else
     {
          JOptionPane.showMessageDialog(this, "You Should Select a Member to promote", "  Message ", JOptionPane.PLAIN_MESSAGE);
     }
       
        
    }//GEN-LAST:event_promotebtnActionPerformed

    private void addmemberbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addmemberbtnActionPerformed
        // TODO add your handling code here
        String usernametoadd = JOptionPane.showInputDialog("Enter username:"); 
        //lw cancel aw close
        if (usernametoadd == null) {

          return;
        }
        //lw a5tar ok mn8er ma yd5l
      else  if (usernametoadd.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "You should enter a username", "Message", JOptionPane.PLAIN_MESSAGE);
         
        }
      
      //lw d5l username
      else 
        {
            User member = userDB.getUserByUsername(usernametoadd); //hageb User mn username
             //check lw user mawgod asln
            if (member == null) {
                JOptionPane.showMessageDialog(this, "User not found!", "Message", JOptionPane.PLAIN_MESSAGE); // lw mafesh User bl name dah
               
            } 
            // if user found
            // get id of the receiver of the req
            else if (member != null)
            {
                   String MemberId = member.getUserId();
                   ArrayList<String> membersIDs= group.getMembersIDs();
                   groupmanage.addMember(MemberId,group.getGroupName());
                   filllist();
                   

            }

        } 
    }//GEN-LAST:event_addmemberbtnActionPerformed

  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addmemberbtn;
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> list;
    private javax.swing.JButton promotebtn;
    private javax.swing.JButton removebtn;
    // End of variables declaration//GEN-END:variables
}
