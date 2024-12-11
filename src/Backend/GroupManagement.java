/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;


import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class GroupManagement {
    GroupDataBase database=GroupDataBase.getInstance();
    GroupRequestDatabase requestDatabase = GroupRequestDatabase.getinstance(); 
    UserDataBase userData=UserDataBase.getDatabase();
     ArrayList<GroupRequests>requests=requestDatabase.getALLRequests();
     PostDataBase postData=PostDataBase.getInstance();
     
    //Admin
   public void addMember(User member,String groupName) 
   {  User currentuser = UserDataBase.getCurrentUser();
    Group group=database.getGroupByname(groupName);
      // (group == null) {
        //System.out.println("Group not found");
       // return false;
    //}
    ArrayList<User> members=group.getMembers();
   // if(members.contains(member))   
       //return false;
    //else{
        members.add(member);
         GroupDataBase.saveGroupToFile(GroupDataBase.getGroups());
        //return true;
    //}
   }
    public void removeMember(User member,String groupName)
    {  User currentuser = UserDataBase.getCurrentUser();
     Group group=database.getGroupByname(groupName);
     /*if (group == null) {
     System.out.println("Group not found");
     return false;
     }*/
    ArrayList<User> members=group.getMembers();
    ArrayList<User> admins=group.getAdmins();
  /*  if(!members.contains(member))
       return false;
    else if(!admins.contains(currentuser))
    {  System.out.println("current user not admin , can not edit memebers");
       return false;}
else if(member.getUserId().equals(group.getPrimaryAdminId())){
         System.out.println("can't remove primary admin");
         return false;
   
}
       else */
        members.remove(member);
         GroupDataBase.saveGroupToFile(GroupDataBase.getGroups());
       // return true;
    
   } 
    
    
     public void acceptrequest(GroupRequests request)
    
     {
       String memberId= request.getMemberId();
       String groupId=request.getGroupId();
       User newMember=userData.getUserById(memberId);
       Group group=database.getGroupById(groupId);
       database.addGroup(group);
      ArrayList<User> members= group.getMembers();
      members.add(newMember);
       requests.remove(request);
       requestDatabase.saveFile(requests);
      
    
    
    
     }
    public void Declinerequest(GroupRequests request)
    
     {
       
       deleterequest(request);
    
    
    
     }
    
      public void deleterequest(GroupRequests request)
    {
        requests.remove(request);
     
       requestDatabase.saveFile(requests);
    }
    
    
   /* public void addPosttoGroup(Post p)
    
    {
    User currentUser = UserDataBase.getCurrentUser();
    
  
    }
*/
   
    
    
    //PrimaryAdmin
public void promoteToAdmin(User member,String groupName)
{    User currentuser = UserDataBase.getCurrentUser();
    Group group=database.getGroupByname(groupName);
     /* if (group == null) {
        System.out.println("Group not found");
        return false;
    }*/
    ArrayList<User> members=group.getMembers();
    ArrayList<User> admins=group.getAdmins();
    
   /* if(members.contains(member) && !admins.contains(member) && currentuser.getUserId().equals(group.getAdminId()))
    {*/
    admins.add(member);
     GroupDataBase.saveGroupToFile(GroupDataBase.getGroups());
    //return true;}
    
    //else return false;*/
    
} 

public void DemoteFromAdmin(User admin,String groupName)
{   User currentuser = UserDataBase.getCurrentUser();
    Group group=database.getGroupByname(groupName);
      /*if (group == null) {
        System.out.println("Group not found");
        return false;
    }*/
    ArrayList<User> admins=group.getAdmins();
    
    //if(admins.contains(admin)&& currentuser.getUserId().equals(group.getPrimaryAdminId()))
   // {
    admins.remove(admin);
     GroupDataBase.saveGroupToFile(GroupDataBase.getGroups());
   // return true;}
    
    //else{ System.out.println("Current user is not primary admin,can't demote admin");
        //return false;}
    
} 
 public void deleteGroup(String groupName) {
     User currentuser = UserDataBase.getCurrentUser();
        Group group = database.getGroupByname(groupName);
        /*if (group ==null ) {
          System.out.println("Group is null");
        }
        else if(!currentuser.getUserId().equals(group.getPrimaryAdminId()))
            System.out.println("Current user not primary admin,can't delete group");
        else{*/
            GroupDataBase.getGroups().remove(group);
            GroupDataBase.saveGroupToFile(GroupDataBase.getGroups());
    //}  
 }

 
 
 
 
 
 
 
 
 
 
}