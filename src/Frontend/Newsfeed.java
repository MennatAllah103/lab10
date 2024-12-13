/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.Management;
import Backend.Notification;
import Backend.NotificationManager;
import Backend.Post;
import Backend.PostDataBase;
import Backend.Request;
import Backend.Story;
import Backend.StoryDataBase;
import Backend.User;
import Backend.UserDataBase;
import Backend.UserLog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
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
    NotificationManager notifManager = new NotificationManager();
    Management friendManager = new Management();

    public Newsfeed() {
        initComponents();
        loadFriendsPosts();
        loadFriendsStories();
        progressBar.setIndeterminate(true);
        progressBar.setString("Refreshing...");
        progressBar.setStringPainted(true);
        progressBar.setVisible(false);
        notificationScrollPane.setVisible(false);
    }

    public void populateNotificationPanel() {
        notificationPanel1.removeAll();

        ArrayList<Notification> notifications = notifManager.getNotificationsForUser(user.getUserId());

        for (Notification notification : notifications) {

            JPanel notificationItemPanel = new JPanel();
            notificationItemPanel.setLayout(new BoxLayout(notificationItemPanel, BoxLayout.X_AXIS));

           notificationItemPanel.setPreferredSize(new Dimension(33, 78));
           notificationItemPanel.setOpaque(false);

            JLabel profilePicLabel = new JLabel();

            if (notification.getType().equalsIgnoreCase("Friend Request")) {
                ArrayList<Request> requests = friendManager.getUserReceivedRequests(user.getUserId());
                final Request[] currentRequest = new Request[1];

                for (Request request : requests) {
                    if (request.getReceiverID().equals(user.getUserId())) {
                        currentRequest[0] = request;
                        break;
                    }
                }

                if (currentRequest[0] != null) {
                    try {
                        String senderId = currentRequest[0].getSenderID();
                        User sender = database.getUserById(senderId);
                        String profilePicPath = sender.getProfilePhoto();
                        ImageIcon icon = new ImageIcon(profilePicPath);

                        BufferedImage image = ImageIO.read(new File(profilePicPath));
                        BufferedImage circleImage = makeCircularImage(image);
                        profilePicLabel = new JLabel(new ImageIcon(circleImage));
                        profilePicLabel.setPreferredSize(new Dimension(50, 50));
                        profilePicLabel.setMaximumSize(new Dimension(50, 50));
                        profilePicLabel.setAlignmentY(Component.CENTER_ALIGNMENT);
                    } catch (Exception e) {
                        profilePicLabel = new JLabel("No Image");
                    }

                    notificationItemPanel.add(profilePicLabel);

                    JPanel messagePanel = new JPanel();
                    messagePanel.setOpaque(false);
                    messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
                    messagePanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

                    JLabel notificationLabel = new JLabel(notification.getMessage());
                    messagePanel.add(notificationLabel);

                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
                    String formattedTimestamp = notification.getTimeStamp().format(formatter);
                    JLabel timestampLabel = new JLabel("Received at: " + formattedTimestamp);
                    messagePanel.add(timestampLabel);

                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setOpaque(false);
                    JButton acceptButton = new JButton("Accept");
                    JButton declineButton = new JButton("Decline");

                    acceptButton.addActionListener(e -> {
                        friendManager.acceptrequest(currentRequest[0]);
                        notifManager.deleteNotification(notification);
                        notificationPanel1.remove(notificationItemPanel);
                        notificationPanel1.revalidate();
                        notificationPanel1.repaint();
                        JOptionPane.showMessageDialog(null, "Friend Request Accepted.");
                    });

                    declineButton.addActionListener(e -> {
                        friendManager.declinerequest(currentRequest[0]);
                        notifManager.deleteNotification(notification);
                        notificationPanel1.remove(notificationItemPanel);
                        notificationPanel1.revalidate();
                        notificationPanel1.repaint();
                        JOptionPane.showMessageDialog(null, "Friend Request Declined.");
                    });

                    buttonPanel.add(acceptButton);
                    buttonPanel.add(declineButton);
                    messagePanel.add(buttonPanel);

                    notificationItemPanel.add(messagePanel);
                }
            }

            notificationPanel1.add(notificationItemPanel);
        }

        notificationPanel1.setLayout(new BoxLayout(notificationPanel1, BoxLayout.Y_AXIS));
        notificationPanel1.revalidate();
        notificationPanel1.repaint();
    }

    private BufferedImage makeCircularImage(BufferedImage source) {
        int diameter = Math.min(source.getWidth(), source.getHeight());

      
        int targetDiameter = 50; 
        BufferedImage resizedImage = new BufferedImage(targetDiameter, targetDiameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2dResize = resizedImage.createGraphics();
        g2dResize.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2dResize.drawImage(source, 0, 0, targetDiameter, targetDiameter, null);
        g2dResize.dispose();

   
        BufferedImage circleImage = new BufferedImage(targetDiameter, targetDiameter, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = circleImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setClip(new Ellipse2D.Double(0, 0, targetDiameter, targetDiameter));
        g2.drawImage(resizedImage, 0, 0, null);
        g2.dispose();

        return circleImage;
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
        friendsPostsScrollPane = new javax.swing.JScrollPane();
        friendsPostsPanel = new javax.swing.JPanel();
        btnAddPost = new javax.swing.JButton();
        btnAddStory = new javax.swing.JButton();
        storiesLabel = new javax.swing.JLabel();
        postsLabel = new javax.swing.JLabel();
        btnRefresh = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        btnNotifications = new javax.swing.JButton();
        notificationScrollPane = new javax.swing.JScrollPane();
        notificationPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

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
            .addGap(0, 424, Short.MAX_VALUE)
        );
        friendsStoriesPanelLayout.setVerticalGroup(
            friendsStoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
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

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnNotifications.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Frontend/pinknotif.png"))); // NOI18N
        btnNotifications.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotificationsActionPerformed(evt);
            }
        });

        notificationScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        notificationPanel1.setBackground(new java.awt.Color(255, 255, 255));
        notificationPanel1.setForeground(new java.awt.Color(255, 255, 255));
        notificationPanel1.setLayout(new javax.swing.BoxLayout(notificationPanel1, javax.swing.BoxLayout.LINE_AXIS));
        notificationScrollPane.setViewportView(notificationPanel1);

        jButton1.setText("view Groups");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Create a Group");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(manageFriends)
                            .addComponent(btnRefresh))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(searchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(72, 72, 72)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(logout)
                                .addGap(15, 15, 15))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnVisitProfile)
                                .addGap(133, 133, 133)
                                .addComponent(btnAddStory)
                                .addGap(41, 41, 41)
                                .addComponent(jButton2)
                                .addGap(36, 36, 36)
                                .addComponent(jButton1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnAddPost)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(friendsPostsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(postsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(friendsStoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(storiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnNotifications, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(notificationScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(26, 26, 26))))))
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
                                .addComponent(searchButton)))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(manageFriends)
                        .addComponent(btnVisitProfile)
                        .addComponent(btnAddPost)
                        .addComponent(btnAddStory)
                        .addComponent(jButton1)
                        .addComponent(jButton2))
                    .addComponent(btnNotifications))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(notificationScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(storiesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(friendsStoriesScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(postsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(friendsPostsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
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

    private void btnNotificationsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotificationsActionPerformed
        if (notificationScrollPane.isVisible()) {
            notificationScrollPane.setVisible(false);
        } else {
            notificationScrollPane.setVisible(true);
            populateNotificationPanel();
        }
        this.revalidate(); // Ensure layout is recalculated
        this.repaint();

    }//GEN-LAST:event_btnNotificationsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        ViewMyGroups viewgroups = new ViewMyGroups(this);
        viewgroups.setVisible(true);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        setVisible(false);
        CreateGroup createG= new CreateGroup(this);
        createG.setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddPost;
    private javax.swing.JButton btnAddStory;
    private javax.swing.JButton btnNotifications;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnVisitProfile;
    private javax.swing.JPanel friendsPostsPanel;
    private javax.swing.JScrollPane friendsPostsScrollPane;
    private javax.swing.JPanel friendsStoriesPanel;
    private javax.swing.JScrollPane friendsStoriesScrollPane;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton logout;
    private javax.swing.JButton manageFriends;
    private javax.swing.JPanel notificationPanel1;
    private javax.swing.JScrollPane notificationScrollPane;
    private javax.swing.JLabel postsLabel;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JLabel storiesLabel;
    // End of variables declaration//GEN-END:variables
}
