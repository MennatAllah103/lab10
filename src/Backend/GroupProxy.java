/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

/**
 *
 * @author daree
 */
public class GroupProxy {
    
     private Group group;
    private User user;

    public GroupProxy(Group group, User user) {
        this.group = group;
        this.user = user;
    }

    public boolean isPrimaryAdmin() {
        return group.getPrimaryAdminId().equals(user.getUserId());
    }

    public boolean isAdmin() {
        return group.getAdminsIDs().contains(user.getUserId());
    }

    public boolean isMember() {
        return group.getMembersIDs().contains(user.getUserId());
    }

    public Group getGroup()
    {
       return group ;
               }

    
}
