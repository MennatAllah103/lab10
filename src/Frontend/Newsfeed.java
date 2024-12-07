/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;



import Backend.Management;
import Backend.User;
import Backend.UserDataBase;
import Backend.UserLog;
import javax.swing.JOptionPane;

/**
 *
 * @author LENOVO
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnManageFriends = new javax.swing.JButton();
        btnVisitProfile = new javax.swing.JButton();
        btnAddPost = new javax.swing.JButton();
        btnAddStory = new javax.swing.JButton();
        btnLogout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        ViewPostsBtn = new javax.swing.JButton();
        ViewStoriesBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Newsfeed");

        btnManageFriends.setBackground(new java.awt.Color(255, 204, 255));
        btnManageFriends.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnManageFriends.setText("Manage Friends");
        btnManageFriends.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManageFriendsActionPerformed(evt);
            }
        });

        btnVisitProfile.setBackground(new java.awt.Color(255, 204, 255));
        btnVisitProfile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnVisitProfile.setText("Visit Profile");
        btnVisitProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisitProfileActionPerformed(evt);
            }
        });

        btnAddPost.setBackground(new java.awt.Color(255, 204, 255));
        btnAddPost.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddPost.setText("Add Post");
        btnAddPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPostActionPerformed(evt);
            }
        });

        btnAddStory.setBackground(new java.awt.Color(255, 204, 255));
        btnAddStory.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnAddStory.setText("Add Story");
        btnAddStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStoryActionPerformed(evt);
            }
        });

        btnLogout.setBackground(new java.awt.Color(255, 204, 255));
        btnLogout.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Newsfeed");

        ViewPostsBtn.setBackground(new java.awt.Color(255, 204, 255));
        ViewPostsBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ViewPostsBtn.setText("View Posts");
        ViewPostsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewPostsBtnActionPerformed(evt);
            }
        });

        ViewStoriesBtn.setBackground(new java.awt.Color(255, 204, 255));
        ViewStoriesBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        ViewStoriesBtn.setText("View Stories");
        ViewStoriesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ViewStoriesBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
            .addGroup(layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ViewPostsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ViewStoriesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnManageFriends, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnVisitProfile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(61, 61, 61)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddStory, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAddPost, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(62, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(146, 146, 146))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddStory, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnManageFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddPost, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVisitProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ViewPostsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ViewStoriesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnVisitProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisitProfileActionPerformed

        ViewProfile p = new ViewProfile(this, manage);
        p.setVisible(true);
    }//GEN-LAST:event_btnVisitProfileActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed

        log.logOut(user);
        JOptionPane.showMessageDialog(this, "Logging out and returning to home");
        home.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_logoutActionPerformed

    private void addstoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addstoryActionPerformed

        AddStoryFront story = new AddStoryFront(this);
        story.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_addstoryActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        home.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void addPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPostActionPerformed

        AddPostFront post = new AddPostFront(this);
        post.setVisible(true);
        this.setVisible(false);


    }//GEN-LAST:event_addPostActionPerformed

    private void ManageFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ManageFriendsActionPerformed
        // TODO add your handling code here:
        ManageFriends manager = new ManageFriends(this, user, manage);
        manager.setVisible(true);
        this.dispose();
        home.setVisible(false);


    }//GEN-LAST:event_ManageFriendsActionPerformed

    private void btnManageFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManageFriendsActionPerformed
        ManageFriends manager = new ManageFriends(this, user, manage);
        manager.setVisible(true);
        this.dispose();
        home.setVisible(false);
    }//GEN-LAST:event_btnManageFriendsActionPerformed

    private void btnAddStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStoryActionPerformed
       AddStoryFront story = new AddStoryFront(this);
        story.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAddStoryActionPerformed

    private void btnAddPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPostActionPerformed
        AddPostFront addPost = new AddPostFront(this);
        addPost.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAddPostActionPerformed

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       home.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void ViewPostsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewPostsBtnActionPerformed
        // TODO add your handling code here:
        ViewPost v = new ViewPost();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ViewPostsBtnActionPerformed

    private void ViewStoriesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ViewStoriesBtnActionPerformed
        // TODO add your handling code here:
        ViewStory v = new ViewStory();
        v.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_ViewStoriesBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ViewPostsBtn;
    private javax.swing.JButton ViewStoriesBtn;
    private javax.swing.JButton btnAddPost;
    private javax.swing.JButton btnAddStory;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnManageFriends;
    private javax.swing.JButton btnVisitProfile;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}