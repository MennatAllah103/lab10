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
        String username = UserDataBase.getCurrentUser().getUsername();
        usernameLabel.setText(username);
        String bio = UserDataBase.getCurrentUser().getBio();
        bioTextLabel.setText(bio);
        reloadProfilePhotos();
        reloadBio();
        loadFriendsList();
        loadPosts();

    }

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
        String profilePhotoPath = UserDataBase.getCurrentUser().getProfilePhoto();
        String defaultProfilePhoto = "defaultProfilePhoto.jpeg";
        if (profilePhotoPath != null && !profilePhotoPath.equals(defaultProfilePhoto)) {
            profilePhotoLabel.setIcon(new ImageIcon(new ImageIcon(profilePhotoPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } else {
            profilePhotoLabel.setIcon(new ImageIcon(getClass().getResource(defaultProfilePhoto)));
        }
        String coverPhotoPath = UserDataBase.getCurrentUser().getCoverPhoto();
        String defaultCoverPhoto = "defaultCoverPhoto.jpg";
        if (coverPhotoPath != null && !coverPhotoPath.equals(defaultCoverPhoto)) {
            coverPhotoLabel.setIcon(new ImageIcon(new ImageIcon(coverPhotoPath).getImage().getScaledInstance(712, 136, Image.SCALE_SMOOTH)));
        } else {
            coverPhotoLabel.setIcon(new ImageIcon(getClass().getResource(defaultCoverPhoto)));
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

        coverPhotoLabel = new javax.swing.JLabel();
        btnEditProfile = new javax.swing.JButton();
        bioLabel = new javax.swing.JLabel();
        usernameLabel = new javax.swing.JLabel();
        friendsScrollPane = new javax.swing.JScrollPane();
        friendsJList = new javax.swing.JList<>();
        bioTextLabel = new javax.swing.JLabel();
        btnBack1 = new javax.swing.JButton();
        profilePhotoLabel = new javax.swing.JLabel();
        postsScrollPane = new javax.swing.JScrollPane();
        postsPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Profile");

        coverPhotoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProfileManagmentFrontend/defaultCoverPhoto.jpg"))); // NOI18N

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

        profilePhotoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProfileManagmentFrontend/defaultProfilePhoto.jpeg"))); // NOI18N

        javax.swing.GroupLayout postsPanelLayout = new javax.swing.GroupLayout(postsPanel);
        postsPanel.setLayout(postsPanelLayout);
        postsPanelLayout.setHorizontalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 439, Short.MAX_VALUE)
        );
        postsPanelLayout.setVerticalGroup(
            postsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 357, Short.MAX_VALUE)
        );

        postsScrollPane.setViewportView(postsPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(profilePhotoLabel)
                                    .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(17, 17, 17))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bioTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(friendsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(103, 103, 103)
                        .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 441, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 815, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(83, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bioTextLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(274, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(friendsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(postsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(46, Short.MAX_VALUE))))
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
    private javax.swing.JPanel postsPanel;
    private javax.swing.JScrollPane postsScrollPane;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
