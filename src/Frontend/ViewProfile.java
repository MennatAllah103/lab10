/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.UserDataBase;
import java.awt.Image;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import Backend.*;
import Backend.User;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import Backend.*;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 *
 * @author hp
 */
public class ViewProfile extends javax.swing.JFrame {

    Newsfeed n;
    UserDataBase userDatabase = UserDataBase.getDatabase();
    User u = userDatabase.getCurrentUser();
    Management manage;

    public ViewProfile(Newsfeed n, Management manage) {
        initComponents();
        this.n = n;
        this.manage = manage;
        String username = u.getUsername();
        usernameLabel.setText(username);
        String bio = u.getBio();
        bioTextLabel.setText(bio);
        reloadProfilePhotos();
        reloadBio();
        loadFriendsList();
        loadPosts();
     //   loadStories();
    }
    
//    private void loadStories(){
//        storiesPanel.removeAll();
//        storiesPanel.setLayout(new BoxLayout(storiesPanel, BoxLayout.Y_AXIS));
    
//        StoryDataBase storyDatabase = StoryDataBase.getInstance();
//        ArrayList<Story> userStories = storyDatabase.ViewUserStories(u.getUserId());
    
//        storiesScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        storiesScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        storiesScrollPane.setViewportView(storiesPanel);
//
//        for (Story story : userStories) {
//            JPanel storyPanel = new JPanel();
//            storyPanel.setLayout(new BoxLayout(storyPanel, BoxLayout.Y_AXIS));
//            storyPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
//
//            JLabel contentLabel = new JLabel(story.getContent());
//            storyPanel.add(contentLabel);
//
//            if (story.getImagePath() != null && !story.getImagePath().isEmpty()) {
//                JLabel imageLabel = new JLabel(new ImageIcon(story.getImagePath()));
//               storyPanel.add(imageLabel);
//            }
//
//            storiesPanel.add(storyPanel);
//        }
//
//        storiesPanel.revalidate();
//        storiesPanel.repaint();
//        
//    }

    private void loadPosts() {
        postsPanel.removeAll();

        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        PostDataBase postDatabase = PostDataBase.getInstance();
        ArrayList<Post> userPosts = postDatabase.ViewUserPosts(u.getUserId());
        postsScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        postsScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        postsScrollPane.setViewportView(postsPanel);

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

            postsPanel.add(postPanel);
        }

        postsPanel.revalidate();
        postsPanel.repaint();

    }

    private void loadFriendsList() {

        ArrayList<String> userFriendsIDs = manage.getUserFriendsIDs(u.getUserId());

        DefaultListModel<String> listModel = new DefaultListModel<>();

        if (userFriendsIDs == null || userFriendsIDs.isEmpty()) {
            listModel.addElement("No friends available to display.");
        } else {

            ArrayList<User> userFriends = new ArrayList<>();
            for (String friendId : userFriendsIDs) {
                User friend = userDatabase.getUserById(friendId);
                if (friend != null) {
                    userFriends.add(friend);
                }
            }

            for (User friend : userFriends) {
                listModel.addElement(friend.getUsername() + " (" + (friend.getStatus() ? "Online" : "Offline") + ")");
            }
        }

        friendsJList.setModel(listModel);
    }

    public void reloadBio() {

        String bio = UserDataBase.getCurrentUser().getBio();
        bioTextLabel.setText(bio);
    }

    public void reloadProfilePhotos() {
        String defaultProfilePath = "/resources/defaultProfilePhoto.jpeg"; 
        String defaultCoverPath = "/resources/defaultCoverPhoto.jpg";

        if (u.getProfilePhoto().equals("defaultProfilePhoto.jpg")) {
            profilePhotoLabel.setIcon(new ImageIcon(getClass().getResource(defaultProfilePath)));
        } else {
            profilePhotoLabel.setIcon(new ImageIcon(new ImageIcon(u.getProfilePhoto())
                    .getImage().getScaledInstance(profilePhotoLabel.getWidth(), profilePhotoLabel.getHeight(), java.awt.Image.SCALE_SMOOTH)));
        }

        if (u.getCoverPhoto().equals("defaultCoverPhoto.jpg")) {
            coverPhotoLabel.setIcon(new ImageIcon(getClass().getResource(defaultCoverPath)));
        } else {
            coverPhotoLabel.setIcon(new ImageIcon(new ImageIcon(u.getCoverPhoto())
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

        btnEditProfile = new javax.swing.JButton();
        bioLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        friendsScrollPane = new javax.swing.JScrollPane();
        friendsJList = new javax.swing.JList<>();
        bioTextLabel = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        postsScrollPane = new javax.swing.JScrollPane();
        postsPanel = new javax.swing.JPanel();
        labelYourFriends = new javax.swing.JLabel();
        labelYourPosts = new javax.swing.JLabel();
        profilePhotoLabel = new javax.swing.JLabel();
        coverPhotoLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Profile");

        btnEditProfile.setBackground(new java.awt.Color(255, 204, 255));
        btnEditProfile.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnEditProfile.setText("Edit Profile");
        btnEditProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProfileActionPerformed(evt);
            }
        });

        bioLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bioLabel.setText("Bio");

        friendsScrollPane.setViewportView(friendsJList);

        btnBack1.setBackground(new java.awt.Color(255, 204, 255));
        btnBack1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack1.setText("Back");
        btnBack1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBack1ActionPerformed(evt);
            }
        });

        postsPanel.setLayout(new javax.swing.BoxLayout(postsPanel, javax.swing.BoxLayout.LINE_AXIS));
        postsScrollPane.setViewportView(postsPanel);

        labelYourFriends.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelYourFriends.setText("Your Friends");

        labelYourPosts.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        labelYourPosts.setText("Your Posts");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(labelYourFriends)
                                .addComponent(bioTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(friendsScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                                .addComponent(profilePhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 739, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(96, 96, 96)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelYourPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(0, 132, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelYourPosts, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bioTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelYourFriends, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(friendsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProfileActionPerformed
        EditProfile profile = new EditProfile(this);
        profile.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEditProfileActionPerformed

    private void btnBack1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBack1ActionPerformed
        this.setVisible(false);
        n.setVisible(true);
    }//GEN-LAST:event_btnBack1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bioLabel;
    private javax.swing.JLabel bioTextLabel;
    private javax.swing.JButton btnBack1;
    private javax.swing.JButton btnEditProfile;
    private javax.swing.JLabel coverPhotoLabel;
    private javax.swing.JList<String> friendsJList;
    private javax.swing.JScrollPane friendsScrollPane;
    private javax.swing.JLabel labelYourFriends;
    private javax.swing.JLabel labelYourPosts;
    private javax.swing.JPanel postsPanel;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
