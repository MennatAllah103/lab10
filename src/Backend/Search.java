/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.util.ArrayList;

/**
 *
 * @author daree
 */
public class Search {
    
    UserDataBase userDB=UserDataBase.getDatabase();
    ArrayList<User> AllUsers =userDB.getAllUsers();
     GroupDataBase groupDB=GroupDataBase.getInstance();
    ArrayList<Group> AllGroups = groupDB.getGroups();
    
    
    
    public  ArrayList<User> searchUser (String searchString)
    {
        ArrayList<User> searchResultsUser= new ArrayList<>();
        for(User u : AllUsers)
        {
            if(u.getUsername().contains(searchString))
            {
                searchResultsUser.add(u);
            }
        }
        return searchResultsUser;
    }
    
    public ArrayList<Group> searchGroup (String searchString)
    {
        ArrayList<Group> searchResultsGroup= new ArrayList<>();
       for(Group g : AllGroups)
        {
           
             if(g.getGroupName().contains(searchString))
            {
                searchResultsGroup.add(g);
            }
            
        }
       return searchResultsGroup;
          
    }

}
