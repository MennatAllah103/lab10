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
    ArrayList<GroupRequests> allrequests = requestDatabase.getALLRequests();
    PostDataBase postDB = PostDataBase.getInstance();
    
    //GroupRequestDatabase RequestDB=GroupRequestDatabase.getinstance();
    // ArrayList<GroupRequests> allrequests =RequestDB.getALLRequests();
     
    public GroupManagement() {
     
    }

      public void addGroup(Group g) {
       groups.add(g);
       groupDB.saveGroupToFile(groups);
    }
     
    public void deleteGroup(Group g) {
       groups.remove(g);
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
        
        
    
         
    public void addMember(String UserId, String groupName) {
        Group group = groupDB.getGroupByname(groupName);
        ArrayList<String> membersIDs = group.getMembersIDs();
        membersIDs.add(UserId);
        groupDB.saveGroupToFile(groups);
        
    }
    

    public void removeMember(String UserId, String groupName) {
      
        Group group = groupDB.getGroupByname(groupName);
        ArrayList<String> membersIDs = group.getMembersIDs();
     
    if(membersIDs.contains(UserId))
    {
        membersIDs.remove(UserId);
        groupDB.saveGroupToFile(groups);
    }
    
    }
    
//hageb group mn group name , w ha7ot postid fy array of postsids aly fy group
//hzwd al post fy fileposts
    public void addPosttoGroup(String postId, String groupName) {
      Group group = groupDB.getGroupByname(groupName);
      ArrayList<String> PostsIds=group.getPostIDs();
      PostsIds.add(postId);
 groupDB.saveGroupToFile(groups);
     ArrayList<Post> posts = postDB.getPosts();
     Post post = postDB.GetPostById(postId);
        postDB.addPost(post);

    }
    
      public void deletePostFromGroup(String postId, String groupName) {
      Group group = groupDB.getGroupByname(groupName);
      ArrayList<String> PostsIds=group.getPostIDs();
      PostsIds.remove(postId);
      groupDB.saveGroupToFile(groups);
     ArrayList<Post> posts = postDB.getPosts();
     Post post = postDB.GetPostById(postId);
        postDB.deletePost(post);
        groupDB.saveGroupToFile(groups);

    }
    //PrimaryAdmin
    public void promoteToAdmin(String memberID, String groupName) {
 
     Group group = groupDB.getGroupByname(groupName);
    if (group == null)
    {
        System.out.println("Group not found");
       return;
    }
   
    else
 {

  ArrayList<String> membersIds = group.getMembersIDs();
  ArrayList<String> adminsIds= group.getAdminsIDs();
  
  
    if(membersIds.contains(memberID) && !adminsIds.contains(memberID) )
    {

       membersIds.remove(memberID);
      adminsIds.add(memberID);
      groupDB.saveGroupToFile(groups);
   
    }
}
    
    

    }

    public void DemoteFromAdmin(String adminID, String groupName) {
      
     Group group = groupDB.getGroupByname(groupName);
      if (group == null) {
        System.out.println("Group not found");
        return;
    }
  ArrayList<String> membersIds = group.getMembersIDs();
  ArrayList<String> adminsIds= group.getAdminsIDs();

  if(adminsIds.contains(adminID))
     
  adminsIds.remove(adminID);
  membersIds.add(adminID);
 groupDB.saveGroupToFile(groups);
    }       
      
    
    public void sendrequest(String userId ,String groupId)
    {
        GroupRequests request = new GroupRequests(userId ,groupId);
        allrequests.add(request);
          requestDatabase.saveFile(allrequests);
        
      
        
    }
    public void acceptrequest(GroupRequests request) {
        String memberId = request.getMemberId();
        String groupId = request.getGroupId();
        User newMember = userDB.getUserById(memberId);
        Group group = groupDB.getGroupById(groupId);
        ArrayList<String> membersIDs = group.getMembersIDs();
        membersIDs.add(memberId);
        groupDB.saveGroupToFile(groups);
        allrequests.remove(request);
        requestDatabase.saveFile(allrequests);

    }

    public void Declinerequest(GroupRequests request) {

        deleterequest(request);

    }

    public void deleterequest(GroupRequests request) {
        allrequests.remove(request);
        requestDatabase.saveFile(allrequests);
    }

}
