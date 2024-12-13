/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import Backend.*;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class GroupManagement {

    GroupDataBase groupDB = GroupDataBase.getInstance();
    ArrayList<Group> groups= groupDB.getGroups();
    GroupRequestDatabase requestDatabase = GroupRequestDatabase.getinstance();
    UserDataBase userDB = UserDataBase.getDatabase();
    ArrayList<GroupRequests> requests = requestDatabase.getALLRequests();
    PostDataBase postData = PostDataBase.getInstance();

    public GroupManagement(GroupDataBase database) {
        this.groupDB = database;
    }

      public void addGroup(Group g) {
  
        for (Group existingGroup : groups) {
            if (existingGroup.getGroupName().equals(g.getGroupName())) {
                System.err.println("Group already exists.");
                return ;
            }
        }

       groups.add(g);
       groupDB.saveGroupToFile(groups);
    }
          
      public ArrayList<Group> viewUserGroups(String userId) {
      
        ArrayList<Group> userGroups = new ArrayList<>();
         for (Group group : groups)
         {
                for (String memberID : group.getMembersIDs()) 
                {
                    if (userId.equals(memberID))
                     {
                        userGroups.add(group);
                       
                    }
                }
                
                 for (String adminID : group.getAdminsIDs()) 
                {
                    if (userId.equals(adminID))
                     {
                        userGroups.add(group);
                       
                    }
                }
                 
                if(userId.equals(group.getPrimaryAdminId()))
                {
                    userGroups.add(group);
                }
         }
        

        return userGroups;
    }
          
       public ArrayList<String> viewUserGroupNames(String userId) {
      
        ArrayList<String> userGroupNames = new ArrayList<>();

            for (Group group : groups) {
                for (String memberID : group.getMembersIDs())
                {
                    if (userId.equals(memberID)) {
                        userGroupNames.add(group.getGroupName());
                       
                    }
                }
                
                   for (String adminID : group.getAdminsIDs()) 
                {
                    if (userId.equals(adminID))
                     {
                        userGroupNames.add(group.getGroupName());
                       
                    }
                }
                 
                if(userId.equals(group.getPrimaryAdminId()))
                {
                    userGroupNames.add(group.getGroupName());
                }
            }
        

        return userGroupNames;
    }
            
        public ArrayList<String> UserGroupIDs(String userId) {
     
        ArrayList<String> userGroupIDs = new ArrayList<>();

  
            for (Group group : groups) 
            {
                for (String memberID : group.getMembersIDs()) 
                {
                    if (userId.equals(memberID)) 
                    {
                        userGroupIDs.add(group.getGroupId());
                       
                    }
                }
                
                     for (String adminID : group.getAdminsIDs()) 
                {
                    if (userId.equals(adminID))
                     {
                        userGroupIDs.add(group.getGroupId());
                       
                    }
                }
                 
                if(userId.equals(group.getPrimaryAdminId()))
                {
                    userGroupIDs.add(group.getGroupId());
                }
            }
        

        return userGroupIDs;
    }
        
        
    
         
     /* public void addMember(User member, String groupName) {
        User currentuser = UserDataBase.getCurrentUser();
        Group group = groupDB.getGroupByname(groupName);
        // (group == null) {
        //System.out.println("Group not found");
        // return false;
        //}
        ArrayList<User> members = group.getMembers();
        // if(members.contains(member))   
        //return false;
        //else{
        members.add(member);
        groupDB.saveGroupToFile(groups);
        //return true;
        //}
    }
    */
/*
    public void removeMember(User member, String groupName) {
        User currentuser = UserDataBase.getCurrentUser();
        Group group = groupDB.getGroupByname(groupName);
        /*if (group == null) {
     System.out.println("Group not found");
     return false;
     }
        ArrayList<User> members = group.getMembers();
        ArrayList<User> admins = group.getAdmins();
          if(!members.contains(member))
       return false;
    else if(!admins.contains(currentuser))
    {  System.out.println("current user not admin , can not edit memebers");
       return false;}
else if(member.getUserId().equals(group.getPrimaryAdminId())){
         System.out.println("can't remove primary admin");
         return false;
   
}
       else 
        members.remove(member);
        groupDB.saveGroupToFile(groups);
        // return true;

    }

    public void acceptrequest(GroupRequests request) {
        String memberId = request.getMemberId();
        String groupId = request.getGroupId();
        User newMember = userData.getUserById(memberId);
        Group group = groupDB.getGroupById(groupId);
        addGroup(group);
        ArrayList<User> members = group.getMembers();
        members.add(newMember);
        requests.remove(request);
        requestDatabase.saveFile(requests);

    }

    public void Declinerequest(GroupRequests request) {

        deleterequest(request);

    }

    public void deleterequest(GroupRequests request) {
        requests.remove(request);

        requestDatabase.saveFile(requests);
    }

    public void addPosttoGroup(Post p, String groupName) {
        User currentUser = UserDataBase.getCurrentUser();
        ArrayList<Post> posts = postData.getPosts();
        posts.add(p);

    }

    //PrimaryAdmin
    public void promoteToAdmin(User member, String groupName) {
        User currentuser = UserDataBase.getCurrentUser();
        Group group = groupDB.getGroupByname(groupName);
        /* if (group == null) {
        System.out.println("Group not found");
        return false;
    }
        ArrayList<User> members = group.getMembers();
        ArrayList<User> admins = group.getAdmins();

        /* if(members.contains(member) && !admins.contains(member) && currentuser.getUserId().equals(group.getAdminId()))
    {
        admins.add(member);
        groupDB.saveGroupToFile(groups);
        //return true;}

        //else return false;
    }

    public void DemoteFromAdmin(User admin, String groupName) {
        User currentuser = UserDataBase.getCurrentUser();
        Group group = groupDB.getGroupByname(groupName);
        /*if (group == null) {
        System.out.println("Group not found");
        return false;
    }
        ArrayList<User> admins = group.getAdmins();

        //if(admins.contains(admin)&& currentuser.getUserId().equals(group.getPrimaryAdminId()))
        // {
        admins.remove(admin);
        groupDB.saveGroupToFile(groups);
        // return true;}

        //else{ System.out.println("Current user is not primary admin,can't demote admin");
        //return false;}
    }

    public void deleteGroup(String groupName) {
        User currentuser = UserDataBase.getCurrentUser();
        Group group = groupDB.getGroupByname(groupName);
        /*if (group ==null ) {
          System.out.println("Group is null");
        }
        else if(!currentuser.getUserId().equals(group.getPrimaryAdminId()))
            System.out.println("Current user not primary admin,can't delete group");
        else{
        groupDB.getGroups().remove(group);
        groupDB.saveGroupToFile(groups);
        //}  
    }    
    */ 
}
