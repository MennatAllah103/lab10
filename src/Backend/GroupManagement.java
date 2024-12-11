/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import static Backend.GroupDataBase.saveGroupToFile;
import java.util.ArrayList;

/**
 *
 * @author LENOVO
 */
public class GroupManagement {
    GroupDataBase database=GroupDataBase.getInstance();

  
    //Admin
   public boolean addMember(User member,String groupName) 
   {
    Group group=database.getGroupByname(groupName);
    ArrayList<User> members=group.getMembers();
    if(members.contains(member))   
       return false;
    else{
        members.add(member);
        return true;
    }
   }
    public boolean removeMember(User member,String groupName)
    {
     Group group=database.getGroupByname(groupName);
    ArrayList<User> members=group.getMembers();
    if(!members.contains(member))
       return false;
    else{
        members.remove(member);
        return true;
    
   }  
}
    
    
    //PrimaryAdmin
public boolean promoteToAdmin(User member,String groupName,User currentuser)
{
    Group group=database.getGroupByname(groupName);
    ArrayList<User> members=group.getMembers();
    ArrayList<User> admins=group.getAdmins();
    
    if(members.contains(member) && !admins.contains(member) && currentuser.getUserId().equals(group.getAdminId()))
    {admins.add(member);
    return true;}
    
    else return false;
    
} 

public boolean DemoteFromAdmin(User admin,String groupName, User currentuser)
{
    Group group=database.getGroupByname(groupName);
    ArrayList<User> admins=group.getAdmins();
    
    if(admins.contains(admin)&& currentuser.getUserId().equals(group.getAdminId()))
    {admins.remove(admin);
    return true;}
    
    else{ System.out.println("Current user is not primary admin,can't demote admin");
        return false;}
    
} 
 public void deleteGroup(String groupName,User currentuser) {
        Group group = database.getGroupByname(groupName);
        if (group ==null ) {
          System.out.println("Group is null");
        }
        else if(!currentuser.getUserId().equals(group.getAdminId()))
            System.out.println("Current user not primary admin,can't delete group");
        else
            GroupDataBase.getGroups().remove(group);
    }  









  
}