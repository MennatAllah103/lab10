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
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingWorker;

/**
 *
 * @author daree
 */
public class Newsfeed extends javax.swing.JFrame {

    ViewProfile profile;
    UserDataBase database = UserDataBase.getDatabase();
     PostDataBase postDatabase = PostDataBase.getInstance();
     StoryDataBase storyDatabase = StoryDataBase.getInstance();
    User user = UserDataBase.getCurrentUser();
    UserLog log = new UserLog(UserDataBase.getDatabase());
    Home home = Home.getInstance();
    Management manage = new Management();

    public Newsfeed() {
        initComponents();
        loadFriendsPosts();
        loadFriendsStories();
        progressBar.setIndeterminate(true);
        progressBar.setString("Refreshing...");
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);

    }

    private void loadFriendsPosts() {
        friendsPostsPanel.removeAll();

        friendsPostsPanel.setLayout(new BoxLayout(friendsPostsPanel, BoxLayout.Y_AXIS));
       
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
            LocalDateTime timestamp = post.getTimestamp();
            String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

            JLabel contentLabel = new JLabel(post.getContent());
            postPanel.add(contentLabel);

            JLabel usernameLabel = new JLabel("Posted by: " + friendUsername + " on " + formattedTimestamp);
            usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
            postPanel.add(usernameLabel);

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
        storyDatabase.updateStories();
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

            LocalDateTime timestamp = story.getTimestamp();
            String formattedTimestamp = timestamp.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));

            JLabel usernameLabel = new JLabel("Posted by: " + friendUsername + " on " + formattedTimestamp);
            usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
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
        btnAddPost = new javax.swing.JButton();
        btnAddStory = new javax.swing.JButton();
        storiesLabel = new javax.swing.JLabel();
        postsLabel = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        GrpBtn = new javax.swing.JButton();
        CreateGrpBtn = new javax.swing.JButton();
        friendsPostsScrollPane = new javax.swing.JScrollPane();
        friendsPostsPanel = new javax.swing.JPanel();

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
            .addGap(0, 384, Short.MAX_VALUE)
        );
        friendsStoriesPanelLayout.setVerticalGroup(
            friendsStoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        friendsStoriesScrollPane.setViewportView(friendsStoriesPanel);

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

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        GrpBtn.setText("My Groups");
        GrpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GrpBtnActionPerformed(evt);
            }
        });

        CreateGrpBtn.setText("Create New Group");
        CreateGrpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateGrpBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout friendsPostsPanelLayout = new javax.swing.GroupLayout(friendsPostsPanel);
        friendsPostsPanel.setLayout(friendsPostsPanelLayout);
        friendsPostsPanelLayout.setHorizontalGroup(
            friendsPostsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 428, Short.MAX_VALUE)
        );
        friendsPostsPanelLayout.setVerticalGroup(
            friendsPostsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        friendsPostsScrollPane.setViewportView(friendsPostsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(friendsStoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(postsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(friendsPostsScrollPane))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CreateGrpBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(143, 143, 143)
                        .addComponent(logout)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(storiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(manageFriends)
                                .addGap(18, 18, 18)
                                .addComponent(searchButton)
                                .addGap(26, 26, 26)
                                .addComponent(btnVisitProfile)
                                .addGap(34, 34, 34)
                                .addComponent(btnAddPost)
                                .addGap(29, 29, 29)
                                .addComponent(btnAddStory)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(GrpBtn)))
                        .addGap(23, 23, 23))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logout)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnRefresh)
                                .addComponent(CreateGrpBtn)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(manageFriends)
                    .addComponent(searchButton)
                    .addComponent(btnVisitProfile)
                    .addComponent(btnAddPost)
                    .addComponent(btnAddStory)
                    .addComponent(GrpBtn))
                .addGap(18, 18, 18)
                .addComponent(storiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendsStoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(postsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(friendsPostsScrollPane)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed

        log.logOut(user);
        // JOptionPane.showMessageDialog(this, "Logging out","Message",JOptionPane.PLAIN_MESSAGE);
        JLabel messageLabel = new JLabel("  Bye " + user.getUsername() + " !");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JOptionPane.showMessageDialog(this, messageLabel, "Logging out !", JOptionPane.PLAIN_MESSAGE);
        setVisible(false);
        home.setVisible(true);

    }//GEN-LAST:event_logoutActionPerformed

    private void manageFriendsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageFriendsActionPerformed

        ManageFriends manager = new ManageFriends(this, user, manage);
        manager.setVisible(true);
        setVisible(false);

    }//GEN-LAST:event_manageFriendsActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed

    }//GEN-LAST:event_formWindowClosed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

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

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed

        progressBar.setVisible(true);
        
        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {

                loadFriendsPosts();
                loadFriendsStories();
                return null;
            }

            @Override
            protected void done() {

                progressBar.setVisible(false);
            }
        };
        worker.execute();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void GrpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GrpBtnActionPerformed
        // TODO add your handling code here:
        ViewMyGroups v = new ViewMyGroups();
        v.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_GrpBtnActionPerformed

    private void CreateGrpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateGrpBtnActionPerformed
        // TODO add your handling code here:
        CreateGroup CG = new CreateGroup(this);
        CG.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_CreateGrpBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateGrpBtn;
    private javax.swing.JButton GrpBtn;
    private javax.swing.JButton btnAddPost;
    private javax.swing.JButton btnAddStory;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnVisitProfile;
    private javax.swing.JPanel friendsPostsPanel;
    private javax.swing.JScrollPane friendsPostsScrollPane;
    private javax.swing.JPanel friendsStoriesPanel;
    private javax.swing.JScrollPane friendsStoriesScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logout;
    private javax.swing.JButton manageFriends;
    private javax.swing.JLabel postsLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel storiesLabel;
    // End of variables declaration//GEN-END:variables
}
