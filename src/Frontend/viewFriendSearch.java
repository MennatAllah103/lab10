/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.Management;
import Backend.Post;
import Backend.PostDataBase;
import Backend.Story;
import Backend.StoryDataBase;
import Backend.User;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author daree
 */
public class viewFriendSearch extends javax.swing.JFrame {

    /**
     * Creates new form viewFriendSearch
     */
     
    Newsfeed newsfeed;
    User currentuser;
    Management manage;
    User user;
    public viewFriendSearch(Newsfeed newsfeed, User currentuser, Management manage,User user) {
        initComponents();
     
        this.newsfeed = newsfeed;
        this.currentuser = currentuser;
        this.manage = manage;
        this.user=user;
        
        
        String username = user.getUsername();
        
        usernameLabel.setText(username);
        String bio = user.getBio();
        bioLabel.setText(bio);
        loadStories();
        loadPosts();
        reloadProfilePhotos();
    }
    
     private void loadStories(){
        storiesPanel.removeAll();
        storiesPanel.setLayout(new BoxLayout(storiesPanel, BoxLayout.Y_AXIS));
    
        StoryDataBase storyDatabase = StoryDataBase.getInstance();
        storyDatabase.updateStories();
        ArrayList<Story> userStories = storyDatabase.ViewUserStories(user.getUserId());
    
        storiesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        storiesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        storiesScrollPane.setViewportView(storiesPanel);
        storiesPanel.setPreferredSize(new Dimension(400, 800));
       

        for (Story story : userStories) {
            JPanel storyPanel = new JPanel();
            storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));
            storyPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel contentLabel = new JLabel(story.getContent());
            storyPanel.add(contentLabel);

            if (story.getImagePath() != null && !story.getImagePath().isEmpty()) {
                JLabel imageLabel = new JLabel(new ImageIcon(story.getImagePath()));
               storyPanel.add(imageLabel);
            }

            storiesPanel.add(storyPanel);
        }
       

        storiesPanel.revalidate();
        storiesPanel.repaint();
        
    }
 private void loadPosts() {
        jPanel1.removeAll();

        jPanel1.setLayout(new BoxLayout(jPanel1, BoxLayout.Y_AXIS));
        PostDataBase postDatabase = PostDataBase.getInstance();
        ArrayList<Post> userPosts = postDatabase.ViewUserPosts(user.getUserId());
        jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        for (Post post : userPosts) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            JLabel contentLabel = new JLabel(post.getContent());
            postPanel.add(contentLabel);

            if (post.getImagePath() != null && !post.getImagePath().isEmpty()) {
                JLabel imageLabel = new JLabel(new ImageIcon(post.getImagePath()));
                    postPanel.add(imageLabel);
            }

            jPanel1.add(postPanel);
           
        }
        
        jScrollPane1.setViewportView(jPanel1);
        jPanel1.setPreferredSize(new Dimension(400, 800));

        jPanel1.revalidate();
        jPanel1.repaint();
        

    }
 
      public void reloadProfilePhotos() {
        String defaultProfilePath = "/resources/defaultProfilePhoto.jpeg"; 
        String defaultCoverPath = "/resources/defaultCoverPhoto.jpg";

        if (user.getProfilePhoto().equals("defaultProfilePhoto.jpg")) {
            profilePhotoLabel.setIcon(new ImageIcon(getClass().getResource(defaultProfilePath)));
        } else {
            profilePhotoLabel.setIcon(new ImageIcon(new ImageIcon(user.getProfilePhoto())
                    .getImage().getScaledInstance(profilePhotoLabel.getWidth(), profilePhotoLabel.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        }

        if (user.getCoverPhoto().equals("defaultCoverPhoto.jpg")) {
            coverPhotoLabel.setIcon(new ImageIcon(getClass().getResource(defaultCoverPath)));
        } else {
            coverPhotoLabel.setIcon(new ImageIcon(new ImageIcon(user.getCoverPhoto())
                    .getImage().getScaledInstance(coverPhotoLabel.getWidth(), coverPhotoLabel.getHeight(), java.awt.Image.SCALE_SMOOTH)));
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

        profilePhotoLabel = new javax.swing.JLabel();
        coverPhotoLabel = new javax.swing.JLabel();
        removeFriend = new javax.swing.JButton();
        blockbtn = new javax.swing.JButton();
        backbtn = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        bioLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        storiesScrollPane = new javax.swing.JScrollPane();
        storiesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        removeFriend.setText("Remove Friend");
        removeFriend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFriendActionPerformed(evt);
            }
        });

        blockbtn.setText("Block");
        blockbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                blockbtnActionPerformed(evt);
            }
        });

        backbtn.setText("Back to Newsfeed");
        backbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backbtnActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Stories");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Posts");

        storiesPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout storiesPanelLayout = new javax.swing.GroupLayout(storiesPanel);
        storiesPanel.setLayout(storiesPanelLayout);
        storiesPanelLayout.setHorizontalGroup(
            storiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 385, Short.MAX_VALUE)
        );
        storiesPanelLayout.setVerticalGroup(
            storiesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 475, Short.MAX_VALUE)
        );

        storiesScrollPane.setViewportView(storiesPanel);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 631, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(profilePhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                            .addComponent(bioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(removeFriend, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addComponent(blockbtn, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(coverPhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(167, 167, 167)
                        .addComponent(backbtn)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(185, 185, 185))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                        .addComponent(storiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(14, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(coverPhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                            .addComponent(profilePhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(removeFriend)
                                .addComponent(blockbtn))
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backbtn)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(storiesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(116, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void blockbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_blockbtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_blockbtnActionPerformed

    private void backbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backbtnActionPerformed
        // TODO add your handling code here:
          setVisible(false);
        newsfeed.setVisible(true);
    }//GEN-LAST:event_backbtnActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
          setVisible(false);
        newsfeed.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void removeFriendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFriendActionPerformed
        // TODO add your handling code here:
        
        
    }//GEN-LAST:event_removeFriendActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backbtn;
    private javax.swing.JLabel bioLabel;
    private javax.swing.JButton blockbtn;
    private javax.swing.JLabel coverPhotoLabel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JButton removeFriend;
    private javax.swing.JPanel storiesPanel;
    private javax.swing.JScrollPane storiesScrollPane;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
