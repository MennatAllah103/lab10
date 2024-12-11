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
    if(members.contains(member))
       return false;
    else{
        members.remove(member);
        return true;
    
   }  
}
public boolean promoteToAdmin(User member,String groupName)
{
      Group group=database.getGroupByname(groupName);
    ArrayList<User> members=group.getMembers();
    ArrayList<User> admins=group.getAdmins();
    
    if(members.contains(member) && !admins.contains(member))
    {admins.add(member);
    return true;}
    
    else return false;
    
} 

public boolean DemoteFromAdmin(User member,String groupName)
{
      Group group=database.getGroupByname(groupName);
    ArrayList<User> members=group.getMembers();
    ArrayList<User> admins=group.getAdmins();
    
    if(admins.contains(member))
    {admins.remove(member);
    return true;}
    
    else return false;
    
} 






    
}