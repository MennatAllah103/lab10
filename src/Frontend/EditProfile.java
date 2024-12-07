/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frontend;

import Backend.ProfileManager;
import Backend.UserDataBase;
import java.awt.Image;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author hp
 */
public class EditProfile extends javax.swing.JFrame {

    ProfileManager manager;
    ViewProfile v;

    /**
     * Creates new form Profile
     */
    public EditProfile(ViewProfile v) {
        initComponents();
        this.v = v;
        manager = new ProfileManager();
        String username = UserDataBase.getCurrentUser().getUsername();
        usernameLabel.setText(username);
        String bio = UserDataBase.getCurrentUser().getBio();
        bioTextArea.setText(bio);
        String profilePhotoPath = UserDataBase.getCurrentUser().getProfilePhoto();
        String defaultProfilePhoto = "defaultProfilePhoto.jpeg";
        if (profilePhotoPath != null && !profilePhotoPath.equals(defaultProfilePhoto)) {
            profilePhotoLabel.setIcon(new ImageIcon(new ImageIcon(profilePhotoPath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
        } 
        String coverPhotoPath = UserDataBase.getCurrentUser().getCoverPhoto();
        String defaultCoverPhoto = "defaultCoverPhoto.jpg";
        if (coverPhotoPath != null && !coverPhotoPath.equals(defaultCoverPhoto)) {
            coverPhotoLabel.setIcon(new ImageIcon(new ImageIcon(coverPhotoPath).getImage().getScaledInstance(712, 136, Image.SCALE_SMOOTH)));
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
        btnUploadProfilePhoto = new javax.swing.JButton();
        btnUploadCoverPhoto = new javax.swing.JButton();
        bioLabel = new javax.swing.JLabel();
        btnSaveBio = new javax.swing.JButton();
        bioScrollPane = new javax.swing.JScrollPane();
        bioTextArea = new javax.swing.JTextArea();
        btnChangePassword = new javax.swing.JButton();
        usernameLabel = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Edit Profile");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        profilePhotoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProfileManagmentFrontend/defaultProfilePhoto.jpeg"))); // NOI18N

        coverPhotoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ProfileManagmentFrontend/defaultCoverPhoto.jpg"))); // NOI18N

        btnUploadProfilePhoto.setBackground(new java.awt.Color(255, 204, 255));
        btnUploadProfilePhoto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUploadProfilePhoto.setText("Upload Profile Photo");
        btnUploadProfilePhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadProfilePhotoActionPerformed(evt);
            }
        });

        btnUploadCoverPhoto.setBackground(new java.awt.Color(255, 204, 255));
        btnUploadCoverPhoto.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnUploadCoverPhoto.setText("Upload Cover Photo");
        btnUploadCoverPhoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUploadCoverPhotoActionPerformed(evt);
            }
        });

        bioLabel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bioLabel.setText("Bio");

        btnSaveBio.setBackground(new java.awt.Color(255, 204, 255));
        btnSaveBio.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSaveBio.setText(" Save Bio");
        btnSaveBio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveBioActionPerformed(evt);
            }
        });

        bioTextArea.setColumns(20);
        bioTextArea.setRows(5);
        bioScrollPane.setViewportView(bioTextArea);

        btnChangePassword.setBackground(new java.awt.Color(255, 204, 255));
        btnChangePassword.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChangePassword.setText("Change Password");
        btnChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChangePasswordActionPerformed(evt);
            }
        });

        btnBack.setBackground(new java.awt.Color(255, 204, 255));
        btnBack.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(btnUploadProfilePhoto))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(bioScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(profilePhotoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(usernameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 684, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnUploadCoverPhoto))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(btnSaveBio, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(141, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBack)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(profilePhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(coverPhotoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnUploadCoverPhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnChangePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usernameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnUploadProfilePhoto, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bioScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSaveBio, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnUploadProfilePhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadProfilePhotoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            profilePhotoLabel.setIcon(icon);
            manager.updatePhoto(filePath, "profile");
            v.reloadProfilePhotos();
            JOptionPane.showMessageDialog(this, "Profile photo updated successfully!");

        }

    }//GEN-LAST:event_btnUploadProfilePhotoActionPerformed

    private void btnSaveBioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveBioActionPerformed
        String bio = bioTextArea.getText();
        String oldBio = UserDataBase.getCurrentUser().getBio();
        if (bio.isEmpty() || bio.equals(oldBio)) {
            JOptionPane.showMessageDialog(this, "No changes detected to save.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        manager.updateBio(bio);
        v.reloadBio();
        JOptionPane.showMessageDialog(this, "Profile updated successfully!");
     
    }//GEN-LAST:event_btnSaveBioActionPerformed

    private void btnUploadCoverPhotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUploadCoverPhotoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            String filePath = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(new ImageIcon(filePath).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            coverPhotoLabel.setIcon(icon);
            manager.updatePhoto(filePath, "cover");
            v.reloadProfilePhotos();
            JOptionPane.showMessageDialog(this, "Cover photo updated successfully!");

        }
    }//GEN-LAST:event_btnUploadCoverPhotoActionPerformed

    private void btnChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChangePasswordActionPerformed
        ChangePassword changePassword = new ChangePassword(manager);
        changePassword.setVisible(true);
    }//GEN-LAST:event_btnChangePasswordActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed

        v.reloadProfilePhotos();
        v.reloadBio();
        this.setVisible(false);
        v.setVisible(true);

    }//GEN-LAST:event_btnBackActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
       v.setVisible(true);
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bioLabel;
    private javax.swing.JScrollPane bioScrollPane;
    private javax.swing.JTextArea bioTextArea;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnChangePassword;
    private javax.swing.JButton btnSaveBio;
    private javax.swing.JButton btnUploadCoverPhoto;
    private javax.swing.JButton btnUploadProfilePhoto;
    private javax.swing.JLabel coverPhotoLabel;
    private javax.swing.JLabel profilePhotoLabel;
    private javax.swing.JLabel usernameLabel;
    // End of variables declaration//GEN-END:variables
}
