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
     
     
     
     public Group(String name, String description, String photo,User creator) {
        this.name = name;
        this.description = description;
        this.photo = photo;
        this.members= new ArrayList<>();
        this.groupId=UUID.randomUUID().toString();
        this.members = new ArrayList<>();
        this.admins = new ArrayList<>();
        this.primaryAdmin = creator;
        this.members.add(creator);
        this.admins.add(creator);
       
    }
    public void setPrimaryAdminId(String primaryAdminId) {
        this.primaryAdminId = primaryAdminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setPrimaryAdmin(User primaryAdmin) {
        this.primaryAdmin = primaryAdmin;
    }



    public User getPrimaryAdmin() {
        return primaryAdmin;
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

    public ArrayList<User> getAdmins() {
        return admins;
    }
   

  

    public void demoteFromAdmin(User admin) {
        admins.remove(admin);
    }
       
    
    
    
    
}
