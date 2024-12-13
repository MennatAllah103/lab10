/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;
import java.util.UUID;

/**
 *
 * @author LENOVO
 */
public class Group {
    
    private String groupName;
    private String description;
    private String groupPhoto;
    private String groupId;
    private String primaryAdminId;
    private ArrayList<String>membersIDs;
    private ArrayList<String> adminsIDs;
    private ArrayList<String> postIDs;
   
        private Group( GroupBuilder builder ) {
         this.groupName = builder.groupName;
        this.description = builder.description;
        this.groupPhoto = builder.groupPhoto;
        this.groupId = builder.groupId ;
        this.primaryAdminId = builder.primaryAdminId;
        this.membersIDs = builder.membersIDs ;
        this.adminsIDs = builder.adminsIDs;
        this.postIDs = builder.postIDs ;
        
     
}



    public static class GroupBuilder {
        private String groupName;
        private String description;
        private String groupPhoto;
        private String groupId;
        private String primaryAdminId;
        private ArrayList<String> membersIDs = new ArrayList<>();
        private ArrayList<String> adminsIDs = new ArrayList<>();
        private ArrayList<String> postIDs = new ArrayList<>();

        public GroupBuilder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public GroupBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public GroupBuilder setGroupPhoto(String groupPhoto) {
            this.groupPhoto = groupPhoto;
            return this;
        }

        public GroupBuilder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public GroupBuilder setPrimaryAdminId(String primaryAdminId) {
            this.primaryAdminId = primaryAdminId;
            return this;
        }

        public GroupBuilder addMember(String memberId) {
            this.membersIDs.add(memberId);
            return this;
        }

        public GroupBuilder addAdmin(String adminId) {
            this.adminsIDs.add(adminId);
            return this;
        }

        public GroupBuilder addPost(String postId) {
            this.postIDs.add(postId);
            return this;
        }
        
      public Group build() {
            return new Group(this);

    }
    }
    public String getGroupName() {
        return groupName;
    }

    public String getDescription() {
        return description;
    }

    public String getGroupPhoto() {
        return groupPhoto;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getPrimaryAdminId() {
        return primaryAdminId;
    }

    public ArrayList<String> getMembersIDs() {
        return membersIDs;
    }

    public ArrayList<String> getAdminsIDs() {
        return adminsIDs;
    }

    public ArrayList<String> getPostIDs() {
        return postIDs;
    }

  
}

   

