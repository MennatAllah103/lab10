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
    
    private String name;
    private String description;
    private String photo;
    private ArrayList<User>members;
    private String groupId;
    private String primaryAdminId;
    private String adminId;
    private User primaryAdmin;
    private ArrayList<User> admins;
    private ArrayList<Post> posts;
     
     
     
     private Group( GroupBuilder builder ) {
         this.name = builder.name;
        this.description = builder.description;
        this.photo = builder.photo;
        this.groupId = builder.groupId != null ? builder.groupId : UUID.randomUUID().toString();
        this.primaryAdmin = builder.primaryAdmin;
        this.primaryAdminId = builder.primaryAdminId;
        this.adminId = builder.adminId;
        this.members = builder.members != null ? builder.members : new ArrayList<>();
        this.admins = builder.admins != null ? builder.admins : new ArrayList<>();
        this.posts = builder.posts != null ? builder.posts : new ArrayList<>();
        
         if (this.primaryAdmin != null) {
            if (!this.members.contains(this.primaryAdmin)) {
                this.members.add(this.primaryAdmin);
            }
            if (!this.admins.contains(this.primaryAdmin)) {
                this.admins.add(this.primaryAdmin);
            }
        }
}
 public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhoto() {
        return photo;
    }

    public ArrayList<User> getMembers() {
        return members;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getPrimaryAdminId() {
        return primaryAdminId;
    }

    public String getAdminId() {
        return adminId;
    }

    public User getPrimaryAdmin() {
        return primaryAdmin;
    }

    public ArrayList<User> getAdmins() {
        return admins;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

   public static class GroupBuilder {
        private String name;
        private String description;
        private String photo;
        private ArrayList<User> members = new ArrayList<>();
        private String groupId;
        private String primaryAdminId;
        private String adminId;
        private User primaryAdmin;
        private ArrayList<User> admins = new ArrayList<>();
        private ArrayList<Post> posts = new ArrayList<>();

       
        public GroupBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public GroupBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public GroupBuilder setPhoto(String photo) {
            this.photo = photo;
            return this;
        }

        public GroupBuilder setGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public GroupBuilder setPrimaryAdmin(User primaryAdmin) {
            this.primaryAdmin = primaryAdmin;
            return this;
        }

        public GroupBuilder setPrimaryAdminId(String primaryAdminId) {
            this.primaryAdminId = primaryAdminId;
            return this;
        }

        public GroupBuilder setAdminId(String adminId) {
            this.adminId = adminId;
            return this;
        }

        public GroupBuilder setMembers(ArrayList<User> members) {
            this.members = members;
            return this;
        }

        public GroupBuilder setAdmins(ArrayList<User> admins) {
            this.admins = admins;
            return this;
        }

        public GroupBuilder setPosts(ArrayList<Post> posts) {
            this.posts = posts;
            return this;
        }

   
        public Group build() {
            return new Group(this);
        }
    }    
}
