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
    
    public  ArrayList<User> search (String searchString)
    {
        ArrayList<User> searchResults= new ArrayList<>();
        for(User u : AllUsers)
        {
            if(u.getUsername().contains(searchString))
            {
                searchResults.add(u);
            }
        }
        
        return searchResults;
        
    }
    
}
