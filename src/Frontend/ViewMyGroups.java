/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import java.util.ArrayList;
import Backend.GroupDataBase;
import Backend.Group;
import Backend.GroupManagement;
import Backend.GroupProxy;
import Backend.Request;
import Backend.UserDataBase;
import Backend.User;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 *
 * @author yaras
 */
public class ViewMyGroups extends javax.swing.JFrame {
    
    UserDataBase userDB = UserDataBase.getDatabase();
    User user = userDB.getCurrentUser();
    GroupDataBase GDB = GroupDataBase.getInstance();
    GroupManagement groupmanage = new GroupManagement();
    Newsfeed newsfeed;

    /**
     * Creates new form ViewGroups
     */
    public ViewMyGroups( Newsfeed newsfeed) {
      
     initComponents();
     this.newsfeed=newsfeed;
     filllist();
       
}

 public void filllist()
 {
       ArrayList<Group> groups = groupmanage.viewUserGroups(user.getUserId());
        DefaultListModel<String> model = new DefaultListModel();

     for (Group group : groups)
     {
         
                String groupName = group.getGroupName();
                model.addElement(groupName);
   
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

        jLabel1 = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        list = new javax.swing.JList<>();
        viewGroupbtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("             Your Groups");

        back.setBackground(new java.awt.Color(255, 204, 255));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(list);

        viewGroupbtn.setText("view Group");
        viewGroupbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewGroupbtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(296, Short.MAX_VALUE)
                .addComponent(viewGroupbtn)
                .addGap(14, 14, 14))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                    .addGap(18, 18, 18)
                    .addComponent(back)
                    .addContainerGap(22, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(viewGroupbtn)
                .addContainerGap(217, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(198, 198, 198)
                            .addComponent(back)))
                    .addContainerGap(18, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        newsfeed.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void viewGroupbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewGroupbtnActionPerformed
        // TODO add your handling code here:
           int index= list.getSelectedIndex();
  
     if (index > -1) {
        String groupName = list.getSelectedValue();
        Group group = GDB.getGroupByname(groupName);
        GDB.setCurrentGroup(group);

        GroupProxy groupProxy = new GroupProxy(group, user);
        
        if (groupProxy.isPrimaryAdmin()) {
            primaryAdminFrame primaryFrame = new primaryAdminFrame(this, group);
            setVisible(false);
            primaryFrame.setVisible(true);
        } else if (groupProxy.isAdmin()) {
           AdminFrame adminFrame = new AdminFrame(this, group);
           setVisible(false);
           adminFrame.setVisible(true);
        } else if (groupProxy.isMember()) {
            MemberFrame memberFrame = new MemberFrame(this,group);
            setVisible(false);
            memberFrame.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "You do not have permission to view this group.", "Access Denied", JOptionPane.WARNING_MESSAGE);
        }
    } 
     else {
        JOptionPane.showMessageDialog(this, "You should select a group to view.", "Message", JOptionPane.PLAIN_MESSAGE);

     }

    }//GEN-LAST:event_viewGroupbtnActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> list;
    private javax.swing.JButton viewGroupbtn;
    // End of variables declaration//GEN-END:variables
}
