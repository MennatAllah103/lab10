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
import Backend.UserDataBase;
import Backend.UserLog;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
        loadFriendsPosts();
        loadFriendsStories();
    }

    private void loadFriendsPosts() {
        friendsPostsPanel.removeAll();

        friendsPostsPanel.setLayout(new BoxLayout(friendsPostsPanel, BoxLayout.Y_AXIS));
        PostDataBase postDatabase = PostDataBase.getInstance();
        ArrayList<Post> friendPosts = postDatabase.ViewFriendsPosts(user.getUserId());
        friendsPostsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friendsPostsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendsPostsScrollPane.setViewportView(friendsPostsPanel);

        for (Post post : friendPosts) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            User friend = database.getUserById(post.getAuthorID());
            String friendUsername = friend.getUsername();

            JLabel contentLabel = new JLabel(post.getContent());
            JLabel usernameLabel = new JLabel("Posted by: " + friendUsername);
            usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));  
            postPanel.add(usernameLabel);
            postPanel.add(contentLabel);

            if (post.getImagePath() != null && !post.getImagePath().isEmpty()) {
                JLabel imageLabel = new JLabel(new ImageIcon(post.getImagePath()));
                postPanel.add(imageLabel);
            }

            friendsPostsPanel.add(postPanel);
        }

        friendsPostsPanel.revalidate();
        friendsPostsPanel.repaint();

    }

    private void loadFriendsStories() {
        friendsStoriesPanel.removeAll();

        friendsStoriesPanel.setLayout(new BoxLayout(friendsStoriesPanel, BoxLayout.Y_AXIS));
        StoryDataBase storyDatabase = StoryDataBase.getInstance();
        ArrayList<Story> friendStories = storyDatabase.ViewFriendsStories(user.getUserId());
        friendsStoriesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        friendsStoriesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        friendsStoriesScrollPane.setViewportView(friendsStoriesPanel);

        for (Story story : friendStories) {
            JPanel postPanel = new JPanel();
            postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
            postPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            
            User friend = database.getUserById(story.getAuthorID());
            String friendUsername = friend.getUsername();
            
            JLabel usernameLabel = new JLabel("Posted by: " + friendUsername);
            usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));  // Optional: Customize font style
            postPanel.add(usernameLabel);

            JLabel contentLabel = new JLabel(story.getContent());
            postPanel.add(contentLabel);

            if (story.getImagePath() != null && !story.getImagePath().isEmpty()) {
                JLabel imageLabel = new JLabel(new ImageIcon(story.getImagePath()));
                postPanel.add(imageLabel);
            }

            friendsStoriesPanel.add(postPanel);
        }

        friendsStoriesPanel.revalidate();
        friendsStoriesPanel.repaint();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        manageFriends = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        btnVisitProfile = new javax.swing.JButton();
        friendsStoriesScrollPane = new javax.swing.JScrollPane();
        friendsStoriesPanel = new javax.swing.JPanel();
        friendsPostsScrollPane = new javax.swing.JScrollPane();
        friendsPostsPanel = new javax.swing.JPanel();
        btnAddPost = new javax.swing.JButton();
        btnAddStory = new javax.swing.JButton();
        storiesLabel = new javax.swing.JLabel();
        postsLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Newsfeed");
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

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        btnVisitProfile.setText("Visit Profile");
        btnVisitProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVisitProfileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout friendsStoriesPanelLayout = new javax.swing.GroupLayout(friendsStoriesPanel);
        friendsStoriesPanel.setLayout(friendsStoriesPanelLayout);
        friendsStoriesPanelLayout.setHorizontalGroup(
            friendsStoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );
        friendsStoriesPanelLayout.setVerticalGroup(
            friendsStoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        friendsStoriesScrollPane.setViewportView(friendsStoriesPanel);

        javax.swing.GroupLayout friendsPostsPanelLayout = new javax.swing.GroupLayout(friendsPostsPanel);
        friendsPostsPanel.setLayout(friendsPostsPanelLayout);
        friendsPostsPanelLayout.setHorizontalGroup(
            friendsPostsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 346, Short.MAX_VALUE)
        );
        friendsPostsPanelLayout.setVerticalGroup(
            friendsPostsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 195, Short.MAX_VALUE)
        );

        friendsPostsScrollPane.setViewportView(friendsPostsPanel);

        btnAddPost.setText("Add Post");
        btnAddPost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddPostActionPerformed(evt);
            }
        });

        btnAddStory.setText("Add Story");
        btnAddStory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddStoryActionPerformed(evt);
            }
        });

        storiesLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        storiesLabel.setText("Stories");

        postsLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        postsLabel.setText("Posts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(140, 140, 140))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(friendsPostsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(friendsStoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(manageFriends)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton)
                                .addGap(26, 26, 26)
                                .addComponent(btnVisitProfile)
                                .addGap(34, 34, 34)
                                .addComponent(btnAddPost)
                                .addGap(29, 29, 29)
                                .addComponent(btnAddStory))
                            .addComponent(storiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)))
                .addComponent(logout)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageFriends)
                    .addComponent(searchButton)
                    .addComponent(btnVisitProfile)
                    .addComponent(btnAddPost)
                    .addComponent(btnAddStory))
                .addGap(18, 18, 18)
                .addComponent(storiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendsStoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(postsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendsPostsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        log.logOut(user);
        // JOptionPane.showMessageDialog(this, "Logging out","Message",JOptionPane.PLAIN_MESSAGE);
        JLabel messageLabel = new JLabel("  Bye " + user.getUsername() + " !");
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

        if (choice == JOptionPane.YES_OPTION) {
            log.logOut(user);
            JLabel messageLabel = new JLabel("  Bye " + user.getUsername() + " !");
            messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JOptionPane.showMessageDialog(this, messageLabel, "Logging out !", JOptionPane.PLAIN_MESSAGE);

            setVisible(false);
            home.setVisible(true);

        } else if (choice == JOptionPane.NO_OPTION) {

            JOptionPane.showMessageDialog(this, "You are still logged in.", "Message", JOptionPane.PLAIN_MESSAGE);
            setVisible(true);

        }

    }//GEN-LAST:event_formWindowClosing

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        // TODO add your handling code here:
        SearchFrame searchframe = new SearchFrame(this, user, manage);
        setVisible(false);
        searchframe.setVisible(true);

    }//GEN-LAST:event_searchButtonActionPerformed

    private void btnVisitProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVisitProfileActionPerformed
        ViewProfile profile = new ViewProfile(this, manage);
        profile.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnVisitProfileActionPerformed

    private void btnAddPostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddPostActionPerformed
        AddPostFront addPost = new AddPostFront(this);
        addPost.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAddPostActionPerformed

    private void btnAddStoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddStoryActionPerformed
        AddStoryFront addStory = new AddStoryFront(this);
        addStory.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAddStoryActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPost;
    private javax.swing.JButton btnAddStory;
    private javax.swing.JButton btnVisitProfile;
    private javax.swing.JPanel friendsPostsPanel;
    private javax.swing.JScrollPane friendsPostsScrollPane;
    private javax.swing.JPanel friendsStoriesPanel;
    private javax.swing.JScrollPane friendsStoriesScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logout;
    private javax.swing.JButton manageFriends;
    private javax.swing.JLabel postsLabel;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel storiesLabel;
    // End of variables declaration//GEN-END:variables
}
